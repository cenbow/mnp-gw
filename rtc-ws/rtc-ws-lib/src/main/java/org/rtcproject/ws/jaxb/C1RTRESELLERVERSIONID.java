
package org.rtcproject.ws.jaxb;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for C1RT_RESELLER_VERSION_ID complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="C1RT_RESELLER_VERSION_ID">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VERSION_ID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "C1RT_RESELLER_VERSION_ID", propOrder = {
    "versionid"
})
public class C1RTRESELLERVERSIONID {

    @XmlElement(name = "VERSION_ID")
    protected long versionid;

    /**
     * Gets the value of the versionid property.
     * 
     */
    public long getVERSIONID() {
        return versionid;
    }

    /**
     * Sets the value of the versionid property.
     * 
     */
    public void setVERSIONID(long value) {
        this.versionid = value;
    }

}
