package org.rtcproject.ws.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.rtcproject.ws.RtcActivateServiceInputObj;

/**
 * <p>
 * Java class for RTC_PORTIN_WS complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="RTC_PORTIN_WS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PORTIN_OPERATOR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RTC_MNP_MODE" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="RTC_PORTIN_ACCOUNT" type="{http://rtcproject.org/MNP_WS}RTC_ACCOUNT_CREATE_TYPE"/>
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
@XmlType(name = "RTC_PORTIN_WS", propOrder = {
    "portinoperator",
    "rtcmnpmode",
    "rtcportinaccount",
    "rtcuser"
})
@XmlRootElement(name = "RTC_PORTIN", namespace = "")
public class RTCPORTINWS implements RtcActivateServiceInputObj {

    @XmlElement(name = "PORTIN_OPERATOR", required = true)
    protected String portinoperator;
    @XmlElement(name = "RTC_MNP_MODE")
    protected Integer rtcmnpmode;
    @XmlElement(name = "RTC_PORTIN_ACCOUNT", required = true)
    protected RTCACCOUNTCREATETYPE rtcportinaccount;
    @XmlElement(name = "RTC_USER", required = true)
    protected RTCREQUESTUSER rtcuser;

    /**
     * Gets the value of the portinoperator property.
     *
     * @return possible object is {@link String }
     *
     */
    @Override
    public String getPORTINOPERATOR() {
        return portinoperator;
    }

    /**
     * Sets the value of the portinoperator property.
     *
     * @param value allowed object is {@link String }
     *
     */
    @Override
    public void setPORTINOPERATOR(String value) {
        this.portinoperator = value;
    }

    /**
     * Gets the value of the rtcmnpmode property.
     *
     */
    @Override
    public Integer getRTCMNPMODE() {
        return rtcmnpmode;
    }

    /**
     * Sets the value of the rtcmnpmode property.
     *
     */
    @Override
    public void setRTCMNPMODE(Integer value) {
        this.rtcmnpmode = value;
    }

    /**
     * Gets the value of the rtcportinaccount property.
     *
     * @return possible object is {@link RTCACCOUNTCREATETYPE }
     *
     */
    @Override
    public RTCACCOUNTCREATETYPE getRTCCREATEINACCOUNT() {
        return rtcportinaccount;
    }

    /**
     * Sets the value of the rtcportinaccount property.
     *
     * @param value allowed object is {@link RTCACCOUNTCREATETYPE }
     *
     */
    @Override
    public void setRTCCREATEINACCOUNT(RTCACCOUNTCREATETYPE value) {
        this.rtcportinaccount = value;
    }

    /**
     * Gets the value of the rtcuser property.
     *
     * @return possible object is {@link RTCREQUESTUSER }
     *
     */
    @Override
    public RTCREQUESTUSER getRTCUSER() {
        return rtcuser;
    }

    /**
     * Sets the value of the rtcuser property.
     *
     * @param value allowed object is {@link RTCREQUESTUSER }
     *
     */
    public void setRTCUSER(RTCREQUESTUSER value) {
        this.rtcuser = value;
    }

    @Override
    public String getRTCDLMODE() {
        return null;
    }

    @Override
    public void setRTCDLMODE(String value) {
    }

    @Override
    public String getCRMDEALERCODE() {
        return null;
    }

    @Override
    public void setCRMDEALERCODE(String value) {
    }

    @Override
    public String getDEALERNAME() {
        return null;
    }

    @Override
    public void setDEALERNAME(String value) {
    }

    @Override
    public String getSHOPNAME() {
        return null;
    }

    @Override
    public void setSHOPNAME(String value) {
    }

    @Override
    public String getUSERNAME() {
        return null;
    }

    @Override
    public void setUSERNAME(String value) {
    }

}
