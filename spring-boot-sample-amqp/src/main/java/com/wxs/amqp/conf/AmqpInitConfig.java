package com.wxs.amqp.conf;

import com.wxs.amqp.mq.Receiver;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: FujiRen
 * Date: 2017/9/14
 * Time: 10:54
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.rabbitmq",name = "")
public class AmqpInitConfig {

    final static String queueName="spring-boot";

    @Bean
    public Queue queue(){
        return new Queue(queueName,false);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("spring-boot-exchange");
    }

    @Bean
    public Binding binding(TopicExchange exchange,Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter){
        SimpleMessageListenerContainer container=new SimpleMessageListenerContainer();
        container.addQueueNames(queueName);
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(Receiver receiver){
        return new MessageListenerAdapter(receiver,"receiver");
    }
}
