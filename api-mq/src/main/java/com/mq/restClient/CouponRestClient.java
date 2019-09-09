package com.mq.restClient;

import com.mq.util.AppJson;
import com.mq.util.ParametersUtils;
import com.mq.util.client.RestClient;
import com.mq.util.client.RestClientUtil;
import org.springframework.stereotype.Component;

@Component
public class CouponRestClient extends RestClient {


    public AppJson ticket(String kex, int status) {

        String serverPath = kex + UriUtils.COUPON;
        RestClientUtil rcu = new RestClientUtil(new StringBuffer(serverPath));
        rcu.addParm("status", status);

        return  ParametersUtils.getByKexAppJson(kexSendRequestGet(rcu));
    }

    public AppJson ticketDetail(String kex, String code){
        String serverPath=kex+UriUtils.COUPON_DETAIL;
        RestClientUtil rcu=new RestClientUtil(new StringBuffer(serverPath));
        rcu.addParm("code",code);
        return ParametersUtils.getByKexAppJson(kexSendRequestGet(rcu));
    }
}
