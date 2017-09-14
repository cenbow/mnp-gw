/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.pincode.core.splitter;

import cat.mnp.pincode.ws.portout.GeneratePinCodeRequest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.integration.annotation.Splitter;

/**
 *
 * @author anuchitr
 */
public class GeneratePinCodeRequestSplitter {
    
    @Splitter
    public List<GeneratePinCodeRequest> split(GeneratePinCodeRequest req) {
        List<GeneratePinCodeRequest> list = new ArrayList<>();
        for (String msisdn : req.getMsisdnList()) {
            GeneratePinCodeRequest r = new GeneratePinCodeRequest();
            r.setChannelType(req.getChannelType());
            r.setChannelRefNumber(req.getChannelRefNumber());
            r.setCustomerType(req.getCustomerType());
            r.setCardNumber(req.getCardNumber());
            List<String> msisdnList = new ArrayList<>();
            msisdnList.add(msisdn);
            r.setMsisdnList(msisdnList);
            r.setContactChannelType(req.getContactChannelType());
            r.setContactEmailAddress(req.getContactEmailAddress());
            r.setContactMsisdn(req.getContactMsisdn());
            
            list.add(r);
        }
        return list;
    }
}
