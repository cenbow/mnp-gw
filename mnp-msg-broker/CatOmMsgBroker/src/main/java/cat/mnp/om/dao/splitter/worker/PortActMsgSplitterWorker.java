/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.dao.splitter.worker;

import cat.mnp.mvno.dao.worker.Worker;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.telcordia.inpac.ws.jaxb.PortDeactMsgType;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import org.springframework.util.Assert;

/**
 *
 * @author HP-CAT
 */
public class PortActMsgSplitterWorker extends Worker {

    @Override
    public void execute(Connection connection) throws SQLException {
        Assert.notNull(msgObject, "msgObject must not be null");
        Assert.notNull(plSqlQuery, "plSqlQuery must not be null");

        PortDeactMsgType msg = (PortDeactMsgType) msgObject;
        try (CallableStatement callableStatement = connection.prepareCall(plSqlQuery)) {

            ArrayListMultimap<String, Object> executionResultList = ArrayListMultimap.create();
            setExecutionResultList(executionResultList);
            Multiset<String> executionCountList = HashMultiset.create();
            setExecutionCountList(executionCountList);

            callableStatement.setString("iMsgId", messageHeader.getMessageID().toString());
            callableStatement.setString("iMsgCreateTimeStamp", messageHeader.getMessageCreateTimeStamp());
            callableStatement.setString("iOrderId", msg.getOrderId());

            callableStatement.setString("iMsisdn", msg.getMSISDN());
            callableStatement.setString("iPortId", msg.getPortId());

            callableStatement.registerOutParameter("oStatus", Types.VARCHAR);
            callableStatement.execute();

            String status = callableStatement.getString("oStatus");
            
            executionResultList.put(status, msgObject);
            executionCountList.add(status, 1);
        }
    }
}
