package cn.cuco.dao;

import java.util.List;

import cn.cuco.entity.City;

public interface CityMapper<T> extends BaseDao<T> {
	/**
	* @Title: getCityByParentID 
	* @Description: 根据 pid 的集合获取市和县的集合  
	* @author huanghua
	* @param city
	* @return List<City>
	 */
	public List<City> getCityByParentID(City city);
	
	/**
	* @Title: selectParent 
	* @Description: 查询父City
	* @author huanghua
	* @param city
	* @return City
	 */
	public City selectParent(City city);
	
	/**
	* @Title: selectByParent 
	* @Description: 根据父id 获取  省
	* @author huanghua
	* @param id
	* @return
	* @return List<City>
	 */
	public List<City> selectByParent(Long id);
	
	/**
	 * 根据城市code 查询城市的集合
	 * @param code
	 * @return
	 */
	public List<City> getCityByCityCode(String code);
}
