package cat.mnp.om.hibernate;

import java.util.Date;

public class MnpPortComplete implements java.io.Serializable {

    private MnpPortCompleteId id;
    private String orderId;
    private String msisdn;
    private String recipient;
    private String donor;
    private Date completionTime;
    private Date submissionTime;
    private String route;
    private String mvnoName;

    public MnpPortComplete() {
        id = new MnpPortCompleteId();
    }

    public MnpPortComplete(MnpPortCompleteId id) {
        this.id = id;
    }

    public MnpPortComplete(MnpPortCompleteId id, String orderId, String msisdn, String recipient, String donor, Date completionTime, Date submissionTime, String route, String mvnoName) {
        this.id = id;
        this.orderId = orderId;
        this.msisdn = msisdn;
        this.recipient = recipient;
        this.donor = donor;
        this.completionTime = completionTime;
        this.submissionTime = submissionTime;
        this.route = route;
        this.mvnoName = mvnoName;
    }

    public Date getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }

    public String getDonor() {
        return donor;
    }

    public void setDonor(String donor) {
        this.donor = donor;
    }

    public MnpPortCompleteId getId() {
        return id;
    }

    public void setId(MnpPortCompleteId id) {
        this.id = id;
    }

    public Integer getMsgId() {
        return this.id.getMsgId();
    }

    public void setMsgId(Integer msgId) {
        this.id.setMsgId(msgId);
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getMvnoName() {
        return mvnoName;
    }

    public void setMvnoName(String mvnoName) {
        this.mvnoName = mvnoName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPortId() {
        return this.id.getPortId();
    }

    public void setPortId(String portId) {
        this.id.setPortId(portId);
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Date getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(Date submissionTime) {
        this.submissionTime = submissionTime;
    }

    @Override
    public String toString() {
        return String.format("MnpPortComplete(%s, %s, %s, %s)", msisdn, id.getMsgId(), id.getPortId(), orderId, mvnoName);
    }
}
