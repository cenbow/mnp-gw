/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.dao.splitter.worker;

import cat.mnp.mvno.dao.worker.Worker;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.telcordia.inpac.ws.jaxb.TimerNotifMsgType;
import com.telcordia.inpac.ws.jaxb.NumTypeBase;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.util.Assert;

/**
 *
 * @author HP-CAT
 */
public class TimerNotifMsgSplitterWorker extends Worker {

    @Override
    public void execute(Connection connection) throws SQLException {
        Assert.notNull(msgObject, "msgObject must not be null");
        Assert.notNull(plSqlQuery, "plSqlQuery must not be null");

        TimerNotifMsgType msg = (TimerNotifMsgType) msgObject;

        try (CallableStatement callableStatement = connection.prepareCall(plSqlQuery)) {
            String msgId = messageHeader.getMessageID().toString();
            ArrayListMultimap<String, NumTypeBase> mvnoNameMultimap = ArrayListMultimap.create();
            ArrayListMultimap<String, Object> executionResultList = ArrayListMultimap.create();
            setExecutionResultList(executionResultList);
            Multiset<String> executionCountList = HashMultiset.create();
            setExecutionCountList(executionCountList);
            for (NumTypeBase child : msg.getNumberDataBase()) {//get mvno name
                callableStatement.setString("iMsgId", msgId);
                callableStatement.setString("iOrderId", msg.getOrderId());
                callableStatement.setString("iMsisdn", child.getMSISDN());
                callableStatement.setString("iPortId", child.getPortId());
                callableStatement.setString("iMsgCreateTimeStamp", messageHeader.getMessageCreateTimeStamp());

                callableStatement.registerOutParameter("oMvnoName", Types.VARCHAR);

                callableStatement.execute();

                mvnoNameMultimap.put(callableStatement.getString("oMvnoName"), child);
            }

            for (String mvnoName : mvnoNameMultimap.keySet()) {//build parent
                TimerNotifMsgType parent = new TimerNotifMsgType();
                parent.setMessageID(msg.getMessageID());
                parent.setOrderId(msg.getOrderId());
                parent.setProcessType(msg.getProcessType());
                parent.setTimerCode(msg.getTimerCode());
                ArrayList<NumTypeBase> list = new ArrayList<>();
                parent.setNumberDataBase(list);
                for (NumTypeBase child : mvnoNameMultimap.get(mvnoName)) {
                    list.add(child);
                }
                executionResultList.put(mvnoName, parent);
                executionCountList.add(mvnoName, list.size());
            }
        }
    }
}
