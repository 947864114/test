package com.mq.restClient;

/**
 * Description:
 * <p>
 * Created by Lqz
 * DATE: 2017/10/26 0026
 */
public class UriUtils {
    public static final String URL_CLIENT      = "/api-client";


    public static final String URL_KEX      = "/webapi";

    /**
     * client
     */
    public static final String PRICE      = URL_KEX + "/v2/coinMarket.html";


    public static final String LATESTDEAL      = URL_KEX + "/v2/lastTrades.html";

    public static final String QUEERCURRENCY      = URL_KEX + "/v2/getTradeList.html";

    public static final String REGISTER      = URL_KEX + "/v2/register.html";

    public static final String SEARCH      = URL_KEX + "/v2/user/search.html";

    public static final String LOGIN      = URL_KEX + "/v2/user/login.html";

    public static final String UPDATE      = URL_KEX + "/v2/user/update.html";

    public static final String KEX_ORDER      = URL_KEX + "/v2/order/create.html";


    /**
     * address
     */
    public  static final String ADDRESS =URL_KEX+"/v2/coinAddress.html";


    /**
     * recharge
     */
    public static final String RECHARGE=URL_KEX+"/v2/recharge.html";


    /**
     * machine
     */
    public static final  String MACHINEINFO=URL_KEX+"/v2/machine/create.html";


    /**
     * machineDetail
     */
    public static final  String MACHINEDETAIL=URL_KEX+"/v2/machineDetail/create.html";

    /**
     * machineDetail
     */
    public static final  String MACHINEGOODS=URL_KEX+"/v2/machineGood/create.html";

    public static  final String MACHINE=URL_KEX+"/v2/machine/upper.html";

    public static final String USERWALLETS=URL_KEX+"/v2/userwallet.html";

    public static final String TRANSFORRECORD=URL_KEX+"/v2/transferRecord.html";

    public static final String MACHINELOWER=URL_KEX+"/v2/machine/lower.html";

    public static final String ELECHG=URL_KEX+"/v2/elecChg/create.html";

    public static final String PROFIT=URL_KEX+"/v2/order/profit.html";

    public static final String WALLETSREFRESH=URL_KEX+"/v2/refresh.html";

    public static final String COUPON=URL_KEX+"/v2/ticket/get.html";

    public static final String COUPON_DETAIL=URL_KEX+"/v2/ticket/seach.html";

}
