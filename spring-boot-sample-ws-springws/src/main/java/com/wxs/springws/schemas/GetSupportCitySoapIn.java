package com.wxs.springws.schemas;

import java.io.Serializable;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/9/10 15:54
 */
public class GetSupportCitySoapIn implements Serializable {
    private String byProvinceName;

    public GetSupportCitySoapIn(String byProvinceName) {
        this.byProvinceName = byProvinceName;
    }

    public String getByProvinceName() {
        return byProvinceName;
    }

    public void setByProvinceName(String byProvinceName) {
        this.byProvinceName = byProvinceName;
    }
}
