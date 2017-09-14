/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.smpp.converter;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.mq.core.MsgHandlerBase;
import cat.mnp.smpp.SmppMessagePostProcessor;
import cat.mnp.smpp.converter.extractor.SmppMsgExtractor;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import com.telcordia.inpac.ws.jaxb.NPCMessageType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;

/**
 *
 * @author HP-CAT
 */
public class SmppMsgConverter extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(SmppMsgConverter.class);
    private AmqpTemplate amqpTemplate;
    private SmppMsgExtractor smppMsgExtractor;
    private SmppMessagePostProcessor acceptMessagePostProcessor;
    private SmppMessagePostProcessor rejectMessagePostProcessor;

    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void setSmppMsgExtractor(SmppMsgExtractor smppMsgExtractor) {
        this.smppMsgExtractor = smppMsgExtractor;
    }

    public void setAcceptMessagePostProcessor(SmppMessagePostProcessor acceptMessagePostProcessor) {
        this.acceptMessagePostProcessor = acceptMessagePostProcessor;
    }

    public void setRejectMessagePostProcessor(SmppMessagePostProcessor rejectMessagePostProcessor) {
        this.rejectMessagePostProcessor = rejectMessagePostProcessor;
    }

    @Override
    public void processMsg(Message msg) throws Exception {
        String msgString = new String(msg.getBody());
        String msgId = msg.getMessageProperties().getReceivedRoutingKey();

        NPCMessageData npcMessageData = NpcMessageUtils.unMarshal(getJaxbUnMarshaller(), msgString);

        NPCDataType npcDataType = npcMessageData.getNPCData();

        MessageHeaderType messageHeader = npcDataType.getMessageHeader();
        NPCMessageType npcMessages = npcDataType.getNPCMessages();

        List msgList = NpcMessageUtils.listOtherMsg(npcMessages);
        msgList.addAll(NpcMessageUtils.listBroadcastMsg(npcMessages));

        if (msgList.size() > 0) {
            logger.info("Converting Msg size: {} {} {}", msgList.size());

            if (acceptMessagePostProcessor != null) {
                List<String> msisdnList = smppMsgExtractor.listAcceptMsisdn(messageHeader, msgList);
                logger.info("Extracted Accept Msg size: {}", msisdnList.size());
                if (!msisdnList.isEmpty()) {
                    amqpTemplate.convertAndSend(msgId, msisdnList, acceptMessagePostProcessor);
                }
            }
            if (rejectMessagePostProcessor != null) {
                List<String> msisdnList = smppMsgExtractor.listRejectMsisdn(messageHeader, msgList);
                logger.info("Extracted Reject Msg size: {}", msisdnList.size());
                if (!msisdnList.isEmpty()) {
                    amqpTemplate.convertAndSend(msgId, msisdnList, rejectMessagePostProcessor);
                }
            }
        } else {
            logger.error("Invalid Msg: {}", messageHeader);
        }
    }
}