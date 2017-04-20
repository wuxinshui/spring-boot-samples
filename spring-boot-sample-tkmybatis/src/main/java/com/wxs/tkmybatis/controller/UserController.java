package com.wxs.tkmybatis.controller;

import com.wxs.tkmybatis.services.LoginService;
import com.wxs.tkmybatis.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.wxs.tkmybatis.util.Constant.*;

/**
 * @Description:模拟登录，不同的参数请求方式
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
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelMap login(String username, String password, MultipartFile file) {
		try {
			if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
				return result(FAIL_CODE, "用户名或密码不能为空", null);
			}

			int result = loginService.login(username, password);
			if (result == 1) {
				return result(SUCCESS_CODE, SUCCESS_MSG, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result(FAIL_CODE, FAIL_MSG, null);
	}

	/**
	 * 使用注解@RequestParam
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login1", method = RequestMethod.POST)
	public ModelMap login1(@RequestParam("username") String username, @RequestParam("password") String password) {

		System.out.println("username: " + username + ",password: " + password);
		return result(SUCCESS_CODE, SUCCESS_MSG, null);
	}

	/**
	 * 使用注解@PathVariable
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login2/{username}/{password}", method = RequestMethod.POST)
	public ModelMap login2(@PathVariable String username, @PathVariable String password) {
		System.out.println("username: " + username + ",password: " + password);
		return result(SUCCESS_CODE, SUCCESS_MSG, null);
	}

	/**
	 * 使用注解@RequestBody
	 *
	 * @param userVo
	 * @return
	 */
	@RequestMapping(value = "/login3", method = RequestMethod.POST)
	public ModelMap login3(@RequestBody UserVo userVo) {
		System.out.println("username: " + userVo.getUsername() + ",password: " + userVo.getPassword());
		return result(SUCCESS_CODE, SUCCESS_MSG, null);
	}

	/**
	 * 使用注解
	 * 文件上传
	 *
	 * @param userVo
	 * @return
	 */
	@RequestMapping(value = "/login4", method = RequestMethod.POST)
	public ModelMap login4(UserVo userVo) {
		System.out.println("username: " + userVo.getUsername() + ",password: " + userVo.getPassword());
		System.out.println("file;"+userVo.getFile());
		return result(SUCCESS_CODE, SUCCESS_MSG, null);
	}


}
