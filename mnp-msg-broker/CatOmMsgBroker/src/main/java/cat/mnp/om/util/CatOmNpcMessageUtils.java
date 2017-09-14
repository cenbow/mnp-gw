/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.util;

import cat.mnp.om.domain.CatOmOrder;
import cat.mnp.om.domain.CatOmBaseMsg;
import cat.mnp.om.domain.CatOmService;
import com.telcordia.inpac.ws.jaxb.ErrorNotifMsgType;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCMessageType;
import com.telcordia.inpac.ws.jaxb.NumReturnAckMsgType;
import com.telcordia.inpac.ws.jaxb.NumReturnFwdMsgType;
import com.telcordia.inpac.ws.jaxb.NumTypeBase;
import com.telcordia.inpac.ws.jaxb.NumTypeWithPin;
import com.telcordia.inpac.ws.jaxb.NumTypeWithFlagWithPortDate;
import com.telcordia.inpac.ws.jaxb.NumTypeWithPinWithCLHFlag;
import com.telcordia.inpac.ws.jaxb.NumTypeWithCLHFlag;
import com.telcordia.inpac.ws.jaxb.PortDeactMsgType;
import com.telcordia.inpac.ws.jaxb.PortNotExceptMsgType;
import com.telcordia.inpac.ws.jaxb.PortNotMsgType;
import com.telcordia.inpac.ws.jaxb.PortReqAckMsgType;
import com.telcordia.inpac.ws.jaxb.PortReqFwdMsgType;
import com.telcordia.inpac.ws.jaxb.PortTerminatedMsgType;
import com.telcordia.inpac.ws.jaxb.TimerNotifMsgType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.telcordia.inpac.ws.jaxb.PortBroadcastMsgType;

/**
 *
 * @author HP-CAT
 */
public class CatOmNpcMessageUtils {

    private static CatOmBaseMsg buildCatOmBaseMsg(MessageHeaderType messageHeader) {
        CatOmBaseMsg msg = new CatOmBaseMsg();

        msg.setPortType(messageHeader.getPortType());
        msg.setMsgId(messageHeader.getMessageID());
        msg.setReqTransId(new BigInteger(messageHeader.getSoapRequestId()));
        msg.setMsgCreateTimeStamp(messageHeader.getMessageCreateTimeStamp());

        return msg;
    }

    public static List<HashMap<String, String>> listInboundMsg(MessageHeaderType messageHeader, NPCMessageType npcMessages) {
        String msgId = messageHeader.getMessageID().toString();
        ArrayList dataList = new ArrayList();
        switch (msgId) {
            case "1002":
                for (PortReqAckMsgType msg : npcMessages.getPortRequestAck()) {
                    dataList.add(listPortReqAck(messageHeader, msg));
                }
                break;
            case "1003":
                for (PortReqFwdMsgType msg : npcMessages.getPortRequestFwd()) {
                    dataList.add(listPortReqDonor(messageHeader, msg));
                }
                break;
            case "1006":
            case "1007":
                for (PortNotMsgType msg : npcMessages.getPortNotification()) {
                    dataList.add(listPortNotification(messageHeader, msg));
                }
                break;
            case "1009":
            case "1014":
                List<PortDeactMsgType> portActList = new ArrayList<>();
                portActList.addAll(npcMessages.getPortDeact());
                portActList.addAll(npcMessages.getPortDeactException());
                for (PortDeactMsgType msg : portActList) {
                    dataList.add(listPortAct(messageHeader, msg));
                }
                break;
            case "1011":
            case "1012":
                for (PortNotExceptMsgType msg : npcMessages.getPortNotificationException()) {
                    dataList.add(listPortNotificationException(messageHeader, msg));
                }
                break;
            case "3002":
                for (NumReturnAckMsgType msg : npcMessages.getNumberReturnAck()) {
                    dataList.add(listNumReturnAck(messageHeader, msg));
                }
                break;
            case "3003":
                for (NumReturnFwdMsgType msg : npcMessages.getNumberReturnFwd()) {
                    dataList.add(listNumReturnReqDonor(messageHeader, msg));
                }
                break;
            case "2010":
            case "3010":
                List<PortBroadcastMsgType> broadcastList = new ArrayList<>();
                broadcastList.addAll(npcMessages.getPortReversalBroadcast());
                broadcastList.addAll(npcMessages.getNumerReturnBroadcast());
                
                for (PortBroadcastMsgType msg : broadcastList) {
                    dataList.add(listBroadcast(messageHeader, msg));
                }
                break;
            case "4002":
                break;
            case "9001":
                for (PortTerminatedMsgType msg : npcMessages.getPortTerminated()) {
                    dataList.add(listPortTerminated(messageHeader, msg));
                }
                break;
            case "9998":
                for (TimerNotifMsgType msg : npcMessages.getTimerNotification()) {
                    dataList.add(listPortTimerNotification(messageHeader, msg));
                }
                break;
            case "9999":
                for (ErrorNotifMsgType msg : npcMessages.getErrorNotification()) {
                    dataList.add(listErrorNotification(messageHeader, msg));
                }
                break;
        }
        return dataList;
    }

