
package org.rtcproject.ws.jaxb;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RTC_ACCOUNT_CREATE_TYPE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RTC_ACCOUNT_CREATE_TYPE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BILLING_ACCOUNT_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RTC_ACCOUNT_INFO" type="{http://rtcproject.org/MNP_WS}RTC_ACCOUNT" minOccurs="0"/>
 *         &lt;element name="RTC_SERVICES" type="{http://rtcproject.org/MNP_WS}RTC_SERVICE"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RTC_ACCOUNT_CREATE_TYPE", propOrder = {
    "billingaccountid",
    "rtcaccountinfo",
    "rtcservices"
})
public class RTCACCOUNTCREATETYPE {

    @XmlElement(name = "BILLING_ACCOUNT_ID")
    protected String billingaccountid;
    @XmlElement(name = "RTC_ACCOUNT_INFO")
    protected RTCACCOUNT rtcaccountinfo;
    @XmlElement(name = "RTC_SERVICES", required = true)
    protected RTCSERVICE rtcservices;

    /**
     * Gets the value of the billingaccountid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBILLINGACCOUNTID() {
        return billingaccountid;
    }

    /**
     * Sets the value of the billingaccountid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBILLINGACCOUNTID(String value) {
        this.billingaccountid = value;
    }

    /**
     * Gets the value of the rtcaccountinfo property.
     * 
     * @return
     *     possible object is
     *     {@link RTCACCOUNT }
     *     
     */
    public RTCACCOUNT getRTCACCOUNTINFO() {
        return rtcaccountinfo;
    }

    /**
     * Sets the value of the rtcaccountinfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link RTCACCOUNT }
     *     
     */
    public void setRTCACCOUNTINFO(RTCACCOUNT value) {
        this.rtcaccountinfo = value;
    }

    /**
     * Gets the value of the rtcservices property.
     * 
     * @return
     *     possible object is
     *     {@link RTCSERVICE }
     *     
     */
    public RTCSERVICE getRTCSERVICES() {
        return rtcservices;
    }

    /**
     * Sets the value of the rtcservices property.
     * 
     * @param value
     *     allowed object is
     *     {@link RTCSERVICE }
     *     
     */
    public void setRTCSERVICES(RTCSERVICE value) {
        this.rtcservices = value;
    }

}
