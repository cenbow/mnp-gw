/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.pincode.ws.service;

import cat.mnp.pincode.ws.portout.CancelPinCodeRequest;
import cat.mnp.pincode.ws.portout.ContactChannelType;
import cat.mnp.pincode.ws.portout.GeneratePinCodeRequest;
import cat.mnp.pincode.ws.portout.PortOutResponse;
import cat.mnp.pincode.ws.portout.PortOutService;
import cat.mnp.pincode.ws.portout.QueryPinCodeRequest;
import cat.mnp.pincode.ws.portout.QueryPinCodeResponse;
import cat.mnp.pincode.ws.portout.RequestInfoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;

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
    private final String contactChannelTypeHolder = "{contactChannelType}";
    private String successRespDesc;

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
    
    @Override
    public PortOutResponse generatePinCode(GeneratePinCodeRequest req) {
        logger.debug("Req: {}", req);
        
        PortOutResponse resp = new PortOutResponse();
        try {
            requestPinCodeAmqpTemplate.convertAndSend(req);
            resp.setStatusCode("0");
            resp.setStatusDesc(getSuccessRespDesc(req.getContactChannelType().getDisplayName()));
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
    
}
