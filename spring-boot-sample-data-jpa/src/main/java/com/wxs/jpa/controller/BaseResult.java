package com.wxs.jpa.controller;

import org.springframework.ui.ModelMap;

/**
 * @Description:
 * @Author:Wuxisnhui
 * @Date:2017/6/12 18:13
 */
public class BaseResult {

	/**
	 * 接口返回通用规范
	 * @param code
	 * @param msg
	 * @param data
	 * @return
	 */
	public ModelMap result(String code, String msg, Object data) {
		ModelMap modelMap = new ModelMap();
		modelMap.put("code", code);
		modelMap.put("msg", msg);
		modelMap.put("result", data);
		return modelMap;
	}
}
