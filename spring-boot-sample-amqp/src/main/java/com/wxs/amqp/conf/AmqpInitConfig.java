package com.wxs.amqp.conf;

import com.wxs.amqp.mq.Receiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.SimpleRoutingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: FujiRen
 * Date: 2017/9/14
 * Time: 10:54
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.rabbitmq",name = "enable", matchIfMissing = false)
public class AmqpInitConfig {

    final static String queueName="spring.boot";

    @Autowired
    private MQConfig mqConfig;

    @Bean
    public Queue queue(){
        return new Queue(queueName,false);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("spring.boot.exchange");
    }

    @Bean
    public Binding binding(TopicExchange exchange,Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with(queueName+".key");
    }

    @Bean
    public ConnectionFactory connectionFactory1(){
        com.rabbitmq.client.ConnectionFactory rabbitConnectionFactory=new com.rabbitmq.client.ConnectionFactory();
        rabbitConnectionFactory.setHost(mqConfig.getHost());
        rabbitConnectionFactory.setPort(mqConfig.getPort());
        rabbitConnectionFactory.setUsername(mqConfig.getUsername());
        rabbitConnectionFactory.setPassword(mqConfig.getPassword());
        rabbitConnectionFactory.setVirtualHost(mqConfig.getVirtualHost1());

        CachingConnectionFactory factory = new CachingConnectionFactory(rabbitConnectionFactory);
        return factory;
    }

    @Bean
    public ConnectionFactory connectionFactory2(){
        com.rabbitmq.client.ConnectionFactory rabbitConnectionFactory=new com.rabbitmq.client.ConnectionFactory();
        rabbitConnectionFactory.setHost(mqConfig.getHost());
        rabbitConnectionFactory.setPort(mqConfig.getPort());
        rabbitConnectionFactory.setUsername(mqConfig.getUsername());
        rabbitConnectionFactory.setPassword(mqConfig.getPassword());
        rabbitConnectionFactory.setVirtualHost(mqConfig.getVirtualHost1());

        CachingConnectionFactory factory = new CachingConnectionFactory(rabbitConnectionFactory);
        return factory;
    }


    @Bean
    ConnectionFactory connectionFactory() {
        SimpleRoutingConnectionFactory factory = new SimpleRoutingConnectionFactory();
        Map<Object, ConnectionFactory> targetConnectionFactories = new HashMap<>();
        targetConnectionFactories.put("connectionFactory1", connectionFactory1());
        targetConnectionFactories.put("connectionFactory2", connectionFactory2());
        factory.setTargetConnectionFactories(targetConnectionFactories);
        return factory;
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory1, MessageListenerAdapter listenerAdapter){
        SimpleMessageListenerContainer container=new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory1);
        container.setMessageListener(listenerAdapter);
        container.addQueueNames(queueName);
        return container;
    }

    @Bean
    public SimpleMessageListenerContainer container2(ConnectionFactory connectionFactory2, MessageListenerAdapter listenerAdapter){
        SimpleMessageListenerContainer container=new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory2);
        container.setMessageListener(listenerAdapter);
        container.addQueueNames(queueName);
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(Receiver receiver){
        return new MessageListenerAdapter(receiver,"receiveMessage");
    }
}
