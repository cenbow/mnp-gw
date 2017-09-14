/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mq.core;

import cat.mnp.clh.util.NpcMessageUtils;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import java.io.File;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;

/**
 *
 * @author HP-CAT
 */
public class MsgSender extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(MsgSender.class);
    private AmqpTemplate amqpTemplate;

    public AmqpTemplate getAmqpTemplate() {
        return amqpTemplate;
    }

    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    private File getFile(MessageHeaderType messageHeader) {
        String dateString = DateFormatUtils.format(new Date(), getFileDateFormat());
        String filename = String.format(getFilenameFormat(), dateString, messageHeader.getMessageID(), messageHeader.getSoapRequestId());
        return new File(FilenameUtils.concat(getFilePath(), filename));
    }

    @Override
    public void processMsg(Message msg) throws Exception {
        MessageHeaderType messageHeader = null;
        File file = null;
        if (getFilePath() != null) {
            String msgString = new String(msg.getBody());
            
            NPCMessageData npcMessageData = NpcMessageUtils.unMarshal(getJaxbUnMarshaller(), msgString);
            messageHeader = npcMessageData.getNPCData().getMessageHeader();

            logger.debug("Writing xml log");
            file = getFile(messageHeader);
            FileUtils.writeStringToFile(file, msgString, getFileEncoding());
        }

        try {
            amqpTemplate.send(msg.getMessageProperties().getReceivedRoutingKey(), msg);
            if (file != null) {
                moveFileToDirectory(file, getBackupPath());
            }
        } catch (Exception ex) {
            if (file != null) {
                logger.error("Error detected while sending msg: " + messageHeader, ex);
                moveFileToDirectory(file, getErrorPath());
            } else {
                logger.error("Error detected while sending msg " + msg.getMessageProperties().getReceivedRoutingKey(), ex);
            }
        }
    }
}
