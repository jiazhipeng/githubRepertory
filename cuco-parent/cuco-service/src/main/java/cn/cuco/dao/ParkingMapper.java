package cn.cuco.dao;

import java.util.List;

import cn.cuco.entity.Parking;

public interface ParkingMapper<T> extends BaseDao<T> {

	/**
	* @Title: selectByConditionByPage 
	* @Description:分页查询停车场
	* @author huanghua
	* @param parking
	* @return List<Parking>
	 */
	public List<Parking> selectByConditionByPage(Parking parking);
	
	/**
	* @Title: selectCountByConditionByPage 
	* @Description: 查询停车场总条数
	* @author huanghua
	* @param parking
	* @return Integer
	 */
	public Integer selectCountByConditionByPage(Parking parking);
}
