/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.core.splitter;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import com.telcordia.inpac.ws.jaxb.NPCMessageType;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.mq.core.MsgHandlerBase;
import cat.mnp.mvno.dao.MvnoMsgDao;

/**
 *
 * @author HP-CAT
 */
public class PortSyncRespMsgProcessor extends MsgHandlerBase {

	private static final Logger logger = LoggerFactory.getLogger(PortSyncRespMsgProcessor.class);
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

	public void processMsg(Message msg) throws Exception {
		String msgString = new String (msg.getBody());
		NPCMessageData npcMessageData = NpcMessageUtils.unMarshal(getJaxbUnMarshaller(), msgString);
		NPCDataType npcDataType = npcMessageData.getNPCData();
		MessageHeaderType messageHeader = npcDataType.getMessageHeader();
		NPCMessageType npcMessages = npcDataType.getNPCMessages();

		// call store to get mvno

		// ftp file from clh?
		// read file from clh
		// re-create new file for the mvno

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
