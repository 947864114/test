package com.mq.vo.kex;

import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

public class WalletsRechargeVO {

    private Integer uid;
    private String coin;
    private BigDecimal amount;
    private BigDecimal btcfees;
    private String address;

    public BigDecimal getBtcfees() {
        return btcfees;
    }

    public void setBtcfees(BigDecimal btcfees) {
        this.btcfees = btcfees;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
