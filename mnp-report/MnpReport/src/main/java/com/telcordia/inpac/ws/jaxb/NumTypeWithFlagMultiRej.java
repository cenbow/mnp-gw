//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2554.06.30 at 12:25:48 หลังเที่ยง ICT 
//


package com.telcordia.inpac.ws.jaxb;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NumTypeWithFlagMultiRej complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NumTypeWithFlagMultiRej">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MSISDN" type="{}TelephoneNumberType"/>
 *         &lt;element name="PortId" type="{}TranIDType"/>
 *         &lt;element name="NumAccepted" type="{}FlagType"/>
 *         &lt;element name="RejectReasonCode" type="{}Len200StrType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NumTypeWithFlagMultiRej", propOrder = {
    "msisdn",
    "portId",
    "numAccepted",
    "rejectReasonCode"
})
public class NumTypeWithFlagMultiRej {

    @XmlElement(name = "MSISDN", required = true)
    protected String msisdn;
    @XmlElement(name = "PortId", required = true)
    protected String portId;
    @XmlElement(name = "NumAccepted", required = true)
    protected BigInteger numAccepted;
    @XmlElement(name = "RejectReasonCode")
    protected String rejectReasonCode;

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
     * Gets the value of the numAccepted property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumAccepted() {
        return numAccepted;
    }

    /**
     * Sets the value of the numAccepted property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumAccepted(BigInteger value) {
        this.numAccepted = value;
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

}
