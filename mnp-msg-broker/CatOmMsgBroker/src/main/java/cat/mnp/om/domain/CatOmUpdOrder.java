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
public class CatOmUpdOrder implements Serializable {
    
    private String orderId;
    private BigInteger processType;
    private String orderDate;
    private String customerIdentifier;
    private String customerName;
    private String customerSurname;
    private String zone;
    private String recipient;
    private String operatorCode;
    private String donor;
    private String validDeadlineDate;
    private BigInteger timeMsgId;
    private String timeTimerCode;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
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

    public List<CatOmService> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<CatOmService> serviceList) {
        this.serviceList = serviceList;
    }
}
