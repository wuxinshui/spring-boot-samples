package com.wxs.exception.controller;

import com.wxs.exception.common.Result;
import com.wxs.exception.common.ResultCode;
import com.wxs.exception.vo.PermissionInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/2/19 11:00
 * 自定义注解测试
 */
@Slf4j
@RestController
@RequestMapping("/constraint")
public class CustomConstraintController {
    /**
     * 需要@Validated支持
     * @param permissionInfo
     * @return
     */
    @PostMapping("/validateDate")
    public Result testValidateDate(@Valid @RequestBody PermissionInfo permissionInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            // 汇总错误消息
            List<String> validateResult=bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
            return new Result(ResultCode.PARAM_ERR.getCode(),validateResult.toString());
        }
        log.info("accept id is {}",permissionInfo);
        return new Result();
    }
}
