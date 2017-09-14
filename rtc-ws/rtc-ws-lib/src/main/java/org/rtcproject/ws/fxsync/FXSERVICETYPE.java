
package org.rtcproject.ws.fxsync;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FX_SERVICE_TYPE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FX_SERVICE_TYPE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PARENT_ACCOUNT" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FX_SERVICE_TYPE", propOrder = {
    "parentaccount"
})
public class FXSERVICETYPE {

    @XmlElement(name = "PARENT_ACCOUNT", required = true)
    protected BigDecimal parentaccount;

    /**
     * Gets the value of the parentaccount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPARENTACCOUNT() {
        return parentaccount;
    }

    /**
     * Sets the value of the parentaccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPARENTACCOUNT(BigDecimal value) {
        this.parentaccount = value;
    }

}
