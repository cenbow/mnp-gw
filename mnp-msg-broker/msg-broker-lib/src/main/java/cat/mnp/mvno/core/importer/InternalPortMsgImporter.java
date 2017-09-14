/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.core.importer;

import cat.mnp.mq.core.MsgHandlerBase;
import java.util.ArrayList;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.util.Assert;

/**
 *
 * @author HP-CAT
 */
public class InternalPortMsgImporter extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(InternalPortMsgImporter.class);
    private MessageConverter messageConverter;

    public MessageConverter getMessageConverter() {
        return messageConverter;
    }

    public void setMessageConverter(MessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    @Override
    public void processMsg(Message msg) throws Exception {
        Assert.notNull(messageConverter, "messageConverter could not be null");

        logger.debug("Converting internal port msg");
        ArrayList<HashMap<String, String>> msgList = (ArrayList) messageConverter.fromMessage(msg);
        
        logger.info("Internal port size: {}", msgList.size());
        processMsg(msgList);
    }

    public void processMsg(ArrayList<HashMap<String, String>> msgList) throws Exception {
        getMvnoMsgDao().processMsg(msgList);
    }
}
