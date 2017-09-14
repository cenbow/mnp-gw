/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.messaging.support;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 *
 * @author anuchitr
 */
public class StringEscapeSupport {
    
    public String unescapeJava(String input) {
        return StringEscapeUtils.unescapeJava(input);
    }
}
