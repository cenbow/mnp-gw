
package org.rtcproject.ws.jaxb;


import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RTC_SERVICE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RTC_SERVICE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SERVICE_TYPE" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DATE_ACTIVE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SERVICE_STATUS" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MSISDN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IMSI" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ICCID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RTC_OFFER" type="{http://rtcproject.org/MNP_WS}RTC_OFFER" maxOccurs="200"/>
 *         &lt;element name="INTERNAL_SALE_REP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RTC_SERVICE", propOrder = {
    "servicetype",
    "dateactive",
    "servicestatus",
    "msisdn",
    "imsi",
    "iccid",
    "rtcoffer",
    "internalsalerep"
})
public class RTCSERVICE {

    @XmlElement(name = "SERVICE_TYPE")
    protected int servicetype;
    @XmlElement(name = "DATE_ACTIVE", required = true)
    protected String dateactive;
    @XmlElement(name = "SERVICE_STATUS")
    protected int servicestatus;
    @XmlElement(name = "MSISDN", required = true)
    protected String msisdn;
    @XmlElement(name = "IMSI", required = true)
    protected String imsi;
    @XmlElement(name = "ICCID", required = true)
    protected String iccid;
    @XmlElement(name = "RTC_OFFER", required = true)
    protected List<RTCOFFER> rtcoffer;
    @XmlElement(name = "INTERNAL_SALE_REP", required = true)
    protected String internalsalerep;

    /**
     * Gets the value of the servicetype property.
     * 
     */
    public int getSERVICETYPE() {
        return servicetype;
    }

    /**
     * Sets the value of the servicetype property.
     * 
     */
    public void setSERVICETYPE(int value) {
        this.servicetype = value;
    }

    /**
     * Gets the value of the dateactive property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDATEACTIVE() {
        return dateactive;
    }

    /**
     * Sets the value of the dateactive property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDATEACTIVE(String value) {
        this.dateactive = value;
    }

    /**
     * Gets the value of the servicestatus property.
     * 
     */
    public int getSERVICESTATUS() {
        return servicestatus;
    }

    /**
     * Sets the value of the servicestatus property.
     * 
     */
    public void setSERVICESTATUS(int value) {
        this.servicestatus = value;
    }

    /**
     * Gets the value of the msisdn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMSISDN() {
        return msisdn;
    }

    /**
     * Sets the value of the msisdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMSISDN(String value) {
        this.msisdn = value;
    }

    /**
     * Gets the value of the imsi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIMSI() {
        return imsi;
    }

    /**
     * Sets the value of the imsi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIMSI(String value) {
        this.imsi = value;
    }

    /**
     * Gets the value of the iccid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getICCID() {
        return iccid;
    }

    /**
     * Sets the value of the iccid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setICCID(String value) {
        this.iccid = value;
    }

    /**
     * Gets the value of the rtcoffer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rtcoffer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRTCOFFER().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RTCOFFER }
     * 
     * 
     */
    public List<RTCOFFER> getRTCOFFER() {
        if (rtcoffer == null) {
            rtcoffer = new ArrayList<RTCOFFER>();
        }
        return this.rtcoffer;
    }

    /**
     * Gets the value of the internalsalerep property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINTERNALSALEREP() {
        return internalsalerep;
    }

    /**
     * Sets the value of the internalsalerep property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINTERNALSALEREP(String value) {
        this.internalsalerep = value;
    }

}
