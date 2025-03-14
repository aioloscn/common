package com.aiolos.common.utils;

import cn.hutool.core.bean.BeanUtil;

import java.util.ArrayList;
import java.util.List;

public class ConvertBeanUtils {

    /**
     * 将一个对象转换为另一个对象
     * @param source
     * @param targetClass
     * @return
     * @param <T>
     */
    public static <T> T convert(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        T t = newInstance(targetClass);
        BeanUtil.copyProperties(source, t);
        return t;
    }

    public static <K, T> List<T> convertList(List<K> sourceList, Class<T> targetClass) {
        if (sourceList == null) {
            return null;
        }
        List<T> targetList = new ArrayList<>((int) (sourceList.size() / 0.75) + 1);
        for (K source : sourceList) {
            targetList.add(convert(source, targetClass));
        }
        return targetList;
    }

    private static <T> T newInstance(Class<T> targetClass) {
        try {
            return targetClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
