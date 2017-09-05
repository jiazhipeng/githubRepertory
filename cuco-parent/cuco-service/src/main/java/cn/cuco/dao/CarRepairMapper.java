package cn.cuco.dao;

import java.util.List;

import cn.cuco.entity.CarRepair;

public interface CarRepairMapper<T> extends BaseDao<T> {

	/**
	* @Title: selectByConditionByPage 
	* @Description: 分页
	* @author huanghua
	* @param carRepair
	* @return List<CarRepair>
	 */
	public List<CarRepair> selectByConditionByPage(CarRepair carRepair);
	
	/**
	* @Title: selectCountByConditionByPage 
	* @Description: 数量
	* @author huanghua
	* @param carRepair
	* @return Integer
	 */
	public Integer selectCountByConditionByPage(CarRepair carRepair);
}
