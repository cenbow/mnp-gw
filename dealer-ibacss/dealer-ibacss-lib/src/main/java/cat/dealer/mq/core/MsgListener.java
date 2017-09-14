/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cat.dealer.mq.core;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

/**
 *
 * @author HP-CAT
 */
public class MsgListener implements ChannelAwareMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(MsgListener.class);
    private MsgHandler msgHandler;
    private Long retryDelay;

    public void setMsgHandler(MsgHandler msgHandler) {
        this.msgHandler = msgHandler;
    }

    public void setRetryDelay(Long retryDelay) {
        this.retryDelay = retryDelay;
    }

    @Override
    public void onMessage(Message msg, Channel channel) throws Exception {
        logger.debug("ListenReceived length: {}", msg.getBody().length);
        if (retryDelay != null && msg.getMessageProperties().isRedelivered()) {
            logger.debug("Sleeping for {} ms before retry", retryDelay);
            Thread.sleep(retryDelay);
        }

        msgHandler.processMsg(msg);

        logger.info("ListenSent length: {}", msg.getBody().length);
    }
}
