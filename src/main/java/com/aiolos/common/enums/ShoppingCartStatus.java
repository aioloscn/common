package com.aiolos.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Aiolos
 * @date 2021/3/20 6:36 下午
 */
@Getter
@AllArgsConstructor
public enum ShoppingCartStatus {

    DELETED(0, "已删除"),
    NORMAL(1, "正常");

    private Integer type;
    private String value;
}
