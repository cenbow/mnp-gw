/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.dao.importer.worker;

import cat.dealer.ibacss.domain.MnpRtcProvHeaders;
import cat.mnp.mvno.dao.worker.Worker;
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

        CallableStatement callableStatement = connection.prepareCall(plSqlQuery);
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
        callableStatement.close();
    }
}
