/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.clh.dao.splitter.worker;

import cat.mnp.mvno.dao.worker.Worker;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.telcordia.inpac.ws.jaxb.NumTypeWithFlag;
import com.telcordia.inpac.ws.jaxb.PortRespMsgType;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import org.springframework.util.Assert;

/**
 *
 * @author HP-CAT
 */
public class PortRespMsgSplitterWorker extends Worker {

    @Override
    public void execute(Connection connection) throws SQLException {
        Assert.notNull(msgObject, "msgObject must not be null");
        Assert.notNull(plSqlQuery, "plSqlQuery must not be null");

        PortRespMsgType msg = (PortRespMsgType) msgObject;
        try (CallableStatement callableStatement = connection.prepareCall(plSqlQuery)) {

            ArrayListMultimap<String, Object> executionResultList = ArrayListMultimap.create();
            setExecutionResultList(executionResultList);
            Multiset<String> executionCountList = HashMultiset.create();
            setExecutionCountList(executionCountList);

            String status = "Error";
            int count = 0;
            callableStatement.setString("iMsgId", messageHeader.getMessageID().toString());
            callableStatement.setString("iOrderId", msg.getOrderId());
            callableStatement.setString("iMsgCreateTimeStamp", messageHeader.getMessageCreateTimeStamp());
            for (NumTypeWithFlag numTypeWithFlagMultiRej : msg.getNumberWithFlag()) {
                callableStatement.setString("iMsisdn", numTypeWithFlagMultiRej.getMSISDN());
                callableStatement.setString("iPortId", numTypeWithFlagMultiRej.getPortId());
                callableStatement.setString("iNumAccepted", numTypeWithFlagMultiRej.getNumberAccepted().toString());
                callableStatement.setString("iRejectReasonCode", numTypeWithFlagMultiRej.getRejectReasonCode());
                callableStatement.setString("iCorrectPinCode", numTypeWithFlagMultiRej.getCorrectPinCode());

                callableStatement.registerOutParameter("oStatus", Types.VARCHAR);
                callableStatement.execute();

                status = callableStatement.getString("oStatus");
                count ++;
                //do the loop to handle multi msisdn per order and insert data into db
            }
            executionResultList.put(status, msgObject);
            executionCountList.add(status, count);
        }
    }
}
