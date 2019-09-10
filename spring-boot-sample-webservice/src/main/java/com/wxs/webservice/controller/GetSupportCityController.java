package com.wxs.webservice.controller;

import com.wxs.webservice.schemas.GetSupportCitySoapIn;
import com.wxs.webservice.soapclient.SoapClient;
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


    @GetMapping("/weather/{city}")
    public Object getSupportCity(@PathVariable("city") String city) {
        GetSupportCitySoapIn soapIn = new GetSupportCitySoapIn(city);
        Object object = soapClient.call(wsdl_url, soapIn);
        return object;
    }

}
