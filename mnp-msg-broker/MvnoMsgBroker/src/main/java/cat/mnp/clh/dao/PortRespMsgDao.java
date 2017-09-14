/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.clh.dao;

import cat.mnp.clh.domain.MnpPortResp;
import cat.mnp.mvno.dao.MvnoMsgDao;
import com.telcordia.inpac.ws.jaxb.MessageFooterType;
import com.telcordia.inpac.ws.jaxb.NumTypeWithFlag;
import com.telcordia.inpac.ws.jaxb.PortRespMsgType;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author HP-CAT
 */
public class PortRespMsgDao extends MvnoMsgDao {

    private static final Logger logger = LoggerFactory.getLogger(PortRespMsgDao.class);
    private String sqlQuery;

    public String getSqlQuery() {
        return sqlQuery;
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    public void joinMsg(List<PortRespMsgType> msgList, MessageFooterType messageFooter) throws Exception {
        Session session = getSessionFactory().getCurrentSession();

        Query q = session.getNamedQuery(sqlQuery);
        int count = 0;

        for (PortRespMsgType msg : msgList) {
            q.setString("orderId", msg.getOrderId());
            List<MnpPortResp> list = (List<MnpPortResp>) q.list();

            List<NumTypeWithFlag> numTypeWithFlagMultiRejList = new ArrayList<>();
            msg.setNumberWithFlag(numTypeWithFlagMultiRejList);

            for (MnpPortResp mnpPortResp : list) {
                logger.trace("{}", mnpPortResp);
                NumTypeWithFlag numTypeWithFlagMultiRej = new NumTypeWithFlag();
                numTypeWithFlagMultiRej.setMSISDN(mnpPortResp.getMsisdn());
                numTypeWithFlagMultiRej.setPortId(mnpPortResp.getPortId());
                numTypeWithFlagMultiRej.setNumberAccepted(new BigInteger(mnpPortResp.getNumAccepted()));
                numTypeWithFlagMultiRej.setRejectReasonCode(mnpPortResp.getRejectReasonCode());
                numTypeWithFlagMultiRej.setCorrectPinCode(mnpPortResp.getCorrectPinCode());
                numTypeWithFlagMultiRejList.add(numTypeWithFlagMultiRej);
                count ++;
            }
        }
        messageFooter.setChecksum(BigInteger.valueOf(count));
    }
}
