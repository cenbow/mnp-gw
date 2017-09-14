
package org.rtcproject.ws.fxsync;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FX_SYNC_TYPE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FX_SYNC_TYPE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SYNC_REQ_DATA" type="{http://RTC.org/RTC_FX_SYNC_MSG}SYNC_REQ_DATA_TYPE"/>
 *         &lt;element name="FX_SYNC_SERVICE_DATA" type="{http://RTC.org/RTC_FX_SYNC_MSG}SYNC_SERVICE_DATA"/>
 *         &lt;element name="DATAVALIDATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VALIDATEMSG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="REF_OM_SERVICE_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="REF_OM_PKG_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="PROC_STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PROC_MSG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FX_SYNC_TYPE", propOrder = {
    "syncreqdata",
    "fxsyncservicedata",
    "datavalidate",
    "validatemsg",
    "refomserviceid",
    "refompkgid",
    "procstatus",
    "procmsg"
})
public class FXSYNCTYPE {

    @XmlElement(name = "SYNC_REQ_DATA", required = true)
    protected SYNCREQDATATYPE syncreqdata;
    @XmlElement(name = "FX_SYNC_SERVICE_DATA", required = true)
    protected SYNCSERVICEDATA fxsyncservicedata;
    @XmlElement(name = "DATAVALIDATE", required = true)
    protected String datavalidate;
    @XmlElement(name = "VALIDATEMSG", required = true)
    protected String validatemsg;
    @XmlElement(name = "REF_OM_SERVICE_ID")
    protected int refomserviceid;
    @XmlElement(name = "REF_OM_PKG_ID")
    protected int refompkgid;
    @XmlElement(name = "PROC_STATUS", required = true)
    protected String procstatus;
    @XmlElement(name = "PROC_MSG", required = true)
    protected String procmsg;

    /**
     * Gets the value of the syncreqdata property.
     * 
     * @return
     *     possible object is
     *     {@link SYNCREQDATATYPE }
     *     
     */
    public SYNCREQDATATYPE getSYNCREQDATA() {
        return syncreqdata;
    }

    /**
     * Sets the value of the syncreqdata property.
     * 
     * @param value
     *     allowed object is
     *     {@link SYNCREQDATATYPE }
     *     
     */
    public void setSYNCREQDATA(SYNCREQDATATYPE value) {
        this.syncreqdata = value;
    }

    /**
     * Gets the value of the fxsyncservicedata property.
     * 
     * @return
     *     possible object is
     *     {@link SYNCSERVICEDATA }
     *     
     */
    public SYNCSERVICEDATA getFXSYNCSERVICEDATA() {
        return fxsyncservicedata;
    }

    /**
     * Sets the value of the fxsyncservicedata property.
     * 
     * @param value
     *     allowed object is
     *     {@link SYNCSERVICEDATA }
     *     
     */
    public void setFXSYNCSERVICEDATA(SYNCSERVICEDATA value) {
        this.fxsyncservicedata = value;
    }

    /**
     * Gets the value of the datavalidate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDATAVALIDATE() {
        return datavalidate;
    }

    /**
     * Sets the value of the datavalidate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDATAVALIDATE(String value) {
        this.datavalidate = value;
    }

    /**
     * Gets the value of the validatemsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVALIDATEMSG() {
        return validatemsg;
    }

    /**
     * Sets the value of the validatemsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVALIDATEMSG(String value) {
        this.validatemsg = value;
    }

    /**
     * Gets the value of the refomserviceid property.
     * 
     */
    public int getREFOMSERVICEID() {
        return refomserviceid;
    }

    /**
     * Sets the value of the refomserviceid property.
     * 
     */
    public void setREFOMSERVICEID(int value) {
        this.refomserviceid = value;
    }

    /**
     * Gets the value of the refompkgid property.
     * 
     */
    public int getREFOMPKGID() {
        return refompkgid;
    }

    /**
     * Sets the value of the refompkgid property.
     * 
     */
    public void setREFOMPKGID(int value) {
        this.refompkgid = value;
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

}
