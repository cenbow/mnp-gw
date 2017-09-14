/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mq.core;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;

/**
 *
 * @author HP-CAT
 */
public class MsgScheduler extends MsgHandlerBase {

    private AmqpTemplate amqpTemplate;
    private Message mqMessage;

    public AmqpTemplate getAmqpTemplate() {
        return amqpTemplate;
    }

    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public Message getMqMessage() {
        return mqMessage;
    }

    public void setMqMessage(Message mqMessage) {
        this.mqMessage = mqMessage;
    }

    public void scheduleMsg() throws Exception {
        amqpTemplate.send(mqMessage);
    }
}
