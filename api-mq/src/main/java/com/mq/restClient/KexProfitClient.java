package com.mq.restClient;

import com.mq.util.AppJson;
import com.mq.util.ParametersUtils;
import com.mq.util.client.RestClient;
import com.mq.util.client.RestClientUtil;
import com.mq.vo.kex.ProfitVO;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

/**
 * 结算收益
 */
public class KexProfitClient extends RestClient {
    public AppJson kexProfit(String url, List<ProfitVO> profitVO){
        String serverPath=url+UriUtils.PROFIT;
        RestClientUtil rcu=new RestClientUtil(new StringBuffer(serverPath));

        for (ProfitVO vo : profitVO) {
            rcu.addListParams(JSONObject.fromObject(vo));
        }

        return  ParametersUtils.getByKexAppJson(kexSendRequestPostList(rcu));
    }
}
