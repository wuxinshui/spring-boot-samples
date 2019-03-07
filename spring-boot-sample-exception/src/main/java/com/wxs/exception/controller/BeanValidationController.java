package com.wxs.exception.controller;

import com.wxs.exception.common.BaseController;
import com.wxs.exception.common.Result;
import com.wxs.exception.common.ResultCode;
import com.wxs.exception.service.InheritExceptionService;
import com.wxs.exception.vo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/2/11 16:06
 * JSR 303 - Bean Validation
 */
@RestController
@RequestMapping("/valid")
@Slf4j
@Validated
public class BeanValidationController{


    /**
     * 测试 Bean validation+BindingResult
     * BindingResult 接受校验结果
     * @param userInfo
     * @param bindingResult
     * @return
     */
    @PostMapping("/bean/binding")
    public Result testBeanAndBindingResult(@Valid UserInfo userInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
           // 汇总错误消息
           List<String> validateResult=bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
            return new Result(ResultCode.PARAM_ERR.getCode(),validateResult.toString());
        }
        return new Result();
    }

    /**
     * 测试 Bean validation+ControllerAdvice
     * ControllerAdvice 全局 处理校验异常 BindingException
     * 暂时没起作用
     * @param userInfo
     * @return
     */
    @PostMapping("/bean/advice")
    public Result testBeanAndControllerAdvice(@Valid UserInfo userInfo) {
        return new Result();
    }

    /**
     * 需要@Validated支持
     * @param id
     * @return
     */
    @PostMapping("/field")
    public Result testFieldBindingResult(@NotNull(message = "用户ID不能为空")@Min(value = 1,message = "最小值为1") Integer id) {
        log.info("accept id is {}",id);
        return new Result();
    }
}
