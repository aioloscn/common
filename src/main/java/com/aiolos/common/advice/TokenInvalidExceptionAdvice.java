package com.aiolos.common.advice;

import com.aiolos.common.exception.TokenInvalidException;
import com.aiolos.common.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Aiolos
 * @date 2021/6/27 5:47 下午
 */
@Order(-1)
@Slf4j
@RestControllerAdvice
public class TokenInvalidExceptionAdvice {
    @ExceptionHandler(value = TokenInvalidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CommonResponse handlerTokenInvalidException(HttpServletRequest req, HttpServletResponse resp, Exception e) {
        log.warn(((TokenInvalidException) e).getErrMsg());
        return CommonResponse.error(((TokenInvalidException) e).getErrCode(), ((TokenInvalidException) e).getErrMsg());
    }
}
