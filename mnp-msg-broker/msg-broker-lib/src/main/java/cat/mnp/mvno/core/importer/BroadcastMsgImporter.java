/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.core.importer;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.mq.core.MsgHandlerBase;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import com.telcordia.inpac.ws.jaxb.NPCMessageType;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.telcordia.inpac.ws.jaxb.PortBroadcastMsgType;

/**
 *
 * @author HP-CAT
 */
public class BroadcastMsgImporter extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(BroadcastMsgImporter.class);
    
    @Override
    public void processMsg(String msgString) throws Exception {
        NPCMessageData npcMessageData = NpcMessageUtils.unMarshal(getJaxbUnMarshaller(), msgString);

        NPCDataType npcDataType = npcMessageData.getNPCData();

        MessageHeaderType messageHeader = npcDataType.getMessageHeader();
        NPCMessageType npcMessages = npcDataType.getNPCMessages();

        List<PortBroadcastMsgType> msgList = NpcMessageUtils.listBroadcastMsg(npcMessages);

        if (msgList.size() > 0) {
            logger.info("Importing BroadcastMsg Msg size: {}", msgList.size());
            getMvnoMsgDao().processMsg(messageHeader, msgList);
        } else {
            logger.error("Invalid BroadcastMsg Msg: {}", messageHeader);
        }
    }
}
