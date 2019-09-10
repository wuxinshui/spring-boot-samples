package com.wxs.webservice.soapclient;

import com.wxs.webservice.config.SOAPConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/9/10 16:14
 */
@Component
public class SoapClient<T> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SOAPConnector soapConnector;

    public Object call(String url, T t) {
        Object object = soapConnector.callWebService(url, t);
        logger.info("response:{}", object);
        return object;
    }
}
