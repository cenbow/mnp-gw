/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.dao;

import cat.mnp.mvno.dao.MvnoMsgDao;
import com.telcordia.inpac.ws.jaxb.MessageFooterType;
import com.telcordia.inpac.ws.jaxb.PortDeactMsgType;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author HP-CAT
 */
public class PortActMsgDao extends MvnoMsgDao {

    private static final Logger logger = LoggerFactory.getLogger(PortActMsgDao.class);
    private String sqlQuery;

    public String getSqlQuery() {
        return sqlQuery;
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    public void joinMsg(List<PortDeactMsgType> msgList, MessageFooterType messageFooter) throws Exception {
        Session session = getSessionFactory().getCurrentSession();

        Query q = session.getNamedQuery(sqlQuery);
        int count = 0;

        PortDeactMsgType msg = msgList.get(0);

        q.setString("orderId", msg.getOrderId());
        List<PortDeactMsgType> list = (List<PortDeactMsgType>) q.list();

        msgList.clear();

        for (PortDeactMsgType msgObject : list) {
            msgObject.setProcessType(msg.getProcessType());
            logger.trace("{}", msgObject);
            msgList.add(msgObject);
            count++;
        }
        messageFooter.setChecksum(BigInteger.valueOf(count));
    }
}
