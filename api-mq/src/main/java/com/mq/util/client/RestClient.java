package com.mq.util.client;

import com.mq.util.AppJson;
import com.mq.util.KexAppJson;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

/**
 * Description: RestClient
 * Created by LQZ
 * DATE: 2019/7/5 17:50
 */
@Component
public class RestClient {
    private static final List<String> urlList = Arrays.asList("/v2/user/search",
            "/v2/userwallet", "/v2/coinAddress", "/v2/transferRecord","/v2/ticket/get","/v2/ticket/seach");

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 发送请求GET
     *
     * @param rcu
     * @return
     */
    public AppJson sendRequestGet(RestClientUtil rcu) {
        String url = rcu.builGetUrl();
        HttpEntity<JSONObject> entity = rcu.getJSONEntity();
        ResponseEntity<AppJson> appJson = restTemplate.exchange(url,
                HttpMethod.GET, entity, AppJson.class);
        AppJson appjson = appJson.getBody();
        return appjson;
    }

    /**
     * 发送请求POST 参数加入request body
     *
     * @param rcu
     * @return
     */
    public AppJson sendRequestPost(RestClientUtil rcu) {
        String url = rcu.getRequestAddress().toString();
        HttpEntity<JSONObject> entity = rcu.postEntity();
        ResponseEntity<AppJson> appJson = restTemplate.exchange(url,
                HttpMethod.POST, entity, AppJson.class);
        AppJson appjson = appJson.getBody();
        return appjson;
    }


    /**
     * kex发送请求GET
     * @param rcu
     * @return
     */
    public KexAppJson kexSendRequestGet(RestClientUtil rcu) {
        String url = rcu.getUrl();
        //如果是特定的接口，需要签名才能返回
        //urlList 里面设置特定的接口
        for(String u : urlList){
            if(url.indexOf(u) >= 0){
                url = rcu.builGetUrl();
                HttpEntity<JSONObject> entity = rcu.postEntity();
                rcu.getParms().remove("identityid");
                String json = net.sf.json.JSONObject.fromObject(rcu.getParms()).toString().replaceAll(" ", "");
                String encodeJson = json;
                System.out.println(encodeJson);
                try {
                    encodeJson = URLEncoder.encode(json, "UTF-8");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(encodeJson);
                ResponseEntity<KexAppJson> appJson = restTemplate.exchange(
                        url, HttpMethod.GET, entity, KexAppJson.class, encodeJson );
                KexAppJson appjson = appJson.getBody();
                return appjson;
            }
        }
        url = rcu.buildUrl();
        HttpEntity<JSONObject> entity = rcu.getJSONEntity();
        ResponseEntity<KexAppJson> appJson = restTemplate.exchange(url, HttpMethod.GET, entity, KexAppJson.class );
        KexAppJson appjson = appJson.getBody();
        return appjson;
    }


    /**
     * 发送请求POST 参数加入request body
     *
     * @param rcu
     * @return
     */
    public KexAppJson kexSendRequestPost(RestClientUtil rcu) {
        String url = rcu.builGetUrl();
        HttpEntity<JSONObject> entity = rcu.postEntity();
        rcu.getParms().remove("identityid");
        String json = net.sf.json.JSONObject.fromObject(rcu.getParms()).toString();
        String encodeJson = json;
        try {
            encodeJson = URLEncoder.encode(json, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(encodeJson);
        ResponseEntity<KexAppJson> appJson = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                KexAppJson.class,
                encodeJson
        );
        KexAppJson appjson = appJson.getBody();
        return appjson;
    }


    /**
     * 发送请求POST 参数加入request body
     *
     * @param rcu
     * @return
     */
    public KexAppJson kexSendRequestPostList(RestClientUtil rcu) {
        String url = rcu.buildUrlWithListParams();
        HttpEntity<JSONObject> entity = rcu.postEntity();
        String json = rcu.getListParams().toString().replaceAll(" ", "");
        System.out.println(json);
        ResponseEntity<KexAppJson> appJson = restTemplate.exchange(
                url, HttpMethod.POST, entity, KexAppJson.class, json );
        KexAppJson appjson = appJson.getBody();
        return appjson;
    }


    public JSONObject findHashrate(RestClientUtil rcu) {
        String url = rcu.buildUrl();
        HttpEntity<JSONObject> entity = rcu.getJSONEntity();
        ResponseEntity<JSONObject> appJson = restTemplate.exchange(url,HttpMethod.GET,entity,JSONObject.class);
        JSONObject ob=appJson.getBody();
        return ob;
    }


}
