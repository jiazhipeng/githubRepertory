package com.hy.gcar.service.basicOperationType.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.BasicOperationTypeMapper;
import com.hy.gcar.entity.BasicOperationType;
import com.hy.gcar.service.basicOperationType.BasicOperationTypeService;

@Service(value = "tcBasicOperationTypeService")
public class BasicOperationTypeServiceImpl implements BasicOperationTypeService {

    @Autowired
    private BasicOperationTypeMapper<BasicOperationType>tcBasicOperationTypeMapper;
    
    @Override
    public Integer insertSelective(BasicOperationType tcBasicOperationType) throws Exception {
           return this.tcBasicOperationTypeMapper.insertSelective(tcBasicOperationType);
        }

    @Override
    public Integer insertBatch(List<BasicOperationType> tcBasicOperationType) throws Exception {
           return this.tcBasicOperationTypeMapper.insertBatch(tcBasicOperationType) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tcBasicOperationTypeMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tcBasicOperationTypeMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(BasicOperationType tcBasicOperationType) {
           return this.tcBasicOperationTypeMapper.updateByPrimaryKeySelective(tcBasicOperationType);
    }

    @Override
    public BasicOperationType findById(Object id) {
           return (BasicOperationType) this.tcBasicOperationTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<BasicOperationType> selectByCondition(BasicOperationType tcBasicOperationType) {
           return (List<BasicOperationType>) this.tcBasicOperationTypeMapper.selectByCondition(tcBasicOperationType);
    }

    @Override
    public Integer selectCountByCondition(BasicOperationType tcBasicOperationType) {
           return  this.tcBasicOperationTypeMapper.selectCountByCondition(tcBasicOperationType);
    }

}
