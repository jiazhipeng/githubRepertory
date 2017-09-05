package cn.cuco.dao;

import java.util.List;

import cn.cuco.entity.CarAccident;

public interface CarAccidentMapper<T> extends BaseDao<T> {

	/**
	* @Title: selectByConditionByPage 
	* @Description: 分页
	* @author huanghua
	* @param carAccident
	* @return List<CarRepair>
	 */
	public List<CarAccident> selectByConditionByPage(CarAccident carAccident);
	
	/**
	* @Title: selectCountByConditionByPage 
	* @Description: 数量
	* @author huanghua
	* @param carAccident
	* @return Integer
	 */
	public Integer selectCountByConditionByPage(CarAccident carAccident);
}
