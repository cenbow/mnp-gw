/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.ws.service;

import cat.mnp.mvno.ws.order.priority.ChangePortReqPriorityService;
import cat.mnp.mvno.ws.order.priority.ChangePriorityResponse;
import cat.mnp.mvno.ws.order.priority.OrderIdList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author HP-CAT
 */
public class DummyChangePortReqPriorityServiceImpl implements ChangePortReqPriorityService {
    
    private static final Logger logger = LoggerFactory.getLogger(DummyChangePortReqPriorityServiceImpl.class);
    private String successResult;
    
    public void setSuccessResult(String successResult) {
        this.successResult = successResult;
    }
    
    @Override
    public ChangePriorityResponse changePriority(OrderIdList orderIdList) {
        ChangePriorityResponse changePriorityResponse = new ChangePriorityResponse();
        
        List<String> orderIdStringList = orderIdList.getOrderId();
        logger.debug("orderIdStringList: {}", orderIdStringList);
        
        changePriorityResponse.setReturn(successResult);
        
        return changePriorityResponse;
    }
}
