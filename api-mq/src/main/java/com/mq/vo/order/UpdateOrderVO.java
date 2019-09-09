package com.mq.vo.order;



public class UpdateOrderVO {
    private Integer fid;//订单id
    private Integer ecFid;//对应电费包id
    private Integer elecChgDayNum;//电费包天数
    private Integer uid;//用户ID
    private Integer goodsNum;//商品数量
    private Integer elecFreeDayNum;//赠送电费包天数

    public Integer getElecFreeDayNum() {
        return elecFreeDayNum;
    }

    public void setElecFreeDayNum(Integer elecFreeDayNum) {
        this.elecFreeDayNum = elecFreeDayNum;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
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
