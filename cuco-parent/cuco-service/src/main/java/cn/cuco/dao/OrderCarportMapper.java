package cn.cuco.dao;

import java.util.List;
import cn.cuco.entity.OrderCarport;

public interface OrderCarportMapper<T> extends BaseDao<T> {
	
	/** 
	* @Title: getOrderCarportCount 
	* @Description: 查询解锁订单数量
	* @author zc.du
	* @param orderCarport
	* @return Integer
	*/
	public Integer getOrderCarportCount(OrderCarport orderCarport);
	/** 
	* @Title: getOrderCarportListWithSort 
	* @Description: 查询解锁订单列表(带排序)
	* @author zc.du
	* @param orderCarport
	* @return List<OrderCarport>
	*/
	public List<OrderCarport> getOrderCarportListWithSort(OrderCarport orderCarport);
	
	/** 
	* @Title: getUnfinishOrderCarportList 
	* @Description: 获取未完成的解锁订单列表
	* @author zc.du
	* @param orderCarport
	* @return List<OrderCarport>
	*/
	public List<OrderCarport> getUnfinishOrderCarportList(OrderCarport orderCarport);
	
	/** 
	* @Title: getUncancelOrderCarportList 
	* @Description: 获取非取消状态的解锁订单列表
	* @author zc.du
	* @param orderCarport
	* @return List<OrderCarport>
	*/
	public List<OrderCarport> getUncancelOrderCarportList(OrderCarport orderCarport);
}
