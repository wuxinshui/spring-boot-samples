package com.wxs.mybatis.controller;

import com.wxs.mybatis.model.User;
import com.wxs.mybatis.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.wxs.mybatis.util.Constant.FAIL_CODE;
import static com.wxs.mybatis.util.Constant.FAIL_MSG;

/**
 * @Description:模拟登录
 * @Author:Wuxinshui
 * @Date:2017/4/18 20:32
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private LoginService loginService;


	/**
	 * Form 表单请求
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelMap login(Integer id) {
		try {
			User user = loginService.selectOne(id);
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result(FAIL_CODE, FAIL_MSG, null);
	}
}
