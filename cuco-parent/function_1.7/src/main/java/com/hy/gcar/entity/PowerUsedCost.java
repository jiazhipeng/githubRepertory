package com.hy.gcar.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author auto create
 * @since 1.0,2016-09-20 12:13:39
 */
public class PowerUsedCost implements Serializable {

    private static final long serialVersionUID = 2328031800718976L;

    private Long id;//

    private Long powerUsedId;//用车记录主表ID

    private BigDecimal total;//用车总费用

    private BigDecimal hurryPrice;//用车加急费

    private BigDecimal usedcarPrice;//用车费用

    private BigDecimal gasoline;//用车费用
    
	private BigDecimal gasolinePrice;//油耗费用（非电动）

    private BigDecimal replacecarPrice;//更换启用车费用

    private BigDecimal accidentcarPrice;//紧急救援费用

    private Long couponId;//优惠券ID

    private String couponName;//优惠券名称

    private BigDecimal couponPrice;//优惠券金额

    private BigDecimal contractPrice;//违约费

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPowerUsedId() {
        return this.powerUsedId;
    }

    public void setPowerUsedId(Long powerUsedId) {
        this.powerUsedId = powerUsedId;
    }

    public BigDecimal getTotal() {
        return this.total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getHurryPrice() {
        return this.hurryPrice;
    }

    public void setHurryPrice(BigDecimal hurryPrice) {
        this.hurryPrice = hurryPrice;
    }

    public BigDecimal getUsedcarPrice() {
        return this.usedcarPrice;
    }

    public void setUsedcarPrice(BigDecimal usedcarPrice) {
        this.usedcarPrice = usedcarPrice;
    }
    
    public BigDecimal getGasoline() {
		return gasoline;
	}

	public void setGasoline(BigDecimal gasoline) {
		this.gasoline = gasoline;
	}
	
    public BigDecimal getGasolinePrice() {
        return this.gasolinePrice;
    }

    public void setGasolinePrice(BigDecimal gasolinePrice) {
        this.gasolinePrice = gasolinePrice;
    }

    public BigDecimal getReplacecarPrice() {
        return this.replacecarPrice;
    }

    public void setReplacecarPrice(BigDecimal replacecarPrice) {
        this.replacecarPrice = replacecarPrice;
    }

    public BigDecimal getAccidentcarPrice() {
        return this.accidentcarPrice;
    }

    public void setAccidentcarPrice(BigDecimal accidentcarPrice) {
        this.accidentcarPrice = accidentcarPrice;
    }

    public Long getCouponId() {
        return this.couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return this.couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public BigDecimal getCouponPrice() {
        return this.couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public BigDecimal getContractPrice() {
        return this.contractPrice;
    }

    public void setContractPrice(BigDecimal contractPrice) {
        this.contractPrice = contractPrice;
    }

}
