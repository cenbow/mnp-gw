/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.clh.dao.importer.worker;

import cat.mnp.mvno.dao.worker.Worker;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import org.springframework.util.Assert;

/**
 *
 * @author HP-CAT
 */
public class PriorityOrderIdMsgImporterWorker extends Worker {

    private short priority;

    public short getPriority() {
        return priority;
    }

    public void setPriority(short priority) {
        this.priority = priority;
    }
    
    @Override
    public void execute(Connection connection) throws SQLException {
        Assert.notNull(msgObject, "msgObject must not be null");
        Assert.notNull(plSqlQuery, "plSqlQuery must not be null");

        try (CallableStatement callableStatement = connection.prepareCall(plSqlQuery)) {
            String orderId = (String) msgObject;

            callableStatement.setString("iOrderId", orderId);
            callableStatement.setShort("iPriority", getPriority());
            callableStatement.execute();

        }
    }
}
