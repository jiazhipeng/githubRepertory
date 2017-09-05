package com.hy.security.service.nation.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.security.dao.NationMapper;
import com.hy.security.entity.Nation;
import com.hy.security.service.nation.NationService;

@Service
public class NationServiceImpl implements NationService {

    @Autowired
    private NationMapper<Nation>tcNationMapper;
    
    @Override
    public Integer insertSelective(Nation tcNation) throws Exception {
           return this.tcNationMapper.insertSelective(tcNation);
        }

    @Override
    public Integer insertBatch(List<Nation> tcNation) throws Exception {
           return this.tcNationMapper.insertBatch(tcNation) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tcNationMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tcNationMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(Nation tcNation) {
           return this.tcNationMapper.updateByPrimaryKeySelective(tcNation);
    }

    @Override
    public Nation findById(Long id) {
           return (Nation) this.tcNationMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Nation> selectByCondition(Nation tcNation) {
           return (List<Nation>) this.tcNationMapper.selectByCondition(tcNation);
    }

    @Override
    public Integer selectCountByCondition(Nation tcNation) {
           return  this.tcNationMapper.selectCountByCondition(tcNation);
    }

    /**
     * 查询所有城市
     */
    public List<Nation> selectAllCity(Nation nation){
    	return this.tcNationMapper.selectAllCity(nation);
    }
}
