package com.hy.gcar.service.Approve.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.ApproveLogMapper;
import com.hy.gcar.entity.ApproveLog;
import com.hy.gcar.service.Approve.ApproveLogService;

@Service(value = "tdApproveLogService")
public class ApproveLogServiceImpl implements ApproveLogService {

    @Autowired
    private ApproveLogMapper<ApproveLog>tdApproveLogMapper;
    
    @Override
    public Integer insertSelective(ApproveLog tdApproveLog) throws Exception {
           return this.tdApproveLogMapper.insertSelective(tdApproveLog);
        }

    @Override
    public Integer insertBatch(List<ApproveLog> tdApproveLog) throws Exception {
           return this.tdApproveLogMapper.insertBatch(tdApproveLog) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tdApproveLogMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tdApproveLogMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(ApproveLog tdApproveLog) {
           return this.tdApproveLogMapper.updateByPrimaryKeySelective(tdApproveLog);
    }

    @Override
    public ApproveLog findById(Object id) {
           return (ApproveLog) this.tdApproveLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ApproveLog> selectByCondition(ApproveLog tdApproveLog) {
           return (List<ApproveLog>) this.tdApproveLogMapper.selectByCondition(tdApproveLog);
    }

    @Override
    public Integer selectCountByCondition(ApproveLog tdApproveLog) {
           return  this.tdApproveLogMapper.selectCountByCondition(tdApproveLog);
    }

}
