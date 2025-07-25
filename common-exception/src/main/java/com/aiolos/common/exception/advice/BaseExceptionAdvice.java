package com.aiolos.common.exception.advice;

import com.aiolos.common.enums.error.ErrorEnum;
import com.aiolos.common.model.response.CommonResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author Aiolos
 * @date 2021/5/20 12:13 上午
 */
@RestControllerAdvice
public class BaseExceptionAdvice {

    private static final Logger log = LoggerFactory.getLogger(BaseExceptionAdvice.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CommonResponse handlerException(HttpServletRequest req, Exception e) {
        log.error("全局异常捕获：{}", e.getMessage(), e);
        if (e instanceof NoHandlerFoundException) {
            return CommonResponse.error(ErrorEnum.NO_HANDLER_FOUND);
        } else if (e instanceof ServletRequestBindingException) {
            return CommonResponse.error(ErrorEnum.BIND_EXCEPTION_ERROR);
        } else if (e instanceof NullPointerException) {
            return CommonResponse.error(ErrorEnum.NULL_POINT_ERROR);
        } else if (e instanceof MaxUploadSizeExceededException) {
            return CommonResponse.error(ErrorEnum.FILE_MAX_SIZE_ERROR);
        } else {
            return CommonResponse.error(ErrorEnum.UNKNOWN_ERROR);
        }
    }
}
