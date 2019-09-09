package com.mq.vo.order;

public class AddOrderVO {
    private Integer fid;
    private String cgFid;
    private Integer uid;
    private Integer goodsNum;
    private Integer  ecFid;
    private Integer elecChgDayNum;
    private String ticketCode;


    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getCgFid() {
        return cgFid;
    }

    public void setCgFid(String cgFid) {
        this.cgFid = cgFid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Integer getEcFid() {
        return ecFid;
    }

    public void setEcFid(Integer ecFid) {
        this.ecFid = ecFid;
    }

    public Integer getElecChgDayNum() {
        return elecChgDayNum;
    }

    public void setElecChgDayNum(Integer elecChgDayNum) {
        this.elecChgDayNum = elecChgDayNum;
    }
}
