/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.dealer.ibacss.core.importer;

import cat.dealer.ibacss.dao.StatusMsgDao;
import cat.dealer.mq.core.MsgHandler;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;

/**
 *
 * @author HP-CAT
 */
public class IbacssStatusMsgImporter implements MsgHandler {

    private static final Logger logger = LoggerFactory.getLogger(IbacssStatusMsgImporter.class);
    StatusMsgDao statusMsgDao;

    public StatusMsgDao getStatusMsgDao() {
        return statusMsgDao;
    }

    public void setStatusMsgDao(StatusMsgDao statusMsgDao) {
        this.statusMsgDao = statusMsgDao;
    }

    @Override
    public void processMsg(Message msg) throws Exception {
        Map<String, Object> mqHeaders = msg.getMessageProperties().getHeaders();

        logger.info("Importing IbacssStatus Msg: {}", mqHeaders);
        statusMsgDao.processMsg(mqHeaders);
    }

}
