package com.aiolos.common.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccessContext implements Serializable {
    
    private Long userId;
    private boolean isAnonymous;
    private String nickName;
    private String avatar;
}
