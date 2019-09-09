package com.mq.vo.kex;

import com.mq.util.MD5;
import com.mq.util.client.RestClientUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Description: RequestCommonVO
 * Created by LQZ
 * DATE: 2019/7/5 16:14
 */
public class RequestCommonVO {

    //时间戳
    private long time;
    //签名
    private String sign;
    //请求参数
    private Object data;

    public static <T> T transData(RequestCommonVO vo, Class<T> t){
        return (T) JSONObject.toBean(JSONObject.fromObject(vo.getData()), t);
    }

    /**
     * 有用户的签名
     * @param identityid
     * @param o
     * @return
     */
    public static String buildUserSign(long time, String identityid, Map<String, Object> o){
        o.remove("identityid");
        String json = net.sf.json.JSONObject.fromObject(o).toString()
                .replaceAll(" ", "")
                .replaceAll("0E-15", "0");
        String encodeJson;
        String sign = null;
        System.out.println(json);
        try {
            encodeJson = URLEncoder.encode(json, "UTF-8");
            String beforeSign =  String.valueOf(time)
                    + encodeJson
                    + identityid
                    + RestClientUtil.source;
            System.out.println(beforeSign);
            sign = URLEncoder.encode(MD5.transMD5(beforeSign), "UTF-8");
            System.out.println(sign);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return sign;
    }

    /**
     * 无用户的签名
     * @param o
     * @return
     */
    public static String buildSign(long time, Map<String, Object> o){
        String json = net.sf.json.JSONObject.fromObject(o).toString()
                .replaceAll(" ", "")
                .replaceAll("0E-15", "0");
        String encodeJson;
        String sign = null;
        System.out.println(json);
        try {
            encodeJson = URLEncoder.encode(json, "UTF-8");
            String beforeSign =  String.valueOf(time)
                    + encodeJson
                    + RestClientUtil.apiKey
                    + RestClientUtil.source;
            System.out.println(beforeSign);
            sign = URLEncoder.encode(MD5.transMD5(beforeSign), "UTF-8");
            System.out.println(sign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sign;
    }

    /**
     * 无用户的签名
     * @param o
     * @return
     */
    public static String buildArraySign(long time, JSONArray o){
        String encodeJson;
        String sign = null;
        try {
            encodeJson = String.valueOf(o).replaceAll(" ", "");
            String beforeSign =  String.valueOf(time) + encodeJson + RestClientUtil.apiKey + RestClientUtil.source;
            System.out.println(beforeSign);
            sign = URLEncoder.encode(MD5.transMD5(beforeSign), "UTF-8");
            System.out.println(sign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sign;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
