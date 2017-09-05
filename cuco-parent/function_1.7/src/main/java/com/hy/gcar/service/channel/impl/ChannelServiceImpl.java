package com.hy.gcar.service.channel.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.ChannelMapper;
import com.hy.gcar.entity.Channel;
import com.hy.gcar.service.channel.ChannelService;

@Service(value = "tdChannelService")
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelMapper<Channel>tdChannelMapper;
    
    @Override
    public Integer insertSelective(Channel tdChannel) throws Exception {
           return this.tdChannelMapper.insertSelective(tdChannel);
        }

    @Override
    public Integer insertBatch(List<Channel> tdChannel) throws Exception {
           return this.tdChannelMapper.insertBatch(tdChannel) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tdChannelMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tdChannelMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(Channel tdChannel) {
           return this.tdChannelMapper.updateByPrimaryKeySelective(tdChannel);
    }

    @Override
    public Channel findById(Object id) {
           return (Channel) this.tdChannelMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Channel> selectByCondition(Channel tdChannel) {
           return (List<Channel>) this.tdChannelMapper.selectByCondition(tdChannel);
    }

    @Override
    public Integer selectCountByCondition(Channel tdChannel) {
           return  this.tdChannelMapper.selectCountByCondition(tdChannel);
    }

}
