
package org.rtcproject.ws;

import org.rtcproject.ws.jaxb.RTCACCOUNTCREATETYPE;
import org.rtcproject.ws.jaxb.RTCREQUESTUSER;

public interface RtcActivateServiceInputObj {

//    dealer
    public String getRTCDLMODE();
    public void setRTCDLMODE(String value);
    public String getCRMDEALERCODE();
    public void setCRMDEALERCODE(String value);
    public String getDEALERNAME();
    public void setDEALERNAME(String value);
    public String getSHOPNAME();
    public void setSHOPNAME(String value);
    public String getUSERNAME();
    public void setUSERNAME(String value);

//    mnp
    public String getPORTINOPERATOR();
    public void setPORTINOPERATOR(String value);
    public Integer getRTCMNPMODE();
    public void setRTCMNPMODE(Integer value);
    
    public RTCACCOUNTCREATETYPE getRTCCREATEINACCOUNT();
    public void setRTCCREATEINACCOUNT(RTCACCOUNTCREATETYPE value);
    
    public RTCREQUESTUSER getRTCUSER();
    public void setRTCUSER(RTCREQUESTUSER value);

}
