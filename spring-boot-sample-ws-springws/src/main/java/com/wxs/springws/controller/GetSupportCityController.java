package com.wxs.springws.controller;

import com.wxs.springws.schemas.GetSupportCitySoapIn;
import com.wxs.springws.soapclient.SoapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * simple string
 *
 * @author DELL
 */
@RestController
public class GetSupportCityController {

    @Autowired
    private SoapClient soapClient;

    private String wsdl_url = "http://http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";
    private String getSupportCity_url = "http://WebXml.com.cn/getSupportCity";


    @GetMapping("/weather/{city}")
    public Object getSupportCity(@PathVariable("city") String city) {
        GetSupportCitySoapIn soapIn = new GetSupportCitySoapIn(city);
        Object object = soapClient.call(getSupportCity_url, soapIn);
        return object;
    }

}
