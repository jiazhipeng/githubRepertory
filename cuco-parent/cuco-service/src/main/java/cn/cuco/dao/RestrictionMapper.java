package cn.cuco.dao;

import java.util.List;

import cn.cuco.entity.Restriction;

public interface RestrictionMapper<T> extends BaseDao<T> {
	
	
	/**根据开始日期和结束日期查询限号
	 * @param basicRestriction
	 * @return
	 */
	public List<Restriction> selectByRestrictionsDate(Restriction restriction);

	/**
	 * 根据年月日查询限号
	 * @param basicRestriction
	 * @return
	 */
	public List<Restriction> selectByDate(Restriction restriction);
}
