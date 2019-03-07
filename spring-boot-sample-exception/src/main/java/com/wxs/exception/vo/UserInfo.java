package com.wxs.exception.vo;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/2/11 17:28
 */
@Data
@ToString
public class UserInfo {
    // 非空判断
    @NotNull(message = "id不能为空")
    private Long id;

    @Future(message = "需要一个将来日期") // 只能是将来的日期
    // @Past //只能去过去的日期
    @DateTimeFormat(pattern = "yyyy-MM-dd") // 日期格式化转换
    @NotNull(message = "日期不能为空") // 不能为空
    private Date date;

    @NotNull(message = "金额不能为空") // 不能为空
    @DecimalMin(value = "0.1") // 最小值0.1元
    @DecimalMax(value = "10000.00") // 最大值10000元
    private Double doubleValue = null;

    @Min(value = 1, message = "最小值为1") // 最小值为1
    @Max(value = 88, message = "最大值为88") // 最大值88
    @NotNull(message = "年龄不能为空") // 不能为空
    private Integer age;

    @Range(min = 1, max = 888, message = "范围为1至888") // 限定范围
    private Long range;

    // 邮箱验证
    @Email(message = "邮箱格式错误")
    private String email;

    @Size(min = 20, max = 30, message = "字符串长度要求20到30之间。")
    private String size;
}
