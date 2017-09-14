/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isag.request.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.isag.jackson.NullToEmptyStringSerializer;

/**
 *
 * @author anuchitr
 */
@JacksonXmlRootElement(localName = "response")
public class ResponseDto {
    
    private String txid;
    private String status;
    @JsonSerialize(nullsUsing = NullToEmptyStringSerializer.class)
    private String description;

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RequestDto{" + "txid=" + txid + ", status=" + status + ", description=" + description + '}';
    }
    
}
