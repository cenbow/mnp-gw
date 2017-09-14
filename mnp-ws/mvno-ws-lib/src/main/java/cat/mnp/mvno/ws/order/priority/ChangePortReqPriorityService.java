package cat.mnp.mvno.ws.order.priority;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;

/**
 * This class was generated by Apache CXF 3.0.1
 * 2014-11-25T21:01:25.653+07:00
 * Generated source version: 3.0.1
 * 
 */
@WebService(targetNamespace = "http://ws.mvno.mnp.cat", name = "ChangePortReqPriorityService")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface ChangePortReqPriorityService {

    @WebMethod(action = "urn:changePriority")
    @Action(input = "urn:changePriority", output = "urn:changePriorityResponse")
    @WebResult(name = "changePriorityResponse", targetNamespace = "http://ws.mvno.mnp.cat", partName = "parameters")
    public ChangePriorityResponse changePriority(
        @WebParam(partName = "parameters", name = "orderIdList", targetNamespace = "http://ws.mvno.mnp.cat")
        OrderIdList parameters
    );
}
