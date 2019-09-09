package com.mq.restClient;

import com.mq.util.KexAppJson;
import com.mq.util.AppJson;
import com.mq.util.ParametersUtils;
import com.mq.util.client.RestClient;
import com.mq.util.client.RestClientUtil;
import com.mq.vo.kex.CustomerUpdateVO;
import com.mq.vo.kex.LoginVO;
import com.mq.vo.kex.OrderVO;
import com.mq.vo.kex.RegisterVO;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

/**
 * Description: CustomerClient
 * Created by LQZ
 * DATE: 2019/7/5 17:53
 */
@Component
public class CustomerRestClient extends RestClient {

    public static final String F2_BITCOIN_URL = "http://api.f2pool.com/bitcoin/";
    public static final String DIFFICULTY = "https://blockexplorer.com/api/status?q=getDifficulty";

    /**
     * 分页查询
     *
     * @return
     */
    /*public AppJson toTest(String url, String test) {
        String serverPath = url + UriUtils.TEST;
        RestClientUtil rcu = new RestClientUtil(new StringBuffer(serverPath));
        if (!StringUtils.isEmpty(test)) rcu.addParm("test", test);
        return sendRequestGet(rcu);
    }*/

    /**
     * kex交易所最新行情信息获取接口
     *
     * @param url
     * @return
     */
    public KexAppJson price(String url, String coin) {
        String serverPath = url + UriUtils.PRICE;
        RestClientUtil rcu = new RestClientUtil(new StringBuffer(serverPath));
        if (!StringUtils.isEmpty(coin)) rcu.addParm("coin", coin);
        return kexSendRequestGet(rcu);
    }

    /**
     * kex交易所最新成交获取接口
     *
     * @param kex
     * @param coin
     * @param successcount
     * @return
     */
    public KexAppJson latestDeal(String kex, String coin, Integer successcount) {

        String serverPath = kex + UriUtils.LATESTDEAL;
        RestClientUtil rcu = new RestClientUtil(new StringBuffer(serverPath));

        rcu.addParm("coin", coin);
        rcu.addParm("successcount", successcount);

        return kexSendRequestGet(rcu);
    }

    /**
     * 查询所有kex交易所正在交易的币种列表
     *
     * @param kex
     * @return
     */
    public KexAppJson queryCurrency(String kex) {
        String serverPath = kex + UriUtils.QUEERCURRENCY;
        RestClientUtil rcu = new RestClientUtil(new StringBuffer(serverPath));

        return kexSendRequestGet(rcu);
    }

    /**
     * 查询算力
     *
     * @param account
     * @return
     */
    public JSONObject findHashrate(String account) {

        String serverPath = F2_BITCOIN_URL + account;
        RestClientUtil rcu = new RestClientUtil(new StringBuffer(serverPath));
        /*rcu.addListParams();
        if (!StringUtils.isEmpty(account)) rcu.addParm("account", account);*/
        return findHashrate(rcu);
    }

    /**
     * 查询全网难度
     *
     * @return
     */
    public JSONObject findEstimateOutput() {

        String serverPath = DIFFICULTY;
        RestClientUtil rcu = new RestClientUtil(new StringBuffer(serverPath));
        JSONObject object = findHashrate(rcu);
        return object;
    }

