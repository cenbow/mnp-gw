/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isag.sms.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.isag.jackson.IsagLocalDateTimeDeserializer;
import com.isag.jackson.IsagLocalDateTimeSerializer;
import com.isag.jackson.NullToEmptyStringSerializer;
import java.time.LocalDateTime;

/**
 *
 * @author CATr
 */
public class SmsRequestMsgDto {
    
    private String destination;
    @JacksonXmlCData
    private String message;
    private String langid;
    @JsonSerialize(using = IsagLocalDateTimeSerializer.class)
    @JsonDeserialize(using = IsagLocalDateTimeDeserializer.class)
    private LocalDateTime timestamp;
    @JsonSerialize(nullsUsing = NullToEmptyStringSerializer.class)
    private String option;

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
        return "SmsRequestMsgDto{" + "destination=" + destination + ", message=" + message + ", langid=" + langid + ", timestamp=" + timestamp + ", option=" + option + '}';
    }

}
