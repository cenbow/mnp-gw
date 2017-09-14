/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.clh.core.reloader;

import cat.mnp.clh.dao.PortReqMsgDao;
import cat.mnp.mq.core.MsgHandlerBase;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

/**
 *
 * @author HP-CAT
 */
public class PortReqMsgReloader extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(PortReqMsgReloader.class);
    private PortReqMsgDao mvnoMsgDao;
    private AmqpTemplate amqpTemplate;
    private MessageProperties msgProperties;

    @Override
    public PortReqMsgDao getMvnoMsgDao() {
        return mvnoMsgDao;
    }

    public void setMvnoMsgDao(PortReqMsgDao mvnoMsgDao) {
        this.mvnoMsgDao = mvnoMsgDao;
    }

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

    @Override
    public void processMsg(String msgString) throws Exception {
        List<String> msgList = getMvnoMsgDao().reloadMsg();

        for (String xml : msgList) {
            Message msg = new Message(xml.getBytes(), msgProperties);
            amqpTemplate.send(msg);
        }
    }
}
