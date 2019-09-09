package com.mq.vo.kex;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description MachineCommonVO
 * @Author Administrator
 * @DATE 2019/4/15 0015 15:35
 **/
public class MachineCommonVO {
    //矿机Id
    private String machineId;
    //机器类型描述
    private String machineTypeDesc;
    //生产货币
    private String productCurrency;
    //矿机类型
    private Integer machineType;
    //矿机名字
    private String name;
    //电费
    private BigDecimal electricityFee;
    //功率
    private Float power;
    //额定产出
    private Float estimateOutput;
    //矿机类型Id
    private String machineTypeId;
    //图片名字
    private String imgs;
    //机器状态
    private int machineStatus;

    private String machineSetting;

    private List<String> imgsName;

    public List<String> getImgsName() {
        return imgsName;
    }

    public void setImgsName(List<String> imgsName) {
        this.imgsName = imgsName;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getMachineTypeDesc() {
        return machineTypeDesc;
    }

    public void setMachineTypeDesc(String machineTypeDesc) {
        this.machineTypeDesc = machineTypeDesc;
    }

    public String getProductCurrency() {
        return productCurrency;
    }

    public void setProductCurrency(String productCurrency) {
        this.productCurrency = productCurrency;
    }

    public Integer getMachineType() {
        return machineType;
    }

    public void setMachineType(Integer machineType) {
        this.machineType = machineType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getElectricityFee() {
        return electricityFee;
    }

    public void setElectricityFee(BigDecimal electricityFee) {
        this.electricityFee = electricityFee;
    }

    public Float getPower() {
        return power;
    }

    public void setPower(Float power) {
        this.power = power;
    }

    public Float getEstimateOutput() {
        return estimateOutput;
    }

    public void setEstimateOutput(Float estimateOutput) {
        this.estimateOutput = estimateOutput;
    }

    public String getMachineTypeId() {
        return machineTypeId;
    }

    public void setMachineTypeId(String machineTypeId) {
        this.machineTypeId = machineTypeId;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public int getMachineStatus() {
        return machineStatus;
    }

    public void setMachineStatus(int machineStatus) {
        this.machineStatus = machineStatus;
    }

    public String getMachineSetting() {
        return machineSetting;
    }

    public void setMachineSetting(String machineSetting) {
        this.machineSetting = machineSetting;
    }
}
