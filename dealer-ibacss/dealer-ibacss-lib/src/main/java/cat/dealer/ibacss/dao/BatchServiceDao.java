/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cat.dealer.ibacss.dao;

import cat.dealer.ibacss.domain.BatchService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author HP-CAT
 */
@Transactional(readOnly = true)
public class BatchServiceDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public BatchService get(Long serviceId) {
        Session session = sessionFactory.getCurrentSession();

        BatchService svc = (BatchService) session.get(BatchService.class, serviceId);
        return svc;
    }
    
}
