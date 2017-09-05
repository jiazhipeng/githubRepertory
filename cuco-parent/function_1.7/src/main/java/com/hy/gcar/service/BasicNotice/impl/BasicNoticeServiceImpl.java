package com.hy.gcar.service.BasicNotice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.BasicNoticeMapper;
import com.hy.gcar.entity.BasicNotice;
import com.hy.gcar.service.BasicNotice.BasicNoticeService;

@Service(value = "tcBasicNoticeService")
public class BasicNoticeServiceImpl implements BasicNoticeService {

    @Autowired
    private BasicNoticeMapper<BasicNotice>tcBasicNoticeMapper;
    
    @Override
    public Integer insertSelective(BasicNotice tcBasicNotice) throws Exception {
           return this.tcBasicNoticeMapper.insertSelective(tcBasicNotice);
        }

    @Override
    public Integer insertBatch(List<BasicNotice> tcBasicNotice) throws Exception {
           return this.tcBasicNoticeMapper.insertBatch(tcBasicNotice) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tcBasicNoticeMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tcBasicNoticeMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(BasicNotice tcBasicNotice) {
           return this.tcBasicNoticeMapper.updateByPrimaryKeySelective(tcBasicNotice);
    }

    @Override
    public BasicNotice findById(Object id) {
           return (BasicNotice) this.tcBasicNoticeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<BasicNotice> selectByCondition(BasicNotice tcBasicNotice) {
           return (List<BasicNotice>) this.tcBasicNoticeMapper.selectByCondition(tcBasicNotice);
    }

    @Override
    public Integer selectCountByCondition(BasicNotice tcBasicNotice) {
           return  this.tcBasicNoticeMapper.selectCountByCondition(tcBasicNotice);
    }

}
