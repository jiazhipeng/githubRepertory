package com.hy.gcar.service.carType.impl;

import java.util.List;

import com.hy.gcar.entity.MemberItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.MemberItemCartypeMapper;
import com.hy.gcar.entity.MemberItemCartype;
import com.hy.gcar.entity.PowerUsed;
import com.hy.gcar.service.carType.MemberItemCartypeService;

@Service(value = "tdMemberItemCartypeService")
public class MemberItemCartypeServiceImpl implements MemberItemCartypeService {

    @Autowired
    private MemberItemCartypeMapper<MemberItemCartype>memberItemCartypeMapper;
    
    @Override
    public Integer insertSelective(MemberItemCartype tdMemberItemCartype) throws Exception {
           return this.memberItemCartypeMapper.insertSelective(tdMemberItemCartype);
        }

    @Override
    public Integer insertBatch(List<MemberItemCartype> tdMemberItemCartype) throws Exception {
           return this.memberItemCartypeMapper.insertBatch(tdMemberItemCartype) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.memberItemCartypeMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.memberItemCartypeMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(MemberItemCartype tdMemberItemCartype) {
           return this.memberItemCartypeMapper.updateByPrimaryKeySelective(tdMemberItemCartype);
    }

    @Override
    public MemberItemCartype findById(Object id) {
           return (MemberItemCartype) this.memberItemCartypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MemberItemCartype> selectByCondition(MemberItemCartype tdMemberItemCartype) {
           return (List<MemberItemCartype>) this.memberItemCartypeMapper.selectByCondition(tdMemberItemCartype);
    }

    @Override
    public Integer selectCountByCondition(MemberItemCartype tdMemberItemCartype) {
           return  this.memberItemCartypeMapper.selectCountByCondition(tdMemberItemCartype);
    }

	@Override
	public Integer deleteByMemberItemId(MemberItemCartype memberItemCartype) {
		
		return this.memberItemCartypeMapper.deleteByMemberItemId(memberItemCartype);
	}

	@Override
	public void updateMemberItemCartype(PowerUsed powerUsed) {
		 this.memberItemCartypeMapper.updateMemberItemCartype(powerUsed);
	}
	/**
	 * 修改我的启用车型为 已经发起用车
	 * @param powerUsed
	 */
	@Override
	public void updateMyCarTypeStatus(PowerUsed powerUsed) {
		 this.memberItemCartypeMapper.updateMyCarTypeStatus(powerUsed);
	}

    @Override
    public List<MemberItemCartype> findEnableAndNOnEnableCarType(MemberItem memberItem) {
        return memberItemCartypeMapper.findEnableAndNOnEnableCarType(memberItem);
    }

    @Override
    public List<MemberItemCartype> findEnableCarType(MemberItem memberItem) {
        return memberItemCartypeMapper.findEnableCarType(memberItem);
    }

	@Override
	public void updateMemberItemCartypeByGetCarComplete(MemberItemCartype memberItemCartype) {
		 memberItemCartypeMapper.updateMemberItemCartypeByGetCarComplete(memberItemCartype);
	}

}
