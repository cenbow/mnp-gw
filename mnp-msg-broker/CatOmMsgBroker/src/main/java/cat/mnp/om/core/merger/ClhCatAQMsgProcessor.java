/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.core.merger;

import java.util.Enumeration;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cat.mnp.mq.core.MsgHandler;

/**
 *
 * @author HP-CAT
 */
public class ClhCatAQMsgProcessor implements MessageListener {

	private static final Logger logger = LoggerFactory.getLogger(ClhCatAQMsgProcessor.class);
	private Map<String, MsgHandler> msgHandlerMap;

	@Override
	public void onMessage(Message message) {
		try {
			logger.info("[AQ] " + message.getJMSDestination().toString()); // TODO: miw debug config later
		} catch (JMSException e1) {
			logger.error(e1.toString(), e1);
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
						propStr += ", ";
					}
				}
				logger.info(propStr);
				String msgId = (String) txtMsg.getObjectProperty("MsgId");
				MsgHandler msgHandler = msgHandlerMap.get(msgId);
				logger.debug( msgHandler.getClass().getName());
				msgHandler.processMsg(message); // direct, no mq

			} catch (Exception ex) {
				// throw new RuntimeException(ex); // retry transac
				logger.error(ex.toString(), ex);

			}
		} else {
			throw new IllegalArgumentException("Message must be of type TextMessage");
		}
	}

	public void setMsgHandlerMap(Map<String, MsgHandler> msgHandlerMap) {
		this.msgHandlerMap = msgHandlerMap;
	}

}
