package cn.cuco.dao;

import java.util.List;

import cn.cuco.entity.SecurityInterval;

public interface SecurityIntervalMapper<T> extends BaseDao<T> {

	/**
	* @Title: getSecurityIntervalByCreatedDesc 
	* @Description: 按时间倒叙取安全区间信息
	* @author huanghua
	* @return
	* @return List<SecurityInterval>
	 */
	public List<SecurityInterval> getSecurityIntervalByCreatedDesc();
}
