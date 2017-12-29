/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.pincode.ws.portout;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author CATr
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "channelType",
    "channelRefNumber",
    "customerType",
    "cardNumber",
    "pinCodeDataList",
    "contactChannelType",
    "contactEmailAddress",
    "contactMsisdn"
})
@XmlRootElement(name = "queryPinCodeResponse")
public class QueryPinCodeResponse {
    
    @XmlElement(required = true)
    protected ChannelType channelType;
    @XmlElement(required = true)
    protected String channelRefNumber;
    @XmlElement(required = true)
    protected CustomerType customerType;
    @XmlElement(required = true)
    protected String cardNumber;
    @XmlElementWrapper(name = "pinCodeDataList", required = true)
    @XmlElement(name = "pinCodeData", required = true)
    protected List<PinCodeData> pinCodeDataList;
    @XmlElement(required = true)
    protected ContactChannelType contactChannelType;
    @XmlElement
    protected String contactEmailAddress;
    @XmlElement
    protected String contactMsisdn;

    public ChannelType getChannelType() {
        return channelType;
    }

    public void setChannelType(ChannelType channelType) {
        this.channelType = channelType;
    }

    public String getChannelRefNumber() {
        return channelRefNumber;
    }

    public void setChannelRefNumber(String channelRefNumber) {
        this.channelRefNumber = channelRefNumber;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public List<PinCodeData> getPinCodeDataList() {
        return pinCodeDataList;
    }

    public void setPinCodeDataList(List<PinCodeData> pinCodeDataList) {
        this.pinCodeDataList = pinCodeDataList;
    }

    public ContactChannelType getContactChannelType() {
        return contactChannelType;
    }

    public void setContactChannelType(ContactChannelType contactChannelType) {
        this.contactChannelType = contactChannelType;
    }

    public String getContactEmailAddress() {
        return contactEmailAddress;
    }

    public void setContactEmailAddress(String contactEmailAddress) {
        this.contactEmailAddress = contactEmailAddress;
    }

    public String getContactMsisdn() {
        return contactMsisdn;
    }

    public void setContactMsisdn(String contactMsisdn) {
        this.contactMsisdn = contactMsisdn;
    }

    @Override
    public String toString() {
        return "QueryPinCodeResponse{" + "channelType=" + channelType + ", channelRefNumber=" + channelRefNumber + ", customerType=" + customerType + ", cardNumber=" + cardNumber + ", pinCodeDataList=" + pinCodeDataList + ", contactChannelType=" + contactChannelType + ", contactEmailAddress=" + contactEmailAddress + ", contactMsisdn=" + contactMsisdn + '}';
    }

}
