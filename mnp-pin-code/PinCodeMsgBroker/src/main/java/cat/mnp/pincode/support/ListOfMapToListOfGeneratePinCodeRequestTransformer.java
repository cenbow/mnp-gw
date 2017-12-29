/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.pincode.support;

import cat.mnp.pincode.ws.portout.ChannelType;
import cat.mnp.pincode.ws.portout.ContactChannelType;
import cat.mnp.pincode.ws.portout.CustomerType;
import cat.mnp.pincode.ws.portout.GeneratePinCodeRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.integration.annotation.Transformer;

/**
 *
 * @author CATr
 */
public class ListOfMapToListOfGeneratePinCodeRequestTransformer {
    
    @Transformer
    public List<GeneratePinCodeRequest> transform(List<Map<String, String>> in) {
        List<GeneratePinCodeRequest> out = new ArrayList<>();
        
        for (Map<String, String> row : in) {
            GeneratePinCodeRequest req = new GeneratePinCodeRequest();
            req.setChannelType(ChannelType.valueOf(row.get("CHANNEL_TYPE")));
            req.setChannelRefNumber(row.get("CHANNEL_REF_NO"));
            req.setCustomerType(CustomerType.valueOf(row.get("CUSTOMER_TYPE")));
            req.setCardNumber(row.get("REF_DOC_NO"));
            
            List<String> msisdnList = new ArrayList<>();
            msisdnList.add(row.get("MSISDN"));
            req.setMsisdnList(msisdnList);
            
            req.setContactChannelType(ContactChannelType.valueOf(row.get("CONTACT_CHANNEL_TYPE")));
            req.setContactEmailAddress(row.get("CONTACT_EMAIL_ADDRESS"));
            req.setContactMsisdn(row.get("CONTACT_MSISDN"));
            
            out.add(req);
        }
        
        return out;
    }
    
}
