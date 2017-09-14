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
 * <p>Java class for NumTypeWithFlag complex type.
 
 <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NumTypeWithFlag">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MSISDN" type="{}TelephoneNumberType"/>
 *         &lt;element name="PortId" type="{}PortIdType"/>
 *         &lt;element name="NumberAccepted" type="{}FlagType"/>
 *         &lt;element name="RejectReasonCode" type="{}ReasonCodeType" minOccurs="0"/>
 *         &lt;element name="CorrectPinCode" type="{}PinCodeType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NumTypeWithFlag", propOrder = {
    "msisdn",
    "portId",
    "numberAccepted",
    "rejectReasonCode",
    "correctPinCode"
})
public class NumTypeWithFlag {

    @XmlElement(name = "MSISDN", required = true)
    protected String msisdn;
    @XmlElement(name = "PortId", required = true)
    protected String portId;
    @XmlElement(name = "NumberAccepted", required = true)
    protected BigInteger numberAccepted;
    @XmlElement(name = "RejectReasonCode")
    protected String rejectReasonCode;
    @XmlElement(name = "CorrectPinCode")
    protected String correctPinCode;

    /**
     * Gets the value of the msisdn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMSISDN() {
        return msisdn;
    }

    /**
     * Sets the value of the msisdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMSISDN(String value) {
        this.msisdn = value;
    }

    /**
     * Gets the value of the portId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortId() {
        return portId;
    }

    /**
     * Sets the value of the portId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortId(String value) {
        this.portId = value;
    }

    /**
     * Gets the value of the numberAccepted property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumberAccepted() {
        return numberAccepted;
    }

    /**
     * Sets the value of the numberAccepted property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumberAccepted(BigInteger value) {
        this.numberAccepted = value;
    }

    /**
     * Gets the value of the rejectReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRejectReasonCode() {
        return rejectReasonCode;
    }

    /**
     * Sets the value of the rejectReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRejectReasonCode(String value) {
        this.rejectReasonCode = value;
    }

    /**
     * Gets the value of the correctPinCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrectPinCode() {
        return correctPinCode;
    }

    /**
     * Sets the value of the correctPinCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrectPinCode(String value) {
        this.correctPinCode = value;
    }

}
