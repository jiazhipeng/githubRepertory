package com.hy.security.service.permissionExchange.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.security.dao.PermissionExchangeMapper;
import com.hy.security.entity.PermissionExchange;
import com.hy.security.service.permissionExchange.PermissionExchangeService;

@Service
public class PermissionExchangeServiceImpl implements PermissionExchangeService {

    @Autowired
    private PermissionExchangeMapper<PermissionExchange> permissionExchangeMapper;
    
    @Override
    public Integer insertSelective(PermissionExchange tdPermissionExchange) throws Exception {
           return this.permissionExchangeMapper.insertSelective(tdPermissionExchange);
        }

    @Override
    public Integer insertBatch(List<PermissionExchange> tdPermissionExchange) throws Exception {
           return this.permissionExchangeMapper.insertBatch(tdPermissionExchange) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.permissionExchangeMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.permissionExchangeMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(PermissionExchange tdPermissionExchange) {
           return this.permissionExchangeMapper.updateByPrimaryKeySelective(tdPermissionExchange);
    }

    @Override
    public PermissionExchange findById(Object id) {
           return (PermissionExchange) this.permissionExchangeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PermissionExchange> selectByCondition(PermissionExchange tdPermissionExchange) {
           return (List<PermissionExchange>) this.permissionExchangeMapper.selectByCondition(tdPermissionExchange);
    }

    @Override
    public Integer selectCountByCondition(PermissionExchange tdPermissionExchange) {
           return  this.permissionExchangeMapper.selectCountByCondition(tdPermissionExchange);
    }

	@Override
	public void updateBatch(List<PermissionExchange> list) {
		// TODO Auto-generated method stub
		permissionExchangeMapper.updateBatch(list);
	}

	@Override
	public List<PermissionExchange> findSystemIds() {
		// TODO Auto-generated method stub
		return permissionExchangeMapper.findSystemIds();
	}

	@Override
	public List<PermissionExchange> selectNotSendExchange(PermissionExchange exchange) {
		// TODO Auto-generated method stub
		return permissionExchangeMapper.selectNotSendExchange(exchange);
	}

}
