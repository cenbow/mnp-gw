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
public class MvnoPortSyncRespMsgSplitter extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(MvnoPortSyncRespMsgSplitter.class);
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

    private File getFile(MessageHeaderType messageHeader, String status) {
        String dateString = DateFormatUtils.format(new Date(), getFileDateFormat());
        String filename = String.format(getFilenameFormat(), dateString, messageHeader.getMessageID(), status, messageHeader.getSoapRequestId());
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
            logger.info("Blocking MvnoPortSyncRespMsg size: {} orders, msgId: {}", msgList.size(), msgId); // alway 1

            HashMap<String, Object> dataMap = getMvnoMsgDao().splitMsg(messageHeader, msgList); // return mvnoName Map
            ArrayListMultimap<String, Object> statusMsgMultimap = (ArrayListMultimap<String, Object>) dataMap.get("data");
            Multiset<String> statusCountMultiset =  (Multiset<String>) dataMap.get("count");

            HashMap<String, String> msgHashMap = new HashMap<>();

            for (String status : statusMsgMultimap.keySet()) {
                List statusMsgList = statusMsgMultimap.get(status);
                int count = statusCountMultiset.count(status);

                NPCMessageData newNpcMessageData = NpcMessageUtils.buildNpcMessageData(messageHeader, statusMsgList, count);

                logger.debug("Marshaling msg for {} size: {} msisdn", status, count);
                String msgXml = NpcMessageUtils.marshal(getJaxbMarshaller(), newNpcMessageData);

                msgHashMap.put(status, msgXml);
            }

            for (String status : msgHashMap.keySet()) {
                String msgXml = msgHashMap.get(status);

                logger.debug("Writing xml log for {}", status);
                File file = getFile(messageHeader, status);
                FileUtils.writeStringToFile(file, msgXml, getFileEncoding());

                msgProperties.setHeader("Status", status);
                Message msg = new Message(msgXml.getBytes(), msgProperties);

                logger.debug("MqMessage for {} received size: {}", status, msgXml.length());
                amqpTemplate.send(msgId, msg);
                if (!status.contains(errorText)) {//no error
                    moveFileToDirectory(file, getBackupPath());
                } else {//has error
                    logger.error("Error detected while blocking mvno orderid msg; status: {}, header: {}", status, messageHeader);
                    moveFileToDirectory(file, getErrorPath());
                }
                logger.debug("MqMessage for {} sent size: {}", status, msgXml.length());
            }
        } else {
            logger.error("Invalid MvnoOrderIdMsg: {}", messageHeader);
        }
    }
}