    private static CatOmBaseMsg listPortReqAck(MessageHeaderType messageHeader, PortReqAckMsgType clhMsg) {
        CatOmBaseMsg msg = buildCatOmBaseMsg(messageHeader);

        ArrayList<CatOmOrder> orderList = new ArrayList<>();
        msg.setMsgList(orderList);

        CatOmOrder order = new CatOmOrder();
        orderList.add(order);

        order.setOrderId(clhMsg.getOrderId());
        order.setProcessType(clhMsg.getProcessType());
        order.setOrderDate(clhMsg.getOrderDate());
        order.setCustomerIdentifier(clhMsg.getSubscriberData().getSubscriberId());
        order.setCustomerRemark(clhMsg.getSubscriberData().getRemark());
        order.setZone(new BigInteger(clhMsg.getZone()));
        order.setRecipient(clhMsg.getRecipient());
        order.setOperatorCode(clhMsg.getOperatorCode());
        order.setDonor(clhMsg.getDonor());
        order.setValidDeadlineDate(clhMsg.getValidationDeadline());
        order.setChannelId(clhMsg.getChannelId());

        ArrayList<CatOmService> serviceList = new ArrayList<>();
        order.setServiceList(serviceList);

        for (NumTypeWithPinWithCLHFlag child : clhMsg.getNumberWithPinWithCLHFlag()) {
            CatOmService service = new CatOmService();
            serviceList.add(service);

            service.setOrderId(order.getOrderId());
            service.setPortId(child.getPortId());
            service.setProcessType(order.getProcessType());
            service.setMsisdn(child.getMSISDN());
            service.setPinCode(child.getPinCode());
            service.setClhAccpFlag(child.getCLHAccepted());
            service.setClhRejCode(child.getCLHRejectCode());
            service.setDonor(order.getDonor());
            service.setRecipient(order.getRecipient());
            service.setZone(order.getZone());
            service.setOperatorCode(order.getOperatorCode());
        }
        return msg;
    }

    private static CatOmBaseMsg listPortReqDonor(MessageHeaderType messageHeader, PortReqFwdMsgType clhMsg) {
        CatOmBaseMsg msg = buildCatOmBaseMsg(messageHeader);

        ArrayList<CatOmOrder> orderList = new ArrayList<>();
        msg.setMsgList(orderList);

        CatOmOrder order = new CatOmOrder();
        orderList.add(order);

        order.setOrderId(clhMsg.getOrderId());
        order.setProcessType(clhMsg.getProcessType());
        order.setOrderDate(clhMsg.getOrderDate());
        order.setCustomerIdentifier(clhMsg.getSubscriberData().getSubscriberId());
        order.setCustomerRemark(clhMsg.getSubscriberData().getRemark());
        order.setZone(new BigInteger(clhMsg.getZone()));
        order.setRecipient(clhMsg.getRecipient());
        order.setOperatorCode(clhMsg.getOperatorCode());
        order.setDonor(clhMsg.getDonor());
        order.setValidDeadlineDate(clhMsg.getValidationDeadline());
        order.setChannelId(clhMsg.getChannelId());

        ArrayList<CatOmService> serviceList = new ArrayList<>();
        order.setServiceList(serviceList);

        for (NumTypeWithPin child : clhMsg.getNumberWithPin()) {
            CatOmService service = new CatOmService();
            serviceList.add(service);

            service.setOrderId(order.getOrderId());
            service.setPortId(child.getPortId());
            service.setProcessType(order.getProcessType());
            service.setMsisdn(child.getMSISDN());
            service.setPinCode(child.getPinCode());
            service.setDonor(order.getDonor());
            service.setRecipient(order.getRecipient());
            service.setZone(order.getZone());
            service.setOperatorCode(order.getOperatorCode());
        }
        return msg;
    }

