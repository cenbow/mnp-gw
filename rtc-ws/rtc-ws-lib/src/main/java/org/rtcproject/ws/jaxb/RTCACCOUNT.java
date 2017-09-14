
package org.rtcproject.ws.jaxb;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RTC_ACCOUNT complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RTC_ACCOUNT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ACCOUNT_TYPE" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ACCOUNT_CATEGORY" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CARD_TYPE" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CARD_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DATE_ACTIVE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BILL_ADDRESS" type="{http://rtcproject.org/MNP_WS}BILL_ADDRESS"/>
 *         &lt;element name="BILL_DELIVERY_ADDRESS" type="{http://rtcproject.org/MNP_WS}BILL_ADDRESS" minOccurs="0"/>
 *         &lt;element name="VAT_ADDRESS" type="{http://rtcproject.org/MNP_WS}BILL_ADDRESS"/>
 *         &lt;element name="VAT_DELIVERY_ADDRESS" type="{http://rtcproject.org/MNP_WS}BILL_ADDRESS" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RTC_ACCOUNT", propOrder = {
    "accounttype",
    "accountcategory",
    "cardtype",
    "cardno",
    "dateactive",
    "billaddress",
    "billdeliveryaddress",
    "vataddress",
    "vatdeliveryaddress"
})
public class RTCACCOUNT {

    @XmlElement(name = "ACCOUNT_TYPE")
    protected int accounttype;
    @XmlElement(name = "ACCOUNT_CATEGORY")
    protected int accountcategory;
    @XmlElement(name = "CARD_TYPE")
    protected int cardtype;
    @XmlElement(name = "CARD_NO", required = true)
    protected String cardno;
    @XmlElement(name = "DATE_ACTIVE", required = true)
    protected String dateactive;
    @XmlElement(name = "BILL_ADDRESS", required = true)
    protected BILLADDRESS billaddress;
    @XmlElement(name = "BILL_DELIVERY_ADDRESS")
    protected BILLADDRESS billdeliveryaddress;
    @XmlElement(name = "VAT_ADDRESS", required = true)
    protected BILLADDRESS vataddress;
    @XmlElement(name = "VAT_DELIVERY_ADDRESS")
    protected BILLADDRESS vatdeliveryaddress;

    /**
     * Gets the value of the accounttype property.
     * 
     */
    public int getACCOUNTTYPE() {
        return accounttype;
    }

    /**
     * Sets the value of the accounttype property.
     * 
     */
    public void setACCOUNTTYPE(int value) {
        this.accounttype = value;
    }

    /**
     * Gets the value of the accountcategory property.
     * 
     */
    public int getACCOUNTCATEGORY() {
        return accountcategory;
    }

    /**
     * Sets the value of the accountcategory property.
     * 
     */
    public void setACCOUNTCATEGORY(int value) {
        this.accountcategory = value;
    }

    /**
     * Gets the value of the cardtype property.
     * 
     */
    public int getCARDTYPE() {
        return cardtype;
    }

    /**
     * Sets the value of the cardtype property.
     * 
     */
    public void setCARDTYPE(int value) {
        this.cardtype = value;
    }

    /**
     * Gets the value of the cardno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCARDNO() {
        return cardno;
    }

    /**
     * Sets the value of the cardno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCARDNO(String value) {
        this.cardno = value;
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
     * Gets the value of the billaddress property.
     * 
     * @return
     *     possible object is
     *     {@link BILLADDRESS }
     *     
     */
    public BILLADDRESS getBILLADDRESS() {
        return billaddress;
    }

    /**
     * Sets the value of the billaddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link BILLADDRESS }
     *     
     */
    public void setBILLADDRESS(BILLADDRESS value) {
        this.billaddress = value;
    }

    /**
     * Gets the value of the billdeliveryaddress property.
     * 
     * @return
     *     possible object is
     *     {@link BILLADDRESS }
     *     
     */
    public BILLADDRESS getBILLDELIVERYADDRESS() {
        return billdeliveryaddress;
    }

    /**
     * Sets the value of the billdeliveryaddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link BILLADDRESS }
     *     
     */
    public void setBILLDELIVERYADDRESS(BILLADDRESS value) {
        this.billdeliveryaddress = value;
    }

    /**
     * Gets the value of the vataddress property.
     * 
     * @return
     *     possible object is
     *     {@link BILLADDRESS }
     *     
     */
    public BILLADDRESS getVATADDRESS() {
        return vataddress;
    }

    /**
     * Sets the value of the vataddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link BILLADDRESS }
     *     
     */
    public void setVATADDRESS(BILLADDRESS value) {
        this.vataddress = value;
    }

    /**
     * Gets the value of the vatdeliveryaddress property.
     * 
     * @return
     *     possible object is
     *     {@link BILLADDRESS }
     *     
     */
    public BILLADDRESS getVATDELIVERYADDRESS() {
        return vatdeliveryaddress;
    }

    /**
     * Sets the value of the vatdeliveryaddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link BILLADDRESS }
     *     
     */
    public void setVATDELIVERYADDRESS(BILLADDRESS value) {
        this.vatdeliveryaddress = value;
    }

}
