//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.10.25 at 11:08:32 PM ICT 
//


package com.eviware.soapui.config;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://eviware.com/soapui/config}settings" minOccurs="0"/>
 *         &lt;element ref="{http://eviware.com/soapui/config}encoding" minOccurs="0"/>
 *         &lt;element ref="{http://eviware.com/soapui/config}endpoint" minOccurs="0"/>
 *         &lt;element ref="{http://eviware.com/soapui/config}request" minOccurs="0"/>
 *         &lt;element ref="{http://eviware.com/soapui/config}assertion" minOccurs="0"/>
 *         &lt;element ref="{http://eviware.com/soapui/config}credentials" minOccurs="0"/>
 *         &lt;element ref="{http://eviware.com/soapui/config}jmsConfig" minOccurs="0"/>
 *         &lt;element ref="{http://eviware.com/soapui/config}jmsPropertyConfig" minOccurs="0"/>
 *         &lt;element ref="{http://eviware.com/soapui/config}wsaConfig" minOccurs="0"/>
 *         &lt;element ref="{http://eviware.com/soapui/config}wsrmConfig" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="outgoingWss" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="incomingWss" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="timeout" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="sslKeystore" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="useWsAddressing" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="useWsReliableMessaging" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="wssPasswordType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "content"
})
@XmlRootElement(name = "request")
public class Request {

    @XmlElementRefs({
        @XmlElementRef(name = "encoding", namespace = "http://eviware.com/soapui/config", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "request", namespace = "http://eviware.com/soapui/config", type = Request.class, required = false),
        @XmlElementRef(name = "settings", namespace = "http://eviware.com/soapui/config", type = Settings.class, required = false),
        @XmlElementRef(name = "jmsPropertyConfig", namespace = "http://eviware.com/soapui/config", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "jmsConfig", namespace = "http://eviware.com/soapui/config", type = JmsConfig.class, required = false),
        @XmlElementRef(name = "wsaConfig", namespace = "http://eviware.com/soapui/config", type = WsaConfig.class, required = false),
        @XmlElementRef(name = "wsrmConfig", namespace = "http://eviware.com/soapui/config", type = WsrmConfig.class, required = false),
        @XmlElementRef(name = "endpoint", namespace = "http://eviware.com/soapui/config", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "assertion", namespace = "http://eviware.com/soapui/config", type = Assertion.class, required = false),
        @XmlElementRef(name = "credentials", namespace = "http://eviware.com/soapui/config", type = Credentials.class, required = false)
    })
    @XmlMixed
    protected List<Object> content;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "outgoingWss")
    protected String outgoingWss;
    @XmlAttribute(name = "incomingWss")
    protected String incomingWss;
    @XmlAttribute(name = "timeout")
    protected String timeout;
    @XmlAttribute(name = "sslKeystore")
    protected String sslKeystore;
    @XmlAttribute(name = "useWsAddressing")
    protected String useWsAddressing;
    @XmlAttribute(name = "useWsReliableMessaging")
    protected String useWsReliableMessaging;
    @XmlAttribute(name = "wssPasswordType")
    protected String wssPasswordType;
    @XmlAttribute(name = "id")
    protected String id;

    /**
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link String }
     * {@link Request }
     * {@link Settings }
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JmsConfig }
     * {@link WsaConfig }
     * {@link WsrmConfig }
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link Assertion }
     * {@link Credentials }
     * 
     * 
     */
    public List<Object> getContent() {
        if (content == null) {
            content = new ArrayList<Object>();
        }
        return this.content;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the outgoingWss property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutgoingWss() {
        return outgoingWss;
    }

    /**
     * Sets the value of the outgoingWss property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutgoingWss(String value) {
        this.outgoingWss = value;
    }

    /**
     * Gets the value of the incomingWss property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncomingWss() {
        return incomingWss;
    }

    /**
     * Sets the value of the incomingWss property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncomingWss(String value) {
        this.incomingWss = value;
    }

    /**
     * Gets the value of the timeout property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeout() {
        return timeout;
    }

    /**
     * Sets the value of the timeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeout(String value) {
        this.timeout = value;
    }

    /**
     * Gets the value of the sslKeystore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSslKeystore() {
        return sslKeystore;
    }

    /**
     * Sets the value of the sslKeystore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSslKeystore(String value) {
        this.sslKeystore = value;
    }

    /**
     * Gets the value of the useWsAddressing property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseWsAddressing() {
        return useWsAddressing;
    }

    /**
     * Sets the value of the useWsAddressing property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseWsAddressing(String value) {
        this.useWsAddressing = value;
    }

    /**
     * Gets the value of the useWsReliableMessaging property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseWsReliableMessaging() {
        return useWsReliableMessaging;
    }

    /**
     * Sets the value of the useWsReliableMessaging property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseWsReliableMessaging(String value) {
        this.useWsReliableMessaging = value;
    }

    /**
     * Gets the value of the wssPasswordType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWssPasswordType() {
        return wssPasswordType;
    }

    /**
     * Sets the value of the wssPasswordType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWssPasswordType(String value) {
        this.wssPasswordType = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}