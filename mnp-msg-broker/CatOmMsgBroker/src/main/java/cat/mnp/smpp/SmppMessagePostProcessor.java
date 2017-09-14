/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.smpp;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.integration.smpp.core.SmppConstants;

/**
 *
 * @author HP-CAT
 */
public class SmppMessagePostProcessor implements MessagePostProcessor {

    private String sourceMsisdn;
    private String smsMessage;

    public String getSourceMsisdn() {
        return sourceMsisdn;
    }

    public void setSourceMsisdn(String sourceMsisdn) {
        this.sourceMsisdn = sourceMsisdn;
    }

    public String getSmsMessage() {
        return smsMessage;
    }

    public void setSmsMessage(String smsMessage) {
        this.smsMessage = smsMessage;
    }

    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        message.getMessageProperties().setHeader(SmppConstants.SRC_ADDR, sourceMsisdn);
        message.getMessageProperties().setHeader(SmppConstants.SMS_MSG, smsMessage);
        return message;
    }
}