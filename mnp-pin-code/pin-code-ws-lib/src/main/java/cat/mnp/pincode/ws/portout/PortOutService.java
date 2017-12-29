/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.pincode.ws.portout;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import org.apache.cxf.annotations.SchemaValidation;

/**
 *
 * @author CATr
 */
@WebService(targetNamespace = "http://portout.ws.pincode.mnp.cat", name = "PortOutService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@SchemaValidation
public interface PortOutService {
    
    @WebMethod(action = "urn:generatePinCode")
    @WebResult(name = "portOutResponse", targetNamespace = "http://portout.ws.pincode.mnp.cat", partName = "generatePinCodeRequest")
    public PortOutResponse generatePinCode(GeneratePinCodeRequest req);
    
    @WebMethod(action = "urn:cancelPinCode")
    @WebResult(name = "portOutResponse", targetNamespace = "http://portout.ws.pincode.mnp.cat", partName = "cancelPinCodeRequest")
    public PortOutResponse cancelPinCode(CancelPinCodeRequest req);
    
    @WebMethod(action = "urn:requestInfo")
    @WebResult(name = "portOutResponse", targetNamespace = "http://portout.ws.pincode.mnp.cat", partName = "requestInfoRequest")
    public PortOutResponse requestInfo(RequestInfoRequest req);
    
    @WebMethod(action = "urn:queryPinCode")
    @WebResult(name = "queryPinCodeResponse", targetNamespace = "http://portout.ws.pincode.mnp.cat", partName = "queryPinCodeRequest")
    public QueryPinCodeResponse queryPinCode(QueryPinCodeRequest req);
    
}
