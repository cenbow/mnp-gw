
package cat.sd.domain;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cat.sd.domain package. 
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

    private final static QName _OfferInfo_QNAME = new QName("http://domain.sd.cat/", "OfferInfo");
    private final static QName _GetCoreBalance_QNAME = new QName("http://domain.sd.cat/", "getCoreBalance");
    private final static QName _GetCoreBalanceResponse_QNAME = new QName("http://domain.sd.cat/", "getCoreBalanceResponse");
    private final static QName _GetInfo_QNAME = new QName("http://domain.sd.cat/", "getInfo");
    private final static QName _GetInfoResponse_QNAME = new QName("http://domain.sd.cat/", "getInfoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cat.sd.domain
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OfferInfo }
     * 
     */
    public OfferInfo createOfferInfo() {
        return new OfferInfo();
    }

    /**
     * Create an instance of {@link GetCoreBalance }
     * 
     */
    public GetCoreBalance createGetCoreBalance() {
        return new GetCoreBalance();
    }

    /**
     * Create an instance of {@link GetCoreBalanceResponse }
     * 
     */
    public GetCoreBalanceResponse createGetCoreBalanceResponse() {
        return new GetCoreBalanceResponse();
    }

    /**
     * Create an instance of {@link GetInfo }
     * 
     */
    public GetInfo createGetInfo() {
        return new GetInfo();
    }

    /**
     * Create an instance of {@link GetInfoResponse }
     * 
     */
    public GetInfoResponse createGetInfoResponse() {
        return new GetInfoResponse();
    }

    /**
     * Create an instance of {@link SubscriberInfoRequest }
     * 
     */
    public SubscriberInfoRequest createSubscriberInfoRequest() {
        return new SubscriberInfoRequest();
    }

    /**
     * Create an instance of {@link SubscriberInfoResponse }
     * 
     */
    public SubscriberInfoResponse createSubscriberInfoResponse() {
        return new SubscriberInfoResponse();
    }

    /**
     * Create an instance of {@link BalanceInfo }
     * 
     */
    public BalanceInfo createBalanceInfo() {
        return new BalanceInfo();
    }

    /**
     * Create an instance of {@link SubScriberCoreBalanceResponse }
     * 
     */
    public SubScriberCoreBalanceResponse createSubScriberCoreBalanceResponse() {
        return new SubScriberCoreBalanceResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OfferInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://domain.sd.cat/", name = "OfferInfo")
    public JAXBElement<OfferInfo> createOfferInfo(OfferInfo value) {
        return new JAXBElement<OfferInfo>(_OfferInfo_QNAME, OfferInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCoreBalance }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://domain.sd.cat/", name = "getCoreBalance")
    public JAXBElement<GetCoreBalance> createGetCoreBalance(GetCoreBalance value) {
        return new JAXBElement<GetCoreBalance>(_GetCoreBalance_QNAME, GetCoreBalance.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCoreBalanceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://domain.sd.cat/", name = "getCoreBalanceResponse")
    public JAXBElement<GetCoreBalanceResponse> createGetCoreBalanceResponse(GetCoreBalanceResponse value) {
        return new JAXBElement<GetCoreBalanceResponse>(_GetCoreBalanceResponse_QNAME, GetCoreBalanceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://domain.sd.cat/", name = "getInfo")
    public JAXBElement<GetInfo> createGetInfo(GetInfo value) {
        return new JAXBElement<GetInfo>(_GetInfo_QNAME, GetInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://domain.sd.cat/", name = "getInfoResponse")
    public JAXBElement<GetInfoResponse> createGetInfoResponse(GetInfoResponse value) {
        return new JAXBElement<GetInfoResponse>(_GetInfoResponse_QNAME, GetInfoResponse.class, null, value);
    }

}
