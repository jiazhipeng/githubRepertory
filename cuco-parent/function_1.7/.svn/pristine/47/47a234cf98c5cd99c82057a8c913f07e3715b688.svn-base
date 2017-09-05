package com.hy.gcar.service.memberAccountLog.impl;

import com.hy.gcar.dao.MemberAccountLogMapper;
import com.hy.gcar.entity.MemberAccountLog;
import com.hy.gcar.service.memberAccountLog.MemberAccountLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Service(value = "tdMemberAccountLogService")
public class MemberAccountLogServiceImpl implements MemberAccountLogService {

    @Autowired
    private MemberAccountLogMapper<MemberAccountLog>tdMemberAccountLogMapper;

    @Override
    public Integer insertSelective(MemberAccountLog tdMemberAccountLog) throws Exception {
        return this.tdMemberAccountLogMapper.insertSelective(tdMemberAccountLog);
    }

    @Override
    public Integer insertBatch(List<MemberAccountLog> tdMemberAccountLog) throws Exception {
        return this.tdMemberAccountLogMapper.insertBatch(tdMemberAccountLog) ;
    }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
        return this.tdMemberAccountLogMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
        return this.tdMemberAccountLogMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(MemberAccountLog tdMemberAccountLog) {
        return this.tdMemberAccountLogMapper.updateByPrimaryKeySelective(tdMemberAccountLog);
    }

    @Override
    public MemberAccountLog findById(Object id) {
        return (MemberAccountLog) this.tdMemberAccountLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MemberAccountLog> selectByCondition(MemberAccountLog tdMemberAccountLog) {
        return (List<MemberAccountLog>) this.tdMemberAccountLogMapper.selectByCondition(tdMemberAccountLog);
    }

    @Override
    public Integer selectCountByCondition(MemberAccountLog tdMemberAccountLog) {
        return  this.tdMemberAccountLogMapper.selectCountByCondition(tdMemberAccountLog);
    }

    @Override
    public MemberAccountLog createMemberAccountLog(MemberAccountLog tdMemberAccountLog) throws Exception {
        Assert.notNull(tdMemberAccountLog,"权益充值消费日志对象不能为空");
        tdMemberAccountLog.setCreated(new Date());
        this.insertSelective(tdMemberAccountLog);
        return tdMemberAccountLog;
    }

}
