
package org.rtcproject.ws.jaxb;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RTC_WS_FAULT complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RTC_WS_FAULT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RTC_FAULT_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RTC_FAULT_MSG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RTC_WS_FAULT", propOrder = {
    "rtcfaultcode",
    "rtcfaultmsg"
})
public class RTCWSFAULT {

    @XmlElement(name = "RTC_FAULT_CODE", required = true)
    protected String rtcfaultcode;
    @XmlElement(name = "RTC_FAULT_MSG", required = true)
    protected String rtcfaultmsg;

    /**
     * Gets the value of the rtcfaultcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRTCFAULTCODE() {
        return rtcfaultcode;
    }

    /**
     * Sets the value of the rtcfaultcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRTCFAULTCODE(String value) {
        this.rtcfaultcode = value;
    }

    /**
     * Gets the value of the rtcfaultmsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRTCFAULTMSG() {
        return rtcfaultmsg;
    }

    /**
     * Sets the value of the rtcfaultmsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRTCFAULTMSG(String value) {
        this.rtcfaultmsg = value;
    }

}
