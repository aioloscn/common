package com.aiolos.common.exception.advice;

import com.aiolos.common.exception.error.TokenInvalidException;
import com.aiolos.common.model.response.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Aiolos
 * @date 2021/6/24 8:33 下午
 */
@Order(-10)  // TokenInvalidException继承了CustomizedException，必须比它的优先级高
@RestControllerAdvice
public class TokenInvalidExceptionAdvice {

    private static final Logger log = LoggerFactory.getLogger(TokenInvalidExceptionAdvice.class);
    
    @ExceptionHandler(value = TokenInvalidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CommonResponse handlerTokenInvalidException(Exception e) {
        log.warn(((TokenInvalidException) e).getErrMsg());
        return CommonResponse.error(((TokenInvalidException) e).getErrCode(), ((TokenInvalidException) e).getErrMsg());
    }
}
