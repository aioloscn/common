package com.aiolos.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GatewayHeaderEnum {
    
    USER_LOGIN_ID("gh_user_id", "用户id"),
    USER_INFO_JSON("gh_user_info_json", "用户信息json"),
    ;
    
    private String headerName;
    private String desc;
}
