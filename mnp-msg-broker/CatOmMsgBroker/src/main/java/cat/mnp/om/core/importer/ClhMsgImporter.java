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
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

/**
 *
 * @author HP-CAT
 */
public class ClhMsgImporter extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(ClhMsgImporter.class);
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

    @Override
    public void processMsg(Message msg) throws Exception {
        String msgString = new String(msg.getBody());
        NPCMessageData npcMessageData = NpcMessageUtils.unMarshal(getJaxbUnMarshaller(), msgString);

        NPCDataType npcDataType = npcMessageData.getNPCData();

        MessageHeaderType messageHeader = npcDataType.getMessageHeader();
        NPCMessageType npcMessages = npcDataType.getNPCMessages();

        List msgList = NpcMessageUtils.listInboundOtherMsg(npcMessages);
        msgList.addAll(NpcMessageUtils.listBroadcastMsg(npcMessages));

        if (msgList.size() > 0) {
            String msgId = messageHeader.getMessageID().toString();
            msgProperties.getHeaders().clear();
            msgProperties.getHeaders().putAll(msg.getMessageProperties().getHeaders());
            Map<String, Object> mqHeaders = msgProperties.getHeaders();
            logger.info("Importing ClhMsg {} size: {}", msgId, msgList.size());
            for (Object msgObject : msgList) {
                String result = Strings.nullToEmpty(getMvnoMsgDao().importMsg(mqHeaders, messageHeader, msgObject));

                if (result.indexOf(errorText) == -1) {//no error
                    Message splittedMsg = new Message(msgString.getBytes(), msgProperties);
                    amqpTemplate.send(msgId, splittedMsg);
                } else {//has error
                    msgProperties.setHeader("Status", result);
                    Message splittedMsg = new Message(msgString.getBytes(), msgProperties);
                    logger.error("Error detected while importing msg: {}, result: {}", messageHeader, result);
                    errorAmqpTemplate.send(msgId, splittedMsg);
                }
            }
        } else {
            logger.error("Invalid ClhMsg: {}", messageHeader);
        }
    }
}
