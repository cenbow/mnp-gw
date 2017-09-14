/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.clh.util;

import com.google.common.base.Strings;
import com.telcordia.inpac.ws.jaxb.ErrorNotifMsgType;
import com.telcordia.inpac.ws.jaxb.MessageFooterType;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import com.telcordia.inpac.ws.jaxb.NPCMessageType;
import com.telcordia.inpac.ws.jaxb.NumReturnAckMsgType;
import com.telcordia.inpac.ws.jaxb.NumReturnFwdMsgType;
import com.telcordia.inpac.ws.jaxb.NumReturnReqMsgType;
import com.telcordia.inpac.ws.jaxb.NumTypeNoPortId;
import com.telcordia.inpac.ws.jaxb.NumTypeBase;
import com.telcordia.inpac.ws.jaxb.NumTypeWithPin;
import com.telcordia.inpac.ws.jaxb.NumTypeWithFlagWithPortDate;
import com.telcordia.inpac.ws.jaxb.NumTypeWithPinWithCLHFlag;
import com.telcordia.inpac.ws.jaxb.NumTypeWithPinNoPortId;
import com.telcordia.inpac.ws.jaxb.NumTypeWithCLHFlag;
import com.telcordia.inpac.ws.jaxb.NumTypeWithFlag;
import com.telcordia.inpac.ws.jaxb.PortDeactMsgType;
import com.telcordia.inpac.ws.jaxb.PortCancelMsgType;
import com.telcordia.inpac.ws.jaxb.PortNotExceptMsgType;
import com.telcordia.inpac.ws.jaxb.PortNotMsgType;
import com.telcordia.inpac.ws.jaxb.PortReqAckMsgType;
import com.telcordia.inpac.ws.jaxb.PortReqFwdMsgType;
import com.telcordia.inpac.ws.jaxb.PortReqMsgType;
import com.telcordia.inpac.ws.jaxb.PortRespMsgType;
import com.telcordia.inpac.ws.jaxb.PortRvrsDonorMsgType;
import com.telcordia.inpac.ws.jaxb.PortRvrsRecipientMsgType;
import com.telcordia.inpac.ws.jaxb.PortTerminatedMsgType;
import com.telcordia.inpac.ws.jaxb.TimerNotifMsgType;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.io.IOUtils;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Service;
import com.telcordia.inpac.ws.jaxb.PortBroadcastMsgType;

/**
 *
 * @author HP-CAT
 */
@Service
public class NpcMessageUtils {
    
    private static final int MAX_SOAP_REQUEST_ID = 1000;
    private static final int MAX_SOAP_REQUEST_ID_DIGIT = 3;
    private static final char SOAP_REQUEST_ID_PAD_CHAR = '0';

    public static NPCMessageData unMarshal(Unmarshaller unMarshaller, String xml) throws IOException {
        StreamSource source = new StreamSource(IOUtils.toInputStream(xml));
        return (NPCMessageData) unMarshaller.unmarshal(source);
    }

    public static String marshal(Marshaller marshaller, NPCMessageData npcMessageData) throws IOException {
        String xml;
        try (StringWriter sw = new StringWriter()) {
            StreamResult result = new StreamResult(sw);
            marshaller.marshal(npcMessageData, result);
            xml = sw.toString();
        }
        return xml;
    }

    public static List<PortBroadcastMsgType> listBroadcastMsg(NPCMessageType npcMessages) {
        ArrayList<PortBroadcastMsgType> msgList = new ArrayList<>();
        msgList.addAll(npcMessages.getPortBroadcast());
        msgList.addAll(npcMessages.getPortBroadcastException());
        msgList.addAll(npcMessages.getPortReversalBroadcast());
        msgList.addAll(npcMessages.getNumerReturnBroadcast());
        return msgList;
    }
    
