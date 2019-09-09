package com.mq.vo.kex;

public class ElecChgVO {

    private String elecChgId;
    private String unitPrice;
    private String ecName;
    private String fid;
    private Integer  goodsStatus;

    public Integer getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(Integer goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getElecChgId() {
        return elecChgId;
    }

    public void setElecChgId(String elecChgId) {
        this.elecChgId = elecChgId;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getEcName() {
        return ecName;
    }

    public void setEcName(String ecName) {
        this.ecName = ecName;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }
}
