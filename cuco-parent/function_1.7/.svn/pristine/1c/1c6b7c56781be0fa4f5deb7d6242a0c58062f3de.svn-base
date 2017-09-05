package com.hy.gcar.dao;

import java.util.List;

import com.hy.gcar.entity.CouponInfo;


public interface CouponInfoMapper<T> extends BaseDao<T> {

	/**根据用户ID获取可使用优惠券列表(状态为待使用，审核中的券)
	 * @param coupon
	 * @return
	 */
	public List<CouponInfo> getCouponInfoListByEnable(CouponInfo couponInfo);
	
	/**
	 * 根据用户Id以及套餐Id获取用户能用的优惠券列表
	 * @param couponInfo
	 * @return
	 */
	public List<CouponInfo> getCouponInfoByMemberIdAndItemId(CouponInfo couponInfo);
	
}
