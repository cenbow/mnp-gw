/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.clh.core.importer;

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
public class PriorityOrderIdMsgImporter extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(PriorityOrderIdMsgImporter.class);
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

        logger.debug("Converting ChangePortReqPriority msg");
        ArrayList<String> msgList = (ArrayList) messageConverter.fromMessage(msg);
        
        logger.info("ChangePortReqPriority size: {}", msgList.size());
        processMsg(msgList);
    }

    public void processMsg(ArrayList<String> msgList) throws Exception {
        getMvnoMsgDao().processMsg(msgList);
    }
}
