package cat.mnp.dealer.ws.portin;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.cxf.annotations.SchemaValidation;

@WebService(targetNamespace = "http://ws.dealer.mnp.cat", name = "PortInService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@SchemaValidation
public interface PortInService {

	@WebMethod(action = "urn:generate")
	@WebResult(name = "portInResponse", targetNamespace = "http://ws.dealer.mnp.cat", partName = "portInResponse")
	public PortInResponse portIn(PortInRequest req);

}
