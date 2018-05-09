/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.core.splitter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpSession;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import com.telcordia.inpac.ws.jaxb.NPCMessageType;
import com.telcordia.inpac.ws.jaxb.SyncRespMsgType;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.mq.core.MsgHandlerBase;
import cat.mnp.mvno.dao.MvnoMsgDao;
import cat.mnp.mvno.dao.PortSyncRespDao;
import jaxb.clh.npcbulksync.ActivatedNumberType;
import jaxb.clh.npcbulksync.NPCData;
import miw.sql.util.OracleTypeUtil;
import miw.util.DBHelper;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;

/**
 *
 * @author HP-CAT
 */
public class PortSyncRespMsgProcessor extends MsgHandlerBase {

	private static final Logger logger = LoggerFactory.getLogger(PortSyncRespMsgProcessor.class);
	private static final String MSG_ID = "MsgId";
	private MvnoMsgDao mvnoMsgDao;
	private AmqpTemplate amqpTemplate;
	private AmqpTemplate errorAmqpTemplate;
	private MessageProperties msgProperties;
	private PortSyncRespDao portSyncRespDao;
	private DefaultSftpSessionFactory esbSftpSessionFactory;
	private String clhPrefixRemotePath;
	private String gwPrefixLocalPath;

	public void processMsg(Message msg) throws Exception {
		String msgString = new String(msg.getBody());
		NPCMessageData npcMessageData = NpcMessageUtils.unMarshal(getJaxbUnMarshaller(), msgString);
		NPCDataType npcDataType = npcMessageData.getNPCData();
		MessageHeaderType messageHeader = npcDataType.getMessageHeader();
		NPCMessageType npcMessages = npcDataType.getNPCMessages();

		logger.debug(msgString);
		SyncRespMsgType syncRespMsgType = npcMessages.getSynchronisationResponse().get(0);
		logger.trace("{} {} {}", syncRespMsgType.getSyncReqId(), syncRespMsgType.getLocation(), syncRespMsgType.getTimeStamp());
		String clhRemotePath = clhPrefixRemotePath + "/" + syncRespMsgType.getLocation(); // FIXME: correct CLH path
		String gwLocalPath = gwPrefixLocalPath + "/" + syncRespMsgType.getLocation(); // FIXME: correct CLH path
		logger.info("sftp in: " + clhRemotePath + "->" + gwLocalPath);
		File clhFile = ftpIn(clhRemotePath, gwLocalPath);

		File file = new File(gwLocalPath);
		JAXBContext jaxbContext = JAXBContext.newInstance(NPCData.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		NPCData npcData = (NPCData) jaxbUnmarshaller.unmarshal(file);
		// portSyncRespDao.insert(npcData);
		// distributeMvno(npcData, clhFile, "rmv001"); // FIXME: other add other mvno

		// finally send 4002 to all vendor via fanout
		portSyncRespDao.callStore(npcData);
	}

	private void distributeMvno(NPCData npcData, File clhFile, String mvnoName) throws IOException {
		File mvnoFile = transfromToMvno(clhFile, mvnoName); // transform to specific mvno
		String remotePath = "/ftp/mvno/" + mvnoName + "/"; // esb path
		logger.info("sftp out:" + mvnoFile + " -> " + remotePath);
		ftpOut(mvnoFile, remotePath); // send to esb
	}

	private File transfromToMvno(File clhFile, String mvnoName) {
		// mvnoName; // call store ?
		return clhFile;
	}

	private void ftpOut(File mvnoFile, String remotePath) throws IOException {
		SftpSession s = esbSftpSessionFactory.getSession();
		// s.mkdir(remotePath); // required ? cant recursive create
		String remoteFileStr = remotePath + "/" + mvnoFile.getName();
		FileInputStream in = new FileInputStream(mvnoFile);
		s.write(in, remoteFileStr);
		in.close();
		s.close();
	}

	private File ftpIn(String remotePath, String localPath) throws IOException {
		File localPathFile = new File(localPath);
		FileUtils.forceMkdir(localPathFile.getParentFile());
		SftpSession s = esbSftpSessionFactory.getSession();
		FileOutputStream os = new FileOutputStream(localPathFile);
		s.read(remotePath, os);
		os.close();
		s.close();
		return localPathFile;
	}

	public String getClhPrefixRemotePath() {
		return clhPrefixRemotePath;
	}

	public void setClhPrefixRemotePath(String clhPrefixRemotePath) {
		this.clhPrefixRemotePath = clhPrefixRemotePath;
	}

	public String getGwPrefixLocalPath() {
		return gwPrefixLocalPath;
	}

	public void setGwPrefixLocalPath(String gwPrefixLocalPath) {
		this.gwPrefixLocalPath = gwPrefixLocalPath;
	}
	public DefaultSftpSessionFactory getEsbSftpSessionFactory() {
		return esbSftpSessionFactory;
	}

	public void setEsbSftpSessionFactory(DefaultSftpSessionFactory esbSftpSessionFactory) {
		this.esbSftpSessionFactory = esbSftpSessionFactory;
	}

	public PortSyncRespDao getPortSyncRespDao() {
		return portSyncRespDao;
	}

	public void setPortSyncRespDao(PortSyncRespDao portSyncRespDao) {
		this.portSyncRespDao = portSyncRespDao;
	}

	@Override
	public MvnoMsgDao getMvnoMsgDao() {
		return mvnoMsgDao;
	}

	public void setMvnoMsgDao(MvnoMsgDao mvnoMsgDao) {
		this.mvnoMsgDao = mvnoMsgDao;
	}

	public AmqpTemplate getAmqpTemplate() {
		return amqpTemplate;
	}

	public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}

	public AmqpTemplate getErrorAmqpTemplate() {
		return errorAmqpTemplate;
	}

	public void setErrorAmqpTemplate(AmqpTemplate errorAmqpTemplate) {
		this.errorAmqpTemplate = errorAmqpTemplate;
	}

	public MessageProperties getMsgProperties() {
		return msgProperties;
	}

	public void setMsgProperties(MessageProperties msgProperties) {
		this.msgProperties = msgProperties;
	}

}
