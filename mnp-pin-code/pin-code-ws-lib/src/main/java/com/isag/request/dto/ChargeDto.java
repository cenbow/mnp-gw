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
@JacksonXmlRootElement(localName = "charge")
public class ChargeDto {
    private MsgType msgtype;
    private String serviceid;
    private String account;
    private ChargeType chargetype;
    private String ticketid;
    private String serialnumber;

    public MsgType getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(MsgType msgtype) {
        this.msgtype = msgtype;
    }

    public String getServiceid() {
        return serviceid;
    }

    public void setServiceid(String serviceid) {
        this.serviceid = serviceid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public ChargeType getChargetype() {
        return chargetype;
    }

    public void setChargetype(ChargeType chargetype) {
        this.chargetype = chargetype;
    }

    public String getTicketid() {
        return ticketid;
    }

    public void setTicketid(String ticketid) {
        this.ticketid = ticketid;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    @Override
    public String toString() {
        return "ChargeDto{" + "msgtype=" + msgtype + ", serviceid=" + serviceid + ", account=" + account + ", chargetype=" + chargetype + ", ticketid=" + ticketid + ", serialnumber=" + serialnumber + '}';
    }
    
}
