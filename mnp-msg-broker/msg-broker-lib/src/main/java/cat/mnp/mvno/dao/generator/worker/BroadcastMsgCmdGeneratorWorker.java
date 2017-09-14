/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.dao.generator.worker;

import cat.mnp.mvno.dao.worker.Worker;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import org.springframework.util.Assert;
import com.telcordia.inpac.ws.jaxb.PortBroadcastMsgType;

/**
 *
 * @author HP-CAT
 */
public class BroadcastMsgCmdGeneratorWorker extends Worker {

    @Override
    public void execute(Connection connection) throws SQLException {
        Assert.notNull(msgObject, "msgObject must not be null");
        Assert.notNull(plSqlQuery, "plSqlQuery must not be null");

        PortBroadcastMsgType msg = (PortBroadcastMsgType) msgObject;

        try (CallableStatement callableStatement = connection.prepareCall(plSqlQuery)) {
            callableStatement.setString("iMsgId", messageHeader.getMessageID().toString());
            callableStatement.setString("iDonor", msg.getDonor());
            callableStatement.setString("iRecipient", msg.getRecipient());
            callableStatement.setString("iMsisdn", msg.getMSISDN());
            callableStatement.setString("iPortId", msg.getPortId());
            callableStatement.setString("iRoute", msg.getRoute());
            callableStatement.setString("iMsgCreateTimeStamp", messageHeader.getMessageCreateTimeStamp());

            callableStatement.registerOutParameter("oCmd", Types.VARCHAR);

            callableStatement.execute();
            setExecutionResult(callableStatement.getString("oCmd"));
        }
    }
}
