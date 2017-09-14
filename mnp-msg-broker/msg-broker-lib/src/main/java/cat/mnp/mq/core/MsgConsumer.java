/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mq.core;

import com.rabbitmq.client.AMQP.Queue.DeclareOk;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.GetResponse;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.ConnectionFactoryUtils;
import org.springframework.amqp.rabbit.core.ChannelCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.DefaultMessagePropertiesConverter;
import org.springframework.amqp.rabbit.support.MessagePropertiesConverter;

/**
 *
 * @author HP-CAT
 */
public class MsgConsumer extends RabbitTemplate implements MsgTemplate {

    private static final Logger cLogger = LoggerFactory.getLogger(MsgConsumer.class);
    private MsgHandler msgHandler;
    private String queue;
    private volatile MessagePropertiesConverter messagePropertiesConverter = new DefaultMessagePropertiesConverter();
    private volatile String encoding = "UTF-8";
    private volatile int prefetchCount = 1;
    private volatile boolean isGreedyConsume = false;
    private volatile int waitTimeout;

    public MsgHandler getMsgHandler() {
        return msgHandler;
    }

    public void setMsgHandler(MsgHandler msgHandler) {
        this.msgHandler = msgHandler;
    }

    public String getQueue() {
        return queue;
    }

    @Override
    public void setQueue(String queue) {
        this.queue = queue;
    }

    public int getPrefetchCount() {
        return prefetchCount;
    }

    public void setPrefetchCount(int prefetchCount) {
        this.prefetchCount = prefetchCount;
    }

    public boolean isIsGreedyConsume() {
        return isGreedyConsume;
    }

    public void setIsGreedyConsume(boolean isGreedyConsume) {
        this.isGreedyConsume = isGreedyConsume;
    }

    public int getWaitTimeout() {
        return waitTimeout;
    }

    public void setWaitTimeout(int waitTimeout) {
        this.waitTimeout = waitTimeout;
    }

    @Override
    public void onConsume() throws Exception {
        final ArrayList<Message> msgList = new ArrayList<>();
        try {
            execute(new ChannelCallback<Message>() {
                @Override
                public Message doInRabbit(Channel channel) throws Exception {
                    Long deliveryTag = null;

                    for (String queueName : StringUtils.split(getQueue(), ",")) {
                        if (isIsGreedyConsume()) {
                            DeclareOk q = channel.queueDeclarePassive(queueName);
                            prefetchCount = q.getMessageCount();
                        }
                        for (int i = 0; i < prefetchCount; i++) {
                            GetResponse response = channel.basicGet(queueName, !isChannelTransacted());
                            if (response != null) {
                                MessageProperties messageProps = messagePropertiesConverter.toMessageProperties(response.getProps(), response.getEnvelope(), encoding);
                                Message msg = getMessageConverter().toMessage(response.getBody(), messageProps);
                                msgList.add(msg);

                                deliveryTag = response.getEnvelope().getDeliveryTag();
                                cLogger.debug("Received msg from {} deliveryTag: {}", queueName, deliveryTag);

                                if (!isIsGreedyConsume() && msgList.size() < prefetchCount && response.getMessageCount() == 0) {
                                    cLogger.debug("Waiting for message for {} ms", waitTimeout);
                                    Thread.sleep(waitTimeout);
                                }
                            }
                        }
                    }

                    if (deliveryTag != null) {
                        cLogger.info("ConsumeReceived size: {}", msgList.size());
                        try {
                            msgHandler.processMsg(msgList);

                            if (isChannelLocallyTransacted(channel)) {
                                cLogger.info("BasicAck deliveryTag: {}", deliveryTag);
                                channel.basicAck(deliveryTag, msgList.size() > 1);
                                channel.txCommit();
                            } else if (isChannelTransacted()) {// Not locally transacted but it is transacted so it could be synchronized with an external transaction
                                for (Message msg : msgList) {
                                    ConnectionFactoryUtils.registerDeliveryTag(getConnectionFactory(), channel, msg.getMessageProperties().getDeliveryTag());
                                }
                            } else {
                                cLogger.debug("UnExpected transaction type");
                            }
                            cLogger.info("ConsumeSuccess size: {}", msgList.size());

                        } catch (Exception ex) {
                            cLogger.error("Exception while processing msg: ", ex);

                            if (isChannelLocallyTransacted(channel)) {
                                cLogger.info("BasicNack deliveryTag: {}", deliveryTag);
                                channel.basicNack(deliveryTag, msgList.size() > 1, true);
                                channel.txCommit();
                            } else if (isChannelTransacted()) {// Not locally transacted but it is transacted so it could be synchronized with an external transaction
                                ConnectionFactoryUtils.registerDeliveryTag(getConnectionFactory(), channel, deliveryTag);
                            } else {
                                cLogger.debug("UnExpected transaction type");
                            }
                        }
                    }

                    return null;
                }
            });
        } catch (Exception ex) {
            cLogger.error("Exception: ", ex);
        }
    }
}
