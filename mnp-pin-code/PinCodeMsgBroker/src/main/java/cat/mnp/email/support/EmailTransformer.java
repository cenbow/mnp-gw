/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.email.support;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.Transformer;

/**
 *
 * @author anuchitr
 */
public class EmailTransformer {
	private static final Logger logger = LoggerFactory.getLogger(EmailTransformer.class);
    
    @Transformer
	public List<EmailFragment> transform(javax.mail.Message mailMessage) {

		final List<EmailFragment> emailFragments = new ArrayList<>();

		EmailParserUtils.handleMessage(null, mailMessage, emailFragments);

		logger.info(String.format("Email contains %s fragments.", emailFragments.size()));

		return emailFragments;
	}
}
