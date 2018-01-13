package miw.util;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.jms.JMSException;
import javax.jms.Message;

public class AQMsgUtil {
	public static Map getHeaderMap(Message txtMsg) throws JMSException {

		Map<String, Object> propMap = new LinkedHashMap<>();
		for (Enumeration e = txtMsg.getPropertyNames(); e.hasMoreElements();) {
			String key = (String) e.nextElement();
			Object obj = txtMsg.getObjectProperty(key);
			if (obj != null) { // && !StringUtils.startsWith(key, "JMS")
				propMap.put(key, obj);
			}
		}
		Map collect = propMap.entrySet().stream().sorted((e1, e2) -> {
			if (e1.getKey().startsWith("JMS") && !e2.getKey().startsWith("JMS"))
				return 1;
			else if (!e1.getKey().startsWith("JMS") && e2.getKey().startsWith("JMS"))
				return -1;
			return 0;
		}).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		return collect;
	}
}
