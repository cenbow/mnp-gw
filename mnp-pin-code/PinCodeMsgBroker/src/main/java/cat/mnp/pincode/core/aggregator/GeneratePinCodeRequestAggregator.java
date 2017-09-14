/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.pincode.core.aggregator;

import cat.mnp.pincode.ws.portout.GeneratePinCodeRequest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.integration.annotation.Aggregator;

/**
 *
 * @author anuchitr
 */
public class GeneratePinCodeRequestAggregator {

    @Aggregator
    public GeneratePinCodeRequest aggregate(List<GeneratePinCodeRequest> list) {
        GeneratePinCodeRequest result = new GeneratePinCodeRequest();
        List<String> msisdnList = new ArrayList<>();
        result.setMsisdnList(msisdnList);
        
        for (GeneratePinCodeRequest r : list) {
            result.setChannelType(r.getChannelType());
            result.setChannelRefNumber(r.getChannelRefNumber());
            result.setCustomerType(r.getCustomerType());
            result.setCardNumber(r.getCardNumber());
            result.getMsisdnList().addAll(r.getMsisdnList());
            result.setContactChannelType(r.getContactChannelType());
            result.setContactEmailAddress(r.getContactEmailAddress());
            result.setContactMsisdn(r.getContactMsisdn());
        }
        return result;
    }
}
