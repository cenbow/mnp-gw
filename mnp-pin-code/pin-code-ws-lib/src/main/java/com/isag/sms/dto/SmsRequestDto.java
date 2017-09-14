/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isag.sms.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 *
 * @author anuchitr
 */
@JacksonXmlRootElement(localName = "request")
public class SmsRequestDto {
    
    private String txid;
    private String user;
    private String password;
    private SmsChargeDto charge;
    private SmsRequestMsgDto msg;

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SmsChargeDto getCharge() {
        return charge;
    }

    public void setCharge(SmsChargeDto charge) {
        this.charge = charge;
    }

    public SmsRequestMsgDto getMsg() {
        return msg;
    }

    public void setMsg(SmsRequestMsgDto msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "SmsRequestDto{" + "txid=" + txid + ", user=" + user + ", password=" + password + ", charge=" + charge + ", msg=" + msg + '}';
    }

}
