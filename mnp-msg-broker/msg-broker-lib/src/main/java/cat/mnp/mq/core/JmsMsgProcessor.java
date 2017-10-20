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
 * Direct handler process, not a forwarder to exhange like JMSSender, best for online, internal task
 */
public class JmsMsgProcessor implements MessageListener {

	private static final Logger logger = LoggerFactory.getLogger(JmsMsgProcessor.class);
	private MsgHandler msgHandler;

	public void setMsgHandler(MsgHandler msgHandler) {
		this.msgHandler = msgHandler;
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
				TextMessage txtMsg = ((TextMessage) message);
				String propStr = "";
				for (Enumeration e = txtMsg.getPropertyNames(); e.hasMoreElements(); ) {
					String key = (String) e.nextElement();
					Object obj = txtMsg.getObjectProperty(key);
					if (obj != null) {
						propStr += key + "=" + obj;
						propStr += ",  ";
					}
				}
				logger.info(propStr);

				msgHandler.processMsg(message); // direct handler process, not a forwarder

			} catch (Exception ex) {
				//throw new RuntimeException(ex); // retry transac
				logger.error(ex.toString(),ex);

			}
		} else {
			throw new IllegalArgumentException("Message must be of type TextMessage");
		}
	}
}
