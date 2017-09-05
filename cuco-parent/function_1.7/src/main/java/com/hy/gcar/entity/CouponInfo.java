package com.hy.gcar.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hy.gcar.constant.ButlerTaskStatus;
import com.hy.gcar.enums.CouponInfoStatus;

/**
 * 
 * @author auto create
 * @since 1.0,2016-09-21 10:42:34
 */
public class CouponInfo implements Serializable {

    private static final long serialVersionUID = 1050741722802753L;

    private Long id;//主键

    private String couponNum;//奖励优惠券编号（HY+8位随机数）

    private Integer status;//优惠券状态 0:待领取；1:未启用；2:待使用；3:审核中；4:已使用；5:已过期；
    
    private String statusInfo;//状态中文描述
    
    public String getStatusInfo() {
    	//返回响应状态的中文描述
    	return CouponInfoStatus.getName(this.getStatus());
	}
    
    private Long marketingId;//活动id

    private String marketingName;//活动名称

    private Date getTime;//获得时间

    private String remark;//备注

    private Date outTime;//过期时间

    private Long couponId;//优惠券id

    private Long memberId;//会员id

    private String memberName;//

    private String modifierId;//操作人id

    private String modifier;//操作人姓名

    private Date lasttimeModify;//最后修改时间

    private Integer couponFrom;//优惠券创建来源 0:活动发放；1:后台发放；

    private Date created;//创建时间

    private Integer valid;//数据有效性 0:无效；1:有效；

    private String couponName;//

    private BigDecimal price;//抵值金额

    private Integer couponType;//优惠券类型：1:用车券

    private Date usedTime;//使用时间

    private Coupon coupon;
    
    private Long itemId;//套餐Id
    
    public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	//asc  desc
    private String orderByOutTime;
    
    public String getOrderByOutTime() {
		return orderByOutTime;
	}

	public void setOrderByOutTime(String orderByOutTime) {
		this.orderByOutTime = orderByOutTime;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCouponNum() {
        return this.couponNum;
    }

    public void setCouponNum(String couponNum) {
        this.couponNum = couponNum;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getMarketingId() {
        return this.marketingId;
    }

    public void setMarketingId(Long marketingId) {
        this.marketingId = marketingId;
    }

    public String getMarketingName() {
        return this.marketingName;
    }

    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName;
    }

    public Date getGetTime() {
        return this.getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getOutTime() {
        return this.outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public Long getCouponId() {
        return this.couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return this.memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
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

    public Integer getCouponFrom() {
        return this.couponFrom;
    }

    public void setCouponFrom(Integer couponFrom) {
        this.couponFrom = couponFrom;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getValid() {
        return this.valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public String getCouponName() {
        return this.couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
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

    public Date getUsedTime() {
        return this.usedTime;
    }

    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
    }
    
    @JsonProperty("status_display")
    public String getStatusDisplay(){
    	return CouponInfoStatus.getName(this.getStatus());
    }

}
