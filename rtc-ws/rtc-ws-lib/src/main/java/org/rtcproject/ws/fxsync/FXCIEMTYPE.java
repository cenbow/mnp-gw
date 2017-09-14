
package org.rtcproject.ws.fxsync;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FX_CIEM_TYPE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FX_CIEM_TYPE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SUBSCR_NO" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="SUBSCR_NO_RESETS" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="ACTIVE_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FX_CIEM_TYPE", propOrder = {
    "subscrno",
    "subscrnoresets",
    "activedate"
})
public class FXCIEMTYPE {

    @XmlElement(name = "SUBSCR_NO", required = true)
    protected BigDecimal subscrno;
    @XmlElement(name = "SUBSCR_NO_RESETS", required = true)
    protected BigDecimal subscrnoresets;
    @XmlElement(name = "ACTIVE_DATE", required = true)
    protected String activedate;

    /**
     * Gets the value of the subscrno property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSUBSCRNO() {
        return subscrno;
    }

    /**
     * Sets the value of the subscrno property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSUBSCRNO(BigDecimal value) {
        this.subscrno = value;
    }

    /**
     * Gets the value of the subscrnoresets property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSUBSCRNORESETS() {
        return subscrnoresets;
    }

    /**
     * Sets the value of the subscrnoresets property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSUBSCRNORESETS(BigDecimal value) {
        this.subscrnoresets = value;
    }

    /**
     * Gets the value of the activedate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getACTIVEDATE() {
        return activedate;
    }

    /**
     * Sets the value of the activedate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setACTIVEDATE(String value) {
        this.activedate = value;
    }

}
