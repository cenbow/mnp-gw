/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.clh.core.filter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import com.telcordia.inpac.ws.jaxb.NumReturnAckMsgType;
import com.telcordia.inpac.ws.jaxb.NumReturnReqMsgType;
import com.telcordia.inpac.ws.jaxb.NumTypeNoPortId;
import com.telcordia.inpac.ws.jaxb.NumTypeWithCLHFlag;
import com.telcordia.inpac.ws.jaxb.SyncReqMsgType;

import cat.mnp.clh.dao.NumberReturnDao;
import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.mq.core.MsgHandlerBase;

public class NumberReturnReqMsgFilter extends MsgHandlerBase {

	private static final Logger logger = LoggerFactory.getLogger(NumberReturnReqMsgFilter.class);
	private NumberReturnDao numberReturnDao;
	private AmqpTemplate amqpTemplate;
	private AmqpTemplate amqpTemplateClh;

	public NumberReturnDao getNumberReturnDao() {
		return numberReturnDao;
	}

	public void setNumberReturnDao(NumberReturnDao numberReturnDao) {
		this.numberReturnDao = numberReturnDao;
	}

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

	public AmqpTemplate getAmqpTemplateClh() {
		return amqpTemplateClh;
	}

	public void setAmqpTemplateClh(AmqpTemplate amqpTemplateClh) {
		this.amqpTemplateClh = amqpTemplateClh;
	}

	@Override
	public void processMsg(Message msg) throws Exception {
		String msgString = new String(msg.getBody());
		NPCMessageData npcMessageData = NpcMessageUtils.unMarshal(getJaxbUnMarshaller(), msgString);
		NPCDataType npcDataType = npcMessageData.getNPCData();
		MessageHeaderType messageHeader = npcDataType.getMessageHeader();
		NPCMessageType npcMessages = npcDataType.getNPCMessages();
		MessageFooterType messageFooter = npcDataType.getMessageFooter();

		List<NumReturnReqMsgType> msgList = NpcMessageUtils.listOutboundOtherMsg(npcMessages); // TODO: direct use npvMessage ?, this should have only number return

		String msgId = messageHeader.getMessageID().toString();
		String sender = messageHeader.getSender();
		logger.info("Filter NumberReturn sender={}, msg {} size: {} orders", sender, msgId, msgList.size());

		BigInteger checksum = messageFooter.getChecksum();
		if (msgList.size() == 1) {
			for (NumReturnReqMsgType numberReturnReq : msgList) {
				String orderId = numberReturnReq.getOrderId();
				boolean isCat3g = numberReturnDao.isCat3gOrder(orderId);; // check order is cat3g
				logger.info("orderId={}, isCat3g={}", orderId, isCat3g);

				if (!isCat3g) {// call store to filter msisdn
					List<String> msisdnList = new ArrayList<>();
					for (NumTypeNoPortId o : numberReturnReq.getNumberNoPortId()) {
						msisdnList.add(o.getMSISDN());
					}

					List<NumTypeNoPortId> oriList = new ArrayList<>(numberReturnReq.getNumberNoPortId());
					List<String> validNumberList = numberReturnDao.verifyNumber(orderId, sender, msisdnList);

					validNumberList = new ArrayList<>(); // FIXME: Test 3001: reject all have problem
					for (Iterator<NumTypeNoPortId> it = numberReturnReq.getNumberNoPortId().iterator(); it.hasNext();) { // filter only valid
						NumTypeNoPortId numTypeNoPortId = it.next();
						if (!validNumberList.contains(numTypeNoPortId.getMSISDN())) {
							// logger.debug("remove "+numTypeNoPortId.getMSISDN());
							it.remove();
							checksum = checksum.subtract(new BigInteger("1"));
						}
					}

					logger.info("filter msisdn {} to={}", msisdnList.size(), numberReturnReq.getNumberNoPortId().size());
					// FIXME: if reject all it will '{NumberNoPortId}' is expected, we should not send to clh and direct 3002 to mvno with empty reason, now wait for decision

					// Make 3002 with all reject number send to another fanout (ClhOtherMsgFanout)
					if (numberReturnReq.getNumberNoPortId().isEmpty()) {
						npcMessageData = create3002Msg(oriList);
						logger.debug("Msg {} sent size: {} orders, {} msisdns", msgId, msgList.size(), messageFooter.getChecksum());
						String msgXml = NpcMessageUtils.marshal(getJaxbMarshaller(), npcMessageData);
						Message numberReturnRespMsg = new Message(msgXml.getBytes(), msgProperties);
						amqpTemplateClh.send("3002",numberReturnRespMsg); // send to mq
						return;
					}
				}
			}

			messageFooter.setChecksum(checksum);
			logger.debug("Msg {} sent size: {} orders, {} msisdns", msgId, msgList.size(), messageFooter.getChecksum());
			String msgXml = NpcMessageUtils.marshal(getJaxbMarshaller(), npcMessageData);
			Message mergedMsg = new Message(msgXml.getBytes(), msgProperties);

			amqpTemplate.send(msgId, mergedMsg);
			logger.info("Msg {} sent size: {} orders, {} msisdns", msgId, msgList.size(), messageFooter.getChecksum());
		} else {
			logger.warn("NumberReturnReq does not expect msgList != 1");
			logger.error("Invalid Msg: {}", messageHeader);
		}
	}

