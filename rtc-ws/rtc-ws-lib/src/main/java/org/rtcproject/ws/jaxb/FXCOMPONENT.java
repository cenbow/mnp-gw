
package org.rtcproject.ws.jaxb;


import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FX_COMPONENT complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FX_COMPONENT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RTC_COM_ID" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="COMPONENT_ID" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="COMPONENT_INST_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MANDATORY" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "FX_COMPONENT", propOrder = {
    "rtccomid",
    "componentid",
    "componentinstid",
    "mandatory",
    "dateactive"
})
public class FXCOMPONENT {

    @XmlElement(name = "RTC_COM_ID")
    protected BigDecimal rtccomid;
    @XmlElement(name = "COMPONENT_ID", required = true)
    protected BigDecimal componentid;
    @XmlElement(name = "COMPONENT_INST_ID")
    protected int componentinstid;
    @XmlElement(name = "MANDATORY")
    protected int mandatory;
    @XmlElement(name = "DATE_ACTIVE", required = true)
    protected String dateactive;

    /**
     * Gets the value of the rtccomid property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRTCCOMID() {
        return rtccomid;
    }

    /**
     * Sets the value of the rtccomid property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRTCCOMID(BigDecimal value) {
        this.rtccomid = value;
    }

    /**
     * Gets the value of the componentid property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCOMPONENTID() {
        return componentid;
    }

    /**
     * Sets the value of the componentid property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCOMPONENTID(BigDecimal value) {
        this.componentid = value;
    }

    /**
     * Gets the value of the componentinstid property.
     * 
     */
    public int getCOMPONENTINSTID() {
        return componentinstid;
    }

    /**
     * Sets the value of the componentinstid property.
     * 
     */
    public void setCOMPONENTINSTID(int value) {
        this.componentinstid = value;
    }

    /**
     * Gets the value of the mandatory property.
     * 
     */
    public int getMANDATORY() {
        return mandatory;
    }

    /**
     * Sets the value of the mandatory property.
     * 
     */
    public void setMANDATORY(int value) {
        this.mandatory = value;
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
