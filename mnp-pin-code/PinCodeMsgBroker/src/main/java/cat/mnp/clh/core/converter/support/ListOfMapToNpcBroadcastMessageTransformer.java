/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.clh.core.converter.support;

import cat.mnp.clh.util.NpcMessageUtils;
import com.telcordia.inpac.ws.jaxb.MessageFooterType;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import com.telcordia.inpac.ws.jaxb.NPCMessageType;
import com.telcordia.inpac.ws.jaxb.PortBroadcastMsgType;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

/**
 *
 * @author CATr
 */
public class ListOfMapToNpcBroadcastMessageTransformer {

    @Transformer
    public NPCMessageData transform(@Headers Map<String, String> headers, @Payload List<Map<String, String>> dataList) {
        NPCMessageData npcMessageData = new NPCMessageData();
        NPCDataType npcDataType = new NPCDataType();
        npcMessageData.setNPCData(npcDataType);

        MessageHeaderType messageHeader = new MessageHeaderType();
        npcDataType.setMessageHeader(messageHeader);
        transformMsgHeader(messageHeader, headers);
        
        NPCMessageType npcMessages = new NPCMessageType();
        npcDataType.setNPCMessages(npcMessages);
        List<PortBroadcastMsgType> portBroadcast = npcMessages.getPortBroadcast();
        transformNpcMessages(portBroadcast, dataList);
        
        MessageFooterType messageFooter = new MessageFooterType();
        npcDataType.setMessageFooter(messageFooter);
        messageFooter.setChecksum(BigInteger.valueOf(portBroadcast.size()));
        
        return npcMessageData;
    }

    private void transformMsgHeader(MessageHeaderType messageHeader, Map<String, String> headers) {
        messageHeader.setMessageID(new BigInteger(headers.get("MsgId")));
        messageHeader.setMessageCreateTimeStamp(headers.get("MsgCreateTimestamp"));
        messageHeader.setPortType(new BigInteger(headers.get("PortType")));
        messageHeader.setReceiver(headers.get("Receiver"));
        messageHeader.setSender(headers.get("Sender"));
        messageHeader.setSoapRequestId(headers.get("SoapRequestId"));
    }

    private void transformNpcMessages(List<PortBroadcastMsgType> portBroadcast, List<Map<String, String>> dataList) {
        for (Map<String, String> data : dataList) {
            PortBroadcastMsgType m = new PortBroadcastMsgType();
            portBroadcast.add(m);
            
            m.setDonor(data.get("DONOR"));
            m.setRecipient(data.get("RECIPIENT"));
            m.setNumberHolderInd(data.get("NUMBER_HOLDER_IND"));
            m.setMSISDN(data.get("MSISDN"));
            m.setPortId(data.get("PORT_ID"));
            m.setRoute(data.get("ROUTE"));
        }
    }
    
}
