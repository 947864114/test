package com.mq.util.client;

import com.mq.vo.kex.RequestCommonVO;
import com.alibaba.fastjson.JSONObject;
import com.mq.util.exception.BlException;
import net.sf.json.JSONArray;
import org.springframework.http.*;

import java.util.*;

public class RestClientUtil {
    public static String INTERNAL_FLAG_KEY = "internalFlag";
    public static String apiKey = "5CB8952A67AEBEA6";
    public static String source = "YS";
    public static int INTERNAL_FLAG_VALUE = 1;
    private StringBuffer requestAddress;
    private Map<String, Object> parms = new HashMap();
    private List<Object> paramList = new ArrayList<>();

    public RestClientUtil(StringBuffer requestAddress) {
        this.requestAddress = requestAddress;
    }

    private void appendParm(StringBuffer path, String key, Object value) {
        if (!path.toString().contains("?")) {
            path.append("?");
        } else if (!path.toString().endsWith("?")) {
            path.append("&");
        }

        path.append(key + "=" + value);
    }

    public void addParm(String key, Object value) {
        this.parms.put(key, value);
    }

    public void addListParams(Object value) {
        this.paramList.add(value);
    }

    public Map<String, Object> getParms() {
        return this.parms;
    }
    public List<Object> getListParams() {
        return this.paramList;
    }

    /**
     * 通用请求
     * @return
     */
    public String buildUrl() {
        if (this.requestAddress.length() == 0) {
            throw new BlException("请求地址错误");
        } else {
            Iterator var1 = this.parms.entrySet().iterator();

            while(var1.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry)var1.next();
                this.appendParm(this.requestAddress, (String)entry.getKey(), entry.getValue());
            }

            return this.requestAddress.toString();
        }
    }

    /**
     * 通用请求
     * @return
     */
    public String buildUrlWithListParams() {
        if (this.requestAddress.length() == 0) {
            throw new BlException("请求地址错误");
        } else {
            if (!this.requestAddress.toString().contains("?")) {
                this.requestAddress.append("?");
            } else if (!this.requestAddress.toString().endsWith("?")) {
                this.requestAddress.append("&");
            }

            //请求时间
            long time = System.currentTimeMillis();

            //拼接地址
            this.requestAddress.append("time=" + time);
            this.requestAddress.append("&source=" + source);
            this.requestAddress.append("&sign=" + RequestCommonVO.buildArraySign(time, JSONArray.fromObject(this.paramList)));
            this.requestAddress.append("&data={json}");

            return this.requestAddress.toString();
        }
    }

    public String getUrl(){
        return this.requestAddress.toString();
    }

    /**
     * 没有用户，用apikey签名
     * @return
     */
    public String builGetUrl() {
        if (this.requestAddress.length() == 0) {
            throw new BlException("请求地址错误");
        } else {
            //设置地址结束符号
            if (!this.requestAddress.toString().contains("?")) {
                this.requestAddress.append("?");
            } else if (!this.requestAddress.toString().endsWith("?")) {
                this.requestAddress.append("&");
            }

            //请求时间
            long time = System.currentTimeMillis();

            //拼接地址
            this.requestAddress.append("time=" + time);
            this.requestAddress.append("&source=" + source);

            //判断是否有用户
            if( this.parms.get("identityid") == null) {

                this.requestAddress.append("&sign=" + RequestCommonVO.buildSign(time, this.parms));
            }else{
                this.requestAddress.append("&sign=" + RequestCommonVO.buildUserSign(time, this.parms.get("identityid").toString(), this.parms));
                this.parms.remove("identityid");
            }

            this.requestAddress.append("&data={json}");

            return this.requestAddress.toString();
        }
    }

    public void setRequestAddress(String address) {
        this.requestAddress = new StringBuffer(address);
    }

    public StringBuffer getRequestAddress() {
        return this.requestAddress;
    }

    public HttpEntity<JSONObject> postEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(String.valueOf(INTERNAL_FLAG_KEY), String.valueOf(INTERNAL_FLAG_VALUE));
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        JSONObject jsonObj = new JSONObject();
        Iterator var3 = this.getParms().entrySet().iterator();

        while(var3.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry)var3.next();
            jsonObj.put((String)entry.getKey(), entry.getValue());
        }

        HttpEntity<JSONObject> entity = new HttpEntity(jsonObj, headers);
        return entity;
    }



    public HttpEntity<JSONObject> getJSONEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(String.valueOf(INTERNAL_FLAG_KEY), String.valueOf(INTERNAL_FLAG_VALUE));
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<JSONObject> entity = new HttpEntity((Object)null, headers);
        return entity;
    }

}
