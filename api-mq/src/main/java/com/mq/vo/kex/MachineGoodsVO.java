package com.mq.vo.kex;

import java.math.BigDecimal;

public class MachineGoodsVO {
    private String calGoodsId;
    private String machineId;
    private Float surplus;
    private Float miniPurchase;
    private BigDecimal unitPrice;
    private BigDecimal manaExpense;
    private String description;
    private String imgs;
    private Integer goodsStatus;
    private Integer status;
    private Integer calIncomeDateNum;
    private Integer awardFlag;
    private Float initStock;
    private Integer actIncomeDateNum;
    private Integer actGoodsNum;
    private Integer languageFlag;
    private Integer orderGoods;

    public String getCalGoodsId() {
        return calGoodsId;
    }

    public void setCalGoodsId(String calGoodsId) {
        this.calGoodsId = calGoodsId;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public Float getSurplus() {
        return surplus;
    }

    public void setSurplus(Float surplus) {
        this.surplus = surplus;
    }

    public Float getMiniPurchase() {
        return miniPurchase;
    }

    public void setMiniPurchase(Float miniPurchase) {
        this.miniPurchase = miniPurchase;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getManaExpense() {
        return manaExpense;
    }

    public void setManaExpense(BigDecimal manaExpense) {
        this.manaExpense = manaExpense;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public Integer getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(Integer goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCalIncomeDateNum() {
        return calIncomeDateNum;
    }

    public void setCalIncomeDateNum(Integer calIncomeDateNum) {
        this.calIncomeDateNum = calIncomeDateNum;
    }

    public Integer getAwardFlag() {
        return awardFlag;
    }

    public void setAwardFlag(Integer awardFlag) {
        this.awardFlag = awardFlag;
    }

    public Float getInitStock() {
        return initStock;
    }

    public void setInitStock(Float initStock) {
        this.initStock = initStock;
    }

    public Integer getActIncomeDateNum() {
        return actIncomeDateNum;
    }

    public void setActIncomeDateNum(Integer actIncomeDateNum) {
        this.actIncomeDateNum = actIncomeDateNum;
    }

    public Integer getActGoodsNum() {
        return actGoodsNum;
    }

    public void setActGoodsNum(Integer actGoodsNum) {
        this.actGoodsNum = actGoodsNum;
    }

    public Integer getLanguageFlag() {
        return languageFlag;
    }

    public void setLanguageFlag(Integer languageFlag) {
        this.languageFlag = languageFlag;
    }

    public Integer getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(Integer orderGoods) {
        this.orderGoods = orderGoods;
    }
}
