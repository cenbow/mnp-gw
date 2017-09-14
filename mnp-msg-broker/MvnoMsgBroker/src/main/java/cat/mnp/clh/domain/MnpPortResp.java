/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.clh.domain;

import java.io.Serializable;

/**
 *
 * @author HP-CAT
 */
public class MnpPortResp implements Serializable {

    private String portId;
    private String orderId;
    private String msisdn;
    private String numAccepted;
    private String rejectReasonCode;
    private String correctPinCode;

    public String getPortId() {
        return portId;
    }

    public void setPortId(String portId) {
        this.portId = portId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getNumAccepted() {
        return numAccepted;
    }

    public void setNumAccepted(String numAccepted) {
        this.numAccepted = numAccepted;
    }

    public String getRejectReasonCode() {
        return rejectReasonCode;
    }

    public void setRejectReasonCode(String rejectReasonCode) {
        this.rejectReasonCode = rejectReasonCode;
    }

    public String getCorrectPinCode() {
        return correctPinCode;
    }

    public void setCorrectPinCode(String correctPinCode) {
        this.correctPinCode = correctPinCode;
    }
    
    
    
    @Override
    public String toString() {
        return String.format("MnpPortResp(%s, %s, %s)", getPortId(), getOrderId(), getMsisdn());
    }
}
