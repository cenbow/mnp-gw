/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;

import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;

import cat.mnp.clh.util.NpcMessageUtils;

public class IntNpcWsClient extends NpcWsClient {

	private static final Logger logger = LoggerFactory.getLogger(IntNpcWsClient.class);

	@Override
	public void processMsg(Message msg) throws Exception {
		String msgString = new String(msg.getBody());
		NPCMessageData npcMessageData = NpcMessageUtils.unMarshal(getJaxbUnMarshaller(), msgString); // TODO: if notice unMarshal() is expensive, dont extends NpcWsClient
		NPCDataType npcDataType = npcMessageData.getNPCData();
		String sender = npcDataType.getMessageHeader().getSender();
		String receiver = npcDataType.getMessageHeader().getReceiver();

		logger.info("Extract MQ msg to set : sender=" + sender + ", receiver=" + receiver);
		setSender(sender);
		setSender(receiver);
		super.processMsg(msg);
	}
}
