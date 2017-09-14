
package org.rtcproject.ws.jaxb;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RTC_TERMINATE_SERVICE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RTC_TERMINATE_SERVICE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MSISDN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DELETE_SERVICE" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="DATE_INACTIVE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RTC_TERMINATE_SERVICE", propOrder = {
    "msisdn",
    "deleteservice",
    "dateinactive"
})
public class RTCTERMINATESERVICE {

    @XmlElement(name = "MSISDN", required = true)
    protected String msisdn;
    @XmlElement(name = "DELETE_SERVICE")
    protected boolean deleteservice;
    @XmlElement(name = "DATE_INACTIVE", required = true)
    protected String dateinactive;

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
     * Gets the value of the deleteservice property.
     * 
     */
    public boolean isDELETESERVICE() {
        return deleteservice;
    }

    /**
     * Sets the value of the deleteservice property.
     * 
     */
    public void setDELETESERVICE(boolean value) {
        this.deleteservice = value;
    }

    /**
     * Gets the value of the dateinactive property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDATEINACTIVE() {
        return dateinactive;
    }

    /**
     * Sets the value of the dateinactive property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDATEINACTIVE(String value) {
        this.dateinactive = value;
    }

}
