
package org.rtcproject.ws.jaxb;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for complexType1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="complexType1">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OMTRANS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "complexType1", propOrder = {
    "omtrans",
    "mode"
})
public class ComplexType1 {

    @XmlElement(name = "OMTRANS", required = true)
    protected String omtrans;
    @XmlElement(name = "MODE", required = true)
    protected String mode;

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
     * Gets the value of the mode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMODE() {
        return mode;
    }

    /**
     * Sets the value of the mode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMODE(String value) {
        this.mode = value;
    }

}
