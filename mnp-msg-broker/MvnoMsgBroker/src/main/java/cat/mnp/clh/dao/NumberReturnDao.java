package cat.mnp.clh.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class NumberReturnDao extends JdbcDaoSupport {
	private static final Logger logger = LoggerFactory.getLogger(NumberReturnDao.class);
//	public void insert(String msisdn) {
//		logger.debug("insert mnp_number_return_fail: "+ msisdn);
//		String sql = "insert into mnpdb.mnp_number_return_fail ( id, msisdn, createtime) " + "values ( mnpdb.mnp_number_return_fail_seq.nextval, ? , systimestamp )";
//		getJdbcTemplate().update(sql, new Object[]{msisdn});
//
//	}
}