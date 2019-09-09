package com.mq.util;

import com.mq.util.exception.ExceptionStatus;
import com.mq.util.exception.BlException;
import com.mq.util.page.Page;
import com.mq.vo.publish.PublicEnum;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;

import java.net.URLDecoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParametersUtils {
    public static final String pledgeType = "抵押类型";
    public static final String pledgeTypeAll = "全抵押";
    public static final String pledgeTypeNull = "无抵押";

    public static void copyPageBaseInfo(Page pageSource, Page pageTarget) {
        if (pageSource != null && pageTarget != null) {
            pageTarget.setNowPage(pageSource.getNowPage());
            pageTarget.setTotalCount(pageSource.getTotalCount());
            pageTarget.setStart(pageSource.getStart());
            pageTarget.setPageShow(pageSource.getPageShow());
            pageTarget.setTotalPage(pageSource.getTotalPage());
        }

    }



    public static Integer coinType(String coin){
        Integer a = null;

        if(coin.contains("BTC")){
            a =  CommonEnum.BTC.getKey();
        }else if(coin.contains("USDT")){
            a =  CommonEnum.USDT.getKey();

        }else if(coin.contains("ETH")){
            a =  CommonEnum.ETH.getKey();

        }else if(coin.contains("BHD")){
            a =  CommonEnum.BHD.getKey();
        }
        return a;
    }

    /**
     * 密码加密
     *
     * @param password
     * @return
     */
    public static String genPassword(String password) {

        return new BCryptPasswordEncoder().encode(password);
    }

    /**
     * 登录密码匹配
     *
     * @param oldPassword
     * @param newPassword
     */
    public static void checkPwd(String oldPassword, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(oldPassword, newPassword)) {
            throw new BlException(ExceptionStatus.SERVER_ERROR);
        }

    }

    /**
     * 判断字符串中是否包含中文
     * @param str
     * 待校验字符串
     * @return 是否为中文
     * @warn 不能校验是否为中文标点符号
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    public static AppJson getByKexAppJson(KexAppJson json){
        AppJson appJson = new AppJson();
        if(json.getCode() != KexAppJson.CODESTATUSSUCCESS) {
            appJson.setCode(json.getCode());
        }else {
            appJson.setCode(0);
        }
        appJson.setMessage(json.getMsg());
        appJson.setObj(json.getData());

        return appJson;
    }

    /**
     * 校验有用户的签名
     * @param o
     * @param identityid
     */
    public static void checkSignUser(Object o, String identityid){
        System.out.println("===============ParametersUtils checkSignUser==================");
        try {
            //请求obj
            JSONObject objects = JSONObject.fromObject(o);

            //获取签名
            String paramSign = URLDecoder.decode(objects.get("sign").toString(), "UTF-8");
            System.out.println("paramSign:" +paramSign);
            //获取请求数据字符串
            String data = objects.get("data").toString();
            System.out.println("data:" + URLDecoder.decode(data, "UTF-8"));

            //获取请求时间
            String timeString = objects.get("time").toString();
            System.out.println("time:" + timeString);
            //校验时间是否过期，接口时间超过5分钟过期
            Long time = Long.parseLong(timeString);
            Long now = System.currentTimeMillis();
            System.out.println("now:" + now);
            if(now - time > 5 * 60 * 1000){
                throw new BlException(ExceptionStatus.SIGN_TIMEOUT);
            }

            //重新加密 时间戳+请求数据+用户identityid+source
            String initSign = timeString + data + identityid + PublicEnum.KEX.getPlatFlag();
            System.out.println(initSign);
            String reSign= MD5.transMD5(initSign);
            System.out.println("reSign:" + reSign);
            //校验签名是否正确
            if(!paramSign.equals(reSign)){
                throw new BlException(ExceptionStatus.SIGN_ERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 校验无用户的签名
     * @param o
     */
    public static void checkSign(Object o){
        try {
            System.out.println("===============ParametersUtils checkSign==================");
            //请求obj
            JSONObject objects = JSONObject.fromObject(o);
            //获取签名
            String paramSign = URLDecoder.decode(objects.get("sign").toString(), "UTF-8");
            System.out.println("paramSign:" + paramSign);
            //获取请求数据字符串
            String data = objects.get("data").toString();
            System.out.println("data:" + URLDecoder.decode(data, "UTF-8"));
            //获取请求时间
            String timeString = objects.get("time").toString();
            System.out.println("timeString:" + timeString);
            //校验时间是否过期，接口时间超过5分钟过期
            Long time = Long.parseLong(timeString);
            Long now = System.currentTimeMillis();
            System.out.println("now:" + now);
            if (now - time > 5 * 60 * 1000) {
                throw new BlException(ExceptionStatus.SIGN_TIMEOUT);
            }

            //重新加密 时间戳+请求数据+apikey+source
            String initSign = timeString + data + PublicEnum.KEX.getPlatApiKey() + PublicEnum.KEX.getPlatFlag();
            System.out.println(initSign);
            String reSign = MD5.transMD5(initSign);
            System.out.println("reSign:" + reSign);
            //校验签名是否正确
            if (!paramSign.equals(reSign)) {
                throw new BlException(ExceptionStatus.SIGN_ERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static List<Integer> rebuildIds(String ids){
        if(StringUtils.isEmpty(ids)){
            return null;
        }
        String[] idsArr = ids.split(",");
        List<Integer> newArr = new ArrayList<>();
        for(String id : idsArr){
            if(!StringUtils.isEmpty(id)){
                newArr.add(Integer.parseInt(id));
            }
        }
        return newArr;
    }

    /**
     * 检查请求输入参数是否为空
     *
     * @param objects
     */
    public static void checkParameters(Object... objects) {
        for (Object object : objects) {
            if (object == null) {
                throw new BlException(ExceptionStatus.SERVER_ERROR);
            } else {
                if (object instanceof String) {
                    if (StringUtils.isEmpty(object) || StringUtils.isEmpty(((String) object).trim())) {
                        throw new BlException(ExceptionStatus.SERVER_ERROR);
                    }
                } else if (object instanceof List) {
                    if (CollectionUtils.isEmpty((List) object)) {
                        throw new BlException(ExceptionStatus.SERVER_ERROR);
                    }
                }
            }
        }
    }


    /**
     * 判断传参是否为标准格式（逗号拼接）
     *
     * @param parameter
     * @return
     */
    public static String formatParameter(String parameter) {
        if (StringUtils.isEmpty(parameter)) {
            return null;
        }

        String[] imgArray = parameter.split(",");
        String result = "";
        for (String s : imgArray) {
            if (!StringUtils.isEmpty(s))
                result += s + ",";
        }
        if (!StringUtils.isEmpty(result)) {
            result = result.substring(0, result.length() - 1);
        }

        return result;
    }


    public static void main(String[] args) {
       /* BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String oldPassword = "$2a$10$bmTISSD9sbuw.pcWGI2viOw4FfjXsUslTP48VtoTJbn4oag/3b8j.";
        String beforeEncode = "a642a77abd7d4f51bf9226ceaf891fcbb5b299b8";
        String a = genPassword(beforeEncode);
        System.out.println(a);
        System.out.println(passwordEncoder.matches(beforeEncode, oldPassword));
        checkPwd(beforeEncode,oldPassword);*/
//        System.err.println(IdGenerator.getID14().toUpperCase());

        String str = "主站划转";
        boolean result = ParametersUtils.isContainChinese(str);
        System.out.println(result);

    }




}

