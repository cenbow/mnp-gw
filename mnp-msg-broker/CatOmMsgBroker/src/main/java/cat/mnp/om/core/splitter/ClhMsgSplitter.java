/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.core.splitter;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.mq.core.MsgHandlerBase;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import com.telcordia.inpac.ws.jaxb.NPCMessageType;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

/**
 *
 * @author HP-CAT
 */
public class ClhMsgSplitter extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(ClhMsgSplitter.class);
    private AmqpTemplate amqpTemplate;
    private AmqpTemplate errorAmqpTemplate;
    private MessageProperties msgProperties;
    private boolean changeSoapRequestId;

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

    public boolean isChangeSoapRequestId() {
        return changeSoapRequestId;
    }

    public void setChangeSoapRequestId(boolean changeSoapRequestId) {
        this.changeSoapRequestId = changeSoapRequestId;
    }

    @Override
    public void processMsg(Message msg) throws Exception {
        String msgString = new String(msg.getBody());
        long deliveryTag = msg.getMessageProperties().getDeliveryTag();
        NPCMessageData npcMessageData = NpcMessageUtils.unMarshal(getJaxbUnMarshaller(), msgString);

        NPCDataType npcDataType = npcMessageData.getNPCData();

        MessageHeaderType messageHeader = npcDataType.getMessageHeader();
        NPCMessageType npcMessages = npcDataType.getNPCMessages();

        List msgList = NpcMessageUtils.listOtherMsg(npcMessages);
        msgList.addAll(NpcMessageUtils.listBroadcastMsg(npcMessages));

        if (msgList.size() > 0) {
            String msgId = messageHeader.getMessageID().toString();
            logger.info("Splitting Msg size: {} orders, msgId: {}", msgList.size(), msgId);

            ArrayList<String> msgXmlList = new ArrayList<>();
            int sequence = 0;
            String originalSoapReqId = messageHeader.getSoapRequestId();
            for (Object msgObject : msgList) {
                if (isChangeSoapRequestId()) {
                    String soapReqId = NpcMessageUtils.getNewSoapRequestId(originalSoapReqId, deliveryTag, sequence);
                    messageHeader.setSoapRequestId(soapReqId);
                }
                NPCMessageData splittedNPCMessageData = NpcMessageUtils.buildNpcMessageData(messageHeader, msgObject);
                msgXmlList.add(NpcMessageUtils.marshal(getJaxbMarshaller(), splittedNPCMessageData));
                sequence++;
            }

            logger.debug("MqMessage for {} receive size: {}", messageHeader, msgXmlList.size());
            for (String msgXml : msgXmlList) {
                Message splittedMsg = new Message(msgXml.getBytes(), msgProperties);
                amqpTemplate.send(msgId, splittedMsg);
            }
            logger.debug("MqMessage for {} sent size: {}", messageHeader, msgXmlList.size());
        } else {
            logger.error("Invalid Msg: {}", messageHeader);
        }
    }
}
