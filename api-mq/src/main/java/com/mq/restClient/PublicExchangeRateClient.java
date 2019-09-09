package com.mq.restClient;

import net.sf.json.JSONArray;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Description: PublicExchangeRateClient
 * Created by LQZ
 * DATE: 2019/7/23 9:45
 */
@Component
public class PublicExchangeRateClient {
    public static final String RMB_BTC_ZB = "http://api.zb.cn/data/v1/ticker?market=btcqc";

    /**
     * 1 rmb=x btc
     * @Param rmbNum     rmb数量
     * @return
     */
    public BigDecimal rmbToBtc(BigDecimal rmbNum){
        BigDecimal result = getOtherToUsdt(RMB_BTC_ZB);
        BigDecimal rmb = new BigDecimal(1).divide(result, 20, BigDecimal.ROUND_HALF_UP);
        return rmb.multiply(rmbNum);
    }

    /**
     * 发送请求获取汇率，1RMB=xUSDT
     * @return
     */
    private BigDecimal getOtherToUsdt(String urlTicket){
        BigDecimal result = null;
        try {
            URL url = new URL(urlTicket);
//            trustAllHosts();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                JSONArray array = JSONArray.fromObject("[" + reader.readLine() + "]");
                net.sf.json.JSONObject jsonObject = array.getJSONObject(0);
                net.sf.json.JSONObject trick = (net.sf.json.JSONObject)jsonObject.get("ticker");
                if (trick != null && trick.get("last") != null) {
                    result = new BigDecimal(trick.get("last").toString()) ;
                }
                reader.close();
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }
}
