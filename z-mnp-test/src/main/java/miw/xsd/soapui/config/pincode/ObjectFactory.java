//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.11.26 at 09:13:40 PM ICT 
//


package miw.xsd.soapui.config.pincode;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the a package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _JmsPropertyConfig_QNAME = new QName("http://eviware.com/soapui/config", "jmsPropertyConfig");
    private final static QName _Endpoint_QNAME = new QName("http://eviware.com/soapui/config", "endpoint");
    private final static QName _DispatchStyle_QNAME = new QName("http://eviware.com/soapui/config", "dispatchStyle");
    private final static QName _Status_QNAME = new QName("http://eviware.com/soapui/config", "status");
    private final static QName _Element_QNAME = new QName("http://eviware.com/soapui/config", "element");
    private final static QName _Properties_QNAME = new QName("http://eviware.com/soapui/config", "properties");
    private final static QName _ResponseContent_QNAME = new QName("http://eviware.com/soapui/config", "responseContent");
    private final static QName _Parameters_QNAME = new QName("http://eviware.com/soapui/config", "parameters");
    private final static QName _AuthType_QNAME = new QName("http://eviware.com/soapui/config", "authType");
    private final static QName _OAuth1ProfileContainer_QNAME = new QName("http://eviware.com/soapui/config", "oAuth1ProfileContainer");
    private final static QName _Url_QNAME = new QName("http://eviware.com/soapui/config", "url");
    private final static QName _Content_QNAME = new QName("http://eviware.com/soapui/config", "content");
    private final static QName _OriginalUri_QNAME = new QName("http://eviware.com/soapui/config", "originalUri");
    private final static QName _DispatchConfig_QNAME = new QName("http://eviware.com/soapui/config", "dispatchConfig");
    private final static QName _OAuth2ProfileContainer_QNAME = new QName("http://eviware.com/soapui/config", "oAuth2ProfileContainer");
    private final static QName _DefaultResponse_QNAME = new QName("http://eviware.com/soapui/config", "defaultResponse");
    private final static QName _Type_QNAME = new QName("http://eviware.com/soapui/config", "type");
    private final static QName _Encoding_QNAME = new QName("http://eviware.com/soapui/config", "encoding");
    private final static QName _Params_QNAME = new QName("http://eviware.com/soapui/config", "params");
    private final static QName _WssContainer_QNAME = new QName("http://eviware.com/soapui/config", "wssContainer");
    private final static QName _MediaType_QNAME = new QName("http://eviware.com/soapui/config", "mediaType");
    private final static QName _SensitiveInformation_QNAME = new QName("http://eviware.com/soapui/config", "sensitiveInformation");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: a
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DefinitionCache }
     * 
     */
    public DefinitionCache createDefinitionCache() {
        return new DefinitionCache();
    }

    /**
     * Create an instance of {@link Part }
     * 
     */
    public Part createPart() {
        return new Part();
    }

    /**
     * Create an instance of {@link Request }
     * 
     */
    public Request createRequest() {
        return new Request();
    }

    /**
     * Create an instance of {@link Settings }
     * 
     */
    public Settings createSettings() {
        return new Settings();
    }

    /**
     * Create an instance of {@link Setting }
     * 
     */
    public Setting createSetting() {
        return new Setting();
    }

    /**
     * Create an instance of {@link Credentials }
     * 
     */
    public Credentials createCredentials() {
        return new Credentials();
    }

    /**
     * Create an instance of {@link JmsConfig }
     * 
     */
    public JmsConfig createJmsConfig() {
        return new JmsConfig();
    }

    /**
     * Create an instance of {@link MockService }
     * 
     */
    public MockService createMockService() {
        return new MockService();
    }

    /**
     * Create an instance of {@link MockOperation }
     * 
     */
    public MockOperation createMockOperation() {
        return new MockOperation();
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link WsaConfig }
     * 
     */
    public WsaConfig createWsaConfig() {
        return new WsaConfig();
    }

    /**
     * Create an instance of {@link Interface }
     * 
     */
    public Interface createInterface() {
        return new Interface();
    }

    /**
     * Create an instance of {@link Endpoints }
     * 
     */
    public Endpoints createEndpoints() {
        return new Endpoints();
    }

    /**
     * Create an instance of {@link Operation }
     * 
     */
    public Operation createOperation() {
        return new Operation();
    }

    /**
     * Create an instance of {@link Call }
     * 
     */
    public Call createCall() {
        return new Call();
    }

    /**
     * Create an instance of {@link WsrmConfig }
     * 
     */
    public WsrmConfig createWsrmConfig() {
        return new WsrmConfig();
    }

    /**
     * Create an instance of {@link Resource }
     * 
     */
    public Resource createResource() {
        return new Resource();
    }

    /**
     * Create an instance of {@link Method }
     * 
     */
    public Method createMethod() {
        return new Method();
    }

    /**
     * Create an instance of {@link Representation }
     * 
     */
    public Representation createRepresentation() {
        return new Representation();
    }

    /**
     * Create an instance of {@link SoapuiProject }
     * 
     */
    public SoapuiProject createSoapuiProject() {
        return new SoapuiProject();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://eviware.com/soapui/config", name = "jmsPropertyConfig")
    public JAXBElement<String> createJmsPropertyConfig(String value) {
        return new JAXBElement<String>(_JmsPropertyConfig_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://eviware.com/soapui/config", name = "endpoint")
    public JAXBElement<String> createEndpoint(String value) {
        return new JAXBElement<String>(_Endpoint_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://eviware.com/soapui/config", name = "dispatchStyle")
    public JAXBElement<String> createDispatchStyle(String value) {
        return new JAXBElement<String>(_DispatchStyle_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://eviware.com/soapui/config", name = "status")
    public JAXBElement<String> createStatus(String value) {
        return new JAXBElement<String>(_Status_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://eviware.com/soapui/config", name = "element")
    public JAXBElement<String> createElement(String value) {
        return new JAXBElement<String>(_Element_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://eviware.com/soapui/config", name = "properties")
    public JAXBElement<String> createProperties(String value) {
        return new JAXBElement<String>(_Properties_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://eviware.com/soapui/config", name = "responseContent")
    public JAXBElement<String> createResponseContent(String value) {
        return new JAXBElement<String>(_ResponseContent_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://eviware.com/soapui/config", name = "parameters")
    public JAXBElement<String> createParameters(String value) {
        return new JAXBElement<String>(_Parameters_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://eviware.com/soapui/config", name = "authType")
    public JAXBElement<String> createAuthType(String value) {
        return new JAXBElement<String>(_AuthType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://eviware.com/soapui/config", name = "oAuth1ProfileContainer")
    public JAXBElement<String> createOAuth1ProfileContainer(String value) {
        return new JAXBElement<String>(_OAuth1ProfileContainer_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://eviware.com/soapui/config", name = "url")
    public JAXBElement<String> createUrl(String value) {
        return new JAXBElement<String>(_Url_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://eviware.com/soapui/config", name = "content")
    public JAXBElement<String> createContent(String value) {
        return new JAXBElement<String>(_Content_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://eviware.com/soapui/config", name = "originalUri")
    public JAXBElement<String> createOriginalUri(String value) {
        return new JAXBElement<String>(_OriginalUri_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://eviware.com/soapui/config", name = "dispatchConfig")
    public JAXBElement<String> createDispatchConfig(String value) {
        return new JAXBElement<String>(_DispatchConfig_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://eviware.com/soapui/config", name = "oAuth2ProfileContainer")
    public JAXBElement<String> createOAuth2ProfileContainer(String value) {
        return new JAXBElement<String>(_OAuth2ProfileContainer_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://eviware.com/soapui/config", name = "defaultResponse")
    public JAXBElement<String> createDefaultResponse(String value) {
        return new JAXBElement<String>(_DefaultResponse_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://eviware.com/soapui/config", name = "type")
    public JAXBElement<String> createType(String value) {
        return new JAXBElement<String>(_Type_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://eviware.com/soapui/config", name = "encoding")
    public JAXBElement<String> createEncoding(String value) {
        return new JAXBElement<String>(_Encoding_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://eviware.com/soapui/config", name = "params")
    public JAXBElement<String> createParams(String value) {
        return new JAXBElement<String>(_Params_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://eviware.com/soapui/config", name = "wssContainer")
    public JAXBElement<String> createWssContainer(String value) {
        return new JAXBElement<String>(_WssContainer_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://eviware.com/soapui/config", name = "mediaType")
    public JAXBElement<String> createMediaType(String value) {
        return new JAXBElement<String>(_MediaType_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://eviware.com/soapui/config", name = "sensitiveInformation")
    public JAXBElement<String> createSensitiveInformation(String value) {
        return new JAXBElement<String>(_SensitiveInformation_QNAME, String.class, null, value);
    }

}
