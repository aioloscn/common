package com.aiolos.common.enums;

import com.aiolos.common.exception.CommonError;
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
    HYSTRIX_FALLBACK(10004, "服务宕机或地址错误"),
    ROUTING_FAILURE(10005, "请求路由失败"),
    NO_HANDLER_FOUND(10006, "找不到执行的路径"),
    NULL_POINT_ERROR(10007, "缺少相关数据"),
    REDIS_ERROR(10008, "服务器缓存出现异常"),
    ES_SEARCH_ERROR(10009, "ES查询出错"),
    BEAN_ERROR(10010, "bean验证错误"),
    REPEAT_SENDING_SMS_CODE(10011, "短信验证码60s内不能重复发送"),
    SYSTEM_OPERATION_ERROR(10012, "操作失败，请重试或联系管理员"),
    GLOBAL_FALLBACK_EXCEPTION(10013, "全局降级(服务提供者)：系统繁忙，请稍后再试"),
    FEIGN_FALLBACK_EXCEPTION(10013, "Feign降级(服务调用者)：系统繁忙，请稍后再试"),
    SYSTEM_ZUUL_ERROR(10014, "请求过于频繁，请稍后再试"),
    SYSTEM_DATE_PARSER_ERROR(10015, "系统日期转换错误"),
    ZUUL_FORWARDING_ERROR(10016, "服务器繁忙，请稍后再试"),

    // 用户服务相关错误类型
    USER_NOT_LOGGED_IN(20000, "用户尚未登录"),
    REGISTER_DUP_FAILED(20001, "手机号已存在"),
    USER_DOES_NOT_EXIST(20002, "用户不存在"),
    LOGIN_FAILED(20003, "手机号或密码错误"),
    SEND_SMS_FAILED(20004, "发送验证码出现异常"),
    PHONE_INCORRECT(20005, "请输入正确的手机号"),
    SMS_CODE_EXPIRED(20006, "验证码已过期"),
    SMS_CODE_INCORRECT(20007, "验证码不正确"),
    FOLLOW_FAILED(20008, "关注失败"),
    CANCEL_FOLLOW_FAILED(20009, "取消关注失败"),
    CONSTRAINT_VIOLATION(20010, "违反唯一约束"),
    DUPLICATION_OPERATION(20011, "重复操作"),
    TOKEN_INVALID(20012, "会话失效，请重新登录"),
    VERIFY_QQ_ERROR(20013, "用户QQ校验异常"),
    ACCOUNT_FROZEN(20014, "账号已被冻结，请联系管理员"),
    REGISTER_FAILED(20015, "注册失败"),
    USER_UPDATE_FAILED(20016, "用户信息更新失败，请联系管理员"),
    USER_INACTIVE_ERROR(20017, "请前往[账号设置]修改信息激活后再进行操作"),
    OPENID_FAILED(20018, "临时登录凭证已过期"),
    SUPER_ADMINISTRATOR_BINDING_ERROR(20019, "无法绑定超级管理员账号"),
    TOKEN_IS_EXPIRED(20020, "令牌已过期"),
    PERMISSION_DENIED(20021, "你不具备相关权限"),
    FACE_MODEL_DOES_NOT_EXIST(20022, "人脸模型不存在"),
    FACIAL_RECOGNITION_SERVICE_IS_ABNORMAL(20023, "人脸识别服务异常"),
    UNABLE_TO_IDENTIFY(20024, "无法识别出人脸"),
    THERE_ARE_MORE_PEOPLE_IN_THE_PHOTO(20025, "照片中存在多张人脸"),
    NOT_IN_PERSON(20026, "签到无效，非本人签到"),
    FAILED_TO_CREATE_FACE_MODEL(20027, "创建人脸模型失败"),

    // 文章业务相关错误类型
    INFORMATION_RELEASE_FAILED(30000, "短消息提交失败"),
    INFORMATION_IMAGE_RELEASE_FAILED(30001, "短消息的图片提交失败"),
    IMAGE_DATA_EMPTY(30002, "上传图片数据为空"),
    IMAGE_DATA_WRONGFUL(30003, "图片数据不合法"),
    DELETE_FAILED(30004, "删除失败"),
    THUMBS_UP_FAILED(30005, "点赞失败"),
    CANCEL_THUMBS_UP_FAILED(30006, "取消点赞失败"),
    RELEASE_FAILED(30007, "发布失败"),
    ACTIVITY_RELEASE_FAILED(30008, "活动发布失败"),
    ACTIVITY_IMAGE_RELEASE_FAILED(30009, "活动的图片提交失败"),
    FILE_UPLOAD_NULL_ERROR(30010, "文件不能为空"),
    FILE_FORMAT_ERROR(30011, "图片格式不支持"),
    FILE_UPLOAD_FAILED(30012, "上传失败"),
    FILE_MAX_SIZE_ERROR(30013, "图片过大，仅支持上传2MB以下的图片"),
    ARTICLE_COVER_NOT_EXIST_ERROR(30014, "文章封面不存在，请选择"),
    ARTICLE_CATEGORY_NOT_EXIST_ERROR(30015, "请选择正确的文章类型"),
    ARTICLE_CREATE_FAILED(30016, "发布文章失败.."),
    ARTICLE_QUERY_PARAMS_ERROR(30017, "查询差数有误"),
    MUST_BE_IN_JPG_FORMAT(30018, "必须是JPG格式的图片"),
    PICTURE_SAVE_FAILED(30019, "图片保存失败"),

    // 管理员相关类型错误
    ADMIN_NOT_EXIST_ERROR(40001, "管理员账号不存在或密码错误"),
    ADMIN_USERNAME_EXIST_ERROR(40002, "管理员登录名已存在"),
    ADMIN_PASSWORD_NULL_ERROR(40003, "密码不能为空"),
    ADMIN_PASSWORD_ERROR(40004, "密码为空或两次输入不一致"),
    ADMIN_INSERT_FAILED(40005, "添加管理员失败"),

    // 图书业务相关错误类型
    BOOK_DOES_NOT_EXIST(50001, "书籍不存在"),
    ADD_BOOK_FAILED(50002, "添加书籍失败"),
    UPDATE_BOOK_FAILED(50003, "修改书籍失败"),
    DELETE_BOOK_FAILED(50004, "删除书籍失败"),
    ADD_TO_CART_FAILED(50005, "添加到购物车失败"),
    UPDATE_CART_FAILED(50006, "购物车数据更新失败"),
    ADD_ORDER_FAILED(50007, "保存订单失败"),
    UPDATE_ORDER_FAILED(50008, "修改订单失败"),
    DELETE_ORDER_FAILED(50009, "删除订单失败"),
    RECEIPT_FAILED(50010, "确认收货失败"),

    // 协同办公相关错误类型
    FAILED_TO_OBTAIN_RISK_LEVEL(60001, "获取风险等级失败"),
    CHECKIN_FAILED(60002, "签到失败"),
    SIGNED_IN_TODAY(60003, "今天已经签到过了"),
    ;

    private Integer errCode;
    private String errMsg;

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
