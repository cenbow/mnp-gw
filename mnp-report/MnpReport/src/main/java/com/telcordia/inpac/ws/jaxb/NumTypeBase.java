//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2554.06.30 at 12:25:48 หลังเที่ยง ICT 
//


package com.telcordia.inpac.ws.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NumTypeBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NumTypeBase">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MSISDN" type="{}TelephoneNumberType"/>
 *         &lt;element name="PortId" type="{}TranIDType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NumTypeBase", propOrder = {
    "msisdn",
    "portId"
})
public class NumTypeBase {

    @XmlElement(name = "MSISDN", required = true)
    protected String msisdn;
    @XmlElement(name = "PortId", required = true)
    protected String portId;

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

}
