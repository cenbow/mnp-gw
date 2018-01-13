/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.core;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.jms.JMSException;

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
		String orderId = aqMsg.getStringProperty("OrderId");
		String orderSeq = aqMsg.getStringProperty("OrderSeq");
		String portId = aqMsg.getStringProperty("PortID"); // FIXME: port terminate to PortId
		String msisdn = aqMsg.getStringProperty("MSISDN");
		logger.info("Process msgId=" + msgId);

		execSP(orderId, orderSeq, 5);

		String msgStr = String.format("msgId= %s, orderId= %s,orderSeq= %s, portId= %s, msisdn= %s ", msgId,orderId,orderSeq, portId, msisdn);

		// Send mail
		SimpleMailMessage msg = new SimpleMailMessage(); // FIXME: real 5001 mail content format
		msg.setFrom("mnpAdmin@cat.com");
		msg.setTo("clhAdmin@clh.com");
		msg.setSubject("Message 5001: "+new Date(System.currentTimeMillis()).toString());
		msg.setText(msgStr);
		mailSender.send(msg);

	}

	private void execSP(String orderId, String orderSeq, int status) throws JMSException, Exception {
		Map m = new LinkedHashMap<>();
		m.put("i_order_id", orderId);
		m.put("i_order_seq", orderSeq);
		m.put("i_status", status);
		String rs = mvnoMsgDao.importMsg(m);
		if ("1".equals(rs)) {
			throw new RuntimeException("Error from execSP(), o_callstatus=" + rs);
		}
	}

}
