package com.aiolos.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GatewayHeaderEnum {
    
    USER_LOGIN_ID("gh_user_id", "用户id"),
    USER_INFO_JSON("gh_user_info_json", "用户信息json"),
    IS_ANONYMOUS("gh_is_anonymous", "是否是未登录用户"),
    DEVICE_ID("gh_device_id", "设备id"),
    ;
    
    private String headerName;
    private String desc;
}
