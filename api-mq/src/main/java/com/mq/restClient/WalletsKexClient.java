package com.mq.restClient;

import com.mq.util.KexAppJson;
import com.mq.util.client.RestClient;
import com.mq.util.client.RestClientUtil;

import com.mq.vo.kex.RefreshVO;
import com.mq.vo.kex.WalletsRechargeVO;
import org.springframework.stereotype.Component;

@Component
public class WalletsKexClient extends RestClient {

    public KexAppJson wallets(Integer uid, String coin, String url, String identityid) {
        String serverPath = url + UriUtils.ADDRESS;
        RestClientUtil rcu = new RestClientUtil(new StringBuffer(serverPath));
        rcu.addParm("uid", uid);
        rcu.addParm("coin", coin);
        rcu.addParm("identityid",identityid);
        return kexSendRequestGet(rcu);
    }

    public KexAppJson recharge(WalletsRechargeVO walletsRechargeVO, String url, String identityid) {
        String serverPath = url + UriUtils.RECHARGE;
        RestClientUtil rcu = new RestClientUtil(new StringBuffer(serverPath));
        rcu.addParm("uid", walletsRechargeVO.getUid());
        rcu.addParm("coin", walletsRechargeVO.getCoin());
        rcu.addParm("amount", walletsRechargeVO.getAmount());
        rcu.addParm("address", walletsRechargeVO.getAddress());
        rcu.addParm("identityid",identityid);
        rcu.addParm("btcfees",walletsRechargeVO.getBtcfees());
        return kexSendRequestPost(rcu);
    }

    public KexAppJson userWallet(String url, Integer uid,String identityid) {
        String serverPath = url + UriUtils.USERWALLETS;
        RestClientUtil rcu = new RestClientUtil(new StringBuffer(serverPath));
        rcu.addParm("uid", uid);
        rcu.addParm("identityid",identityid);
        return kexSendRequestGet(rcu);
    }

    public KexAppJson transferRecord(String url, Integer uid, String coin, Long begindate, Long endDate, String identityid) {
        String serverPath = url + UriUtils.TRANSFORRECORD;
        RestClientUtil rcu = new RestClientUtil(new StringBuffer(serverPath));

        rcu.addParm("uid",uid);
        rcu.addParm("coin",coin);
        rcu.addParm("begindate",begindate);
        rcu.addParm("enddate",endDate);
        rcu.addParm("identityid",identityid);
        return kexSendRequestGet(rcu);
    }

    public KexAppJson walletsRefresh(String url, RefreshVO refreshVO, String identityid){
        String serverPath=url+UriUtils.WALLETSREFRESH;
        RestClientUtil rcu=new RestClientUtil(new StringBuffer(serverPath));
        rcu.addParm("uid",refreshVO.getUid());
        rcu.addParm("coin",refreshVO.getCoin());
        rcu.addParm("tradePassword",refreshVO.getTradePassword());
        rcu.addParm("identityid",identityid);
        return kexSendRequestPost(rcu);
    }
}
