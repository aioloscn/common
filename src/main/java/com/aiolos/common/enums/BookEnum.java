package com.aiolos.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Aiolos
 * @date 2021/4/9 6:28 下午
 */
@Getter
@AllArgsConstructor
public enum BookEnum {

    HOT(1, "热度"),
    NAME(2, "名称"),
    PRICE(3, "价格");

    private Integer type;
    private String value;
}
