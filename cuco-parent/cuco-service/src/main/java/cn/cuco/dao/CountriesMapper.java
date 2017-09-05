package cn.cuco.dao;

import java.util.List;

import cn.cuco.entity.Countries;

public interface CountriesMapper<T> extends BaseDao<T> {

	/**
	* @Title: selectByConditionByPage 
	* @Description: 分页
	* @author huanghua
	* @param countries
	* @return
	* @return List<Countries>
	 */
	public List<Countries> selectByConditionByPage(Countries countries);
	
	/**
	* @Title: selectCountByConditionByPage 
	* @Description: 数量
	* @author huanghua
	* @param countries
	* @return
	* @return Integer
	 */
	public Integer selectCountByConditionByPage(Countries countries);
}
