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
 * @author anuchitr
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "channelType",
    "channelRefNumber",
    "customerType",
    "cardNumber"
})
@XmlRootElement(name = "queryPinCodeRequest")
public class QueryPinCodeRequest {
    
    @XmlElement(required = true)
    protected ChannelType channelType;
    @XmlElement(required = true)
    protected String channelRefNumber;
    @XmlElement(required = true)
    protected CustomerType customerType;
    @XmlElement(required = true)
    protected String cardNumber;

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

    @Override
    public String toString() {
        return "QueryPinCodeRequest{" + "channelType=" + channelType + ", channelRefNumber=" + channelRefNumber + ", customerType=" + customerType + ", cardNumber=" + cardNumber + '}';
    }

}
