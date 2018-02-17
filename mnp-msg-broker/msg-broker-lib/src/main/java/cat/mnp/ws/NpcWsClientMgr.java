package cat.mnp.ws;

import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;

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

	private static Map<String, String> orderTypeMap;
	static {
		orderTypeMap = new LinkedHashMap<String, String>();
		orderTypeMap.put("0", "Unknown");
		orderTypeMap.put("1", "External Clh WS");
		orderTypeMap.put("2", "Internal Clh WS");
	}

	@Override
	public void processMsg(Message msg) throws Exception {
		String msisdn = null;
		String orderId = null;
		String msgString = new String(msg.getBody());
		String orderType;
		String logStr;
		try {
			NPCMessageData npcMessageData = NpcMessageUtils.unMarshal(getJaxbUnMarshaller(), msgString);
			NPCDataType npcDataType = npcMessageData.getNPCData();

			BigInteger msgId = npcDataType.getMessageHeader().getMessageID();
			//FIXME: Complete All msgs
			if (msgId.equals(new BigInteger("4001")) || msgId.equals(new BigInteger("2001")) || msgId.equals(new BigInteger("2002")) ) { // 4001, 2001, 2002
				orderType = "1";
			} else if (!npcDataType.getNPCMessages().getPortRequest().isEmpty()) { // 1001
				msisdn = npcDataType.getNPCMessages().getPortRequest().get(0).getNumberWithPinNoPortId().get(0).getMSISDN();
				orderId = npcDataType.getNPCMessages().getPortRequest().get(0).getOrderId();
				orderType = npcWsDao.checkOrderType(orderId, "receipient");
			} else if (!npcDataType.getNPCMessages().getPortResponse().isEmpty()) { // 1004
				//msisdn = npcDataType.getNPCMessages().getPortResponse().get(0).getNumberWithFlag().get(0).getMSISDN(); // we dont care for 1004 ??
				orderId = npcDataType.getNPCMessages().getPortResponse().get(0).getOrderId();
				orderType = npcWsDao.checkOrderType(orderId, "donor");
			} else if (!npcDataType.getNPCMessages().getPortCancel().isEmpty()) { // 1005
				msisdn = npcDataType.getNPCMessages().getPortCancel().get(0).getNumberDataBase().get(0).getMSISDN();
				orderId = npcDataType.getNPCMessages().getPortCancel().get(0).getOrderId();
				orderType = npcWsDao.checkOrderType(orderId, "donor");
			} else if (!npcDataType.getNPCMessages().getPortDeact().isEmpty()) { // 1008
				msisdn = npcDataType.getNPCMessages().getPortDeact().get(0).getMSISDN();
				orderId = npcDataType.getNPCMessages().getPortDeact().get(0).getOrderId();
				orderType = npcWsDao.checkOrderType(orderId, "donor");
			} else if (!npcDataType.getNPCMessages().getNumberReturn().isEmpty()) { // 3001
				msisdn = npcDataType.getNPCMessages().getNumberReturn().get(0).getNumberNoPortId().get(0).getMSISDN();
				orderId = npcDataType.getNPCMessages().getNumberReturn().get(0).getOrderId();
				orderType = npcWsDao.checkOrderType(orderId, "donor");  // FIXME: change to new procedure
			} else {
				orderType = "0";
			}
			logStr = "msgId=" + npcDataType.getMessageHeader().getMessageID() + ", orderId=" + orderId + ", msisdn=" + msisdn + ", orderType=" + orderType + ": " + orderTypeMap.get(orderType);
			logger.info(logStr);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			throw new AmqpRejectAndDontRequeueException("Error while unmarshaling msg (Extract Info)", e);
		}

		if ("1".equals(orderType)) { // ext
			clhWsClient.processMsg(msg);
		} else if ("2".equals(orderType)) { // int
			intClhWsClient.processMsg(msg);
		} else {
			throw new Exception(logStr);
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
