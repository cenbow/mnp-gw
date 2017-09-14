
package org.rtcproject.ws.fxsync;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SYNC_SERVICE_DATA complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SYNC_SERVICE_DATA">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FX_CIEM" type="{http://RTC.org/RTC_FX_SYNC_MSG}FX_CIEM_TYPE"/>
 *         &lt;element name="FX_SERVICE" type="{http://RTC.org/RTC_FX_SYNC_MSG}FX_SERVICE_TYPE"/>
 *         &lt;element name="FX_PACKAGE" type="{http://RTC.org/RTC_FX_SYNC_MSG}FX_CP_TYPE" maxOccurs="200"/>
 *         &lt;element name="FX_COMPONENT" type="{http://RTC.org/RTC_FX_SYNC_MSG}FX_CPC_TYPE" maxOccurs="200"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SYNC_SERVICE_DATA", propOrder = {
    "fxciem",
    "fxservice",
    "fxpackage",
    "fxcomponent"
})
public class SYNCSERVICEDATA {

    @XmlElement(name = "FX_CIEM", required = true)
    protected FXCIEMTYPE fxciem;
    @XmlElement(name = "FX_SERVICE", required = true)
    protected FXSERVICETYPE fxservice;
    @XmlElement(name = "FX_PACKAGE", required = true)
    protected List<FXCPTYPE> fxpackage;
    @XmlElement(name = "FX_COMPONENT", required = true)
    protected List<FXCPCTYPE> fxcomponent;

    /**
     * Gets the value of the fxciem property.
     * 
     * @return
     *     possible object is
     *     {@link FXCIEMTYPE }
     *     
     */
    public FXCIEMTYPE getFXCIEM() {
        return fxciem;
    }

    /**
     * Sets the value of the fxciem property.
     * 
     * @param value
     *     allowed object is
     *     {@link FXCIEMTYPE }
     *     
     */
    public void setFXCIEM(FXCIEMTYPE value) {
        this.fxciem = value;
    }

    /**
     * Gets the value of the fxservice property.
     * 
     * @return
     *     possible object is
     *     {@link FXSERVICETYPE }
     *     
     */
    public FXSERVICETYPE getFXSERVICE() {
        return fxservice;
    }

    /**
     * Sets the value of the fxservice property.
     * 
     * @param value
     *     allowed object is
     *     {@link FXSERVICETYPE }
     *     
     */
    public void setFXSERVICE(FXSERVICETYPE value) {
        this.fxservice = value;
    }

    /**
     * Gets the value of the fxpackage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fxpackage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFXPACKAGE().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FXCPTYPE }
     * 
     * 
     */
    public List<FXCPTYPE> getFXPACKAGE() {
        if (fxpackage == null) {
            fxpackage = new ArrayList<FXCPTYPE>();
        }
        return this.fxpackage;
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
     * {@link FXCPCTYPE }
     * 
     * 
     */
    public List<FXCPCTYPE> getFXCOMPONENT() {
        if (fxcomponent == null) {
            fxcomponent = new ArrayList<FXCPCTYPE>();
        }
        return this.fxcomponent;
    }

}
