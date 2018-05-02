package cat.mnp.portsync.stp;

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

public class PortSyncStpDao extends JdbcDaoSupport {
	private static final Logger logger = LoggerFactory.getLogger(PortSyncStpDao.class);

	// FIXME: รอ  sp พี่เก่ง Array of String Delimter (,)
	public List<String> portSyncStpSP(String orderId) throws SQLException {
		List<String> r = new ArrayList<>();
		logger.debug("orderId={} ", orderId);
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate()).withCatalogName("cat_mnp_port_sync").withProcedureName("stp_generate")
				.declareParameters(
						new SqlParameter("I_MSG_ID", OracleTypes.NUMBER),
						new SqlOutParameter("o_output", OracleTypes.ARRAY, "VCHAR_ARRAY_STP"),
						new SqlOutParameter("o_callstatus", OracleTypes.VARCHAR),
						new SqlOutParameter("o_errmsg", OracleTypes.VARCHAR));
		Map<String, Object> inMap = new LinkedHashMap<String, Object>();
		inMap.put("I_MSG_ID", orderId);
		Map<String, Object> callResult = call.execute(inMap);
		logger.debug("callResult=" + callResult);
		r = OracleTypeUtil.toStringList((ARRAY) callResult.get("o_output"));
		logger.info("sp return size=" + r.size() + ", r= " + r);
		return r;
	}

}