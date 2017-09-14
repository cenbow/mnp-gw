/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.report.service;

import java.util.HashMap;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP-CAT
 */
@Service
public class ReportStatusService {

    private String baseKey;
    private String keyDateFormat;
    private RedisTemplate reportTemplate;

    public String getBaseKey() {
        return baseKey;
    }

    public void setBaseKey(String baseKey) {
        this.baseKey = baseKey;
    }

    public String getKeyDateFormat() {
        return keyDateFormat;
    }

    public void setKeyDateFormat(String keyDateFormat) {
        this.keyDateFormat = keyDateFormat;
    }

    public RedisTemplate getReportTemplate() {
        return reportTemplate;
    }

    public void setReportTemplate(RedisTemplate reportTemplate) {
        this.reportTemplate = reportTemplate;
    }

    private String getRealKey(String key) {
        return String.format(baseKey, key);
    }

    public Object getValue() {
        return reportTemplate.opsForValue().get(baseKey);
    }

    public void setValue(String key, Object value) {
        reportTemplate.opsForValue().set(getRealKey(key), value);
    }

    public Object getHashEntries(String key) {
        return reportTemplate.opsForHash().entries(getRealKey(key));
    }

    public Object getHashValue(String key, String key2) {
        return reportTemplate.opsForHash().get(getRealKey(key), key2);
    }

    public void setHashValue(String key, HashMap obj) {
        reportTemplate.opsForHash().putAll(getRealKey(key), obj);
    }

    public void setHashValue(String key, String key2, Object obj) {
        reportTemplate.opsForHash().put(getRealKey(key), key2, obj);
    }

    public void deleteHashValue(String key, String key2) {
        reportTemplate.opsForHash().delete(getRealKey(key), key2);
    }

    public void deleteHashEntries(String key) {
        reportTemplate.delete(getRealKey(key));
    }
}
