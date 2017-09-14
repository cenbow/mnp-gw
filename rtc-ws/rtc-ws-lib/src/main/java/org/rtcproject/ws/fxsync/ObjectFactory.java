
package org.rtcproject.ws.fxsync;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.rtc.rtc_fx_sync_msg package. 
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

    private final static QName _FXSYNCPROCESS_QNAME = new QName("http://RTC.org/RTC_FX_SYNC_MSG", "FX_SYNC_PROCESS");
    private final static QName _SYNCREQ_QNAME = new QName("http://RTC.org/RTC_FX_SYNC_MSG", "SYNC_REQ");
    private final static QName _FXSYNCREQUESTRES_QNAME = new QName("http://RTC.org/RTC_FX_SYNC_MSG", "FX_SYNC_REQUEST_RES");
    private final static QName _FXSYNCREQUEST_QNAME = new QName("http://RTC.org/RTC_FX_SYNC_MSG", "FX_SYNC_REQUEST");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.rtc.rtc_fx_sync_msg
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FXSYNCREQRESTYPE }
     * 
     */
    public FXSYNCREQRESTYPE createFXSYNCREQRESTYPE() {
        return new FXSYNCREQRESTYPE();
    }

    /**
     * Create an instance of {@link ComplexType }
     * 
     */
    public ComplexType createComplexType() {
        return new ComplexType();
    }

    /**
     * Create an instance of {@link FXSYNCTYPE }
     * 
     */
    public FXSYNCTYPE createFXSYNCTYPE() {
        return new FXSYNCTYPE();
    }

    /**
     * Create an instance of {@link FXSYNCREQTYPE }
     * 
     */
    public FXSYNCREQTYPE createFXSYNCREQTYPE() {
        return new FXSYNCREQTYPE();
    }

    /**
     * Create an instance of {@link FXCIAMTYPE }
     * 
     */
    public FXCIAMTYPE createFXCIAMTYPE() {
        return new FXCIAMTYPE();
    }

    /**
     * Create an instance of {@link SYNCSERVICEDATA }
     * 
     */
    public SYNCSERVICEDATA createSYNCSERVICEDATA() {
        return new SYNCSERVICEDATA();
    }

    /**
     * Create an instance of {@link FXCPTYPE }
     * 
     */
    public FXCPTYPE createFXCPTYPE() {
        return new FXCPTYPE();
    }

    /**
     * Create an instance of {@link FXCPCTYPE }
     * 
     */
    public FXCPCTYPE createFXCPCTYPE() {
        return new FXCPCTYPE();
    }

    /**
     * Create an instance of {@link SYNCREQDATATYPE }
     * 
     */
    public SYNCREQDATATYPE createSYNCREQDATATYPE() {
        return new SYNCREQDATATYPE();
    }

    /**
     * Create an instance of {@link FXCIEMTYPE }
     * 
     */
    public FXCIEMTYPE createFXCIEMTYPE() {
        return new FXCIEMTYPE();
    }

    /**
     * Create an instance of {@link FXSERVICETYPE }
     * 
     */
    public FXSERVICETYPE createFXSERVICETYPE() {
        return new FXSERVICETYPE();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FXSYNCTYPE }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://RTC.org/RTC_FX_SYNC_MSG", name = "FX_SYNC_PROCESS")
    public JAXBElement<FXSYNCTYPE> createFXSYNCPROCESS(FXSYNCTYPE value) {
        return new JAXBElement<FXSYNCTYPE>(_FXSYNCPROCESS_QNAME, FXSYNCTYPE.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComplexType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://RTC.org/RTC_FX_SYNC_MSG", name = "SYNC_REQ")
    public JAXBElement<ComplexType> createSYNCREQ(ComplexType value) {
        return new JAXBElement<ComplexType>(_SYNCREQ_QNAME, ComplexType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FXSYNCREQRESTYPE }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://RTC.org/RTC_FX_SYNC_MSG", name = "FX_SYNC_REQUEST_RES")
    public JAXBElement<FXSYNCREQRESTYPE> createFXSYNCREQUESTRES(FXSYNCREQRESTYPE value) {
        return new JAXBElement<FXSYNCREQRESTYPE>(_FXSYNCREQUESTRES_QNAME, FXSYNCREQRESTYPE.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FXSYNCREQTYPE }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://RTC.org/RTC_FX_SYNC_MSG", name = "FX_SYNC_REQUEST")
    public JAXBElement<FXSYNCREQTYPE> createFXSYNCREQUEST(FXSYNCREQTYPE value) {
        return new JAXBElement<FXSYNCREQTYPE>(_FXSYNCREQUEST_QNAME, FXSYNCREQTYPE.class, null, value);
    }

}
