//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.11.26 at 09:13:40 PM ICT 
//


package miw.xsd.soapui.config.pincode;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element ref="{http://eviware.com/soapui/config}settings"/>
 *         &lt;element ref="{http://eviware.com/soapui/config}interface" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://eviware.com/soapui/config}mockService" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://eviware.com/soapui/config}properties"/>
 *         &lt;element ref="{http://eviware.com/soapui/config}wssContainer"/>
 *         &lt;element ref="{http://eviware.com/soapui/config}oAuth2ProfileContainer"/>
 *         &lt;element ref="{http://eviware.com/soapui/config}oAuth1ProfileContainer"/>
 *         &lt;element ref="{http://eviware.com/soapui/config}sensitiveInformation"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="activeEnvironment" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="resourceRoot" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="soapui-version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="abortOnError" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="runType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "settings",
    "_interface",
    "mockService",
    "properties",
    "wssContainer",
    "oAuth2ProfileContainer",
    "oAuth1ProfileContainer",
    "sensitiveInformation"
})
@XmlRootElement(name = "soapui-project")
public class SoapuiProject {

    @XmlElement(required = true)
    protected Settings settings;
    @XmlElement(name = "interface")
    protected List<Interface> _interface;
    protected List<MockService> mockService;
    @XmlElement(required = true)
    protected String properties;
    @XmlElement(required = true)
    protected String wssContainer;
    @XmlElement(required = true)
    protected String oAuth2ProfileContainer;
    @XmlElement(required = true)
    protected String oAuth1ProfileContainer;
    @XmlElement(required = true)
    protected String sensitiveInformation;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "activeEnvironment")
    protected String activeEnvironment;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "resourceRoot")
    protected String resourceRoot;
    @XmlAttribute(name = "soapui-version")
    protected String soapuiVersion;
    @XmlAttribute(name = "abortOnError")
    protected String abortOnError;
    @XmlAttribute(name = "runType")
    protected String runType;

    /**
     * Gets the value of the settings property.
     * 
     * @return
     *     possible object is
     *     {@link Settings }
     *     
     */
    public Settings getSettings() {
        return settings;
    }

    /**
     * Sets the value of the settings property.
     * 
     * @param value
     *     allowed object is
     *     {@link Settings }
     *     
     */
    public void setSettings(Settings value) {
        this.settings = value;
    }

    /**
     * Gets the value of the interface property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the interface property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInterface().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Interface }
     * 
     * 
     */
    public List<Interface> getInterface() {
        if (_interface == null) {
            _interface = new ArrayList<Interface>();
        }
        return this._interface;
    }

    /**
     * Gets the value of the mockService property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mockService property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMockService().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MockService }
     * 
     * 
     */
    public List<MockService> getMockService() {
        if (mockService == null) {
            mockService = new ArrayList<MockService>();
        }
        return this.mockService;
    }

    /**
     * Gets the value of the properties property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProperties() {
        return properties;
    }

    /**
     * Sets the value of the properties property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProperties(String value) {
        this.properties = value;
    }

    /**
     * Gets the value of the wssContainer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWssContainer() {
        return wssContainer;
    }

    /**
     * Sets the value of the wssContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWssContainer(String value) {
        this.wssContainer = value;
    }

    /**
     * Gets the value of the oAuth2ProfileContainer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOAuth2ProfileContainer() {
        return oAuth2ProfileContainer;
    }

    /**
     * Sets the value of the oAuth2ProfileContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOAuth2ProfileContainer(String value) {
        this.oAuth2ProfileContainer = value;
    }

    /**
     * Gets the value of the oAuth1ProfileContainer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOAuth1ProfileContainer() {
        return oAuth1ProfileContainer;
    }

    /**
     * Sets the value of the oAuth1ProfileContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOAuth1ProfileContainer(String value) {
        this.oAuth1ProfileContainer = value;
    }

    /**
     * Gets the value of the sensitiveInformation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSensitiveInformation() {
        return sensitiveInformation;
    }

    /**
     * Sets the value of the sensitiveInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSensitiveInformation(String value) {
        this.sensitiveInformation = value;
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

    /**
     * Gets the value of the activeEnvironment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActiveEnvironment() {
        return activeEnvironment;
    }

    /**
     * Sets the value of the activeEnvironment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActiveEnvironment(String value) {
        this.activeEnvironment = value;
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
     * Gets the value of the resourceRoot property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourceRoot() {
        return resourceRoot;
    }

    /**
     * Sets the value of the resourceRoot property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourceRoot(String value) {
        this.resourceRoot = value;
    }

    /**
     * Gets the value of the soapuiVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoapuiVersion() {
        return soapuiVersion;
    }

    /**
     * Sets the value of the soapuiVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoapuiVersion(String value) {
        this.soapuiVersion = value;
    }

    /**
     * Gets the value of the abortOnError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbortOnError() {
        return abortOnError;
    }

    /**
     * Sets the value of the abortOnError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbortOnError(String value) {
        this.abortOnError = value;
    }

    /**
     * Gets the value of the runType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRunType() {
        return runType;
    }

    /**
     * Sets the value of the runType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRunType(String value) {
        this.runType = value;
    }

}
