package com.wxs.exception.vo;

import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class VerifyPass {
    private String username;
    private String pass;
}
