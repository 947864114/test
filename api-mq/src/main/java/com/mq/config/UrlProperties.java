package com.mq.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description: UrlProperties
 * Created by TJ
 * DATE: 2018/12/18 10:38
 */
@ConfigurationProperties(prefix = "url")
@Component
public class UrlProperties {
    private String client;
    private String kex;
    private String bl;

    public String getBl() {
        return bl;
    }

    public void setBl(String bl) {
        this.bl = bl;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getKex() {
        return kex;
    }

    public void setKex(String kex) {
        this.kex = kex;
    }
}
