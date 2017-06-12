package com.wxs.redis.services;

import com.wxs.redis.domain.User;

import java.util.List;

/**
 * @Description:
 * @Author:Wuxisnhui
 * @Date:2017/6/12 16:58
 */
public interface UserService {
	List<User> selectAll();
}
