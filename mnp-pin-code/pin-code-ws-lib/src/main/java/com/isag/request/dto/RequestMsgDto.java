/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isag.request.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.isag.jackson.IsagLocalDateTimeDeserializer;
import java.time.LocalDateTime;

/**
 *
 * @author CATr
 */
public class RequestMsgDto {
    
    private String originator;
    private String destination;
    @JacksonXmlCData
    private String message;
    private Integer messageid;
    private Integer messageseq;
    private Integer messagetotal;
    private String langid;
    @JsonDeserialize(using = IsagLocalDateTimeDeserializer.class)
    private LocalDateTime timestamp;
    private String option;

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getMessageid() {
        return messageid;
    }

    public void setMessageid(Integer messageid) {
        this.messageid = messageid;
    }

    public Integer getMessageseq() {
        return messageseq;
    }

    public void setMessageseq(Integer messageseq) {
        this.messageseq = messageseq;
    }

    public Integer getMessagetotal() {
        return messagetotal;
    }

    public void setMessagetotal(Integer messagetotal) {
        this.messagetotal = messagetotal;
    }

    public String getLangid() {
        return langid;
    }

    public void setLangid(String langid) {
        this.langid = langid;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "RequestMsgDto{" + "originator=" + originator + ", destination=" + destination + ", message=" + message + ", messageid=" + messageid + ", messageseq=" + messageseq + ", messagetotal=" + messagetotal + ", langid=" + langid + ", timestamp=" + timestamp + ", option=" + option + '}';
    }
    
}
