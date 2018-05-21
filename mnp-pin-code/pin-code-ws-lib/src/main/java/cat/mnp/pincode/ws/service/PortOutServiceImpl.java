/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.pincode.ws.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import cat.mnp.pincode.ws.portout.CancelPinCodeRequest;
import cat.mnp.pincode.ws.portout.ContactChannelType;
import cat.mnp.pincode.ws.portout.CustomerType;
import cat.mnp.pincode.ws.portout.GeneratePinCodeRequest;
import cat.mnp.pincode.ws.portout.PortOutResponse;
import cat.mnp.pincode.ws.portout.PortOutService;
import cat.mnp.pincode.ws.portout.QueryPinCodeRequest;
import cat.mnp.pincode.ws.portout.QueryPinCodeResponse;
import cat.mnp.pincode.ws.portout.RequestInfoRequest;
import miw.sql.util.OracleArrayValue;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author CATr
 */
public class PortOutServiceImpl implements PortOutService {

	private static final Logger logger = LoggerFactory.getLogger(PortOutServiceImpl.class);
	private AmqpTemplate requestPinCodeAmqpTemplate;
	private AmqpTemplate cancelPinCodeAmqpTemplate;
	private AmqpTemplate requestInfoAmqpTemplate;
	private AmqpTemplate queryPinCodeAmqpTemplate;
	private AmqpTemplate smsAmqpTemplate;
	private final String contactChannelTypeHolder = "{contactChannelType}";
	private String successRespDesc;
	private JdbcTemplate pinCodeJdbcTemplate;

	@Override
	public PortOutResponse generatePinCode(GeneratePinCodeRequest req) {
		logger.debug("Req: {}", req);
		smsAmqpTemplate.convertAndSend(req);

		PortOutResponse resp = new PortOutResponse();
		try {
			// List<Map<String, Object>> rs = pinCodeJdbcTemplate.queryForList("select * from test_param");
			String status = "0";
			if (CustomerType.INDIVIDUAL.equals(req.getCustomerType())) {
				SimpleJdbcCall call = new SimpleJdbcCall(pinCodeJdbcTemplate).withCatalogName("").withProcedureName("check_cross_channel")
						.declareParameters(
								new SqlParameter("i_msisdn", OracleTypes.VARCHAR),
								new SqlParameter("i_channel_type", OracleTypes.VARCHAR),
								new SqlOutParameter("o_status", OracleTypes.VARCHAR),
								new SqlOutParameter("o_callstatus", OracleTypes.NUMBER),
								new SqlOutParameter("o_errmsg", OracleTypes.VARCHAR));
				Map<String, Object> inMap = new LinkedHashMap<String, Object>();
				inMap.put("i_msisdn", req.getMsisdnList().get(0));
				inMap.put("i_channel_type", req.getChannelType());
				Map<String, Object> callResult = call.execute(inMap);
				status = (String) callResult.get("o_status");
				logger.info("callResult={} ",callResult);
				logger.info("msisdn={}, channelType={}, status={}", req.getMsisdnList().get(0), req.getChannelType(), status);
			}
			boolean isGenerated = "1".equals(status);
			if (isGenerated) { // FIXME: check if system already generated pincode -> reject client
				resp.setStatusCode("700");
				resp.setStatusDesc("pincode already generated");

				// also send sms
//				smsAmqpTemplate.convertAndSend(req);


			} else {
				requestPinCodeAmqpTemplate.convertAndSend(req);
				resp.setStatusCode("0");
				resp.setStatusDesc(getSuccessRespDesc(req.getContactChannelType().getDisplayName()));
			}
		} catch (AmqpException ex) {
			logger.error("generatePinCode AmqpException: ", ex);
			resp.setStatusCode("621");
			resp.setStatusDesc(ex.getMessage());
		} catch (Exception ex) {
			logger.error("generatePinCode Exception: ", ex);
			resp.setStatusCode("500");
			resp.setStatusDesc(ex.getMessage());
		}
		return resp;
	}

	@Override
	public PortOutResponse cancelPinCode(CancelPinCodeRequest req) {
		logger.debug("Req: {}", req);
		PortOutResponse resp = new PortOutResponse();
		try {
			cancelPinCodeAmqpTemplate.convertAndSend(req);
			resp.setStatusCode("0");
			resp.setStatusDesc(getSuccessRespDesc(req.getContactChannelType().getDisplayName()));
		} catch (AmqpException ex) {
			logger.error("cancelPinCode AmqpException: ", ex);
			resp.setStatusCode("621");
			resp.setStatusDesc(ex.getMessage());
		} catch (Exception ex) {
			logger.error("cancelPinCode Exception: ", ex);
			resp.setStatusCode("500");
			resp.setStatusDesc(ex.getMessage());
		}
		return resp;
	}

	@Override
	public PortOutResponse requestInfo(RequestInfoRequest req) {
		logger.debug("Req: {}", req);
		PortOutResponse resp = new PortOutResponse();
		try {
			requestInfoAmqpTemplate.convertAndSend(req);
			resp.setStatusCode("0");
			resp.setStatusDesc(getSuccessRespDesc(ContactChannelType.SMS.getDisplayName()));
		} catch (AmqpException ex) {
			logger.error("requestInfo AmqpException: ", ex);
			resp.setStatusCode("621");
			resp.setStatusDesc(ex.getMessage());
		} catch (Exception ex) {
			logger.error("requestInfo Exception: ", ex);
			resp.setStatusCode("500");
			resp.setStatusDesc(ex.getMessage());
		}
		return resp;
	}

	@Override
	public QueryPinCodeResponse queryPinCode(QueryPinCodeRequest req) {
		logger.debug("Req: {}", req);
		Object respObject = queryPinCodeAmqpTemplate.convertSendAndReceive(req);
		logger.debug("Resp: {}", respObject);
		if (respObject == null) {
			return null;
		}

		QueryPinCodeResponse resp = (QueryPinCodeResponse) respObject;
		return resp;
	}

	public void setRequestPinCodeAmqpTemplate(AmqpTemplate requestPinCodeAmqpTemplate) {
		this.requestPinCodeAmqpTemplate = requestPinCodeAmqpTemplate;
	}

	public void setCancelPinCodeAmqpTemplate(AmqpTemplate cancelPinCodeAmqpTemplate) {
		this.cancelPinCodeAmqpTemplate = cancelPinCodeAmqpTemplate;
	}

	public void setRequestInfoAmqpTemplate(AmqpTemplate requestInfoAmqpTemplate) {
		this.requestInfoAmqpTemplate = requestInfoAmqpTemplate;
	}

	public void setQueryPinCodeAmqpTemplate(AmqpTemplate queryPinCodeAmqpTemplate) {
		this.queryPinCodeAmqpTemplate = queryPinCodeAmqpTemplate;
	}

	public String getSuccessRespDesc(String contactChannelType) {
		return successRespDesc.replace(contactChannelTypeHolder, contactChannelType);
	}

	public void setSuccessRespDesc(String successRespDesc) {
		this.successRespDesc = successRespDesc;
	}

	public JdbcTemplate getPinCodeJdbcTemplate() {
		return pinCodeJdbcTemplate;
	}

	public void setPinCodeJdbcTemplate(JdbcTemplate pinCodeJdbcTemplate) {
		this.pinCodeJdbcTemplate = pinCodeJdbcTemplate;
	}

	public AmqpTemplate getSmsAmqpTemplate() {
		return smsAmqpTemplate;
	}

	public void setSmsAmqpTemplate(AmqpTemplate smsAmqpTemplate) {
		this.smsAmqpTemplate = smsAmqpTemplate;
	}

}
