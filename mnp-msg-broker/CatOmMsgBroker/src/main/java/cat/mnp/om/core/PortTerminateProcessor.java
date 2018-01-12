/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.core;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.MessageChannel;

import cat.mnp.mq.core.MsgHandlerBase;
import cat.mnp.mvno.dao.MvnoMsgDao;

/**
 *
 * @author HP-CAT
 */
public class PortTerminateProcessor extends MsgHandlerBase {

	private static final Logger logger = LoggerFactory.getLogger(PortTerminateProcessor.class);
	private static final String MSG_ID = "MsgId";
	private MvnoMsgDao mvnoMsgDao;
	private AmqpTemplate amqpTemplate;
	private AmqpTemplate errorAmqpTemplate;
	private MessageProperties msgProperties;

	private JavaMailSender mailSender;
	private MessageChannel inputChannel;
	private long timeout;

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public MessageChannel getInputChannel() {
		return inputChannel;
	}

	public void setInputChannel(MessageChannel inputChannel) {
		this.inputChannel = inputChannel;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

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
		String msgId = "5001";
		String portId = aqMsg.getStringProperty("PortId") + ""; // FIXME: Waiting logic to implement 5001
		logger.info("prcoess msg {}, portId={}", msgId, portId);

		msgProperties.getHeaders().clear();
		TextMessage txtMsg = ((TextMessage) aqMsg);
		for (Enumeration e = txtMsg.getPropertyNames(); e.hasMoreElements();) {
			String key = (String) e.nextElement();
			Object obj = txtMsg.getObjectProperty(key);
			if (obj != null && !StringUtils.startsWith(key, "JMS")) {
				msgProperties.setHeader(key, txtMsg.getObjectProperty(key));
			}
		}

		// execSP(portId, 5);

		// Send mail
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("to@test.com");
		msg.setSubject("test subject");
		msg.setText("test body");
		mailSender.send(msg);

	}

	private void execSP(String orderId, int status) throws JMSException, Exception {
		Map m = new LinkedHashMap<>();
		m.put("i_order_id", orderId);
		m.put("i_status", status);
		String rs = mvnoMsgDao.importMsg(m);
		if ("1".equals(rs)) {
			throw new RuntimeException("Error from execSP(), o_callstatus=" + rs);
		}
	}

}