    public static List listOtherMsg(NPCMessageType npcMessages) {
        ArrayList msgList = new ArrayList();
        msgList.addAll(npcMessages.getPortRequest());//1001
        msgList.addAll(npcMessages.getPortRequestAck());//1002
        msgList.addAll(npcMessages.getPortRequestFwd());//1003
        msgList.addAll(npcMessages.getPortResponse());//1004
        msgList.addAll(npcMessages.getPortCancel());//1005
        msgList.addAll(npcMessages.getPortNotification());//1006,1007
        msgList.addAll(npcMessages.getPortNotificationException());//1011,1012
        msgList.addAll(npcMessages.getPortDeact());//1008,1009
        msgList.addAll(npcMessages.getPortDeactException());//1013,1014
        msgList.addAll(npcMessages.getPortReversalDonor());//2001
        msgList.addAll(npcMessages.getPortReversalRecipient());//2002
        msgList.addAll(npcMessages.getNumberReturn());//3001
        msgList.addAll(npcMessages.getNumberReturnAck());//3002
        msgList.addAll(npcMessages.getNumberReturnFwd());//3003
        msgList.addAll(npcMessages.getSynchronisationResponse());//4002
        msgList.addAll(npcMessages.getPortTerminated());//9001
        msgList.addAll(npcMessages.getTimerNotification());//9998
        msgList.addAll(npcMessages.getErrorNotification());//9999
        return msgList;
    }

    public static List listInboundOtherMsg(NPCMessageType npcMessages) {
        ArrayList msgList = new ArrayList();
        msgList.addAll(npcMessages.getPortRequestAck());//1002
        msgList.addAll(npcMessages.getPortRequestFwd());//1003
        msgList.addAll(npcMessages.getPortNotification());//1006,1007
        msgList.addAll(npcMessages.getPortNotificationException());//1011,1012
        msgList.addAll(npcMessages.getPortDeact());//1009
        msgList.addAll(npcMessages.getPortDeactException());//1014
        msgList.addAll(npcMessages.getNumberReturnAck());//3002
        msgList.addAll(npcMessages.getNumberReturnFwd());//3003
        msgList.addAll(npcMessages.getSynchronisationResponse());//4002
        msgList.addAll(npcMessages.getPortTerminated());//9001
        msgList.addAll(npcMessages.getTimerNotification());//9998
        msgList.addAll(npcMessages.getErrorNotification());//9999
        return msgList;
    }

    public static List listOutboundOtherMsg(NPCMessageType npcMessages) {
        ArrayList msgList = new ArrayList();
        msgList.addAll(npcMessages.getPortRequest());//1001
        msgList.addAll(npcMessages.getPortResponse());//1004
        msgList.addAll(npcMessages.getPortCancel());//1005
        msgList.addAll(npcMessages.getPortDeact());//1008
        msgList.addAll(npcMessages.getPortDeactException());//1013
        msgList.addAll(npcMessages.getPortReversalDonor());//2001
        msgList.addAll(npcMessages.getPortReversalRecipient());//2002
        msgList.addAll(npcMessages.getNumberReturn());//3001
        return msgList;
    }

