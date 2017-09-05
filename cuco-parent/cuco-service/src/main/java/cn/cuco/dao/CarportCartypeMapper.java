package cn.cuco.dao;

import cn.cuco.entity.CarportCartype;

public interface CarportCartypeMapper<T> extends BaseDao<T> {

	/**
	* @Title: deleteCarportCartypeByCarprotId 
	* @Description: 根据车库删除所有车型
	* @author huanghua
	* @param carportCartype
	* @return
	* @return Integer
	 */
	public Integer deleteCarportCartypeByCarprotId(CarportCartype carportCartype);
}
