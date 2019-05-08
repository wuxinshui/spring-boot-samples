package com.wxs.aop.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/4/2 11:48
 */
@Data
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdviceVo extends AdviceResponse {
    private String name;
    private int age;
}
