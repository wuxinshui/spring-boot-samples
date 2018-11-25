package com.wxs.jersey.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class VerifyInfo {
    private String username;
    private String pass;
}
