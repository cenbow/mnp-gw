
package org.rtcproject.ws.jaxb;


import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RTC_PORTIN complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RTC_PORTIN">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RTC_PORTIN_ACCOUNT" type="{http://rtcproject.org/MNP_WS}RTC_ACCOUNT_CREATE_TYPE"/>
 *         &lt;element name="PORTIN_OPERATOR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RTC_MNP_MODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RTC_USER" type="{http://rtcproject.org/MNP_WS}RTC_REQUEST_USER" minOccurs="0"/>
 *         &lt;element name="OM_ACCT_ID" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="PROC_STATUS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PROC_MSG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OM_TRANS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PROC2_STATUS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PROC2_MSG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PROC3_STATUS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PROC3_MSG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="C1RT_1_P_CODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="C1RT_1_E_CODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="C1RT_2_P_CODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="C1RT_2_E_CODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="C1RT_TOKEN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MNP_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="REQ_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="REPLY_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RTC_ACT_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="RTC_SERVICE_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="FIELD_VALIDATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FIELD_ERROR_MSG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OM_SERVICE_ID" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="OM_ACT_ID_STR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RTC_PORTIN", propOrder = {
    "rtcportinaccount",
    "portinoperator",
    "rtcmnpmode",
    "rtcuser",
    "omacctid",
    "procstatus",
    "procmsg",
    "omtrans",
    "proc2STATUS",
    "proc2MSG",
    "proc3STATUS",
    "proc3MSG",
    "c1RT1PCODE",
    "c1RT1ECODE",
    "c1RT2PCODE",
    "c1RT2ECODE",
    "c1RTTOKEN",
    "mnpid",
    "reqid",
    "replyid",
    "rtcactid",
    "rtcserviceid",
    "fieldvalidate",
    "fielderrormsg",
    "omserviceid",
    "omactidstr"
})
public class RTCPORTIN {

    @XmlElement(name = "RTC_PORTIN_ACCOUNT", required = true)
    protected RTCACCOUNTCREATETYPE rtcportinaccount;
    @XmlElement(name = "PORTIN_OPERATOR", required = true)
    protected String portinoperator;
    @XmlElement(name = "RTC_MNP_MODE", required = true)
    protected String rtcmnpmode;
    @XmlElement(name = "RTC_USER")
    protected RTCREQUESTUSER rtcuser;
    @XmlElement(name = "OM_ACCT_ID")
    protected BigDecimal omacctid;
    @XmlElement(name = "PROC_STATUS")
    protected String procstatus;
    @XmlElement(name = "PROC_MSG")
    protected String procmsg;
    @XmlElement(name = "OM_TRANS")
    protected String omtrans;
    @XmlElement(name = "PROC2_STATUS")
    protected String proc2STATUS;
    @XmlElement(name = "PROC2_MSG")
    protected String proc2MSG;
    @XmlElement(name = "PROC3_STATUS")
    protected String proc3STATUS;
    @XmlElement(name = "PROC3_MSG")
    protected String proc3MSG;
    @XmlElement(name = "C1RT_1_P_CODE")
    protected String c1RT1PCODE;
    @XmlElement(name = "C1RT_1_E_CODE")
    protected String c1RT1ECODE;
    @XmlElement(name = "C1RT_2_P_CODE")
    protected String c1RT2PCODE;
    @XmlElement(name = "C1RT_2_E_CODE")
    protected String c1RT2ECODE;
    @XmlElement(name = "C1RT_TOKEN")
    protected String c1RTTOKEN;
    @XmlElement(name = "MNP_ID")
    protected String mnpid;
    @XmlElement(name = "REQ_ID")
    protected String reqid;
    @XmlElement(name = "REPLY_ID", required = true)
    protected String replyid;
    @XmlElement(name = "RTC_ACT_ID")
    protected int rtcactid;
    @XmlElement(name = "RTC_SERVICE_ID")
    protected int rtcserviceid;
    @XmlElement(name = "FIELD_VALIDATE", required = true)
    protected String fieldvalidate;
    @XmlElement(name = "FIELD_ERROR_MSG")
    protected String fielderrormsg;
    @XmlElement(name = "OM_SERVICE_ID", required = true)
    protected BigDecimal omserviceid;
    @XmlElement(name = "OM_ACT_ID_STR", required = true)
    protected String omactidstr;

    /**
     * Gets the value of the rtcportinaccount property.
     * 
     * @return
     *     possible object is
     *     {@link RTCACCOUNTCREATETYPE }
     *     
     */
    public RTCACCOUNTCREATETYPE getRTCPORTINACCOUNT() {
        return rtcportinaccount;
    }

    /**
     * Sets the value of the rtcportinaccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link RTCACCOUNTCREATETYPE }
     *     
     */
    public void setRTCPORTINACCOUNT(RTCACCOUNTCREATETYPE value) {
        this.rtcportinaccount = value;
    }

