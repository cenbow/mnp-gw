
package cat.sd.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for subscriberInfoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subscriberInfoResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="activeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="balanceInfoList" type="{http://domain.sd.cat/}balanceInfo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="cachedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="coreBalanceInfo" type="{http://domain.sd.cat/}balanceInfo" minOccurs="0"/&gt;
 *         &lt;element name="languageCode" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *         &lt;element name="msisdn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="offerInfoList" type="{http://domain.sd.cat/}offerInfo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="paymentType" type="{http://domain.sd.cat/}PaymentType" minOccurs="0"/&gt;
 *         &lt;element name="ratingStateType" type="{http://domain.sd.cat/}RatingStateType" minOccurs="0"/&gt;
 *         &lt;element name="serviceInactiveDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subscriberInfoResponse", propOrder = {
    "activeDate",
    "balanceInfoList",
    "cachedDate",
    "coreBalanceInfo",
    "languageCode",
    "msisdn",
    "offerInfoList",
    "paymentType",
    "ratingStateType",
    "serviceInactiveDate"
})
public class SubscriberInfoResponse {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar activeDate;
    @XmlElement(nillable = true)
    protected List<BalanceInfo> balanceInfoList;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar cachedDate;
    protected BalanceInfo coreBalanceInfo;
    protected Short languageCode;
    protected String msisdn;
    @XmlElement(nillable = true)
    protected List<OfferInfo> offerInfoList;
    @XmlSchemaType(name = "string")
    protected PaymentType paymentType;
    @XmlSchemaType(name = "string")
    protected RatingStateType ratingStateType;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar serviceInactiveDate;

    /**
     * Gets the value of the activeDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActiveDate() {
        return activeDate;
    }

    /**
     * Sets the value of the activeDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActiveDate(XMLGregorianCalendar value) {
        this.activeDate = value;
    }

    /**
     * Gets the value of the balanceInfoList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the balanceInfoList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBalanceInfoList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BalanceInfo }
     * 
     * 
     */
    public List<BalanceInfo> getBalanceInfoList() {
        if (balanceInfoList == null) {
            balanceInfoList = new ArrayList<BalanceInfo>();
        }
        return this.balanceInfoList;
    }

    /**
     * Gets the value of the cachedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCachedDate() {
        return cachedDate;
    }

    /**
     * Sets the value of the cachedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCachedDate(XMLGregorianCalendar value) {
        this.cachedDate = value;
    }

    /**
     * Gets the value of the coreBalanceInfo property.
     * 
     * @return
     *     possible object is
     *     {@link BalanceInfo }
     *     
     */
    public BalanceInfo getCoreBalanceInfo() {
        return coreBalanceInfo;
    }

    /**
     * Sets the value of the coreBalanceInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link BalanceInfo }
     *     
     */
    public void setCoreBalanceInfo(BalanceInfo value) {
        this.coreBalanceInfo = value;
    }

    /**
     * Gets the value of the languageCode property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getLanguageCode() {
        return languageCode;
    }

    /**
     * Sets the value of the languageCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setLanguageCode(Short value) {
        this.languageCode = value;
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
     * Gets the value of the offerInfoList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the offerInfoList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOfferInfoList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OfferInfo }
     * 
     * 
     */
    public List<OfferInfo> getOfferInfoList() {
        if (offerInfoList == null) {
            offerInfoList = new ArrayList<OfferInfo>();
        }
        return this.offerInfoList;
    }

    /**
     * Gets the value of the paymentType property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentType }
     *     
     */
    public PaymentType getPaymentType() {
        return paymentType;
    }

    /**
     * Sets the value of the paymentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentType }
     *     
     */
    public void setPaymentType(PaymentType value) {
        this.paymentType = value;
    }

    /**
     * Gets the value of the ratingStateType property.
     * 
     * @return
     *     possible object is
     *     {@link RatingStateType }
     *     
     */
    public RatingStateType getRatingStateType() {
        return ratingStateType;
    }

    /**
     * Sets the value of the ratingStateType property.
     * 
     * @param value
     *     allowed object is
     *     {@link RatingStateType }
     *     
     */
    public void setRatingStateType(RatingStateType value) {
        this.ratingStateType = value;
    }

    /**
     * Gets the value of the serviceInactiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getServiceInactiveDate() {
        return serviceInactiveDate;
    }

    /**
     * Sets the value of the serviceInactiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setServiceInactiveDate(XMLGregorianCalendar value) {
        this.serviceInactiveDate = value;
    }

}
