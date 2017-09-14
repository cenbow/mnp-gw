/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.dao.importer.worker;

import cat.mnp.mvno.dao.worker.Worker;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 *
 * @author HP-CAT
 */
public class InternalPortMsgImporterWorker extends Worker {

    private static final Logger logger = LoggerFactory.getLogger(InternalPortMsgImporterWorker.class);

    @Override
    public void execute(Connection connection) throws SQLException {
        Assert.notNull(msgObject, "msgObject must not be null");
        Assert.notNull(plSqlQuery, "plSqlQuery must not be null");

        HashMap<String, String> msg = (HashMap<String, String>) msgObject;

        try (CallableStatement callableStatement = connection.prepareCall(plSqlQuery)) {
            callableStatement.setString("iType", msg.get("type"));
            callableStatement.setString("iMsisdn", msg.get("msisdn"));
            callableStatement.setString("iRecipient", msg.get("recipient"));
            callableStatement.setString("iRnidx", msg.get("rnidx"));
            callableStatement.setString("iDate", msg.get("date"));
            callableStatement.setString("iDesc",msg.get("desc"));
            callableStatement.registerOutParameter("oResult", Types.VARCHAR);

            callableStatement.execute();
            
            String oResult = callableStatement.getString("oResult");
            if (!oResult.equals(getSuccessResult())) {
                logger.error("Error while executing plSql params: {}, result: {}", msg, oResult);
            }
        }
    }
}