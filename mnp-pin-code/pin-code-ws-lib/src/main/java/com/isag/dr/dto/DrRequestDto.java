/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isag.dr.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 *
 * @author CATr
 */
@JacksonXmlRootElement(localName = "request")
public class DrRequestDto {
    
    private String txid;
    private String user;
    private String password;
    private DrRequestMsgDto dr;

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

    public DrRequestMsgDto getDr() {
        return dr;
    }

    public void setDr(DrRequestMsgDto dr) {
        this.dr = dr;
    }

    @Override
    public String toString() {
        return "DrRequestDto{" + "txid=" + txid + ", user=" + user + ", password=" + password + ", dr=" + dr + '}';
    }
    
}
