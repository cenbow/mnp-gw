/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.ws.dao;

import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author HP-CAT
 */
public class NpcBatchIdGeneratorDao {

    private static final Logger logger = LoggerFactory.getLogger(NpcBatchIdGeneratorDao.class);
    private SessionFactory sessionFactory;
    private List<BigInteger> msgIdList;
    private String soapReqIdSqlQuery;
    private String soapReqIdSqlParam;
    private String msgCreateTimeStampDateFormat;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setMsgIdList(List<BigInteger> msgIdList) {
        this.msgIdList = msgIdList;
    }

    public void setSoapReqIdSqlQuery(String soapReqIdSqlQuery) {
        this.soapReqIdSqlQuery = soapReqIdSqlQuery;
    }

    public void setSoapReqIdSqlParam(String soapReqIdSqlParam) {
        this.soapReqIdSqlParam = soapReqIdSqlParam;
    }

    public void setMsgCreateTimeStampDateFormat(String msgCreateTimeStampDateFormat) {
        this.msgCreateTimeStampDateFormat = msgCreateTimeStampDateFormat;
    }

    public void generateBatchId(MessageHeaderType msgHeader) throws Exception {
        BigInteger msgId = msgHeader.getMessageID();
        if (msgIdList == null || !msgIdList.contains(msgId)) {
            return;
        }
        
        String msgCreateTimestamp = DateFormatUtils.format(new Date(), msgCreateTimeStampDateFormat);
        String soapReqId = (String) sessionFactory.getCurrentSession().createSQLQuery(soapReqIdSqlQuery).addScalar(soapReqIdSqlParam, StandardBasicTypes.STRING).uniqueResult();

        logger.debug("New soapReqId: {}, msgCreateTimestamp: {}", soapReqId, msgCreateTimestamp);
        msgHeader.setMessageCreateTimeStamp(msgCreateTimestamp);
        msgHeader.setSoapRequestId(soapReqId);
    }
}