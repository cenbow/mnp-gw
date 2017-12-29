/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.pincode.controller;

import cat.mnp.pincode.ws.portout.CancelPinCodeRequest;
import cat.mnp.pincode.ws.portout.ChannelType;
import cat.mnp.pincode.ws.portout.ContactChannelType;
import cat.mnp.pincode.ws.portout.CustomerType;
import cat.mnp.pincode.ws.portout.GeneratePinCodeRequest;
import cat.mnp.pincode.ws.portout.PortOutResponse;
import cat.mnp.pincode.ws.portout.PortOutService;
import cat.mnp.pincode.ws.portout.RequestInfoRequest;
import com.isag.request.dto.RequestDto;
import com.isag.request.dto.ResponseDto;
import com.isag.dr.dto.DrRequestDto;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author CATr
 */
@Controller
@RequestMapping("/isag/portout")
public class IsagPortoutController {

    private static final Logger logger = LoggerFactory.getLogger(IsagPortoutController.class);

    private static final Pattern ussdPattern = Pattern.compile("\\*151\\*(?<cancelflag>0\\*)*(?<idcard>.+[^#])#$");

    @Autowired
    private PortOutService portOutService;

    @RequestMapping(value = {"", "/"},
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_XML_VALUE
    )
    @ResponseBody
    public ResponseDto main(@RequestBody RequestDto req) {
        logger.debug("Req: {}", req);
        ResponseDto resp = new ResponseDto();
        resp.setTxid(req.getTxid());

        switch (req.getCharge().getServiceid()) {
            case "4444151":
                generatePin(req, resp);
                break;
            case "4444150":
                cancelPin(req, resp);
                break;
            case "4444159":
                reqInfo(req, resp);
                break;
            default:
                resp.setStatus("607");
        }

        logger.debug("Resp: {}", resp);
        return resp;
    }

    private void generatePin(RequestDto req, ResponseDto resp) {
        GeneratePinCodeRequest genPinReq;
        try {
            genPinReq = convertGeneratePin(req);
        } catch (Exception ex) {
            resp.setStatus("400");
            resp.setDescription(ex.getMessage());
            return;
        }
        PortOutResponse genPinResp = portOutService.generatePinCode(genPinReq);
        resp.setStatus(genPinResp.getStatusCode());
        resp.setDescription(genPinResp.getStatusDesc());
    }

    private void cancelPin(RequestDto req, ResponseDto resp) {
        CancelPinCodeRequest cancelPinReq;
        try {
            cancelPinReq = convertCancelPin(req);
        } catch (Exception ex) {
            resp.setStatus("400");
            resp.setDescription(ex.getMessage());
            return;
        }
        PortOutResponse cancelPinResp = portOutService.cancelPinCode(cancelPinReq);
        resp.setStatus(cancelPinResp.getStatusCode());
        resp.setDescription(cancelPinResp.getStatusDesc());
    }

    private void reqInfo(RequestDto req, ResponseDto resp) {
        RequestInfoRequest reqInfoReq;
        try {
            reqInfoReq = convertReqInfo(req);
        } catch (Exception ex) {
            resp.setStatus("400");
            resp.setDescription(ex.getMessage());
            return;
        }
        PortOutResponse reqInfoResp = portOutService.requestInfo(reqInfoReq);
        resp.setStatus(reqInfoResp.getStatusCode());
        resp.setDescription(reqInfoResp.getStatusDesc());
    }

    @RequestMapping(value = {"/dr"},
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_XML_VALUE
    )

    @ResponseBody
    public ResponseDto dr(@RequestBody DrRequestDto req) {
        logger.debug("Req: {}", req);
        ResponseDto resp = new ResponseDto();
        resp.setTxid(req.getTxid());
        resp.setStatus("0");
        return resp;
    }

    private GeneratePinCodeRequest convertGeneratePin(RequestDto req) {
        GeneratePinCodeRequest genPinReq = new GeneratePinCodeRequest();
        genPinReq.setChannelType(ChannelType.valueOf(req.getCharge().getMsgtype().name()));
        genPinReq.setChannelRefNumber(req.getCharge().getTicketid());
        genPinReq.setCustomerType(CustomerType.INDIVIDUAL);
        genPinReq.setCardNumber(convertCardNumber(req.getMsg().getMessage()));
        List<String> msisdnList = new ArrayList<>();
        String msisdn = convertMsisdn(req.getMsg().getOriginator());
        msisdnList.add(msisdn);
        genPinReq.setMsisdnList(msisdnList);
        genPinReq.setContactChannelType(ContactChannelType.SMS);
        genPinReq.setContactMsisdn(msisdn);
        return genPinReq;
    }

    private CancelPinCodeRequest convertCancelPin(RequestDto req) {
        CancelPinCodeRequest cancelPinReq = new CancelPinCodeRequest();
        cancelPinReq.setChannelType(ChannelType.valueOf(req.getCharge().getMsgtype().name()));
        cancelPinReq.setChannelRefNumber(req.getCharge().getTicketid());
        cancelPinReq.setCardNumber(convertCardNumber(req.getMsg().getMessage()));
        List<String> msisdnList = new ArrayList<>();
        String msisdn = convertMsisdn(req.getMsg().getOriginator());
        msisdnList.add(msisdn);
        cancelPinReq.setMsisdnList(msisdnList);
        cancelPinReq.setContactChannelType(ContactChannelType.SMS);
        cancelPinReq.setContactMsisdn(msisdn);
        return cancelPinReq;
    }

    private RequestInfoRequest convertReqInfo(RequestDto req) {
        RequestInfoRequest reqInfoReq = new RequestInfoRequest();
        reqInfoReq.setChannelType(ChannelType.valueOf(req.getCharge().getMsgtype().name()));
        reqInfoReq.setChannelRefNumber(req.getCharge().getTicketid());
        reqInfoReq.setMsisdn(convertMsisdn(req.getMsg().getOriginator()));
        return reqInfoReq;
    }

    // 66876543210 -> 0876543210
    private String convertMsisdn(String input) {
        String output = String.format("0%s", input.substring(2, input.length()));
        return output;
    }

    // *151*0*1222233333445 -> 1222233333445
    private String convertCardNumber(String input) {
        Matcher m = ussdPattern.matcher(input);
        if (!m.matches()) {
            logger.debug("UssdPattern does not match for input {}", input);
            return input;
        }
        String output = m.group("idcard");
        logger.debug("Extracted pincode {} to idcard {}", input, output);
        return output;
    }

}