    /**
     * 云算平台调用kex接口生成商品订单
     *
     * @param kex
     * @param orderVO
     * @param identityid
     */
    public AppJson createOrder(String kex, OrderVO orderVO, String identityid) {

        String serverPath = kex + UriUtils.KEX_ORDER;
        RestClientUtil rcu = new RestClientUtil(new StringBuffer(serverPath));
        rcu.addParm("identityid", identityid);
        if (orderVO.getUid() != null) rcu.addParm("uid", orderVO.getUid());
        if (orderVO.getFid() != null) rcu.addParm("fid", orderVO.getFid());
        if (orderVO.getOrderid() != null) rcu.addParm("orderid", orderVO.getOrderid());
        if (orderVO.getNumber() != null) rcu.addParm("number", orderVO.getNumber());
        if (orderVO.getPrice() != null) rcu.addParm("price", orderVO.getPrice());
        if (orderVO.getAmount() != null) rcu.addParm("amount", orderVO.getAmount());
        if (orderVO.getElecChgId() != null) rcu.addParm("elecChgId", orderVO.getElecChgId());
        if (orderVO.getElecChgDayNum() != null) rcu.addParm("elecChgDayNum", orderVO.getElecChgDayNum());
        if (orderVO.getTicketCode() != null) rcu.addParm("ticketCode", orderVO.getTicketCode());
        System.out.println(orderVO.getElecChgAmount().toPlainString());

        if (orderVO.getElecChgAmount().compareTo(new BigDecimal(0)) == 0) {
            orderVO.setElecChgAmount(new BigDecimal(0));
        }
        if (orderVO.getElecChgAmount() != null) rcu.addParm("elecChgAmount", orderVO.getElecChgAmount());

        return ParametersUtils.getByKexAppJson(kexSendRequestPost(rcu));
    }

    /**
     * kex注册
     *
     * @param kex
     * @param customer
     * @return
     */
    public AppJson register(String kex, RegisterVO customer) {

        String serverPath = kex + UriUtils.REGISTER;

        RestClientUtil rcu = new RestClientUtil(new StringBuffer(serverPath));
        if (customer.getPassword() != null) rcu.addParm("password", customer.getPassword());
        rcu.addParm("name", customer.getName());
        if (customer.getPhone() != null) rcu.addParm("phone", customer.getPhone());
        if (customer.getMail() != null) rcu.addParm("mail", customer.getMail());
        rcu.addParm("idnumber", customer.getIdnumber());
        rcu.addParm("ident1", customer.getIdent1());
        rcu.addParm("ident2", customer.getIdent2());
        rcu.addParm("token", customer.getToken());

        return ParametersUtils.getByKexAppJson(kexSendRequestPost(rcu));
    }

    /**
     * kex登录
     *
     * @param kex
     * @param loginVO
     * @return
     */
    public AppJson login(String kex, LoginVO loginVO) {
        String serverPath = kex + UriUtils.LOGIN;

        RestClientUtil rcu = new RestClientUtil(new StringBuffer(serverPath));

        rcu.addParm("uid", loginVO.getUid());
        rcu.addParm("password", loginVO.getPassword());

        return ParametersUtils.getByKexAppJson(kexSendRequestPost(rcu));
    }

    /**
     * 修改用户信息
     *
     * @param
     * @return
     */
    public AppJson update(String kex, CustomerUpdateVO updateVO) {
        String serverPath = kex + UriUtils.UPDATE;

        RestClientUtil rcu = new RestClientUtil(new StringBuffer(serverPath));

        rcu.addParm("uid", updateVO.getUid());
        if (updateVO.getIdentityid() != null) rcu.addParm("identityid", updateVO.getIdentityid());
        if (updateVO.getCustomerName() != null) rcu.addParm("name", updateVO.getCustomerName());
        if (updateVO.getPhone() != null) rcu.addParm("phone", updateVO.getPhone());
        if (updateVO.getCustomerMail() != null) rcu.addParm("mail", updateVO.getCustomerMail());
        if (updateVO.getIdCard() != null) rcu.addParm("idnumber", updateVO.getIdCard());
        if (updateVO.getIdCardImg1() != null) rcu.addParm("ident1", updateVO.getIdCardImg1());
        if (updateVO.getIdCardImg2() != null) rcu.addParm("ident2", updateVO.getIdCardImg2());

        return ParametersUtils.getByKexAppJson(kexSendRequestPost(rcu));
    }

    /**
     * 查询用户信息
     *
     * @param
     * @return
     */
    public AppJson search(String kex, Integer uid) {
        String serverPath = kex + UriUtils.SEARCH;
        RestClientUtil rcu = new RestClientUtil(new StringBuffer(serverPath));
        if (!StringUtils.isEmpty(uid)) rcu.addParm("uid", uid);

        return ParametersUtils.getByKexAppJson(kexSendRequestGet(rcu));
    }
}
