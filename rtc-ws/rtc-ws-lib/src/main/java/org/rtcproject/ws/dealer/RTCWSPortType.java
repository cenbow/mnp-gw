
package org.rtcproject.ws.dealer;

import org.rtcproject.ws.jaxb.*;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Holder;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebService(name = "RTC_WSPortType", targetNamespace = "http://rtcproject.org/DL_WS")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface RTCWSPortType {


    /**
     * 
     * @param rtcDEALERIN
     * @return
     *     returns org.rtcproject.mnp_ws.RTCACCOUNTCREATERESPONSE
     * @throws RTCDEALERINFault1
     */
    @WebMethod(operationName = "RTC_DEALERIN")
    @WebResult(name = "RTC_DEALERIN_RES", targetNamespace = "http://rtcproject.org/DL_WS", partName = "RTC_DEALERIN_RES")
    public RTCACCOUNTCREATERESPONSE rtcDEALERIN(
        @WebParam(name = "RTC_DEALERIN", targetNamespace = "http://rtcproject.org/DL_WS", partName = "RTC_DEALERIN")
        RTCDEALERIN rtcDEALERIN)
        throws RTCDEALERINFault1
    ;

    /**
     * 
     * @param completeDATE
     * @param rtTERMINATE
     * @param msisdn
     * @param rtcPROCESS
     * @param omTERMINATE
     * @param dlID
     * @param omIDEL
     * @param portSTATUS
     * @param omACTIVE
     * @param rtACTIVE
     * @param omMSG2
     * @param omMSG1
     * @param omtransI
     * @param omtransT
     * @param createDATE
     * @param rtcTRANS
     * @param rtCREATE
     * @param omtransA
     */
    @WebMethod(operationName = "RTC_DEALER_QUERY")
    @RequestWrapper(localName = "RTC_DEALER_QUERY", targetNamespace = "http://rtcproject.org/DL_WS", className = "org.rtcproject.dl_ws.RTCPORT")
    @ResponseWrapper(localName = "RTC_DEALER_QUERY_RES", targetNamespace = "http://rtcproject.org/DL_WS", className = "org.rtcproject.dl_ws.RTCDLQRES")
    public void rtcDEALERQUERY(
        @WebParam(name = "RTC_TRANS", targetNamespace = "")
        String rtcTRANS,
        @WebParam(name = "DL_ID", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> dlID,
        @WebParam(name = "OMTRANS_I", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> omtransI,
        @WebParam(name = "OMTRANS_A", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> omtransA,
        @WebParam(name = "OMTRANS_T", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> omtransT,
        @WebParam(name = "OM_MSG1", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> omMSG1,
        @WebParam(name = "OM_MSG2", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> omMSG2,
        @WebParam(name = "RTC_PROCESS", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> rtcPROCESS,
        @WebParam(name = "OM_IDEL", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> omIDEL,
        @WebParam(name = "OM_ACTIVE", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> omACTIVE,
        @WebParam(name = "OM_TERMINATE", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> omTERMINATE,
        @WebParam(name = "RT_CREATE", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> rtCREATE,
        @WebParam(name = "RT_ACTIVE", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> rtACTIVE,
        @WebParam(name = "RT_TERMINATE", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> rtTERMINATE,
        @WebParam(name = "MSISDN", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> msisdn,
        @WebParam(name = "CREATE_DATE", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> createDATE,
        @WebParam(name = "COMPLETE_DATE", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> completeDATE,
        @WebParam(name = "PORT_STATUS", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> portSTATUS);

}
