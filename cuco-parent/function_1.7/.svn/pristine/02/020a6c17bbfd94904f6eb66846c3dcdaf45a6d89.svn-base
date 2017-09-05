package com.hy.gcar.service.channel.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.ChannelDetailMapper;
import com.hy.gcar.entity.ChannelDetail;
import com.hy.gcar.service.channel.ChannelDetailService;

@Service(value = "tdChannelDetailService")
public class ChannelDetailServiceImpl implements ChannelDetailService {

    @Autowired
    private ChannelDetailMapper<ChannelDetail>tdChannelDetailMapper;
    
    @Override
    public Integer insertSelective(ChannelDetail tdChannelDetail) throws Exception {
           return this.tdChannelDetailMapper.insertSelective(tdChannelDetail);
        }

    @Override
    public Integer insertBatch(List<ChannelDetail> tdChannelDetail) throws Exception {
           return this.tdChannelDetailMapper.insertBatch(tdChannelDetail) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tdChannelDetailMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tdChannelDetailMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(ChannelDetail tdChannelDetail) {
           return this.tdChannelDetailMapper.updateByPrimaryKeySelective(tdChannelDetail);
    }

    @Override
    public ChannelDetail findById(Object id) {
           return (ChannelDetail) this.tdChannelDetailMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ChannelDetail> selectByCondition(ChannelDetail tdChannelDetail) {
           return (List<ChannelDetail>) this.tdChannelDetailMapper.selectByCondition(tdChannelDetail);
    }

    @Override
    public Integer selectCountByCondition(ChannelDetail tdChannelDetail) {
           return  this.tdChannelDetailMapper.selectCountByCondition(tdChannelDetail);
    }

}
