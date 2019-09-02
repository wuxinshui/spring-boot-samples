package com.wxs.tomcat.controller;

import com.wxs.tomcat.vo.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/9/2 10:34
 */
@RestController
public class GcTestController {
    private Queue<Greeting> objCache = new ConcurrentLinkedDeque<>();

    @GetMapping("/greeting")
    public Greeting greeting() {
        Greeting greeting = new Greeting("Hello World!");

        if (objCache.size() >= 200000) {
            objCache.clear();
        } else {
            objCache.add(greeting);
        }
        return greeting;
        //return greeting.getMessage();
    }
}
