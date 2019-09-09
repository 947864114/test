package com.mq.util;

import java.io.Serializable;

/**
 * Description: BlAppJson
 * Created by LQZ
 * DATE: 2019/7/5 17:50
 */
public class AppJson implements Serializable {
    public static final int CODESTATUSSUCCESS = 0;
    public static final int CODESTATUSFAIL = 1;
    private int code;
    private String message;
    private Object obj;
    private String auth_token;

    public AppJson() {
    }

    public AppJson(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }

    public AppJson(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
