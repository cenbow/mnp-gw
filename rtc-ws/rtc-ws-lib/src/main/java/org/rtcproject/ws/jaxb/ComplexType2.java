
package org.rtcproject.ws.jaxb;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for complexType2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="complexType2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OMTRANS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="C1RT_PROCESS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="C1RT_P_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="C1RT_E_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "complexType2", propOrder = {
    "omtrans",
    "c1RTPROCESS",
    "c1RTPCODE",
    "c1RTECODE"
})
public class ComplexType2 {

    @XmlElement(name = "OMTRANS", required = true)
    protected String omtrans;
    @XmlElement(name = "C1RT_PROCESS", required = true)
    protected String c1RTPROCESS;
    @XmlElement(name = "C1RT_P_CODE", required = true)
    protected String c1RTPCODE;
    @XmlElement(name = "C1RT_E_CODE", required = true)
    protected String c1RTECODE;

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
     * Gets the value of the c1RTPROCESS property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getC1RTPROCESS() {
        return c1RTPROCESS;
    }

    /**
     * Sets the value of the c1RTPROCESS property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setC1RTPROCESS(String value) {
        this.c1RTPROCESS = value;
    }

    /**
     * Gets the value of the c1RTPCODE property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getC1RTPCODE() {
        return c1RTPCODE;
    }

    /**
     * Sets the value of the c1RTPCODE property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setC1RTPCODE(String value) {
        this.c1RTPCODE = value;
    }

    /**
     * Gets the value of the c1RTECODE property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getC1RTECODE() {
        return c1RTECODE;
    }

    /**
     * Sets the value of the c1RTECODE property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setC1RTECODE(String value) {
        this.c1RTECODE = value;
    }

}
