package com.aiolos.common.model.response;

import cn.hutool.http.HttpStatus;
import com.aiolos.common.enums.errors.ErrorEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 自定义响应数据结构
 * 状态码定义在ErrorEnum类中，HttpStatus.200：成功（默认），500：错误（默认）
 * @author Aiolos
 * @date 2020/9/22 1:11 下午
 */
@Data
public class CommonResponse<T> implements Serializable {

    /**
     * 响应业务状态码
     */
    private Integer code = HttpStatus.HTTP_OK;

    /**
     * 响应信息
     */
    private String msg;
    
    private Boolean success = true;

    /**
     * 响应中的数据
     */
    private T data;

    public CommonResponse() {
    }

    private CommonResponse(String msg) {
        this.msg = msg;
    }

    private CommonResponse(T data) {
        this.data = data;
    }

    private CommonResponse(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    public static <T> CommonResponse<T> ok() {
        return new CommonResponse<>();
    }

    public static <T> CommonResponse<T> ok(String msg) {
        return new CommonResponse<>(msg);
    }

    public static <T> CommonResponse<T> ok(T data) {
        return new CommonResponse<>(data);
    }

    public static <T> CommonResponse<T> ok(String msg, T data) {
        return new CommonResponse<>(msg, data);
    }
    
    public static <T> CommonResponse<T> error(Integer errCode, String errMsg, T data) {
        if (errCode == null) {
            throw new IllegalArgumentException("Error code cannot be null");
        }
        CommonResponse<T> res = new CommonResponse<>();
        res.setSuccess(false);
        res.setCode(errCode);
        res.setMsg(errMsg);
        res.setData(data);
        return res;
    }
    
    public static <T> CommonResponse<T> error(Integer errCode, String errMsg) {
        return error(errCode, errMsg, null);
    }
    
    public static <T> CommonResponse<T> error(String errMsg, T data) {
        return error(HttpStatus.HTTP_INTERNAL_ERROR, errMsg, data);
    }

    public static <T> CommonResponse<T> error(ErrorEnum errorEnum) {
        return error(errorEnum.getErrCode(), errorEnum.getErrMsg(), null);
    }
}