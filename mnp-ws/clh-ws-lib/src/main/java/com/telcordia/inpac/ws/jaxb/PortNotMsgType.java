//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.26 at 11:06:53 PM ICT 
//


package com.telcordia.inpac.ws.jaxb;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PortNotMsgType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PortNotMsgType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessType" type="{}ProcessDataType"/>
 *         &lt;element name="OrderId" type="{}OrderIdType"/>
 *         &lt;element name="NumberWithFlagWithPortDate" type="{}NumTypeWithFlagWithPortDate" maxOccurs="6000"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PortNotMsgType", propOrder = {
    "processType",
    "orderId",
    "numberWithFlagWithPortDate"
})
public class PortNotMsgType {

    @XmlElement(name = "ProcessType", required = true)
    protected BigInteger processType;
    @XmlElement(name = "OrderId", required = true)
    protected String orderId;
    @XmlElement(name = "NumberWithFlagWithPortDate", required = true)
    protected List<NumTypeWithFlagWithPortDate> numberWithFlagWithPortDate;

    /**
     * Gets the value of the processType property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getProcessType() {
        return processType;
    }

    /**
     * Sets the value of the processType property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setProcessType(BigInteger value) {
        this.processType = value;
    }

    /**
     * Gets the value of the orderId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Sets the value of the orderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderId(String value) {
        this.orderId = value;
    }

    /**
     * Gets the value of the numberWithFlagWithPortDate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the numberWithFlagWithPortDate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
    getNumberWithFlagWithPortDate().add(newItem);
 </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NumTypeWithFlagWithPortDate }
     * 
     * 
     */
    public List<NumTypeWithFlagWithPortDate> getNumberWithFlagWithPortDate() {
        if (numberWithFlagWithPortDate == null) {
            numberWithFlagWithPortDate = new ArrayList<NumTypeWithFlagWithPortDate>();
        }
        return this.numberWithFlagWithPortDate;
    }

    public void setNumberWithFlagWithPortDate(List<NumTypeWithFlagWithPortDate> numberWithFlagWithPortDate) {
        this.numberWithFlagWithPortDate = numberWithFlagWithPortDate;
    }
    
}
