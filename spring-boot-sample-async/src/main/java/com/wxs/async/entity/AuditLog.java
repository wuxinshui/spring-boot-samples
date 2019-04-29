package com.wxs.async.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/4/28 17:02
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
