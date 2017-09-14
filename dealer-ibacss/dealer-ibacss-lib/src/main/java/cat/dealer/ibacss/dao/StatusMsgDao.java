/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.dealer.ibacss.dao;

import cat.dealer.ibacss.dao.worker.Worker;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author HP-CAT
 */
@Transactional(readOnly = true)
public class StatusMsgDao {

    private SessionFactory sessionFactory;
    private Worker worker;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    @Transactional(readOnly = false)
    public void processMsg(Map<String, Object> mqHeaders) throws Exception {
        worker.setMqHeaders(mqHeaders);
        Session session = sessionFactory.getCurrentSession();
        session.doWork(worker);
    }
}
