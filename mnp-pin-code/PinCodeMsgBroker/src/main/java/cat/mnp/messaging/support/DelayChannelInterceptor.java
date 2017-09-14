/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.messaging.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

/**
 *
 * @author anuchitr
 */
public class DelayChannelInterceptor extends ChannelInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(DelayChannelInterceptor.class);
    private Long delay = new Long("10000");

    public void setDelay(Long delay) {
        this.delay = delay;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        logger.debug("Sleeping for {} ms", delay);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ex) {
            logger.warn("Error while sleeping", ex);
        }
        return message;
    }
}
