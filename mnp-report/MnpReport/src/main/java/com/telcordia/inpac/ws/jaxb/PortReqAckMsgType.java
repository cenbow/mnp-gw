//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2554.06.30 at 12:25:48 หลังเที่ยง ICT 
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
 * <p>Java class for PortReqAckMsgType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PortReqAckMsgType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProcessType" type="{}ProcessDataType"/>
 *         &lt;element name="OrderId" type="{}OrderIdType"/>
 *         &lt;element name="OrderDate" type="{}NPDateFormatType"/>
 *         &lt;element name="ValidationDeadline" type="{}NPDateFormatType"/>
 *         &lt;element name="Zone" type="{}ZoneType"/>
 *         &lt;element name="Donor" type="{}PartIDType"/>
 *         &lt;element name="Recipient" type="{}PartIDType"/>
 *         &lt;element name="OperatorCode" type="{}OpCodeType"/>
 *         &lt;element name="NumberDataPayRef" type="{}NumTypeWithFlagWithPaymentRef" maxOccurs="unbounded"/>
 *         &lt;element name="SubscriberData" type="{}SubscriberDataType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PortReqAckMsgType", propOrder = {
    "processType",
    "orderId",
    "orderDate",
    "validationDeadline",
    "zone",
    "donor",
    "recipient",
    "operatorCode",
    "numberDataPayRef",
    "subscriberData"
})
public class PortReqAckMsgType {

    @XmlElement(name = "ProcessType", required = true)
    protected BigInteger processType;
    @XmlElement(name = "OrderId", required = true)
    protected String orderId;
    @XmlElement(name = "OrderDate", required = true)
    protected String orderDate;
    @XmlElement(name = "ValidationDeadline", required = true)
    protected String validationDeadline;
    @XmlElement(name = "Zone", required = true)
    protected String zone;
    @XmlElement(name = "Donor", required = true)
    protected String donor;
    @XmlElement(name = "Recipient", required = true)
    protected String recipient;
    @XmlElement(name = "OperatorCode", required = true)
    protected String operatorCode;
    @XmlElement(name = "NumberDataPayRef", required = true)
    protected List<NumTypeWithFlagWithPaymentRef> numberDataPayRef;
    @XmlElement(name = "SubscriberData", required = true)
    protected SubscriberDataType subscriberData;

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
     * Gets the value of the orderDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * Sets the value of the orderDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderDate(String value) {
        this.orderDate = value;
    }

    /**
     * Gets the value of the validationDeadline property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidationDeadline() {
        return validationDeadline;
    }

    /**
     * Sets the value of the validationDeadline property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidationDeadline(String value) {
        this.validationDeadline = value;
    }

    /**
     * Gets the value of the zone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZone() {
        return zone;
    }

    /**
     * Sets the value of the zone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZone(String value) {
        this.zone = value;
    }

    /**
     * Gets the value of the donor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDonor() {
        return donor;
    }

    /**
     * Sets the value of the donor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDonor(String value) {
        this.donor = value;
    }

    /**
     * Gets the value of the recipient property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Sets the value of the recipient property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecipient(String value) {
        this.recipient = value;
    }

    /**
     * Gets the value of the operatorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorCode() {
        return operatorCode;
    }

    /**
     * Sets the value of the operatorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorCode(String value) {
        this.operatorCode = value;
    }

    /**
     * Gets the value of the numberDataPayRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the numberDataPayRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNumberDataPayRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NumTypeWithFlagWithPaymentRef }
     * 
     * 
     */
    public List<NumTypeWithFlagWithPaymentRef> getNumberDataPayRef() {
        if (numberDataPayRef == null) {
            numberDataPayRef = new ArrayList<NumTypeWithFlagWithPaymentRef>();
        }
        return this.numberDataPayRef;
    }

    public void setNumberDataPayRef(List<NumTypeWithFlagWithPaymentRef> numberDataPayRef) {
        this.numberDataPayRef = numberDataPayRef;
    }

    /**
     * Gets the value of the subscriberData property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriberDataType }
     *     
     */
    public SubscriberDataType getSubscriberData() {
        return subscriberData;
    }

    /**
     * Sets the value of the subscriberData property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriberDataType }
     *     
     */
    public void setSubscriberData(SubscriberDataType value) {
        this.subscriberData = value;
    }

}
