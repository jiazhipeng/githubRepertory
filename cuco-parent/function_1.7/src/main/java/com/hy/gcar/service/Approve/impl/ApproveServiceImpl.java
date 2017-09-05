package com.hy.gcar.service.Approve.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.ApproveMapper;
import com.hy.gcar.entity.Approve;
import com.hy.gcar.service.Approve.ApproveService;

@Service(value = "tdApproveService")
public class ApproveServiceImpl implements ApproveService {

    @Autowired
    private ApproveMapper<Approve>tdApproveMapper;
    
    @Override
    public Integer insertSelective(Approve tdApprove) throws Exception {
           return this.tdApproveMapper.insertSelective(tdApprove);
        }

    @Override
    public Integer insertBatch(List<Approve> tdApprove) throws Exception {
           return this.tdApproveMapper.insertBatch(tdApprove) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tdApproveMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tdApproveMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(Approve tdApprove) {
           return this.tdApproveMapper.updateByPrimaryKeySelective(tdApprove);
    }

    @Override
    public Approve findById(Object id) {
           return (Approve) this.tdApproveMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Approve> selectByCondition(Approve tdApprove) {
           return (List<Approve>) this.tdApproveMapper.selectByCondition(tdApprove);
    }

    @Override
    public Integer selectCountByCondition(Approve tdApprove) {
           return  this.tdApproveMapper.selectCountByCondition(tdApprove);
    }

}
