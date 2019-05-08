package com.wxs.aop.controller;

import com.wxs.aop.vo.AdviceResponse;
import com.wxs.aop.vo.AdviceVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/4/2 11:46
 */
@RestController
@Api(description = "接口增强测试",tags = {"advice"})
@RequestMapping("/advice")
public class AdviceController {

    @GetMapping("/get")
    public AdviceResponse get(){
        AdviceResponse adviceVo=new AdviceVo("sansi",12);
        adviceVo.setCreateUser("admin");
        adviceVo.setCreateTime(LocalTime.now());
        return adviceVo;
    }
}
