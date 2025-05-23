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

    NO(0, "否"),
    YES(1, "是");

    private final Integer type;
    private final String value;
}