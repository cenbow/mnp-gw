/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cat.rtc.util;

import java.io.IOException;
import java.io.StringWriter;
import javax.xml.transform.stream.StreamResult;
import org.rtcproject.ws.RtcActivateServiceInputObj;
import org.rtcproject.ws.jaxb.RTCPORTOUT;
import org.springframework.oxm.Marshaller;

/**
 *
 * @author HP
 */
public class RtcWsUtils {
    
    public static String marshalRtcProvisioningServiceInputObj(Marshaller marshaller, RtcActivateServiceInputObj inputObj) throws IOException {
        String xml;
        try (StringWriter sw = new StringWriter()) {
            StreamResult result = new StreamResult(sw);
            marshaller.marshal(inputObj, result);
            xml = sw.toString();
        }
        return xml;
    }
    
    public static String marshalRtcMnpPortOut(Marshaller marshaller, RTCPORTOUT rtcPortOutObj) throws IOException {
        String xml;
        try (StringWriter sw = new StringWriter()) {
            StreamResult result = new StreamResult(sw);
            marshaller.marshal(rtcPortOutObj, result);
            xml = sw.toString();
        }
        return xml;
    }
}
