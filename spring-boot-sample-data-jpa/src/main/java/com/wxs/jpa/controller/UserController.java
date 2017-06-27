package com.wxs.jpa.controller;

import com.wxs.jpa.domain.User;
import com.wxs.jpa.services.UserService;
import com.wxs.jpa.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(value = "/selectGet/{id}", method = RequestMethod.GET)
	public ModelMap selectGet(@PathVariable Integer id) {
		User user = userService.selectOne(id);
		return result(Constant.SUCCESS_CODE, Constant.SUCCESS_MSG, user);
	}

	@RequestMapping(value = "/selectPost", method = RequestMethod.POST)
	public ModelMap selectPost(String username, String password) {
		List<User> userList = userService.selectPost(username, password);

		return result(Constant.SUCCESS_CODE, Constant.SUCCESS_MSG, userList);
	}

	@RequestMapping(value = "/selectPut", method = RequestMethod.PUT)
	public ModelMap selectPut(String username, String password) {
		List<User> userList = userService.selectPost(username, password);

		return result(Constant.SUCCESS_CODE, Constant.SUCCESS_MSG, userList);
	}

	@RequestMapping(value = "/selectDelete/{id}", method = RequestMethod.DELETE)
	public ModelMap selectDelete(@PathVariable Integer id) {

		userService.selectDelete(id);

		return result(Constant.SUCCESS_CODE, Constant.SUCCESS_MSG, 0);
	}

	@RequestMapping(value = "/selectHead/{id}", method = RequestMethod.HEAD)
	public ModelMap selectHead(@PathVariable Integer id) {
		User user = userService.selectOne(id);
		return result(Constant.SUCCESS_CODE, Constant.SUCCESS_MSG, user);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelMap login() {
		List<User> userList = userService.selectAll();

		return result(Constant.SUCCESS_CODE, Constant.SUCCESS_MSG, userList);
	}

}
