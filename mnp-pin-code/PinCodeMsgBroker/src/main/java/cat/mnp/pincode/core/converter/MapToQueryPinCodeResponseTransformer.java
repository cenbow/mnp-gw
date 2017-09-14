/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.pincode.core.converter;

import cat.mnp.pincode.ws.portout.ChannelType;
import cat.mnp.pincode.ws.portout.ContactChannelType;
import cat.mnp.pincode.ws.portout.CustomerType;
import cat.mnp.pincode.ws.portout.PinCodeData;
import cat.mnp.pincode.ws.portout.QueryPinCodeResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.integration.annotation.Transformer;

/**
 *
 * @author anuchitr
 */
public class MapToQueryPinCodeResponseTransformer {
    
    @Transformer
    public QueryPinCodeResponse transform(Map<String, String> m) {
        QueryPinCodeResponse r = new QueryPinCodeResponse();
        r.setChannelType(ChannelType.valueOf(m.get("CHANNEL_TYPE")));
        r.setChannelRefNumber(m.get("CHANNEL_REF_NO"));
        r.setCustomerType(CustomerType.valueOf(m.get("CUSTOMER_TYPE")));
        r.setCardNumber(m.get("REF_DOC_NO"));
        List<PinCodeData> pinCodeDataList = new ArrayList<>();
        PinCodeData pinCodeData = new PinCodeData();
        pinCodeData.setPinCode(m.get("PIN_CODE"));
        pinCodeData.setMsisdn(m.get("MSISDN"));
        pinCodeData.setRejectReason(m.get("REJECT_REASON"));
        pinCodeDataList.add(pinCodeData);
        r.setPinCodeDataList(pinCodeDataList);
        r.setContactChannelType(ContactChannelType.valueOf(m.get("CONTACT_CHANNEL_TYPE")));
        r.setContactEmailAddress(m.get("CONTACT_EMAIL_ADDRESS"));
        r.setContactMsisdn(m.get("CONTACT_MSISDN"));
        return r;
    }
}
