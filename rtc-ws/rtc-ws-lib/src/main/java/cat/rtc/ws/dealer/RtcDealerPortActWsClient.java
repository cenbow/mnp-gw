/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.rtc.ws.dealer;

import cat.rtc.ws.RtcPortActWsClient;
import org.rtcproject.ws.RtcActivateServiceInputObj;
import org.rtcproject.ws.dealer.RTCWSPortType;
import org.rtcproject.ws.jaxb.RTCACCOUNTCREATERESPONSE;
import org.rtcproject.ws.jaxb.RTCDEALERIN;

/**
 *
 * @author HP
 */
public class RtcDealerPortActWsClient extends RtcPortActWsClient {
    
    private RTCWSPortType rtcProvisioning;

    public void setRtcProvisioning(RTCWSPortType rtcProvisioning) {
        this.rtcProvisioning = rtcProvisioning;
    }
    
    @Override
    protected RTCACCOUNTCREATERESPONSE callWs(RtcActivateServiceInputObj inputObj) throws Exception {
        RTCACCOUNTCREATERESPONSE response = rtcProvisioning.rtcDEALERIN((RTCDEALERIN) inputObj);
        return response;
    }
    
}
