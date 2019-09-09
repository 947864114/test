package com.mq.vo.kex;

import java.math.BigDecimal;

public class OrderVO {

    private Integer uid;
    //商品ID
    private Integer fid;
    //订单编号:0新建订单，非0修改订单，增加电力包
    private Integer orderid;
    //购买数量
    private Integer number;
    //单价
    private BigDecimal price;
    //成交金额
    private BigDecimal amount;
    //电费包ID
    private String elecChgId;
    //电费包购买天数,最少不能少于10天
    private Integer elecChgDayNum;
    //电费包总价
    private BigDecimal elecChgAmount;
    //优惠券编码
    private String ticketCode;

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getElecChgId() {
        return elecChgId;
    }

    public void setElecChgId(String elecChgId) {
        this.elecChgId = elecChgId;
    }

    public Integer getElecChgDayNum() {
        return elecChgDayNum;
    }

    public void setElecChgDayNum(Integer elecChgDayNum) {
        this.elecChgDayNum = elecChgDayNum;
    }

    public BigDecimal getElecChgAmount() {
        return elecChgAmount;
    }

    public void setElecChgAmount(BigDecimal elecChgAmount) {
        this.elecChgAmount = elecChgAmount;
    }
}
