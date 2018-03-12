/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.clh.core.filter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

import com.telcordia.inpac.ws.jaxb.MessageFooterType;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import com.telcordia.inpac.ws.jaxb.NPCMessageType;

import cat.mnp.clh.dao.NumberReturnDao;
import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.mq.core.MsgHandlerBase;

/**
 *
 * @author HP-CAT
 */
public class NumberReturnReqMsgFilter extends MsgHandlerBase {

	private static final Logger logger = LoggerFactory.getLogger(NumberReturnReqMsgFilter.class);
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

		List msgList = NpcMessageUtils.listOutboundOtherMsg(npcMessages); // TODO: direct use npvMessage ?, this should have only number return

		if (msgList.size() > 0) {
			String msgId = messageHeader.getMessageID().toString();

			logger.info("Filter NumberReturn msg {} size: {} orders", msgId, msgList.size());
			//FIXME:  3001 implement
			// need for loop all 3001 items and check orderId
			String orderId =npcMessages.getNumberReturn().get(0).getOrderId();
			logger.info("orderId="+orderId);

			boolean isCat3g = numberReturnDao.isCat3gOrder(orderId); ; // check order is cat3g
			if (!isCat3g) {// call store to filter msisdn

			}

			logger.debug("Marshaling msg {} size: orders, {} msisdns", msgId, msgList.size(), messageFooter.getChecksum());
			String msgXml = NpcMessageUtils.marshal(getJaxbMarshaller(), npcMessageData);

			Message mergedMsg = new Message(msgXml.getBytes(), msgProperties);

			amqpTemplate.send(msgId, mergedMsg);
			logger.info("Msg {} sent size: {} orders, {} msisdns", msgId, msgList.size(), messageFooter.getChecksum());
		} else {
			logger.error("Invalid Msg: {}", messageHeader);
		}
	}
}
