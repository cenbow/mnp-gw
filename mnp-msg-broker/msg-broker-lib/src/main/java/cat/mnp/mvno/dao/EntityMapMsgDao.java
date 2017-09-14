/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 *
 * @author HP-CAT
 */
public class EntityMapMsgDao {

    private static final Logger logger = LoggerFactory.getLogger(EntityMapMsgDao.class);
    private SessionFactory sessionFactory;
    private String sqlQuery;
    private String parameterName;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public List<Map<String, Object>> queryMsg() throws Exception {
        return queryMsg(null, null);
    }

    public List<Map<String, Object>> queryMsg(List valueList) throws Exception {
        Assert.notNull(parameterName, "ParameterName must not be null");
        return queryMsg(parameterName, valueList);
    }

    public List<Map<String, Object>> queryMsg(String parameterName, List valueList) throws Exception {
        //maybe fixed here

        Query q = getQuery();
        ArrayList<Map<String, Object>> resultList = new ArrayList<>();

        int listSize = valueList.size();//3100
        int counter = listSize / 1000;//3

        if (counter > 0) {

            for (int i = 0; i < counter; i++) {

                if (parameterName != null) {
                    if(i == counter-1) {
                        q.setParameterList(parameterName, valueList.subList(i*1000, listSize-1));
                    }else{
                        q.setParameterList(parameterName, valueList.subList(i*1000, (i+1)*1000-1));
                    }
                    resultList.addAll(q.list());
                }
            }

        } else {

            if (parameterName != null) {
                q.setParameterList(parameterName, valueList);
                resultList.addAll(q.list());
            }
        }

//        List<Map<String, Object>> resultList = q.list();
        logger.info("Result size: {}", resultList.size());
        return resultList;
    }

    private Query getQuery() {
        Session session = getSessionFactory().getCurrentSession();

        Query q = session.getNamedQuery(sqlQuery);
        q.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

        return q;
    }
}
