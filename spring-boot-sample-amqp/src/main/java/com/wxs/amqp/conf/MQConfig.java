package com.wxs.amqp.conf;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: FujiRen
 * Date: 2018/4/6
 * Time: 22:19
 */
@Component
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class MQConfig {
    private String username;
    private String password;
    private String virtualHost1;
    private String virtualHost2;
    private String host;
    private int port;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVirtualHost1() {
        return virtualHost1;
    }

    public void setVirtualHost1(String virtualHost1) {
        this.virtualHost1 = virtualHost1;
    }

    public String getVirtualHost2() {
        return virtualHost2;
    }

    public void setVirtualHost2(String virtualHost2) {
        this.virtualHost2 = virtualHost2;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
