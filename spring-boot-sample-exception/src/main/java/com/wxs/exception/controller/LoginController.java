package com.wxs.exception.controller;

import com.wxs.exception.common.Result;
import com.wxs.exception.common.ResultCode;
import com.wxs.exception.common.exception.ApiException;
import com.wxs.exception.common.exception.OrderNotFoundException;
import com.wxs.exception.service.impl.LoginServiceImpl;
import com.wxs.exception.vo.VerifyInfo;
import com.wxs.exception.vo.VerifyPass;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping("/login")
@Api(value = "/login", tags = "用户登录接口")
@Slf4j
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping("/verify")
    @ApiOperation(value = "校验", notes = "校验")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid tag value")})
    public Result verifyLogin(VerifyPass verifyPass) {
        Result<VerifyInfo> result = new Result<>();
        try {
            VerifyInfo verifyInfo;
            verifyInfo = loginService.verify(verifyPass.getUsername(), verifyPass.getPass());
            if (null == verifyInfo) {
                result.doErrorHandle("当前用户不存在");
            }
            result.setData(verifyInfo);
            return result;
        } catch (Exception e) {
            result.doErrorHandle("当前用户不存在");
            return result;
        }
    }

    @GetMapping("/test")
    @ApiOperation(value = "测试接口", notes = "测试接口")
    public Result test() {
        log.info("/login/test------------");
        loginService.testApiException();
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value="/orders/{id}", method=GET)
    public String showOrder(@PathVariable("id") long id, @NotNull VerifyPass verifyPass, Model model) {
        VerifyInfo  verifyInfo = loginService.testOrderNotFoundException(verifyPass.getUsername(), verifyPass.getPass());

        if (verifyInfo == null) {
            throw new OrderNotFoundException(id);
        }

        model.addAttribute(verifyInfo);
        return "orderDetail";
    }
}
