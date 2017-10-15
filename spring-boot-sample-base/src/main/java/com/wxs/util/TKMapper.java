package com.wxs.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Description:
 * @Author:Wuxinshui
 * @Date:2017/4/18 17:09
 */
public interface TKMapper<T> extends Mapper<T>,MySqlMapper<T>{
	// TODO
	// FIXME 特别注意，该接口不能被扫描到，否则会出错
}
