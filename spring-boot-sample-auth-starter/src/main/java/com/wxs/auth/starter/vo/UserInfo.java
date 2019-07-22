package com.wxs.auth.starter.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/6/11 9:47
 */
@Data
public class UserInfo {
    private String name;
    private String age;
}
