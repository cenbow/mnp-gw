
package org.rtcproject.ws.jaxb;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PORTOUT_PROCESS_TYPE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PORTOUT_PROCESS_TYPE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OM_TRANS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MNP_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PROC_MSG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PORT_OUT" type="{http://rtcproject.org/MNP_WS}RTC_PORTOUT"/>
 *         &lt;element name="PROC_STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="REPLY_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PORTOUT_PROCESS_TYPE", propOrder = {
    "omtrans",
    "mnpid",
    "procmsg",
    "portout",
    "procstatus",
    "replyid"
})
public class PORTOUTPROCESSTYPE {

    @XmlElement(name = "OM_TRANS", required = true)
    protected String omtrans;
    @XmlElement(name = "MNP_ID", required = true)
    protected String mnpid;
    @XmlElement(name = "PROC_MSG", required = true)
    protected String procmsg;
    @XmlElement(name = "PORT_OUT", required = true)
    protected RTCPORTOUT portout;
    @XmlElement(name = "PROC_STATUS", required = true)
    protected String procstatus;
    @XmlElement(name = "REPLY_ID", required = true)
    protected String replyid;

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
     * Gets the value of the portout property.
     * 
     * @return
     *     possible object is
     *     {@link RTCPORTOUT }
     *     
     */
    public RTCPORTOUT getPORTOUT() {
        return portout;
    }

    /**
     * Sets the value of the portout property.
     * 
     * @param value
     *     allowed object is
     *     {@link RTCPORTOUT }
     *     
     */
    public void setPORTOUT(RTCPORTOUT value) {
        this.portout = value;
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

}
