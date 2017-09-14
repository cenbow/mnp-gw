
package org.rtcproject.ws.jaxb;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RTC_ENV_CONFIG complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RTC_ENV_CONFIG">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RTC_MODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="C1RT_SAPI_URL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="C1RT_UPM_URL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="C1RT_USER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="C1RT_PASSWORD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RTC_ENV_CONFIG", propOrder = {
    "rtcmode",
    "c1RTSAPIURL",
    "c1RTUPMURL",
    "c1RTUSER",
    "c1RTPASSWORD"
})
public class RTCENVCONFIG {

    @XmlElement(name = "RTC_MODE", required = true)
    protected String rtcmode;
    @XmlElement(name = "C1RT_SAPI_URL", required = true)
    protected String c1RTSAPIURL;
    @XmlElement(name = "C1RT_UPM_URL", required = true)
    protected String c1RTUPMURL;
    @XmlElement(name = "C1RT_USER", required = true)
    protected String c1RTUSER;
    @XmlElement(name = "C1RT_PASSWORD", required = true)
    protected String c1RTPASSWORD;

    /**
     * Gets the value of the rtcmode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRTCMODE() {
        return rtcmode;
    }

    /**
     * Sets the value of the rtcmode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRTCMODE(String value) {
        this.rtcmode = value;
    }

    /**
     * Gets the value of the c1RTSAPIURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getC1RTSAPIURL() {
        return c1RTSAPIURL;
    }

    /**
     * Sets the value of the c1RTSAPIURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setC1RTSAPIURL(String value) {
        this.c1RTSAPIURL = value;
    }

    /**
     * Gets the value of the c1RTUPMURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getC1RTUPMURL() {
        return c1RTUPMURL;
    }

    /**
     * Sets the value of the c1RTUPMURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setC1RTUPMURL(String value) {
        this.c1RTUPMURL = value;
    }

    /**
     * Gets the value of the c1RTUSER property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getC1RTUSER() {
        return c1RTUSER;
    }

    /**
     * Sets the value of the c1RTUSER property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setC1RTUSER(String value) {
        this.c1RTUSER = value;
    }

    /**
     * Gets the value of the c1RTPASSWORD property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getC1RTPASSWORD() {
        return c1RTPASSWORD;
    }

    /**
     * Sets the value of the c1RTPASSWORD property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setC1RTPASSWORD(String value) {
        this.c1RTPASSWORD = value;
    }

}
