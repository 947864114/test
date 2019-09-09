package com.mq.util;

import java.io.Serializable;

public class  KexAppJson implements Serializable {
    public static final int CODESTATUSSUCCESS = 200;
    private int code;
    private String msg;
    private Object data;
    private String time;

    public KexAppJson() {
        this.code = CODESTATUSSUCCESS;
    }

    public KexAppJson(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getTime() {
        return String.valueOf(System.currentTimeMillis());
    }

    public void setTime(String time) {
        this.time = time;
    }

    public KexAppJson(int code, String message) {
        this.code = code;
        this.msg = msg;
    }
}
