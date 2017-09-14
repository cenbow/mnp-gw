/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.dao.impl;

import cat.mnp.om.dao.PortCompleteDao;
import cat.mnp.om.hibernate.MnpPortComplete;
import java.util.List;
import org.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author HP-CAT
 */
public class PortCompleteDaoImpl implements PortCompleteDao {

    private static Logger logger = LoggerFactory.getLogger(PortCompleteDao.class);
    private SessionFactory sessionFactory;
    private int saveBatchSize;
    private String dateFormat;
    private String dateTimeFormat;
    private String selectMvnoNameNamedQuery;

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }

    public void setDateTimeFormat(String dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
    }

    public int getSaveBatchSize() {
        return saveBatchSize;
    }

    public void setSaveBatchSize(int saveBatchSize) {
        this.saveBatchSize = saveBatchSize;
    }

    public String getSelectMvnoNameNamedQuery() {
        return selectMvnoNameNamedQuery;
    }

    public void setSelectMvnoNameNamedQuery(String selectMvnoNameNamedQuery) {
        this.selectMvnoNameNamedQuery = selectMvnoNameNamedQuery;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public MnpPortComplete create(String portId, String msisdn) {
        MnpPortComplete mnpPortComplete = new MnpPortComplete();
        mnpPortComplete.setPortId(portId);
        mnpPortComplete.setMsisdn(msisdn);
        return mnpPortComplete;
    }

    @Override
    public int save(MnpPortComplete newMnpPortComplete) throws Exception {
        Session currentSession = sessionFactory.getCurrentSession();

        MnpPortComplete oldMnpPortComplete;
        try {
            oldMnpPortComplete = (MnpPortComplete) currentSession.load(MnpPortComplete.class, newMnpPortComplete.getId(), LockOptions.UPGRADE);
        } catch (ObjectNotFoundException ex) {
            logger.trace("Saving: {}", newMnpPortComplete);
            currentSession.save(newMnpPortComplete);
            return 1;
        }

        oldMnpPortComplete.setOrderId(newMnpPortComplete.getOrderId());
        oldMnpPortComplete.setMsisdn(newMnpPortComplete.getMsisdn());
        oldMnpPortComplete.setDonor(newMnpPortComplete.getDonor());
        oldMnpPortComplete.setRecipient(newMnpPortComplete.getRecipient());
        oldMnpPortComplete.setRoute(newMnpPortComplete.getRoute());
        oldMnpPortComplete.setMvnoName(newMnpPortComplete.getMvnoName());
        oldMnpPortComplete.setCompletionTime(newMnpPortComplete.getCompletionTime());
        oldMnpPortComplete.setSubmissionTime(newMnpPortComplete.getSubmissionTime());

        logger.trace("Merging: {}", oldMnpPortComplete);
        currentSession.update(oldMnpPortComplete);
        return 1;
    }

    @Override
    public int saveBatch(List<MnpPortComplete> mnpPortCompleteList) throws Exception {
        int cnt = 0;
        int savedCount = 0;
        for (MnpPortComplete mnpPortComplete : mnpPortCompleteList) {
            savedCount += save(mnpPortComplete);
            cnt++;
            if (cnt == saveBatchSize) {
                logger.debug("Reached flush point: {}", cnt);
                Session currentSession = sessionFactory.getCurrentSession();
                currentSession.flush();
                currentSession.clear();
                cnt = 0;
            }
        }
        return savedCount;
    }

    @Override
    public void updateMvnoName(MnpPortComplete mnpPortComplete) throws Exception {
        Query q = sessionFactory.getCurrentSession().getNamedQuery(selectMvnoNameNamedQuery);
        q.setParameter("msisdn", mnpPortComplete.getMsisdn());
        q.setParameter("completionTime", mnpPortComplete.getCompletionTime());

        String mvnoName = (String) q.uniqueResult();
        mnpPortComplete.setMvnoName(mvnoName);
    }

    @Override
    public List<MnpPortComplete> listAll() throws Exception {
        Criteria q = sessionFactory.getCurrentSession().createCriteria(MnpPortComplete.class);

        List<MnpPortComplete> mnpPortCompleteList = q.list();
        logger.info("listAll size: {}", mnpPortCompleteList.size());

        return mnpPortCompleteList;
    }
}