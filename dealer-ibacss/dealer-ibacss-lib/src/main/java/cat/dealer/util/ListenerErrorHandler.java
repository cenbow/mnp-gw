/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.dealer.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ErrorHandler;

/**
 *
 * @author HP-CAT
 */
public class ListenerErrorHandler implements ErrorHandler {
    private static final Logger logger = LoggerFactory.getLogger(ListenerErrorHandler.class);
    
    @Override
    public void handleError(Throwable t) {
        logger.error("", t);
    }

}
