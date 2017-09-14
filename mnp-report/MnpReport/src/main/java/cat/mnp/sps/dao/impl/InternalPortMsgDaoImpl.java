/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.sps.dao.impl;

import cat.mnp.om.hibernate.MnpPortComplete;
import cat.mnp.sps.dao.InternalPortMsgDao;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author HP-CAT
 */
public class InternalPortMsgDaoImpl implements InternalPortMsgDao {

    private static Logger logger = LoggerFactory.getLogger(InternalPortMsgDao.class);
    private SessionFactory sessionFactory;
    private String dateFormat;
    private String selectNamedQuery;

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getSelectNamedQuery() {
        return selectNamedQuery;
    }

    public void setSelectNamedQuery(String selectNamedQuery) {
        this.selectNamedQuery = selectNamedQuery;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public List<MnpPortComplete> listInternalPort(Date startDate, Date endDate) throws Exception {
        logger.info("Querying listInternalPort: from {} to {}", DateFormatUtils.format(startDate, dateFormat), DateFormatUtils.format(endDate, dateFormat));
        
        Session currentSession = sessionFactory.getCurrentSession();

        Query q = currentSession.getNamedQuery(selectNamedQuery);
        q.setParameter("startDate", startDate);
        q.setParameter("endDate", endDate);

        List<MnpPortComplete> list = q.list();

        logger.info("get internal port size: {}", list.size());

        return list;
    }

    @Override
    public List<MnpPortComplete> listByDate(Date startDate) throws Exception {
        Date endDate = DateUtils.addDays(startDate, 1);

        return listInternalPort(startDate, endDate);
    }

    @Override
    public List<MnpPortComplete> listByMonth(Date startDate) throws Exception {
        startDate = DateUtils.truncate(startDate, Calendar.MONTH);
        Date endDate = DateUtils.addMonths(startDate, 1);

        return listInternalPort(startDate, endDate);
    }
}