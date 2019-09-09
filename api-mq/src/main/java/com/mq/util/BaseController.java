package com.mq.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public BaseController() {
    }

    protected String getToken() {
        return this.request.getHeader("baby_token");
    }

    protected String getApiKey() {
        return this.request.getHeader("apiKey");
    }


    protected KexAppJson getTokenAppJson() {
        KexAppJson appjson = new KexAppJson();
        return appjson;
    }



}
