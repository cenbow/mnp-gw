/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isag.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author anuchitr
 */
public class IsagLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    private static final DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyMMddHHmmssSSS");
    private static final DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private static final Logger logger = LoggerFactory.getLogger(IsagLocalDateTimeDeserializer.class);
    
    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        LocalDateTime dt;
        try {
            dt = LocalDateTime.parse(p.getText(), formatter1);
        } catch (Exception ex) {
            try {
                dt = LocalDateTime.parse(p.getText(), formatter2);
            } catch (Exception ex2) {
                logger.error("Cannot parse dateTime from " + p.getText(), ex2);
                dt = null;
            }
        }
        return dt;
    }
    
}
