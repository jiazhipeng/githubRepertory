package com.hy.gcar.dao;

import java.util.List;

import com.hy.gcar.entity.Nation;

public interface NationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Nation record);

    int insertSelective(Nation record);

    Nation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Nation record);

    int updateByPrimaryKey(Nation record);
    
	/**
	* 根据 pid 的集合获取市和县的集合  
	* @Title: getNationByParentID 
	* @Description: TODO
	* @author q.p.x
	* @param @return    
	* @return List<Nation>    
	* @date 2016年4月22日 下午7:20:36 
	* @throws
	 */
	public List<Nation> getNationByParentID(Nation nation);

	/**
	* 获取省的集合 
	* @Title: getNationProvince 
	* @Description: TODO
	* @author q.p.x
	* @param @param nid
	* @param @return    
	* @return List<Nation>    
	* @date 2016年4月22日 下午9:30:38 
	* @throws
	 */
	public List<Nation> getNationProvince();
	
	/**
	 * 根据城市名称获取城市信息
	* @Title: getNationByCity 
	* @Description: TODO
	* @author q.p.x
	* @param @return    
	* @return List<Nation>    
	* @date 2016年5月10日 下午2:02:56 
	* @throws
	 */
	public List<Nation> getNationByCity(Nation nation);
	
	/**
	 * 根据父id 获取  省
	 * @param id
	 * @return
	 */
	public List<Nation> selectByParent(Integer id);
	
	/**
	 * 根据城市code 查询城市的集合
	 * @param code
	 * @return
	 */
	public List<Nation> getNationByCityCode(String code);
	
	/**
	 * 根据cityCode 或者 cityID 或者CityName 查询父级城市
	 * @param code
	 * @return
	 */
	public List<Nation> getParentNationByCityeCodeOrId(Nation nation);
}