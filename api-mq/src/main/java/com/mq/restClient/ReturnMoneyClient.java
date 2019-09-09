package com.mq.restClient;

import com.alibaba.fastjson.JSONObject;
import com.mq.util.AppJson;
import com.mq.util.client.RestClient;
import com.mq.util.client.RestClientUtil;
import com.mq.util.uri.ReleaseApiUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ReturnMoneyClient extends RestClient {


    /**
     * 商品返现
     *
     * @param url
     * @param jsonObject
     * @return
     */
    public AppJson returnMoney(String url, JSONObject jsonObject) {

        String serverPath = url + ReleaseApiUtil.RETURNMONEY;

        RestClientUtil rcu = new RestClientUtil(new StringBuffer(serverPath));

        rcu.addParm("orderid", (Integer) jsonObject.get("fid"));
        rcu.addParm("uid", (Integer) jsonObject.get("uid"));
        rcu.addParm("amount",new BigDecimal (String.valueOf(jsonObject.get("totalPrice"))));
        rcu.addParm("customerId", (String) jsonObject.get("customerId"));

        return sendRequestPost(rcu);
    }
}
