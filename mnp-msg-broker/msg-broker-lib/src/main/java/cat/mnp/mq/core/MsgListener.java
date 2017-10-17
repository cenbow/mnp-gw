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
public class MsgListener implements ChannelAwareMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(MsgListener.class);
    private MsgHandler msgHandler;
    private Long retryDelay = new Long("30000");

    public void setMsgHandler(MsgHandler msgHandler) {
        this.msgHandler = msgHandler;
    }

    public Long getRetryDelay() {
        return retryDelay;
    }

    public void setRetryDelay(Long retryDelay) {
        this.retryDelay = retryDelay;
    }

    @Override
    public void onMessage(Message msg, Channel channel) throws Exception {
        logger.info("[MQ] "+msg.getMessageProperties().getConsumerQueue());
        logger.info("ListenReceived length: {}", msg.getBody().length);
        if (retryDelay != null && msg.getMessageProperties().isRedelivered()) {
            logger.debug("Sleeping for {} ms before retry", retryDelay);
            Thread.sleep(retryDelay);
        }

        try { // TODO: MIW: test avoid reQ
			msgHandler.processMsg(msg);
		} catch (Exception e) {
			logger.error("(Test)ignore reQ: "+e.toString(),e);
		}

        logger.info("ListenSent length: {}", msg.getBody().length);
    }
}
