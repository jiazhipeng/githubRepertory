package com.hy.security.service.LoginLog.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.security.dao.LoginLogMapper;
import com.hy.security.entity.LoginLog;
import com.hy.security.service.LoginLog.LoginLogService;

@Service(value = "tdLoginLogService")
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogMapper<LoginLog> tdLoginLogMapper;
    
    @Override
    public Integer insertSelective(LoginLog tdLoginLog) throws Exception {
           return this.tdLoginLogMapper.insertSelective(tdLoginLog);
        }

    @Override
    public Integer insertBatch(List<LoginLog> tdLoginLog) throws Exception {
           return this.tdLoginLogMapper.insertBatch(tdLoginLog) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tdLoginLogMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tdLoginLogMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(LoginLog tdLoginLog) {
           return this.tdLoginLogMapper.updateByPrimaryKeySelective(tdLoginLog);
    }

    @Override
    public LoginLog findById(Object id) {
           return (LoginLog) this.tdLoginLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<LoginLog> selectByCondition(LoginLog tdLoginLog) {
           return (List<LoginLog>) this.tdLoginLogMapper.selectByCondition(tdLoginLog);
    }

	@Override
	public List<LoginLog> selectLoginListByUserId(Long userId) {
		LoginLog loginLog = new LoginLog();
		loginLog.setUserId(userId);
		List<LoginLog> loginLogList = this.tdLoginLogMapper.selectLoginListByUserId(loginLog);
		return loginLogList;
	}

}
