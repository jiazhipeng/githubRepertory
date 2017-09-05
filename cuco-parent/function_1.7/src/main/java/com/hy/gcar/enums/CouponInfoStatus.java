package com.hy.gcar.enums;

/**
* @ClassName: CouponInfoStatus 
* @Description: TODO(优惠券明细状态) 
* @author huanghua
* @date 2016年9月21日 上午11:41:46
 */
public enum CouponInfoStatus {
	//'优惠券状态 0:待领取；1:未启用；2:待使用；3:审核中；4:已使用；5:已过期；',
	COUPONINFOSTATUS_RECEIVE(0,"待领取"),
	COUPONINFOSTATUS_ENABLE(1,"未启用"),
	COUPONINFOSTATUS_WAITUSED(2,"待使用"),
	COUPONINFOSTATUS_AUDIT(3,"审核中"),
	COUPONINFOSTATUS_USED(4,"已使用"),
	COUPONSTATUS_OUTDATE(5,"已过期");

	private Integer index;
	
	private String name;

	private CouponInfoStatus(Integer index, String name) {
		this.index = index;
		this.name = name;
	}
	
	public static  String getName(Integer targetIndex){
		if(null == targetIndex)
			return "";
		for(CouponInfoStatus status: CouponInfoStatus.values()){
			if(status.getIndex().intValue() == targetIndex.intValue()){
				return status.getName();
			}
		}
		return "";
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
