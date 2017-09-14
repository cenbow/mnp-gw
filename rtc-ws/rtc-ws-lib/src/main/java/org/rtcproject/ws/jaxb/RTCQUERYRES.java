
package org.rtcproject.ws.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RTC_QUERY_RES complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RTC_QUERY_RES">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MNP_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OMTRANS_I" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OMTRANS_A" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OMTRANS_T" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OM_MSG1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OM_MSG2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RTC_PROCESS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OM_IDEL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OM_ACTIVE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OM_TERMINATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RT_CREATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RT_ACTIVE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RT_TERMINATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MSISDN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CREATE_DATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="COMPLETE_DATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PORT_STATUS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RTC_QUERY_RES", propOrder = {
    "mnpid",
    "omtransi",
    "omtransa",
    "omtranst",
    "ommsg1",
    "ommsg2",
    "rtcprocess",
    "omidel",
    "omactive",
    "omterminate",
    "rtcreate",
    "rtactive",
    "rtterminate",
    "msisdn",
    "createdate",
    "completedate",
    "portstatus"
})
public class RTCQUERYRES {

    @XmlElement(name = "MNP_ID", required = true)
    protected String mnpid;
    @XmlElement(name = "OMTRANS_I")
    protected String omtransi;
    @XmlElement(name = "OMTRANS_A")
    protected String omtransa;
    @XmlElement(name = "OMTRANS_T")
    protected String omtranst;
    @XmlElement(name = "OM_MSG1")
    protected String ommsg1;
    @XmlElement(name = "OM_MSG2")
    protected String ommsg2;
    @XmlElement(name = "RTC_PROCESS")
    protected String rtcprocess;
    @XmlElement(name = "OM_IDEL")
    protected String omidel;
    @XmlElement(name = "OM_ACTIVE")
    protected String omactive;
    @XmlElement(name = "OM_TERMINATE")
    protected String omterminate;
    @XmlElement(name = "RT_CREATE")
    protected String rtcreate;
    @XmlElement(name = "RT_ACTIVE")
    protected String rtactive;
    @XmlElement(name = "RT_TERMINATE")
    protected String rtterminate;
    @XmlElement(name = "MSISDN")
    protected String msisdn;
    @XmlElement(name = "CREATE_DATE")
    protected String createdate;
    @XmlElement(name = "COMPLETE_DATE")
    protected String completedate;
    @XmlElement(name = "PORT_STATUS")
    protected String portstatus;

    /**
     * Gets the value of the mnpid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMNPID() {
        return mnpid;
    }

    /**
     * Sets the value of the mnpid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMNPID(String value) {
        this.mnpid = value;
    }

    /**
     * Gets the value of the omtransi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOMTRANSI() {
        return omtransi;
    }

    /**
     * Sets the value of the omtransi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOMTRANSI(String value) {
        this.omtransi = value;
    }

    /**
     * Gets the value of the omtransa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOMTRANSA() {
        return omtransa;
    }

    /**
     * Sets the value of the omtransa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOMTRANSA(String value) {
        this.omtransa = value;
    }

    /**
     * Gets the value of the omtranst property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOMTRANST() {
        return omtranst;
    }

    /**
     * Sets the value of the omtranst property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOMTRANST(String value) {
        this.omtranst = value;
    }

    /**
     * Gets the value of the ommsg1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOMMSG1() {
        return ommsg1;
    }

    /**
     * Sets the value of the ommsg1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOMMSG1(String value) {
        this.ommsg1 = value;
    }

    /**
     * Gets the value of the ommsg2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOMMSG2() {
        return ommsg2;
    }

    /**
     * Sets the value of the ommsg2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOMMSG2(String value) {
        this.ommsg2 = value;
    }

    /**
     * Gets the value of the rtcprocess property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRTCPROCESS() {
        return rtcprocess;
    }

    /**
     * Sets the value of the rtcprocess property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRTCPROCESS(String value) {
        this.rtcprocess = value;
    }

    /**
     * Gets the value of the omidel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOMIDEL() {
        return omidel;
    }

    /**
     * Sets the value of the omidel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOMIDEL(String value) {
        this.omidel = value;
    }

    /**
     * Gets the value of the omactive property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOMACTIVE() {
        return omactive;
    }

    /**
     * Sets the value of the omactive property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOMACTIVE(String value) {
        this.omactive = value;
    }

    /**
     * Gets the value of the omterminate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOMTERMINATE() {
        return omterminate;
    }

    /**
     * Sets the value of the omterminate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOMTERMINATE(String value) {
        this.omterminate = value;
    }

    /**
     * Gets the value of the rtcreate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRTCREATE() {
        return rtcreate;
    }

    /**
     * Sets the value of the rtcreate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRTCREATE(String value) {
        this.rtcreate = value;
    }

    /**
     * Gets the value of the rtactive property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRTACTIVE() {
        return rtactive;
    }

    /**
     * Sets the value of the rtactive property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRTACTIVE(String value) {
        this.rtactive = value;
    }

    /**
     * Gets the value of the rtterminate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRTTERMINATE() {
        return rtterminate;
    }

    /**
     * Sets the value of the rtterminate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRTTERMINATE(String value) {
        this.rtterminate = value;
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
     * Gets the value of the createdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCREATEDATE() {
        return createdate;
    }

    /**
     * Sets the value of the createdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCREATEDATE(String value) {
        this.createdate = value;
    }

    /**
     * Gets the value of the completedate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOMPLETEDATE() {
        return completedate;
    }

    /**
     * Sets the value of the completedate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOMPLETEDATE(String value) {
        this.completedate = value;
    }

    /**
     * Gets the value of the portstatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPORTSTATUS() {
        return portstatus;
    }

    /**
     * Sets the value of the portstatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPORTSTATUS(String value) {
        this.portstatus = value;
    }

}
