package cat.mnp.sps.hibernate;

import java.util.Date;

public class InternalPortHist implements java.io.Serializable {

    private String portId;
    private String msisdn;
    private String recipient;
    private Date completionTime;
    private Date submissionTime;
    private String route;

    public InternalPortHist(String portId) {
        this.portId = portId;
    }

    public InternalPortHist(String portId, String msisdn, String recipient, Date completionTime, Date submissionTime, String route) {
        this.portId = portId;
        this.msisdn = msisdn;
        this.recipient = recipient;
        this.completionTime = completionTime;
        this.submissionTime = submissionTime;
        this.route = route;
    }

    public Date getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getPortId() {
        return this.portId;
    }

    public void setPortId(String portId) {
        this.portId = portId;
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
        return String.format("InternalPortHist(%s, %s, %s)", msisdn, portId, recipient);
    }
}
