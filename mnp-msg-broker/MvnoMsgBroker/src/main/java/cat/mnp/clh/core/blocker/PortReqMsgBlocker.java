/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.clh.core.blocker;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.mq.core.MsgHandlerBase;
import com.telcordia.inpac.ws.jaxb.MessageFooterType;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import com.telcordia.inpac.ws.jaxb.NPCMessageType;
import com.telcordia.inpac.ws.jaxb.PortReqMsgType;
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
public class PortReqMsgBlocker extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(PortReqMsgBlocker.class);
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

    @Override
    public void processMsg(String msgString) throws Exception {
        NPCMessageData npcMessageData = NpcMessageUtils.unMarshal(getJaxbUnMarshaller(), msgString);

        NPCDataType npcDataType = npcMessageData.getNPCData();

        MessageHeaderType messageHeader = npcDataType.getMessageHeader();
        NPCMessageType npcMessages = npcDataType.getNPCMessages();
        MessageFooterType messageFooter = npcDataType.getMessageFooter();

        List<PortReqMsgType> msgList = NpcMessageUtils.listPortRequest(npcMessages);

        if (msgList.size() > 0) {
            logger.info("Blocking PortReqMsg size: {} orders", msgList.size());

            String msgId = messageHeader.getMessageID().toString();
            String status = getMvnoMsgDao().blockMsg(messageHeader, msgList, messageFooter);
            String orderId = msgList.get(0).getOrderId();
            String mvnoName = messageHeader.getSender();

            msgProperties.getHeaders().clear();
            msgProperties.setHeader("Status", status);
            msgProperties.setHeader("OrderId", orderId);
            Message msg = new Message(msgString.getBytes(), msgProperties);

            amqpTemplate.send(msgId, msg);
            if (!status.contains(errorText)) {//no error
            } else {//has error
                logger.error("Error detected while blocking portreq msg for mvno: {}, header: {}", mvnoName, messageHeader);
            }
            logger.trace("PortReqMsg mvnoName {}, orderId {}, status {}", mvnoName, orderId, status);
        } else {
            logger.error("Invalid PortReqMsg: {}", messageHeader);
        }
    }
}
