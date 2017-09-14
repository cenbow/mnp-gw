/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.messaging.support;

import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

/**
 *
 * @author anuchitr
 */
public class DelayRedeliveryChannelInterceptor extends ChannelInterceptorAdapter {
    
    private static final Logger logger = LoggerFactory.getLogger(DelayRedeliveryChannelInterceptor.class);
    private Long retryDelay = new Long("30000");

    public void setRetryDelay(Long retryDelay) {
        this.retryDelay = retryDelay;
    }
    
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        Boolean redelivered = (Boolean) message.getHeaders().getOrDefault(AmqpHeaders.REDELIVERED, false);
        if (BooleanUtils.isTrue(redelivered)) {
            logger.debug("Sleeping for {} ms before retry", retryDelay);
            try {
                Thread.sleep(retryDelay);
            } catch (InterruptedException ex) {
                logger.warn("Error while sleeping", ex);
            }
        }
        return message;
    }
}
