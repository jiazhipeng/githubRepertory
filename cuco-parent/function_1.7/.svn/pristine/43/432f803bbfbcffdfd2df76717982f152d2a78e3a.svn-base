package com.hy.gcar.dao;

import java.util.List;

import com.hy.gcar.entity.PowerUsed;

public interface PowerUsedMapper<T> extends BaseDao<T> {
	/**
	 * 查询正在使用的车型记录
	 * @param t
	 * @return
	 */
	List<PowerUsed> selectPowerUsedOfUseingCars(PowerUsed t);

	List<PowerUsed> selectByPageList(PowerUsed powerUsed);
	
	/**
	* @Title: selectPowerUsedListOfStatus 
	* @Description: TODO(查询当前权益下所有待送车 、使用中、已完成、已取消) 
	* @param @param t
	* @param @return    设定文件 
	* @return List<PowerUsed>    返回类型 
	* @throws
	 */
	List<PowerUsed>  selectPowerUsedListOfStatus(PowerUsed t);
	
	/**
	 * 
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-30 下午4:40:44   
	* @return String
	 */
	public List<PowerUsed> selectByMemBerIdItemIdCarId(PowerUsed t);
	
	/**
	 * 通过用户ID查询用车记录
	 * @param t
	 * @return
	 */
	public List<PowerUsed> selectpowerUsedByCompleted(PowerUsed t);
	/**
	 * 通过用户ID查询未完成的用车记录
	 * @param t
	 * @return
	 */
	public List<PowerUsed> selectpowerUsedByNoCompleted(PowerUsed t);
	/**
	 * 并发防止重复提交
	 * @param powerUsed
	 * @return
	 */
	public Integer insertIntoSelective(PowerUsed powerUsed);
}
