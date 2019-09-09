package com.mq.vo.publish;

/**
 * Description: PublishJson
 * Created by LQZ
 * DATE: 2019/7/17 16:33
 */
public class PublicJson {
    public static final int CODESTATUSSUCCESS = 200;
    public static final int CODESTATUFAILED = 300;
    private int code;
    private String message;
    private Object data;

    public PublicJson() {
        this.code = CODESTATUSSUCCESS;
    }
    public PublicJson(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public PublicJson(Object data) {
        this.code = CODESTATUSSUCCESS;
        this.message = "成功";
        this.data = data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
