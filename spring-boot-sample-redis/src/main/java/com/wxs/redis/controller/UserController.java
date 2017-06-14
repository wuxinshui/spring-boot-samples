package com.wxs.redis.controller;

import com.wxs.redis.domain.User;
import com.wxs.redis.services.UserService;
import com.wxs.redis.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author:Wuxisnhui
 * @Date:2017/6/12 17:00
 */
@RestController
@RequestMapping("/user")

public class UserController extends BaseResult {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	RedisConnectionFactory redisConnectionFactory;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@Cacheable(value = "search")
	public ModelMap search() {
		List<User> userList = userService.selectAll();
		LOGGER.info("search....");
		return result(Constant.SUCCESS_CODE, Constant.SUCCESS_MSG, userList);
	}

}
