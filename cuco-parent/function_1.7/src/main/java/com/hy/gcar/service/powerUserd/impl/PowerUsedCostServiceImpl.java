package com.hy.gcar.service.powerUserd.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.PowerUsedCostMapper;
import com.hy.gcar.entity.PowerUsedCost;
import com.hy.gcar.service.powerUserd.PowerUsedCostService;

@Service(value = "tdPowerUsedCostService")
public class PowerUsedCostServiceImpl implements PowerUsedCostService {

    @Autowired
    private PowerUsedCostMapper<PowerUsedCost>powerUsedCostMapper;
    
    @Override
    public Integer insertSelective(PowerUsedCost tdPowerUsedCost) throws Exception {
           return this.powerUsedCostMapper.insertSelective(tdPowerUsedCost);
        }

    @Override
    public Integer insertBatch(List<PowerUsedCost> tdPowerUsedCost) throws Exception {
           return this.powerUsedCostMapper.insertBatch(tdPowerUsedCost) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.powerUsedCostMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.powerUsedCostMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(PowerUsedCost tdPowerUsedCost) {
           return this.powerUsedCostMapper.updateByPrimaryKeySelective(tdPowerUsedCost);
    }

    @Override
    public PowerUsedCost findById(Object id) {
           return (PowerUsedCost) this.powerUsedCostMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PowerUsedCost> selectByCondition(PowerUsedCost tdPowerUsedCost) {
           return (List<PowerUsedCost>) this.powerUsedCostMapper.selectByCondition(tdPowerUsedCost);
    }

    @Override
    public Integer selectCountByCondition(PowerUsedCost tdPowerUsedCost) {
           return  this.powerUsedCostMapper.selectCountByCondition(tdPowerUsedCost);
    }

	@Override
	public PowerUsedCost selectByPowerUsedId(Long powerUsedId) {
		PowerUsedCost tdPowerUsedCost = new PowerUsedCost();
		tdPowerUsedCost.setPowerUsedId(powerUsedId);
		List<PowerUsedCost> list = selectByCondition(tdPowerUsedCost );
		if(CollectionUtils.isNotEmpty(list)){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void updateCancelCarSubmit(PowerUsedCost usedCost) {
		this.powerUsedCostMapper.updateCancelCarSubmit(usedCost);
		
	}

}