    private static CatOmBaseMsg listPortNotification(MessageHeaderType messageHeader, PortNotMsgType clhMsg) {
        CatOmBaseMsg msg = buildCatOmBaseMsg(messageHeader);

        ArrayList<CatOmOrder> orderList = new ArrayList<>();
        msg.setMsgList(orderList);

        CatOmOrder order = new CatOmOrder();
        orderList.add(order);

        order.setOrderId(clhMsg.getOrderId());
        order.setProcessType(clhMsg.getProcessType());

        ArrayList<CatOmService> serviceList = new ArrayList<>();
        order.setServiceList(serviceList);

        for (NumTypeWithFlagWithPortDate child : clhMsg.getNumberWithFlagWithPortDate()) {
            CatOmService service = new CatOmService();
            serviceList.add(service);

            service.setOrderId(order.getOrderId());
            service.setPortId(child.getPortId());
            service.setMsisdn(child.getMSISDN());
            service.setPortingDate(child.getPortingDate());
            service.setNumAppFlag(child.getNumApprovedFlag());
            service.setRejectReasonCode(child.getRejectReasonCode());
            service.setCorrectPinCode(child.getCorrectPinCode());
        }
        return msg;
    }

    private static CatOmBaseMsg listPortNotificationException(MessageHeaderType messageHeader, PortNotExceptMsgType clhMsg) {
        CatOmBaseMsg msg = buildCatOmBaseMsg(messageHeader);

        ArrayList<CatOmOrder> orderList = new ArrayList<>();
        msg.setMsgList(orderList);

        CatOmOrder order = new CatOmOrder();
        orderList.add(order);

        order.setOrderId(clhMsg.getOrderId());
        order.setProcessType(clhMsg.getProcessType());

        ArrayList<CatOmService> serviceList = new ArrayList<>();
        order.setServiceList(serviceList);

        CatOmService service = new CatOmService();
        serviceList.add(service);

        service.setOrderId(order.getOrderId());
        service.setPortId(clhMsg.getPortId());
        service.setMsisdn(clhMsg.getMSISDN());
        service.setPortingDate(clhMsg.getPortingDate());

        return msg;
    }

    private static CatOmBaseMsg listPortAct(MessageHeaderType messageHeader, PortDeactMsgType clhMsg) {
        CatOmBaseMsg msg = buildCatOmBaseMsg(messageHeader);

        ArrayList<CatOmService> serviceList = new ArrayList<>();
        msg.setMsgList(serviceList);

        CatOmService service = new CatOmService();
        serviceList.add(service);

        service.setOrderId(clhMsg.getOrderId());
        service.setPortId(clhMsg.getPortId());
        service.setMsisdn(clhMsg.getMSISDN());
        service.setProcessType(clhMsg.getProcessType());
        
        return msg;
    }

    private static CatOmBaseMsg listNumReturnAck(MessageHeaderType messageHeader, NumReturnAckMsgType clhMsg) {
        CatOmBaseMsg msg = buildCatOmBaseMsg(messageHeader);

        ArrayList<CatOmOrder> orderList = new ArrayList<>();
        msg.setMsgList(orderList);

        CatOmOrder order = new CatOmOrder();
        orderList.add(order);

        order.setOrderId(clhMsg.getOrderId());

        ArrayList<CatOmService> serviceList = new ArrayList<>();
        order.setServiceList(serviceList);

        for (NumTypeWithCLHFlag child : clhMsg.getNumberWithCLHFlag()) {
            CatOmService service = new CatOmService();
            serviceList.add(service);

            service.setOrderId(order.getOrderId());
            service.setPortId(child.getPortId());
            service.setMsisdn(child.getMSISDN());
            service.setClhAccpFlag(child.getCLHAccepted());
            service.setClhRejCode(child.getCLHRejectCode());
        }
        return msg;
    }

    private static CatOmBaseMsg listNumReturnReqDonor(MessageHeaderType messageHeader, NumReturnFwdMsgType clhMsg) {
        CatOmBaseMsg msg = buildCatOmBaseMsg(messageHeader);

        ArrayList<CatOmOrder> orderList = new ArrayList<>();
        msg.setMsgList(orderList);

        CatOmOrder order = new CatOmOrder();
        orderList.add(order);

        order.setOrderId(clhMsg.getOrderId());

        ArrayList<CatOmService> serviceList = new ArrayList<>();
        order.setServiceList(serviceList);

        for (NumTypeBase child : clhMsg.getNumberDataBase()) {
            CatOmService service = new CatOmService();
            serviceList.add(service);

            service.setPortId(child.getPortId());
            service.setMsisdn(child.getMSISDN());
        }
        return msg;
    }

