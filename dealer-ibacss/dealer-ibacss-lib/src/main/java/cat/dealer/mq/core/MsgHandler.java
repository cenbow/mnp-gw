/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.dealer.mq.core;

import org.springframework.amqp.core.Message;

/**
 *
 * @author HP-CAT
 */
public interface MsgHandler {

    public void processMsg(Message msg) throws Exception;
}
