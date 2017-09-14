/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.dao.splitter.worker;

import cat.mnp.mvno.dao.worker.Worker;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.telcordia.inpac.ws.jaxb.NumTypeWithPin;
import com.telcordia.inpac.ws.jaxb.PortReqFwdMsgType;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import org.springframework.util.Assert;

/**
 *
 * @author HP-CAT
 */
public class PortRequestDonorSplitterWorker extends Worker {

    @Override
    public void execute(Connection connection) throws SQLException {
        Assert.notNull(msgObject, "msgObject must not be null");
        Assert.notNull(plSqlQuery, "plSqlQuery must not be null");

        PortReqFwdMsgType msg = (PortReqFwdMsgType) msgObject;

        try (CallableStatement callableStatement = connection.prepareCall(plSqlQuery)) {
            String msgId = messageHeader.getMessageID().toString();
            ArrayListMultimap<String, NumTypeWithPin> mvnoNameMultimap = ArrayListMultimap.create();
            ArrayListMultimap<String, Object> executionResultList = ArrayListMultimap.create();
            setExecutionResultList(executionResultList);
            Multiset<String> executionCountList = HashMultiset.create();
            setExecutionCountList(executionCountList);
            for (NumTypeWithPin child : msg.getNumberWithPin()) {//get mvno name
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
                PortReqFwdMsgType parent = new PortReqFwdMsgType();
                parent.setDonor(msg.getDonor());
                parent.setOperatorCode(msg.getOperatorCode());
                parent.setOrderDate(msg.getOrderDate());
                parent.setOrderId(msg.getOrderId());
                parent.setProcessType(msg.getProcessType());
                parent.setRecipient(msg.getRecipient());
                parent.setChannelId(msg.getChannelId());
                parent.setSubscriberData(msg.getSubscriberData());
                parent.setValidationDeadline(msg.getValidationDeadline());
                parent.setZone(msg.getZone());
                ArrayList<NumTypeWithPin> list = new ArrayList<>();
                parent.setNumberWithPin(list);
                for (NumTypeWithPin child : mvnoNameMultimap.get(mvnoName)) {
                    list.add(child);
                }
                executionResultList.put(mvnoName, parent);
                executionCountList.add(mvnoName, list.size());
            }
        }
    }
}
