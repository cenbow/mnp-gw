/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.clh.core.splitter;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.mq.core.MsgHandlerBase;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multiset;
import com.telcordia.inpac.ws.jaxb.MessageFooterType;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import com.telcordia.inpac.ws.jaxb.NPCMessageType;
import com.telcordia.inpac.ws.jaxb.PortRespMsgType;
import java.io.File;
import java.math.BigInteger;
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
public class PortRespBlockerMsgSplitter extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(PortRespBlockerMsgSplitter.class);
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

    private File getFile(MessageHeaderType messageHeader) {
        String dateString = DateFormatUtils.format(new Date(), getFileDateFormat());
        String filename = String.format(getFilenameFormat(), dateString, messageHeader.getMessageID(), messageHeader.getSoapRequestId());
        return new File(FilenameUtils.concat(getFilePath(), filename));
    }

    @Override
    public void processMsg(String msgString) throws Exception {
        NPCMessageData npcMessageData = NpcMessageUtils.unMarshal(getJaxbUnMarshaller(), msgString);

        NPCDataType npcDataType = npcMessageData.getNPCData();

        MessageHeaderType messageHeader = npcDataType.getMessageHeader();
        NPCMessageType npcMessages = npcDataType.getNPCMessages();

        List<PortRespMsgType> msgList = NpcMessageUtils.listOutboundOtherMsg(npcMessages);

        if (msgList.size() > 0) {
            String msgId = messageHeader.getMessageID().toString();
            logger.info("Splitting PortRespMsg size: {} orders, msgId: {}", msgList.size(), msgId);

            HashMap<String, Object> dataMap = getMvnoMsgDao().splitMsg(messageHeader, msgList);
            ArrayListMultimap<String, Object> msgStatusMultimap = (ArrayListMultimap<String, Object>) dataMap.get("data");
            Multiset<String> statusCountMultiset = (Multiset<String>) dataMap.get("count");

            HashMap<String, String> msgStatusHashMap = new HashMap<>();

            for (String status : msgStatusMultimap.keySet()) {
                List statusList = msgStatusMultimap.get(status);
                int count = statusCountMultiset.count(status);

                NPCMessageData statusNpcMessageData = NpcMessageUtils.buildNpcMessageData(messageHeader, statusList, count);

                logger.debug("Marshaling msg status {} size: {} msisdn", status, count);
                String msgXml = NpcMessageUtils.marshal(getJaxbMarshaller(), statusNpcMessageData);

                msgStatusHashMap.put(status, msgXml);
            }

            for (String status : msgStatusHashMap.keySet()) {
                String msgXml = msgStatusHashMap.get(status);

                msgProperties.getHeaders().clear();
                msgProperties.setHeader("Status", status);
                Message msg = new Message(msgXml.getBytes(), msgProperties);

                if (!status.contains(errorText)) {//no error
                    logger.debug("MqMessage for status {} received size: {}", status, msgXml.length());
                    amqpTemplate.send(msgId, msg);
                    logger.debug("MqMessage for status {} sent size: {}", status, msgXml.length());
                } else {//has error
                    logger.error("Error detected while splitting port resp msg; header: {}, status: {}", messageHeader, status);
                    File file = getFile(messageHeader);
                    FileUtils.writeStringToFile(file, msgXml, getFileEncoding());
                    moveFileToDirectory(file, getErrorPath());
                }
            }
        } else {
            logger.error("Invalid PortRespMsg: {}", messageHeader);
        }
    }
}
