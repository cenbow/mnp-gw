/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.clh.dao.impl;

import cat.mnp.clh.dao.BroadcastMsgDao;
import cat.mnp.clh.hibernate.NpChInMsgTrace;
import cat.mnp.om.hibernate.MnpPortComplete;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author HP-CAT
 */
public class BroadcastMsgDaoImpl implements BroadcastMsgDao {

    private static Logger logger = LoggerFactory.getLogger(BroadcastMsgDao.class);
    private SessionFactory sessionFactory;
    private List<Integer> messageTypeIdList;
    private String dateFormat;
    private String selectPortIdNamedQuery;

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public List<Integer> getMessageTypeIdList() {
        return messageTypeIdList;
    }

    public void setMessageTypeIdList(List<Integer> messageTypeIdList) {
        this.messageTypeIdList = messageTypeIdList;
    }

    public String getSelectPortIdNamedQuery() {
        return selectPortIdNamedQuery;
    }

    public void setSelectPortIdNamedQuery(String selectPortIdNamedQuery) {
        this.selectPortIdNamedQuery = selectPortIdNamedQuery;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public MnpPortComplete get(String portId, String msisdn) throws Exception {
        Query q = sessionFactory.getCurrentSession().getNamedQuery(selectPortIdNamedQuery);
        q.setParameter("portId", portId);
        q.setParameter("msisdn", msisdn);

        MnpPortComplete mnpPortComplete = (MnpPortComplete) q.uniqueResult();

        return mnpPortComplete;
    }

    @Override
    public List listMsg(Date startDate, Date endDate) throws Exception {
        logger.info("Querying getMsgByDate: from {} to {}", DateFormatUtils.format(startDate, dateFormat), DateFormatUtils.format(endDate, dateFormat));
        Criteria query = sessionFactory.getCurrentSession().createCriteria(NpChInMsgTrace.class).add(Restrictions.in("messageTypeId", messageTypeIdList)).add(Restrictions.ge("messageTimestamp", startDate)).add(Restrictions.lt("messageTimestamp", endDate)).addOrder(Order.asc("messageTimestamp"));
        List list = query.list();
        logger.debug("listMsg size: {}", list.size());

        return list;
    }

    @Override
    public List listMsgByDate(Date startDate) throws Exception {
        Date endDate = DateUtils.addDays(startDate, 1);

        return listMsg(startDate, endDate);
    }

    @Override
    public List listMsgByMonth(Date startDate) throws Exception {
        startDate = DateUtils.truncate(startDate, Calendar.MONTH);
        Date endDate = DateUtils.addMonths(startDate, 1);

        return listMsg(startDate, endDate);
    }
}
