
package com.sicap.ussdgw;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for __processUMBReqOutput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="__processUMBReqOutput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="msgCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="msgDesc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="outF01" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="outF02" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="outF03" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="outF04" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="outF05" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="outF06" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="outF07" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="outF08" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="outF09" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="outF10" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="outF11" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="outF12" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="outF13" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="outF14" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="outF15" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="outF16" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="outF17" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="outF18" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="outF19" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="outF20" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="outArr01" type="{http://SICAP_SERVER/WSDL}__outArrDoc"/>
 *         &lt;element name="outArr02" type="{http://SICAP_SERVER/WSDL}__outArrDoc"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "__processUMBReqOutput", propOrder = {
    "msgCode",
    "msgDesc",
    "outF01",
    "outF02",
    "outF03",
    "outF04",
    "outF05",
    "outF06",
    "outF07",
    "outF08",
    "outF09",
    "outF10",
    "outF11",
    "outF12",
    "outF13",
    "outF14",
    "outF15",
    "outF16",
    "outF17",
    "outF18",
    "outF19",
    "outF20",
    "outArr01",
    "outArr02"
})
public class ProcessUMBReqOutput {

    protected String msgCode;
    protected String msgDesc;
    protected String outF01;
    protected String outF02;
    protected String outF03;
    protected String outF04;
    protected String outF05;
    protected String outF06;
    protected String outF07;
    protected String outF08;
    protected String outF09;
    protected String outF10;
    protected String outF11;
    protected String outF12;
    protected String outF13;
    protected String outF14;
    protected String outF15;
    protected String outF16;
    protected String outF17;
    protected String outF18;
    protected String outF19;
    protected String outF20;
    protected OutArrDoc outArr01;
    protected OutArrDoc outArr02;

    /**
     * Gets the value of the msgCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgCode() {
        return msgCode;
    }

    /**
     * Sets the value of the msgCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgCode(String value) {
        this.msgCode = value;
    }

    /**
     * Gets the value of the msgDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgDesc() {
        return msgDesc;
    }

    /**
     * Sets the value of the msgDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgDesc(String value) {
        this.msgDesc = value;
    }

    /**
     * Gets the value of the outF01 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutF01() {
        return outF01;
    }

    /**
     * Sets the value of the outF01 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutF01(String value) {
        this.outF01 = value;
    }

    /**
     * Gets the value of the outF02 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutF02() {
        return outF02;
    }

    /**
     * Sets the value of the outF02 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutF02(String value) {
        this.outF02 = value;
    }

    /**
     * Gets the value of the outF03 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutF03() {
        return outF03;
    }

    /**
     * Sets the value of the outF03 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutF03(String value) {
        this.outF03 = value;
    }

    /**
     * Gets the value of the outF04 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutF04() {
        return outF04;
    }

    /**
     * Sets the value of the outF04 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutF04(String value) {
        this.outF04 = value;
    }

    /**
     * Gets the value of the outF05 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutF05() {
        return outF05;
    }

    /**
     * Sets the value of the outF05 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutF05(String value) {
        this.outF05 = value;
    }

    /**
     * Gets the value of the outF06 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutF06() {
        return outF06;
    }

    /**
     * Sets the value of the outF06 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutF06(String value) {
        this.outF06 = value;
    }

    /**
     * Gets the value of the outF07 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutF07() {
        return outF07;
    }

    /**
     * Sets the value of the outF07 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutF07(String value) {
        this.outF07 = value;
    }

    /**
     * Gets the value of the outF08 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutF08() {
        return outF08;
    }

    /**
     * Sets the value of the outF08 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutF08(String value) {
        this.outF08 = value;
    }

    /**
     * Gets the value of the outF09 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutF09() {
        return outF09;
    }

    /**
     * Sets the value of the outF09 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutF09(String value) {
        this.outF09 = value;
    }

    /**
     * Gets the value of the outF10 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutF10() {
        return outF10;
    }

    /**
     * Sets the value of the outF10 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutF10(String value) {
        this.outF10 = value;
    }

    /**
     * Gets the value of the outF11 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutF11() {
        return outF11;
    }

    /**
     * Sets the value of the outF11 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutF11(String value) {
        this.outF11 = value;
    }

    /**
     * Gets the value of the outF12 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutF12() {
        return outF12;
    }

    /**
     * Sets the value of the outF12 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutF12(String value) {
        this.outF12 = value;
    }

    /**
     * Gets the value of the outF13 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutF13() {
        return outF13;
    }

    /**
     * Sets the value of the outF13 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutF13(String value) {
        this.outF13 = value;
    }

    /**
     * Gets the value of the outF14 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutF14() {
        return outF14;
    }

    /**
     * Sets the value of the outF14 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutF14(String value) {
        this.outF14 = value;
    }

    /**
     * Gets the value of the outF15 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutF15() {
        return outF15;
    }

    /**
     * Sets the value of the outF15 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutF15(String value) {
        this.outF15 = value;
    }

    /**
     * Gets the value of the outF16 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutF16() {
        return outF16;
    }

    /**
     * Sets the value of the outF16 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutF16(String value) {
        this.outF16 = value;
    }

    /**
     * Gets the value of the outF17 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutF17() {
        return outF17;
    }

    /**
     * Sets the value of the outF17 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutF17(String value) {
        this.outF17 = value;
    }

    /**
     * Gets the value of the outF18 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutF18() {
        return outF18;
    }

    /**
     * Sets the value of the outF18 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutF18(String value) {
        this.outF18 = value;
    }

    /**
     * Gets the value of the outF19 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutF19() {
        return outF19;
    }

    /**
     * Sets the value of the outF19 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutF19(String value) {
        this.outF19 = value;
    }

    /**
     * Gets the value of the outF20 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutF20() {
        return outF20;
    }

    /**
     * Sets the value of the outF20 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutF20(String value) {
        this.outF20 = value;
    }

    /**
     * Gets the value of the outArr01 property.
     * 
     * @return
     *     possible object is
     *     {@link OutArrDoc }
     *     
     */
    public OutArrDoc getOutArr01() {
        return outArr01;
    }

    /**
     * Sets the value of the outArr01 property.
     * 
     * @param value
     *     allowed object is
     *     {@link OutArrDoc }
     *     
     */
    public void setOutArr01(OutArrDoc value) {
        this.outArr01 = value;
    }

    /**
     * Gets the value of the outArr02 property.
     * 
     * @return
     *     possible object is
     *     {@link OutArrDoc }
     *     
     */
    public OutArrDoc getOutArr02() {
        return outArr02;
    }

    /**
     * Sets the value of the outArr02 property.
     * 
     * @param value
     *     allowed object is
     *     {@link OutArrDoc }
     *     
     */
    public void setOutArr02(OutArrDoc value) {
        this.outArr02 = value;
    }

}
