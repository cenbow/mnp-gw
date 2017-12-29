/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.pincode.ws.portout;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author CATr
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "statusCode", 
    "statusDesc"
})
@XmlRootElement(name = "portOutResponse")
public class PortOutResponse {

    @XmlElement(required = true)
    protected String statusCode;
    
    @XmlElement
    protected String statusDesc;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    @Override
    public String toString() {
        return "GeneratePinCodeResponse{" + "statusCode=" + statusCode + ", statusDesc=" + statusDesc + '}';
    }
    
}
