package com.aiolos.common.enums;

/**
 * 文章发布类型类型枚举
 * @author Aiolos
 * @date 2020/11/26 7:20 下午
 */
public enum ArticleAppointType {

    TIMEING(1, "文章定时发布"),
    IMMEDIATELY(0, "文章立即发布");

    private final Integer type;
    private final String value;

    ArticleAppointType(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

    public Integer getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
