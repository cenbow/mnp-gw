
package org.rtcproject.ws.jaxb;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RTC_PORT complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RTC_PORT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RTC_TRANS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RTC_PORT", propOrder = {
    "rtctrans"
})
public class RTCPORT {

    @XmlElement(name = "RTC_TRANS", required = true)
    protected String rtctrans;

    /**
     * Gets the value of the rtctrans property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRTCTRANS() {
        return rtctrans;
    }

    /**
     * Sets the value of the rtctrans property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRTCTRANS(String value) {
        this.rtctrans = value;
    }

}
