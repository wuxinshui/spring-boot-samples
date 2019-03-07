package com.wxs.exception.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/2/18 18:34
 * 验证自定义校验注解
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, PARAMETER, TYPE})
@Retention(RUNTIME)
@Repeatable(CustomValidateDate.List.class)
@Documented
@Constraint(validatedBy = {CustomDateValidator.class})
public @interface CustomValidateDate {

    String start() default "from";

    String end() default "to";

    String message() default "{com.wxs.exception.constraints.CustomValidateDate}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Defines several {@link CustomValidateDate} annotations on the same element.
     *
     * @see javax.validation.constraints.NotNull
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, PARAMETER, TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {

        CustomValidateDate[] value();
    }
}
