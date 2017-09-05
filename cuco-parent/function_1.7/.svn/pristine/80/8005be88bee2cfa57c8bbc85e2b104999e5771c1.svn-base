package com.hy.gcar.service.basicGarageImage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.BasicGarageImageMapper;
import com.hy.gcar.entity.BasicGarageImage;
import com.hy.gcar.service.basicGarageImage.BasicGarageImageService;

@Service(value = "tcBasicGarageImageService")
public class BasicGarageImageServiceImpl implements BasicGarageImageService {

    @Autowired
    private BasicGarageImageMapper<BasicGarageImage>tcBasicGarageImageMapper;
    
    @Override
    public Integer insertSelective(BasicGarageImage tcBasicGarageImage) throws Exception {
           return this.tcBasicGarageImageMapper.insertSelective(tcBasicGarageImage);
        }

    @Override
    public Integer insertBatch(List<BasicGarageImage> tcBasicGarageImage) throws Exception {
           return this.tcBasicGarageImageMapper.insertBatch(tcBasicGarageImage) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tcBasicGarageImageMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tcBasicGarageImageMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(BasicGarageImage tcBasicGarageImage) {
           return this.tcBasicGarageImageMapper.updateByPrimaryKeySelective(tcBasicGarageImage);
    }

    @Override
    public BasicGarageImage findById(Object id) {
           return (BasicGarageImage) this.tcBasicGarageImageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<BasicGarageImage> selectByCondition(BasicGarageImage tcBasicGarageImage) {
           return (List<BasicGarageImage>) this.tcBasicGarageImageMapper.selectByCondition(tcBasicGarageImage);
    }

    @Override
    public Integer selectCountByCondition(BasicGarageImage tcBasicGarageImage) {
           return  this.tcBasicGarageImageMapper.selectCountByCondition(tcBasicGarageImage);
    }

}
