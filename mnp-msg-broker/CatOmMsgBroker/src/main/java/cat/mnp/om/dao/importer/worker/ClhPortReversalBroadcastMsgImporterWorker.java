/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.dao.importer.worker;

import cat.mnp.mvno.dao.worker.Worker;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import com.telcordia.inpac.ws.jaxb.PortBroadcastMsgType;

/**
 *
 * @author HP-CAT
 */
public class ClhPortReversalBroadcastMsgImporterWorker extends Worker {

    private static final Logger logger = LoggerFactory.getLogger(ClhPortReversalBroadcastMsgImporterWorker.class);

    @Override
    public void execute(Connection connection) throws SQLException {
        Assert.notNull(messageHeader, "messageHeader must not be null");
        Assert.notNull(mqHeaders, "mqHeaders must not be null");
        Assert.notNull(msgObject, "msgObject must not be null");
        Assert.notNull(plSqlQuery, "plSqlQuery must not be null");

        try (CallableStatement callableStatement = connection.prepareCall(plSqlQuery)) {
            PortBroadcastMsgType msg = (PortBroadcastMsgType) msgObject;

            callableStatement.setString("iMsgId", messageHeader.getMessageID().toString());
            callableStatement.setString("iMsgCreateTimeStamp", messageHeader.getMessageCreateTimeStamp());
            callableStatement.setString("iDonor", msg.getDonor());
            callableStatement.setString("iRecipient", msg.getRecipient());
            callableStatement.setString("iMsisdn", msg.getMSISDN());
            callableStatement.setString("iPortId", msg.getPortId());
            callableStatement.registerOutParameter("oReversalType", Types.VARCHAR);
            callableStatement.registerOutParameter("oErrmsg", Types.VARCHAR);

            callableStatement.execute();

            String reversalType = callableStatement.getString("oReversalType");
            mqHeaders.put("ReversalType", reversalType);
            logger.trace("Msisdn: {}, PortId: {}, ReversalType: {}", msg.getMSISDN(), msg.getPortId(), reversalType);
            setExecutionResult(callableStatement.getString("oErrmsg"));
        }
    }
}
