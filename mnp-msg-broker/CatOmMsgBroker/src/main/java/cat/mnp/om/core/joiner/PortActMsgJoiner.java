/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.core.joiner;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.mq.core.MsgHandlerBase;
import cat.mnp.om.dao.PortActMsgDao;
import com.telcordia.inpac.ws.jaxb.MessageFooterType;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import com.telcordia.inpac.ws.jaxb.NPCMessageType;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

/**
 *
 * @author HP-CAT
 */
public class PortActMsgJoiner extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(PortActMsgJoiner.class);
    private PortActMsgDao mvnoMsgDao;
    private AmqpTemplate amqpTemplate;
    private MessageProperties msgProperties;
    private boolean changeSoapRequestId;

    @Override
    public PortActMsgDao getMvnoMsgDao() {
        return mvnoMsgDao;
    }

    public void setMvnoMsgDao(PortActMsgDao mvnoMsgDao) {
        this.mvnoMsgDao = mvnoMsgDao;
    }

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

    public boolean isChangeSoapRequestId() {
        return changeSoapRequestId;
    }

    public void setChangeSoapRequestId(boolean changeSoapRequestId) {
        this.changeSoapRequestId = changeSoapRequestId;
    }

    @Override
    public void processMsg(Message msg) throws Exception {
        Map<String, Object> mqHeaders = msg.getMessageProperties().getHeaders();
        String msgString = new String(msg.getBody());
        long deliveryTag = msg.getMessageProperties().getDeliveryTag();
        NPCMessageData npcMessageData = NpcMessageUtils.unMarshal(getJaxbUnMarshaller(), msgString);

        NPCDataType npcDataType = npcMessageData.getNPCData();

        MessageHeaderType messageHeader = npcDataType.getMessageHeader();
        NPCMessageType npcMessages = npcDataType.getNPCMessages();
        MessageFooterType messageFooter = npcDataType.getMessageFooter();

        List msgList = NpcMessageUtils.listOutboundOtherMsg(npcMessages);

        if (msgList.size() > 0) {
            String msgId = messageHeader.getMessageID().toString();

            logger.info("Joining msg {} size: {} orders", msgId, msgList.size());

            String originalSoapReqId = messageHeader.getSoapRequestId();
            if (isChangeSoapRequestId()) {
                String soapReqId = NpcMessageUtils.getNewSoapRequestId(originalSoapReqId, deliveryTag, 0);
                messageHeader.setSoapRequestId(soapReqId);
            }
            
            getMvnoMsgDao().joinMsg(msgList, messageFooter);
            switch (msgId) {
                case "1009":
                    npcMessages.setPortDeact(msgList);
                    break;
                case "1014":
                    npcMessages.setPortDeactException(msgList);
                    break;
            }
            
            logger.debug("Marshaling msg {} size: orders, {} msisdns", msgId, msgList.size(), messageFooter.getChecksum());
            String msgXml = NpcMessageUtils.marshal(getJaxbMarshaller(), npcMessageData);

            msgProperties.getHeaders().clear();
            for (Entry<String, Object> entry : mqHeaders.entrySet()) {
                msgProperties.setHeader(entry.getKey(), entry.getValue());
            }
            Message mergedMsg = new Message(msgXml.getBytes(), msgProperties);

            amqpTemplate.send(msgId, mergedMsg);
            logger.info("Msg {} sent size: {} orders, {} msisdns", msgId, msgList.size(), messageFooter.getChecksum());
        } else {
            logger.error("Invalid Msg: {}", messageHeader);
        }
    }
}
