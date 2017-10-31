/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.dao.worker;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multiset;
import com.telcordia.inpac.ws.jaxb.MessageFooterType;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import org.hibernate.jdbc.Work;

/**
 *
 * @author HP-CAT
 */
public class Worker implements Work {

    protected MessageHeaderType messageHeader;
    protected MessageFooterType messageFooter;
    protected Map<String, Object> mqHeaders;
    protected Object msgObject;
    protected String plSqlQuery;
    private String successResult;
    private String executionResult;
    private ArrayListMultimap<String, Object> executionResultList;
    private int executionCount;
    private Multiset<String> executionCountList;

    public void setMessageHeader(MessageHeaderType messageHeader) {
        this.messageHeader = messageHeader;
    }

    public void setMessageFooter(MessageFooterType messageFooter) {
        this.messageFooter = messageFooter;
    }

    public void setMqHeaders(Map<String, Object> mqHeaders) {
        this.mqHeaders = mqHeaders;
    }

    public void setMsgObject(Object msgObject) {
        this.msgObject = msgObject;
    }

    public void setPlSqlQuery(String plSqlQuery) {
        this.plSqlQuery = plSqlQuery;
    }

    public String getPlSqlQuery() { // TODO: for debug reason
		return plSqlQuery;
	}

	public String getSuccessResult() {
        return successResult;
    }

    public void setSuccessResult(String successResult) {
        this.successResult = successResult;
    }

    public String getExecutionResult() {
        return executionResult;
    }

    protected void setExecutionResult(String executionResult) {
        this.executionResult = executionResult;
    }

    public ArrayListMultimap<String, Object> getExecutionResultList() {
        return executionResultList;
    }

    public void setExecutionResultList(ArrayListMultimap<String, Object> executionResultList) {
        this.executionResultList = executionResultList;
    }

    public int getExecutionCount() {
        return executionCount;
    }

    public void setExecutionCount(int executionCount) {
        this.executionCount = executionCount;
    }

    public Multiset<String> getExecutionCountList() {
        return executionCountList;
    }

    public void setExecutionCountList(Multiset<String> executionCountList) {
        this.executionCountList = executionCountList;
    }

    @Override
    public void execute(Connection connection) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
