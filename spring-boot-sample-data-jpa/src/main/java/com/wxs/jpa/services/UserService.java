package com.wxs.jpa.services;

import com.wxs.jpa.domain.User;

import java.util.List;

/**
 * @Description:
 * @Author:Wuxisnhui
 * @Date:2017/6/12 16:58
 */
public interface UserService {
	List<User> selectAll();
}
