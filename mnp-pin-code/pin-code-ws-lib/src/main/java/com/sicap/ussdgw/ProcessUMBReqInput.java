
package com.sicap.ussdgw;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for __processUMBReqInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="__processUMBReqInput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cpurl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tranxId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inpF01" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inpF02" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inpF03" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inpF04" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inpF05" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inpF06" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inpF07" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inpF08" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inpF09" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inpF10" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inpF11" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inpF12" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inpF13" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inpF14" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inpF15" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inpF16" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inpF17" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inpF18" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inpF19" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="inpF20" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "__processUMBReqInput", propOrder = {
    "cpurl",
    "tranxId",
    "msisdn",
    "inpF01",
    "inpF02",
    "inpF03",
    "inpF04",
    "inpF05",
    "inpF06",
    "inpF07",
    "inpF08",
    "inpF09",
    "inpF10",
    "inpF11",
    "inpF12",
    "inpF13",
    "inpF14",
    "inpF15",
    "inpF16",
    "inpF17",
    "inpF18",
    "inpF19",
    "inpF20"
})
public class ProcessUMBReqInput {

    @XmlElement(required = true, nillable = false)
    protected String cpurl;
    @XmlElement(required = true, nillable = false)
    protected String tranxId;
    @XmlElement(required = true, nillable = false)
    protected String msisdn;
    @XmlElement(required = true, nillable = false)
    protected String inpF01;
    @XmlElement(required = false, nillable = true)
    protected String inpF02;
    @XmlElement(required = false, nillable = true)
    protected String inpF03;
    @XmlElement(required = false, nillable = true)
    protected String inpF04;
    @XmlElement(required = false, nillable = true)
    protected String inpF05;
    @XmlElement(required = false, nillable = true)
    protected String inpF06;
    @XmlElement(required = false, nillable = true)
    protected String inpF07;
    @XmlElement(required = false, nillable = true)
    protected String inpF08;
    @XmlElement(required = false, nillable = true)
    protected String inpF09;
    @XmlElement(required = false, nillable = true)
    protected String inpF10;
    @XmlElement(required = false, nillable = true)
    protected String inpF11;
    @XmlElement(required = false, nillable = true)
    protected String inpF12;
    @XmlElement(required = false, nillable = true)
    protected String inpF13;
    @XmlElement(required = false, nillable = true)
    protected String inpF14;
    @XmlElement(required = false, nillable = true)
    protected String inpF15;
    @XmlElement(required = false, nillable = true)
    protected String inpF16;
    @XmlElement(required = false, nillable = true)
    protected String inpF17;
    @XmlElement(required = false, nillable = true)
    protected String inpF18;
    @XmlElement(required = false, nillable = true)
    protected String inpF19;
    @XmlElement(required = false, nillable = true)
    protected String inpF20;

    /**
     * Gets the value of the cpurl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpurl() {
        return cpurl;
    }

    /**
     * Sets the value of the cpurl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpurl(String value) {
        this.cpurl = value;
    }

    /**
     * Gets the value of the tranxId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTranxId() {
        return tranxId;
    }

    /**
     * Sets the value of the tranxId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTranxId(String value) {
        this.tranxId = value;
    }

    /**
     * Gets the value of the msisdn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsisdn() {
        return msisdn;
    }

    /**
     * Sets the value of the msisdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsisdn(String value) {
        this.msisdn = value;
    }

    /**
     * Gets the value of the inpF01 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInpF01() {
        return inpF01;
    }

    /**
     * Sets the value of the inpF01 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInpF01(String value) {
        this.inpF01 = value;
    }

    /**
     * Gets the value of the inpF02 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInpF02() {
        return inpF02;
    }

    /**
     * Sets the value of the inpF02 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInpF02(String value) {
        this.inpF02 = value;
    }

    /**
     * Gets the value of the inpF03 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInpF03() {
        return inpF03;
    }

    /**
     * Sets the value of the inpF03 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInpF03(String value) {
        this.inpF03 = value;
    }

    /**
     * Gets the value of the inpF04 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInpF04() {
        return inpF04;
    }

    /**
     * Sets the value of the inpF04 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInpF04(String value) {
        this.inpF04 = value;
    }

    /**
     * Gets the value of the inpF05 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInpF05() {
        return inpF05;
    }

    /**
     * Sets the value of the inpF05 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInpF05(String value) {
        this.inpF05 = value;
    }

    /**
     * Gets the value of the inpF06 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInpF06() {
        return inpF06;
    }

    /**
     * Sets the value of the inpF06 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInpF06(String value) {
        this.inpF06 = value;
    }

    /**
     * Gets the value of the inpF07 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInpF07() {
        return inpF07;
    }

    /**
     * Sets the value of the inpF07 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInpF07(String value) {
        this.inpF07 = value;
    }

    /**
     * Gets the value of the inpF08 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInpF08() {
        return inpF08;
    }

    /**
     * Sets the value of the inpF08 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInpF08(String value) {
        this.inpF08 = value;
    }

    /**
     * Gets the value of the inpF09 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInpF09() {
        return inpF09;
    }

    /**
     * Sets the value of the inpF09 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInpF09(String value) {
        this.inpF09 = value;
    }

    /**
     * Gets the value of the inpF10 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInpF10() {
        return inpF10;
    }

    /**
     * Sets the value of the inpF10 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInpF10(String value) {
        this.inpF10 = value;
    }

    /**
     * Gets the value of the inpF11 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInpF11() {
        return inpF11;
    }

    /**
     * Sets the value of the inpF11 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInpF11(String value) {
        this.inpF11 = value;
    }

    /**
     * Gets the value of the inpF12 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInpF12() {
        return inpF12;
    }

    /**
     * Sets the value of the inpF12 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInpF12(String value) {
        this.inpF12 = value;
    }

    /**
     * Gets the value of the inpF13 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInpF13() {
        return inpF13;
    }

    /**
     * Sets the value of the inpF13 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInpF13(String value) {
        this.inpF13 = value;
    }

    /**
     * Gets the value of the inpF14 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInpF14() {
        return inpF14;
    }

    /**
     * Sets the value of the inpF14 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInpF14(String value) {
        this.inpF14 = value;
    }

    /**
     * Gets the value of the inpF15 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInpF15() {
        return inpF15;
    }

    /**
     * Sets the value of the inpF15 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInpF15(String value) {
        this.inpF15 = value;
    }

    /**
     * Gets the value of the inpF16 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInpF16() {
        return inpF16;
    }

    /**
     * Sets the value of the inpF16 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInpF16(String value) {
        this.inpF16 = value;
    }

    /**
     * Gets the value of the inpF17 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInpF17() {
        return inpF17;
    }

    /**
     * Sets the value of the inpF17 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInpF17(String value) {
        this.inpF17 = value;
    }

    /**
     * Gets the value of the inpF18 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInpF18() {
        return inpF18;
    }

    /**
     * Sets the value of the inpF18 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInpF18(String value) {
        this.inpF18 = value;
    }

    /**
     * Gets the value of the inpF19 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInpF19() {
        return inpF19;
    }

    /**
     * Sets the value of the inpF19 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInpF19(String value) {
        this.inpF19 = value;
    }

    /**
     * Gets the value of the inpF20 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInpF20() {
        return inpF20;
    }

    /**
     * Sets the value of the inpF20 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInpF20(String value) {
        this.inpF20 = value;
    }

}
