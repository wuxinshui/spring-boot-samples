package com.wxs.aop.controller;

import com.wxs.aop.common.Result;
import com.wxs.aop.service.impl.LoginServiceImpl;
import com.wxs.aop.vo.VerifyInfo;
import com.wxs.aop.vo.VerifyPass;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/register")
@Api(value = "/register", tags = "用户注册接口")
@Slf4j
public class RegisterController {

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping("/verify")
    @ApiOperation(value = "校验", notes = "校验")
    public Result verifyLogin(VerifyPass verifyPass) {
        Result<VerifyInfo> result = new Result<>();
        try {
            VerifyInfo pa;
            pa = loginService.verify(verifyPass.getUsername(), verifyPass.getPass());
            if (null == pa) {
                result.doErrorHandle("当前用户不存在");
            }
            result.setData(pa);
            return result;
        } catch (Exception e) {
            result.doErrorHandle("当前用户不存在");
            return result;
        }
    }

    @GetMapping("/test")
    @ApiOperation(value = "测试接口", notes = "测试接口")
    public String test() {
        log.info("/login/test------------");
        return "success";
    }
}
