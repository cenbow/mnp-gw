package miw.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import miw.sql.util.OracleArrayValue;
import oracle.jdbc.OracleTypes;

public class DBHelper {

	public static Map<String, Object> callStore(JdbcTemplate jdbcTemplate, String storeName, Map<String, Object[]> m) throws Exception {
		String[] ss = storeName.split("\\.");
		String packageName = "";
		String name = storeName;
		if (ss.length == 2) {
			packageName = ss[0];
			name = ss[1];
		}

		Map<String, Object> inMap = new LinkedHashMap<String, Object>();
		List<SqlParameter> sqlParameterList = new ArrayList<>();
		for (String key : m.keySet()) {
			Object[] v = m.get(key);
			SqlParameter p = null;
			int oracleType = 0;
			if (v[0] instanceof String) {
				oracleType = OracleTypes.VARCHAR;
			} else if (v[0] instanceof Number) {
				oracleType = OracleTypes.NUMBER;
			} else if (v[0] instanceof OracleArrayValue) {
				oracleType = OracleTypes.ARRAY;
			}

			if (key.startsWith("i_")) {
				p = (oracleType == OracleTypes.ARRAY) ? new SqlParameter(key, oracleType) : new SqlParameter(key, oracleType, (String) v[1]);
			} else if (key.startsWith("o_")) {
				p = (oracleType == OracleTypes.ARRAY) ? new SqlOutParameter(key, oracleType) : new SqlOutParameter(key, oracleType, (String) v[0]);
			} else {
				throw new Exception("Helper Not support");
			}
			sqlParameterList.add(p);
			inMap.put(key, v[0]);
		}

		SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withCatalogName(packageName).withProcedureName(name)
				.declareParameters(sqlParameterList.toArray(new SqlParameter[0]));

		Map<String, Object> callResult = call.execute(inMap);

		return callResult;
	}

	public static void main(String[] args) {

		// System.out.println("aaabbb".split("\\.")[1]);
		Integer a = 1;
		System.out.println(a instanceof Number);
	}
}
