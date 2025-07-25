package com.aiolos.common.util;

import cn.hutool.core.bean.BeanUtil;
import com.aiolos.common.wrapper.Page;
import com.aiolos.common.wrapper.PageResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PageConvertUtil {

    public static <T, R> PageResult<R> convert(Page<T> page, Class<R> targetClass) {
        PageResult<R> result = new PageResult<>();
        result.setTotal(page.getTotal());
        result.setSize(page.getSize());
        result.setCurrent(page.getCurrent());
        result.setRecords(convertRecords(page.getRecords(), targetClass));
        result.setHasPrevious(page.hasPrevious());
        result.setHasNext(page.hasNext());
        return result;
    }

    private static <T, R> List<R> convertRecords(List<T> records, Class<R> targetClass) {
        if (records == null) {
            return new ArrayList<>();
        }
        return records.stream()
                .map(record -> convertRecord(record, targetClass))
                .collect(Collectors.toList());
    }

    private static <T, R> R convertRecord(T source, Class<R> targetClass) {
        try {
            R target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtil.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert target class", e);
        }
    }
    
    public static <T> PageResult<T> convert(Page<T> page) {
        PageResult<T> result = new PageResult<>();
        result.setTotal(page.getTotal());
        result.setSize(page.getSize());
        result.setCurrent(page.getCurrent());
        result.setRecords(page.getRecords());
        result.setHasPrevious(page.hasPrevious());
        result.setHasNext(page.hasNext());
        return result;
    }

    public static <T, R> PageResult<R> convert(PageResult<T> source, Class<R> targetClass) {
        PageResult<R> target = new PageResult<>();
        target.setTotal(source.getTotal());
        target.setSize(source.getSize());
        target.setCurrent(source.getCurrent());
        target.setRecords(convertRecords(source.getRecords(), targetClass));
        target.setHasPrevious(source.getHasPrevious());
        target.setHasNext(source.getHasNext());
        return target;
    }
}
