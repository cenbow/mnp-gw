/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.rtc.ws.mnp;

import cat.rtc.ws.RtcPortActWsClient;
import org.rtcproject.ws.RtcActivateServiceInputObj;
import org.rtcproject.ws.jaxb.RTCACCOUNTCREATERESPONSE;
import org.rtcproject.ws.jaxb.RTCPORTINWS;
import org.rtcproject.ws.mnp.RTCWSPortType;

/**
 *
 * @author HP
 */
public class RtcMnpPortActWsClient extends RtcPortActWsClient {
    
    private RTCWSPortType rtcProvisioning;

    public void setRtcProvisioning(RTCWSPortType rtcProvisioning) {
        this.rtcProvisioning = rtcProvisioning;
    }
    
    @Override
    protected RTCACCOUNTCREATERESPONSE callWs(RtcActivateServiceInputObj inputObj) throws Exception {
        RTCACCOUNTCREATERESPONSE response = rtcProvisioning.portin((RTCPORTINWS) inputObj);
        return response;
    }
}
