/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cat.dealer.ibacss.dao;

import cat.dealer.ibacss.domain.BatchServiceTerminate;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author HP-CAT
 */
@Transactional(readOnly = true)
public class TerminateServiceDao {

    private SessionFactory sessionFactory;
    private String batchType;
    private String lastUpdateBy;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public String getBatchType() {
        return batchType;
    }

    public void setBatchType(String batchType) {
        this.batchType = batchType;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public BatchServiceTerminate get(Long serviceTerminateId) {
        Session session = sessionFactory.getCurrentSession();

        BatchServiceTerminate svc = (BatchServiceTerminate) session.get(BatchServiceTerminate.class, serviceTerminateId);
        return svc;
    }
    
    @Transactional(readOnly = false)
    public void update(BatchServiceTerminate svc) {
        Session session = sessionFactory.getCurrentSession();

        svc.setLastUpdateDate(new Date());
        svc.setLastUpdateBy(lastUpdateBy);
        session.update(svc);
    }
}
