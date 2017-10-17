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
import cat.mnp.ws.dao.NpcWsDao;
public class NpcWsClientMgr extends MsgHandlerBase {
	private static final Logger logger = LoggerFactory.getLogger(NpcWsClientMgr.class);
	MsgHandlerBase intClhWsClient;
	MsgHandlerBase clhWsClient;
	NpcWsDao npcWsDao;
	@Override
	public void processMsg(Message msg) throws Exception {
		String msisdn;
		String orderId;
		String msgString = new String(msg.getBody());

		try {
			NPCMessageData npcMessageData = NpcMessageUtils.unMarshal(getJaxbUnMarshaller(), msgString);
			NPCDataType npcDataType = npcMessageData.getNPCData();

			if ( ! npcDataType.getNPCMessages().getPortRequest().isEmpty()) {  //1001
				msisdn = npcDataType.getNPCMessages().getPortRequest().get(0).getNumberWithPinNoPortId().get(0).getMSISDN();
				orderId = npcDataType.getNPCMessages().getPortRequest().get(0).getOrderId();
			}else {  //1005
				msisdn = npcDataType.getNPCMessages().getPortCancel().get(0).getNumberDataBase().get(0).getMSISDN();
				orderId = npcDataType.getNPCMessages().getPortCancel().get(0).getOrderId();
			}
			 logger.info("orderId=" + orderId + ", msisdn=" + msisdn);

		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException("Error while unmarshaling msg (Extract Info)", e);
		}
		String orderType = npcWsDao.checkOrderType(orderId);
		if ("1".equals(orderType)) { // ext
			logger.info("orderId=" + orderId + ", msisdn=" + msisdn + ",orderType=" + orderType + ": External Clh WS");
			clhWsClient.processMsg(msg);
		} else if ("2".equals(orderType)) { // int
			logger.info("orderId=" + orderId + ", msisdn=" + msisdn + ",orderType=" + orderType + ": Internal Clh WS");
			intClhWsClient.processMsg(msg);
		} else {
			logger.warn("orderId=" + orderId + ", msisdn=" + msisdn + ",orderType=" + orderType + ": Unknown");
			throw new Exception("orderId=" + orderId + ", msisdn=" + msisdn + ",orderType=" + orderType + ": Unknown");
		}
	}

	public void setIntClhWsClient(MsgHandlerBase intClhWsClient) {
		this.intClhWsClient = intClhWsClient;
	}

	public void setClhWsClient(MsgHandlerBase clhWsClient) {
		this.clhWsClient = clhWsClient;
	}

	public void setNpcWsDao(NpcWsDao npcWsDao) {
		this.npcWsDao = npcWsDao;
	}

}
