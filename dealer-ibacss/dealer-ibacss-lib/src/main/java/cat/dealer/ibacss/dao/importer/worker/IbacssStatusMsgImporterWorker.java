/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.dealer.ibacss.dao.importer.worker;

import cat.dealer.ibacss.dao.worker.Worker;
import cat.dealer.ibacss.domain.MnpRtcProvHeaders;
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
public class IbacssStatusMsgImporterWorker extends Worker {

    private static final Logger logger = LoggerFactory.getLogger(IbacssStatusMsgImporterWorker.class);

    @Override
    public void execute(Connection connection) throws SQLException {
        Assert.notNull(mqHeaders, "mqHeaders must not be null");
        Assert.notNull(plSqlQuery, "plSqlQuery must not be null");

        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(plSqlQuery);
            Long serviceId = (Long) mqHeaders.get(MnpRtcProvHeaders.ServiceId.name());
            String rtcTransId = (String) mqHeaders.get(MnpRtcProvHeaders.RtcTransId.name());
            String rtcTransMsg = (String) mqHeaders.get(MnpRtcProvHeaders.RtcTransMsg.name());
            String rtcStatus = (String) mqHeaders.get(MnpRtcProvHeaders.RtcStatus.name());

            callableStatement.setLong(":iServiceId", serviceId);
            callableStatement.setString(":iRtcTransId", rtcTransId);
            callableStatement.setString(":iRtcTransMsg", rtcTransMsg);
            callableStatement.setString(":iRtcStatus", rtcStatus);

            logger.trace("Status header: {}", mqHeaders);
            callableStatement.execute();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            if (callableStatement != null && !callableStatement.isClosed()) {
                callableStatement.close();
            }
        }
    }
}
