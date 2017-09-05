package com.hy.gcar.service.coupon.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.hy.gcar.dao.CouponInfoMapper;
import com.hy.gcar.entity.Coupon;
import com.hy.gcar.entity.CouponInfo;
import com.hy.gcar.service.coupon.CouponInfoService;
import com.hy.gcar.service.coupon.CouponService;

@Service
public class CouponInfoServiceImpl implements CouponInfoService {

	protected Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private CouponInfoMapper<CouponInfo> couponInfoMapper;
    @Autowired
	private CouponService couponService;
    @Override
    public Integer insertSelective(CouponInfo tdCouponInfo) throws Exception {
           return this.couponInfoMapper.insertSelective(tdCouponInfo);
        }

    @Override
    public Integer insertBatch(List<CouponInfo> tdCouponInfo) throws Exception {
           return this.couponInfoMapper.insertBatch(tdCouponInfo) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.couponInfoMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.couponInfoMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(CouponInfo tdCouponInfo) {
           return this.couponInfoMapper.updateByPrimaryKeySelective(tdCouponInfo);
    }

    @Override
    public CouponInfo findById(Object id) {
           return (CouponInfo) this.couponInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CouponInfo> selectByCondition(CouponInfo tdCouponInfo) {
           return (List<CouponInfo>) this.couponInfoMapper.selectByCondition(tdCouponInfo);
    }

    @Override
    public Integer selectCountByCondition(CouponInfo tdCouponInfo) {
           return  this.couponInfoMapper.selectCountByCondition(tdCouponInfo);
    }
    
    @Override
   	public int getCouponInfoCountByMemberId(CouponInfo couponInfo) {
   		Assert.notNull(couponInfo, "优惠券明细对象为空");
   		int validCount = 0;//未过期的优惠券的数量
   		if(null == couponInfo.getMemberId()){
   			this.logger.error("查询用户的可用优惠卷个数，没有传入该用户ID");
   			return validCount;
   		}
   		List<CouponInfo> couponInfoList = this.couponInfoMapper.getCouponInfoListByEnable(couponInfo);
   		for(CouponInfo couponIn:couponInfoList){
   			//有效优惠券数量累加
   			if(5 != couponIn.getStatus() && 4 != couponIn.getStatus()){
   				validCount +=1 ;
   			}
   		}
   		return validCount;
   	}
    
    @Override
	public List<CouponInfo> getCouponInfoListByEnable(CouponInfo couponInfo) {
		Assert.notNull(couponInfo, "优惠券明细对象为空");
		List<CouponInfo> couponInfoList = new ArrayList<>();
		if(null == couponInfo.getMemberId()){
			this.logger.error("查询用户的可用优惠卷个数，没有传入该用户ID");
			return couponInfoList;
		}
		couponInfoList = this.couponInfoMapper.getCouponInfoListByEnable(couponInfo);
		for(CouponInfo couponIn:couponInfoList){
			if(null==couponIn.getCouponId()){
				//如果不存在优惠券ID，跳出本次循环
				continue;
			}
			this.setCouponById(couponIn);
		}
		return couponInfoList;
	}
    
    /**根据优惠卷ID将优惠卷信息带入优惠卷明细对象
	 * @param couponInfo
	 */
	public CouponInfo setCouponById(CouponInfo couponInfo){
		Coupon coupon  = couponService.findById(couponInfo.getCouponId());
		couponInfo.setCoupon(coupon);
		return couponInfo;
	}
	
	/**
	 * 清楚失效优惠券
	 */
	@Override
	public void removeCouponInfoByOutTime(CouponInfo couponInfo) {
		Assert.notNull(couponInfo, "优惠券明细对象为空");
		 //获取当前时间
		Date curDate = new Date();
		//获取当前用户的所有优惠卷
		List<CouponInfo> couponInfoList = this.couponInfoMapper.selectByCondition(couponInfo);
		for(CouponInfo couponI : couponInfoList){
			if((null!=couponI.getOutTime()&&curDate.after(couponI.getOutTime())) || couponI.getStatus() == 4 || couponI.getStatus() == 5){
				//设置成无效数据
				couponI.setValid(0);
				this.couponInfoMapper.updateByPrimaryKeySelective(couponI);
			}
		}
	}
	
	public List<CouponInfo> getCouponInfoByMemberIdAndItemId(CouponInfo couponInfo){
		Assert.notNull(couponInfo, "优惠券明细对象为空");
		List<CouponInfo> couponInfoList = new ArrayList<>();
		couponInfoList = this.couponInfoMapper.getCouponInfoByMemberIdAndItemId(couponInfo);
		return couponInfoList;
	}

}
