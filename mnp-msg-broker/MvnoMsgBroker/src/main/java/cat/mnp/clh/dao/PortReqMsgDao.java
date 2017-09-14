/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.clh.dao;

import cat.mnp.clh.dao.importer.worker.PendingPortReqMsgImporterWorker;
import cat.mnp.clh.domain.MnpPortReqXml;
import cat.mnp.mvno.dao.MvnoMsgDao;
import java.sql.Clob;
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
public class PortReqMsgDao extends MvnoMsgDao {

    private static final Logger logger = LoggerFactory.getLogger(PortReqMsgDao.class);
    private String sqlQuery;
    private String mvnoName;
    private PendingPortReqMsgImporterWorker worker;

    public String getSqlQuery() {
        return sqlQuery;
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    public String getMvnoName() {
        return mvnoName;
    }

    public void setMvnoName(String mvnoName) {
        this.mvnoName = mvnoName;
    }

    @Override
    public PendingPortReqMsgImporterWorker getWorker() {
        return worker;
    }

    public void setWorker(PendingPortReqMsgImporterWorker worker) {
        this.worker = worker;
    }

    public List<String> reloadMsg() throws Exception {
        Session session = getSessionFactory().getCurrentSession();

        Query q = session.getNamedQuery(sqlQuery);
        q.setString("mvnoName", mvnoName);
        logger.debug("Querying PortReqMsg for {}", mvnoName);
        List<MnpPortReqXml> list = (List<MnpPortReqXml>) q.list();
        List<String> xmlList = new ArrayList<>();
        for (MnpPortReqXml mnpPortReqXml : list) {
            logger.trace("OrderId: {}", mnpPortReqXml.getOrderId());
            Clob xmlClob = mnpPortReqXml.getXml();
            String xmlString = xmlClob.getSubString(1, (int) xmlClob.length());
            xmlList.add(xmlString);
        }
        if (!list.isEmpty()) {
            logger.info("Reloading PortReqMsg for {} size: {} orders", mvnoName, xmlList.size());
        } else {
            logger.info("No pending PortReqMsg found for {}", mvnoName);
        }
        return xmlList;
    }

    public void importPendingPortReqMsg(String orderId, String xml) throws Exception {
        Session session = getSessionFactory().getCurrentSession();

        worker.setOrderId(orderId);
        worker.setXml(xml);
        session.doWork(worker);
    }
}
