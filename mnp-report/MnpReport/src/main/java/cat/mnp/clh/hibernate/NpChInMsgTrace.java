package cat.mnp.clh.hibernate;

import java.sql.Clob;
import java.util.Date;

public class NpChInMsgTrace implements java.io.Serializable {

    private Date messageTimestamp;
    private Long clearingHouseId;
    private Clob messageData;
    private Integer messageTypeId;

    public NpChInMsgTrace() {
    }

    public NpChInMsgTrace(Date messageTimestamp, Long clearingHouseId, Clob messageData, Integer messageTypeId) {
        this.messageTimestamp = messageTimestamp;
        this.clearingHouseId = clearingHouseId;
        this.messageData = messageData;
        this.messageTypeId = messageTypeId;
    }

    public Long getClearingHouseId() {
        return clearingHouseId;
    }

    public void setClearingHouseId(Long clearingHouseId) {
        this.clearingHouseId = clearingHouseId;
    }

    public Clob getMessageData() {
        return messageData;
    }

    public void setMessageData(Clob messageData) {
        this.messageData = messageData;
    }

    public Date getMessageTimestamp() {
        return messageTimestamp;
    }

    public void setMessageTimestamp(Date messageTimestamp) {
        this.messageTimestamp = messageTimestamp;
    }

    public Integer getMessageTypeId() {
        return messageTypeId;
    }

    public void setMessageTypeId(Integer messageTypeId) {
        this.messageTypeId = messageTypeId;
    }
    
    @Override
    public String toString() {
        return String.format("%s(%s, %s)", this.getClass().getName(), this.getMessageTypeId(), this.getMessageTimestamp());
    }
}
