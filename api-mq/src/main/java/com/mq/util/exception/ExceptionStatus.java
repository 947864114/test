package com.mq.util.exception;

import com.mq.config.cors.Description;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExceptionStatus {

    @Description(value = "调用成功", key = 200)
    public static int SUCCESS = 200;

    @Description(value = "签名错误", key = 201)
    public static int SIGN_ERROR = 201;

    @Description(value = "参数错误", key = 202)
    public static int PARAMETER_ERROR = 202;

    @Description(value = "用户禁用", key = 203)
    public static int USER_LOCKED = 203;

    @Description(value = "签名超时", key = 204)
    public static int SIGN_TIMEOUT = 204;

    @Description(value = "未设置交易密码", key = 205)
    public static int DEAL_PASSWORD_UNSET = 205;

    @Description(value = "交易密码错误", key = 206)
    public static int DEAL_PASSWORD_ERROR = 206;

    @Description(value = "币种不存在", key = 207)
    public static int COIN_NOT_EXIST = 207;

    @Description(value = "登录过期，请重新登录", key = 208)
    public static int LOGIN_TIMEOUT = 208;

    @Description(value = "账户余额不足", key = 215)
    public static int USER_BALANCE_NOT_ENOUGHT = 215;

    @Description(value = "网络超时，请稍候重试！", key = 216)
    public static int NET_TIMEOUT = 216;



    @Description(value = "系统错误", key = 300)
    public static int SERVER_ERROR = 300;

    @Description(value = "余额不足", key = 400)
    public static int BALANCE_NOT_ENOUGTH = 400;

    @Description(value = "当前币种不允许充值", key = 401)
    public static int COIN_NOT_RECHARGE = 401;

    @Description(value = "地址数量不足", key = 402)
    public static int ADDRESS_NOT_ENOUGTH = 402;

    @Description(value = "Tokent已存在", key = 500)
    public static int TOKEN_EXIST = 500;

    @Description(value = "地址数量不足", key = 600)
    public static int AUTHOR_ERROR = 600;




    protected static Map<Integer, String> descriptionMap = new LinkedHashMap();

    public ExceptionStatus() {
    }

    static {
        Field[] fields = ExceptionStatus.class.getDeclaredFields();
        Field[] var1 = fields;
        int var2 = fields.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Field field = var1[var3];
            if (field.isAnnotationPresent(Description.class)) {
                Description description = (Description)field.getAnnotation(Description.class);
                descriptionMap.put(description.key(), description.value());
            }
        }

    }

    public static String getDesc(int status) {
        return (String)descriptionMap.get(status);
    }
}
