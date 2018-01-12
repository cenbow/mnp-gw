/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.clh.core.importer;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.mq.core.MsgHandlerBase;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import com.telcordia.inpac.ws.jaxb.NPCMessageType;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;

/**
 *
 * @author HP-CAT
 */
public class OtherMsgImporter extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(OtherMsgImporter.class);

    @Override
    public void processMsg(Message msg) throws Exception {
        Map<String, Object> mqHeaders = msg.getMessageProperties().getHeaders();
        String msgString = new String(msg.getBody());
        NPCMessageData npcMessageData = NpcMessageUtils.unMarshal(getJaxbUnMarshaller(), msgString);

        NPCDataType npcDataType = npcMessageData.getNPCData();

        MessageHeaderType messageHeader = npcDataType.getMessageHeader();
        NPCMessageType npcMessages = npcDataType.getNPCMessages();

        List msgList = NpcMessageUtils.listOutboundOtherMsg(npcMessages);

        if (messageHeader.getMessageID().equals(new BigInteger("4001"))) { // port sync has excpetional logic
        		logger.warn("Skip import for 4001: "+mqHeaders);
        }else if (msgList.size() > 0) {
            logger.info("Importing Other Msg size: {}", msgList.size());
            getMvnoMsgDao().processMsg(mqHeaders, messageHeader, msgList);
        } else {
            logger.error("Invalid Other Msg: {}", messageHeader);
        }
    }
}