/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.isag.converter.support;

import cat.mnp.sms.integration.SmsHeaders;
import com.isag.request.dto.ChargeType;
import com.isag.sms.dto.SmsChargeDto;
import com.isag.sms.dto.SmsRequestDto;
import com.isag.sms.dto.SmsRequestMsgDto;
import java.io.DataInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.handler.annotation.Header;

/**
 *
 * @author CATr
 */
public class IsagSmsRequestDtoTransformer {

    private static final Logger logger = LoggerFactory.getLogger(IsagSmsRequestDtoTransformer.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmssnnnnnnnnn");

    @Transformer
    public SmsRequestDto transform(
        @Header(SmsHeaders.USERNAME) String username,
        @Header(SmsHeaders.PASSWORD) String password,
        @Header(SmsHeaders.REF_ID) String refId,
        @Header(SmsHeaders.TO) String msisdn,
        @Header(SmsHeaders.BODY) Object body) {
        
        String bodyString = convertBody(body);

        SmsRequestDto dto = new SmsRequestDto();
        dto.setTxid(getTxId());
        dto.setUser(username);
        dto.setPassword(password);
        dto.setCharge(getChargeDto(refId));
        dto.setMsg(getMsg(msisdn, bodyString));
        return dto;
    }

    private String getTxId() {
        String now = formatter.format(LocalDateTime.now());
        return now;
    }

    private SmsChargeDto getChargeDto(String refId) {
        SmsChargeDto dto = new SmsChargeDto();
        dto.setChargetype(ChargeType.N);
        dto.setTicketid(refId);
        return dto;
    }

    private SmsRequestMsgDto getMsg(String msisdn, String msg) {
        SmsRequestMsgDto dto = new SmsRequestMsgDto();
        dto.setDestination(convertMsisdn(msisdn));
        dto.setLangid("T");
        dto.setMessage(msg);
        dto.setTimestamp(LocalDateTime.now());
        return dto;
    }

    private String convertMsisdn(String input) {
        String output = String.format("66%s", input.substring(1, input.length()));
        return output;
    }

    private String convertBody(Object body) {
        if (body instanceof DataInputStream) {
            try {
                return IOUtils.toString((DataInputStream) body);
            } catch (IOException ex) {
                logger.error("Cannot convert body", ex);
                throw new RuntimeException("Cannot convert body; error: " + ex.getMessage());
            }
        } else {
            return (String) body;
        }
    }
}
