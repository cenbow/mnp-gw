
package org.rtcproject.ws.fxsync;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FX_SYNC_REQ_TYPE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FX_SYNC_REQ_TYPE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SYNC_TRANS_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MSISDN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="REF_APP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FX_SYNC_REQ_TYPE", propOrder = {
    "synctransid",
    "msisdn",
    "refapp"
})
public class FXSYNCREQTYPE {

    @XmlElement(name = "SYNC_TRANS_ID", required = true)
    protected String synctransid;
    @XmlElement(name = "MSISDN", required = true)
    protected String msisdn;
    @XmlElement(name = "REF_APP", required = true)
    protected String refapp;

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

}
