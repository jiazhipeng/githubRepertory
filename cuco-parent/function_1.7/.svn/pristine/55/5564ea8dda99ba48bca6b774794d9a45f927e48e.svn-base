package com.hy.gcar.service.getmoney.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.GetmoneyApplyLogMapper;
import com.hy.gcar.entity.GetmoneyApplyLog;
import com.hy.gcar.service.getmoney.GetmoneyApplyLogService;

@Service(value = "tdGetmoneyApplyLogService")
public class GetmoneyApplyLogServiceImpl implements GetmoneyApplyLogService {

    @Autowired
    private GetmoneyApplyLogMapper<GetmoneyApplyLog>tdGetmoneyApplyLogMapper;
    
    @Override
    public Integer insertSelective(GetmoneyApplyLog tdGetmoneyApplyLog) throws Exception {
           return this.tdGetmoneyApplyLogMapper.insertSelective(tdGetmoneyApplyLog);
        }

    @Override
    public Integer insertBatch(List<GetmoneyApplyLog> tdGetmoneyApplyLog) throws Exception {
           return this.tdGetmoneyApplyLogMapper.insertBatch(tdGetmoneyApplyLog) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tdGetmoneyApplyLogMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tdGetmoneyApplyLogMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(GetmoneyApplyLog tdGetmoneyApplyLog) {
           return this.tdGetmoneyApplyLogMapper.updateByPrimaryKeySelective(tdGetmoneyApplyLog);
    }

    @Override
    public GetmoneyApplyLog findById(Object id) {
           return (GetmoneyApplyLog) this.tdGetmoneyApplyLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GetmoneyApplyLog> selectByCondition(GetmoneyApplyLog tdGetmoneyApplyLog) {
           return (List<GetmoneyApplyLog>) this.tdGetmoneyApplyLogMapper.selectByCondition(tdGetmoneyApplyLog);
    }

    @Override
    public Integer selectCountByCondition(GetmoneyApplyLog tdGetmoneyApplyLog) {
           return  this.tdGetmoneyApplyLogMapper.selectCountByCondition(tdGetmoneyApplyLog);
    }

}
