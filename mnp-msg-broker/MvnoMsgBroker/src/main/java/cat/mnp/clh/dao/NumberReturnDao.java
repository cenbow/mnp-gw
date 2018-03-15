package cat.mnp.clh.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import oracle.jdbc.OracleTypes;

public class NumberReturnDao extends JdbcDaoSupport {
	private static final Logger logger = LoggerFactory.getLogger(NumberReturnDao.class);

	public boolean isCat3gOrder(String orderId) {
		boolean r = false;

		logger.debug("orderId: " + orderId);
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate()).withCatalogName("number_return_pkg").withProcedureName("check_cat3g_order");
		Map<String, Object> inMap = new LinkedHashMap<String, Object>();
		inMap.put("i_orderid", orderId);
		Map<String, Object> callResult = call.execute(new MapSqlParameterSource(inMap));
		logger.debug("callResult=" + callResult);

		String o_result = (String) callResult.get("o_result");
		if ("0".equals(o_result)) {
			r = true;
		}
		return r;
	}
	// 3001 ?
	public List<String> verifyNumber(String orderId, String sender, List<String> msisdnList) {
		List<String> r = new ArrayList<>();

		logger.debug("orderId={}, sender={}, msisdnList={} ", orderId, sender, msisdnList);
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate()).withCatalogName("number_return_pkg").withProcedureName("verify_number")
				.declareParameters(
						new SqlParameter("pi_client_code", OracleTypes.NUMBER, "pi_client_code"),
						new SqlInOutParameter("po_system_users", OracleTypes.ARRAY, "T_SYSTEM_USER_TAB",
						new OracleSystemUser());
		Map<String, Object> inMap = new LinkedHashMap<String, Object>();
		inMap.put("i_orderid", orderId);
		inMap.put("i_sender", sender);
		inMap.put("i_msisdn_list", msisdnList);
		Map<String, Object> callResult = call.execute(new MapSqlParameterSource(inMap));
		logger.debug("callResult=" + callResult);

		r = (List<String>) callResult.get("o_msisdn_list");

		return r;
	}
	// 3002?
	public List<String> findInvalidNumber(String orderId, String sender, List<String> msisdnList) {
		List<String> r = new ArrayList<>();

		logger.debug("orderId={}, sender={}, msisdnList={} ", orderId, sender, msisdnList);
		SimpleJdbcCall call = new SimpleJdbcCall(getJdbcTemplate()).withCatalogName("number_return_pkg").withProcedureName("find_invalid_number");
		Map<String, Object> inMap = new LinkedHashMap<String, Object>();
		inMap.put("i_orderid", orderId);
		inMap.put("i_sender", sender);
		inMap.put("i_msisdn_list", msisdnList);
		Map<String, Object> callResult = call.execute(new MapSqlParameterSource(inMap));
		logger.debug("callResult=" + callResult);

		r = (List<String>) callResult.get("o_portid_list");

		return r;
	}

}