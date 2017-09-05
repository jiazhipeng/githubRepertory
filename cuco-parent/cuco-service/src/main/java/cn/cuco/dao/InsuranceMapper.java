package cn.cuco.dao;

import java.util.List;

import cn.cuco.entity.Insurance;

public interface InsuranceMapper<T> extends BaseDao<T> {

	
	/**
	* @Title: selectByConditionByPage 
	* @Description: 分页
	* @author huanghua
	* @param insurance
	* @return List<Insurance>
	 */
	public List<Insurance> selectByConditionByPage(Insurance insurance);
	
	/**
	* @Title: selectCountByConditionByPage 
	* @Description: 数量
	* @author huanghua
	* @param insurance
	* @return Integer
	 */
	public Integer selectCountByConditionByPage(Insurance insurance);
}
