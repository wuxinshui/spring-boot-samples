package com.wxs.tkmybatis.controller;

import com.wxs.tkmybatis.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static com.wxs.tkmybatis.util.Constants.*;

/**
 * @Description:
 * @Author:Wuxinshui
 * @Date:2017/4/18 20:32
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private LoginService loginService;


    @RequestMapping(name = "/login/{username}/{password}", method = RequestMethod.POST)
    public ModelMap login(@PathVariable String username, @PathVariable String password) {
        try {
            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                return result(FAIL_CODE, FAIL_MSG, null);
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
}
