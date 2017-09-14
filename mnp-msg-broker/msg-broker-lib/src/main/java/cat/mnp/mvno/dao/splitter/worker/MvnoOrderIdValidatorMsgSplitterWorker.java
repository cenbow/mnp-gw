/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.dao.splitter.worker;

import cat.mnp.clh.util.NpcMessageUtils;
import cat.mnp.mvno.dao.worker.Worker;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import org.springframework.util.Assert;

/**
 *
 * @author anuchitr
 */
public class MvnoOrderIdValidatorMsgSplitterWorker extends Worker {

    @Override
    public void execute(Connection connection) throws SQLException {
        Assert.notNull(msgObject, "msgObject must not be null");
        Assert.notNull(plSqlQuery, "plSqlQuery must not be null");

        try (CallableStatement callableStatement = connection.prepareCall(plSqlQuery)) {
            String msgId = messageHeader.getMessageID().toString();
            List<HashMap<String, String>> dataList = NpcMessageUtils.extractOtherMsgData(messageHeader, msgObject);
            ArrayListMultimap<String, Object> executionResultList = ArrayListMultimap.create();
            setExecutionResultList(executionResultList);
            Multiset<String> executionCountList = HashMultiset.create();
            setExecutionCountList(executionCountList);

            for (HashMap<String, String> child : dataList) {
                callableStatement.setString("iMsgId", msgId);
                callableStatement.setString("iOrderId", child.get("orderId"));
                callableStatement.setString("iPortId", child.get("portId"));
                callableStatement.setString("iMvnoName", messageHeader.getSender());

                callableStatement.registerOutParameter("oStatus", Types.VARCHAR);

                callableStatement.execute();
                String status = callableStatement.getString("oStatus");

                executionResultList.put(status, msgObject);
                executionCountList.add(status, dataList.size());
                
                break;//this blocker blocks order level
            }
        }
    }
}
