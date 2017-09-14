/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mq.core;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

/**
 *
 * @author HP-CAT
 */
public class MsgListenerScheduler extends MsgListener implements ChannelAwareMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(MsgListenerScheduler.class);
    private MsgTemplate msgConsumer;

    public void setMsgConsumer(MsgTemplate msgConsumer) {
        this.msgConsumer = msgConsumer;
    }

    @Override
    public void onMessage(Message msg, Channel channel) throws Exception {
        if (getRetryDelay() != null && msg.getMessageProperties().isRedelivered()) {
            logger.debug("Sleeping for {} ms before retry", getRetryDelay());
            Thread.sleep(getRetryDelay());
        }

        msgConsumer.onConsume();
    }
}
