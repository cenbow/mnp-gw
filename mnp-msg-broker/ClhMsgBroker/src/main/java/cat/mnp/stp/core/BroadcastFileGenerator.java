/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.stp.core;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.file.FileSender;
import cat.mnp.mq.core.MsgHandlerBase;
import cat.mnp.stp.dao.BroadcastFileGeneratorDao;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import com.telcordia.inpac.ws.jaxb.NPCMessageType;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.util.StringUtils;
import com.telcordia.inpac.ws.jaxb.PortBroadcastMsgType;

/**
 *
 * @author HP-CAT
 */
public class BroadcastFileGenerator extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(BroadcastFileGenerator.class);
    private BroadcastFileGeneratorDao broadcastFileGeneratorDao;
    private FileSender fileSender;

    public BroadcastFileGeneratorDao getBroadcastFileGeneratorDao() {
        return broadcastFileGeneratorDao;
    }

    public void setBroadcastFileGeneratorDao(BroadcastFileGeneratorDao broadcastFileGeneratorDao) {
        this.broadcastFileGeneratorDao = broadcastFileGeneratorDao;
    }

    public FileSender getFileSender() {
        return fileSender;
    }

    public void setFileSender(FileSender fileSender) {
        this.fileSender = fileSender;
    }

    private List<String> generateCmd(MessageHeaderType messageHeader, List<PortBroadcastMsgType> broadcastMsgList) throws Exception {
        List<String> cmdList = null;
        if (broadcastMsgList.size() > 0) {
            cmdList = broadcastFileGeneratorDao.queryMsg(messageHeader, broadcastMsgList);
        } else {
            logger.error("Invalid Broadcast Msg: {}", messageHeader);
        }
        return cmdList;
    }

    private File getFile(long fileId, int checkSum) {
        String dateString = DateFormatUtils.format(new Date(), getFileDateFormat());
        String filename = String.format(getFilenameFormat(), dateString, fileId, checkSum);
        return new File(FilenameUtils.concat(getFilePath(), filename));
    }

    private void doProcessMsg(ArrayList<String> msgStringList) throws Exception {
        ArrayList<PortBroadcastMsgType> broadcastMsgList = new ArrayList<>();
        ArrayList<String> cmdList = new ArrayList<>();

        long startTime = System.currentTimeMillis();
        logger.info("Generating Cmd from {} soaps", msgStringList.size());
        for (String msgString : msgStringList) {
            NPCMessageData npcMessageData = NpcMessageUtils.unMarshal(getJaxbUnMarshaller(), msgString);

            NPCDataType npcDataType = npcMessageData.getNPCData();

            MessageHeaderType messageHeader = npcDataType.getMessageHeader();
            NPCMessageType npcMessages = npcDataType.getNPCMessages();

            ArrayList<PortBroadcastMsgType> tmpBroadcastMsgList = new ArrayList<>();
            tmpBroadcastMsgList.addAll(NpcMessageUtils.listBroadcastMsg(npcMessages));
            broadcastMsgList.addAll(tmpBroadcastMsgList);

            cmdList.addAll(generateCmd(messageHeader, tmpBroadcastMsgList));
        }
        logger.info("Generated Cmd from Broadcast size: {} to Cmd size: {} in {} ms", broadcastMsgList.size(), cmdList.size(), (System.currentTimeMillis() - startTime));

        int checkSum = broadcastMsgList.size();
        long fileId = broadcastFileGeneratorDao.getFileId();

        File file = getFile(fileId, checkSum);
        String cmdString = StringUtils.collectionToDelimitedString(cmdList, "");
        FileUtils.writeStringToFile(file, cmdString, getFileEncoding());

        // FIXME: test exceptiokn
//        if(true)
//        		throw new Exception("Temporary Test!!!");
        fileSender.send(file);
    }

    public void precessMsg(String msgString) throws Exception {
        ArrayList<String> msgStringList = new ArrayList<>();
        msgStringList.add(msgString);

        doProcessMsg(msgStringList);
    }

    @Override
    public void processMsg(List<Message> msgList) throws Exception {
        ArrayList<String> msgStringList = new ArrayList<>();
        for (Message msg : msgList) {
            msgStringList.add(new String(msg.getBody()));
        }

        doProcessMsg(msgStringList);
    }
}
