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
import java.util.HashSet;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 *
 * @author HP-CAT
 */
public class PortRespStatusMsgImporterWorker extends Worker {

    private static final Logger logger = LoggerFactory.getLogger(PortRespStatusMsgImporterWorker.class);

    @Override
    public void execute(Connection connection) throws SQLException {
        Assert.notNull(msgObject, "msgObject must not be null");
        Assert.notNull(plSqlQuery, "plSqlQuery must not be null");

        try (CallableStatement callableStatement = connection.prepareCall(plSqlQuery)) {
            List<HashMap<String, String>> dataList = NpcMessageUtils.extractOtherMsgData(messageHeader, msgObject);

            HashSet<String> orderIdSet = new HashSet<>();
            for (HashMap<String, String> data : dataList) {
                orderIdSet.add(data.get("orderId"));
                logger.trace("{}", data);
            }
            callableStatement.setString("iMsgId", messageHeader.getMessageID().toString());
            for (String orderId : orderIdSet) {
                callableStatement.setString("iOrderId", orderId);
                callableStatement.execute();
            }
        }
    }
}
