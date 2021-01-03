package com.wxs.mybatis.interceptor;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class NumberValidationUtils {


    private static final String REGEX_PHONE = "^[1][3,4,5,6,7,8,9][0-9]{9}$"; // ^1[3|4|5|7|8][0-9]\\d{4,8}$
    private static final String REGEX_POSITIVE_INTEGER = "^[1-9]+[0-9]*$";
    private static final String REGEX_IS_NUMERIC = "-?\\d+(\\.\\d+)?";

    public static boolean validatePhone(String phone) {
        return validateFormat(phone, REGEX_PHONE);
    }

    public static boolean validateJobNum(String code) {
        return validateFormat(code, REGEX_POSITIVE_INTEGER);
    }

    public static boolean validateFormat(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean isNumeric(String strNum) {
        Pattern pattern = Pattern.compile(REGEX_IS_NUMERIC);

        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

}
