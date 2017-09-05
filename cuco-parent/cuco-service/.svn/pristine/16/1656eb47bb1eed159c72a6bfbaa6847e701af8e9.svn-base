package cn.cuco.dao;

import java.util.List;

import cn.cuco.entity.CarBrand;

public interface CarBrandMapper<T> extends BaseDao<T> {

	/**
	* @Title: selectCarBrandForAll 
	* @Description: 去重查询所有车品牌
	* @author huanghua
	* @return List<String>
	 */
	public List<String> selectCarBrandForAll();

	/**
	* @Title: selectCartypeByBrand 
	* @Description: 根据车品牌查询所有车型
	* @author huanghua
	* @param carType
	* @return List<CarType>
	 */
	public List<CarBrand> selectCartypeByBrand(CarBrand carBrand);
}
