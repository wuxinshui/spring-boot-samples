package com.wxs.exception.vo;

import com.google.gson.Gson;
import com.wxs.exception.constraints.CustomValidateDate;
import lombok.Data;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/2/18 18:23
 * 用户权限 验证自定义校验注解、嵌套注解
 */
@Data
@ToString
@CustomValidateDate(start = "startDate",end = "endDate",message = "结束时间不能小于开始时间")
public class PermissionInfo {

    // 非空判断
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @NotNull(message = "开始时间不能为空")
    private Date startDate;

    @NotNull(message = "结束时间不能为空")
    private Date endDate;

    @Valid
    @NotNull(message = "菜单列表不能为空")
    private List<Permission> permissions;

}
