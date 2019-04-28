package com.wxs.async.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/4/28 17:02
 */
public class AuditLog {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String body;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
