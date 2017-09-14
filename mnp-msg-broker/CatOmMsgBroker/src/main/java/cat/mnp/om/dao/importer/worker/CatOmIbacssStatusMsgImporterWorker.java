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
public class CatOmIbacssStatusMsgImporterWorker extends Worker {

    private static final Logger logger = LoggerFactory.getLogger(CatOmIbacssStatusMsgImporterWorker.class);

    @Override
    public void execute(Connection connection) throws SQLException {
        Assert.notNull(mqHeaders, "mqHeaders must not be null");
        Assert.notNull(plSqlQuery, "plSqlQuery must not be null");

        try (CallableStatement callableStatement = connection.prepareCall(plSqlQuery)) {

            Long serviceId = (Long) mqHeaders.get(MnpRtcProvHeaders.ServiceId.name());
            String referenceOrderId = (String) mqHeaders.get(MnpRtcProvHeaders.ReferenceOrderId.name());
            String msisdn = (String) mqHeaders.get(MnpRtcProvHeaders.Msisdn.name());
            String batchStatus = (String) mqHeaders.get(MnpRtcProvHeaders.BatchStatus.name());
            String batchErrorMessage = (String) mqHeaders.get(MnpRtcProvHeaders.BatchErrorMessage.name());

            logger.trace("serviceId: {}, referenceOrderId: {}, msisdn: {}, batchStatus: {}, batchErrorMessage: {}", serviceId, referenceOrderId, msisdn, batchStatus, batchErrorMessage);
            
            callableStatement.setLong(":iServiceId", serviceId);
            callableStatement.setString(":iReferenceOrderId", referenceOrderId);
            callableStatement.setString(":iBatchStatus", batchStatus);
            callableStatement.setString(":iBatchErrorMessage", batchErrorMessage);

            callableStatement.execute();

        }
    }
}
