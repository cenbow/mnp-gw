package cat.rtc.ws;

import cat.dealer.ibacss.domain.MnpRtcProvHeaders;
import cat.mnp.mq.core.MsgHandlerBase;
import java.util.Map;
import org.rtcproject.ws.fxsync.FXSYNCREQRESTYPE;
import org.rtcproject.ws.fxsync.FXSYNCREQTYPE;
import org.rtcproject.ws.fxsync.RTCFXSYNCMSGPortType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

public class SyncFxWsClient extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(SyncFxWsClient.class);
    private AmqpTemplate errorAmqpTemplate;
    private MessageProperties msgProperties;

    private Integer countryCode;

    private String successResponse;
    private FXSYNCREQTYPE fxSynReqType;
    private RTCFXSYNCMSGPortType rtcFxSync;

    public void setErrorAmqpTemplate(AmqpTemplate errorAmqpTemplate) {
        this.errorAmqpTemplate = errorAmqpTemplate;
    }

    public void setMsgProperties(MessageProperties msgProperties) {
        this.msgProperties = msgProperties;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public void setSuccessResponse(String successResponse) {
        this.successResponse = successResponse;
    }

    public FXSYNCREQTYPE getFxSynReqType() {
        return fxSynReqType;
    }

    public void setFxSynReqType(FXSYNCREQTYPE fxSynReqType) {
        this.fxSynReqType = fxSynReqType;
    }

    public void setRtcFxSync(RTCFXSYNCMSGPortType rtcFxSync) {
        this.rtcFxSync = rtcFxSync;
    }

    @Override
    public void processMsg(Message msg) throws Exception {
        String msgString = new String(msg.getBody());
        String msgId = msg.getMessageProperties().getReceivedRoutingKey();
        Map<String, Object> mqHeaders = msg.getMessageProperties().getHeaders();
        logger.info("Received mqHeaders: {}", mqHeaders);

        String transId = (String) mqHeaders.get(MnpRtcProvHeaders.RtcTransId.name());
        String msisdn = (String) mqHeaders.get(MnpRtcProvHeaders.Msisdn.name());
        String refApp = (String) mqHeaders.get(MnpRtcProvHeaders.BatchType.name());

        msgProperties.getHeaders().clear();
        msgProperties.setHeader(MnpRtcProvHeaders.RtcTransId.name(), transId);
        msgProperties.setHeader(MnpRtcProvHeaders.Msisdn.name(), msisdn);
        msgProperties.setHeader(MnpRtcProvHeaders.BatchType.name(), refApp);
        try {
            // 1.Getter msg from RtcIbacssPortActStatusMsgQ
            msisdn = msisdn.substring(1, msisdn.length());

            // 2.Setter msg
            fxSynReqType.setSYNCTRANSID(transId);
            fxSynReqType.setMSISDN(countryCode + msisdn);
            fxSynReqType.setREFAPP(refApp);

            // 3.Call RTC Webservices
            FXSYNCREQRESTYPE response = rtcFxSync.rtcFXSYNCREQ(fxSynReqType);

            msgProperties.setHeader(MnpRtcProvHeaders.RtcTransId.name(), response.getSYNCTRANSID());
            msgProperties.setHeader(MnpRtcProvHeaders.RtcStatus.name(), response.getSTATUS());
            msgProperties.setHeader(MnpRtcProvHeaders.RtcTransMsg.name(), response.getSYNCMSG());

            // 4.Push in rabbitMQ (Error)
            Message newMsg = new Message(msgString.getBytes(), msgProperties);
            logger.debug("Ws response: {}", msgProperties.getHeaders());

            if (!response.getSTATUS().equals(successResponse)) {//error
                errorAmqpTemplate.send(msgId, newMsg);
                logger.error("Error detected while calling ws: {}", msgProperties.getHeaders());
            }
        } catch (Exception e) {
            Message newMsg = new Message(msgString.getBytes(), msgProperties);
            logger.error(String.format("Error detected while calling rtc ws: %s", msgProperties.getHeaders()), e);
            errorAmqpTemplate.send(msgId, newMsg);
        }
    }
}
