/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author HP-CAT
 */
public class CatOmBaseMsg implements Serializable {

    private BigInteger portType;
    private BigInteger msgId;
    private BigInteger reqTransId;
    private String msgCreateTimeStamp;
    private List msgList;

    public BigInteger getPortType() {
        return portType;
    }

    public void setPortType(BigInteger portType) {
        this.portType = portType;
    }

    public BigInteger getMsgId() {
        return msgId;
    }

    public void setMsgId(BigInteger msgId) {
        this.msgId = msgId;
    }

    public BigInteger getReqTransId() {
        return reqTransId;
    }

    public void setReqTransId(BigInteger reqTransId) {
        this.reqTransId = reqTransId;
    }

    public String getMsgCreateTimeStamp() {
        return msgCreateTimeStamp;
    }

    public void setMsgCreateTimeStamp(String msgCreateTimeStamp) {
        this.msgCreateTimeStamp = msgCreateTimeStamp;
    }
    
    public List getMsgList() {
        return msgList;
    }

    public void setMsgList(List msgList) {
        this.msgList = msgList;
    }
}
