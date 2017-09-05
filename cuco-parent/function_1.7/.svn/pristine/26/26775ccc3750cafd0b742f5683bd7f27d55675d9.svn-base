package com.hy.gcar.service.getmoney.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.hy.gcar.dao.GetmoneyApplyMapper;
import com.hy.gcar.entity.GetmoneyApply;
import com.hy.gcar.service.getmoney.GetmoneyApplyService;

@Service(value = "tdGetmoneyApplyService")
public class GetmoneyApplyServiceImpl implements GetmoneyApplyService {

    @Autowired
    private GetmoneyApplyMapper<GetmoneyApply>getmoneyApplyMapper;
    
    @Override
    public Integer insertSelective(GetmoneyApply tdGetmoneyApply) throws Exception {
           return this.getmoneyApplyMapper.insertSelective(tdGetmoneyApply);
        }

    @Override
    public Integer insertBatch(List<GetmoneyApply> tdGetmoneyApply) throws Exception {
           return this.getmoneyApplyMapper.insertBatch(tdGetmoneyApply) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.getmoneyApplyMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.getmoneyApplyMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(GetmoneyApply tdGetmoneyApply) {
           return this.getmoneyApplyMapper.updateByPrimaryKeySelective(tdGetmoneyApply);
    }

    @Override
    public GetmoneyApply findById(Object id) {
           return (GetmoneyApply) this.getmoneyApplyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GetmoneyApply> selectByCondition(GetmoneyApply tdGetmoneyApply) {
           return (List<GetmoneyApply>) this.getmoneyApplyMapper.selectByCondition(tdGetmoneyApply);
    }

    @Override
    public Integer selectCountByCondition(GetmoneyApply tdGetmoneyApply) {
           return  this.getmoneyApplyMapper.selectCountByCondition(tdGetmoneyApply);
    }

	@Override
	public List<GetmoneyApply> selectMoneyApplyOfUnfinished(GetmoneyApply getmoneyApply) {
		   Assert.notNull(getmoneyApply.getMemberItemId() ,"参数错误，没有传入MemberItemId！");
		  return  this.getmoneyApplyMapper.selectMoneyApplyOfUnfinished(getmoneyApply);
	}

}
