
package org.rtcproject.ws.jaxb;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BILL_ADDRESS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BILL_ADDRESS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PERSONAL" type="{http://rtcproject.org/MNP_WS}PERSONAL" minOccurs="0"/>
 *         &lt;element name="CORPORATION" type="{http://rtcproject.org/MNP_WS}CORPORATION" minOccurs="0"/>
 *         &lt;element name="KHET" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="KWANG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PROVINCE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="POSTAL_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="COUNTRY_CODE" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ADDRESS1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ADDRESS2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ADDRESS3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BILL_ADDRESS", propOrder = {
    "personal",
    "corporation",
    "khet",
    "kwang",
    "province",
    "postalcode",
    "countrycode",
    "address1",
    "address2",
    "address3"
})
public class BILLADDRESS {

    @XmlElement(name = "PERSONAL")
    protected PERSONAL personal;
    @XmlElement(name = "CORPORATION")
    protected CORPORATION corporation;
    @XmlElement(name = "KHET", required = true)
    protected String khet;
    @XmlElement(name = "KWANG", required = true)
    protected String kwang;
    @XmlElement(name = "PROVINCE", required = true)
    protected String province;
    @XmlElement(name = "POSTAL_CODE", required = true)
    protected String postalcode;
    @XmlElement(name = "COUNTRY_CODE")
    protected int countrycode;
    @XmlElement(name = "ADDRESS1", required = true)
    protected String address1;
    @XmlElement(name = "ADDRESS2")
    protected String address2;
    @XmlElement(name = "ADDRESS3")
    protected String address3;

    /**
     * Gets the value of the personal property.
     * 
     * @return
     *     possible object is
     *     {@link PERSONAL }
     *     
     */
    public PERSONAL getPERSONAL() {
        return personal;
    }

    /**
     * Sets the value of the personal property.
     * 
     * @param value
     *     allowed object is
     *     {@link PERSONAL }
     *     
     */
    public void setPERSONAL(PERSONAL value) {
        this.personal = value;
    }

    /**
     * Gets the value of the corporation property.
     * 
     * @return
     *     possible object is
     *     {@link CORPORATION }
     *     
     */
    public CORPORATION getCORPORATION() {
        return corporation;
    }

    /**
     * Sets the value of the corporation property.
     * 
     * @param value
     *     allowed object is
     *     {@link CORPORATION }
     *     
     */
    public void setCORPORATION(CORPORATION value) {
        this.corporation = value;
    }

    /**
     * Gets the value of the khet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKHET() {
        return khet;
    }

    /**
     * Sets the value of the khet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKHET(String value) {
        this.khet = value;
    }

    /**
     * Gets the value of the kwang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKWANG() {
        return kwang;
    }

    /**
     * Sets the value of the kwang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKWANG(String value) {
        this.kwang = value;
    }

    /**
     * Gets the value of the province property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPROVINCE() {
        return province;
    }

    /**
     * Sets the value of the province property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPROVINCE(String value) {
        this.province = value;
    }

    /**
     * Gets the value of the postalcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPOSTALCODE() {
        return postalcode;
    }

    /**
     * Sets the value of the postalcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPOSTALCODE(String value) {
        this.postalcode = value;
    }

    /**
     * Gets the value of the countrycode property.
     * 
     */
    public int getCOUNTRYCODE() {
        return countrycode;
    }

    /**
     * Sets the value of the countrycode property.
     * 
     */
    public void setCOUNTRYCODE(int value) {
        this.countrycode = value;
    }

    /**
     * Gets the value of the address1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getADDRESS1() {
        return address1;
    }

    /**
     * Sets the value of the address1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setADDRESS1(String value) {
        this.address1 = value;
    }

    /**
     * Gets the value of the address2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getADDRESS2() {
        return address2;
    }

    /**
     * Sets the value of the address2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setADDRESS2(String value) {
        this.address2 = value;
    }

    /**
     * Gets the value of the address3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getADDRESS3() {
        return address3;
    }

    /**
     * Sets the value of the address3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setADDRESS3(String value) {
        this.address3 = value;
    }

}
