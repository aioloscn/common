package com.aiolos.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Aiolos
 * @date 2021/3/20 7:03 上午
 */
@Getter
@AllArgsConstructor
public enum BookStatus {

    DELETED(0, "已删除"),
    ON_THE_SHELVES(1, "已上架"),
    NOT_ON_THE_SHELVES(2, "未上架");

    private final Integer type;
    private final String value;
}
