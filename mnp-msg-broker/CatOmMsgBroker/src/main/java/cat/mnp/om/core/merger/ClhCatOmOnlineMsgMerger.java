/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.core.merger;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.mq.core.MsgHandlerBase;
import cat.mnp.om.dao.CatOmBaseMsgDao;
import cat.mnp.om.domain.CatOmBaseMsg;
import cat.mnp.om.util.NpcCatOmMessageUtils;
import com.telcordia.inpac.ws.jaxb.MessageFooterType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import java.math.BigInteger;
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
public class ClhCatOmOnlineMsgMerger extends MsgHandlerBase {

	private static final Logger logger = LoggerFactory.getLogger(ClhCatOmOnlineMsgMerger.class);
	private static final String MSG_ID = "MsgId";
	private CatOmBaseMsgDao mvnoMsgDao;
	private AmqpTemplate amqpTemplate;
	private AmqpTemplate errorAmqpTemplate;
	private MessageProperties msgProperties;

	@Override
	public CatOmBaseMsgDao getMvnoMsgDao() {
		return mvnoMsgDao;
	}

	public void setMvnoMsgDao(CatOmBaseMsgDao mvnoMsgDao) {
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

	@Override
	public void processMsg(javax.jms.Message aqMsg) throws Exception {
		String msgId = "1001";
		String orderId = (String) aqMsg.getObjectProperty("OrderId");

		List<CatOmBaseMsg> msgList = mvnoMsgDao.mergeMsg(); // this changed
		logger.info("Merging msg {}", msgId);

		NPCMessageData npcMessageData = NpcCatOmMessageUtils.listOutboundMsg(msgId, msgList);
		MessageFooterType messageFooter = npcMessageData.getNPCData().getMessageFooter();

		if (!messageFooter.getChecksum().equals(BigInteger.ZERO)) {
			String msgXml;
			try {
				logger.debug("Marshaling msg {} size: {} msisdns", msgId, messageFooter.getChecksum());
				msgXml = NpcMessageUtils.marshal(getJaxbMarshaller(), npcMessageData);
			} catch (Exception ex) {
				logger.error("Error while merging msg " + msgId, ex);
				Message msg = new Message(ex.getMessage().getBytes(), msgProperties);
				errorAmqpTemplate.send(msgId, msg);
				return;
			}
			Message msg = new Message(msgXml.getBytes(), msgProperties);

			amqpTemplate.send(msgId, msg);
			logger.info("Msg {} sent size: {} msisdns", msgId, messageFooter.getChecksum());
		} else {
			logger.info("No msg {} to merge", msgId);
		}
	}
}
