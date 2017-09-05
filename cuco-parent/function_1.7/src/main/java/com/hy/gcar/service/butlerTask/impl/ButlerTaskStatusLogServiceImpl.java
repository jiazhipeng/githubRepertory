package com.hy.gcar.service.butlerTask.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.dao.ButlerTaskStatusLogMapper;
import com.hy.gcar.entity.ButlerTaskStatusLog;
import com.hy.gcar.service.butlerTask.ButlerTaskStatusLogService;

@Service
public class ButlerTaskStatusLogServiceImpl implements ButlerTaskStatusLogService {

    @Autowired
    private ButlerTaskStatusLogMapper<ButlerTaskStatusLog> butlerTaskStatusLogMapper;
   
    protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ButlerTaskStatusLog createButlerTaskStatusLog(ButlerTaskStatusLog butlerTaskStatusLog) {
		butlerTaskStatusLog.setCreated(new Date());
		this.butlerTaskStatusLogMapper.insertSelective(butlerTaskStatusLog);
		return butlerTaskStatusLogMapper.selectByPrimaryKey(butlerTaskStatusLog.getId());
	}

	@Override
	public List<ButlerTaskStatusLog> getButlerTaskStatusLogList(ButlerTaskStatusLog butlerTaskStatusLog) {
		List<ButlerTaskStatusLog> butlerTaskStatusLogList = this.butlerTaskStatusLogMapper.selectByCondition(butlerTaskStatusLog);
		return butlerTaskStatusLogList;
	}
    
}
