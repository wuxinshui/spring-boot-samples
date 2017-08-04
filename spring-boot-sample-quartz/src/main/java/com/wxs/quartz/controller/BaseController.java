package com.wxs.quartz.controller;

import org.springframework.ui.ModelMap;

/**
 * <p>BaseController</p>
 *
 * @author wuxinshui
 */
public class BaseController {

	/**
	 * 接口返回通用规范
	 *
	 * @param code
	 * @param msg
	 * @param data
	 * @return
	 */
	public ModelMap result(String code, String msg, String data) {
		ModelMap modelMap = new ModelMap();
		modelMap.put("code", code);
		modelMap.put("msg", msg);
		modelMap.put("result", data);
		return modelMap;
	}
}
