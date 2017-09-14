/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.core.converter.support;

import java.util.List;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.integration.annotation.Transformer;
import org.springframework.util.StringUtils;

/**
 *
 * @author anuchitr
 */
public class ListOfStringToStringTransformer {
    private String delim;
    private String prefix;
    private String suffix;

    public void setDelim(String delim) {
        this.delim = StringEscapeUtils.unescapeJava(delim);
    }

    public void setPrefix(String prefix) {
        this.prefix = StringEscapeUtils.unescapeJava(prefix);
    }

    public void setSuffix(String suffix) {
        this.suffix = StringEscapeUtils.unescapeJava(suffix);
    }
    
    @Transformer
    public String transform(List<String> dataList) {
        String str = StringUtils.collectionToDelimitedString(dataList, delim, prefix, suffix);
        return str;
    }
}
