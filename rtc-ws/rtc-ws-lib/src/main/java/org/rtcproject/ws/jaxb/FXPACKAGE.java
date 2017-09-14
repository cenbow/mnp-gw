
package org.rtcproject.ws.jaxb;


import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FX_PACKAGE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FX_PACKAGE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RTC_PKG_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PACKAGE_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PACKAGE_INST_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DATE_ACTIVE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FX_COMPONENT" type="{http://rtcproject.org/MNP_WS}FX_COMPONENT" maxOccurs="200"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FX_PACKAGE", propOrder = {
    "rtcpkgid",
    "packageid",
    "packageinstid",
    "dateactive",
    "fxcomponent"
})
public class FXPACKAGE {

    @XmlElement(name = "RTC_PKG_ID")
    protected String rtcpkgid;
    @XmlElement(name = "PACKAGE_ID", required = true)
    protected String packageid;
    @XmlElement(name = "PACKAGE_INST_ID", required = true)
    protected String packageinstid;
    @XmlElement(name = "DATE_ACTIVE", required = true)
    protected String dateactive;
    @XmlElement(name = "FX_COMPONENT", required = true)
    protected List<FXCOMPONENT> fxcomponent;

    /**
     * Gets the value of the rtcpkgid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRTCPKGID() {
        return rtcpkgid;
    }

    /**
     * Sets the value of the rtcpkgid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRTCPKGID(String value) {
        this.rtcpkgid = value;
    }

    /**
     * Gets the value of the packageid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACKAGEID() {
        return packageid;
    }

    /**
     * Sets the value of the packageid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACKAGEID(String value) {
        this.packageid = value;
    }

    /**
     * Gets the value of the packageinstid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPACKAGEINSTID() {
        return packageinstid;
    }

    /**
     * Sets the value of the packageinstid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPACKAGEINSTID(String value) {
        this.packageinstid = value;
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

    /**
     * Gets the value of the fxcomponent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fxcomponent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFXCOMPONENT().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FXCOMPONENT }
     * 
     * 
     */
    public List<FXCOMPONENT> getFXCOMPONENT() {
        if (fxcomponent == null) {
            fxcomponent = new ArrayList<FXCOMPONENT>();
        }
        return this.fxcomponent;
    }

}
