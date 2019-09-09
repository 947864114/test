package com.mq.vo.kex;


import java.math.BigDecimal;

public class GoodsCalAddVO {

    //云算力商品主键
    private String calGoodsId;
    //矿机主键
    private String machineId;
    //剩余
    private float surplus;
    //最少购买
    private float miniPurchase;
    //单价
    private BigDecimal unitPrice;
    //管理费用
    private BigDecimal manaExpense;
    //描述
    private String description;
    //图片
    private String imgs;
    //上下架
    private int goodsStatus;
    //有无效
    private int status;
    //计算收益天数
    private int calIncomeDateNum;
    //计算收益天数
    private int awardFlag;
    //初始化库存
    private Float initStock;
    //活动抽奖商品收益天数（只有活动商品有用）
    private Integer actIncomeDateNum=0;
    //活动商品在奖池中的数量（只有活动商品使用）
    private Integer actGoodsNum=0;
    //1-中文，2-英文
    private Integer languageFlag;
    //排序字段，数字越大月靠前
    private int orderGoods=0;
    //关联商品fid
    private int relatedgoodid;
    //关联系数
    private int relatedrate;

    private int elecFreeDayNum;

    public int getElecFreeDayNum() {
        return elecFreeDayNum;
    }

    public void setElecFreeDayNum(int elecFreeDayNum) {
        this.elecFreeDayNum = elecFreeDayNum;
    }

    public int getRelatedgoodid() {
        return relatedgoodid;
    }

    public void setRelatedgoodid(int relatedgoodid) {
        this.relatedgoodid = relatedgoodid;
    }

    public int getRelatedrate() {
        return relatedrate;
    }

    public void setRelatedrate(int relatedrate) {
        this.relatedrate = relatedrate;
    }

    private String platFlag;

    public String getPlatFlag() {
        return platFlag;
    }

    public void setPlatFlag(String platFlag) {
        this.platFlag = platFlag;
    }

    public Integer getActGoodsNum() {
        return actGoodsNum;
    }

    public void setActGoodsNum(Integer actGoodsNum) {
        this.actGoodsNum = actGoodsNum;
    }

    public int getAwardFlag() {
        return awardFlag;
    }

    public void setAwardFlag(int awardFlag) {
        this.awardFlag = awardFlag;
    }

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


    public float getSurplus() {
        return surplus;
    }

    public void setSurplus(float surplus) {
        this.surplus = surplus;
    }

    public float getMiniPurchase() {
        return miniPurchase;
    }

    public void setMiniPurchase(float miniPurchase) {
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

    public int getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(int goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCalIncomeDateNum() {
        return calIncomeDateNum;
    }

    public void setCalIncomeDateNum(int calIncomeDateNum) {
        this.calIncomeDateNum = calIncomeDateNum;
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

    public Integer getLanguageFlag() {
        return languageFlag;
    }

    public void setLanguageFlag(Integer languageFlag) {
        this.languageFlag = languageFlag;
    }

    public int getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(int orderGoods) {
        this.orderGoods = orderGoods;
    }
}
