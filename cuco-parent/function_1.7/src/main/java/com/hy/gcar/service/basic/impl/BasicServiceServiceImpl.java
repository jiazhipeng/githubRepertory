package com.hy.gcar.service.basic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.BasicServiceMapper;
import com.hy.gcar.entity.BasicService;
import com.hy.gcar.service.basic.BasicServiceService;

@Service(value = "tcBasicServiceService")
public class BasicServiceServiceImpl implements BasicServiceService {

    @Autowired
    private BasicServiceMapper<BasicService>tcBasicServiceMapper;
    
    @Override
    public Integer insertSelective(BasicService tcBasicService) throws Exception {
           return this.tcBasicServiceMapper.insertSelective(tcBasicService);
        }

    @Override
    public Integer insertBatch(List<BasicService> tcBasicService) throws Exception {
           return this.tcBasicServiceMapper.insertBatch(tcBasicService) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tcBasicServiceMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tcBasicServiceMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(BasicService tcBasicService) {
           return this.tcBasicServiceMapper.updateByPrimaryKeySelective(tcBasicService);
    }

    @Override
    public BasicService findById(Object id) {
           return (BasicService) this.tcBasicServiceMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<BasicService> selectByCondition(BasicService tcBasicService) {
           return (List<BasicService>) this.tcBasicServiceMapper.selectByCondition(tcBasicService);
    }

    @Override
    public Integer selectCountByCondition(BasicService tcBasicService) {
           return  this.tcBasicServiceMapper.selectCountByCondition(tcBasicService);
    }

}
