/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isag.sms.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.isag.request.dto.ChargeType;

/**
 *
 * @author anuchitr
 */
@JacksonXmlRootElement(localName = "charge")
public class SmsChargeDto {
    private ChargeType chargetype;
    private String ticketid;

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

    @Override
    public String toString() {
        return "SmsChargeDto{" + "chargetype=" + chargetype + ", ticketid=" + ticketid + '}';
    }

}
