package com.mq.vo.kex;

import java.math.BigDecimal;
import java.util.Date;

public class ProfitVO {
    private String orderid;
    private String coinid;
    private Long date;
    private BigDecimal profit;
    private BigDecimal power;
    private BigDecimal manager;
    private BigDecimal actual;
    private int deduct;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getCoinid() {
        return coinid;
    }

    public void setCoinid(String coinid) {
        this.coinid = coinid;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getPower() {
        return power;
    }

    public void setPower(BigDecimal power) {
        this.power = power;
    }

    public BigDecimal getManager() {
        return manager;
    }

    public void setManager(BigDecimal manager) {
        this.manager = manager;
    }

    public BigDecimal getActual() {
        return actual;
    }

    public void setActual(BigDecimal actual) {
        this.actual = actual;
    }

    public int getDeduct() {
        return deduct;
    }

    public void setDeduct(int deduct) {
        this.deduct = deduct;
    }
}
