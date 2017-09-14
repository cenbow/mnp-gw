/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.dao.importer.worker;

import cat.mnp.mvno.dao.worker.Worker;
import com.telcordia.inpac.ws.jaxb.NumTypeWithFlagWithPortDate;
import com.telcordia.inpac.ws.jaxb.PortNotMsgType;
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
public class PortNotificationMsgImporterWorker extends Worker {

    private static final Logger logger = LoggerFactory.getLogger(PortNotificationMsgImporterWorker.class);

    @Override
    public void execute(Connection connection) throws SQLException {
        Assert.notNull(mqHeaders, "mqHeaders must not be null");
        Assert.notNull(messageHeader, "messageHeader must not be null");
        Assert.notNull(msgObject, "msgObject must not be null");
        Assert.notNull(plSqlQuery, "plSqlQuery must not be null");

        PortNotMsgType msg = (PortNotMsgType) msgObject;

        try (CallableStatement callableStatement = connection.prepareCall(plSqlQuery)) {
            callableStatement.setString("iOrderId", msg.getOrderId());
            callableStatement.setString("iMsgId", messageHeader.getMessageID().toString());
            callableStatement.setString("iMsgCreateTimeStamp", messageHeader.getMessageCreateTimeStamp());
            callableStatement.setString("iMvnoName", (String) mqHeaders.get("MvnoName"));

            for (NumTypeWithFlagWithPortDate ref : msg.getNumberWithFlagWithPortDate()) {
                callableStatement.setString("iMsisdn", ref.getMSISDN());
                callableStatement.setString("iPortId", ref.getPortId());
                callableStatement.setString("iPortingDate", ref.getPortingDate());
                callableStatement.setString("iNumApprovedFlag", ref.getNumApprovedFlag());
                callableStatement.setString("iRejectReasonCode", ref.getRejectReasonCode());

                logger.trace("OrderId: {}, Msisdn: {}, PortId: {}", msg.getOrderId(), ref.getMSISDN(), ref.getPortId());
                callableStatement.execute();
            }
        }
    }
}
