/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.dealer.ws.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;

import cat.mnp.dealer.ws.portin.MsisdnInfo;
import cat.mnp.dealer.ws.portin.PortInRequest;
import cat.mnp.dealer.ws.portin.PortInResponse;
import cat.mnp.dealer.ws.portin.PortInService;
import cat.mnp.dealer.ws.portin.PortInStatus;

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
		logger.info("req userId=" + req.getUserId() + ", msisdn size=" + req.getCustomerInfo().getMsisdnInfoList().size());

		// prepare object to call store
		// prepare store result to ws

		PortInResponse r = new PortInResponse();
		List<PortInStatus> portInStatusList = new ArrayList<>();

		for (MsisdnInfo msisdnInfo : req.getCustomerInfo().getMsisdnInfoList()) {
			logger.info(msisdnInfo.getMsisdn());
			PortInStatus portInStatus = new PortInStatus();
			portInStatus.setStatus("0");
			portInStatus.setPortType("EXT");
			portInStatus.setOrderid("00000000001");
			portInStatus.setMsisdn(msisdnInfo.getMsisdn());
			portInStatus.setDesc("description xxx");
			portInStatusList.add(portInStatus);

		}
		r.setPortInStatusList(portInStatusList);
		return r;
	}

}
