/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.ws.service;

import cat.mnp.mvno.dao.order.generator.GenerateOrderIdDto;
import cat.mnp.mvno.ws.order.generator.GenerateOrderIdRequest;
import cat.mnp.mvno.ws.order.generator.GenerateOrderIdResponse;
import cat.mnp.mvno.ws.order.generator.OrderIdGeneratorService;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.cxf.annotations.SchemaValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;

/**
 *
 * @author HP-CAT
 */
@SchemaValidation
public class OrderIdGeneratorServiceImpl implements OrderIdGeneratorService {

    private static final Logger logger = LoggerFactory.getLogger(OrderIdGeneratorServiceImpl.class);
    private Map<String, Map<String, String>> userMapper;
    private String successResult;
    private AmqpTemplate amqpTemplate;
    private String errorText;

    public void setUserMapper(Map<String, Map<String, String>> userMapper) {
        this.userMapper = userMapper;
    }

    public void setSuccessResult(String successResult) {
        this.successResult = successResult;
    }

    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    @Override
    public GenerateOrderIdResponse generate(GenerateOrderIdRequest req) {
        GenerateOrderIdResponse resp;
        String mvnoName = mapMvnoName(req.getUserId(), req.getPassword());
        if (mvnoName.startsWith(errorText)) {
            resp = new GenerateOrderIdResponse(mvnoName, null);
            logger.info("Response: {}", resp);
            return resp;
        }

        GenerateOrderIdDto dto = new GenerateOrderIdDto(req.getOrderType(), mvnoName);
        String orderId = (String) amqpTemplate.convertSendAndReceive(dto);

        if (orderId == null) {//reply timeout raised{
            resp = new GenerateOrderIdResponse("Error timeout: cannot generate new OrderId", null);
        } else if (orderId.startsWith(errorText)) {
            resp = new GenerateOrderIdResponse(orderId, null);
        } else {
            resp = new GenerateOrderIdResponse(successResult, orderId);
        }
        logger.info("Response: {}", resp);
        return resp;
    }

    private String mapMvnoName(String userId, String password) {
        String decodedPassword = new String(Base64.decodeBase64(password));
        if (!userMapper.containsKey(userId)) {
            return "Error: username does not exists";
        } else if (!decodedPassword.equals(userMapper.get(userId).get("password"))) {
            return "Error: password does not match";
        }

        String mvnoName = userMapper.get(userId).get("sender");
        logger.debug("userId {} is {}", userId, mvnoName);
        return mvnoName;
    }
}
