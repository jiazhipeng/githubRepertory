package com.hy.gcar.service.powerUserd.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.PowerUsedTimelogMapper;
import com.hy.gcar.entity.PowerUsedTimelog;
import com.hy.gcar.service.powerUserd.PowerUsedTimelogService;

@Service(value = "tdPowerUsedTimelogService")
public class PowerUsedTimelogServiceImpl implements PowerUsedTimelogService {

    @Autowired
    private PowerUsedTimelogMapper<PowerUsedTimelog>powerUsedTimelogMapper;
    
    @Override
    public Integer insertSelective(PowerUsedTimelog tdPowerUsedTimelog) throws Exception {
           return this.powerUsedTimelogMapper.insertSelective(tdPowerUsedTimelog);
        }

    @Override
    public Integer insertBatch(List<PowerUsedTimelog> tdPowerUsedTimelog) throws Exception {
           return this.powerUsedTimelogMapper.insertBatch(tdPowerUsedTimelog) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.powerUsedTimelogMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.powerUsedTimelogMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(PowerUsedTimelog tdPowerUsedTimelog) {
           return this.powerUsedTimelogMapper.updateByPrimaryKeySelective(tdPowerUsedTimelog);
    }

    @Override
    public PowerUsedTimelog findById(Object id) {
           return (PowerUsedTimelog) this.powerUsedTimelogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PowerUsedTimelog> selectByCondition(PowerUsedTimelog tdPowerUsedTimelog) {
           return (List<PowerUsedTimelog>) this.powerUsedTimelogMapper.selectByCondition(tdPowerUsedTimelog);
    }

    @Override
    public Integer selectCountByCondition(PowerUsedTimelog tdPowerUsedTimelog) {
           return  this.powerUsedTimelogMapper.selectCountByCondition(tdPowerUsedTimelog);
    }

}
