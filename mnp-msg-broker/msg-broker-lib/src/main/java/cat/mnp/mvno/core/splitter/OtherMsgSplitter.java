/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.core.splitter;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.mq.core.MsgHandlerBase;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multiset;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import com.telcordia.inpac.ws.jaxb.NPCMessageType;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

/**
 *
 * @author HP-CAT
 */
public class OtherMsgSplitter extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(OtherMsgSplitter.class);
    private AmqpTemplate amqpTemplate;
    private MessageProperties msgProperties;
    private String errorText;

    public AmqpTemplate getAmqpTemplate() {
        return amqpTemplate;
    }

    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public MessageProperties getMsgProperties() {
        return msgProperties;
    }

    public void setMsgProperties(MessageProperties msgProperties) {
        this.msgProperties = msgProperties;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    private File getFile(MessageHeaderType messageHeader, String mvnoName) {
        String dateString = DateFormatUtils.format(new Date(), getFileDateFormat());
        String filename = String.format(getFilenameFormat(), dateString, messageHeader.getMessageID(), mvnoName, messageHeader.getSoapRequestId());
        return new File(FilenameUtils.concat(getFilePath(), filename));
    }

    @Override
    public void processMsg(String msgString) throws Exception {
        NPCMessageData npcMessageData = NpcMessageUtils.unMarshal(getJaxbUnMarshaller(), msgString);

        NPCDataType npcDataType = npcMessageData.getNPCData();

        MessageHeaderType messageHeader = npcDataType.getMessageHeader();
        NPCMessageType npcMessages = npcDataType.getNPCMessages();

        List msgList = NpcMessageUtils.listOtherMsg(npcMessages);

        if (msgList.size() > 0) {
            String msgId = messageHeader.getMessageID().toString();
            logger.info("Splitting OtherMsg size: {} orders, msgId: {}", msgList.size(), msgId);
            
            HashMap<String, Object> dataMap = getMvnoMsgDao().splitMsg(messageHeader, msgList);
            ArrayListMultimap<String, Object> mvnoMsgMultimap = (ArrayListMultimap<String, Object>) dataMap.get("data");
            Multiset<String> mvnoCountMultiset =  (Multiset<String>) dataMap.get("count");
            
            HashMap<String, String> mvnoMsgHashMap = new HashMap<>();

            for (String mvnoName : mvnoMsgMultimap.keySet()) {
                List mvnoMsgList = mvnoMsgMultimap.get(mvnoName);
                int count = mvnoCountMultiset.count(mvnoName);

                NPCMessageData mvnoNpcMessageData = NpcMessageUtils.buildNpcMessageData(messageHeader, mvnoMsgList, count);

                logger.debug("Marshaling msg for {} size: {} msisdn", mvnoName, count);
                String msgXml = NpcMessageUtils.marshal(getJaxbMarshaller(), mvnoNpcMessageData);

                mvnoMsgHashMap.put(mvnoName, msgXml);
            }

            for (String mvnoName : mvnoMsgHashMap.keySet()) {
                String msgXml = mvnoMsgHashMap.get(mvnoName);
                
                logger.debug("Writing xml log for {}", mvnoName);
                File file = getFile(messageHeader, mvnoName);
                FileUtils.writeStringToFile(file, msgXml, getFileEncoding());

                msgProperties.setHeader("MvnoName", mvnoName);
                Message msg = new Message(msgXml.getBytes(), msgProperties);
                
                logger.debug("MqMessage for {} received size: {}", mvnoName, msgXml.length());
                amqpTemplate.send(msgId, msg);
                if (!mvnoName.contains(errorText)) {//no error
                    moveFileToDirectory(file, getBackupPath());
                } else {//has error
                    logger.error("Error detected while splitting other msg for mvno: {}, header: {}", mvnoName, messageHeader);
                    moveFileToDirectory(file, getErrorPath());
                }
                logger.debug("MqMessage for {} sent size: {}", mvnoName, msgXml.length());
            }
        } else {
            logger.error("Invalid OtherMsg: {}", messageHeader);
        }
    }
}
