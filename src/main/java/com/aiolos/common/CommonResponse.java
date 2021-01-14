package com.aiolos.common;

import com.aiolos.common.enums.ErrorEnum;
import org.apache.http.HttpStatus;

import java.io.Serializable;

/**
 * 自定义响应数据结构
 * 状态码定义在ErrorEnum类中，HttpStatus.200：成功（默认），500：错误（默认）
 * @author Aiolos
 * @date 2020/9/22 1:11 下午
 */
public class CommonResponse<T> implements Serializable {

    /**
     * 响应业务状态码
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 响应中的数据
     */
    private T data;

    private CommonResponse() {
        this.code = HttpStatus.SC_OK;
        this.msg = "SUCCESS";
    }

    private CommonResponse(String msg) {
        this.code = HttpStatus.SC_OK;
        this.msg = msg;
    }

    private CommonResponse(T data) {
        this.code = HttpStatus.SC_OK;
        this.msg = "SUCCESS";
        this.data = data;
    }

    private CommonResponse(String msg, T data) {
        this.code = HttpStatus.SC_OK;
        this.msg = msg;
        this.data = data;
    }

    public static CommonResponse ok() {
        return new CommonResponse();
    }

    public static CommonResponse ok(String msg) {
        return new CommonResponse(msg);
    }

    public static CommonResponse ok(Object data) {
        return new CommonResponse(data);
    }

    public static CommonResponse ok(String msg, Object data) {
        return new CommonResponse(msg, data);
    }

    public static CommonResponse error(Integer errCode, String errMsg) {
        CommonResponse res = new CommonResponse();
        res.code = errCode;
        res.msg = errMsg;
        return res;
    }

    public static CommonResponse error(String errMsg, Object data) {
        CommonResponse res = new CommonResponse();
        res.code = HttpStatus.SC_INTERNAL_SERVER_ERROR;
        res.msg = errMsg;
        res.data = data;
        return res;
    }

    public static CommonResponse error(Integer errCode, String errMsg, Object data) {
        CommonResponse res = new CommonResponse();
        res.code = errCode;
        res.msg = errMsg;
        res.data = data;
        return res;
    }

    public static CommonResponse error(ErrorEnum errorEnum) {
        CommonResponse res = new CommonResponse();
        res.code = errorEnum.getErrCode();
        res.msg = errorEnum.getErrMsg();
        return res;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
