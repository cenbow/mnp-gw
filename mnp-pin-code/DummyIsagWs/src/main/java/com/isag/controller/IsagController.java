/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isag.controller;

import com.isag.request.dto.ResponseDto;
import com.isag.sms.dto.SmsRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author anuchitr
 */
@Controller
@RequestMapping("/isag")
public class IsagController {

    private static final Logger logger = LoggerFactory.getLogger(IsagController.class);

    @RequestMapping(value = {"/sms"},
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_XML_VALUE
    )
    @ResponseBody
    public ResponseDto main(@RequestBody SmsRequestDto req) {
        logger.debug("Req: {}", req);
        ResponseDto resp = new ResponseDto();
        resp.setTxid(req.getTxid());
        resp.setStatus("000");
        
        return resp;
    }

}
