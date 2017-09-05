package cn.cuco.service.basic.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cuco.dao.SecurityIntervalMapper;
import cn.cuco.entity.SecurityInterval;
import cn.cuco.service.basic.business.SecurityIntervalService;

@Service(value = "securityIntervalService")
public class SecurityIntervalServiceImpl implements SecurityIntervalService {

    @Autowired
    private SecurityIntervalMapper<SecurityInterval> securityIntervalMapper;

    /**
     * 取安全区间信息
     */
	@Override
	public SecurityInterval getSecurityIntervalByCreatedDesc() {
		List<SecurityInterval> securityIntervals = this.securityIntervalMapper.getSecurityIntervalByCreatedDesc();
		if(securityIntervals.isEmpty()){
			return null;
		}
		return securityIntervals.get(0);
	}

    
  

}
