/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.core.merger;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.mq.core.MsgHandlerBase;
import cat.mnp.om.dao.CatOmBaseMsgDao;
import cat.mnp.om.domain.CatOmBaseMsg;
import cat.mnp.om.domain.CatOmOrder;
import cat.mnp.om.domain.CatOmService;
import com.telcordia.inpac.ws.jaxb.NPCMessageData;
import java.util.Iterator;
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
public class ClhPortDeactCatOmMsgMerger extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(ClhPortDeactCatOmMsgMerger.class);
    private CatOmBaseMsgDao mvnoMsgDao;
    private AmqpTemplate amqpTemplate;
    private AmqpTemplate errorAmqpTemplate;
    private MessageProperties msgProperties;

    @Override
    public CatOmBaseMsgDao getMvnoMsgDao() {
        return mvnoMsgDao;
    }

    public void setMvnoMsgDao(CatOmBaseMsgDao mvnoMsgDao) {
        this.mvnoMsgDao = mvnoMsgDao;
    }

    public AmqpTemplate getAmqpTemplate() {
        return amqpTemplate;
    }

    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public AmqpTemplate getErrorAmqpTemplate() {
        return errorAmqpTemplate;
    }

    public void setErrorAmqpTemplate(AmqpTemplate errorAmqpTemplate) {
        this.errorAmqpTemplate = errorAmqpTemplate;
    }

    public MessageProperties getMsgProperties() {
        return msgProperties;
    }

    public void setMsgProperties(MessageProperties msgProperties) {
        this.msgProperties = msgProperties;
    }

    @Override
    public void processMsg(Message mqMsg) throws Exception {
        String msgId = mqMsg.getMessageProperties().getReceivedRoutingKey();

        List<CatOmBaseMsg> msgList = mvnoMsgDao.mergeMsg();
        logger.info("Merging msg {}", msgId);

        int numberCnt = 0;
        for (CatOmBaseMsg omMsg : msgList) {
            for (Iterator it = omMsg.getMsgList().iterator(); it.hasNext();) {
                CatOmOrder order = (CatOmOrder) it.next();
                for (CatOmService service : order.getServiceList()) {
                    NPCMessageData npcMessageData = new NPCMessageData();
                    omMsg.setReqTransId(service.getClhAccpFlag());//workaround for soapId
//                    npcMessageData.setNPCData(NpcCatOmMessageUtils.listPortDeact(msgId, omMsg, order, service));
                    numberCnt++;

                    String msgXml;
                    try {
                        logger.debug("Marshaling msg {} for msisdn: {}", msgId, service.getMsisdn());
                        msgXml = NpcMessageUtils.marshal(getJaxbMarshaller(), npcMessageData);
                    } catch (Exception ex) {
                        String errorString = String.format("Error while merging msg %s for msisdn: %s", msgId, service.getMsisdn());
                        logger.error(errorString, ex);
                        Message msg = new Message(String.format("%s, %s",errorString, ex.getMessage()).getBytes(), msgProperties);
                        errorAmqpTemplate.send(msgId, msg);
                        continue;
                    }

                    msgProperties.setHeader("Recipient", order.getRecipient());
                    msgProperties.setHeader("IsPrepaid", service.getIsPrepaid());
                    Message msg = new Message(msgXml.getBytes(), msgProperties);

                    amqpTemplate.send(msgId, msg);
                    logger.info("Msg {} sent for msisdn: {}", msgId, service.getMsisdn());
                }
            }
        }
        if (numberCnt == 0) {
            logger.info("No msg {} to merge", msgId);
        }
    }
}
