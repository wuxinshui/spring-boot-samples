package com.wxs.exception.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/2/18 18:25
 *  菜单权限 验证自定义校验注解、嵌套注解
 */
@Data
@ToString
public class Permission {
    // 非空判断
    @NotNull(message = "菜单id不能为空")
    private Long id;
    @NotNull(message = "菜单名称不能为空")
    private String menu;

}

