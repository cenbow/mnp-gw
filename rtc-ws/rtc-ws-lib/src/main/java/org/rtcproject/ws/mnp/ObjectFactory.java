
package org.rtcproject.ws.mnp;

import org.rtcproject.ws.jaxb.*;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.rtcproject.mnp_ws package. 
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

    private final static QName _RTCSERVICECONFIG_QNAME = new QName("http://rtcproject.org/MNP_WS", "RTC_SERVICE_CONFIG");
    private final static QName _RTCDEALERQUERYRES_QNAME = new QName("http://rtcproject.org/MNP_WS", "RTC_DEALER_QUERY_RES");
    private final static QName _RTCC1RTVERSION_QNAME = new QName("http://rtcproject.org/MNP_WS", "RTC_C1RT_VERSION");
    private final static QName _RTCPORTIN_QNAME = new QName("http://rtcproject.org/MNP_WS", "RTC_PORTIN");
    private final static QName _RTCACCOUNTCREATERES_QNAME = new QName("http://rtcproject.org/MNP_WS", "RTC_ACCOUNT_CREATE_RES");
    private final static QName _RTCPORTQUERY_QNAME = new QName("http://rtcproject.org/MNP_WS", "RTC_PORT_QUERY");
    private final static QName _RTCC1RTTOKEN_QNAME = new QName("http://rtcproject.org/MNP_WS", "RTC_C1RT_TOKEN");
    private final static QName _RTCPORTINFAULTRES_QNAME = new QName("http://rtcproject.org/MNP_WS", "RTC_PORTIN_FAULT_RES");
    private final static QName _RTCDEALEROUTRES_QNAME = new QName("http://rtcproject.org/MNP_WS", "RTC_DEALEROUT_RES");
    private final static QName _RTCPORTPROCESS_QNAME = new QName("http://rtcproject.org/MNP_WS", "RTC_PORT_PROCESS");
    private final static QName _DLPROCESS_QNAME = new QName("http://rtcproject.org/MNP_WS", "DL_PROCESS");
    private final static QName _DEALERPROCESS_QNAME = new QName("http://rtcproject.org/MNP_WS", "DEALER_PROCESS");
    private final static QName _RTCPORTRES_QNAME = new QName("http://rtcproject.org/MNP_WS", "RTC_PORT_RES");
    private final static QName _RTCDEALERIN_QNAME = new QName("http://rtcproject.org/MNP_WS", "RTC_DEALERIN");
    private final static QName _RTCDEALERINFALUTRES_QNAME = new QName("http://rtcproject.org/MNP_WS", "RTC_DEALERIN_FALUT_RES");
    private final static QName _RTCACCOUNTCREATE_QNAME = new QName("http://rtcproject.org/MNP_WS", "RTC_ACCOUNT_CREATE");
    private final static QName _RTCPORTOUTRES_QNAME = new QName("http://rtcproject.org/MNP_WS", "RTC_PORTOUT_RES");
    private final static QName _RTCDEALERQUERY_QNAME = new QName("http://rtcproject.org/MNP_WS", "RTC_DEALER_QUERY");
    private final static QName _RTCDEALEROUT_QNAME = new QName("http://rtcproject.org/MNP_WS", "RTC_DEALEROUT");
    private final static QName _MNPPROCESS_QNAME = new QName("http://rtcproject.org/MNP_WS", "MNP_PROCESS");
    private final static QName _C1RTRES_QNAME = new QName("http://rtcproject.org/MNP_WS", "C1RT_RES");
    private final static QName _PORTOUTPROCESS_QNAME = new QName("http://rtcproject.org/MNP_WS", "PORTOUT_PROCESS");
    private final static QName _Message1_QNAME = new QName("http://rtcproject.org/MNP_WS", "message1");
    private final static QName _SENDQ_QNAME = new QName("http://rtcproject.org/MNP_WS", "SENDQ");
    private final static QName _RTCPORTPOUTFAULTRES_QNAME = new QName("http://rtcproject.org/MNP_WS", "RTC_PORTPOUT_FAULT_RES");
    private final static QName _SYNCREQ_QNAME = new QName("http://rtcproject.org/MNP_WS", "SYNC_REQ");
    private final static QName _RTCPORTOUT_QNAME = new QName("http://rtcproject.org/MNP_WS", "RTC_PORTOUT");
    private final static QName _PORTINPROCESS_QNAME = new QName("http://rtcproject.org/MNP_WS", "PORTIN_PROCESS");
    private final static QName _RTCDEALERINRES_QNAME = new QName("http://rtcproject.org/MNP_WS", "RTC_DEALERIN_RES");
    private final static QName _RTCWSFAULTRES_QNAME = new QName("http://rtcproject.org/MNP_WS", "RTC_WS_FAULT_RES");
    private final static QName _RTCPORTINRES_QNAME = new QName("http://rtcproject.org/MNP_WS", "RTC_PORTIN_RES");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.rtcproject.mnp_ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RTCDEALEROUT }
     * 
     */
    public RTCDEALEROUT createRTCDEALEROUT() {
        return new RTCDEALEROUT();
    }

    /**
     * Create an instance of {@link MNPPROCESSTYPE }
     * 
     */
    public MNPPROCESSTYPE createMNPPROCESSTYPE() {
        return new MNPPROCESSTYPE();
    }

    /**
     * Create an instance of {@link ComplexType2 }
     * 
     */
    public ComplexType2 createComplexType2() {
        return new ComplexType2();
    }

    /**
     * Create an instance of {@link PORTOUTPROCESSTYPE }
     * 
     */
    public PORTOUTPROCESSTYPE createPORTOUTPROCESSTYPE() {
        return new PORTOUTPROCESSTYPE();
    }

    /**
     * Create an instance of {@link C1RTRESELLERVERSIONID }
     * 
     */
    public C1RTRESELLERVERSIONID createC1RTRESELLERVERSIONID() {
        return new C1RTRESELLERVERSIONID();
    }

    /**
     * Create an instance of {@link ComplexType1 }
     * 
     */
    public ComplexType1 createComplexType1() {
        return new ComplexType1();
    }

    /**
     * Create an instance of {@link ComplexType7 }
     * 
     */
    public ComplexType7 createComplexType7() {
        return new ComplexType7();
    }

    /**
     * Create an instance of {@link RTCTERMINATESERVICERESPONSE }
     * 
     */
    public RTCTERMINATESERVICERESPONSE createRTCTERMINATESERVICERESPONSE() {
        return new RTCTERMINATESERVICERESPONSE();
    }

    /**
     * Create an instance of {@link RTCPORTOUT }
     * 
     */
    public RTCPORTOUT createRTCPORTOUT() {
        return new RTCPORTOUT();
    }

    /**
     * Create an instance of {@link RTCPORTIN }
     * 
     */
    public RTCPORTIN createRTCPORTIN() {
        return new RTCPORTIN();
    }

    /**
     * Create an instance of {@link RTCACCOUNTCREATERESPONSE }
     * 
     */
    public RTCACCOUNTCREATERESPONSE createRTCACCOUNTCREATERESPONSE() {
        return new RTCACCOUNTCREATERESPONSE();
    }

    /**
     * Create an instance of {@link RTCWSFAULT }
     * 
     */
    public RTCWSFAULT createRTCWSFAULT() {
        return new RTCWSFAULT();
    }

    /**
     * Create an instance of {@link RTCENVCONFIG }
     * 
     */
    public RTCENVCONFIG createRTCENVCONFIG() {
        return new RTCENVCONFIG();
    }

    /**
     * Create an instance of {@link RTCPORTINWS }
     * 
     */
    public RTCPORTINWS createRTCPORTINWS() {
        return new RTCPORTINWS();
    }

    /**
     * Create an instance of {@link RTCPORT }
     * 
     */
    public RTCPORT createRTCPORT() {
        return new RTCPORT();
    }

    /**
     * Create an instance of {@link RTCDLQRES }
     * 
     */
    public RTCDLQRES createRTCDLQRES() {
        return new RTCDLQRES();
    }

    /**
     * Create an instance of {@link C1RTTOKEN }
     * 
     */
    public C1RTTOKEN createC1RTTOKEN() {
        return new C1RTTOKEN();
    }

    /**
     * Create an instance of {@link DLINPROCESS }
     * 
     */
    public DLINPROCESS createDLINPROCESS() {
        return new DLINPROCESS();
    }

    /**
     * Create an instance of {@link DLPROCESSTYPE }
     * 
     */
    public DLPROCESSTYPE createDLPROCESSTYPE() {
        return new DLPROCESSTYPE();
    }

    /**
     * Create an instance of {@link RTCQUERYRES }
     * 
     */
    public RTCQUERYRES createRTCQUERYRES() {
        return new RTCQUERYRES();
    }

    /**
     * Create an instance of {@link RTCDEALERIN }
     * 
     */
    public RTCDEALERIN createRTCDEALERIN() {
        return new RTCDEALERIN();
    }

    /**
     * Create an instance of {@link RTCACCOUNTCREATETYPE }
     * 
     */
    public RTCACCOUNTCREATETYPE createRTCACCOUNTCREATETYPE() {
        return new RTCACCOUNTCREATETYPE();
    }

    /**
     * Create an instance of {@link FXPACKAGE }
     * 
     */
    public FXPACKAGE createFXPACKAGE() {
        return new FXPACKAGE();
    }

    /**
     * Create an instance of {@link ComplexType }
     * 
     */
    public ComplexType createComplexType() {
        return new ComplexType();
    }

    /**
     * Create an instance of {@link ComplexType6 }
     * 
     */
    public ComplexType6 createComplexType6() {
        return new ComplexType6();
    }

    /**
     * Create an instance of {@link ComplexType5 }
     * 
     */
    public ComplexType5 createComplexType5() {
        return new ComplexType5();
    }

    /**
     * Create an instance of {@link FXCOMPONENT }
     * 
     */
    public FXCOMPONENT createFXCOMPONENT() {
        return new FXCOMPONENT();
    }

    /**
     * Create an instance of {@link ComplexType4 }
     * 
     */
    public ComplexType4 createComplexType4() {
        return new ComplexType4();
    }

    /**
     * Create an instance of {@link ComplexType3 }
     * 
     */
    public ComplexType3 createComplexType3() {
        return new ComplexType3();
    }

    /**
     * Create an instance of {@link BILLADDRESS }
     * 
     */
    public BILLADDRESS createBILLADDRESS() {
        return new BILLADDRESS();
    }

    /**
     * Create an instance of {@link RTCREQUESTUSER }
     * 
     */
    public RTCREQUESTUSER createRTCREQUESTUSER() {
        return new RTCREQUESTUSER();
    }

    /**
     * Create an instance of {@link CORPORATION }
     * 
     */
    public CORPORATION createCORPORATION() {
        return new CORPORATION();
    }

    /**
     * Create an instance of {@link RTCOFFER }
     * 
     */
    public RTCOFFER createRTCOFFER() {
        return new RTCOFFER();
    }

    /**
     * Create an instance of {@link PERSONAL }
     * 
     */
    public PERSONAL createPERSONAL() {
        return new PERSONAL();
    }

    /**
     * Create an instance of {@link RTCSERVICE }
     * 
     */
    public RTCSERVICE createRTCSERVICE() {
        return new RTCSERVICE();
    }

    /**
     * Create an instance of {@link RTCACCOUNT }
     * 
     */
    public RTCACCOUNT createRTCACCOUNT() {
        return new RTCACCOUNT();
    }

    /**
     * Create an instance of {@link RTCTERMINATESERVICE }
     * 
     */
    public RTCTERMINATESERVICE createRTCTERMINATESERVICE() {
        return new RTCTERMINATESERVICE();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RTCENVCONFIG }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "RTC_SERVICE_CONFIG")
    public JAXBElement<RTCENVCONFIG> createRTCSERVICECONFIG(RTCENVCONFIG value) {
        return new JAXBElement<RTCENVCONFIG>(_RTCSERVICECONFIG_QNAME, RTCENVCONFIG.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RTCDLQRES }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "RTC_DEALER_QUERY_RES")
    public JAXBElement<RTCDLQRES> createRTCDEALERQUERYRES(RTCDLQRES value) {
        return new JAXBElement<RTCDLQRES>(_RTCDEALERQUERYRES_QNAME, RTCDLQRES.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link C1RTRESELLERVERSIONID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "RTC_C1RT_VERSION")
    public JAXBElement<C1RTRESELLERVERSIONID> createRTCC1RTVERSION(C1RTRESELLERVERSIONID value) {
        return new JAXBElement<C1RTRESELLERVERSIONID>(_RTCC1RTVERSION_QNAME, C1RTRESELLERVERSIONID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RTCPORTINWS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "RTC_PORTIN")
    public JAXBElement<RTCPORTINWS> createRTCPORTIN(RTCPORTINWS value) {
        return new JAXBElement<RTCPORTINWS>(_RTCPORTIN_QNAME, RTCPORTINWS.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RTCACCOUNTCREATERESPONSE }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "RTC_ACCOUNT_CREATE_RES")
    public JAXBElement<RTCACCOUNTCREATERESPONSE> createRTCACCOUNTCREATERES(RTCACCOUNTCREATERESPONSE value) {
        return new JAXBElement<RTCACCOUNTCREATERESPONSE>(_RTCACCOUNTCREATERES_QNAME, RTCACCOUNTCREATERESPONSE.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RTCPORT }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "RTC_PORT_QUERY")
    public JAXBElement<RTCPORT> createRTCPORTQUERY(RTCPORT value) {
        return new JAXBElement<RTCPORT>(_RTCPORTQUERY_QNAME, RTCPORT.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link C1RTTOKEN }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "RTC_C1RT_TOKEN")
    public JAXBElement<C1RTTOKEN> createRTCC1RTTOKEN(C1RTTOKEN value) {
        return new JAXBElement<C1RTTOKEN>(_RTCC1RTTOKEN_QNAME, C1RTTOKEN.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RTCWSFAULT }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "RTC_PORTIN_FAULT_RES")
    public JAXBElement<RTCWSFAULT> createRTCPORTINFAULTRES(RTCWSFAULT value) {
        return new JAXBElement<RTCWSFAULT>(_RTCPORTINFAULTRES_QNAME, RTCWSFAULT.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RTCTERMINATESERVICERESPONSE }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "RTC_DEALEROUT_RES")
    public JAXBElement<RTCTERMINATESERVICERESPONSE> createRTCDEALEROUTRES(RTCTERMINATESERVICERESPONSE value) {
        return new JAXBElement<RTCTERMINATESERVICERESPONSE>(_RTCDEALEROUTRES_QNAME, RTCTERMINATESERVICERESPONSE.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RTCQUERYRES }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "RTC_PORT_PROCESS")
    public JAXBElement<RTCQUERYRES> createRTCPORTPROCESS(RTCQUERYRES value) {
        return new JAXBElement<RTCQUERYRES>(_RTCPORTPROCESS_QNAME, RTCQUERYRES.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DLPROCESSTYPE }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "DL_PROCESS")
    public JAXBElement<DLPROCESSTYPE> createDLPROCESS(DLPROCESSTYPE value) {
        return new JAXBElement<DLPROCESSTYPE>(_DLPROCESS_QNAME, DLPROCESSTYPE.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DLINPROCESS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "DEALER_PROCESS")
    public JAXBElement<DLINPROCESS> createDEALERPROCESS(DLINPROCESS value) {
        return new JAXBElement<DLINPROCESS>(_DEALERPROCESS_QNAME, DLINPROCESS.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RTCQUERYRES }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "RTC_PORT_RES")
    public JAXBElement<RTCQUERYRES> createRTCPORTRES(RTCQUERYRES value) {
        return new JAXBElement<RTCQUERYRES>(_RTCPORTRES_QNAME, RTCQUERYRES.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RTCDEALERIN }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "RTC_DEALERIN")
    public JAXBElement<RTCDEALERIN> createRTCDEALERIN(RTCDEALERIN value) {
        return new JAXBElement<RTCDEALERIN>(_RTCDEALERIN_QNAME, RTCDEALERIN.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RTCWSFAULT }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "RTC_DEALERIN_FALUT_RES")
    public JAXBElement<RTCWSFAULT> createRTCDEALERINFALUTRES(RTCWSFAULT value) {
        return new JAXBElement<RTCWSFAULT>(_RTCDEALERINFALUTRES_QNAME, RTCWSFAULT.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RTCACCOUNTCREATETYPE }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "RTC_ACCOUNT_CREATE")
    public JAXBElement<RTCACCOUNTCREATETYPE> createRTCACCOUNTCREATE(RTCACCOUNTCREATETYPE value) {
        return new JAXBElement<RTCACCOUNTCREATETYPE>(_RTCACCOUNTCREATE_QNAME, RTCACCOUNTCREATETYPE.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RTCTERMINATESERVICERESPONSE }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "RTC_PORTOUT_RES")
    public JAXBElement<RTCTERMINATESERVICERESPONSE> createRTCPORTOUTRES(RTCTERMINATESERVICERESPONSE value) {
        return new JAXBElement<RTCTERMINATESERVICERESPONSE>(_RTCPORTOUTRES_QNAME, RTCTERMINATESERVICERESPONSE.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RTCPORT }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "RTC_DEALER_QUERY")
    public JAXBElement<RTCPORT> createRTCDEALERQUERY(RTCPORT value) {
        return new JAXBElement<RTCPORT>(_RTCDEALERQUERY_QNAME, RTCPORT.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RTCDEALEROUT }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "RTC_DEALEROUT")
    public JAXBElement<RTCDEALEROUT> createRTCDEALEROUT(RTCDEALEROUT value) {
        return new JAXBElement<RTCDEALEROUT>(_RTCDEALEROUT_QNAME, RTCDEALEROUT.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MNPPROCESSTYPE }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "MNP_PROCESS")
    public JAXBElement<MNPPROCESSTYPE> createMNPPROCESS(MNPPROCESSTYPE value) {
        return new JAXBElement<MNPPROCESSTYPE>(_MNPPROCESS_QNAME, MNPPROCESSTYPE.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComplexType2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "C1RT_RES")
    public JAXBElement<ComplexType2> createC1RTRES(ComplexType2 value) {
        return new JAXBElement<ComplexType2>(_C1RTRES_QNAME, ComplexType2 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PORTOUTPROCESSTYPE }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "PORTOUT_PROCESS")
    public JAXBElement<PORTOUTPROCESSTYPE> createPORTOUTPROCESS(PORTOUTPROCESSTYPE value) {
        return new JAXBElement<PORTOUTPROCESSTYPE>(_PORTOUTPROCESS_QNAME, PORTOUTPROCESSTYPE.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link C1RTRESELLERVERSIONID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "message1")
    public JAXBElement<C1RTRESELLERVERSIONID> createMessage1(C1RTRESELLERVERSIONID value) {
        return new JAXBElement<C1RTRESELLERVERSIONID>(_Message1_QNAME, C1RTRESELLERVERSIONID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComplexType1 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "SENDQ")
    public JAXBElement<ComplexType1> createSENDQ(ComplexType1 value) {
        return new JAXBElement<ComplexType1>(_SENDQ_QNAME, ComplexType1 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RTCTERMINATESERVICERESPONSE }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "RTC_PORTPOUT_FAULT_RES")
    public JAXBElement<RTCTERMINATESERVICERESPONSE> createRTCPORTPOUTFAULTRES(RTCTERMINATESERVICERESPONSE value) {
        return new JAXBElement<RTCTERMINATESERVICERESPONSE>(_RTCPORTPOUTFAULTRES_QNAME, RTCTERMINATESERVICERESPONSE.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComplexType7 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "SYNC_REQ")
    public JAXBElement<ComplexType7> createSYNCREQ(ComplexType7 value) {
        return new JAXBElement<ComplexType7>(_SYNCREQ_QNAME, ComplexType7 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RTCPORTOUT }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "RTC_PORTOUT")
    public JAXBElement<RTCPORTOUT> createRTCPORTOUT(RTCPORTOUT value) {
        return new JAXBElement<RTCPORTOUT>(_RTCPORTOUT_QNAME, RTCPORTOUT.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RTCPORTIN }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "PORTIN_PROCESS")
    public JAXBElement<RTCPORTIN> createPORTINPROCESS(RTCPORTIN value) {
        return new JAXBElement<RTCPORTIN>(_PORTINPROCESS_QNAME, RTCPORTIN.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RTCACCOUNTCREATERESPONSE }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "RTC_DEALERIN_RES")
    public JAXBElement<RTCACCOUNTCREATERESPONSE> createRTCDEALERINRES(RTCACCOUNTCREATERESPONSE value) {
        return new JAXBElement<RTCACCOUNTCREATERESPONSE>(_RTCDEALERINRES_QNAME, RTCACCOUNTCREATERESPONSE.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RTCWSFAULT }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "RTC_WS_FAULT_RES")
    public JAXBElement<RTCWSFAULT> createRTCWSFAULTRES(RTCWSFAULT value) {
        return new JAXBElement<RTCWSFAULT>(_RTCWSFAULTRES_QNAME, RTCWSFAULT.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RTCACCOUNTCREATERESPONSE }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtcproject.org/MNP_WS", name = "RTC_PORTIN_RES")
    public JAXBElement<RTCACCOUNTCREATERESPONSE> createRTCPORTINRES(RTCACCOUNTCREATERESPONSE value) {
        return new JAXBElement<RTCACCOUNTCREATERESPONSE>(_RTCPORTINRES_QNAME, RTCACCOUNTCREATERESPONSE.class, null, value);
    }

}
