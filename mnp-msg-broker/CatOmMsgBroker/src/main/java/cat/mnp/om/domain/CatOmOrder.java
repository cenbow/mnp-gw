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
public class CatOmOrder implements Serializable {

    private String orderId;
    private BigInteger processType;
    private String orderDate;
    private String customerIdentifier;
    private String customerRemark;
    private BigInteger zone;
    private String recipient;
    private String operatorCode;
    private String donor;
    private String validDeadlineDate;
    private BigInteger timeMsgId;
    private String timeTimerCode;
    private String channelId;
    private List<CatOmService> serviceList;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigInteger getProcessType() {
        return processType;
    }

    public void setProcessType(BigInteger processType) {
        this.processType = processType;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerIdentifier() {
        return customerIdentifier;
    }

    public void setCustomerIdentifier(String customerIdentifier) {
        this.customerIdentifier = customerIdentifier;
    }

    public String getCustomerRemark() {
        return customerRemark;
    }

    public void setCustomerRemark(String customerRemark) {
        this.customerRemark = customerRemark;
    }

    public BigInteger getZone() {
        return zone;
    }

    public void setZone(BigInteger zone) {
        this.zone = zone;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getDonor() {
        return donor;
    }

    public void setDonor(String donor) {
        this.donor = donor;
    }

    public String getValidDeadlineDate() {
        return validDeadlineDate;
    }

    public void setValidDeadlineDate(String validDeadlineDate) {
        this.validDeadlineDate = validDeadlineDate;
    }

    public BigInteger getTimeMsgId() {
        return timeMsgId;
    }

    public void setTimeMsgId(BigInteger timeMsgId) {
        this.timeMsgId = timeMsgId;
    }

    public String getTimeTimerCode() {
        return timeTimerCode;
    }

    public void setTimeTimerCode(String timeTimerCode) {
        this.timeTimerCode = timeTimerCode;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public List<CatOmService> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<CatOmService> serviceList) {
        this.serviceList = serviceList;
    }

}
