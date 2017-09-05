package com.hy.gcar.service.basic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.BasicServiceServicecityMapper;
import com.hy.gcar.entity.BasicServiceServicecity;
import com.hy.gcar.service.basic.BasicServiceServicecityService;

@Service(value = "trBasicServiceServicecityService")
public class BasicServiceServicecityServiceImpl implements BasicServiceServicecityService {

    @Autowired
    private BasicServiceServicecityMapper<BasicServiceServicecity>trBasicServiceServicecityMapper;
    
    @Override
    public Integer insertSelective(BasicServiceServicecity trBasicServiceServicecity) throws Exception {
           return this.trBasicServiceServicecityMapper.insertSelective(trBasicServiceServicecity);
        }

    @Override
    public Integer insertBatch(List<BasicServiceServicecity> trBasicServiceServicecity) throws Exception {
           return this.trBasicServiceServicecityMapper.insertBatch(trBasicServiceServicecity) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.trBasicServiceServicecityMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.trBasicServiceServicecityMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(BasicServiceServicecity trBasicServiceServicecity) {
           return this.trBasicServiceServicecityMapper.updateByPrimaryKeySelective(trBasicServiceServicecity);
    }

    @Override
    public BasicServiceServicecity findById(Object id) {
           return (BasicServiceServicecity) this.trBasicServiceServicecityMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<BasicServiceServicecity> selectByCondition(BasicServiceServicecity trBasicServiceServicecity) {
           return (List<BasicServiceServicecity>) this.trBasicServiceServicecityMapper.selectByCondition(trBasicServiceServicecity);
    }

    @Override
    public Integer selectCountByCondition(BasicServiceServicecity trBasicServiceServicecity) {
           return  this.trBasicServiceServicecityMapper.selectCountByCondition(trBasicServiceServicecity);
    }

}
