
package com.netnumber.titan.view.soap.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.netnumber.titan.view.soap.types package. 
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

    private final static QName _ChangeRequest_QNAME = new QName("http://www.netnumber.com/titan/view/soap/types", "ChangeRequest");
    private final static QName _ChangeResponse_QNAME = new QName("http://www.netnumber.com/titan/view/soap/types", "ChangeResponse");
    private final static QName _StatusRequest_QNAME = new QName("http://www.netnumber.com/titan/view/soap/types", "StatusRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.netnumber.titan.view.soap.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetResponse }
     * 
     */
    public GetResponse createGetResponse() {
        return new GetResponse();
    }

    /**
     * Create an instance of {@link ChangeResponse }
     * 
     */
    public ChangeResponse createChangeResponse() {
        return new ChangeResponse();
    }

    /**
     * Create an instance of {@link ChangeRequest }
     * 
     */
    public ChangeRequest createChangeRequest() {
        return new ChangeRequest();
    }

    /**
     * Create an instance of {@link GetRequest }
     * 
     */
    public GetRequest createGetRequest() {
        return new GetRequest();
    }

    /**
     * Create an instance of {@link GetResponse.Entry }
     * 
     */
    public GetResponse.Entry createGetResponseEntry() {
        return new GetResponse.Entry();
    }

    /**
     * Create an instance of {@link StatusResponse }
     * 
     */
    public StatusResponse createStatusResponse() {
        return new StatusResponse();
    }

    /**
     * Create an instance of {@link ChangeResponse.OperationResult }
     * 
     */
    public ChangeResponse.OperationResult createChangeResponseOperationResult() {
        return new ChangeResponse.OperationResult();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.netnumber.com/titan/view/soap/types", name = "ChangeRequest")
    public JAXBElement<ChangeRequest> createChangeRequest(ChangeRequest value) {
        return new JAXBElement<ChangeRequest>(_ChangeRequest_QNAME, ChangeRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.netnumber.com/titan/view/soap/types", name = "ChangeResponse")
    public JAXBElement<ChangeResponse> createChangeResponse(ChangeResponse value) {
        return new JAXBElement<ChangeResponse>(_ChangeResponse_QNAME, ChangeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.netnumber.com/titan/view/soap/types", name = "StatusRequest")
    public JAXBElement<Object> createStatusRequest(Object value) {
        return new JAXBElement<Object>(_StatusRequest_QNAME, Object.class, null, value);
    }

}