    public static List<HashMap<String, String>> extractOtherMsgData(MessageHeaderType messageHeader, Object msgObject) {
        String msgId = messageHeader.getMessageID().toString();
        List<HashMap<String, String>> dataList = new ArrayList<>();
        switch (msgId) {
            case "1001":
                PortReqMsgType portReqMsg = (PortReqMsgType) msgObject;
                for (NumTypeWithPinNoPortId child : portReqMsg.getNumberWithPinNoPortId()) {
                    HashMap<String, String> m = new HashMap<>();
                    m.put("orderId", portReqMsg.getOrderId());
                    m.put("msisdn", child.getMSISDN());
                    dataList.add(m);
                }
                break;
            case "1002":
                PortReqAckMsgType portReqAckMsg = (PortReqAckMsgType) msgObject;
                for (NumTypeWithPinWithCLHFlag child : portReqAckMsg.getNumberWithPinWithCLHFlag()) {
                    HashMap<String, String> m = new HashMap<>();
                    m.put("orderId", portReqAckMsg.getOrderId());
                    m.put("msisdn", child.getMSISDN());
                    m.put("portId", child.getPortId());
                    dataList.add(m);
                }
                break;
            case "1003":
                PortReqFwdMsgType portReqDonor = (PortReqFwdMsgType) msgObject;
                for (NumTypeWithPin child : portReqDonor.getNumberWithPin()) {
                    HashMap<String, String> m = new HashMap<>();
                    m.put("orderId", portReqDonor.getOrderId());
                    m.put("msisdn", child.getMSISDN());
                    m.put("portId", child.getPortId());
                    dataList.add(m);
                }
                break;
            case "1004":
                PortRespMsgType portRespMsg = (PortRespMsgType) msgObject;
                for (NumTypeWithFlag child : portRespMsg.getNumberWithFlag()) {
                    HashMap<String, String> m = new HashMap<>();
                    m.put("orderId", portRespMsg.getOrderId());
                    m.put("msisdn", child.getMSISDN());
                    m.put("portId", child.getPortId());
                    dataList.add(m);
                }
                break;
            case "1005":
                PortCancelMsgType portCancelMsg = (PortCancelMsgType) msgObject;
                for (NumTypeBase child : portCancelMsg.getNumberDataBase()) {
                    HashMap<String, String> m = new HashMap<>();
                    m.put("orderId", portCancelMsg.getOrderId());
                    m.put("msisdn", child.getMSISDN());
                    m.put("portId", child.getPortId());
                    dataList.add(m);
                }
                break;
            case "1006":
            case "1007":
                PortNotMsgType portNotMsg = (PortNotMsgType) msgObject;
                for (NumTypeWithFlagWithPortDate child : portNotMsg.getNumberWithFlagWithPortDate()) {
                    HashMap<String, String> m = new HashMap<>();
                    m.put("orderId", portNotMsg.getOrderId());
                    m.put("msisdn", child.getMSISDN());
                    m.put("portId", child.getPortId());
                    dataList.add(m);
                }
                break;
            case "1008":
            case "1009":
            case "1014":
            case "1013":
                PortDeactMsgType portBaseMsg = (PortDeactMsgType) msgObject;
                HashMap<String, String> portBaseMsgHashMap = new HashMap<>();
                portBaseMsgHashMap.put("orderId", portBaseMsg.getOrderId());
                portBaseMsgHashMap.put("msisdn", portBaseMsg.getMSISDN());
                portBaseMsgHashMap.put("portId", portBaseMsg.getPortId());
                dataList.add(portBaseMsgHashMap);
                break;
            case "1011":
            case "1012":
                PortNotExceptMsgType portNotExceptMsg = (PortNotExceptMsgType) msgObject;
                HashMap<String, String> portNotExceptHashMap = new HashMap<>();
                portNotExceptHashMap.put("orderId", portNotExceptMsg.getOrderId());
                portNotExceptHashMap.put("msisdn", portNotExceptMsg.getMSISDN());
                portNotExceptHashMap.put("portId", portNotExceptMsg.getPortId());
                dataList.add(portNotExceptHashMap);
                break;
            case "2001":
                PortRvrsDonorMsgType portRvrsRequestDonor = (PortRvrsDonorMsgType) msgObject;
                HashMap<String, String> portRvrsRequestDonorHashMap = new HashMap<>();
                portRvrsRequestDonorHashMap.put("msisdn", portRvrsRequestDonor.getMSISDN());
                portRvrsRequestDonorHashMap.put("portId", portRvrsRequestDonor.getPortId());
                dataList.add(portRvrsRequestDonorHashMap);
                break;
            case "2002":
                PortRvrsRecipientMsgType portRvrsRequestRecp = (PortRvrsRecipientMsgType) msgObject;
                HashMap<String, String> portRvrsRequestRecpHashMap = new HashMap<>();
                portRvrsRequestRecpHashMap.put("msisdn", portRvrsRequestRecp.getMSISDN());
                portRvrsRequestRecpHashMap.put("portId", portRvrsRequestRecp.getPortId());
                dataList.add(portRvrsRequestRecpHashMap);
                break;
            case "2010":
                PortBroadcastMsgType portRvrsBroadcast = (PortBroadcastMsgType) msgObject;
                HashMap<String, String> portRvrsBroadcastHashMap = new HashMap<>();
                portRvrsBroadcastHashMap.put("msisdn", portRvrsBroadcast.getMSISDN());
                portRvrsBroadcastHashMap.put("portId", portRvrsBroadcast.getPortId());
                dataList.add(portRvrsBroadcastHashMap);
                break;
            case "3001":
                NumReturnReqMsgType numReturnReq = (NumReturnReqMsgType) msgObject;
                for (NumTypeNoPortId child : numReturnReq.getNumberNoPortId()) {
                    HashMap<String, String> m = new HashMap<>();
                    m.put("orderId", numReturnReq.getOrderId());
                    m.put("msisdn", child.getMSISDN());
                    dataList.add(m);
                }
                break;
            case "3002":
                NumReturnAckMsgType numReturnAck = (NumReturnAckMsgType) msgObject;
                for (NumTypeWithCLHFlag child : numReturnAck.getNumberWithCLHFlag()) {
                    HashMap<String, String> m = new HashMap<>();
                    m.put("orderId", numReturnAck.getOrderId());
                    m.put("msisdn", child.getMSISDN());
                    m.put("portId", child.getPortId());
                    dataList.add(m);
                }
                break;
            case "3003":
                NumReturnFwdMsgType numReturnRangeHol = (NumReturnFwdMsgType) msgObject;
                for (NumTypeBase child : numReturnRangeHol.getNumberDataBase()) {
                    HashMap<String, String> m = new HashMap<>();
                    m.put("orderId", numReturnRangeHol.getOrderId());
                    m.put("msisdn", child.getMSISDN());
                    m.put("portId", child.getPortId());
                    dataList.add(m);
                }
                break;
            case "4001":
                break;
            case "4002":
                HashMap<String, String> syncReqMsgHashMap = new HashMap<>();
                syncReqMsgHashMap.put("orderId", null);
                syncReqMsgHashMap.put("msisdn", null);
                syncReqMsgHashMap.put("portId", null);
                dataList.add(syncReqMsgHashMap);
                break;
            case "9001":
                PortTerminatedMsgType portTerminatedMsg = (PortTerminatedMsgType) msgObject;
                for (NumTypeBase child : portTerminatedMsg.getNumberDataBase()) {
                    HashMap<String, String> m = new HashMap<>();
                    m.put("orderId", portTerminatedMsg.getOrderId());
                    m.put("msisdn", child.getMSISDN());
                    m.put("portId", child.getPortId());
                    dataList.add(m);
                }
                break;
            case "9998":
                TimerNotifMsgType timerNotifMsg = (TimerNotifMsgType) msgObject;
                for (NumTypeBase child : timerNotifMsg.getNumberDataBase()) {
                    HashMap<String, String> m = new HashMap<>();
                    m.put("orderId", timerNotifMsg.getOrderId());
                    m.put("msisdn", child.getMSISDN());
                    m.put("portId", child.getPortId());
                    dataList.add(m);
                }
                break;
            case "9999":
                ErrorNotifMsgType errorNotifMsg = (ErrorNotifMsgType) msgObject;
                HashMap<String, String> errorNotifMsgHashMap = new HashMap<>();
                errorNotifMsgHashMap.put("orderId", errorNotifMsg.getOrderId());
                errorNotifMsgHashMap.put("msisdn", errorNotifMsg.getMSISDN());
                errorNotifMsgHashMap.put("portId", errorNotifMsg.getPortId());
                dataList.add(errorNotifMsgHashMap);
                break;
        }
        return dataList;
    }
    
