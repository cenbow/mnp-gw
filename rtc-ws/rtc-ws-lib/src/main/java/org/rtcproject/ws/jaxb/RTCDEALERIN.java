
package org.rtcproject.ws.jaxb;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.rtcproject.ws.RtcActivateServiceInputObj;


/**
 * <p>Java class for RTC_DEALERIN complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RTC_DEALERIN">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RTC_DL_MODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CRM_DEALER_CODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DEALER_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SHOP_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="USERNAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RTC_DEALER_IN_ACCOUNT" type="{http://rtcproject.org/MNP_WS}RTC_ACCOUNT_CREATE_TYPE"/>
 *         &lt;element name="RTC_USER" type="{http://rtcproject.org/MNP_WS}RTC_REQUEST_USER" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RTC_DEALERIN", propOrder = {
    "rtcdlmode",
    "crmdealercode",
    "dealername",
    "shopname",
    "username",
    "rtcdealerinaccount",
    "rtcuser"
})
@XmlRootElement(name = "RTC_DEALERIN", namespace = "")
public class RTCDEALERIN implements RtcActivateServiceInputObj {

    @XmlElement(name = "RTC_DL_MODE", required = true)
    protected String rtcdlmode;
    @XmlElement(name = "CRM_DEALER_CODE")
    protected String crmdealercode;
    @XmlElement(name = "DEALER_NAME")
    protected String dealername;
    @XmlElement(name = "SHOP_NAME")
    protected String shopname;
    @XmlElement(name = "USERNAME")
    protected String username;
    @XmlElement(name = "RTC_DEALER_IN_ACCOUNT", required = true)
    protected RTCACCOUNTCREATETYPE rtcdealerinaccount;
    @XmlElement(name = "RTC_USER")
    protected RTCREQUESTUSER rtcuser;

    /**
     * Gets the value of the rtcdlmode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Override
    public String getRTCDLMODE() {
        return rtcdlmode;
    }

    /**
     * Sets the value of the rtcdlmode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Override
    public void setRTCDLMODE(String value) {
        this.rtcdlmode = value;
    }

    /**
     * Gets the value of the crmdealercode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Override
    public String getCRMDEALERCODE() {
        return crmdealercode;
    }

    /**
     * Sets the value of the crmdealercode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Override
    public void setCRMDEALERCODE(String value) {
        this.crmdealercode = value;
    }

    /**
     * Gets the value of the dealername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Override
    public String getDEALERNAME() {
        return dealername;
    }

    /**
     * Sets the value of the dealername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Override
    public void setDEALERNAME(String value) {
        this.dealername = value;
    }

    /**
     * Gets the value of the shopname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Override
    public String getSHOPNAME() {
        return shopname;
    }

    /**
     * Sets the value of the shopname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Override
    public void setSHOPNAME(String value) {
        this.shopname = value;
    }

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Override
    public String getUSERNAME() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Override
    public void setUSERNAME(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the rtcdealerinaccount property.
     * 
     * @return
     *     possible object is
     *     {@link RTCACCOUNTCREATETYPE }
     *     
     */
    @Override
    public RTCACCOUNTCREATETYPE getRTCCREATEINACCOUNT() {
        return rtcdealerinaccount;
    }

    /**
     * Sets the value of the rtcdealerinaccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link RTCACCOUNTCREATETYPE }
     *     
     */
    @Override
    public void setRTCCREATEINACCOUNT(RTCACCOUNTCREATETYPE value) {
        this.rtcdealerinaccount = value;
    }

    /**
     * Gets the value of the rtcuser property.
     * 
     * @return
     *     possible object is
     *     {@link RTCREQUESTUSER }
     *     
     */
    @Override
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
    @Override
    public void setRTCUSER(RTCREQUESTUSER value) {
        this.rtcuser = value;
    }

    @Override
    public String getPORTINOPERATOR() {
        return null;
    }

    @Override
    public void setPORTINOPERATOR(String value) {
    }

    @Override
    public Integer getRTCMNPMODE() {
        return null;
    }

    @Override
    public void setRTCMNPMODE(Integer value) {
    }

}
