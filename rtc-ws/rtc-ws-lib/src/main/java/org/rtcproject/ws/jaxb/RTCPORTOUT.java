
package org.rtcproject.ws.jaxb;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RTC_PORTOUT complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RTC_PORTOUT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RTC_PORTOUT_SERVICE" type="{http://rtcproject.org/MNP_WS}RTC_TERMINATE_SERVICE"/>
 *         &lt;element name="PORTOUT_OPERATOR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RTC_USER" type="{http://rtcproject.org/MNP_WS}RTC_REQUEST_USER"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RTC_PORTOUT", propOrder = {
    "rtcportoutservice",
    "portoutoperator",
    "rtcuser"
})
@XmlRootElement(name = "RTC_PORTOUT", namespace = "")
public class RTCPORTOUT {

    @XmlElement(name = "RTC_PORTOUT_SERVICE", required = true)
    protected RTCTERMINATESERVICE rtcportoutservice;
    @XmlElement(name = "PORTOUT_OPERATOR", required = true)
    protected String portoutoperator;
    @XmlElement(name = "RTC_USER", required = true)
    protected RTCREQUESTUSER rtcuser;

    /**
     * Gets the value of the rtcportoutservice property.
     * 
     * @return
     *     possible object is
     *     {@link RTCTERMINATESERVICE }
     *     
     */
    public RTCTERMINATESERVICE getRTCPORTOUTSERVICE() {
        return rtcportoutservice;
    }

    /**
     * Sets the value of the rtcportoutservice property.
     * 
     * @param value
     *     allowed object is
     *     {@link RTCTERMINATESERVICE }
     *     
     */
    public void setRTCPORTOUTSERVICE(RTCTERMINATESERVICE value) {
        this.rtcportoutservice = value;
    }

    /**
     * Gets the value of the portoutoperator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPORTOUTOPERATOR() {
        return portoutoperator;
    }

    /**
     * Sets the value of the portoutoperator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPORTOUTOPERATOR(String value) {
        this.portoutoperator = value;
    }

    /**
     * Gets the value of the rtcuser property.
     * 
     * @return
     *     possible object is
     *     {@link RTCREQUESTUSER }
     *     
     */
    public RTCREQUESTUSER getRTCUSER() {
        return rtcuser;
    }

    /**
     * Sets the value of the rtcuser property.
     * 
     * @param value
     *     allowed object is
     *     {@link RTCREQUESTUSER }
     *     
     */
    public void setRTCUSER(RTCREQUESTUSER value) {
        this.rtcuser = value;
    }

}
