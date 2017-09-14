/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.dao;

import cat.mnp.mvno.dao.worker.Worker;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.telcordia.inpac.ws.jaxb.MessageFooterType;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author HP-CAT
 */
public class MvnoMsgDao {

    private static final Logger logger = LoggerFactory.getLogger(MvnoMsgDao.class);
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

    public void processMsg(Map<String, Object> mqHeaders) throws Exception {
        worker.setMqHeaders(mqHeaders);
        Session session = sessionFactory.getCurrentSession();
        session.doWork(worker);
    }

    public void processMsg(Map<String, Object> mqHeaders, MessageHeaderType messageHeader, List msgList) throws Exception {
        worker.setMqHeaders(mqHeaders);
        processMsg(messageHeader, msgList);
    }

    public void processMsg(MessageHeaderType messageHeader, List msgList) throws Exception {
        worker.setMessageHeader(messageHeader);
        processMsg(msgList);
    }

    public void processMsg(List msgList) throws Exception {
        Session session = sessionFactory.getCurrentSession();

        for (Object msg : msgList) {
            worker.setMsgObject(msg);
            session.doWork(worker);
            logger.trace("Msg: {}", msg);
        }
    }

    public String queryMsg(MessageHeaderType messageHeader, Object msgObject) throws Exception {
        worker.setMessageHeader(messageHeader);

        ArrayList<Object> msgList = new ArrayList<>();
        msgList.add(msgObject);
        return queryMsg(msgList).get(0);
    }

    public List<String> queryMsg(MessageHeaderType messageHeader, List msgList) throws Exception {
        worker.setMessageHeader(messageHeader);
        return queryMsg(msgList);
    }

    public List<String> queryMsg(List msgList) throws Exception {
        Session session = getSessionFactory().getCurrentSession();

        ArrayList<String> resultList = new ArrayList<>();

        for (Object msg : msgList) {
            worker.setMsgObject(msg);
            session.doWork(worker);
            resultList.add(worker.getExecutionResult());

            logger.trace("Msg: {}, Result: {}", msg, worker.getExecutionResult());
        }

        return resultList;
    }

    public String blockMsg(MessageHeaderType messageHeader, List msgList, MessageFooterType messageFooter) throws Exception {
        worker.setMessageHeader(messageHeader);
        worker.setMessageFooter(messageFooter);
        return blockMsg(msgList);
    }

    public String blockMsg(List msgList) throws Exception {
        Session session = getSessionFactory().getCurrentSession();

        String status = "Error";
        for (Object msg : msgList) {
            worker.setMsgObject(msg);
            session.doWork(worker);
            status = worker.getExecutionResult();

            logger.trace("Msg: {}, Result: {}", msg, status);
            break;//only allow 1 order per soap
        }
        return status;
    }

    public HashMap<String, Object> splitMsg(MessageHeaderType messageHeader, List msgList) throws Exception {
        worker.setMessageHeader(messageHeader);
        return splitMsg(msgList);
    }

    public HashMap<String, Object> splitMsg(List msgList) throws Exception {
        Session session = getSessionFactory().getCurrentSession();

        ArrayListMultimap<String, Object> listMultimap = ArrayListMultimap.create();
        Multiset<String> countMultiset = HashMultiset.create();
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("data", listMultimap);
        dataMap.put("count", countMultiset);

        for (Object msg : msgList) {
            worker.setMsgObject(msg);
            session.doWork(worker);

            Multiset<String> executionCountMultiset = worker.getExecutionCountList();
            countMultiset.addAll(executionCountMultiset);

            ArrayListMultimap<String, Object> mvnoNameList = worker.getExecutionResultList();
            listMultimap.putAll(mvnoNameList);

            for (String mvnoName : mvnoNameList.keySet()) {
                logger.trace("Splitted MvnoName: {}, Count: {}", mvnoName, executionCountMultiset.count(mvnoName));
            }
        }

        return dataMap;
    }

    public String importMsg(Map<String, Object> mqHeaders, MessageHeaderType messageHeader, Object msgObject) throws Exception {
        worker.setMqHeaders(mqHeaders);
        return importMsg(messageHeader, msgObject);
    }

    public String importMsg(MessageHeaderType messageHeader, Object msgObject) throws Exception {
        worker.setMessageHeader(messageHeader);
        return importMsg(msgObject);
    }

    public String importMsg(Object msgObject) throws Exception {
        Session session = getSessionFactory().getCurrentSession();

        worker.setMsgObject(msgObject);
        session.doWork(worker);

        return worker.getExecutionResult();
    }
}
