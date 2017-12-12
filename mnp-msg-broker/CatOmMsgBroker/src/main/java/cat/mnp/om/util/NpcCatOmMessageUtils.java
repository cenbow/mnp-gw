/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.util;

import cat.mnp.om.domain.CatOmBaseMsg;
import cat.mnp.om.domain.CatOmOrder;
import cat.mnp.om.domain.CatOmService;
import com.google.common.base.Strings;
import com.telcordia.inpac.ws.jaxb.MessageFooterType;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import com.telcordia.inpac.ws.jaxb.NPCMessageType;
import com.telcordia.inpac.ws.jaxb.NumReturnReqMsgType;
import com.telcordia.inpac.ws.jaxb.NumTypeBase;
import com.telcordia.inpac.ws.jaxb.NumTypeNoPortId;
import com.telcordia.inpac.ws.jaxb.NumTypeWithFlag;
import com.telcordia.inpac.ws.jaxb.NumTypeWithFlag;
import com.telcordia.inpac.ws.jaxb.NumTypeWithPinNoPortId;
import com.telcordia.inpac.ws.jaxb.PortDeactMsgType;
import com.telcordia.inpac.ws.jaxb.PortCancelMsgType;
import com.telcordia.inpac.ws.jaxb.PortReqMsgType;
import com.telcordia.inpac.ws.jaxb.PortRespMsgType;
import com.telcordia.inpac.ws.jaxb.PortRvrsDonorMsgType;
import com.telcordia.inpac.ws.jaxb.PortRvrsRecipientMsgType;
import com.telcordia.inpac.ws.jaxb.SubscriberDataType;
import com.telcordia.inpac.ws.jaxb.SyncReqMsgType;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author HP-CAT
 */
public class NpcCatOmMessageUtils {

    private static final String SENDER = "DUMMY";

    private static MessageHeaderType buildMessageHeader(String msgId, CatOmBaseMsg omMsg) {
        MessageHeaderType msg = new MessageHeaderType();
        msg.setMessageID(new BigInteger(msgId));
        msg.setMessageCreateTimeStamp(omMsg.getMsgCreateTimeStamp());
        msg.setPortType(omMsg.getPortType());
        msg.setSoapRequestId(Strings.padStart(omMsg.getReqTransId().toString(), 3, '0'));
        msg.setSender(SENDER);

        return msg;
    }

    private static MessageFooterType buildMessageFooter(long numberCnt) {
        MessageFooterType messageFooter = new MessageFooterType();
        messageFooter.setChecksum(BigInteger.valueOf(numberCnt));
        return messageFooter;
    }

    public static NPCMessageData listOutboundMsg(String msgId, List<CatOmBaseMsg> omMsgList) {
        NPCMessageData npcMessageData = new NPCMessageData();
        switch (msgId) {
            case "1001":
                npcMessageData.setNPCData(listPortReq(msgId, omMsgList));
                break;
            case "1004":
                npcMessageData.setNPCData(listPortResp(msgId, omMsgList));
                break;
            case "1005":
                npcMessageData.setNPCData(listPortCancel(msgId, omMsgList));
                break;
            case "1008":
            case "1013":
                npcMessageData.setNPCData(listPortDeact(msgId, omMsgList));
                break;
            case "2001":
                npcMessageData.setNPCData(listPortReversalReqDonor(msgId, omMsgList));
                break;
            case "2002":
                npcMessageData.setNPCData(listPortReversalReqRecp(msgId, omMsgList));
                break;
            case "3001":
                npcMessageData.setNPCData(listNumReturnReq(msgId, omMsgList));
                break;
            case "4001": // TODO: complete 4001
            	 npcMessageData.setNPCData(listPortSync(msgId, omMsgList));
            	break;
        }

        return npcMessageData;
    }

    private static NPCDataType listPortSync(String msgId, List<CatOmBaseMsg> omMsgList) {
    	  NPCDataType npcData = new NPCDataType();
          npcData.setMessageHeader(buildMessageHeader(msgId, omMsgList.get(0)));
          NPCMessageType npcMessage = new NPCMessageType();
          npcData.setNPCMessages(npcMessage);

          SyncReqMsgType e =new SyncReqMsgType();
          e.setDownloadType("D");
          e.setStartDate("20161031000000");
          e.setEndDate("20161101235959");
          npcData.setMessageFooter(buildMessageFooter(0));
          npcMessage.getSynchronisationRequest().add(e);
		return npcData;
	}

