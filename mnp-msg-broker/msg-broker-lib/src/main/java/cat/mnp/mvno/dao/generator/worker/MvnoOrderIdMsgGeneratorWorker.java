/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.dao.generator.worker;

import cat.mnp.mvno.dao.order.generator.GenerateOrderIdDto;
import cat.mnp.mvno.dao.worker.Worker;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import org.springframework.util.Assert;

/**
 *
 * @author HP-CAT
 */
public class MvnoOrderIdMsgGeneratorWorker extends Worker {

    @Override
    public void execute(Connection connection) throws SQLException {
        Assert.notNull(msgObject, "msgObject must not be null");
        Assert.notNull(plSqlQuery, "plSqlQuery must not be null");
        
        GenerateOrderIdDto req = (GenerateOrderIdDto) msgObject;
        try (CallableStatement callableStatement = connection.prepareCall(plSqlQuery)) {

            callableStatement.setString("iOrderType", req.getOrderType().name());
            callableStatement.setString("iMvnoName", req.getMvnoName());

            callableStatement.registerOutParameter("oOrderId", Types.VARCHAR);

            callableStatement.execute();

            String orderId = callableStatement.getString("oOrderId");
            setExecutionResult(orderId);
        }
    }
}
