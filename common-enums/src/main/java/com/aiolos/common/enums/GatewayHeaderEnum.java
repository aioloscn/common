package com.aiolos.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GatewayHeaderEnum {
    
    USER_LOGIN_ID("gh_user_id", "用户id"),
    ;
    
    private String headerName;
    private String desc;
}
