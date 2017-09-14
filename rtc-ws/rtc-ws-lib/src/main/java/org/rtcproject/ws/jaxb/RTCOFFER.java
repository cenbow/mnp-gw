
package org.rtcproject.ws.jaxb;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RTC_OFFER complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RTC_OFFER">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OFFER_ID" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="OFFER_LEVEL" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DATE_ACTIVE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RTC_OFFER", propOrder = {
    "offerid",
    "offerlevel",
    "dateactive"
})
public class RTCOFFER {

    @XmlElement(name = "OFFER_ID", required = true)
    protected BigDecimal offerid;
    @XmlElement(name = "OFFER_LEVEL")
    protected int offerlevel;
    @XmlElement(name = "DATE_ACTIVE", required = true)
    protected String dateactive;

    /**
     * Gets the value of the offerid property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOFFERID() {
        return offerid;
    }

    /**
     * Sets the value of the offerid property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOFFERID(BigDecimal value) {
        this.offerid = value;
    }

    /**
     * Gets the value of the offerlevel property.
     * 
     */
    public int getOFFERLEVEL() {
        return offerlevel;
    }

    /**
     * Sets the value of the offerlevel property.
     * 
     */
    public void setOFFERLEVEL(int value) {
        this.offerlevel = value;
    }

    /**
     * Gets the value of the dateactive property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDATEACTIVE() {
        return dateactive;
    }

    /**
     * Sets the value of the dateactive property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDATEACTIVE(String value) {
        this.dateactive = value;
    }

}
