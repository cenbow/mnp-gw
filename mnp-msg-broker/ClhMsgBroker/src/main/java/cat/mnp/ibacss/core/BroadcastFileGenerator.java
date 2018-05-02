/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.ibacss.core;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.file.FileSender;
import cat.mnp.mq.core.MsgHandlerBase;
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
import org.springframework.util.StringUtils;
import com.telcordia.inpac.ws.jaxb.PortBroadcastMsgType;

/**
 *
 * @author HP-CAT
 */
public class BroadcastFileGenerator extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(BroadcastFileGenerator.class);
    private FileSender fileSender;

    public FileSender getFileSender() {
        return fileSender;
    }

    public void setFileSender(FileSender fileSender) {
        this.fileSender = fileSender;
    }

    private List<String> generateCmd(MessageHeaderType messageHeader, List<PortBroadcastMsgType> broadcastMsgList) throws Exception {
        List<String> cmdList = null;
        if (broadcastMsgList.size() > 0) {
        		logger.debug("broadcastMsgList="+broadcastMsgList);
            cmdList = getMvnoMsgDao().queryMsg(messageHeader, broadcastMsgList);
        } else {
            logger.error("Invalid Broadcast Msg: {}", messageHeader);
        }
        return cmdList;
    }

    private File getFile(MessageHeaderType messageHeader) {
        String dateString = DateFormatUtils.format(new Date(), getFileDateFormat());
        String filename = String.format(getFilenameFormat(), dateString, messageHeader.getMessageID(), messageHeader.getSoapRequestId());
        return new File(FilenameUtils.concat(getFilePath(), filename));
    }

    @Override
    public void processMsg(String msgString) throws Exception {
        ArrayList<PortBroadcastMsgType> broadcastMsgList = new ArrayList<>();
        ArrayList<String> cmdList = new ArrayList<>();

        long startTime = System.currentTimeMillis();
        logger.info("Generating Cmd from 1 soap");

        NPCMessageData npcMessageData = NpcMessageUtils.unMarshal(getJaxbUnMarshaller(), msgString);

        NPCDataType npcDataType = npcMessageData.getNPCData();

        MessageHeaderType messageHeader = npcDataType.getMessageHeader();
        NPCMessageType npcMessages = npcDataType.getNPCMessages();

        ArrayList<PortBroadcastMsgType> tmpBroadcastMsgList = new ArrayList<>();
        tmpBroadcastMsgList.addAll(NpcMessageUtils.listBroadcastMsg(npcMessages));
        broadcastMsgList.addAll(tmpBroadcastMsgList);

        cmdList.addAll(generateCmd(messageHeader, tmpBroadcastMsgList));
        logger.info("Generated Cmd from Broadcast size: {} to Cmd size: {} in {} ms", broadcastMsgList.size(), cmdList.size(), (System.currentTimeMillis() - startTime));

        File file = getFile(messageHeader);
        String cmdString = StringUtils.collectionToDelimitedString(cmdList, "");
        FileUtils.writeStringToFile(file, cmdString, getFileEncoding());

        fileSender.send(file);
    }
}
