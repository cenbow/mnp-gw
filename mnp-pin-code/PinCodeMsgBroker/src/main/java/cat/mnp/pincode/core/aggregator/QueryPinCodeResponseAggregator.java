/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.pincode.core.aggregator;

import cat.mnp.pincode.ws.portout.PinCodeData;
import cat.mnp.pincode.ws.portout.QueryPinCodeResponse;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.Aggregator;

/**
 *
 * @author CATr
 */
public class QueryPinCodeResponseAggregator {
    
    private static final Logger logger = LoggerFactory.getLogger(QueryPinCodeResponseAggregator.class);
    
    @Aggregator
    public QueryPinCodeResponse aggregate(List<QueryPinCodeResponse> list) {
        QueryPinCodeResponse output = new QueryPinCodeResponse();
        List<PinCodeData> pinCodeDataList = new ArrayList<>();
        output.setPinCodeDataList(pinCodeDataList);
        for (QueryPinCodeResponse l : list) {
            output.setChannelType(l.getChannelType());
            output.setChannelRefNumber(l.getChannelRefNumber());
            output.setCustomerType(l.getCustomerType());
            output.setCardNumber(l.getCardNumber());
            pinCodeDataList.addAll(l.getPinCodeDataList());
            output.setContactChannelType(l.getContactChannelType());
            output.setContactEmailAddress(l.getContactEmailAddress());
            output.setContactMsisdn(l.getContactMsisdn());
        }
        logger.info("Output: {}", output);
        return output;
    }
}
