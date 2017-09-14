package cat.mnp.mvno.ws.order.generator;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import org.apache.cxf.annotations.SchemaValidation;

@WebService(targetNamespace = "http://ws.mvno.mnp.cat", name = "OrderIdGeneratorService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@SchemaValidation
public interface OrderIdGeneratorService {

    @WebMethod(action = "urn:generate")
    @WebResult(name = "generateOrderIdResponse", targetNamespace = "http://ws.mvno.mnp.cat", partName = "generateOrderIdResponse")
    public GenerateOrderIdResponse generate(GenerateOrderIdRequest req);
    
}
