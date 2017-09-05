package com.hy.gcar.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

/**
 * 
 * @author auto create
 * @since 1.0,2016-08-12 10:02:07
 */
public class Item implements Serializable {

    private static final long serialVersionUID = 5270853972547284L;

    private Long id;//

    private String levelName;//等级名称

    private String levelUrl;//等级图片URL

    private BigDecimal price;//购买价格

    private Integer enableCount;//启动车辆数量

    private BigDecimal changePrice;//更换车辆价格

    private BigDecimal driver;//选配司机价格

    private Date created;//创建时间

    private String modifier;//操作人

    private String modifierId;//操作人ID

    private Date lasttimeModify;//最后修改时间

    private Integer status;//上架状态 0:未上架；1:已上架；2:已下架；默认0

    private Integer level;//会员成长级别
    
    private List<CarType> carTypes;//套餐下的所有车型
    
    private BigDecimal dayPriceMinimum;//套餐车型最低的日使用价格

    public BigDecimal getDayPriceMinimum() {
		return dayPriceMinimum;
	}

	public void setDayPriceMinimum(BigDecimal dayPriceMinimum) {
		this.dayPriceMinimum = dayPriceMinimum;
	}

	public List<CarType> getCarTypes() {
		return carTypes;
	}

	public void setCarTypes(List<CarType> carTypes) {
		this.carTypes = carTypes;
	}

	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevelName() {
        return this.levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getLevelUrl() {
        return this.levelUrl;
    }

    public void setLevelUrl(String levelUrl) {
        this.levelUrl = levelUrl;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getEnableCount() {
        return this.enableCount;
    }

    public void setEnableCount(Integer enableCount) {
        this.enableCount = enableCount;
    }

    public BigDecimal getChangePrice() {
        return this.changePrice;
    }

    public void setChangePrice(BigDecimal changePrice) {
        this.changePrice = changePrice;
    }

    public BigDecimal getDriver() {
        return this.driver;
    }

    public void setDriver(BigDecimal driver) {
        this.driver = driver;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getModifier() {
        return this.modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifierId() {
        return this.modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    public Date getLasttimeModify() {
        return this.lasttimeModify;
    }

    public void setLasttimeModify(Date lasttimeModify) {
        this.lasttimeModify = lasttimeModify;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

}
