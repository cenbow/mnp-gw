/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.dao.portterminate.worker;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ArrayListMultimap;

import cat.mnp.mvno.dao.worker.Worker;

/**
 *
 * @author HP-CAT
 */
public class PortTerminateMsgWorker extends Worker {
	private static final Logger logger = LoggerFactory.getLogger(PortTerminateMsgWorker.class);
	@Override
	public void execute(Connection connection) throws SQLException {

		// so something !!!
		Map m = (Map) msgObject;
		logger.trace(plSqlQuery + m.toString());
		try (CallableStatement callableStatement = connection.prepareCall(plSqlQuery)) {

			callableStatement.setString("i_order_id", (String) m.get("i_order_id"));
			callableStatement.setObject("i_status", m.get("i_status"));

			callableStatement.registerOutParameter("o_callstatus", Types.VARCHAR);
			callableStatement.registerOutParameter("o_errmsg", Types.VARCHAR);

			callableStatement.execute();

			String status = callableStatement.getString("o_callstatus");
			String err = callableStatement.getString("o_errmsg");
			if (StringUtils.contains(err, "Exception")) {
				logger.error(err);
			}
			setExecutionResult(status);
		}
	}
}
