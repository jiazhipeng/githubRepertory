package com.hy.gcar.entity;

import java.io.Serializable;

/**
 * 
 * @author auto create
 * @since 1.0,2016-09-14 11:47:25
 */
public class CouponDetail implements Serializable {

    private static final long serialVersionUID = 8941806646667633L;

    private Long id;//主键

    private String itemName;//会员成长名称

    private Long itemId;//会员成长ID

    private Long couponId;//优惠券主表ID

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getCouponId() {
        return this.couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

}
