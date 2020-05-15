package com.wxs.rest.httpclient;

import com.wxs.rest.httpclient.config.RestHttpClient;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HttpClientApplication {

    @Value("${server.rest.http-client.url}")
    public String url;

    public static void main(String[] args) {
        SpringApplication.run(HttpClientApplication.class, args);
    }

    @Bean
    public HttpClient httpClient() {

        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(3000).setConnectTimeout(5000).setSocketTimeout(100000).setMaxRedirects(3).build();
        return HttpClientBuilder.create().setDefaultRequestConfig(requestConfig)
                .build();
    }

    @Bean
    public RestHttpClient restHttpClient() {
        return new RestHttpClient(url, httpClient());
    }

}