package com.wxs.rest.httpclient.bo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StuInfo {
    private String age;
    private String name;
}
