/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.core.importer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessageProperties;

import com.google.common.base.Strings;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import com.telcordia.inpac.ws.jaxb.NPCMessageType;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.mq.core.MsgHandlerBase;
import cat.mnp.om.util.CatOmNpcMessageUtils;
import miw.util.StringUtils;

/**
 *
 * @author HP-CAT
 */
public class CatOmClhMsgImporter extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(CatOmClhMsgImporter.class);
    private AmqpTemplate amqpTemplate;
    private MessageProperties msgProperties;
    private String errorText;

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

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    @Override
    public void processMsg(String msgString) throws Exception {
        NPCMessageData npcMessageData = NpcMessageUtils.unMarshal(getJaxbUnMarshaller(), msgString);

        NPCDataType npcDataType = npcMessageData.getNPCData();

        MessageHeaderType messageHeader = npcDataType.getMessageHeader();
        NPCMessageType npcMessages = npcDataType.getNPCMessages();

        logger.info("Converting ClhMsg {} ", messageHeader);
        List msgList = CatOmNpcMessageUtils.listInboundMsg(messageHeader, npcMessages);

        if (msgList.size() > 0) {
            logger.info("Importing ClhMsg {} size: {} orders", messageHeader, msgList.size());

            for (Object msgObject : msgList) {
                String result = getMvnoMsgDao().importMsg(msgObject);
                if (Strings.nullToEmpty(result).startsWith(errorText)) {
                	logger.error("Error detected while importing {}: {}", messageHeader, result);
					amqpTemplate.convertAndSend(StringUtils.utf8truncate(String.format("%s, importResult: %s", messageHeader, result), 255), msgObject); // FIXME: limit max byte 255 for UTF-8 better way?
                }
            }
            logger.debug("Imported ClhMsg {} size: {} orders", messageHeader, msgList.size());
        } else {
            logger.error("Invalid ClhMsg Msg: {}", messageHeader);
        }
    }
}
