package com.mq.config.cors;

import com.mq.util.ParametersUtils;
import com.mq.util.TokenUtils;
import com.mq.util.exception.BlException;
import com.mq.util.exception.ExceptionStatus;
import com.mq.util.log.LogUtil;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Description: 切面打印调用方法的参数和地址
 * <p>
 * Created by Lqz
 * DATE: 2018/7/23 0023
 */
@Aspect
@Component
public class ControllerLogAspect {
    private static final List<String> urlList = Arrays.asList(
            "/api-bl/customer/update",
            "/api-bl/order/add",
//            "/api-bl/order/updateInfo",
            "/api-bl/order/updateStatus");

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Pointcut("execution(public * bl.publish.controller..*.*(..))")
    public void controllerLog() {
    }

    @Before("controllerLog()")
    public void doBefore(JoinPoint joinPoint) {
        Logger logger = LogUtil.getPlatformLogger();
        // 接收到请求，记录请求内容
        if(logger != null) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String url = request.getRequestURI();
            logger.info("");
            logger.info("");
            logger.info("=====================开始调用：" + url + "=======================");
            // 只记录post方法
            if ("POST".equals(request.getMethod())) {
                // 获取参数, 只取自定义的参数, 自带的HttpServletRequest, HttpServletResponse不管
                if (joinPoint.getArgs().length > 0) {

                    for (Object o : joinPoint.getArgs()) {
                        System.out.println(o);

                        if (o instanceof HttpServletRequest || o instanceof HttpServletResponse) {
                            continue;
                        }
                        logger.info("请求参数 : " + JSON.toJSONString(o));
                        //校验kex签名是否正确
                        if(checkSign(url, o)){
                            continue;
                        }
                    }
                }
            } else if ("GET".equals(request.getMethod())) {
                Map paramMap = request.getParameterMap();
                logger.info("请求参数 : " + getUrlParamsByMap(paramMap));
            }
        }
    }

    @AfterReturning(returning = "ret", pointcut = "controllerLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        Logger logger = LogUtil.getPlatformLogger();
        if(logger != null) {
            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String url = request.getRequestURI();
            // 处理完请求，返回内容

            logger.info("返回 : " + JSONObject.fromObject(ret));
            logger.info("=====================结束调用：" + url + "=======================");

        }
    }

    /**
     * 将map转换成url
     * @param map
     * @return
     */
    public static String getUrlParamsByMap(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String value = null;
            if(entry.getValue() instanceof String[]) {
                String[] values = (String[]) entry.getValue();
                value = values[0];
            }
            if(entry.getValue() instanceof String) {
                value = (String) entry.getValue();
            }
            if(value != null) {
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(value);
                sb.append("&");
            }
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = org.apache.commons.lang.StringUtils.substringBeforeLast(s, "&");
        }
        return s;
    }

    /**
     * kex 签名校验
     * @param url
     * @param o
     */
    private boolean checkSign(String url, Object o){
        System.out.println("===Aspect checkSign===");
        try {
            if(urlList.contains(url)) {
                JSONObject obj = JSONObject.fromObject(o);
                if(obj != null){
                    Object data = obj.get("data");
                    System.out.println("data：" + data);
                    if(data != null){
                        String dataStr = URLDecoder.decode(data.toString(), "UTF-8");
                        System.out.println("dataStr：" + dataStr);
                        Object uid = JSONObject.fromObject(dataStr).get("uid");
                        System.out.println("uid：" + uid);
                        if(uid != null && url.indexOf("/api-bl/order/add") < 0){
                            String objUid = String.valueOf(uid);
                            String identityid = TokenUtils.kexGetIdentityIdByUid(objUid, stringRedisTemplate);
                            if(StringUtils.isEmpty(identityid))throw new BlException(ExceptionStatus.SIGN_ERROR);
                            ParametersUtils.checkSignUser(o, identityid);
                            return true;
                        }else if(url.indexOf("/api-bl/order/add") >= 0){
                            ParametersUtils.checkSign(o);
                            return true;
                        }
                    }
                }
                throw new BlException(ExceptionStatus.SIGN_ERROR);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            throw new BlException(ExceptionStatus.SIGN_ERROR);
        }
    }
}
