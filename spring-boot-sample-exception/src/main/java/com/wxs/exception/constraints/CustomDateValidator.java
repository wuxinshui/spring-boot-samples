package com.wxs.exception.constraints;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/2/18 18:43
 */
public class CustomDateValidator implements ConstraintValidator<CustomValidateDate, Object> {
    private String start;
    private String end;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (null == value) {
            return true;
        }

        BeanWrapper beanWrapper = new BeanWrapperImpl(value);
        Object startDate = beanWrapper.getPropertyValue(start);
        Object endDate = beanWrapper.getPropertyValue(end);

        if (((Date) endDate).compareTo(((Date) startDate)) >= 0
                ) {
            return true;
        }

        return false;
    }

    @Override
    public void initialize(CustomValidateDate constraintAnnotation) {
        this.start = constraintAnnotation.start();
        this.end = constraintAnnotation.end();
    }
}
