package com.hy.security.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.security.dao.UserCityMapper;
import com.hy.security.entity.UserCity;
import com.hy.security.service.user.UserCityService;

/**
 * Created by WangShuai on 2016/7/22.
 */
@Service
public class UserCityServiceImpl implements UserCityService {

    @Autowired
    private UserCityMapper<UserCity>tdUserMapper;
    

    @Override
    public Integer insertBatch(List<UserCity> tdUser){
           return this.tdUserMapper.insertBatch(tdUser) ;
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tdUserMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public List<UserCity> selectByCondition(UserCity tdUser) {
          return (List<UserCity>) this.tdUserMapper.selectByCondition(tdUser);
    }
   
    @Override
    public void deleteCityByUser(UserCity userCity){
    	 this.tdUserMapper.deleteCityByUser(userCity);
    }
}