	private static NPCDataType listPortReq(String msgId, List<CatOmBaseMsg> omMsgList) {
        NPCDataType npcData = new NPCDataType();
        npcData.setMessageHeader(buildMessageHeader(msgId, omMsgList.get(0)));
        NPCMessageType npcMessage = new NPCMessageType();
        npcData.setNPCMessages(npcMessage);
        long numberCnt = 0;

        ArrayList<PortReqMsgType> msgList = new ArrayList<>();
        npcMessage.setPortRequest(msgList);
        for (CatOmBaseMsg omMsg : omMsgList) {
            for (Iterator it = omMsg.getMsgList().iterator(); it.hasNext();) {
                CatOmOrder order = (CatOmOrder) it.next();

                PortReqMsgType msg = new PortReqMsgType();
                msgList.add(msg);

                msg.setProcessType(order.getProcessType());
                msg.setOrderId(order.getOrderId());
                msg.setOrderDate(order.getOrderDate());
                msg.setZone(order.getZone().toString());
                msg.setRecipient(order.getRecipient());
                msg.setOperatorCode(order.getOrderId().substring(0, 2));
                msg.setChannelId(order.getChannelId());

                List<NumTypeWithPinNoPortId> numberList = new ArrayList<>();
                msg.setNumberWithPinNoPortId(numberList);
                for (CatOmService service : order.getServiceList()) {
                    NumTypeWithPinNoPortId number = new NumTypeWithPinNoPortId();
                    number.setMSISDN(service.getMsisdn());
                    number.setPinCode(service.getPinCode());
                    numberList.add(number);

                    numberCnt++;
                }

                SubscriberDataType subscriberData = new SubscriberDataType();
                msg.setSubscriberData(subscriberData);
                subscriberData.setSubscriberId(order.getCustomerIdentifier());
                subscriberData.setRemark(order.getCustomerRemark());
            }
        }
        npcData.setMessageFooter(buildMessageFooter(numberCnt));

        return npcData;
    }

    private static NPCDataType listPortResp(String msgId, List<CatOmBaseMsg> omMsgList) {
        NPCDataType npcData = new NPCDataType();
        npcData.setMessageHeader(buildMessageHeader(msgId, omMsgList.get(0)));
        NPCMessageType npcMessage = new NPCMessageType();
        npcData.setNPCMessages(npcMessage);
        long numberCnt = 0;

        ArrayList<PortRespMsgType> msgList = new ArrayList<>();
        npcMessage.setPortResponse(msgList);
        for (CatOmBaseMsg omMsg : omMsgList) {
            for (Iterator it = omMsg.getMsgList().iterator(); it.hasNext();) {
                CatOmOrder order = (CatOmOrder) it.next();

                PortRespMsgType msg = new PortRespMsgType();
                msgList.add(msg);

                msg.setProcessType(order.getProcessType());
                msg.setOrderId(order.getOrderId());

                List<NumTypeWithFlag> numberList = new ArrayList<>();
                msg.setNumberWithFlag(numberList);
                for (CatOmService service : order.getServiceList()) {
                    NumTypeWithFlag number = new NumTypeWithFlag();
                    number.setMSISDN(service.getMsisdn());
                    number.setPortId(service.getPortId());
                    number.setNumberAccepted(service.getNumAcceptFlag());
                    number.setRejectReasonCode(service.getDonorRejReasonCode());
                    number.setCorrectPinCode(service.getCorrectPinCode());
                    numberList.add(number);

                    numberCnt++;
                }
            }
        }
        npcData.setMessageFooter(buildMessageFooter(numberCnt));

        return npcData;
    }

    //TODO: Change flow
    private static NPCDataType listPortCancel(String msgId, List<CatOmBaseMsg> omMsgList) {
        NPCDataType npcData = new NPCDataType();
        npcData.setMessageHeader(buildMessageHeader(msgId, omMsgList.get(0)));
        NPCMessageType npcMessage = new NPCMessageType();
        npcData.setNPCMessages(npcMessage);
        long numberCnt = 0;

        ArrayList<PortCancelMsgType> msgList = new ArrayList<>();
        npcMessage.setPortCancel(msgList);
        for (CatOmBaseMsg omMsg : omMsgList) {
            for (Iterator it = omMsg.getMsgList().iterator(); it.hasNext();) {
                CatOmOrder order = (CatOmOrder) it.next();

                PortCancelMsgType msg = new PortCancelMsgType();
                msgList.add(msg);

                msg.setProcessType(order.getProcessType());
                msg.setOrderId(order.getOrderId());

                List<NumTypeBase> numberList = new ArrayList<>();
                msg.setNumberDataBase(numberList);
                for (CatOmService service : order.getServiceList()) {
                    NumTypeBase number = new NumTypeBase();
                    number.setMSISDN(service.getMsisdn());
                    number.setPortId(service.getPortId());
                    numberList.add(number);

                    numberCnt++;
                }
            }
        }
        npcData.setMessageFooter(buildMessageFooter(numberCnt));

        return npcData;
    }

