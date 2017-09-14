/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.clh.dao.blocker.worker;

import cat.mnp.mvno.dao.worker.Worker;
import com.telcordia.inpac.ws.jaxb.NumTypeWithPinNoPortId;
import com.telcordia.inpac.ws.jaxb.PortReqMsgType;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import org.springframework.util.Assert;

/**
 *
 * @author HP-CAT
 */
public class PortReqMsgBlockerWorker extends Worker {

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

        PortReqMsgType portReqMsg = (PortReqMsgType) msgObject;
        try (CallableStatement callableStatement = connection.prepareCall(plSqlQuery)) {

            callableStatement.setString("iOrderId", portReqMsg.getOrderId());
            callableStatement.setString("iOrderDate", portReqMsg.getOrderDate());
            for (NumTypeWithPinNoPortId numTypeWithOutId : portReqMsg.getNumberWithPinNoPortId()) {//just get the first msisdn in order to check donor
                callableStatement.setString("iMsisdn", numTypeWithOutId.getMSISDN());
                break;
            }
            callableStatement.setShort("iPriority", getPriority());
            callableStatement.setInt("iChecksum", messageFooter.getChecksum().intValue());

            callableStatement.registerOutParameter("oStatus", Types.VARCHAR);

            callableStatement.execute();

            String status = callableStatement.getString("oStatus");
            setExecutionResult(status);
        }
    }
}
