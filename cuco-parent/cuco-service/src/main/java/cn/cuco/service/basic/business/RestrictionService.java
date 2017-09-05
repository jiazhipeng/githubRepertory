package cn.cuco.service.basic.business;


import java.util.List;

import cn.cuco.entity.Restriction;

/**
* @ClassName: RestrictionService 
* @Description: 限号接口
* @author huanghua
* @date 2017年2月21日 下午2:26:27
 */
public interface RestrictionService {
	
	/**
	* @Title: createRestriction 
	* @Description: 创建-修改修改限号
	* @param restriction
	* @return Restriction
	 */
	public Restriction createRestriction(Restriction restriction);
	
	/**
	* @Title: getMouthRestriction 
	* @Description: 按开始-结束时间查询限号记录
	* @param restriction
	* @return List<Restriction>
	 */
	public List<Restriction> getMouthRestriction(Restriction restriction);
	
	/**
	* @Title: getRestrictionByDate 
	* @Description: 根据年月日-城市查询限号
	* @param restriction
	* @return List<Restriction>
	 */
	public Restriction getRestrictionByDate(Restriction restriction);
	
}
