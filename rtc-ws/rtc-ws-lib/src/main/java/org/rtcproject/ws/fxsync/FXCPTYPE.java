
package org.rtcproject.ws.fxsync;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FX_CP_TYPE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FX_CP_TYPE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PACKAGE_INST_ID" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="PACKAGE_INST_ID_SERV" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="PACKAGE_ID" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="ACTIVE_DT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FX_CP_TYPE", propOrder = {
    "packageinstid",
    "packageinstidserv",
    "packageid",
    "activedt"
})
public class FXCPTYPE {

    @XmlElement(name = "PACKAGE_INST_ID", required = true)
    protected BigDecimal packageinstid;
    @XmlElement(name = "PACKAGE_INST_ID_SERV", required = true)
    protected BigDecimal packageinstidserv;
    @XmlElement(name = "PACKAGE_ID", required = true)
    protected BigDecimal packageid;
    @XmlElement(name = "ACTIVE_DT", required = true)
    protected String activedt;

    /**
     * Gets the value of the packageinstid property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPACKAGEINSTID() {
        return packageinstid;
    }

    /**
     * Sets the value of the packageinstid property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPACKAGEINSTID(BigDecimal value) {
        this.packageinstid = value;
    }

    /**
     * Gets the value of the packageinstidserv property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPACKAGEINSTIDSERV() {
        return packageinstidserv;
    }

    /**
     * Sets the value of the packageinstidserv property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPACKAGEINSTIDSERV(BigDecimal value) {
        this.packageinstidserv = value;
    }

    /**
     * Gets the value of the packageid property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPACKAGEID() {
        return packageid;
    }

    /**
     * Sets the value of the packageid property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPACKAGEID(BigDecimal value) {
        this.packageid = value;
    }

    /**
     * Gets the value of the activedt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getACTIVEDT() {
        return activedt;
    }

    /**
     * Sets the value of the activedt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setACTIVEDT(String value) {
        this.activedt = value;
    }

}
