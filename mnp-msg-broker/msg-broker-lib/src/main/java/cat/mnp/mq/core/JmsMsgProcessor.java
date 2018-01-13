/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mq.core;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import miw.util.AQMsgUtil;

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
			logger.info("[AQ] " + message.getJMSDestination().toString()); // TODO: miw debug config later
		} catch (JMSException e1) {
			logger.error(e1.toString(), e1);
		}
		if (message instanceof TextMessage) {
			try {
				logger.debug( AQMsgUtil.getHeaderMap(message).toString() );
				msgHandler.processMsg(message); // direct to process handler, not a forwarder

			} catch (Exception ex) {
				// throw new RuntimeException(ex); // retry transac
				logger.error("Test Ignore AQ Tx: " + ex.toString(), ex); // FIXME: JmsMsgProcessor remove on prod

			}
		} else {
			throw new IllegalArgumentException("Message must be of type TextMessage");
		}
	}
}
