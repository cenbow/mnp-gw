
package org.rtcproject.ws.jaxb;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RTC_TERMINATE_SERVICE_RESPONSE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RTC_TERMINATE_SERVICE_RESPONSE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RTC_TERMINATE_TRANS_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "RTC_TERMINATE_SERVICE_RESPONSE", propOrder = {
    "rtcterminatetransid",
    "rtctransstatus",
    "rtctransmsg"
})
public class RTCTERMINATESERVICERESPONSE {

    @XmlElement(name = "RTC_TERMINATE_TRANS_ID", required = true)
    protected String rtcterminatetransid;
    @XmlElement(name = "RTC_TRANS_STATUS", required = true)
    protected String rtctransstatus;
    @XmlElement(name = "RTC_TRANS_MSG", required = true)
    protected String rtctransmsg;

    /**
     * Gets the value of the rtcterminatetransid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRTCTERMINATETRANSID() {
        return rtcterminatetransid;
    }

    /**
     * Sets the value of the rtcterminatetransid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRTCTERMINATETRANSID(String value) {
        this.rtcterminatetransid = value;
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
