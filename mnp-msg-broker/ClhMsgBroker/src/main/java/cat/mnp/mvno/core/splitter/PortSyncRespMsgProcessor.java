/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.core.splitter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

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

import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import com.telcordia.inpac.ws.jaxb.NPCMessageType;
import com.telcordia.inpac.ws.jaxb.SyncRespMsgType;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.mq.core.MsgHandlerBase;
import cat.mnp.mvno.dao.MvnoMsgDao;
import cat.mnp.mvno.dao.PortSyncRespDao;
import jaxb.clh.npcbulksync.NPCData;

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

	public void processMsg(Message msg) throws Exception {
		String msgString = new String(msg.getBody());
		NPCMessageData npcMessageData = NpcMessageUtils.unMarshal(getJaxbUnMarshaller(), msgString);
		NPCDataType npcDataType = npcMessageData.getNPCData();
		MessageHeaderType messageHeader = npcDataType.getMessageHeader();
		NPCMessageType npcMessages = npcDataType.getNPCMessages();

		logger.debug(msgString);
		SyncRespMsgType syncRespMsgType = npcMessages.getSynchronisationResponse().get(0);
		logger.trace("{} {} {}",syncRespMsgType.getSyncReqId(), syncRespMsgType.getLocation(), syncRespMsgType.getTimeStamp());
		String clhRemotePath = clhPrefixRemotePath + "/" + syncRespMsgType.getLocation(); // FIXME: correct CLH path
		String gwLocalPath = gwPrefixLocalPath + "/" + syncRespMsgType.getLocation();  // FIXME: correct CLH path
		logger.info("SFTP: "+clhRemotePath +"->"+gwLocalPath);
		ftp(clhRemotePath, gwLocalPath);

		File file = new File(gwLocalPath);
		JAXBContext jaxbContext = JAXBContext.newInstance(NPCData.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		NPCData npcData = (NPCData) jaxbUnmarshaller.unmarshal(file);

		getPortSyncRespDao().insert(npcData);
	}

	private void ftp(String remotePath, String localPath) throws IOException {
		File localPathFile = new File(localPath);
		FileUtils.forceMkdir(localPathFile.getParentFile());
		SftpSession s = esbSftpSessionFactory.getSession();
		FileOutputStream os = new FileOutputStream(localPathFile);
		s.read(remotePath, os);
		s.close();
	}

}
