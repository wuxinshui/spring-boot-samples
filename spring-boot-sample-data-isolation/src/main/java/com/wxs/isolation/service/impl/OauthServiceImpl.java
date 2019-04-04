package com.wxs.isolation.service.impl;

import com.wxs.isolation.context.LocalMap;
import com.wxs.isolation.service.OauthService;
import org.springframework.stereotype.Service;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/4/3 20:35
 */
@Service
public class OauthServiceImpl implements OauthService {

    @Override
    public String getUsername() {
        return local.get().get("username").toString();
    }

    @Override
    public int getUserAge() {
        return (int) local.get().get("userage");
    }

    @Override
    public void put(LocalMap localMap) {
        local.set(localMap);
    }

    //public static void main(String[] args) {
    //    LocalMap localMap=new LocalMap();
    //    localMap.put("username","玖拾");
    //    localMap.put("userage",123);
    //    OauthService service=new OauthServiceImpl();
    //    service.put(localMap);
    //    System.out.println(service.getUserAge());
    //    System.out.println(service.getUsername());
    //    System.out.println(OauthService.local.get().get("username"));
    //    System.out.println(OauthService.local.get().get("userage"));
    //    System.out.println(OauthService.local.get().get("userage1"));
    //
    //}
}
