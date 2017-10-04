/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mq.core;

import java.util.Enumeration;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author HP-CAT
 */
public class JmsOnlineMsgSender implements MessageListener {

	private static final Logger logger = LoggerFactory.getLogger(JmsOnlineMsgSender.class);
	private MsgHandler msgHandler;

	public void setMsgHandler(MsgHandler msgHandler) {
		this.msgHandler = msgHandler;
	}

	@Override
	public void onMessage(Message message) {
    	try {
			logger.warn("AQ: "+ message.getJMSDestination().toString() ); // TODO: miw debug config later
		} catch (JMSException e1) {
			logger.error(e1.toString(),e1);
		} 
		if (message instanceof TextMessage) {
			try {
				TextMessage txtMsg = ((TextMessage) message);
				for (Enumeration e = txtMsg.getPropertyNames(); e.hasMoreElements();) {
					String key = (String) e.nextElement();
					Object obj = txtMsg.getObjectProperty(key);
					if (obj != null) {
						logger.info(key + "=" + obj);
					}
				}

				msgHandler.processMsg(message); // direct, no mq 

			} catch (Exception ex) {
				//throw new RuntimeException(ex); // retry transac
				logger.error(ex.toString(),ex);
				
			}
		} else {
			throw new IllegalArgumentException("Message must be of type TextMessage");
		}
	}
}
