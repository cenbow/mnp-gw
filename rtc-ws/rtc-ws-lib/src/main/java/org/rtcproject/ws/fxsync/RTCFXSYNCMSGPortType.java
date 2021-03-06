
package org.rtcproject.ws.fxsync;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebService(name = "RTC_FX_SYNC_MSGPortType", targetNamespace = "http://RTC.org/RTC_FX_SYNC_MSG")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface RTCFXSYNCMSGPortType {


    /**
     * 
     * @param fxSYNCREQUEST
     * @return
     *     returns org.rtc.rtc_fx_sync_msg.FXSYNCREQRESTYPE
     */
    @WebMethod(operationName = "RTC_FX_SYNC_REQ")
    @WebResult(name = "FX_SYNC_REQUEST_RES", targetNamespace = "http://RTC.org/RTC_FX_SYNC_MSG", partName = "FX_SYNC_REQUEST_RES")
    public FXSYNCREQRESTYPE rtcFXSYNCREQ(
        @WebParam(name = "FX_SYNC_REQUEST", targetNamespace = "http://RTC.org/RTC_FX_SYNC_MSG", partName = "FX_SYNC_REQUEST")
        FXSYNCREQTYPE fxSYNCREQUEST);

}
