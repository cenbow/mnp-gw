/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.integration.handler.advice;

import java.util.ArrayList;
import org.springframework.integration.handler.advice.AbstractRequestHandlerAdvice;
import org.springframework.messaging.Message;

/**
 *
 * @author anuchitr
 */
public class NullToEmptyListAdvice extends AbstractRequestHandlerAdvice {

    @Override
    protected Object doInvoke(ExecutionCallback callback, Object target, Message<?> message) throws Exception {
        Object result = callback.execute();
		if (result == null) {
			result = new ArrayList<>();
		}
		return result;
    }
    
}
