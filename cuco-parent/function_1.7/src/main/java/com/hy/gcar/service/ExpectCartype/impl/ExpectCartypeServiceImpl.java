package com.hy.gcar.service.ExpectCartype.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.ExpectCartypeMapper;
import com.hy.gcar.entity.ExpectCartype;
import com.hy.gcar.service.ExpectCartype.ExpectCartypeService;

@Service(value = "tdExpectCartypeService")
public class ExpectCartypeServiceImpl implements ExpectCartypeService {

    @Autowired
    private ExpectCartypeMapper<ExpectCartype>tdExpectCartypeMapper;
    
    @Override
    public Integer insertSelective(ExpectCartype tdExpectCartype) throws Exception {
           return this.tdExpectCartypeMapper.insertSelective(tdExpectCartype);
        }

    @Override
    public Integer insertBatch(List<ExpectCartype> tdExpectCartype) throws Exception {
           return this.tdExpectCartypeMapper.insertBatch(tdExpectCartype) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tdExpectCartypeMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tdExpectCartypeMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(ExpectCartype tdExpectCartype) {
           return this.tdExpectCartypeMapper.updateByPrimaryKeySelective(tdExpectCartype);
    }

    @Override
    public ExpectCartype findById(Object id) {
           return (ExpectCartype) this.tdExpectCartypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ExpectCartype> selectByCondition(ExpectCartype tdExpectCartype) {
           return (List<ExpectCartype>) this.tdExpectCartypeMapper.selectByCondition(tdExpectCartype);
    }

    @Override
    public Integer selectCountByCondition(ExpectCartype tdExpectCartype) {
           return  this.tdExpectCartypeMapper.selectCountByCondition(tdExpectCartype);
    }

}
