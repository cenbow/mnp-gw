/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.pincode.core.aggregator;

import cat.mnp.pincode.support.PinCodeHeaders;
import cat.mnp.pincode.ws.portout.GeneratePinCodeRequest;
import com.google.common.collect.HashMultimap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.integration.annotation.Aggregator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 *
 * @author anuchitr
 */
public class GeneratePinCodeRequestOrderIdGroupAggregator {

    @Aggregator
    public List<Message<GeneratePinCodeRequest>> aggregate(List<Message<GeneratePinCodeRequest>> list) {
        HashMultimap<String, GeneratePinCodeRequest> resultMultiMap = HashMultimap.create();
        Map<String, Boolean> isReverifiedMap = new HashMap<>();
        for (Message<GeneratePinCodeRequest> msg : list) {
            String orderId = msg.getHeaders().get(PinCodeHeaders.ORDER_ID, String.class);
            resultMultiMap.put(orderId, msg.getPayload());
            
            //if some of msisdn in the same order need to be reverified, whole order will need to be reverified
            boolean isReverified = msg.getHeaders().get(PinCodeHeaders.IS_REVERIFIED, Boolean.class);
            if (isReverified == true) {
                isReverifiedMap.put(orderId, true);
            }
        }
        
        List<Message<GeneratePinCodeRequest>> result = new ArrayList<>();
        for (Entry<String, Collection<GeneratePinCodeRequest>> entry : resultMultiMap.asMap().entrySet()) {
            Message<GeneratePinCodeRequest> msg = MessageBuilder
                .withPayload(combine(entry.getValue()))
                .setHeader(PinCodeHeaders.ORDER_ID, entry.getKey())
                .setHeader(PinCodeHeaders.IS_REVERIFIED, isReverifiedMap.getOrDefault(entry.getKey(), false))
                .build();
            result.add(msg);
        }
        
        return result;
    }
    
    private GeneratePinCodeRequest combine(Collection<GeneratePinCodeRequest> list) {
        GeneratePinCodeRequest result = new GeneratePinCodeRequest();
        List<String> msisdnList = new ArrayList<>();
        result.setMsisdnList(msisdnList);
        for (GeneratePinCodeRequest r : list) {
            result.setChannelType(r.getChannelType());
            result.setChannelRefNumber(r.getChannelRefNumber());
            result.setCustomerType(r.getCustomerType());
            result.setCardNumber(r.getCardNumber());
            msisdnList.addAll(r.getMsisdnList());
            result.setContactChannelType(r.getContactChannelType());
            result.setContactEmailAddress(r.getContactEmailAddress());
            result.setContactMsisdn(r.getContactMsisdn());
        }
        return result;
    }
}
