/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.core.splitter;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.mq.core.MsgHandlerBase;
import com.google.common.collect.ArrayListMultimap;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import com.telcordia.inpac.ws.jaxb.NPCMessageType;
import com.telcordia.inpac.ws.jaxb.PortDeactMsgType;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
public class PortActBlockerMsgSplitter extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(PortActBlockerMsgSplitter.class);
    private AmqpTemplate amqpTemplate;
    private MessageProperties msgProperties;

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

        List<PortDeactMsgType> msgList = NpcMessageUtils.listOtherMsg(npcMessages);

        if (msgList.size() > 0) {
            String msgId = messageHeader.getMessageID().toString();
            logger.info("Splitting Msg size: {} orders, msgId: {}", msgList.size(), msgId);

            HashMap<String, Object> dataMap = getMvnoMsgDao().splitMsg(messageHeader, msgList);
            ArrayListMultimap<String, PortDeactMsgType> msgStatusMultimap = (ArrayListMultimap<String, PortDeactMsgType>) dataMap.get("data");

            ArrayList<String> msgXmlList = new ArrayList<>();
            ArrayList<String> statusList = new ArrayList<>();
            ArrayList<PortDeactMsgType> msgObjectList = new ArrayList<>();
            HashSet<String> joinOrderIdSet = new HashSet<>();

            for (String status : msgStatusMultimap.keySet()) {
                List<PortDeactMsgType> msgStatusList = msgStatusMultimap.get(status);

                for (PortDeactMsgType msgObject : msgStatusList) {
                    if (status.equals("Join")) {
                        joinOrderIdSet.add(msgObject.getOrderId());
                    }

                    NPCMessageData splittedNPCMessageData = NpcMessageUtils.buildNpcMessageData(messageHeader, msgObject);
                    msgXmlList.add(NpcMessageUtils.marshal(getJaxbMarshaller(), splittedNPCMessageData));

                    statusList.add(status);
                    msgObjectList.add(msgObject);
                }
            }

            logger.debug("MqMessage for {} receive size: {}", messageHeader, msgXmlList.size());
            for (int i = 0; i < msgXmlList.size(); i++) {
                String msgXml = msgXmlList.get(i);
                String status = statusList.get(i);
                PortDeactMsgType msgObject = msgObjectList.get(i);

                switch (status) {
                    case "Accept":
                    case "Join":
                        logger.debug("Status {} ({}, {}, {})", status, msgObject.getOrderId(), msgObject.getMSISDN(), msgObject.getPortId());
                        break;
                    case "Reject":
                        if (joinOrderIdSet.contains(msgObject.getOrderId())) {//whole order will be join together
                            logger.debug("Status {} ({}, {}, {})", status, msgObject.getOrderId(), msgObject.getMSISDN(), msgObject.getPortId());
                            continue;
                        } else {
                            logger.error("Warning activation of ({}, {}, {}) is rejected due to lack of port deact from donor operator", msgObject.getOrderId(), msgObject.getMSISDN(), msgObject.getPortId());

                            File file = getFile(messageHeader);
                            FileUtils.writeStringToFile(file, msgXml, getFileEncoding());
                            moveFileToDirectory(file, getErrorPath());
                        }
                        break;
                    default://has error
                        logger.error("Error detected while importing msg: {}, result: {}", messageHeader, status);
                        File file = getFile(messageHeader);
                        FileUtils.writeStringToFile(file, msgXml, getFileEncoding());
                        moveFileToDirectory(file, getErrorPath());
                }
                msgProperties.setHeader("Status", status);
                msgProperties.setHeader("OrderId", msgObject.getOrderId());
                msgProperties.setHeader("Msisdn", msgObject.getMSISDN());
                msgProperties.setHeader("PortId", msgObject.getPortId());
                Message splittedMsg = new Message(msgXml.getBytes(), msgProperties);
                amqpTemplate.send(msgId, splittedMsg);
            }
            logger.debug("MqMessage for {} sent size: {}", messageHeader, msgXmlList.size());
        } else {
            logger.error("Invalid Msg: {}", messageHeader);
        }
    }
}
