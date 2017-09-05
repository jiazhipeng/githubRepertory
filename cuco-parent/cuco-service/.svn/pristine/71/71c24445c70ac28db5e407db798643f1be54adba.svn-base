package cn.cuco.dao;

import java.util.List;

import cn.cuco.entity.CarType;

public interface CarTypeMapper<T> extends BaseDao<T> {

	/**
	* @Title: selectByConditionByPage 
	* @Description: 分页
	* @author huanghua
	* @param carType
	* @return
	* @return List<CarType>
	 */
	public List<CarType> selectByConditionByPage(CarType carType);
	
	/**
	* @Title: selectCountByConditionByPage 
	* @Description: 车型数量
	* @author huanghua
	* @param carType
	* @return
	* @return Integer
	 */
	public Integer selectCountByConditionByPage(CarType carType);
	
	/**
	* @Title: selectByConditionByUpAsc 
	* @Description: 车型价格-时间排序
	* @author huanghua
	* @param carType
	* @return List<CarType>
	 */
	public List<CarType> selectByConditionByUpAsc(CarType carType);
	
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
	public List<CarType> selectCartypeByBrand(CarType carType);
	
}
