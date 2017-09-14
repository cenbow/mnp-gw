
package org.rtcproject.ws.jaxb;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DL_PROCESS_TYPE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DL_PROCESS_TYPE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DL_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="C1RT_PROCESS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OMTRANS_I" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OMTRANS_A" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OMTRANS_T" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OM_MSG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OM_STATUS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="C1RT_P_CODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="C1RT_E_CODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="C1RT_P_MSG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DL_PROCESS_TYPE", propOrder = {
    "dlid",
    "c1RTPROCESS",
    "omtransi",
    "omtransa",
    "omtranst",
    "ommsg",
    "omstatus",
    "c1RTPCODE",
    "c1RTECODE",
    "c1RTPMSG"
})
public class DLPROCESSTYPE {

    @XmlElement(name = "DL_ID")
    protected String dlid;
    @XmlElement(name = "C1RT_PROCESS")
    protected String c1RTPROCESS;
    @XmlElement(name = "OMTRANS_I")
    protected String omtransi;
    @XmlElement(name = "OMTRANS_A")
    protected String omtransa;
    @XmlElement(name = "OMTRANS_T")
    protected String omtranst;
    @XmlElement(name = "OM_MSG")
    protected String ommsg;
    @XmlElement(name = "OM_STATUS")
    protected String omstatus;
    @XmlElement(name = "C1RT_P_CODE")
    protected String c1RTPCODE;
    @XmlElement(name = "C1RT_E_CODE")
    protected String c1RTECODE;
    @XmlElement(name = "C1RT_P_MSG", required = true)
    protected String c1RTPMSG;

    /**
     * Gets the value of the dlid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDLID() {
        return dlid;
    }

    /**
     * Sets the value of the dlid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDLID(String value) {
        this.dlid = value;
    }

    /**
     * Gets the value of the c1RTPROCESS property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getC1RTPROCESS() {
        return c1RTPROCESS;
    }

    /**
     * Sets the value of the c1RTPROCESS property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setC1RTPROCESS(String value) {
        this.c1RTPROCESS = value;
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
     * Gets the value of the ommsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOMMSG() {
        return ommsg;
    }

    /**
     * Sets the value of the ommsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOMMSG(String value) {
        this.ommsg = value;
    }

    /**
     * Gets the value of the omstatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOMSTATUS() {
        return omstatus;
    }

    /**
     * Sets the value of the omstatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOMSTATUS(String value) {
        this.omstatus = value;
    }

    /**
     * Gets the value of the c1RTPCODE property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getC1RTPCODE() {
        return c1RTPCODE;
    }

    /**
     * Sets the value of the c1RTPCODE property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setC1RTPCODE(String value) {
        this.c1RTPCODE = value;
    }

    /**
     * Gets the value of the c1RTECODE property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getC1RTECODE() {
        return c1RTECODE;
    }

    /**
     * Sets the value of the c1RTECODE property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setC1RTECODE(String value) {
        this.c1RTECODE = value;
    }

    /**
     * Gets the value of the c1RTPMSG property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getC1RTPMSG() {
        return c1RTPMSG;
    }

    /**
     * Sets the value of the c1RTPMSG property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setC1RTPMSG(String value) {
        this.c1RTPMSG = value;
    }

}
