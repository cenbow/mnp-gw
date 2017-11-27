package hello;
import java.util.Enumeration;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AQReceiver implements MessageListener {

	private static final Logger logger = LoggerFactory.getLogger(AQReceiver.class);

	@Override
	public void onMessage(Message message) {
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
			System.out.println("[AQ]: "+message.getJMSDestination()+", Prop="+propStr);

		} catch (Exception e) {
			logger.error("Error receiving queue message!", e);
		}
	}
}