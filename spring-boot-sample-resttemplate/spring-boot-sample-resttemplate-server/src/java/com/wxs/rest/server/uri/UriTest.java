package com.wxs.rest.server.uri;

import java.net.URI;

public class UriTest {
    public static void main(String[] args) {
        String url = "http://root@tool.oschina.net:90/apidocs/apidoc?api=jdk-zh#dddd";
        URI uri= URI.create(url);
        String scheme=uri.getScheme();
        String fragment=uri.getFragment();
        String authority=uri.getAuthority();
        String userInfo=uri.getUserInfo();
        String host=uri.getHost();
        int port=uri.getPort();
        String path=uri.getPath();
        String query=uri.getQuery();
        String schemeSpecificPart=uri.getSchemeSpecificPart();
        System.out.println("---------url---------");
        System.out.println(url);

        System.out.println("---------About URI---------");
        System.out.println("scheme:"+scheme);
        System.out.println("fragment:"+fragment);
        System.out.println("authority:"+authority);
        System.out.println("userInfo:"+userInfo);
        System.out.println("host:"+host);
        System.out.println("port:"+port);
        System.out.println("path:"+path);
        System.out.println("query:"+query);
        System.out.println("schemeSpecificPart:"+schemeSpecificPart);

        System.out.println("---------About URI---------");


    }
}
