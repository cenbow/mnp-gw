/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.core.merger;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.telcordia.inpac.ws.jaxb.MessageFooterType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.mq.core.MsgHandlerBase;
import cat.mnp.om.dao.CatOmBaseMsgDao;
import cat.mnp.om.domain.CatOmBaseMsg;
import cat.mnp.om.domain.CatOmOrder;
import cat.mnp.om.domain.CatOmService;
import cat.mnp.om.util.NpcCatOmMessageUtils;
import miw.util.JsonClientUtil;

/**
 *
 * @author HP-CAT
 */
public class ClhNumberReturnCatOmMsgMerger extends MsgHandlerBase {

	private static final Logger logger = LoggerFactory.getLogger(ClhNumberReturnCatOmMsgMerger.class);
	private static final String MSG_ID = "MsgId";
	private CatOmBaseMsgDao mvnoMsgDao;
	private AmqpTemplate amqpTemplate;
	private AmqpTemplate errorAmqpTemplate;
	private MessageProperties msgProperties;

	private String targetEndPoint;
	private String userId;
	private String password;

	public String getTargetEndPoint() {
		return targetEndPoint;
	}

	public void setTargetEndPoint(String targetEndPoint) {
		this.targetEndPoint = targetEndPoint;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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
	public void processMsg(Message mqMsg) throws Exception {
		String msgId = (String) mqMsg.getMessageProperties().getHeaders().get(MSG_ID);

		List<CatOmBaseMsg> msgList = mvnoMsgDao.mergeMsg();
		logger.info("Merging msg {}", msgId);

		logger.info("filter msgList size=" + msgList.size());
		filterMsgList(msgList); // filter with C1rtGw

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

	private void filterMsgList(List<CatOmBaseMsg> msgList) throws IOException, ClientProtocolException, JsonParseException, JsonMappingException {
		if (!CollectionUtils.isEmpty(msgList)) {
			CatOmBaseMsg catOmBaseMsg = msgList.get(0);
			if (!CollectionUtils.isEmpty(catOmBaseMsg.getMsgList())) {
				CatOmOrder catOmOrder = (CatOmOrder) catOmBaseMsg.getMsgList().get(0);
				List<CatOmService> catOmServiceList = catOmOrder.getServiceList();

				for (Iterator<CatOmService> it = catOmServiceList.iterator(); it.hasNext();) {
					CatOmService catOmService = it.next();
					String msisdn = catOmService.getMsisdn();

					Map<String, Object> jsonMap = getJsonInfo(msisdn); // call cr1 rest ws
					// logger.debug("msisdn=" + msisdn + ", result =" + jsonMap.get("ratingStateType")); // if note active -> remove
					if (!StringUtils.equalsIgnoreCase("Active", (String) jsonMap.get("ratingStateType"))) {
						logger.debug("remove " + msisdn + " ratingStateType=" + jsonMap.get("ratingStateType"));
						it.remove();
					}
				}
			}
		}
	}

	private Map<String, Object> getJsonInfo(String msisdn) throws IOException, ClientProtocolException, JsonParseException, JsonMappingException {
		String url = targetEndPoint.replace("{msisdn}", msisdn);
		//logger.debug("url="+url);
		return JsonClientUtil.get(url, userId, password);
	}

	/**
	 * MIW: Support AQ Msg
	 */
	public void processMsg(javax.jms.Message aqMsg) throws Exception {
		String msgId = (String) aqMsg.getObjectProperty("MsgId");

		List<CatOmBaseMsg> msgList = mvnoMsgDao.mergeMsg();
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
