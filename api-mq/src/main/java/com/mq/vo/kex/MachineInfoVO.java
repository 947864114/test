package com.mq.vo.kex;

import java.math.BigDecimal;

public class MachineInfoVO {

    private String machineId;
    private String machineTypeDesc;
    private String productCurrency;
    private Integer machineType;
    private String name;
    private BigDecimal electricityFee;
    private Float power;
    private Float estimateOutput;
    private String  machineTypeId;
    private String imgs;
    private Integer machineStatus;
    private  String machineSetting;

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

    public Integer getMachineStatus() {
        return machineStatus;
    }

    public void setMachineStatus(Integer machineStatus) {
        this.machineStatus = machineStatus;
    }

    public String getMachineSetting() {
        return machineSetting;
    }

    public void setMachineSetting(String machineSetting) {
        this.machineSetting = machineSetting;
    }
}
