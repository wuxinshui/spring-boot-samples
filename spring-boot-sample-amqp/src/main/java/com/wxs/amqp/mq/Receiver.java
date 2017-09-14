package com.wxs.amqp.mq;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * Created with IntelliJ IDEA.
 * User: FujiRen
 * Date: 2017/9/14
 * Time: 15:06
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Receiver {
    CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
