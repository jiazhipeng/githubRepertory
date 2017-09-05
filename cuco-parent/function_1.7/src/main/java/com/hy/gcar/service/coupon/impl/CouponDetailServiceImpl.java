package com.hy.gcar.service.coupon.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.CouponDetailMapper;
import com.hy.gcar.entity.CouponDetail;
import com.hy.gcar.service.coupon.CouponDetailService;

@Service(value = "tdCouponDetailService")
public class CouponDetailServiceImpl implements CouponDetailService {

    @Autowired
    private CouponDetailMapper<CouponDetail>tdCouponDetailMapper;
    
    @Override
    public Integer insertSelective(CouponDetail tdCouponDetail) throws Exception {
           return this.tdCouponDetailMapper.insertSelective(tdCouponDetail);
        }

    @Override
    public Integer insertBatch(List<CouponDetail> tdCouponDetail) throws Exception {
           return this.tdCouponDetailMapper.insertBatch(tdCouponDetail) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tdCouponDetailMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tdCouponDetailMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(CouponDetail tdCouponDetail) {
           return this.tdCouponDetailMapper.updateByPrimaryKeySelective(tdCouponDetail);
    }

    @Override
    public CouponDetail findById(Object id) {
           return (CouponDetail) this.tdCouponDetailMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CouponDetail> selectByCondition(CouponDetail tdCouponDetail) {
           return (List<CouponDetail>) this.tdCouponDetailMapper.selectByCondition(tdCouponDetail);
    }

    @Override
    public Integer selectCountByCondition(CouponDetail tdCouponDetail) {
           return  this.tdCouponDetailMapper.selectCountByCondition(tdCouponDetail);
    }

}
