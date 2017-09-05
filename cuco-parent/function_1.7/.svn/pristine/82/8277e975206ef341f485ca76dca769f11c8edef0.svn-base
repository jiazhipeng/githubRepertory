package com.hy.gcar.service.chargeOrdertypeLog.impl;

import java.util.List;

import com.hy.gcar.service.chargeOrdertypeLog.ChargeOrdertypeLogService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.http.util.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.hy.gcar.dao.ChargeOrdertypeLogMapper;
import com.hy.gcar.entity.ChargeOrdertypeLog;

@Service(value = "tdChargeOrdertypeLogService")
public class ChargeOrdertypeLogServiceImpl implements ChargeOrdertypeLogService {

    @Autowired
    private ChargeOrdertypeLogMapper<ChargeOrdertypeLog>tdChargeOrdertypeLogMapper;
    
    @Override
    public Integer insertSelective(ChargeOrdertypeLog tdChargeOrdertypeLog) throws Exception {
           return this.tdChargeOrdertypeLogMapper.insertSelective(tdChargeOrdertypeLog);
        }

    @Override
    public Integer insertBatch(List<ChargeOrdertypeLog> tdChargeOrdertypeLog) throws Exception {
           return this.tdChargeOrdertypeLogMapper.insertBatch(tdChargeOrdertypeLog) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tdChargeOrdertypeLogMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tdChargeOrdertypeLogMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(ChargeOrdertypeLog tdChargeOrdertypeLog) {
           return this.tdChargeOrdertypeLogMapper.updateByPrimaryKeySelective(tdChargeOrdertypeLog);
    }

    @Override
    public ChargeOrdertypeLog findById(Object id) {
           return (ChargeOrdertypeLog) this.tdChargeOrdertypeLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ChargeOrdertypeLog> selectByCondition(ChargeOrdertypeLog tdChargeOrdertypeLog) {
           return (List<ChargeOrdertypeLog>) this.tdChargeOrdertypeLogMapper.selectByCondition(tdChargeOrdertypeLog);
    }

    @Override
    public Integer selectCountByCondition(ChargeOrdertypeLog tdChargeOrdertypeLog) {
           return  this.tdChargeOrdertypeLogMapper.selectCountByCondition(tdChargeOrdertypeLog);
    }

    @Override
    public ChargeOrdertypeLog createChargeOrdertypeLog(ChargeOrdertypeLog tdChargeOrdertypeLog) throws Exception {
        this.insertSelective(tdChargeOrdertypeLog);
        return tdChargeOrdertypeLog;
    }

	@Override
	public ChargeOrdertypeLog updateIsOpenpage(ChargeOrdertypeLog chargeOrdertypeLog) {
		Asserts.notNull(chargeOrdertypeLog, "支付类型不允许为空");
		Asserts.notNull(chargeOrdertypeLog.getOrderNo(), "支付订单号不允许为空");
		
		ChargeOrdertypeLog cotl = new ChargeOrdertypeLog();
		cotl.setOrderNo(chargeOrdertypeLog.getOrderNo());
		cotl.setPayType(0);//0:京东；1:微信；2:后台人工',
		
		//根据类型变更时间 倒叙查询
		List<ChargeOrdertypeLog> chargeOrdertypeLogs = this.selectByCondition(cotl);
		if(CollectionUtils.isNotEmpty(chargeOrdertypeLogs)){
			chargeOrdertypeLog = chargeOrdertypeLogs.get(0);
		}
		cotl = new ChargeOrdertypeLog();
		cotl.setId(chargeOrdertypeLog.getId());
		cotl.setIsOpenpage(1);//是否打开网页 0：未打开；1：已打开
		this.updateByPrimaryKeySelective(cotl);
		return this.findById(cotl);
	}

}
