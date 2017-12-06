/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.clh.ws.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netnumber.titan.view.soap.api.ViewSOAPProvisioning;
import com.netnumber.titan.view.soap.types.ChangeRequest;
import com.netnumber.titan.view.soap.types.ChangeResponse;
import com.netnumber.titan.view.soap.types.ChangeResponse.OperationResult;
import com.netnumber.titan.view.soap.types.GetRequest;
import com.netnumber.titan.view.soap.types.GetResponse;
import com.netnumber.titan.view.soap.types.StatusResponse;

/**
 *
 * @author HP-CAT
 */
public class DummyViewSOAPProvisioningServiceImpl implements ViewSOAPProvisioning {

	private static final Logger logger = LoggerFactory.getLogger(DummyViewSOAPProvisioningServiceImpl.class);

	@Override
	public ChangeResponse change(ChangeRequest changeRequest) {
		ChangeResponse changeResponse = new ChangeResponse();
		changeResponse.setCode(0);
		changeResponse.setMessage("Success");
		OperationResult operationResult = new OperationResult();
		operationResult.setCode(0);
		operationResult.setMessage("Success");
		changeResponse.getOperationResult().add(operationResult);
		return changeResponse;
	}

	@Override
	public GetResponse get(GetRequest getRequest) {
		return null;
	}

	@Override
	public StatusResponse status(Object statusRequest) {
		// <StatusResponse status="AVAILABLE" role="PRIMARY" version="1.3" code="0" message="Success" xmlns="http://www.netnumber.com/titan/view/soap/types"/>
		StatusResponse statusResponse = new StatusResponse();
		statusResponse.setStatus("AVAILABLE");
		statusResponse.setRole("PRIMARY");
		statusResponse.setVersion("1.3");
		statusResponse.setCode(0);
		statusResponse.setMessage("Success");
		return statusResponse;
	}

}
