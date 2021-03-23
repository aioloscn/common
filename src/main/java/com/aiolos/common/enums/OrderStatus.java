package com.aiolos.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Aiolos
 * @date 2021/3/21 5:01 下午
 */
@Getter
@AllArgsConstructor
public enum OrderStatus {

    DELETED(0, "已删除"),
    UNPAID(1, "未支付"),
    PAID(2, "已支付"),
    RECEIVED(3, "已收货");

    private final Integer type;
    private final String value;
}
