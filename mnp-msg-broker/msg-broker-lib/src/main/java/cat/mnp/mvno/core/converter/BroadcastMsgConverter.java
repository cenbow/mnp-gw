/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.core.converter;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.mq.core.MsgHandlerBase;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import com.telcordia.inpac.ws.jaxb.NPCMessageType;
import java.io.File;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import com.telcordia.inpac.ws.jaxb.PortBroadcastMsgType;

/**
 *
 * @author HP-CAT
 */
public class BroadcastMsgConverter extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(BroadcastMsgConverter.class);
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
    public void processMsg(String msgString) throws Exception {
        NPCMessageData npcMessageData = NpcMessageUtils.unMarshal(getJaxbUnMarshaller(), msgString);

        NPCDataType npcDataType = npcMessageData.getNPCData();

        MessageHeaderType messageHeader = npcDataType.getMessageHeader();
        NPCMessageType npcMessages = npcDataType.getNPCMessages();
        String msgId = messageHeader.getMessageID().toString();

        List<PortBroadcastMsgType> msgList = NpcMessageUtils.listBroadcastMsg(npcMessages);

        if (msgList.size() > 0) {
            logger.info("Converting Broadcast size: {}", msgList.size());
            getMvnoMsgDao().queryMsg(messageHeader, msgList);

            logger.debug("Marshaling msg: {}", msgList.size());
            String msgXml = NpcMessageUtils.marshal(getJaxbMarshaller(), npcMessageData);
            Message msg = new Message(msgXml.getBytes(), msgProperties);

            logger.debug("Writing xml log");
            File file = getFile(messageHeader);
            FileUtils.writeStringToFile(file, msgXml, getFileEncoding());

            logger.debug("MqMessage received: {}", msgList.size());
            if (msgXml.indexOf(errorText) == -1) {//no error
                amqpTemplate.send(msgId, msg);
                moveFileToDirectory(file, getBackupPath());
            } else {//has error
                logger.error("Error detected while converting broadcast msg for mvno: {}", messageHeader);
                errorAmqpTemplate.send(msgId, msg);
                moveFileToDirectory(file, getErrorPath());
            }
            logger.info("MqMessage sent: {}", msgList.size());
        } else {
            logger.error("Invalid Broadcast Msg: {}", messageHeader);
        }
    }
}
