/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.email.integration;

import org.springframework.integration.mail.MailHeaders;

/**
 *
 * @author anuchitr
 */
public class ExtendedMailHeaders extends MailHeaders {
    
	public static final String BODY = PREFIX + "body";
    
}
