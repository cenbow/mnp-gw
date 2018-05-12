package cat.mnp.om.smd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import miw.sql.util.OracleArrayValue;
import miw.sql.util.OracleTypeUtil;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;

public class SmdDao extends JdbcDaoSupport {
	private static final Logger logger = LoggerFactory.getLogger(SmdDao.class);

	public void smartUpdatePkg(Map inMap) throws SQLException {
		logger.debug("inMap={} ", inMap);
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate()).withCatalogName("").withProcedureName("SMART_UPDATE_PACKAGE")
				.declareParameters(
						new SqlParameter("i_order_seq", OracleTypes.VARCHAR),
						new SqlParameter("i_srv_seq", OracleTypes.VARCHAR),
						new SqlParameter("i_package_id1", OracleTypes.NUMBER),
						new SqlParameter("i_package_id2", OracleTypes.NUMBER),
						new SqlParameter("i_package_start_dtm", OracleTypes.DATE),
						new SqlParameter("i_package_end_dtm", OracleTypes.DATE),

						new SqlOutParameter("o_callstatus", OracleTypes.VARCHAR),
						new SqlOutParameter("o_errmsg", OracleTypes.VARCHAR));

		Map<String, Object> callResult = call.execute(inMap);
		logger.debug("callResult=" + callResult);
	}

}