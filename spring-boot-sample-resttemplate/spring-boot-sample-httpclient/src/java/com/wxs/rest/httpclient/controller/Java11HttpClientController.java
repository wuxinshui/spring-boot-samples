package com.wxs.rest.httpclient.controller;

import com.wxs.rest.httpclient.config.RestHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

/**
 * 测试授权登陆流程
 */
@Slf4j
@RestController
@RequestMapping("/java11")
public class Java11HttpClientController {
    public static final String url = "http://127.0.0.1:8080/server";

    @Autowired
    private RestHttpClient restHttpClient;


    @GetMapping("/")
    public Object index() throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://javastack.cn"))
                .GET()
                .build();
        var client = HttpClient.newHttpClient();
        // 同步
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
        // 异步
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())

                .thenApply(HttpResponse::body)

                .thenAccept(System.out::println);
        return response;
    }

    @GetMapping("/1")
    public String testName2() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://javastack.cn")).build();
        HttpResponse.BodyHandler<String> responseBodyHandler = HttpResponse.BodyHandlers.ofString();
        CompletableFuture<HttpResponse<String>> sendAsync = client.sendAsync(request, responseBodyHandler);
        sendAsync.thenApply(t -> t.body()).thenAccept(System.out::println);
        HttpResponse<String> response = sendAsync.get();
        String body = response.body();
        System.out.println(body);
        return body;

    }

    @GetMapping("/2")
    public String testName() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://javastack.cn")).build();
        HttpResponse.BodyHandler<String> responseBodyHandler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = client.send(request, responseBodyHandler);
        String body = response.body();
        System.out.println(body);

        return body;
    }


}
