package com.wxs.webservice.schemas;

import java.io.Serializable;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/9/10 15:54
 */
public class GetSupportCitySoapOut implements Serializable {
    private String[] getSupportCityResult;

    public GetSupportCitySoapOut(String[] getSupportCityResult) {
        this.getSupportCityResult = getSupportCityResult;
    }

    public String[] getGetSupportCityResult() {
        return getSupportCityResult;
    }

    public void setGetSupportCityResult(String[] getSupportCityResult) {
        this.getSupportCityResult = getSupportCityResult;
    }
}
