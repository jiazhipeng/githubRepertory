package com.hy.gcar.service.memberBank.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.MemberBankMapper;
import com.hy.gcar.entity.MemberBank;
import com.hy.gcar.service.memberBank.MemberBankService;

@Service(value = "tdMemberBankService")
public class MemberBankServiceImpl implements MemberBankService {

    @Autowired
    private MemberBankMapper<MemberBank>tdMemberBankMapper;
    
    @Override
    public Integer insertSelective(MemberBank tdMemberBank) throws Exception {
           return this.tdMemberBankMapper.insertSelective(tdMemberBank);
        }

    @Override
    public Integer insertBatch(List<MemberBank> tdMemberBank) throws Exception {
           return this.tdMemberBankMapper.insertBatch(tdMemberBank) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tdMemberBankMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tdMemberBankMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(MemberBank tdMemberBank) {
           return this.tdMemberBankMapper.updateByPrimaryKeySelective(tdMemberBank);
    }

    @Override
    public MemberBank findById(Object id) {
           return (MemberBank) this.tdMemberBankMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MemberBank> selectByCondition(MemberBank tdMemberBank) {
           return (List<MemberBank>) this.tdMemberBankMapper.selectByCondition(tdMemberBank);
    }

    @Override
    public Integer selectCountByCondition(MemberBank tdMemberBank) {
           return  this.tdMemberBankMapper.selectCountByCondition(tdMemberBank);
    }

}