    public static NPCDataType listPortDeact(String msgId, List<CatOmBaseMsg> omMsgList) {
        NPCDataType npcData = new NPCDataType();
        npcData.setMessageHeader(buildMessageHeader(msgId, omMsgList.get(0)));
        NPCMessageType npcMessage = new NPCMessageType();
        npcData.setNPCMessages(npcMessage);
        long numberCnt = 0;

        ArrayList<PortDeactMsgType> msgList = new ArrayList<>();
        switch (msgId) {
            case "1008":
                npcMessage.setPortDeact(msgList);
                break;
            case "1013":
                npcMessage.setPortDeactException(msgList);
                break;
        }

        for (CatOmBaseMsg omMsg : omMsgList) {
            for (Iterator it = omMsg.getMsgList().iterator(); it.hasNext();) {
                CatOmOrder order = (CatOmOrder) it.next();

                for (CatOmService service : order.getServiceList()) {
                    PortDeactMsgType msg = new PortDeactMsgType();
                    msgList.add(msg);

                    msg.setProcessType(order.getProcessType());
                    msg.setOrderId(order.getOrderId());
                    msg.setMSISDN(service.getMsisdn());
                    msg.setPortId(service.getPortId());
                    numberCnt++;
                }
            }
        }

        npcData.setMessageFooter(buildMessageFooter(numberCnt));

        return npcData;
    }

    private static NPCDataType listPortReversalReqDonor(String msgId, List<CatOmBaseMsg> omMsgList) {
        NPCDataType npcData = new NPCDataType();
        npcData.setMessageHeader(buildMessageHeader(msgId, omMsgList.get(0)));
        NPCMessageType npcMessage = new NPCMessageType();
        npcData.setNPCMessages(npcMessage);
        long numberCnt = 0;

        ArrayList<PortRvrsDonorMsgType> msgList = new ArrayList<>();
        npcMessage.setPortReversalDonor(msgList);
        for (CatOmBaseMsg omMsg : omMsgList) {
            for (Iterator it = omMsg.getMsgList().iterator(); it.hasNext();) {
                CatOmService service = (CatOmService) it.next();

                PortRvrsDonorMsgType msg = new PortRvrsDonorMsgType();
                msgList.add(msg);

                msg.setOperatorCode(service.getOrderId().substring(0, 2));
                msg.setMSISDN(service.getMsisdn());
                msg.setPortId(service.getPortId());
                msg.setZone(service.getZone().toString());

                numberCnt++;
            }
        }
        npcData.setMessageFooter(buildMessageFooter(numberCnt));

        return npcData;
    }

    private static NPCDataType listPortReversalReqRecp(String msgId, List<CatOmBaseMsg> omMsgList) {
        NPCDataType npcData = new NPCDataType();
        npcData.setMessageHeader(buildMessageHeader(msgId, omMsgList.get(0)));
        NPCMessageType npcMessage = new NPCMessageType();
        npcData.setNPCMessages(npcMessage);
        long numberCnt = 0;

        ArrayList<PortRvrsRecipientMsgType> msgList = new ArrayList<>();
        npcMessage.setPortReversalRecipient(msgList);
        for (CatOmBaseMsg omMsg : omMsgList) {
            for (Iterator it = omMsg.getMsgList().iterator(); it.hasNext();) {
                CatOmService service = (CatOmService) it.next();

                PortRvrsRecipientMsgType msg = new PortRvrsRecipientMsgType();
                msgList.add(msg);

                msg.setMSISDN(service.getMsisdn());
                msg.setPortId(service.getPortId());

                numberCnt++;
            }
        }
        npcData.setMessageFooter(buildMessageFooter(numberCnt));

        return npcData;
    }

    private static NPCDataType listNumReturnReq(String msgId, List<CatOmBaseMsg> omMsgList) {
        NPCDataType npcData = new NPCDataType();
        npcData.setMessageHeader(buildMessageHeader(msgId, omMsgList.get(0)));
        NPCMessageType npcMessage = new NPCMessageType();
        npcData.setNPCMessages(npcMessage);
        long numberCnt = 0;

        ArrayList<NumReturnReqMsgType> msgList = new ArrayList<>();
        npcMessage.setNumReturn(msgList);
        for (CatOmBaseMsg omMsg : omMsgList) {
            for (Iterator it = omMsg.getMsgList().iterator(); it.hasNext();) {
                CatOmOrder order = (CatOmOrder) it.next();

                NumReturnReqMsgType msg = new NumReturnReqMsgType();
                msgList.add(msg);

                msg.setOrderId(order.getOrderId());

                List<NumTypeNoPortId> numberList = new ArrayList<>();
                msg.setNumberNoPortId(numberList);
                for (CatOmService service : order.getServiceList()) {
                    NumTypeNoPortId number = new NumTypeNoPortId();
                    number.setMSISDN(service.getMsisdn());
                    numberList.add(number);

                    numberCnt++;
                }
            }
        }
        npcData.setMessageFooter(buildMessageFooter(numberCnt));

        return npcData;
    }
}
