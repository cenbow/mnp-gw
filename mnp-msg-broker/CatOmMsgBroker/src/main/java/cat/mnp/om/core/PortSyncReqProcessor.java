/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.core;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

import com.telcordia.inpac.ws.jaxb.MessageFooterType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.mq.core.MsgHandlerBase;
import cat.mnp.mvno.dao.MvnoMsgDao;
import cat.mnp.om.domain.CatOmBaseMsg;
import cat.mnp.om.util.NpcCatOmMessageUtils;

/**
 *
 * @author HP-CAT
 */
public class PortSyncReqProcessor extends MsgHandlerBase {

	private static final Logger logger = LoggerFactory.getLogger(PortSyncReqProcessor.class);
	private static final String MSG_ID = "MsgId";
	private MvnoMsgDao mvnoMsgDao;
	private AmqpTemplate amqpTemplate;
	private AmqpTemplate errorAmqpTemplate;
	private MessageProperties msgProperties;

	@Override
	public MvnoMsgDao getMvnoMsgDao() {
		return mvnoMsgDao;
	}

	public void setMvnoMsgDao(MvnoMsgDao mvnoMsgDao) {
		this.mvnoMsgDao = mvnoMsgDao;
	}

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

	/**
	 * MIW: Support AQ Msg
	 */
	public void processMsg(javax.jms.Message aqMsg) throws Exception {
		String msgId = "4001";
		String orderId= aqMsg.getStringProperty("OrderId");
		logger.info("prcoess msg {}, orderId={}", msgId, orderId);

		msgProperties.getHeaders().clear();
		TextMessage txtMsg = ((TextMessage) aqMsg);
		for (Enumeration e = txtMsg.getPropertyNames(); e.hasMoreElements();) {
			String key = (String) e.nextElement();
			Object obj = txtMsg.getObjectProperty(key);
			if (obj != null && !StringUtils.startsWith(key, "JMS")) {
				msgProperties.setHeader(key, txtMsg.getObjectProperty(key));
			}
		}

		CatOmBaseMsg omMsg = new CatOmBaseMsg();
		omMsg.setMsgCreateTimeStamp(aqMsg.getStringProperty("messageCreateTimeStamp"));
		omMsg.setPortType(new BigInteger("0"));
		omMsg.setMsgId(new BigInteger("4001"));
		omMsg.setReqTransId(new BigInteger(aqMsg.getStringProperty("SoapId")));

		NPCMessageData npcMessageData = NpcCatOmMessageUtils.getPortSyncReq(msgId, omMsg, "D", aqMsg.getStringProperty("Startdate"), aqMsg.getStringProperty("Enddate"));
		MessageFooterType messageFooter = npcMessageData.getNPCData().getMessageFooter();

		String msgXml;
		try {
			logger.debug("Marshaling msg {} size: {} msisdns", msgId, messageFooter.getChecksum());
			msgXml = NpcMessageUtils.marshal(getJaxbMarshaller(), npcMessageData);
		} catch (Exception ex) {
			logger.error("Error while create msg " + msgId, ex);
			Message msg = new Message(ex.getMessage().getBytes(), msgProperties);
			errorAmqpTemplate.send(msgId, msg);
			return;
		}
		Message msg = new Message(msgXml.getBytes(), msgProperties);
		amqpTemplate.send(msgId, msg);
		execSP(orderId, 5);
		logger.info("Msg {} sent size: {} msisdns", msgId, messageFooter.getChecksum());
	}

	private void execSP(String orderId, int status) throws JMSException, Exception {
		Map m = new LinkedHashMap<>();
		m.put("i_order_id",orderId);
		m.put("i_status", status);
		String rs = mvnoMsgDao.importMsg(m);
		if("1".equals(rs)) {
			throw new RuntimeException("Error from execSP(), o_callstatus="+rs);
		}
	}

}
