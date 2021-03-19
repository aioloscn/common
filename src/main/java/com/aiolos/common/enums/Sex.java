package com.aiolos.common.enums;

/**
 * @author Aiolos
 * @date 2020/10/14 10:54 下午
 */
public enum Sex {

    woman(0 ,"女"),
    man(1, "男"),
    secret(2, "保密");

    private final Integer type;
    private final String value;

    public Integer getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    Sex(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
