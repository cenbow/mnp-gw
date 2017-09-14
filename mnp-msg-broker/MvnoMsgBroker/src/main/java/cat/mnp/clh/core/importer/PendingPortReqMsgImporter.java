/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.clh.core.importer;

import cat.mnp.clh.dao.PortReqMsgDao;
import cat.mnp.mq.core.MsgHandlerBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

/**
 *
 * @author HP-CAT
 */
public class PendingPortReqMsgImporter extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(PendingPortReqMsgImporter.class);
    private PortReqMsgDao mvnoMsgDao;

    @Override
    public PortReqMsgDao getMvnoMsgDao() {
        return mvnoMsgDao;
    }

    public void setMvnoMsgDao(PortReqMsgDao mvnoMsgDao) {
        this.mvnoMsgDao = mvnoMsgDao;
    }
    
    @Override
    public void processMsg(Message msg) throws Exception {
        MessageProperties messageProperties = msg.getMessageProperties();
        String orderId = (String) messageProperties.getHeaders().get("OrderId");
        String msgString = new String(msg.getBody());
        
        logger.debug("Importing PendingPortReqMsg orderId: {}, size: {}", orderId, msgString.length());
        getMvnoMsgDao().importPendingPortReqMsg(orderId, msgString);
    }
}
