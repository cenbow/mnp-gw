
package org.rtcproject.ws.fxsync;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SYNC_REQ_DATA_TYPE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SYNC_REQ_DATA_TYPE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SYNCID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MSISDN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="REF_ID" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="REF_APP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MSG1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MSG2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="REF_APP_MODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SYNC_REQ_DATA_TYPE", propOrder = {
    "syncid",
    "msisdn",
    "refid",
    "refapp",
    "status",
    "msg1",
    "msg2",
    "refappmode"
})
public class SYNCREQDATATYPE {

    @XmlElement(name = "SYNCID", required = true)
    protected String syncid;
    @XmlElement(name = "MSISDN", required = true)
    protected String msisdn;
    @XmlElement(name = "REF_ID", required = true)
    protected BigDecimal refid;
    @XmlElement(name = "REF_APP", required = true)
    protected String refapp;
    @XmlElement(name = "STATUS", required = true)
    protected String status;
    @XmlElement(name = "MSG1", required = true)
    protected String msg1;
    @XmlElement(name = "MSG2", required = true)
    protected String msg2;
    @XmlElement(name = "REF_APP_MODE", required = true)
    protected String refappmode;

    /**
     * Gets the value of the syncid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSYNCID() {
        return syncid;
    }

    /**
     * Sets the value of the syncid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSYNCID(String value) {
        this.syncid = value;
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
     * Gets the value of the refid property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getREFID() {
        return refid;
    }

    /**
     * Sets the value of the refid property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setREFID(BigDecimal value) {
        this.refid = value;
    }

    /**
     * Gets the value of the refapp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREFAPP() {
        return refapp;
    }

    /**
     * Sets the value of the refapp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREFAPP(String value) {
        this.refapp = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTATUS() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTATUS(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the msg1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMSG1() {
        return msg1;
    }

    /**
     * Sets the value of the msg1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMSG1(String value) {
        this.msg1 = value;
    }

    /**
     * Gets the value of the msg2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMSG2() {
        return msg2;
    }

    /**
     * Sets the value of the msg2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMSG2(String value) {
        this.msg2 = value;
    }

    /**
     * Gets the value of the refappmode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREFAPPMODE() {
        return refappmode;
    }

    /**
     * Sets the value of the refappmode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREFAPPMODE(String value) {
        this.refappmode = value;
    }

}
