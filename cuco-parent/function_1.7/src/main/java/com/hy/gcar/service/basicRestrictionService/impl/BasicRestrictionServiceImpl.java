package com.hy.gcar.service.basicRestrictionService.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.BasicRestrictionMapper;
import com.hy.gcar.entity.BasicRestriction;
import com.hy.gcar.service.basicRestrictionService.BasicRestrictionService;

@Service(value = "tdBasicRestrictionService")
public class BasicRestrictionServiceImpl implements BasicRestrictionService {

    @Autowired
    private BasicRestrictionMapper<BasicRestriction>tdBasicRestrictionMapper;
    
    @Override
    public Integer insertSelective(BasicRestriction tdBasicRestriction) throws Exception {
           return this.tdBasicRestrictionMapper.insertSelective(tdBasicRestriction);
        }

    @Override
    public Integer insertBatch(List<BasicRestriction> tdBasicRestriction) throws Exception {
           return this.tdBasicRestrictionMapper.insertBatch(tdBasicRestriction) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tdBasicRestrictionMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tdBasicRestrictionMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(BasicRestriction tdBasicRestriction) {
           return this.tdBasicRestrictionMapper.updateByPrimaryKeySelective(tdBasicRestriction);
    }

    @Override
    public BasicRestriction findById(Object id) {
           return (BasicRestriction) this.tdBasicRestrictionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<BasicRestriction> selectByCondition(BasicRestriction tdBasicRestriction) {
           return (List<BasicRestriction>) this.tdBasicRestrictionMapper.selectByCondition(tdBasicRestriction);
    }

    @Override
    public Integer selectCountByCondition(BasicRestriction tdBasicRestriction) {
           return  this.tdBasicRestrictionMapper.selectCountByCondition(tdBasicRestriction);
    }

}
