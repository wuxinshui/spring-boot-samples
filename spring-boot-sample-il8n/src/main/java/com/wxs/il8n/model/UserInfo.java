package com.wxs.il8n.model;

import lombok.Data;

@Data
public class UserInfo {
    private Long id;
    private String name;
    private Integer age;

    public UserInfo() {
    }

    public UserInfo(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
