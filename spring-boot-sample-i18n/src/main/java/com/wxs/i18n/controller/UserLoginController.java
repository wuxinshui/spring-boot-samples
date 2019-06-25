package com.wxs.i18n.controller;

import com.wxs.i18n.dao.UserInfoMapper;
import com.wxs.i18n.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/6/10 19:15
 */
@RestController
@RequestMapping("/il8n")
public class UserLoginController {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @PostMapping("/user-add")
    public Map getUsers(@RequestBody LoginVo loginVo) {
        return Map.of("data", loginVo);
    }

}