    public static NPCMessageData buildNpcMessageData(MessageHeaderType messageHeader, List msgList, int count) {
        NPCMessageData npcMessageData = new NPCMessageData();
        NPCDataType npcDataType = new NPCDataType();
        npcMessageData.setNPCData(npcDataType);
        npcDataType.setMessageHeader(messageHeader);
        MessageFooterType messageFooter = new MessageFooterType();
        messageFooter.setChecksum(BigInteger.valueOf(count));
        npcDataType.setMessageFooter(messageFooter);

        NPCMessageType mvnoNpcMessages = new NPCMessageType();
        npcDataType.setNPCMessages(mvnoNpcMessages);

        String msgId = messageHeader.getMessageID().toString();
        setNpcMessages(msgId, mvnoNpcMessages, msgList);
        return npcMessageData;
    }

    public static NPCMessageData buildNpcMessageData(MessageHeaderType messageHeader, Object msgObject) {
        NPCMessageData npcMessageData = new NPCMessageData();
        NPCDataType npcDataType = new NPCDataType();
        npcMessageData.setNPCData(npcDataType);
        npcDataType.setMessageHeader(messageHeader);
        MessageFooterType messageFooter = new MessageFooterType();
        messageFooter.setChecksum(BigInteger.valueOf(NpcMessageUtils.extractOtherMsgData(messageHeader, msgObject).size()));
        npcDataType.setMessageFooter(messageFooter);

        NPCMessageType mvnoNpcMessages = new NPCMessageType();
        npcDataType.setNPCMessages(mvnoNpcMessages);

        String msgId = messageHeader.getMessageID().toString();
        List msgList = new ArrayList<>();
        msgList.add(msgObject);
        setNpcMessages(msgId, mvnoNpcMessages, msgList);
        return npcMessageData;
    }

