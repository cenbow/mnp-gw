/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.dao.splitter.worker;

import cat.mnp.mvno.dao.worker.Worker;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.telcordia.inpac.ws.jaxb.NumTypeWithFlagWithPortDate;
import com.telcordia.inpac.ws.jaxb.PortNotMsgType;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.Assert;

/**
 *
 * @author HP-CAT
 */
public class PortNotMsgSplitterWorker extends Worker {

    private String[] lowLevelMvnoNameList;

    public void setLowLevelMvnoNameList(String[] lowLevelMvnoNameList) {
        this.lowLevelMvnoNameList = lowLevelMvnoNameList;
    }

    private PortNotMsgType buildParent(PortNotMsgType msg) {
        PortNotMsgType parent = new PortNotMsgType();
        parent.setOrderId(msg.getOrderId());
        parent.setProcessType(msg.getProcessType());
        parent.setNumberWithFlagWithPortDate(new ArrayList<NumTypeWithFlagWithPortDate>());
        return parent;
    }

    @Override
    public void execute(Connection connection) throws SQLException {
        Assert.notNull(msgObject, "msgObject must not be null");
        Assert.notNull(plSqlQuery, "plSqlQuery must not be null");

        PortNotMsgType msg = (PortNotMsgType) msgObject;

        try (CallableStatement callableStatement = connection.prepareCall(plSqlQuery)) {
            String msgId = messageHeader.getMessageID().toString();
            ArrayListMultimap<String, NumTypeWithFlagWithPortDate> mvnoNameMultimap = ArrayListMultimap.create();
            ArrayListMultimap<String, Object> executionResultList = ArrayListMultimap.create();
            setExecutionResultList(executionResultList);
            Multiset<String> executionCountList = HashMultiset.create();
            setExecutionCountList(executionCountList);
            for (NumTypeWithFlagWithPortDate child : msg.getNumberWithFlagWithPortDate()) {//get mvno name
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
                if (ArrayUtils.contains(lowLevelMvnoNameList, mvnoName)) {//split all
                    for (NumTypeWithFlagWithPortDate child : mvnoNameMultimap.get(mvnoName)) {
                        PortNotMsgType parent = buildParent(msg);
                        parent.getNumberWithFlagWithPortDate().add(child);
                        executionResultList.put(mvnoName, parent);
                        executionCountList.add(mvnoName, parent.getNumberWithFlagWithPortDate().size());
                    }
                } else {
                    PortNotMsgType parent = buildParent(msg);
                    for (NumTypeWithFlagWithPortDate child : mvnoNameMultimap.get(mvnoName)) {
                        parent.getNumberWithFlagWithPortDate().add(child);
                    }
                    executionResultList.put(mvnoName, parent);
                    executionCountList.add(mvnoName, parent.getNumberWithFlagWithPortDate().size());
                }
            }
        }
    }
}
