package com.aiolos.common.exception.response;

import com.aiolos.common.enums.errors.ErrorEnum;
import lombok.Data;
import org.springframework.http.HttpStatus;

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
    private Integer code = HttpStatus.OK.value();

    /**
     * 响应信息
     */
    private String msg = "SUCCESS";;

    /**
     * 响应中的数据
     */
    private T data;

    private CommonResponse() {
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
    
    public static CommonResponse error(Integer errCode, String errMsg, Object data) {
        if (errCode == null) {
            throw new IllegalArgumentException("Error code cannot be null");
        }
        CommonResponse res = new CommonResponse();
        res.setCode(errCode);
        res.setMsg(errMsg);
        res.setData(data);
        return res;
    }
    
    public static CommonResponse error(Integer errCode, String errMsg) {
        return error(errCode, errMsg, null);
    }
    
    public static CommonResponse error(String errMsg, Object data) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), errMsg, data);
    }

    public static CommonResponse error(ErrorEnum errorEnum) {
        return error(errorEnum.getErrCode(), errorEnum.getErrMsg(), null);
    }
}