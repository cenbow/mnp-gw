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
 * <p>Java class for TimerNotifMsgType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TimerNotifMsgType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessType" type="{}ProcessDataType" minOccurs="0"/>
 *         &lt;element name="OrderId" type="{}OrderIdType" minOccurs="0"/>
 *         &lt;element name="TimerCode" type="{}Len10StrType"/>
 *         &lt;element name="MessageID" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="NumberDataBase" type="{}NumTypeBase" maxOccurs="6000"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimerNotifMsgType", propOrder = {
    "processType",
    "orderId",
    "timerCode",
    "messageID",
    "numberDataBase"
})
public class TimerNotifMsgType {

    @XmlElement(name = "ProcessType")
    protected BigInteger processType;
    @XmlElement(name = "OrderId")
    protected String orderId;
    @XmlElement(name = "TimerCode", required = true)
    protected String timerCode;
    @XmlElement(name = "MessageID", required = true)
    protected BigInteger messageID;
    @XmlElement(name = "NumberDataBase", required = true)
    protected List<NumTypeBase> numberDataBase;

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
     * Gets the value of the timerCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimerCode() {
        return timerCode;
    }

    /**
     * Sets the value of the timerCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimerCode(String value) {
        this.timerCode = value;
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
     * Gets the value of the numberDataBase property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the numberDataBase property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNumberDataBase().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NumTypeBase }
     * 
     * 
     */
    public List<NumTypeBase> getNumberDataBase() {
        if (numberDataBase == null) {
            numberDataBase = new ArrayList<NumTypeBase>();
        }
        return this.numberDataBase;
    }

    public void setNumberDataBase(List<NumTypeBase> numberDataBase) {
        this.numberDataBase = numberDataBase;
    }
    
}
