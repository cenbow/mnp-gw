/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.smpp;

import cat.mnp.mq.core.MsgHandlerBase;
import cat.mnp.om.core.importer.ClhPortDeactMsgImporter;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.integration.support.MessageBuilder;

/**
 *
 * @author HP-CAT
 */
public class SmsSender extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(ClhPortDeactMsgImporter.class);
    private Map<String, ?> headers;
    private MessageChannel outputChannel;
    private String errorText;

    public Map<String, ?> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, ?> headers) {
        this.headers = headers;
    }

    public MessageChannel getOutputChannel() {
        return outputChannel;
    }

    public void setOutputChannel(MessageChannel outputChannel) {
        this.outputChannel = outputChannel;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    @Override
    public void processMsg(String msgString) throws Exception {
        try {
            logger.info("Sending {}", msgString);
            Message<String> message = MessageBuilder.withPayload(msgString).copyHeaders(headers).build();

            boolean isSent = outputChannel.send(message);
            logger.debug("Message Sent status {}", isSent);
        } catch(Exception ex) {
            logger.debug("{}", ex);
        }
    }
}