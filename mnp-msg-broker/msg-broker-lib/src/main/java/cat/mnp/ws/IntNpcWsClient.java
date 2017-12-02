/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.ws;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.mq.core.MsgHandlerBase;
import cat.mnp.ws.dao.NpcBatchIdGeneratorDao;
import com.telcordia.inpac.ws.NPCWebService;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import java.io.File;
import java.math.BigInteger;
import java.util.Date;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

/**
 *
 * @author HP-CAT
 */
public class IntNpcWsClient extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(IntNpcWsClient.class);
    private String userId;
    private String password;
    private NPCWebService npcWebService;
    private String sender;
    private String receiver;
    private NpcBatchIdGeneratorDao npcBatchIdGeneratorDao;
    private String successResponse;
    private AmqpTemplate errorAmqpTemplate;
    private MessageProperties msgProperties;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = Base64.encodeBase64String(password.getBytes());
    }

    public void setNpcWebService(NPCWebService npcWebService) {
        this.npcWebService = npcWebService;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setNpcBatchIdGeneratorDao(NpcBatchIdGeneratorDao npcBatchIdGeneratorDao) {
        this.npcBatchIdGeneratorDao = npcBatchIdGeneratorDao;
    }

    public void setSuccessResponse(String successResponse) {
        this.successResponse = successResponse;
    }

    public void setErrorAmqpTemplate(AmqpTemplate errorAmqpTemplate) {
        this.errorAmqpTemplate = errorAmqpTemplate;
    }

    public void setMsgProperties(MessageProperties msgProperties) {
        this.msgProperties = msgProperties;
    }

    private File getFile() {
        String dateString = DateFormatUtils.format(new Date(), getFileDateFormat());
        String filename = String.format(getFilenameFormat(), dateString, "Error", "");
        return new File(FilenameUtils.concat(getFilePath(), filename));
    }

    private File getFile(MessageHeaderType messageHeader) {
        String dateString = DateFormatUtils.format(new Date(), getFileDateFormat());
        String filename = String.format(getFilenameFormat(), dateString, messageHeader.getMessageID(), messageHeader.getSoapRequestId());
        return new File(FilenameUtils.concat(getFilePath(), filename));
    }

    private File getFile(MessageHeaderType messageHeader, String result) {
        String dateString = DateFormatUtils.format(new Date(), getFileDateFormat());
        String filename = String.format(getFilenameFormat(), dateString, messageHeader.getMessageID(), messageHeader.getSoapRequestId() + "_" + result);
        return new File(FilenameUtils.concat(getFilePath(), filename));
    }

    @Override
    public void processMsg(Message msg) throws Exception {
        Map<String, Object> mqHeaders = msg.getMessageProperties().getHeaders();
        String msgString = new String(msg.getBody());
        MessageHeaderType messageHeader;
        try {
            NPCMessageData npcMessageData = NpcMessageUtils.unMarshal(getJaxbUnMarshaller(), msgString);
            NPCDataType npcDataType = npcMessageData.getNPCData();
            messageHeader = npcDataType.getMessageHeader();

            String sender = npcDataType.getMessageHeader().getSender();
     		String receiver = npcDataType.getMessageHeader().getReceiver();
     		logger.debug("Extract MQ msg: sender=" + sender + ", receiver=" + receiver);

     		if("DUMMY".equalsIgnoreCase(sender)) { // OM
         		sender=  "CAT3G";
         		receiver= "CAT3G";
     		}

     		logger.info("New sender: {}, receiver: {}", sender, receiver);
            if (sender != null && receiver != null) {
                messageHeader.setSender(sender);
                messageHeader.setReceiver(receiver);

                if (npcBatchIdGeneratorDao != null) {
                    npcBatchIdGeneratorDao.generateBatchId(messageHeader);
                }

                logger.debug("Marshaling msg for new sender: {}, receiver: {}", sender, receiver);
                msgString = NpcMessageUtils.marshal(getJaxbMarshaller(), npcMessageData);
            }
        } catch (Exception ex) {
            File file = getFile();
            FileUtils.writeStringToFile(file, msgString, getFileEncoding());
            moveFileToDirectory(file, getErrorPath());
            throw new AmqpRejectAndDontRequeueException("Error while unmarshaling msg", ex);
        }

        logger.debug("Calling ws, {}", messageHeader);
        String response = npcWebService.processNPCMsg(userId, password, msgString);

        String result = String.format("Ws Response: %s for %s", response, messageHeader);
        logger.info(result);

        if (response.equalsIgnoreCase(successResponse)) {
            if (getBackupPath() != null) {
                logger.debug("Writing xml log for {}", messageHeader);
                File file = getFile(messageHeader);
                FileUtils.writeStringToFile(file, msgString, getFileEncoding());
                moveFileToDirectory(file, getBackupPath());
            }
        } else {
            if (response.startsWith("NPC1001E") || response.startsWith("NPC1008E")) {//retry
                throw new Exception(result);
            } else {
                if (errorAmqpTemplate != null && response.startsWith("NPC1036E") && errorAmqpTemplate != null) {
                    logger.error("Duplicate msg detected: {}, result: {}", messageHeader, result);
                    if (!msg.getMessageProperties().isRedelivered()) {//error on first call
                        msgProperties.getHeaders().clear();
                        msgProperties.getHeaders().putAll(mqHeaders);
                        msgProperties.setHeader("Status", result);
                        Message splittedMsg = new Message(msgString.getBytes(), msgProperties);
                        errorAmqpTemplate.send(messageHeader.getMessageID().toString(), splittedMsg);
                    }
                }
                File file = getFile(messageHeader, result);
                FileUtils.writeStringToFile(file, msgString, getFileEncoding());
                moveFileToDirectory(file, getErrorPath());
                throw new AmqpRejectAndDontRequeueException(result);
            }
        }
    }
}
