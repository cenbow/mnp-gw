/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.dao.importer.worker;

import cat.mnp.mvno.dao.worker.Worker;
import com.telcordia.inpac.ws.jaxb.PortDeactMsgType;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 *
 * @author HP-CAT
 */
public class ClhPortDeactMsgImporterWorker extends Worker {

    private static final Logger logger = LoggerFactory.getLogger(ClhPortDeactMsgImporterWorker.class);
    private String refIdKey;

    public String getRefIdKey() {
        return refIdKey;
    }

    public void setRefIdKey(String refIdKey) {
        this.refIdKey = refIdKey;
    }

    @Override
    public void execute(Connection connection) throws SQLException {
        Assert.notNull(mqHeaders, "mqHeaders must not be null");
        Assert.notNull(messageHeader, "messageHeader must not be null");
        Assert.notNull(msgObject, "msgObject must not be null");
        Assert.notNull(plSqlQuery, "plSqlQuery must not be null");

        try (CallableStatement callableStatement = connection.prepareCall(plSqlQuery)) {
            PortDeactMsgType msg = (PortDeactMsgType) msgObject;

            callableStatement.setString("iMsgId", messageHeader.getMessageID().toString());
            callableStatement.setString("iMsgCreateTimeStamp", messageHeader.getMessageCreateTimeStamp());

            callableStatement.setString("iOrderId", msg.getOrderId());
            callableStatement.setString("iMsisdn", msg.getMSISDN());
            callableStatement.setString("iPortId", msg.getPortId());
            callableStatement.registerOutParameter("oRefId", Types.NUMERIC);
            callableStatement.registerOutParameter("oIsPrepaid", Types.NUMERIC);
            callableStatement.registerOutParameter("oErrmsg", Types.VARCHAR);

            callableStatement.execute();

            Long refId = callableStatement.getLong("oRefId");
            Long isPrepaid = callableStatement.getLong("oIsPrepaid");
            String status = callableStatement.getString("oErrmsg");
            
            mqHeaders.put(refIdKey, refId);
            mqHeaders.put("IsPrepaid", isPrepaid);
            logger.trace("mqHeaders: {}", mqHeaders);

            setExecutionResult(status);
        }
    }
}
