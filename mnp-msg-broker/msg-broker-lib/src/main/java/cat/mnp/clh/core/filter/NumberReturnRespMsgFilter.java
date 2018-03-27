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

public class NumberReturnRespMsgFilter extends MsgHandlerBase {

	private static final Logger logger = LoggerFactory.getLogger(NumberReturnRespMsgFilter.class);
	private NumberReturnDao numberReturnDao;
	private AmqpTemplate amqpTemplate;

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

	@Override
	public void processMsg(Message msg) throws Exception {
		String msgString = new String(msg.getBody());
		NPCMessageData npcMessageData = NpcMessageUtils.unMarshal(getJaxbUnMarshaller(), msgString);
		NPCDataType npcDataType = npcMessageData.getNPCData();
		MessageHeaderType messageHeader = npcDataType.getMessageHeader();
		NPCMessageType npcMessages = npcDataType.getNPCMessages();
		MessageFooterType messageFooter = npcDataType.getMessageFooter();

		List<NumReturnAckMsgType> msgList = NpcMessageUtils.listInboundOtherMsg(npcMessages); // TODO: direct use npvMessage ?, this should have only number return

		String msgId = messageHeader.getMessageID().toString();
		String sender = messageHeader.getSender();
		logger.info("Filter NumberReturnAck sender={}, msg {} size: {} orders", sender, msgId, msgList.size());

		BigInteger checksum = messageFooter.getChecksum();
		if (msgList.size() > 0) {
			for (NumReturnAckMsgType numberReturnAck : msgList) {
				String orderId = numberReturnAck.getOrderId();
				boolean isCat3g = numberReturnDao.isCat3gOrder(orderId);; // check order is cat3g
				logger.info("orderId={}, isCat3g={}", orderId, isCat3g);

				if (!isCat3g) {// call store to filter msisdn
					List<String> msisdnList = new ArrayList<>();
					for (NumTypeWithCLHFlag o : numberReturnAck.getNumberWithCLHFlag()) {
						msisdnList.add(o.getMSISDN());
					}

					List<String> invalidNumberList = numberReturnDao.findInvalidNumber(orderId, sender, msisdnList);

					invalidNumberList = new ArrayList<>(); // FIXME: Test: reject all have problem
					for (Iterator<NumTypeWithCLHFlag> it = numberReturnAck.getNumberWithCLHFlag().iterator(); it.hasNext();) { // filter only valid
						NumTypeWithCLHFlag numTypeWithCLHFlag = it.next();
						if (invalidNumberList.contains(numTypeWithCLHFlag.getMSISDN())) {
							 logger.debug("remove "+numTypeWithCLHFlag.getMSISDN());
							it.remove();
							checksum = checksum.subtract(new BigInteger("1"));
						}
					}

					logger.info("filter msisdn {} to={}", msisdnList.size(), numberReturnAck.getNumberWithCLHFlag().size());

				}
			}
			messageFooter.setChecksum(checksum);
			logger.debug("Msg {} sent size: {} orders, {} msisdns", msgId, msgList.size(), messageFooter.getChecksum());
			String msgXml = NpcMessageUtils.marshal(getJaxbMarshaller(), npcMessageData);

			Message mergedMsg = new Message(msgXml.getBytes(), msgProperties);

			//amqpTemplate.send(msgId, mergedMsg);
			amqpTemplate.send( mergedMsg); // fanout
			logger.info("Msg {} sent size: {} orders, {} msisdns", msgId, msgList.size(), messageFooter.getChecksum());
		} else {
			logger.error("Invalid Msg: {}", messageHeader);
		}
	}
}
