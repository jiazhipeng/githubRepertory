package com.hy.gcar.service.coupon.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.CouponMapper;
import com.hy.gcar.entity.Coupon;
import com.hy.gcar.entity.CouponDetail;
import com.hy.gcar.service.coupon.CouponDetailService;
import com.hy.gcar.service.coupon.CouponService;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponMapper<Coupon>tdCouponMapper;
    @Autowired
    private CouponDetailService couponDetailService;
    
    @Override
    public Integer insertSelective(Coupon tdCoupon) throws Exception {
           return this.tdCouponMapper.insertSelective(tdCoupon);
        }

    @Override
    public Integer insertBatch(List<Coupon> tdCoupon) throws Exception {
           return this.tdCouponMapper.insertBatch(tdCoupon) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tdCouponMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tdCouponMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(Coupon tdCoupon) {
           return this.tdCouponMapper.updateByPrimaryKeySelective(tdCoupon);
    }

    @Override
    public Coupon findById(Object id) {
    	Coupon coupon = this.tdCouponMapper.selectByPrimaryKey(id);
    	CouponDetail couponDetail = new CouponDetail();
    	couponDetail.setCouponId(coupon.getId());
    	List<CouponDetail> list = couponDetailService.selectByCondition(couponDetail);
    	if(list.size()>0){
    		String view = "";
    		for (CouponDetail couponDetailView : list) {
    			view+= couponDetailView.getItemName()+",";
			}
    		coupon.setItems(view.substring(0, view.lastIndexOf(",")));
    	}
           return coupon;
    }

    @Override
    public List<Coupon> selectByCondition(Coupon tdCoupon) {
           return (List<Coupon>) this.tdCouponMapper.selectByCondition(tdCoupon);
    }

    @Override
    public Integer selectCountByCondition(Coupon tdCoupon) {
           return  this.tdCouponMapper.selectCountByCondition(tdCoupon);
    }

}
