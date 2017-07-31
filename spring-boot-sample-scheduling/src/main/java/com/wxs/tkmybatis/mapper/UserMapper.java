package com.wxs.tkmybatis.mapper;

import com.wxs.tkmybatis.model.User;
import com.wxs.tkmybatis.util.TKMapper;

public interface UserMapper extends TKMapper<User> {
	int insert(User user);
	User selectOne(int id);
}