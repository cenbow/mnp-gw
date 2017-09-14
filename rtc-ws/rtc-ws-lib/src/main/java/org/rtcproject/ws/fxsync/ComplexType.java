
package org.rtcproject.ws.fxsync;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for complexType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="complexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SYNCID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "complexType", propOrder = {
    "syncid"
})
public class ComplexType {

    @XmlElement(name = "SYNCID", required = true)
    protected String syncid;

    /**
     * Gets the value of the syncid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSYNCID() {
        return syncid;
    }

    /**
     * Sets the value of the syncid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSYNCID(String value) {
        this.syncid = value;
    }

}
