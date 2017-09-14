
package org.rtcproject.ws.jaxb;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RTC_ACCOUNT_CREATE_RESPONSE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RTC_ACCOUNT_CREATE_RESPONSE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RTC_CREATE_TRANS_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RTC_TRANS_STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RTC_TRANS_MSG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RTC_ACCOUNT_CREATE_RESPONSE", propOrder = {
    "rtccreatetransid",
    "rtctransstatus",
    "rtctransmsg"
})
public class RTCACCOUNTCREATERESPONSE {

    @XmlElement(name = "RTC_CREATE_TRANS_ID", required = true)
    protected String rtccreatetransid;
    @XmlElement(name = "RTC_TRANS_STATUS", required = true)
    protected String rtctransstatus;
    @XmlElement(name = "RTC_TRANS_MSG", required = true)
    protected String rtctransmsg;

    /**
     * Gets the value of the rtccreatetransid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRTCCREATETRANSID() {
        return rtccreatetransid;
    }

    /**
     * Sets the value of the rtccreatetransid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRTCCREATETRANSID(String value) {
        this.rtccreatetransid = value;
    }

    /**
     * Gets the value of the rtctransstatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRTCTRANSSTATUS() {
        return rtctransstatus;
    }

    /**
     * Sets the value of the rtctransstatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRTCTRANSSTATUS(String value) {
        this.rtctransstatus = value;
    }

    /**
     * Gets the value of the rtctransmsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRTCTRANSMSG() {
        return rtctransmsg;
    }

    /**
     * Sets the value of the rtctransmsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRTCTRANSMSG(String value) {
        this.rtctransmsg = value;
    }

}
