
package org.rtcproject.ws.jaxb;


import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DLIN_PROCESS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DLIN_PROCESS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RTC_DEALER_IN_ACCOUNT" type="{http://rtcproject.org/MNP_WS}RTC_ACCOUNT_CREATE_TYPE"/>
 *         &lt;element name="CRM_DEALER_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DEALER_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SHOP_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="USERNAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RTC_USER" type="{http://rtcproject.org/MNP_WS}RTC_REQUEST_USER"/>
 *         &lt;element name="OM_ACCT_ID" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="PROC_STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PROC_MSG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OM_TRANS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PROC2_STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PROC2_MSG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PROC3_STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PROC3_MSG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="C1RT_1_P_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="C1RT_1_E_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="C1RT_2_P_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="C1RT_2_E_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DL_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="REPLY_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RTC_ACCT_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RTC_SERVICE_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RTC_DL_MODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OM_ACCT_ID_STR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FIELD_VALIDATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FIELD_ERROR_MSG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DLIN_PROCESS", propOrder = {
    "rtcdealerinaccount",
    "crmdealercode",
    "dealername",
    "shopname",
    "username",
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
    "dlid",
    "replyid",
    "rtcacctid",
    "rtcserviceid",
    "rtcdlmode",
    "omacctidstr",
    "fieldvalidate",
    "fielderrormsg"
})
public class DLINPROCESS {

    @XmlElement(name = "RTC_DEALER_IN_ACCOUNT", required = true)
    protected RTCACCOUNTCREATETYPE rtcdealerinaccount;
    @XmlElement(name = "CRM_DEALER_CODE", required = true)
    protected String crmdealercode;
    @XmlElement(name = "DEALER_NAME", required = true)
    protected String dealername;
    @XmlElement(name = "SHOP_NAME", required = true)
    protected String shopname;
    @XmlElement(name = "USERNAME", required = true)
    protected String username;
    @XmlElement(name = "RTC_USER", required = true)
    protected RTCREQUESTUSER rtcuser;
    @XmlElement(name = "OM_ACCT_ID", required = true)
    protected BigDecimal omacctid;
    @XmlElement(name = "PROC_STATUS", required = true)
    protected String procstatus;
    @XmlElement(name = "PROC_MSG", required = true)
    protected String procmsg;
    @XmlElement(name = "OM_TRANS", required = true)
    protected String omtrans;
    @XmlElement(name = "PROC2_STATUS", required = true)
    protected String proc2STATUS;
    @XmlElement(name = "PROC2_MSG", required = true)
    protected String proc2MSG;
    @XmlElement(name = "PROC3_STATUS", required = true)
    protected String proc3STATUS;
    @XmlElement(name = "PROC3_MSG", required = true)
    protected String proc3MSG;
    @XmlElement(name = "C1RT_1_P_CODE", required = true)
    protected String c1RT1PCODE;
    @XmlElement(name = "C1RT_1_E_CODE", required = true)
    protected String c1RT1ECODE;
    @XmlElement(name = "C1RT_2_P_CODE", required = true)
    protected String c1RT2PCODE;
    @XmlElement(name = "C1RT_2_E_CODE", required = true)
    protected String c1RT2ECODE;
    @XmlElement(name = "DL_ID", required = true)
    protected String dlid;
    @XmlElement(name = "REPLY_ID", required = true)
    protected String replyid;
    @XmlElement(name = "RTC_ACCT_ID", required = true)
    protected String rtcacctid;
    @XmlElement(name = "RTC_SERVICE_ID", required = true)
    protected String rtcserviceid;
    @XmlElement(name = "RTC_DL_MODE", required = true)
    protected String rtcdlmode;
    @XmlElement(name = "OM_ACCT_ID_STR", required = true)
    protected String omacctidstr;
    @XmlElement(name = "FIELD_VALIDATE", required = true)
    protected String fieldvalidate;
    @XmlElement(name = "FIELD_ERROR_MSG", required = true)
    protected String fielderrormsg;

    /**
     * Gets the value of the rtcdealerinaccount property.
     * 
     * @return
     *     possible object is
     *     {@link RTCACCOUNTCREATETYPE }
     *     
     */
    public RTCACCOUNTCREATETYPE getRTCDEALERINACCOUNT() {
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
    public void setRTCDEALERINACCOUNT(RTCACCOUNTCREATETYPE value) {
        this.rtcdealerinaccount = value;
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
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
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
    public void setUSERNAME(String value) {
        this.username = value;
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
     * Gets the value of the rtcacctid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRTCACCTID() {
        return rtcacctid;
    }

    /**
     * Sets the value of the rtcacctid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRTCACCTID(String value) {
        this.rtcacctid = value;
    }

    /**
     * Gets the value of the rtcserviceid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRTCSERVICEID() {
        return rtcserviceid;
    }

    /**
     * Sets the value of the rtcserviceid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRTCSERVICEID(String value) {
        this.rtcserviceid = value;
    }

    /**
     * Gets the value of the rtcdlmode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
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
    public void setRTCDLMODE(String value) {
        this.rtcdlmode = value;
    }

    /**
     * Gets the value of the omacctidstr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOMACCTIDSTR() {
        return omacctidstr;
    }

    /**
     * Sets the value of the omacctidstr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOMACCTIDSTR(String value) {
        this.omacctidstr = value;
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

}
