/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.clh.dao.importer.worker;

import cat.mnp.mvno.dao.worker.Worker;
import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import org.springframework.util.Assert;

/**
 *
 * @author HP-CAT
 */
public class PendingPortReqMsgImporterWorker extends Worker {

    private String orderId;
    private String xml;

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    @Override
    public void execute(Connection connection) throws SQLException {
        Assert.notNull(orderId, "orderId must not be null");
        Assert.notNull(xml, "xml must not be null");
        Assert.notNull(plSqlQuery, "plSqlQuery must not be null");

        try (CallableStatement callableStatement = connection.prepareCall(plSqlQuery)) {
            callableStatement.setString("iOrderId", orderId);

            try (StringReader r = new StringReader(xml)) {
                callableStatement.setClob("iXml", r);
                callableStatement.execute();
            }
        }
    }
}
