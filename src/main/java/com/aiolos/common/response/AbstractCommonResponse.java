package com.aiolos.common.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aiolos
 * @date 2021/3/15 11:01 上午
 */
@Data
abstract class AbstractCommonResponse<T> {

    /**
     * 响应业务状态码
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 响应中的数据
     */
    private T data;

    Map<Object, Object> map;

    AbstractCommonResponse(int capacity) {
        map = new HashMap<>(capacity);
    }

    public AbstractCommonResponse put(Object key, Object value) {
        map.put(key, value);
        return this;
    }
}
