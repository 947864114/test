package com.mq.util;

import com.mq.util.exception.ExceptionStatus;
import com.mq.util.exception.BlException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@Configuration
public class TokenUtils {
    public static final Long LOGIN_EXPIRY = 60 * 30L;            //半小时过期
    public static final Long LOGIN_CODE = 60L;            //一分钟
    public static final Long ORDER_API_KEY = 1L;            //一秒
    public static final Long LOGIN_ACCESSTOKEN = 60 * 4 * 30L;            //2小时过期
    public static final Long LOGIN_IDENTITYIDTOKEN = 60 * 2 * 30L;            //1小时过期


    public static final String USER_RANDOM = "_token_";
    public static final String ORDER_KEY = "order_";


    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 新增token到redis,并且设置默认过期时间
     *
     * @return
     */
    public static void tokenToRedis(String userId, StringRedisTemplate redisTemplate, String privateKey) {
        String token = genToken(userId, privateKey, redisTemplate);
        redisTemplate.opsForValue().set(token, userId, LOGIN_EXPIRY, TimeUnit.SECONDS);

    }

    /**
     * 获取customerId-identityid
     *
     * @return
     */
    public static String kexGetIdentityIdByUid(String uid, StringRedisTemplate redisTemplate) {
        String identityid = redisTemplate.opsForValue().get(uid);
        return identityid;
    }

    /**
     * 从token中获取userId,并在服务器进行验证.
     * @param token
     * @return
     */
    public static Integer getUsrIdFromToken(String token, StringRedisTemplate redisTemplate){
        if(checkAccessToken(token, redisTemplate) == null){
            return null;
        }
        return Integer.parseInt(checkAccessToken(token, redisTemplate));
    }

    /**
     * 将accessToken存入redis
     * @param key
     * @param value
     * @param redisTemplate
     */
    public static void accessTokenToRedis(String key,String value, StringRedisTemplate redisTemplate) {
        redisTemplate.opsForValue().set(key, value, LOGIN_ACCESSTOKEN, TimeUnit.SECONDS);
    }

    /**
     * 判断accessToken是否存在
     * @param key
     * @param stringRedisTemplate
     * @return
     */
    public static String checkAccessToken(String key, StringRedisTemplate stringRedisTemplate){
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 新增验证码token到redis,并且设置默认过期时间
     *
     * @return
     */
    public static void codeTokenToRedis(String token,StringRedisTemplate redisTemplate) {
        redisTemplate.opsForValue().set(token, token, LOGIN_CODE, TimeUnit.SECONDS);

    }

    /**
     * 生成token
     *
     * @param privateKey
     * @return
     */
    public static String genToken(String userId, String privateKey, StringRedisTemplate stringRedisTemplate) {
        String token = RSAUtil.encryptByPrivate(userId + USER_RANDOM, privateKey);
        stringRedisTemplate.opsForValue().set(token, userId, LOGIN_EXPIRY, TimeUnit.SECONDS);
        return token;
    }

    /**
     * 根据token获取Id
     *
     * @param token
     * @param stringRedisTemplate
     */
    public static String  getToken(String token, StringRedisTemplate stringRedisTemplate) {
        return stringRedisTemplate.opsForValue().get(token);
    }

    /**
     * 设置apikey到redis，一分钟过期
     * @param apiKey
     * @param flag
     * @param stringRedisTemplate
     * @return
     */
    public static void setApiKey(String apiKey, String flag, StringRedisTemplate stringRedisTemplate) {
        stringRedisTemplate.opsForValue().set(flag, apiKey, LOGIN_CODE, TimeUnit.SECONDS);
    }

    /**
     * 获取secret对应的key
     * @param flag
     * @param stringRedisTemplate
     * @return
     */
    public static String getApiKey(String flag, StringRedisTemplate stringRedisTemplate) {
        return stringRedisTemplate.opsForValue().get(flag);
    }


    /**
     * 设置apiKey次数
     * @param flag
     * @param stringRedisTemplate
     * @return
     */
    public static void setOrderApiKey(String flag, StringRedisTemplate stringRedisTemplate) {
        String numStr = stringRedisTemplate.opsForValue().get(flag);
        if (StringUtils.isEmpty(numStr))numStr="0";
        else numStr = String.valueOf(Integer.parseInt(numStr) + 1);
        stringRedisTemplate.opsForValue().set(flag,numStr, ORDER_API_KEY, TimeUnit.SECONDS);
    }

    /**
     * 获取secret对应的key
     * @param flag
     * @param stringRedisTemplate
     * @return
     */
    public static Integer getOrderApiKey(String flag, StringRedisTemplate stringRedisTemplate) {
        String numStr = stringRedisTemplate.opsForValue().get(flag);
        if (StringUtils.isEmpty(numStr))
            return 0;
        return Integer.parseInt(numStr);
    }


    /**
     * 验证token是否存在
     *
     * @param token
     * @param stringRedisTemplate
     */
    public static void checkToken(String token, StringRedisTemplate stringRedisTemplate) {
        if(StringUtils.isEmpty(token)){
            throw new BlException(ExceptionStatus.SERVER_ERROR);
        }

        boolean result = stringRedisTemplate.hasKey(token);
        if (result == false) {
            throw new BlException(ExceptionStatus.SERVER_ERROR);
        }

        String adminId = stringRedisTemplate.opsForValue().get(token);
        stringRedisTemplate.opsForValue().set(token, adminId, LOGIN_EXPIRY, TimeUnit.SECONDS);
    }


    /**
     * 将identityid存入redis 过期时间为1个小时
     * @param uid
     * @param identityid
     * @param stringRedisTemplate
     */
    public static void identityidToredis(Integer uid, String identityid, StringRedisTemplate stringRedisTemplate) {

        stringRedisTemplate.opsForValue().set(uid.toString(), identityid, LOGIN_IDENTITYIDTOKEN, TimeUnit.SECONDS);

    }

    public static String getIdentityidByUid(Integer uid, StringRedisTemplate stringRedisTemplate) {
        return stringRedisTemplate.opsForValue().get(uid.toString());
    }
}
