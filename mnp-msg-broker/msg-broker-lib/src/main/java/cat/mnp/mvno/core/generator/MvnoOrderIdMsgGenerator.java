/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.core.generator;

import cat.mnp.mq.core.MsgHandlerBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

/**
 *
 * @author HP-CAT
 */
public class MvnoOrderIdMsgGenerator extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(MvnoOrderIdMsgGenerator.class);
    private AmqpTemplate amqpTemplate;
    private Jackson2JsonMessageConverter amqpJsonMsgConverter;
    private MessageProperties msgProperties;
    private String errorText;

    public AmqpTemplate getAmqpTemplate() {
        return amqpTemplate;
    }

    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public Jackson2JsonMessageConverter getAmqpJsonMsgConverter() {
        return amqpJsonMsgConverter;
    }

    public void setAmqpJsonMsgConverter(Jackson2JsonMessageConverter amqpJsonMsgConverter) {
        this.amqpJsonMsgConverter = amqpJsonMsgConverter;
    }

    public MessageProperties getMsgProperties() {
        return msgProperties;
    }

    public void setMsgProperties(MessageProperties msgProperties) {
        this.msgProperties = msgProperties;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    @Override
    public void processMsg(Message msg) throws Exception {
        String replyToQueue = msg.getMessageProperties().getReplyTo();
        if (logger.isTraceEnabled()) {
            logger.trace("Received q {}, body: {}", replyToQueue, new String(msg.getBody()));
        } else {
            logger.debug("Received q {}", replyToQueue);
        }

        Object msgObject = amqpJsonMsgConverter.fromMessage(msg);

        String orderId;
        try {
            orderId = getMvnoMsgDao().importMsg(msgObject);
            logger.info("OrderId for {} is {}", replyToQueue, orderId);
        } catch (Exception ex) {
            orderId = "Error: " + ex.getMessage();
            logger.info("Cannot generate orderId for " + replyToQueue, ex);
        }

        msgProperties.getHeaders().clear();

        Message newMsg = amqpJsonMsgConverter.toMessage(orderId, msgProperties);

        amqpTemplate.send(replyToQueue, newMsg);
//        if (!orderId.contains(errorText)) {//no error
//        } else {//has error
//            logger.error("Error detected while generating orderId for mvno: {}", mvnoName);
//        }
    }
}
