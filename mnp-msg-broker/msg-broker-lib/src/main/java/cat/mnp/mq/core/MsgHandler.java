/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mq.core;

import java.io.File;
import java.util.List;
import org.springframework.amqp.core.Message;

/**
 *
 * @author HP-CAT
 */
public interface MsgHandler {

    public void processMsg(Message msg) throws Exception;

    public void processMsg(List<Message> msgList) throws Exception;
    
    public void processMsg(File file) throws Exception;
    
    public void processMsg(javax.jms.Message aqMsg) throws Exception;
}
