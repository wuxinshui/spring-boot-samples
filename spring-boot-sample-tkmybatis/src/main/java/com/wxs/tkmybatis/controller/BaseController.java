package com.wxs.tkmybatis.controller;

import org.springframework.ui.ModelMap;

/**
 * <p>BaseController</p>
 *
 * @author wuxinshui
 */
public class BaseController {

    public ModelMap result(String code, String msg, String data) {
        ModelMap modelMap = new ModelMap();
        modelMap.put("code", code);
        modelMap.put("msg", msg);
        modelMap.put("result", data);
        return modelMap;
    }
}
