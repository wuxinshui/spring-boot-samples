package com.wxs.rocketmq.consumer;

import com.wxs.rocketmq.bo.User;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;


@RocketMQMessageListener(topic = "test",consumerGroup = "demo")
public class Consumer implements RocketMQListener<User> {
    @Override
    public void onMessage(User message) {

    }
}
