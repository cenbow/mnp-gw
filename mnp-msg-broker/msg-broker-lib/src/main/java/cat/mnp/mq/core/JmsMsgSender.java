/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mq.core;

import com.google.common.base.Strings;
import java.util.Enumeration;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessageProperties;

/**
 *
 * @author HP-CAT
 */
public class JmsMsgSender implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(JmsMsgSender.class);
    private AmqpTemplate amqpTemplate;
    private MessageProperties msgProperties;

    public AmqpTemplate getAmqpTemplate() {
        return amqpTemplate;
    }

    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public MessageProperties getMsgProperties() {
        return msgProperties;
    }

    public void setMsgProperties(MessageProperties msgProperties) {
        this.msgProperties = msgProperties;
    }

    @Override
    public void onMessage(Message message) {    	
    	try {
			logger.info("[AQ] "+ message.getJMSDestination().toString() ); // TODO: miw debug config later
		} catch (JMSException e1) {
			logger.error(e1.toString(),e1);
		} 
    	
        if (message instanceof TextMessage) {
            try {
                msgProperties.getHeaders().clear();
                TextMessage txtMsg = ((TextMessage) message);
                for (Enumeration e = txtMsg.getPropertyNames(); e.hasMoreElements();) {
                    String key = (String) e.nextElement();
                    Object obj = txtMsg.getObjectProperty(key);
                    if (obj != null) {
                        msgProperties.setHeader(key, txtMsg.getObjectProperty(key));
                    }
                }

                org.springframework.amqp.core.Message msg = new org.springframework.amqp.core.Message(Strings.nullToEmpty(txtMsg.getText()).getBytes(), msgProperties);

                logger.debug("MqMessage received: {}", msgProperties.getHeaders());
                amqpTemplate.send(msg);
                logger.debug("MqMessage sent: {}", msgProperties.getHeaders());
                
            } catch (JMSException | AmqpException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            throw new IllegalArgumentException("Message must be of type TextMessage");
        }
    }
}
