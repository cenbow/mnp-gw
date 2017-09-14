/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.sps.dao.impl;

import cat.mnp.sps.dao.InternalPortHistDao;
import cat.mnp.sps.hibernate.InternalPortHist;
import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author HP-CAT
 */
public class InternalPortHistDaoImpl implements InternalPortHistDao {

    private static Logger logger = LoggerFactory.getLogger(InternalPortHistDao.class);
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
    public void save(InternalPortHist internalPortHist) throws Exception {
        Session currentSession = sessionFactory.getCurrentSession();

        InternalPortHist oldInternalPortHist;
        try {
            oldInternalPortHist = (InternalPortHist) currentSession.load(InternalPortHist.class, internalPortHist.getPortId(), LockOptions.UPGRADE);
        } catch (ObjectNotFoundException ex) {
            logger.trace("Saving: {}", internalPortHist);
            currentSession.save(internalPortHist);
            return;
        }

        oldInternalPortHist.setMsisdn(internalPortHist.getMsisdn());
        oldInternalPortHist.setRecipient(internalPortHist.getRecipient());
        oldInternalPortHist.setRoute(internalPortHist.getRoute());
        oldInternalPortHist.setCompletionTime(internalPortHist.getCompletionTime());
        oldInternalPortHist.setSubmissionTime(internalPortHist.getSubmissionTime());

        logger.trace("Merging: {}", oldInternalPortHist);
        currentSession.update(oldInternalPortHist);

    }

    @Override
    public void saveBatch(List<InternalPortHist> internalPortHistList) throws Exception {
        int cnt = 0;
        for (InternalPortHist internalPortHist : internalPortHistList) {
            save(internalPortHist);
            cnt++;
            if (cnt == saveBatchSize) {
                logger.debug("Reached flush point: {}", cnt);
                Session currentSession = sessionFactory.getCurrentSession();
                currentSession.flush();
                currentSession.clear();
                cnt = 0;
            }
        }
    }
}