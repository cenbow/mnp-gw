/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.portsync.stp;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

import com.google.common.base.Strings;
import com.telcordia.inpac.ws.jaxb.MessageFooterType;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import com.telcordia.inpac.ws.jaxb.NPCMessageType;
import com.telcordia.inpac.ws.jaxb.PortBroadcastMsgType;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.mq.core.MsgHandlerBase;

/**
 *
 * @author HP-CAT
 */
public class PortSyncStpMsgProcessor extends MsgHandlerBase {
	private static final Logger logger = LoggerFactory.getLogger(PortSyncStpMsgProcessor.class);

	private PortSyncStpDao portSyncStpDao;
	private MessageProperties msgProperties;
	private AmqpTemplate amqpTemplate;

	// FIXME: 4002 เอาข้อมูลจาก DB -> ส่งเข้า StpFanout เพื่อ re-use การส่งไป stp จาก listener เดิม
	public void processMsg(javax.jms.Message aqMsg) throws Exception {
		String msgId = "4010"; // ???
		String orderId = aqMsg.getStringProperty("OrderId");
		logger.info("Process OrderId=" + orderId);

		List<String> spOutList = portSyncStpDao.portSyncStpSP(orderId); // call store by order id
		List<PortBroadcastMsgType> portBroadcastMsgType = createPortBroadcastList(spOutList); // transform string list (delimeter) to npc list
		NPCMessageData npcMessageData = create4010(msgId, portBroadcastMsgType); // create npcMessgae

		logger.debug("Marshaling msg {} size: , {} msisdns", msgId, portBroadcastMsgType.size());
		if (portBroadcastMsgType.size() != 0) {
			String msgXml = NpcMessageUtils.marshal(getJaxbMarshaller(), npcMessageData); // make xml
			Message mqMsg = new Message(msgXml.getBytes(), msgProperties); // put to fanout
			amqpTemplate.send(msgId, mqMsg);
		}
		logger.info("Msg {} sent size: {} orders, {} msisdns", msgId, 1, portBroadcastMsgType.size()); // 1 order

	}

	private List<PortBroadcastMsgType> createPortBroadcastList(List<String> spOutList) {
		List<PortBroadcastMsgType> r = new ArrayList<>();
		for (String delimData : spOutList) {
			logger.debug(delimData);
			String[] ss = delimData.split(",", -1);
			int i = 0;
			// FIXME: correct order and value
			PortBroadcastMsgType portBroadcastMsgType = new PortBroadcastMsgType();
			portBroadcastMsgType.setPortId(ss[i++]);
			portBroadcastMsgType.setMSISDN(ss[i++]);
			portBroadcastMsgType.setDonor(ss[i++]);
			portBroadcastMsgType.setRecipient(ss[i++]);
			portBroadcastMsgType.setRoute(ss[i++]);
			logger.debug("date =" + ss[i++]);
			portBroadcastMsgType.setNumberHolderInd(ss[i++]);
			r.add(portBroadcastMsgType);
		}

		return r;
	}

	private NPCMessageData create4010(String msgId, List<PortBroadcastMsgType> portBroadcastMsgTypeList) {
		MessageHeaderType header = new MessageHeaderType();
		header.setMessageID(new BigInteger(msgId));
		header.setMessageCreateTimeStamp(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		header.setPortType(new BigInteger("1"));

		header.setSoapRequestId(Strings.padStart("001", 3, '0'));
		header.setSender("DUMMY");
		header.setReceiver("DUMMY");

		NPCMessageData npcMessageData = new NPCMessageData();
		NPCDataType npcData = new NPCDataType();
		npcData.setMessageHeader(header);
		NPCMessageType npcMessage = new NPCMessageType();
		npcData.setNPCMessages(npcMessage);

		npcMessage.setPortBroadcast(portBroadcastMsgTypeList);
		MessageFooterType messageFooter = new MessageFooterType();
		messageFooter.setChecksum(BigInteger.valueOf(portBroadcastMsgTypeList.size()));

		npcData.setMessageFooter(messageFooter);

		npcMessageData.setNPCData(npcData);
		return npcMessageData;
	}

	public void setPortSyncStpDao(PortSyncStpDao portSyncStpDao) {
		this.portSyncStpDao = portSyncStpDao;
	}

	public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}

	public PortSyncStpDao getPortSyncStpDao() {
		return portSyncStpDao;
	}

	public AmqpTemplate getAmqpTemplate() {
		return amqpTemplate;
	}

	public MessageProperties getMsgProperties() {
		return msgProperties;
	}

	public void setMsgProperties(MessageProperties msgProperties) {
		this.msgProperties = msgProperties;
	}

	public static Logger getLogger() {
		return logger;
	}

}