    /**
     * Gets the value of the portinoperator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPORTINOPERATOR() {
        return portinoperator;
    }

    /**
     * Sets the value of the portinoperator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPORTINOPERATOR(String value) {
        this.portinoperator = value;
    }

    /**
     * Gets the value of the rtcmnpmode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRTCMNPMODE() {
        return rtcmnpmode;
    }

    /**
     * Sets the value of the rtcmnpmode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRTCMNPMODE(String value) {
        this.rtcmnpmode = value;
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

    /**
     * Gets the value of the omacctid property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOMACCTID() {
        return omacctid;
    }

    /**
     * Sets the value of the omacctid property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOMACCTID(BigDecimal value) {
        this.omacctid = value;
    }

    /**
     * Gets the value of the procstatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPROCSTATUS() {
        return procstatus;
    }

    /**
     * Sets the value of the procstatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPROCSTATUS(String value) {
        this.procstatus = value;
    }

    /**
     * Gets the value of the procmsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPROCMSG() {
        return procmsg;
    }

    /**
     * Sets the value of the procmsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPROCMSG(String value) {
        this.procmsg = value;
    }

    /**
     * Gets the value of the omtrans property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOMTRANS() {
        return omtrans;
    }

    /**
     * Sets the value of the omtrans property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOMTRANS(String value) {
        this.omtrans = value;
    }

    /**
     * Gets the value of the proc2STATUS property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPROC2STATUS() {
        return proc2STATUS;
    }

    /**
     * Sets the value of the proc2STATUS property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPROC2STATUS(String value) {
        this.proc2STATUS = value;
    }

    /**
     * Gets the value of the proc2MSG property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPROC2MSG() {
        return proc2MSG;
    }

    /**
     * Sets the value of the proc2MSG property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPROC2MSG(String value) {
        this.proc2MSG = value;
    }

    /**
     * Gets the value of the proc3STATUS property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPROC3STATUS() {
        return proc3STATUS;
    }

    /**
     * Sets the value of the proc3STATUS property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPROC3STATUS(String value) {
        this.proc3STATUS = value;
    }

    /**
     * Gets the value of the proc3MSG property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPROC3MSG() {
        return proc3MSG;
    }

    /**
     * Sets the value of the proc3MSG property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPROC3MSG(String value) {
        this.proc3MSG = value;
    }

    /**
     * Gets the value of the c1RT1PCODE property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getC1RT1PCODE() {
        return c1RT1PCODE;
    }

    /**
     * Sets the value of the c1RT1PCODE property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setC1RT1PCODE(String value) {
        this.c1RT1PCODE = value;
    }

    /**
     * Gets the value of the c1RT1ECODE property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getC1RT1ECODE() {
        return c1RT1ECODE;
    }

    /**
     * Sets the value of the c1RT1ECODE property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setC1RT1ECODE(String value) {
        this.c1RT1ECODE = value;
    }

    /**
     * Gets the value of the c1RT2PCODE property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getC1RT2PCODE() {
        return c1RT2PCODE;
    }

    /**
     * Sets the value of the c1RT2PCODE property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setC1RT2PCODE(String value) {
        this.c1RT2PCODE = value;
    }

    /**
     * Gets the value of the c1RT2ECODE property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getC1RT2ECODE() {
        return c1RT2ECODE;
    }

    /**
     * Sets the value of the c1RT2ECODE property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setC1RT2ECODE(String value) {
        this.c1RT2ECODE = value;
    }

    /**
     * Gets the value of the c1RTTOKEN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getC1RTTOKEN() {
        return c1RTTOKEN;
    }

    /**
     * Sets the value of the c1RTTOKEN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setC1RTTOKEN(String value) {
        this.c1RTTOKEN = value;
    }

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
     * Gets the value of the reqid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREQID() {
        return reqid;
    }

    /**
     * Sets the value of the reqid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREQID(String value) {
        this.reqid = value;
    }

    /**
     * Gets the value of the replyid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREPLYID() {
        return replyid;
    }

    /**
     * Sets the value of the replyid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREPLYID(String value) {
        this.replyid = value;
    }

    /**
     * Gets the value of the rtcactid property.
     * 
     */
    public int getRTCACTID() {
        return rtcactid;
    }

    /**
     * Sets the value of the rtcactid property.
     * 
     */
    public void setRTCACTID(int value) {
        this.rtcactid = value;
    }

    /**
     * Gets the value of the rtcserviceid property.
     * 
     */
    public int getRTCSERVICEID() {
        return rtcserviceid;
    }

    /**
     * Sets the value of the rtcserviceid property.
     * 
     */
    public void setRTCSERVICEID(int value) {
        this.rtcserviceid = value;
    }

    /**
     * Gets the value of the fieldvalidate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFIELDVALIDATE() {
        return fieldvalidate;
    }

    /**
     * Sets the value of the fieldvalidate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFIELDVALIDATE(String value) {
        this.fieldvalidate = value;
    }

    /**
     * Gets the value of the fielderrormsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFIELDERRORMSG() {
        return fielderrormsg;
    }

    /**
     * Sets the value of the fielderrormsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFIELDERRORMSG(String value) {
        this.fielderrormsg = value;
    }

    /**
     * Gets the value of the omserviceid property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOMSERVICEID() {
        return omserviceid;
    }

    /**
     * Sets the value of the omserviceid property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOMSERVICEID(BigDecimal value) {
        this.omserviceid = value;
    }

    /**
     * Gets the value of the omactidstr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOMACTIDSTR() {
        return omactidstr;
    }

    /**
     * Sets the value of the omactidstr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOMACTIDSTR(String value) {
        this.omactidstr = value;
    }

}
