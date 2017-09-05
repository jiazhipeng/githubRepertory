package com.hy.gcar.service.bank.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.BankMapper;
import com.hy.gcar.entity.Bank;
import com.hy.gcar.service.bank.BankService;

@Service(value = "tcBankService")
public class BankServiceImpl implements BankService {

    @Autowired
    private BankMapper<Bank>tcBankMapper;
    
    @Override
    public Integer insertSelective(Bank tcBank) throws Exception {
           return this.tcBankMapper.insertSelective(tcBank);
        }

    @Override
    public Integer insertBatch(List<Bank> tcBank) throws Exception {
           return this.tcBankMapper.insertBatch(tcBank) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tcBankMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tcBankMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(Bank tcBank) {
           return this.tcBankMapper.updateByPrimaryKeySelective(tcBank);
    }

    @Override
    public Bank findById(Object id) {
           return (Bank) this.tcBankMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Bank> selectByCondition(Bank tcBank) {
           return (List<Bank>) this.tcBankMapper.selectByCondition(tcBank);
    }

    @Override
    public Integer selectCountByCondition(Bank tcBank) {
           return  this.tcBankMapper.selectCountByCondition(tcBank);
    }

}
