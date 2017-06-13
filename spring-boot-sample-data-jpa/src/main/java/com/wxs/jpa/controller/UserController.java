package com.wxs.jpa.controller;

import com.wxs.jpa.domain.User;
import com.wxs.jpa.services.UserService;
import com.wxs.jpa.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelMap login() {
		List<User> userList = userService.selectAll();

		return result(Constant.SUCCESS_CODE, Constant.SUCCESS_MSG, userList);
	}

}
