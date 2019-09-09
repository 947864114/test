package com.mq.util;


import com.mq.vo.kex.RequestCommonVO;
import org.aspectj.weaver.BCException;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: MD5
 * Created by LQZ
 * DATE: 2019/7/5 11:35
 */
public class MD5 {

    /**
     * md5码生成（先使用MD5加密，再使用Base64加密）
     *
     * @param content 需要加密的字符串
     * @return
     * @throws Exception
     */
    public static String transMD5(String content) throws BCException {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            throw new BCException();
        }
        sun.misc.BASE64Encoder baseEncoder = new sun.misc.BASE64Encoder();
        String retString = baseEncoder.encode(md5.digest(content.getBytes()));
        return retString;
    }
    //登录
    public static void login(){

        Map<String, Object> map = new HashMap<>();
        map.put("uid", "10000122");
        map.put("password", "111");
        String sign = RequestCommonVO.buildSign(System.currentTimeMillis(), map);
        System.out.println(sign);
    }

    /**
     * 获取用户钱包信息
     */
    public static void userWallets(){
        Map<String, Object> map = new HashMap<>();
        map.put("uid", "10000122");
        String sign = RequestCommonVO.buildUserSign(System.currentTimeMillis(), "8dyzKTL7+Pkc0GG665ww1Q==", map);
        System.out.println(sign);
    }

    public static void splitImgs(){
        String t1 = "http://ljkl/123.jpg,http://sss/xxx.png";
        String t2 = "http://ljkl/123.jpghttp://sss/xxx.png";
        String t3 = "123.jpg,xxx.png";

        String[] xxs = t3.replaceAll("http://", ",http://").split(",");
        for(String img : xxs){
            if(!StringUtils.isEmpty(img)) {
                String[] imgName = img.split("/");
                System.out.println(imgName[imgName.length - 1]);
            }
        }
    }



    public static void main(String[] args) throws UnsupportedEncodingException {
//        login();
        //userWallets();
//        String before = transMD5("1564039340480%7B%22fid%22%3A126%2C%22uid%22%3A10000355%2C%22number%22%3A10%2C%22amount%22%3A100%2C%22orderid%22%3A0%2C%22elecChgDayNum%22%3A0%2C%22price%22%3A10%2C%22elecChgId%22%3A%225d37c8a168ac2e71bc8f9bc9%22%2C%22elecChgAmount%22%3A0E-15%7D5CB8952A67AEBEA6YS");
//        System.out.println(before);
//        System.out.println(URLEncoder.encode(before, "UTF-8"));
//        System.out.println(transMD5("KAIDA_FOR_KEX"));
//        System.out.println(transMD5("KAIDA_FOR_KG"));
//        Map<String, Object> map = new HashMap<>();
//        map.put("test1", 1);
//        map.put("test2", "2");
//        map.put("test3", 0.0001);
//        JSONObject obj = JSONObject.fromObject(map);
//        JSONObject obj1 = JSONObject.fromObject(new PublishMachineBean());
//        System.out.println(obj.toString());
//        System.out.println(obj1.toString());

//        BigDecimal t1 = new BigDecimal(1);
//        BigDecimal t2 = new BigDecimal(1);
//        System.out.println(t1.compareTo(t2));

        String before = transMD5("12345678");
        System.out.println(before);
//        splitImgs();

        Date d = new Date();


    }


}