    private static void setNpcMessages(String msgId, NPCMessageType mvnoNpcMessages, List msgList) {
        switch (msgId) {
            case "1001":
                mvnoNpcMessages.setPortRequest(msgList);
                break;
            case "1002":
                mvnoNpcMessages.setPortRequestAck(msgList);
                break;
            case "1003":
                mvnoNpcMessages.setPortRequestFwd(msgList);
                break;
            case "1004":
                mvnoNpcMessages.setPortResponse(msgList);
                break;
            case "1005":
                mvnoNpcMessages.setPortCancel(msgList);
                break;
            case "1006":
            case "1007":
                mvnoNpcMessages.setPortNotification(msgList);
                break;
            case "1008":
            case "1009":
                mvnoNpcMessages.setPortDeact(msgList);
                break;
            case "1010":
                mvnoNpcMessages.setPortBroadcast(msgList);
                break;
            case "1011":
            case "1012":
                mvnoNpcMessages.setPortNotificationException(msgList);
                break;
            case "1013":
            case "1014":
                mvnoNpcMessages.setPortDeactException(msgList);
                break;
            case "1015":
                mvnoNpcMessages.setPortBroadcastException(msgList);
                break;
            case "2001":
                mvnoNpcMessages.setPortReversalDonor(msgList);
                break;
            case "2002":
                mvnoNpcMessages.setPortReversalRecipient(msgList);
                break;
            case "2010":
                mvnoNpcMessages.setPortReversalBroadcast(msgList);
                break;
            case "3001":
                mvnoNpcMessages.setNumReturn(msgList);
                break;
            case "3002":
                mvnoNpcMessages.setNumberReturnAck(msgList);
                break;
            case "3003":
                mvnoNpcMessages.setNumberReturnFwd(msgList);
                break;
            case "3010":
                mvnoNpcMessages.setNumberReturnBroadcast(msgList);
                break;
            case "4001":
                mvnoNpcMessages.setSynchronisationRequest(msgList);
                break;
            case "4002":
                mvnoNpcMessages.setSynchronisationResponse(msgList);
                break;
            case "9001":
                mvnoNpcMessages.setPortTerminated(msgList);
                break;
            case "9998":
                mvnoNpcMessages.setTimerNotification(msgList);
                break;
            case "9999":
                mvnoNpcMessages.setErrorNotification(msgList);
                break;
        }
    }

    public static List<PortReqMsgType> listPortRequest(NPCMessageType npcMessages) {//1001
        ArrayList<PortReqMsgType> msgList = new ArrayList<>();
        msgList.addAll(npcMessages.getPortRequest());
        return msgList;
    }

    public static List<PortReqAckMsgType> listPortRequestAck(NPCMessageType npcMessages) {//1002
        ArrayList<PortReqAckMsgType> msgList = new ArrayList<>();
        msgList.addAll(npcMessages.getPortRequestAck());
        return msgList;
    }

    public static List<PortReqFwdMsgType> listPortRequestDonor(NPCMessageType npcMessages) {//1003
        ArrayList<PortReqFwdMsgType> msgList = new ArrayList<>();
        msgList.addAll(npcMessages.getPortRequestFwd());
        return msgList;
    }

    public static List listPortNotification(NPCMessageType npcMessages) {
        ArrayList msgList = new ArrayList();
        msgList.addAll(npcMessages.getPortNotification());
        msgList.addAll(npcMessages.getPortNotificationException());
        return msgList;
    }
    
    public static String getNewSoapRequestId(String originalSoapReqId, long deliveryTag, int sequence) {
        Integer soapRequestId = (int) (Integer.parseInt(originalSoapReqId) + deliveryTag + sequence) % MAX_SOAP_REQUEST_ID;
        return Strings.padStart(soapRequestId.toString(), MAX_SOAP_REQUEST_ID_DIGIT, SOAP_REQUEST_ID_PAD_CHAR);
    }
}
