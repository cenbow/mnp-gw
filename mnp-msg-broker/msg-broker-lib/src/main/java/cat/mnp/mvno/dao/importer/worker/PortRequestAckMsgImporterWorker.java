/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.dao.importer.worker;

import cat.mnp.mvno.dao.worker.Worker;
import com.telcordia.inpac.ws.jaxb.NumTypeWithPinWithCLHFlag;
import com.telcordia.inpac.ws.jaxb.PortReqAckMsgType;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import org.springframework.util.Assert;

/**
 *
 * @author HP-CAT
 */
public class PortRequestAckMsgImporterWorker extends Worker {

    @Override
    public void execute(Connection connection) throws SQLException {
        Assert.notNull(mqHeaders, "mqHeaders must not be null");
        Assert.notNull(messageHeader, "messageHeader must not be null");
        Assert.notNull(msgObject, "msgObject must not be null");
        Assert.notNull(plSqlQuery, "plSqlQuery must not be null");

        PortReqAckMsgType msg = (PortReqAckMsgType) msgObject;

        try (CallableStatement callableStatement = connection.prepareCall(plSqlQuery)) {
            callableStatement.setString("iOrderId", msg.getOrderId());
            callableStatement.setString("iOrderDate", msg.getOrderDate());
            callableStatement.setString("iDonor", msg.getDonor());
            callableStatement.setString("iRecipient", msg.getRecipient());
            callableStatement.setString("iMsgCreateTimeStamp", messageHeader.getMessageCreateTimeStamp());
            callableStatement.setString("iMvnoName", (String) mqHeaders.get("MvnoName"));

            for (NumTypeWithPinWithCLHFlag ref : msg.getNumberWithPinWithCLHFlag()) {
                callableStatement.setString("iMsisdn", ref.getMSISDN());
                callableStatement.setString("iPinCode", ref.getPinCode());
                callableStatement.setString("iPortId", ref.getPortId());
                callableStatement.setString("iIsAccepted", ref.getCLHAccepted().toString());
                callableStatement.setString("iRejectCode", ref.getCLHRejectCode());

                callableStatement.execute();
            }
        }
    }
}
