/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.clh.dao.importer.worker;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.mvno.dao.worker.Worker;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 *
 * @author HP-CAT
 */
public class OtherMsgImporterWorker extends Worker {

    private static final Logger logger = LoggerFactory.getLogger(OtherMsgImporterWorker.class);

    @Override
    public void execute(Connection connection) throws SQLException {
        Assert.notNull(msgObject, "msgObject must not be null");
        Assert.notNull(plSqlQuery, "plSqlQuery must not be null");

        try (CallableStatement callableStatement = connection.prepareCall(plSqlQuery)) {
            List<HashMap<String, String>> dataList = NpcMessageUtils.extractOtherMsgData(messageHeader, msgObject);

            callableStatement.setString("iMsgId", messageHeader.getMessageID().toString());
            callableStatement.setString("iMsgCreateTimeStamp", messageHeader.getMessageCreateTimeStamp());
            callableStatement.setString("iMvnoName", (String) mqHeaders.get("MvnoName"));
            for (HashMap<String, String> data : dataList) {
                callableStatement.setString("iOrderId", data.get("orderId"));
                callableStatement.setString("iMsisdn", data.get("msisdn"));
                callableStatement.setString("iPortId", data.get("portId"));

                logger.trace("{}", data);
                callableStatement.execute();
            }
        }
    }
}
