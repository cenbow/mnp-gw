//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.26 at 11:06:53 PM ICT 
//


package com.telcordia.inpac.ws.jaxb;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MessageHeaderType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MessageHeaderType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PortType" type="{}PortSimpleType"/>
 *         &lt;element name="MessageID" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="SoapRequestId" type="{}SoapReqIdType"/>
 *         &lt;element name="MessageCreateTimeStamp" type="{}MNPDateType"/>
 *         &lt;element name="Sender" type="{}ParticipantIdType"/>
 *         &lt;element name="Receiver" type="{}ParticipantIdType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageHeaderType", propOrder = {
    "portType",
    "messageID",
    "soapRequestId",
    "messageCreateTimeStamp",
    "sender",
    "receiver"
})
public class MessageHeaderType {

    @XmlElement(name = "PortType", required = true)
    protected BigInteger portType;
    @XmlElement(name = "MessageID", required = true)
    protected BigInteger messageID;
    @XmlElement(name = "SoapRequestId", required = true)
    protected String soapRequestId;
    @XmlElement(name = "MessageCreateTimeStamp", required = true)
    protected String messageCreateTimeStamp;
    @XmlElement(name = "Sender", required = true)
    protected String sender;
    @XmlElement(name = "Receiver")
    protected String receiver;

    /**
     * Gets the value of the portType property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPortType() {
        return portType;
    }

    /**
     * Sets the value of the portType property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPortType(BigInteger value) {
        this.portType = value;
    }

    /**
     * Gets the value of the messageID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMessageID() {
        return messageID;
    }

    /**
     * Sets the value of the messageID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMessageID(BigInteger value) {
        this.messageID = value;
    }

    /**
     * Gets the value of the soapRequestId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoapRequestId() {
        return soapRequestId;
    }

    /**
     * Sets the value of the soapRequestId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoapRequestId(String value) {
        this.soapRequestId = value;
    }

    /**
     * Gets the value of the messageCreateTimeStamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageCreateTimeStamp() {
        return messageCreateTimeStamp;
    }

    /**
     * Sets the value of the messageCreateTimeStamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageCreateTimeStamp(String value) {
        this.messageCreateTimeStamp = value;
    }

    /**
     * Gets the value of the sender property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSender() {
        return sender;
    }

    /**
     * Sets the value of the sender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSender(String value) {
        this.sender = value;
    }

    /**
     * Gets the value of the receiver property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * Sets the value of the receiver property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiver(String value) {
        this.receiver = value;
    }

    @Override
    public String toString() {
        return "MessageHeaderType{" + "portType=" + portType + ", messageID=" + messageID + ", soapRequestId=" + soapRequestId + ", messageCreateTimeStamp=" + messageCreateTimeStamp + '}';
    }

}
