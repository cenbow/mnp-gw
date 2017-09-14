
package org.rtcproject.ws.jaxb;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RTC_DEALEROUT complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RTC_DEALEROUT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RTC_DEALEROUT_SERVICE" type="{http://rtcproject.org/MNP_WS}RTC_TERMINATE_SERVICE"/>
 *         &lt;element name="CRM_DEALER_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DEALER_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SHOP_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "RTC_DEALEROUT", propOrder = {
    "rtcdealeroutservice",
    "crmdealercode",
    "dealername",
    "shopname",
    "rtcuser"
})
public class RTCDEALEROUT {

    @XmlElement(name = "RTC_DEALEROUT_SERVICE", required = true)
    protected RTCTERMINATESERVICE rtcdealeroutservice;
    @XmlElement(name = "CRM_DEALER_CODE", required = true)
    protected String crmdealercode;
    @XmlElement(name = "DEALER_NAME", required = true)
    protected String dealername;
    @XmlElement(name = "SHOP_NAME", required = true)
    protected String shopname;
    @XmlElement(name = "RTC_USER", required = true)
    protected RTCREQUESTUSER rtcuser;

    /**
     * Gets the value of the rtcdealeroutservice property.
     * 
     * @return
     *     possible object is
     *     {@link RTCTERMINATESERVICE }
     *     
     */
    public RTCTERMINATESERVICE getRTCDEALEROUTSERVICE() {
        return rtcdealeroutservice;
    }

    /**
     * Sets the value of the rtcdealeroutservice property.
     * 
     * @param value
     *     allowed object is
     *     {@link RTCTERMINATESERVICE }
     *     
     */
    public void setRTCDEALEROUTSERVICE(RTCTERMINATESERVICE value) {
        this.rtcdealeroutservice = value;
    }

    /**
     * Gets the value of the crmdealercode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
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
    public void setSHOPNAME(String value) {
        this.shopname = value;
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
