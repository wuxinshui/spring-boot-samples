package com.wxs.aop.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/4/2 11:47
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdviceResponse {
    private LocalTime createTime;
    private String createUser;
}
