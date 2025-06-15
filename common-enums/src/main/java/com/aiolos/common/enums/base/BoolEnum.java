package com.aiolos.common.enums.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Aiolos
 * @date 2020/11/26 7:12 下午
 */
@Getter
@AllArgsConstructor
public enum BoolEnum {

    NO(0, false, "否"),
    YES(1, true, "是");

    private final Integer code;
    private final Boolean value;
    private final String desc;
}