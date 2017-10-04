package cat.mnp.ws;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;

import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.mq.core.MsgHandlerBase;
/**
 * Call Int or Ext
 */
public class NpcWsClientMgr extends MsgHandlerBase {
	private static final Logger logger = LoggerFactory.getLogger(NpcWsClientMgr.class);
	MsgHandlerBase intClhWsClient;
	MsgHandlerBase clhWsClient;

	@Override
	public void processMsg(Message msg) throws Exception {
		logger.info("Select Clh");
		String msisdn;
		String orderId;

		String msgString = new String(msg.getBody());

		try {
			NPCMessageData npcMessageData = NpcMessageUtils.unMarshal(getJaxbUnMarshaller(), msgString);
			NPCDataType npcDataType = npcMessageData.getNPCData();
			msisdn = npcDataType.getNPCMessages().getPortRequest().get(0).getNumberWithPinNoPortId().get(0).getMSISDN();
			orderId = npcDataType.getNPCMessages().getPortRequest().get(0).getOrderId();
			logger.info("orderId=" + orderId + ", msisdn=" + msisdn);

		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException("Error while unmarshaling msg", e);
		}
		if ("021708291131368".equals(orderId)) { // hardcode
			logger.info("External Clh WS: Hardcode for 021708291131368 ");
			clhWsClient.processMsg(msg);
		} else {
			logger.info("Internal WS");
			intClhWsClient.processMsg(msg);
		}
	}

	public void setIntClhWsClient(MsgHandlerBase intClhWsClient) {
		this.intClhWsClient = intClhWsClient;
	}

	public void setClhWsClient(MsgHandlerBase clhWsClient) {
		this.clhWsClient = clhWsClient;
	}
}
