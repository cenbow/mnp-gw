/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.dao.importer.worker;

import cat.mnp.mvno.dao.worker.Worker;
import com.telcordia.inpac.ws.jaxb.PortNotExceptMsgType;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 *
 * @author HP-CAT
 */
public class PortNotificationExceptionMsgImporterWorker extends Worker {
    private static final Logger logger = LoggerFactory.getLogger(PortNotificationExceptionMsgImporterWorker.class);

    @Override
    public void execute(Connection connection) throws SQLException {
        Assert.notNull(mqHeaders, "mqHeaders must not be null");
        Assert.notNull(messageHeader, "messageHeader must not be null");
        Assert.notNull(msgObject, "msgObject must not be null");
        Assert.notNull(plSqlQuery, "plSqlQuery must not be null");

        PortNotExceptMsgType msg = (PortNotExceptMsgType) msgObject;

        try (CallableStatement callableStatement = connection.prepareCall(plSqlQuery)) {
            callableStatement.setString("iOrderId", msg.getOrderId());
            callableStatement.setString("iMsgId", messageHeader.getMessageID().toString());
            callableStatement.setString("iMsgCreateTimeStamp", messageHeader.getMessageCreateTimeStamp());
            callableStatement.setString("iMvnoName", (String) mqHeaders.get("MvnoName"));
            callableStatement.setString("iMsisdn", msg.getMSISDN());
            callableStatement.setString("iPortId", msg.getPortId());
            callableStatement.setString("iPortingDate", msg.getPortingDate());
            callableStatement.setString("iNumApprovedFlag", null);
            callableStatement.setString("iRejectReasonCode", null);

            logger.trace("OrderId: {}, Msisdn: {}, PortId: {}", msg.getOrderId(), msg.getMSISDN(), msg.getPortId());
            callableStatement.execute();
        }
    }
}
