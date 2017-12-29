/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.pincode.core.aggregator;

import cat.mnp.pincode.support.PinCodeHeaders;
import cat.mnp.pincode.ws.portout.CancelPinCodeRequest;
import com.google.common.collect.HashMultimap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import org.springframework.integration.annotation.Aggregator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 *
 * @author CATr
 */
public class CancelPinCodeRequestGroupAggregator {

    @Aggregator
    public List<Message<CancelPinCodeRequest>> aggregate(List<Message<CancelPinCodeRequest>> list) {
        HashMultimap<String, CancelPinCodeRequest> resultMultiMap = HashMultimap.create();
        for (Message<CancelPinCodeRequest> msg : list) {
            String errorMsg = msg.getHeaders().get(PinCodeHeaders.MSG_CODE, String.class);
            resultMultiMap.put(errorMsg, msg.getPayload());
        }
        
        List<Message<CancelPinCodeRequest>> result = new ArrayList<>();
        for (Entry<String, Collection<CancelPinCodeRequest>> entry : resultMultiMap.asMap().entrySet()) {
            Message<CancelPinCodeRequest> msg = MessageBuilder
                .withPayload(combine(entry.getValue()))
                .setHeader(PinCodeHeaders.MSG_CODE, entry.getKey())
                .build();
            result.add(msg);
        }
        
        return result;
    }
    
    private CancelPinCodeRequest combine(Collection<CancelPinCodeRequest> list) {
        CancelPinCodeRequest result = new CancelPinCodeRequest();
        List<String> msisdnList = new ArrayList<>();
        result.setMsisdnList(msisdnList);
        for (CancelPinCodeRequest r : list) {
            result.setChannelType(r.getChannelType());
            result.setChannelRefNumber(r.getChannelRefNumber());
            result.setCardNumber(r.getCardNumber());
            msisdnList.addAll(r.getMsisdnList());
            result.setContactChannelType(r.getContactChannelType());
            result.setContactEmailAddress(r.getContactEmailAddress());
            result.setContactMsisdn(r.getContactMsisdn());
        }
        return result;
    }
}
