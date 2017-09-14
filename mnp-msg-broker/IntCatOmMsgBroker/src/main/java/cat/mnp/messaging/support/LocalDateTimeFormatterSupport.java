/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.messaging.support;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author anuchitr
 */
public class LocalDateTimeFormatterSupport {
    private static final ConcurrentHashMap<String, DateTimeFormatter> map = new ConcurrentHashMap<>();
    
    public String format(String format) {
        return format(format, LocalDateTime.now());
    }
    public String format(String format, LocalDateTime dt) {
        DateTimeFormatter formatter = map.get(format);
        if (formatter == null) {
            formatter = DateTimeFormatter.ofPattern(format);
            map.put(format, formatter);
        }
        return dt.format(formatter);
    }
    
}
