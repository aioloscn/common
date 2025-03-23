package com.aiolos.common.exception.advice;

import com.aiolos.common.enums.errors.ErrorEnum;
import com.aiolos.common.exception.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Aiolos
 * @date 2021/5/20 7:42 上午
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
@RestControllerAdvice
public class MethodArgumentNotValidExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CommonResponse handlerException(Exception e) {
        log.warn("请求参数校验不合格：{}", e.getMessage());
        BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
        return CommonResponse.error(ErrorEnum.PARAMETER_VALIDATION_ERROR.getErrCode(), processErrorString(bindingResult));
    }

    private String processErrorString(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return StringUtils.EMPTY;
        }

        StringBuilder sb = new StringBuilder();
        for (FieldError error : bindingResult.getFieldErrors()) {
            sb.append(error.getDefaultMessage() + ",");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
