package com.wxs.excel.utils;

import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

import java.util.stream.Stream;

/**
 * @description:
 * @author: Zhenglai Zhang
 * @create: 2019/1/23 3:40 PM
 **/

public class CustomizedHeaderNameMapping<T> extends HeaderColumnNameTranslateMappingStrategy<T> {
    @Override
    public String[] generateHeader() {
        return Stream.of(super.generateHeader())
                .map(getColumnMapping()::get)
                .toArray(String[]::new);
    }
}