    private static CatOmBaseMsg listBroadcast(MessageHeaderType messageHeader, PortBroadcastMsgType clhMsg) {
        CatOmBaseMsg msg = buildCatOmBaseMsg(messageHeader);

        ArrayList<CatOmService> serviceList = new ArrayList<>();
        msg.setMsgList(serviceList);

        CatOmService service = new CatOmService();
        serviceList.add(service);

        service.setPortId(clhMsg.getPortId());
        service.setMsisdn(clhMsg.getMSISDN());
        service.setDonor(clhMsg.getDonor());
        service.setRecipient(clhMsg.getRecipient());
        service.setRoute(clhMsg.getRoute());
        
        return msg;
    }

    private static CatOmBaseMsg listPortTerminated(MessageHeaderType messageHeader, PortTerminatedMsgType clhMsg) {
        CatOmBaseMsg msg = buildCatOmBaseMsg(messageHeader);

        ArrayList<CatOmOrder> orderList = new ArrayList<>();
        msg.setMsgList(orderList);

        CatOmOrder order = new CatOmOrder();
        orderList.add(order);

        order.setOrderId(clhMsg.getOrderId());
        order.setProcessType(clhMsg.getProcessType());
        order.setRecipient(clhMsg.getRecipient());
        order.setDonor(clhMsg.getDonor());

        ArrayList<CatOmService> serviceList = new ArrayList<>();
        order.setServiceList(serviceList);

        for (NumTypeBase child : clhMsg.getNumberDataBase()) {
            CatOmService service = new CatOmService();
            serviceList.add(service);

            service.setOrderId(order.getOrderId());
            service.setPortId(child.getPortId());
            service.setProcessType(order.getProcessType());
            service.setMsisdn(child.getMSISDN());
            service.setDonor(order.getDonor());
            service.setRecipient(order.getRecipient());
        }

        return msg;
    }

    private static CatOmBaseMsg listPortTimerNotification(MessageHeaderType messageHeader, TimerNotifMsgType clhMsg) {
        CatOmBaseMsg msg = buildCatOmBaseMsg(messageHeader);

        ArrayList<CatOmOrder> orderList = new ArrayList<>();
        msg.setMsgList(orderList);

        CatOmOrder order = new CatOmOrder();
        orderList.add(order);

        order.setOrderId(clhMsg.getOrderId());
        order.setProcessType(clhMsg.getProcessType());
        order.setTimeMsgId(clhMsg.getMessageID());
        order.setTimeTimerCode(clhMsg.getTimerCode());

        ArrayList<CatOmService> serviceList = new ArrayList<>();
        order.setServiceList(serviceList);

        for (NumTypeBase child : clhMsg.getNumberDataBase()) {
            CatOmService service = new CatOmService();
            serviceList.add(service);

            service.setOrderId(order.getOrderId());
            service.setPortId(child.getPortId());
            service.setProcessType(order.getProcessType());
            service.setMsisdn(child.getMSISDN());
        }

        return msg;
    }

    private static CatOmBaseMsg listErrorNotification(MessageHeaderType messageHeader, ErrorNotifMsgType clhMsg) {
        CatOmBaseMsg msg = buildCatOmBaseMsg(messageHeader);

        ArrayList<CatOmService> serviceList = new ArrayList<>();
        msg.setMsgList(serviceList);

        CatOmService service = new CatOmService();
        serviceList.add(service);

        service.setProcessType(clhMsg.getProcessType());
        service.setOrderId(clhMsg.getOrderId());
        service.setPortId(clhMsg.getPortId());
        service.setMsisdn(clhMsg.getMSISDN());
        service.setNotifyErrCode(clhMsg.getErrorCode());
        service.setNotifyErrDesc(clhMsg.getErrorDesc());
        service.setNotifyErrMsgId(clhMsg.getErroredMessageID());

        return msg;
    }
    
    public static BigInteger bigDecimalToBigInteger(BigDecimal bigDecimal) {
        if (bigDecimal != null) {
            return bigDecimal.toBigInteger();
        }
        return null;
    }
}
