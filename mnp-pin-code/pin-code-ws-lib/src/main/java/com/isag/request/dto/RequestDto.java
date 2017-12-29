/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isag.request.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 *
 * @author CATr
 */
@JacksonXmlRootElement(localName = "request")
public class RequestDto {
    
    private String txid;
    private String user;
    private String password;
    private ChargeDto charge;
    private RequestMsgDto msg;

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

    public ChargeDto getCharge() {
        return charge;
    }

    public void setCharge(ChargeDto charge) {
        this.charge = charge;
    }

    public RequestMsgDto getMsg() {
        return msg;
    }

    public void setMsg(RequestMsgDto msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "RequestDto{" + "txid=" + txid + ", user=" + user + ", password=" + password + ", charge=" + charge + ", msg=" + msg + '}';
    }
    
}
