package cat.rtc.ws;

import cat.dealer.ibacss.dao.TerminateServiceDao;
import cat.dealer.ibacss.domain.BatchServiceTerminate;
import cat.dealer.ibacss.domain.MnpRtcProvHeaders;
import cat.mnp.mq.core.MsgHandlerBase;
import java.io.File;
import java.util.Map;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.rtcproject.ws.jaxb.RTCPORTOUT;
import org.rtcproject.ws.jaxb.RTCTERMINATESERVICE;
import org.rtcproject.ws.jaxb.RTCTERMINATESERVICERESPONSE;
import org.rtcproject.ws.mnp.RTCWSPortType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

public class RtcPortDeactWsClient extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(RtcPortDeactWsClient.class);
    private AmqpTemplate amqpTemplate;
    private AmqpTemplate errorAmqpTemplate;
    private MessageProperties msgProperties;

    private Integer countryCode;

    private RTCWSPortType rtcProvisioning;
    private Map<String, Object> requestContextMap;
    private RTCPORTOUT inputObj;
    private String successResponse;
    private TerminateServiceDao dao;
    private String dateFormat;

    public AmqpTemplate getAmqpTemplate() {
        return amqpTemplate;
    }

    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public AmqpTemplate getErrorAmqpTemplate() {
        return errorAmqpTemplate;
    }

    public void setErrorAmqpTemplate(AmqpTemplate errorAmqpTemplate) {
        this.errorAmqpTemplate = errorAmqpTemplate;
    }

    public MessageProperties getMsgProperties() {
        return msgProperties;
    }

    public void setMsgProperties(MessageProperties msgProperties) {
        this.msgProperties = msgProperties;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public void setRtcProvisioning(RTCWSPortType rtcProvisioning) {
        this.rtcProvisioning = rtcProvisioning;
    }

    public Map<String, Object> getRequestContextMap() {
        return requestContextMap;
    }

    public void setRequestContextMap(Map<String, Object> requestContextMap) {
        this.requestContextMap = requestContextMap;
    }

    public RTCPORTOUT getInputObj() {
        return inputObj;
    }

    public void setInputObj(RTCPORTOUT inputObj) {
        this.inputObj = inputObj;
    }

    public String getSuccessResponse() {
        return successResponse;
    }

    public void setSuccessResponse(String successResponse) {
        this.successResponse = successResponse;
    }

    public TerminateServiceDao getDao() {
        return dao;
    }

    public void setDao(TerminateServiceDao dao) {
        this.dao = dao;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public void processMsg(Message msg) throws Exception {
        String msgString = new String(msg.getBody());
        String msgId = msg.getMessageProperties().getReceivedRoutingKey();
        Map<String, Object> mqHeaders = msg.getMessageProperties().getHeaders();
        logger.info("Received mqHeaders: {}", mqHeaders);

        Long serviceId = (Long) mqHeaders.get(MnpRtcProvHeaders.ServiceId.name());
        BatchServiceTerminate svc = dao.get(serviceId);

        RTCTERMINATESERVICE terminateSV = new RTCTERMINATESERVICE();
        inputObj.setRTCPORTOUTSERVICE(terminateSV);

        terminateSV.setMSISDN(countryCode + svc.getPropertyOne());
        terminateSV.setDELETESERVICE(true);
        terminateSV.setDATEINACTIVE(DateFormatUtils.format(svc.getEndDate(), dateFormat));

        inputObj.setPORTOUTOPERATOR(svc.getRecipientOperator());

        File file;
        try {
            logger.info("Calling rtc ws");
            RTCTERMINATESERVICERESPONSE response = rtcProvisioning.portout(inputObj);

            // 3.Push in rabbitMQ (update IBACSS Status)
            msgProperties.setHeader(MnpRtcProvHeaders.ServiceId.name(), serviceId);
            msgProperties.setHeader(MnpRtcProvHeaders.Msisdn.name(), "0" + svc.getPropertyOne());
            msgProperties.setHeader(MnpRtcProvHeaders.RtcTransId.name(), response.getRTCTERMINATETRANSID());
            msgProperties.setHeader(MnpRtcProvHeaders.RtcStatus.name(), response.getRTCTRANSSTATUS());
            msgProperties.setHeader(MnpRtcProvHeaders.RtcTransMsg.name(), response.getRTCTRANSMSG());

            Message newMsg = new Message(msgString.getBytes(), msgProperties);

            logger.debug("Ws response: {}", msgProperties.getHeaders());
            if (response.getRTCTRANSSTATUS().equals(successResponse)) {//no error
                amqpTemplate.send(msgId, newMsg);
            } else {//has error
                logger.error("Error detected while calling ws: {}", msgProperties.getHeaders());
                errorAmqpTemplate.send(msgId, newMsg);
            }
        } catch (Exception e) {
            msgProperties.setHeader(MnpRtcProvHeaders.ServiceId.name(), serviceId);
            msgProperties.setHeader(MnpRtcProvHeaders.Msisdn.name(), "0" + svc.getPropertyOne());
            msgProperties.setHeader(MnpRtcProvHeaders.RtcTransId.name(), "");
            msgProperties.setHeader(MnpRtcProvHeaders.RtcStatus.name(), e.getMessage());
            
            Message newMsg = new Message(msgString.getBytes(), msgProperties);
            logger.error(String.format("Error detected while calling rtc ws: %s", msgProperties.getHeaders()), e);
            errorAmqpTemplate.send(msgId, newMsg);
        }
    }
}
