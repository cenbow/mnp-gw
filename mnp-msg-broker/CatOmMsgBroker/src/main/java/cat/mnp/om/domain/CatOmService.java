/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.om.domain;

import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @author HP-CAT
 */
public class CatOmService implements Serializable {

    public static final String STRUCT_TYPE_NAME = "REC_MNP_SERV";
    public static final String ARRAY_TYPE_NAME = "TABLE_MNP_SERV";
    private String orderId;
    private String portId;
    private BigInteger processType;
    private String msisdn;
    private String pinCode;
    private String correctPinCode;
    private BigInteger clhAccpFlag;
    private String clhRejCode;
    private BigInteger numAcceptFlag;
    private String donorRejReasonCode;
    private BigInteger recipientConfFlag;
    private String portingDate;
    private String numAppFlag;
    private String rejectReasonCode;
    private String donor;
    private String recipient;
    private String numberRangeHolder;
    private String route;
    private BigInteger zone;
    private String operatorCode;
    private String downloadType;
    private String downloadStartDate;
    private String downloadEndDate;
    private String synReqId;
    private String dataDate;
    private String dataLocation;
    private String contactdetail;
    private String notifyErrCode;
    private String notifyErrDesc;
    private BigInteger notifyErrMsgId;
    private BigInteger isPrepaid;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPortId() {
        return portId;
    }

    public void setPortId(String portId) {
        this.portId = portId;
    }

    public BigInteger getProcessType() {
        return processType;
    }

    public void setProcessType(BigInteger processType) {
        this.processType = processType;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getCorrectPinCode() {
        return correctPinCode;
    }

    public void setCorrectPinCode(String correctPinCode) {
        this.correctPinCode = correctPinCode;
    }

    public BigInteger getClhAccpFlag() {
        return clhAccpFlag;
    }

    public void setClhAccpFlag(BigInteger clhAccpFlag) {
        this.clhAccpFlag = clhAccpFlag;
    }

    public String getClhRejCode() {
        return clhRejCode;
    }

    public void setClhRejCode(String clhRejCode) {
        this.clhRejCode = clhRejCode;
    }

    public BigInteger getNumAcceptFlag() {
        return numAcceptFlag;
    }

    public void setNumAcceptFlag(BigInteger numAcceptFlag) {
        this.numAcceptFlag = numAcceptFlag;
    }

    public String getDonorRejReasonCode() {
        return donorRejReasonCode;
    }

    public void setDonorRejReasonCode(String donorRejReasonCode) {
        this.donorRejReasonCode = donorRejReasonCode;
    }

    public BigInteger getRecipientConfFlag() {
        return recipientConfFlag;
    }

    public void setRecipientConfFlag(BigInteger recipientConfFlag) {
        this.recipientConfFlag = recipientConfFlag;
    }

    public String getPortingDate() {
        return portingDate;
    }

    public void setPortingDate(String portingDate) {
        this.portingDate = portingDate;
    }

    public String getNumAppFlag() {
        return numAppFlag;
    }

    public void setNumAppFlag(String numAppFlag) {
        this.numAppFlag = numAppFlag;
    }

    public String getRejectReasonCode() {
        return rejectReasonCode;
    }

    public void setRejectReasonCode(String rejectReasonCode) {
        this.rejectReasonCode = rejectReasonCode;
    }

    public String getDonor() {
        return donor;
    }

    public void setDonor(String donor) {
        this.donor = donor;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getNumberRangeHolder() {
        return numberRangeHolder;
    }

    public void setNumberRangeHolder(String numberRangeHolder) {
        this.numberRangeHolder = numberRangeHolder;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public BigInteger getZone() {
        return zone;
    }

    public void setZone(BigInteger zone) {
        this.zone = zone;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(String downloadType) {
        this.downloadType = downloadType;
    }

    public String getDownloadStartDate() {
        return downloadStartDate;
    }

    public void setDownloadStartDate(String downloadStartDate) {
        this.downloadStartDate = downloadStartDate;
    }

    public String getDownloadEndDate() {
        return downloadEndDate;
    }

    public void setDownloadEndDate(String downloadEndDate) {
        this.downloadEndDate = downloadEndDate;
    }

    public String getSynReqId() {
        return synReqId;
    }

    public void setSynReqId(String synReqId) {
        this.synReqId = synReqId;
    }

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }

    public String getDataLocation() {
        return dataLocation;
    }

    public void setDataLocation(String dataLocation) {
        this.dataLocation = dataLocation;
    }

    public String getContactdetail() {
        return contactdetail;
    }

    public void setContactdetail(String contactdetail) {
        this.contactdetail = contactdetail;
    }

    public String getNotifyErrCode() {
        return notifyErrCode;
    }

    public void setNotifyErrCode(String notifyErrCode) {
        this.notifyErrCode = notifyErrCode;
    }

    public String getNotifyErrDesc() {
        return notifyErrDesc;
    }

    public void setNotifyErrDesc(String notifyErrDesc) {
        this.notifyErrDesc = notifyErrDesc;
    }

    public BigInteger getNotifyErrMsgId() {
        return notifyErrMsgId;
    }

    public void setNotifyErrMsgId(BigInteger notifyErrMsgId) {
        this.notifyErrMsgId = notifyErrMsgId;
    }

    public BigInteger getIsPrepaid() {
        return isPrepaid;
    }

    public void setIsPrepaid(BigInteger isPrepaid) {
        this.isPrepaid = isPrepaid;
    }

}
