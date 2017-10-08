/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.ws.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author HP-CAT
 */
public class NpcWsDao {

	private static final Logger logger = LoggerFactory.getLogger(NpcWsDao.class);
	private SessionFactory sessionFactory;

	public String checkOrderType(String orderId) throws Exception {
		String sql = "select cat_mnp_inf_gw.check_order_type ( '021708291131368' ) orderType from dual ";
		String type = (String) sessionFactory.getCurrentSession().createSQLQuery(sql).addScalar("orderType", StandardBasicTypes.STRING).uniqueResult();
		return type;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}