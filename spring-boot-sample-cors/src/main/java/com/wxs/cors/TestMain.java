package com.wxs.cors;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: FujiRen
 * Date: 2017/10/17
 * Time: 10:04
 * To change this template use File | Settings | File Templates.
 */
public class TestMain {
    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        test4();
    }

    private static void test4() {
        Random ra=new Random();
        System.out.println(ra.nextInt());;
    }

    /**
     * test3
     */
    private static void test3() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        for (String item : list) {
            if ("2".equals(item)) {
                list.remove(item);
            }
        }

        Iterator<String> iterator=list.iterator();
        while (iterator.hasNext()){
            String str=iterator.next();
            if ("2".equals(str)){
                iterator.remove();
            }
        }
    }

    private static void test2() {
        String[] str = new String[] { "you", "wu" };
        System.out.println(str);
        List lists = new ArrayList(Arrays.asList(str));
        System.out.println(lists);
        str[0]="sd";
        System.out.println(Arrays.toString(str));
        System.out.println(lists);
    }

    private static void test1() {
        List<String> list = new ArrayList<String>(2);
        list.add("guan");
        list.add("bao");
        String[] array = new String[list.size()];
        array = list.toArray(array);

        System.out.println(array);
        String[] strings=list.toArray(array);
        System.out.println(strings);
    }
}
