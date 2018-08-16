package com.wxs.mybatis.controller;

import com.wxs.mybatis.model.User;
import com.wxs.mybatis.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.wxs.mybatis.util.Constant.*;

/**
 * @Description:模拟登录
 * @Author:Wuxinshui
 * @Date:2017/4/18 20:32
 */
@Controller
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


    /**
     * Form 表单请求
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelMap list() {
        try {
            List<Integer> list = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toList());

            return result(SUCCESS_CODE, SUCCESS_MSG, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result(FAIL_CODE, FAIL_MSG, null);
    }


    /**
     * Form 表单请求
     *
     * @return
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        try {
            List<Integer> list = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toList());

            return "test";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "test";
    }
}
