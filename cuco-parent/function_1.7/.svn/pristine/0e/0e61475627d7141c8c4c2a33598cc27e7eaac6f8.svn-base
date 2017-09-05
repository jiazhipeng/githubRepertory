package com.hy.gcar.service.basic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.BasicCostMapper;
import com.hy.gcar.entity.BasicCost;
import com.hy.gcar.service.basic.BasicCostService;

@Service(value = "tcBasicCostService")
public class BasicCostServiceImpl implements BasicCostService {

    @Autowired
    private BasicCostMapper<BasicCost>tcBasicCostMapper;
    
    @Override
    public Integer insertSelective(BasicCost tcBasicCost) throws Exception {
           return this.tcBasicCostMapper.insertSelective(tcBasicCost);
        }

    @Override
    public Integer insertBatch(List<BasicCost> tcBasicCost) throws Exception {
           return this.tcBasicCostMapper.insertBatch(tcBasicCost) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tcBasicCostMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tcBasicCostMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(BasicCost tcBasicCost) {
           return this.tcBasicCostMapper.updateByPrimaryKeySelective(tcBasicCost);
    }

    @Override
    public BasicCost findById(Object id) {
           return (BasicCost) this.tcBasicCostMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<BasicCost> selectByCondition(BasicCost tcBasicCost) {
           return (List<BasicCost>) this.tcBasicCostMapper.selectByCondition(tcBasicCost);
    }

    @Override
    public Integer selectCountByCondition(BasicCost tcBasicCost) {
           return  this.tcBasicCostMapper.selectCountByCondition(tcBasicCost);
    }
	@Override
	public BasicCost selectBasicCost() {
		// TODO Auto-generated method stub
		 return  this.tcBasicCostMapper.selectBasicCost();
	}
}
