package com.aiolos.common.utils;

import cn.hutool.core.bean.BeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ConvertBeanUtil {

    /**
     * 将一个对象转换为另一个对象
     * @param source
     * @param supplier
     * @return
     * @param <T>
     */
    public static <T> T convert(Object source, Supplier<T> supplier) {
        if (source == null || supplier == null) {
            return null;
        }
        T t = supplier.get();
        if (t != null) {
            BeanUtil.copyProperties(source, t);
        }
        return t;
    }

    public static <S, T> List<T> convertList(List<S> source, Supplier<T> supplier) {
        if (source == null || supplier == null) {
            return new ArrayList<>();
        }
        return source.stream().map(s -> {
                    T t = supplier.get();
                    BeanUtil.copyProperties(s, t);
                    return t;
                })
                .collect(Collectors.toList());
    }
}
