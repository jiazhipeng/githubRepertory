package com.hy.gcar.service.nation;

import java.util.List;

import com.hy.gcar.entity.Nation;

public interface NationService {
	
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
	 * 根据城市名称获取城市信息
	* @Title: getNationByCity 
	* @Description: TODO
	* @author q.p.x
	* @param @param nation
	* @param @return    
	* @return List<Nation>    
	* @date 2016年5月10日 下午2:01:31 
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
	 * 根据code 查询code信息
	 * @param code
	 * @return
	 */
	public Nation getNationByCityCode(String code);
	
	
	/**
	 * 根据城市id 或者城市代码查询父级城市
	 * @param nation
	 * @return
	 */
	public Nation getParentNationByCityeCodeOrId(Nation nation);

	
	
	

}
