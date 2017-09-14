
package org.rtcproject.ws.jaxb;


import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "RTC_PORTIN_FAULT_RES", targetNamespace = "http://rtcproject.org/MNP_WS")
public class PORTINFault1
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private RTCWSFAULT faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public PORTINFault1(String message, RTCWSFAULT faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public PORTINFault1(String message, RTCWSFAULT faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: org.rtcproject.mnp_ws.RTCWSFAULT
     */
    public RTCWSFAULT getFaultInfo() {
        return faultInfo;
    }

}
