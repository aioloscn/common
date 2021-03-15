package com.aiolos.common.response;

import com.aiolos.common.enums.ErrorEnum;
import org.apache.http.HttpStatus;

import java.io.Serializable;

/**
 * 自定义响应数据结构
 * 状态码定义在ErrorEnum类中，HttpStatus.200：成功（默认），500：错误（默认）
 * @author Aiolos
 * @date 2020/9/22 1:11 下午
 */
public class CommonResponse<T> extends AbstractCommonResponse implements Serializable {

    private CommonResponse() {
        super(8);
        super.setCode(HttpStatus.SC_OK);
        super.setMsg("SUCCESS");
    }

    private CommonResponse(String msg) {
        this();
        super.setCode(HttpStatus.SC_OK);
        super.setMsg(msg);
    }

    private CommonResponse(T data) {
        this();
        super.setCode(HttpStatus.SC_OK);
        super.setMsg("SUCCESS");
        super.setData(data);
    }

    private CommonResponse(String msg, T data) {
        this();
        super.setCode(HttpStatus.SC_OK);
        super.setMsg(msg);
        super.setData(data);
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
        res.setCode(errCode);
        res.setMsg(errMsg);
        return res;
    }

    public static CommonResponse error(String errMsg, Object data) {
        CommonResponse res = new CommonResponse();
        res.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        res.setMsg(errMsg);
        res.setData(data);
        return res;
    }

    public static CommonResponse error(Integer errCode, String errMsg, Object data) {
        CommonResponse res = new CommonResponse();
        res.setCode(errCode);
        res.setMsg(errMsg);
        res.setData(data);
        return res;
    }

    public static CommonResponse error(ErrorEnum errorEnum) {
        CommonResponse res = new CommonResponse();
        res.setCode(errorEnum.getErrCode());
        res.setMsg(errorEnum.getErrMsg());
        return res;
    }

    @Override
    public CommonResponse put(Object key, Object value) {
        super.put(key, value);
        return this;
    }
}
