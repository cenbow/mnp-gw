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
import org.springframework.amqp.core.AmqpTemplate;

/**
 *
 * @author HP-CAT
 */
public class ChangePortReqPriorityServiceImpl implements ChangePortReqPriorityService {

    private static final Logger logger = LoggerFactory.getLogger(ChangePortReqPriorityServiceImpl.class);
    private AmqpTemplate amqpTemplate;
    private String successResult;

    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void setSuccessResult(String successResult) {
        this.successResult = successResult;
    }

    @Override
    public ChangePriorityResponse changePriority(OrderIdList orderIdList) {
        ChangePriorityResponse changePriorityResponse = new ChangePriorityResponse();

        List<String> orderIdStringList = orderIdList.getOrderId();

        try {
            amqpTemplate.convertAndSend(orderIdStringList);
            changePriorityResponse.setReturn(successResult);
        } catch (Exception ex) {
            logger.error("RabbitMq Exception{}: ", ex);
            changePriorityResponse.setReturn("NPC1001E: Internal Error: Could not connect to database; Please contact system administrator");
        }
        return changePriorityResponse;
    }
}
