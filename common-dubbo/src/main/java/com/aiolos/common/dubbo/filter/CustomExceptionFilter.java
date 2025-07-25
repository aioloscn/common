package com.aiolos.common.dubbo.filter;

import com.aiolos.common.exception.error.CustomizedException;
import org.apache.dubbo.common.logger.ErrorTypeAwareLogger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.common.utils.ReflectUtils;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.service.GenericService;
import org.apache.dubbo.rpc.support.RpcUtils;

import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;

import static org.apache.dubbo.common.constants.LoggerCodeConstants.CONFIG_FILTER_VALIDATION_EXCEPTION;

public class CustomExceptionFilter implements Filter, Filter.Listener {
    private ErrorTypeAwareLogger logger = LoggerFactory.getErrorTypeAwareLogger(CustomExceptionFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return invoker.invoke(invocation);
    }

    @Override
    public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
        if (appResponse.hasException() && GenericService.class != invoker.getInterface()) {
            try {
                Throwable exception = appResponse.getException();

                if (exception instanceof UndeclaredThrowableException) {
                    Throwable cause = exception.getCause();
                    if (cause instanceof CustomizedException) {
                        // 替换为原始异常
                        appResponse.setException(cause);

                        logger.error(CONFIG_FILTER_VALIDATION_EXCEPTION, "", "",
                                "Got custom exception which called by " + RpcContext.getServiceContext().getRemoteHost() +
                                        ". service: " + invoker.getInterface().getName() + ", method: " + RpcUtils.getMethodName(invocation) +
                                        ", exception: " + cause.getClass().getName() + ": " + cause.getMessage());
                        return;
                    }
                }

                // directly throw if it's checked exception
                if (!(exception instanceof RuntimeException) && (exception instanceof Exception)) {
                    return;
                }
                // directly throw if the exception appears in the signature
                try {
                    Method method = invoker.getInterface().getMethod(RpcUtils.getMethodName(invocation), invocation.getParameterTypes());
                    Class<?>[] exceptionClasses = method.getExceptionTypes();
                    for (Class<?> exceptionClass : exceptionClasses) {
                        if (exception.getClass().equals(exceptionClass)) {
                            return;
                        }
                    }
                } catch (NoSuchMethodException e) {
                    return;
                }

                // for the exception not found in method's signature, print ERROR message in server's log.
                logger.error(CONFIG_FILTER_VALIDATION_EXCEPTION, "", "",
                        "Got unchecked and undeclared exception which called by " + RpcContext.getServiceContext().getRemoteHost() +
                                ". service: " + invoker.getInterface().getName() + ", method: " + RpcUtils.getMethodName(invocation) +
                                ", exception: " + exception.getClass().getName() + ": " + exception.getMessage(), exception);

                // directly throw if exception class and interface class are in the same jar file.
                String serviceFile = ReflectUtils.getCodeBase(invoker.getInterface());
                String exceptionFile = ReflectUtils.getCodeBase(exception.getClass());
                if (serviceFile == null || exceptionFile == null || serviceFile.equals(exceptionFile)) {
                    return;
                }
                // directly throw if it's JDK exception
                String className = exception.getClass().getName();
                if (className.startsWith("java.") || className.startsWith("javax.")) {
                    return;
                }
                // directly throw if it's dubbo exception
                if (exception instanceof RpcException) {
                    return;
                }

                // otherwise, wrap with RuntimeException and throw back to the client
                appResponse.setException(new RuntimeException(StringUtils.toString(exception)));
            } catch (Throwable e) {
                logger.warn(CONFIG_FILTER_VALIDATION_EXCEPTION, "", "",
                        "Fail to ExceptionFilter when called by " + RpcContext.getServiceContext().getRemoteHost() +
                                ". service: " + invoker.getInterface().getName() + ", method: " + RpcUtils.getMethodName(invocation) +
                                ", exception: " + e.getClass().getName() + ": " + e.getMessage(), e);
            }
        }
    }

    @Override
    public void onError(Throwable e, Invoker<?> invoker, Invocation invocation) {
        logger.error(CONFIG_FILTER_VALIDATION_EXCEPTION, "", "",
                "Got unchecked and undeclared exception which called by " + RpcContext.getServiceContext().getRemoteHost() +
                        ". service: " + invoker.getInterface().getName() + ", method: " + RpcUtils.getMethodName(invocation) +
                        ", exception: " + e.getClass().getName() + ": " + e.getMessage(), e);
    }

    // For test purpose
    public void setLogger(ErrorTypeAwareLogger logger) {
        this.logger = logger;
    }
}
