package com.wxs.tomcat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/10/9 10:20
 */
@RestController
public class StringDeduplicationExampleController {
    private static List<String> myStrings = new ArrayList<>();

    @GetMapping("/stringdeduplication")
    public String test() {
        for (int counter = 0; counter < 200; ++counter) {
            for (int secondCounter = 0; secondCounter < 1000; ++secondCounter) {
                // Add it 1000 times.
                myStrings.add(("Hello World-" + counter));
            }

            System.out.println("Hello World-" + counter + " has been added 1000 times");
        }
        return null;
    }

}
