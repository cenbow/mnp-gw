
package org.rtcproject.ws.fxsync;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FX_SYNC_REQ_RES_TYPE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FX_SYNC_REQ_RES_TYPE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SYNC_TRANS_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SYNC_MSG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FX_SYNC_REQ_RES_TYPE", propOrder = {
    "synctransid",
    "status",
    "syncmsg"
})
public class FXSYNCREQRESTYPE {

    @XmlElement(name = "SYNC_TRANS_ID", required = true)
    protected String synctransid;
    @XmlElement(name = "STATUS", required = true)
    protected String status;
    @XmlElement(name = "SYNC_MSG", required = true)
    protected String syncmsg;

    /**
     * Gets the value of the synctransid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSYNCTRANSID() {
        return synctransid;
    }

    /**
     * Sets the value of the synctransid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSYNCTRANSID(String value) {
        this.synctransid = value;
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
     * Gets the value of the syncmsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSYNCMSG() {
        return syncmsg;
    }

    /**
     * Sets the value of the syncmsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSYNCMSG(String value) {
        this.syncmsg = value;
    }

}
