package com.wxs.exception.controller;

import com.wxs.exception.common.BaseController;
import com.wxs.exception.common.BaseHanler;
import com.wxs.exception.common.Result;
import com.wxs.exception.service.InheritExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/2/11 16:06
 * 异常处理的一种：实现
 */
@RestController
@RequestMapping("/impl")
public class ImplHandlerExceptionController implements BaseHanler {
    @Autowired
    private InheritExceptionService inheritExceptionService;

    @GetMapping("/system")
    public Result testInherit() {
        return inheritExceptionService.print();
    }
}
