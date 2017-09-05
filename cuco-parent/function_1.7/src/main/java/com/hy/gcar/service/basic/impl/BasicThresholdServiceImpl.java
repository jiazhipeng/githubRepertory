package com.hy.gcar.service.basic.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.BasicThresholdMapper;
import com.hy.gcar.entity.BasicThreshold;
import com.hy.gcar.service.basic.BasicThresholdService;

@Service(value = "tcBasicThresholdService")
public class BasicThresholdServiceImpl implements BasicThresholdService {

    @Autowired
    private BasicThresholdMapper<BasicThreshold>tcBasicThresholdMapper;
    
    @Override
    public Integer insertSelective(BasicThreshold tcBasicThreshold) throws Exception {
           return this.tcBasicThresholdMapper.insertSelective(tcBasicThreshold);
        }

    @Override
    public Integer insertBatch(List<BasicThreshold> tcBasicThreshold) throws Exception {
           return this.tcBasicThresholdMapper.insertBatch(tcBasicThreshold) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tcBasicThresholdMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tcBasicThresholdMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(BasicThreshold tcBasicThreshold) {
           return this.tcBasicThresholdMapper.updateByPrimaryKeySelective(tcBasicThreshold);
    }

    @Override
    public BasicThreshold findById(Object id) {
           return (BasicThreshold) this.tcBasicThresholdMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<BasicThreshold> selectByCondition(BasicThreshold tcBasicThreshold) {
           return (List<BasicThreshold>) this.tcBasicThresholdMapper.selectByCondition(tcBasicThreshold);
    }

    @Override
    public Integer selectCountByCondition(BasicThreshold tcBasicThreshold) {
           return  this.tcBasicThresholdMapper.selectCountByCondition(tcBasicThreshold);
    }

	@Override
	public BasicThreshold selectBasicThreshold() {
		List<BasicThreshold> list=(List<BasicThreshold>) this.tcBasicThresholdMapper.selectByCondition(new BasicThreshold());
		if(CollectionUtils.isNotEmpty(list)){
			return list.get(0);
		}
		return null;
	}

}
