package com.aiolos.common.advice;

import com.aiolos.common.response.CommonResponse;
import com.aiolos.common.exception.CustomizeException;
import com.aiolos.common.enums.ErrorEnum;
import com.aiolos.common.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 所有执行的controller都会被这个切面所包含
 * @author Aiolos
 * @date 2020/10/10 4:14 下午
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 定义ExceptionHandler解决为被controller层吸收的exception
     * 只要对应的controller抛出了任何Exception或者继承自Exception的异常，都会做一个对应的处理
     * @return  返回封装好的公共web对象
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CommonResponse handlerCustomizeException(HttpServletRequest req, HttpServletResponse res, Exception e) {

        if (e instanceof CustomizeException) {
            log.info("全局异常捕获，异常信息：{}", ((CustomizeException) e).getErrMsg());
            return CommonResponse.error(((CustomizeException) e).getErrCode(), ((CustomizeException) e).getErrMsg());
        } else {
            log.info(e.getMessage());
            if (e instanceof NoHandlerFoundException) {
                return CommonResponse.error(ErrorEnum.NO_HANDLER_FOUND);
            } else if (e instanceof MethodArgumentNotValidException) {
                BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
                return CommonResponse.error(ErrorEnum.PARAMETER_VALIDATION_ERROR.getErrCode(), CommonUtils.processErrorString(bindingResult));
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
}
