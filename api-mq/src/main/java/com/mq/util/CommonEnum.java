package com.mq.util;


/**
 * 公用枚举类
 */
public enum CommonEnum {
    COMMON_STATUS_YES(1, "有效"),
    COMMON_STATUS_NO(2, "无效"),

    COMPANY_PLAT_BL(1, "BL"),
    COMPANY_PLAT_KEX(2, "KEX"),
    COMPANY_PLAT_KG(3, "KG"),

    CATE_TYPE_DEFAULT(1, "默认"), CATE_TYPE_DEFINE(2, "自定义"),
    DIET_UNIT_1(1, "体积"), DIET_UNIT_2(2, "重量"),


    CALINCOMEDATENUM(1, "次日交割"),

    CALCULATEGOODS(1, "云算力商品"),
    MACHINEGOODS(2, "矿机商品"),



    BTC(1, "BTC"),
    ETH(2, "ETH"),
    USDT(3, "USDT"),
    BHD(4, "BHD"),

    UNPAID(1, "待支付"),
    PAID(2, "已支付"),
    INVALID(3, "已作废"),

    ELECPAID(1,"已支付"),
    ELEUNPAID(2,"未支付"),

    DETUPAID(0,"待支付"),

    UNSHIPPED(1,"待发货"),

    SHIPPED(2,"已发货"),

    UNDELIVERY(3,"待交割"),

    DELIVERY(4,"已交割"),

    RECEIVED(5,"已收货"),

    ORDER_DETAIL_STATUS_DELIVERY_OVERDUE(6,"已过期"),

    OK_INSTRUMENT_TYPE_ETH2USDT(1, "ETH2USDT"),
    OK_INSTRUMENT_TYPE_RMB2USDT(2, "RMB2USDT"),
    OK_INSTRUMENT_TYPE_BTC2USDT(3, "BTC2USDT"),

    ORDER_THIRD_OVERDUE(2, "已过期"),
    ORDER_THIRD_NO_PASS(1, "未过期"),

    TRANSTYPE_CURRENCY(2,"虚拟币交易"),

    TRANSTYPE_POOL(3,"矿池钱包操作"),

    FTYPE_RECHARGE(1,"充值"),
    FTYPE_WITHDRAW(2,"提现"),

    INCOME_RECHARGE(1, "充值收入"),
    INCOME_CAL(2, "算力产出收入"),
    OUTCOME_PAY(3, "购买算力支出"),
    OUTCOME_WITHDRAW(4, "提现支出"),
    OUTCOME_CAL(5, "算力支出"),
    OUTCOME_TRANSFERRECORD(6,"资金划转"),
    OUTCOME_ELEC(7, "电费包购买支出"),

    CUSCASH_STATUS_YES(1, "提现成功"),
    CUSCASH_STATUS_NO(2, "提现失败"),
    CUSCASH_STATUS_PROCESSED(3, "提现处理"),

    DEDUCT_YES(1,"已抵扣"),
    DEDUCT_NO(2,"未抵扣"),

    CPSTATUS_UNUSED(1,"未使用"),

    CPSTATUS_USED(2,"已使用"),

    CPSTATUS_OVERDUE(3,"已过期"),

    CPTYPE_MONEY(1,"金额优惠"),

    CPTYPE_DISCOUNT(2,"折扣"),


    RETURNSTATUS_AUDIT(1, "待审核"),
    RETURNSTATUS_YES(2, "通过"),
    RETURNSTATUS_NO(3, "拒绝"),

    ;

    private int key;
    private String code;

    CommonEnum(int key, String code) {
        this.key = key;
        this.code = code;
    }

    public int
    getKey() {
        return key;
    }


    public String getCode() {
        return code;
    }

    }
