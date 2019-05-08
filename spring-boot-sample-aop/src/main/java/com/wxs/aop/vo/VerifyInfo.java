package com.wxs.aop.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class VerifyInfo {

    private String username;
    private int age;
    private boolean isPass;

}
