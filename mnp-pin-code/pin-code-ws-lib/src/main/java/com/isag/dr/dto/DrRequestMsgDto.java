/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isag.dr.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.isag.jackson.IsagLocalDateTimeDeserializer;
import java.time.LocalDateTime;

/**
 *
 * @author CATr
 */
public class DrRequestMsgDto {
    
    private String smstxid;
    @JsonDeserialize(using = IsagLocalDateTimeDeserializer.class)
    private LocalDateTime timestamp;
    private String status;
    private String description;

    public String getSmstxid() {
        return smstxid;
    }

    public void setSmstxid(String smstxid) {
        this.smstxid = smstxid;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
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
        return "DrRequestMsgDto{" + "smstxid=" + smstxid + ", timestamp=" + timestamp + ", status=" + status + ", description=" + description + '}';
    }
    
}
