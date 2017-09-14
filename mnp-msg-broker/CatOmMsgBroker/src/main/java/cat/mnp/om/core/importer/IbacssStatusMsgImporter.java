/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.core.importer;

import cat.mnp.mq.core.MsgHandlerBase;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;

/**
 *
 * @author HP-CAT
 */
public class IbacssStatusMsgImporter extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(IbacssStatusMsgImporter.class);

    @Override
    public void processMsg(Message msg) throws Exception {
        Map<String, Object> mqHeaders = msg.getMessageProperties().getHeaders();

        logger.info("Importing IbacssStatus Msg: {}", mqHeaders);
        getMvnoMsgDao().processMsg(mqHeaders);
    }

}
