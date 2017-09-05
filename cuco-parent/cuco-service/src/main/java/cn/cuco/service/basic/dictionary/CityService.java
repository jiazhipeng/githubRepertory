package cn.cuco.service.basic.dictionary;


import cn.cuco.entity.City;
import cn.cuco.page.PageResult;

import java.util.List;

/**
* @ClassName: CityService 
* @Description: 城市接口
* @author huanghua
* @date 2017年2月21日 下午3:03:05
 */
public interface CityService {
	
	/**
	* @Title: getCityPage 
	* @Description: 分页查询省市地区
	* @author huanghua
	* @param city
	* @return PageResult<City>
	 */
	public PageResult<City> getCityPage(City city);
	
	/**
	* @Title: getCityById 
	* @Description: 根据ID查询省市地区
	* @author huanghua
	* @param id
	* @return City
	 */
	public City getCityById(Long id);
	
	/**
	* @Title: getCityByParent 
	* @Description: 根据父id获取城市信息
	* @author huanghua
	* @param id
	* @return
	* @return List<City>
	 */
	public List<City> getCityByParent(Long id);
	
	/**
	* @Title: getCityByCityCode 
	* @Description: 根据code查询城市信息
	* @author huanghua
	* @param code
	* @return
	* @return List<City>
	 */
	public List<City> getCityByCityCode(String code);
	
	/**
	* @Title: getParentCity 
	* @Description: 查询父级city
	* @author huanghua
	* @param city
	* @return City
	 */
	public City getParentCity(City city);
	
	
	/**根据 pid 的集合获取市和县的集合
	 * @param nation
	 * @return
	 */
	public List<City> getCityByParentID(City city);

}
