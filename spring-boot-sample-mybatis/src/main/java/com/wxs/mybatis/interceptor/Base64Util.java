package com.wxs.mybatis.interceptor;

public class Base64Util {

    /**
     * @param str
     * @return
     */
    public static String decryptBase64(String str) {
        // byte[] b = null;
        // byte[] s = null;
        // try {
        //     b = str.getBytes("utf-8");
        // } catch (UnsupportedEncodingException e) {
        //     System.out.println("Base64Util getBase64 Exception: " + e);
        // }
        // if (b != null) {
        //     Base64.Decoder decoder = Base64.getDecoder();
        //     s = decoder.decode(b);
        // }

        // return new String(s);
        return str.split("111")[0];
    }

    /**
     * 加密
     *
     * @param str
     * @return
     */
    public static String encryptBase64(String str) {
        // byte[] b = null;
        // String s = null;
        // try {
        //     b = str.getBytes("utf-8");
        // } catch (UnsupportedEncodingException e) {
        //     System.out.println("Base64Util getBase64 Exception: "+e);
        // }
        // if (b != null) {
        //     s = Base64.getEncoder().encodeToString(b);
        // }
        // return s;
        return str + "111";
    }
}
