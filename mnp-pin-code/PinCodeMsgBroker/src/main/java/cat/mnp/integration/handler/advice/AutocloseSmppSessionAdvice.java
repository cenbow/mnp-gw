/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.integration.handler.advice;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.handler.advice.AbstractRequestHandlerAdvice;
import org.springframework.integration.smpp.session.ExtendedSmppSession;
import org.springframework.messaging.Message;

/**
 *
 * @author CATr
 */
public class AutocloseSmppSessionAdvice extends AbstractRequestHandlerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(AutocloseSmppSessionAdvice.class);
    private ExtendedSmppSession smppSession;
    private ScheduledExecutorService scheduledExecutorService;
    private long closeDelay = 10000;

    private ScheduledFuture<?> scheduledFuture;

    public void setSmppSession(ExtendedSmppSession smppSession) {
        this.smppSession = smppSession;
    }

    public void setScheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = scheduledExecutorService;
    }

    public void setCloseDelay(long closeDelay) {
        this.closeDelay = closeDelay;
    }

    @Override
    public void onInit() throws Exception {
        super.onInit();
        delayCloseConnection();
    }

    @Override
    protected Object doInvoke(ExecutionCallback callback, Object target, Message<?> message) throws Exception {
        if(true) {  //FIXME: For Test
        		logger.warn("Test Mock SMS:"+message);
        		return null;
        }
    		if (!smppSession.getSessionState().isBound()) {
            logger.info("Reconnecting to smsc");
            smppSession.start();
        }

        try {
            Object result = callback.execute();
            return result;
        } finally {
            delayCloseConnection();
        }

    }

    private void delayCloseConnection() {
        if (scheduledFuture != null && !(scheduledFuture.isCancelled() || scheduledFuture.isDone())) {
            scheduledFuture.cancel(false);
        }
        scheduledFuture = scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                logger.info("Closing smsc connection");
                smppSession.stop();
            }
        }, closeDelay, TimeUnit.MILLISECONDS);
    }

}
