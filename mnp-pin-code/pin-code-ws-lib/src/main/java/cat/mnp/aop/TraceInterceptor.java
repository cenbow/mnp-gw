package cat.mnp.aop;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;

/**
 * Extends {@link CustomizableTraceInterceptor} to provide custom logging levels
 */
public class TraceInterceptor extends CustomizableTraceInterceptor {
    
    private boolean enableErrorLog = true;

    public void setEnableErrorLog(boolean enableErrorLog) {
        this.enableErrorLog = enableErrorLog;
    }

    @Override
    protected void writeToLog(Log logger, String message, Throwable ex) {
        if (enableErrorLog && ex != null) {
            logger.error(message, ex);
        } else if (logger.isDebugEnabled()) {
            logger.debug(message);
        }
    }

    @Override
    protected boolean isInterceptorEnabled(MethodInvocation invocation, Log logger) {
        return true;
    }
}