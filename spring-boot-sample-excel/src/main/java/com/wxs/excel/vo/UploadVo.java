package com.wxs.excel.vo;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/6/4 14:45
 */

public class UploadVo {
    private String name;
    private String sex;
    private String age;


    public UploadVo(String name, String sex, String age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
