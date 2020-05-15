package com.wxs.rest.httpclient.config;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.IOException;

public class RestHttpClient {

    private final String url;
    private final HttpClient httpClient;

    public RestHttpClient(String url, HttpClient httpClient) {
        this.url = url;
        this.httpClient = httpClient;
    }

    public HttpResponse doGet(String path) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        return httpClient.execute(httpGet);
    }


    public HttpResponse doPost(String path, String params) throws IOException {
        HttpPost doPost = new HttpPost(url);
        HttpEntity string = new StringEntity(params);
        doPost.setEntity(string);
        return httpClient.execute(doPost);
    }
}
