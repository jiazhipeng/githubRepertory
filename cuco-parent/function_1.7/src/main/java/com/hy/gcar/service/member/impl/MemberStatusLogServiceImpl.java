package com.hy.gcar.service.member.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.MemberStatusLogMapper;
import com.hy.gcar.entity.MemberStatusLog;
import com.hy.gcar.service.member.MemberStatusLogService;

@Service(value = "tdMemberStatusLogService")
public class MemberStatusLogServiceImpl implements MemberStatusLogService {

    @Autowired
    private MemberStatusLogMapper<MemberStatusLog>tdMemberStatusLogMapper;
    
    @Override
    public Integer insertSelective(MemberStatusLog tdMemberStatusLog) throws Exception {
           return this.tdMemberStatusLogMapper.insertSelective(tdMemberStatusLog);
        }

    @Override
    public Integer insertBatch(List<MemberStatusLog> tdMemberStatusLog) throws Exception {
           return this.tdMemberStatusLogMapper.insertBatch(tdMemberStatusLog) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tdMemberStatusLogMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tdMemberStatusLogMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(MemberStatusLog tdMemberStatusLog) {
           return this.tdMemberStatusLogMapper.updateByPrimaryKeySelective(tdMemberStatusLog);
    }

    @Override
    public MemberStatusLog findById(Object id) {
           return (MemberStatusLog) this.tdMemberStatusLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MemberStatusLog> selectByCondition(MemberStatusLog tdMemberStatusLog) {
           return (List<MemberStatusLog>) this.tdMemberStatusLogMapper.selectByCondition(tdMemberStatusLog);
    }

    @Override
    public Integer selectCountByCondition(MemberStatusLog tdMemberStatusLog) {
           return  this.tdMemberStatusLogMapper.selectCountByCondition(tdMemberStatusLog);
    }

}
