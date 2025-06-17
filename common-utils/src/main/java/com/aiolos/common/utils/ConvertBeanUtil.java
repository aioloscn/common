package com.aiolos.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertBeanUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    /**
     * 将一个对象转换为另一个对象
     * @param source
     * @param targetClass
     * @return
     * @param <T>
     */
    public static <T> T convert(Object source, Class<T> targetClass) {
        if (source == null || targetClass == null) {
            return null;
        }
        return objectMapper.convertValue(source, targetClass);
    }

    /**
     * 将List集合转成另一个对象集合，支持 redis的LinkedHashMap
     * @param source
     * @param targetClass
     * @return
     * @param <S>
     * @param <T>
     */
    public static <S, T> List<T> convertList(List<S> source, Class<T> targetClass) {
        if (source == null || targetClass == null) {
            return new ArrayList<>();
        }
        return source.stream()
                .map(item -> objectMapper.convertValue(item, targetClass))
                .collect(Collectors.toList());
    }
}
