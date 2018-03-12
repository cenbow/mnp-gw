/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.dealer.ws.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;

import com.netnumber.titan.view.soap.api.ViewSOAPProvisioning;
import com.netnumber.titan.view.soap.types.ChangeRequest;
import com.netnumber.titan.view.soap.types.ChangeResponse;
import com.netnumber.titan.view.soap.types.ChangeResponse.OperationResult;
import com.netnumber.titan.view.soap.types.GetRequest;
import com.netnumber.titan.view.soap.types.GetResponse;
import com.netnumber.titan.view.soap.types.StatusResponse;

import cat.mnp.dealer.ws.portin.PortInRequest;
import cat.mnp.dealer.ws.portin.PortInResponse;
import cat.mnp.dealer.ws.portin.PortInService;

/**
 *
 * @author HP-CAT
 */
public class PortInServiceImpl implements PortInService {

	private static final Logger logger = LoggerFactory.getLogger(PortInServiceImpl.class);
	private Map<String, Map<String, String>> userMapper;
	private String successResult;
	private AmqpTemplate amqpTemplate;
	private String errorText;

	public void setUserMapper(Map<String, Map<String, String>> userMapper) {
		this.userMapper = userMapper;
	}

	public void setSuccessResult(String successResult) {
		this.successResult = successResult;
	}

	public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
	@Override
	public PortInResponse portIn(PortInRequest req) {
		logger.info("req=" + req);

		PortInResponse r = new PortInResponse();
		r.setOrderId("Order1");
		r.setStatus("OK");
		return r;
	}

}
