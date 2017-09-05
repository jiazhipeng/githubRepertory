package com.hy.gcar.service.chargeReward.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.ChargeRewardMapper;
import com.hy.gcar.entity.ChargeReward;
import com.hy.gcar.service.chargeReward.ChargeRewardService;

@Service(value = "tcChargeRewardService")
public class ChargeRewardServiceImpl implements ChargeRewardService {

    @Autowired
    private ChargeRewardMapper<ChargeReward>tcChargeRewardMapper;
    
    @Override
    public Integer insertSelective(ChargeReward tcChargeReward) throws Exception {
           return this.tcChargeRewardMapper.insertSelective(tcChargeReward);
        }

    @Override
    public Integer insertBatch(List<ChargeReward> tcChargeReward) throws Exception {
           return this.tcChargeRewardMapper.insertBatch(tcChargeReward) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tcChargeRewardMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tcChargeRewardMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(ChargeReward tcChargeReward) {
           return this.tcChargeRewardMapper.updateByPrimaryKeySelective(tcChargeReward);
    }

    @Override
    public ChargeReward findById(Object id) {
           return (ChargeReward) this.tcChargeRewardMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ChargeReward> selectByCondition(ChargeReward tcChargeReward) {
           return (List<ChargeReward>) this.tcChargeRewardMapper.selectByCondition(tcChargeReward);
    }

    @Override
    public Integer selectCountByCondition(ChargeReward tcChargeReward) {
           return  this.tcChargeRewardMapper.selectCountByCondition(tcChargeReward);
    }
    
    @Override
    public ChargeReward selectObjectByCodition(ChargeReward tcChargeReward){
    	List<ChargeReward> list = this.tcChargeRewardMapper.selectByCondition(tcChargeReward);
    	ChargeReward reward = null;
    	if(list != null){
    		 reward = list.get(0);
    	}
    	return reward;
    }
    

}
