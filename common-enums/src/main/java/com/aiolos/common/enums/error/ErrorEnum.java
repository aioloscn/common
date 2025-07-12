package com.aiolos.common.enums.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * 557：检查用户是否在CAS登录，用户门票的校验
 * @author Aiolos
 * @date 2020/9/22 2:50 下午
 */
@Getter
@AllArgsConstructor
public enum ErrorEnum implements CommonError {

    // 通用错误类型
    UNKNOWN_ERROR(10001, "未知错误"),
    BIND_EXCEPTION_ERROR(10002, "请求参数错误"),
    PARAMETER_VALIDATION_ERROR(10003, "请求参数校验失败"),
    NO_PROVIDER_ERROR(10004, "下游服务异常"),
    ROUTING_FAILURE(10005, "请求路由失败"),
    NO_HANDLER_FOUND(10006, "找不到执行的路径"),
    NULL_POINT_ERROR(10007, "缺少相关数据"),
    REDIS_ERROR(10008, "服务器缓存出现异常"),
    ES_SEARCH_ERROR(10009, "ES查询出错"),
    BEAN_ERROR(10010, "bean验证错误"),
    REPEAT_SENDING_SMS_CODE(10011, "短信验证码60s内不能重复发送"),
    SYSTEM_OPERATION_ERROR(10012, "操作失败，请重试或联系管理员"),
    GLOBAL_FALLBACK_EXCEPTION(10013, "全局降级(服务提供者)：系统繁忙，请稍后再试"),
    DUBBO_TIMEOUT_EXCEPTION(10013, "下游服务超时，请稍后再试"),
    SYSTEM_GATEWAY_ERROR(10014, "请求过于频繁，请稍后再试"),
    SYSTEM_DATE_PARSER_ERROR(10015, "系统日期转换错误"),
    DUBBO_FORWARDING_ERROR(10016, "服务器繁忙，请稍后再试"),

    // 用户服务相关错误类型
    USER_NOT_LOGGED_IN(20000, "用户尚未登录"),
    REGISTER_DUP_FAILED(20001, "手机号已存在"),
    USER_DOES_NOT_EXIST(20002, "用户不存在"),
    LOGIN_FAILED(20003, "手机号或密码错误"),
    SEND_SMS_FAILED(20004, "发送验证码出现异常"),
    PHONE_INCORRECT(20005, "请输入正确的手机号"),
    SMS_CODE_EXPIRED(20006, "验证码已过期"),
    SMS_CODE_INCORRECT(20007, "验证码不正确"),
    FILE_MAX_SIZE_ERROR(20008, "图片过大，仅支持上传2MB以下的图片"),
    ;

    private Integer errCode;
    private String errMsg;

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}