	private NPCMessageData create3002Msg(List<NumTypeNoPortId> oriList) {
		// <MessageHeader>
		// <PortType>1</PortType>
		// <MessageID>3002</MessageID>
		// <SoapRequestId>947</SoapRequestId>
		// <MessageCreateTimeStamp>20161012140200</MessageCreateTimeStamp>
		// <Sender>CRDB</Sender>
		// <Receiver>CATCDMA</Receiver>
		// </MessageHeader>
		// <NPCMessages>
		// <NumberReturnAck>
		// <OrderId>021610121117546</OrderId>
		// <NumberWithCLHFlag>
		// <MSISDN>0828810791</MSISDN>
		// <PortId>20161012CATCDMA0000001</PortId>
		// <CLHAccepted>1</CLHAccepted>
		// </NumberWithCLHFlag>
		// <NumberWithCLHFlag>
		// <MSISDN>0828810792</MSISDN>
		// <PortId>20161012CATCDMA0000002</PortId>
		// <CLHAccepted>1</CLHAccepted>
		// </NumberWithCLHFlag>
		// </NumberReturnAck>
		// </NPCMessages>
		// <MessageFooter>
		// <Checksum>2</Checksum>
		// </MessageFooter>
		MessageHeaderType header = new MessageHeaderType();
		header.setMessageID(new BigInteger("3002"));
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

		NumReturnAckMsgType numReturnAck = new NumReturnAckMsgType();
		numReturnAck.setOrderId("1234567890abcde"); // FIXME: Double check this method
		List<NumTypeWithCLHFlag> numberWithCLHFlagList = numReturnAck.getNumberWithCLHFlag();

		for (NumTypeNoPortId numTypeNoPortId : oriList) {
			NumTypeWithCLHFlag numTypeWithCLHFlag = new NumTypeWithCLHFlag();
			numTypeWithCLHFlag.setPortId("1234567890abcdefghi");
			numTypeWithCLHFlag.setMSISDN(numTypeNoPortId.getMSISDN());
			numTypeWithCLHFlag.setCLHAccepted(new BigInteger("1"));
			numTypeWithCLHFlag.setCLHRejectCode("1");
			numberWithCLHFlagList.add(numTypeWithCLHFlag);
		}

		npcMessage.getNumberReturnAck().add(numReturnAck);
		MessageFooterType messageFooter = new MessageFooterType();
		messageFooter.setChecksum(BigInteger.valueOf(numberWithCLHFlagList.size()));

		npcData.setMessageFooter(messageFooter);

		npcMessageData.setNPCData(npcData);
		return npcMessageData;
	}

}
