package cn.cuco.dao;

import java.util.List;

import cn.cuco.entity.Supplier;

public interface SupplierMapper<T> extends BaseDao<T> {

	/**
	* @Title: selectByConditionByPage 
	* @Description: 分页
	* @author huanghua
	* @param supplier
	* @return
	* @return List<Supplier>
	 */
	public List<Supplier> selectByConditionByPage(Supplier supplier);
	
	/**
	* @Title: selectCountByConditionByPage 
	* @Description: 数量
	* @author huanghua
	* @param supplier
	* @return
	* @return Integer
	 */
	public Integer selectCountByConditionByPage(Supplier supplier);
}
