/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.core.importer;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.mq.core.MsgHandlerBase;
import com.google.common.base.Strings;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import com.telcordia.inpac.ws.jaxb.NPCMessageType;
import com.telcordia.inpac.ws.jaxb.PortDeactMsgType;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
public class ClhPortDeactMsgImporter extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(ClhPortDeactMsgImporter.class);
    private AmqpTemplate amqpTemplate;
    private AmqpTemplate errorAmqpTemplate;
    private MessageProperties msgProperties;
    private String errorText;

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
    public void processMsg(Message msg) throws Exception {
        Map<String, Object> mqHeaders = msg.getMessageProperties().getHeaders();
        String msgString = new String(msg.getBody());
        NPCMessageData npcMessageData = NpcMessageUtils.unMarshal(getJaxbUnMarshaller(), msgString);

        NPCDataType npcDataType = npcMessageData.getNPCData();

        MessageHeaderType messageHeader = npcDataType.getMessageHeader();
        NPCMessageType npcMessages = npcDataType.getNPCMessages();

        List<PortDeactMsgType> msgList = NpcMessageUtils.listInboundOtherMsg(npcMessages);

        if (msgList.size() > 0) {
            String msgId = messageHeader.getMessageID().toString();
            logger.info("Importing ClhPortDeactMsg size: {} orders, msgId: {}", msgList.size(), msgId);

            String status = null;
            boolean hasError = false;
            for (Object msgObject : msgList) {
                status = getMvnoMsgDao().importMsg(mqHeaders, messageHeader, msgObject);
                if (Strings.nullToEmpty(status).indexOf(errorText) != -1) {//has error
                    hasError = true;
                    break;
                }
            }

            msgProperties.getHeaders().clear();
            msgProperties.getHeaders().putAll(mqHeaders);
            msgProperties.setHeader("Status", status);
            Message importedMsg = new Message(msgString.getBytes(), msgProperties);

            logger.debug("MqMessage for status {} received size: {}", status, msgString.length());
            if (!hasError) {//no error
                amqpTemplate.send(msgId, importedMsg);
            } else {
                logger.error("Error detected while importing clh port deact msg; header: {}, status: {}", messageHeader, status);
                File file = getFile(messageHeader);
                FileUtils.writeStringToFile(file, msgString, getFileEncoding());
                moveFileToDirectory(file, getErrorPath());

                errorAmqpTemplate.send(msgId, importedMsg);
            }
            logger.debug("MqMessage for status {} sent size: {}", status, msgString.length());
        } else {
            logger.error("Invalid ClhPortDeactMsg: {}", messageHeader);
        }
    }
}
