package com.hy.gcar.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 
 * @author auto create
 * @since 1.0,2016-09-14 11:47:25
 */
public class Coupon implements Serializable {

    private static final long serialVersionUID = 6985722628484893L;

    private Long id;//主键

    private String name;//优惠券名称

    private String modifierId;//操作人id

    private String modifier;//操作人姓名

    private Date lasttimeModify;//

    private Integer daysAfter;//结束日期相对有效期

    private Integer status;//优惠券状态 0:待使用；1:使用中；2:已过期；3:已停止；

    private Date created;//创建时间

    private String remark;//备注

    private BigDecimal price;//抵值金额

    private Integer couponType;//类型，1：用车券

    private String items;//适用权益
    
    public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModifierId() {
        return this.modifierId;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    public String getModifier() {
        return this.modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getLasttimeModify() {
        return this.lasttimeModify;
    }

    public void setLasttimeModify(Date lasttimeModify) {
        this.lasttimeModify = lasttimeModify;
    }

    public Integer getDaysAfter() {
        return this.daysAfter;
    }

    public void setDaysAfter(Integer daysAfter) {
        this.daysAfter = daysAfter;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCouponType() {
        return this.couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

}
