package com.hy.gcar.service.order;

import java.util.List;

import com.hy.gcar.entity.Order;
import com.hy.gcar.entity.OrderLog;

/**
 * @author gbw
 *
 */
public interface OrderService {
	/**
	 * 创建订单基础方法
	 * @param order
	 * @return
	 */
	public Order createOrder(Order order);
	
	/**
	 * 更新订单信息基础方法
	 * @param order
	 * @return
	 */
	public Order updateOrder(Order order);
	
	/**删除订单基础方法
	 * @param order
	 * @return
	 */
	public int deleteOrder(Order order);
	
	/**根据订单ID查询订单详情
	 * @param order
	 * @return
	 */
	public Order findOrderById(long id);
	
	/**
	 * 获取订单列表数据(支持根据负责人、状态、手机号、组织机构代码进行列表查询)
	 * @param order
	 * @return
	 */
	public List<Order> getOrderList(Order order);
	
	/**
	 * 修改订单状态为"已取消"
	 * @param order
	 * @return
	 */
	public Order updateOrderByCancle(Order order);
	
	/**
	 * 修改订单状态为"待签约"(审核通过之后,状态变为待签约)
	 * @param order
	 * @return
	 */
	public Order updateOrderStatusByWaitSign(Order order);
	
	/**
	 * 修改订单状态为"待付款"
	 * @param order
	 * @return
	 */
	public Order updateOrderStatusWaitPay(Order order);
	
	/**
	 * 修改订单状态为"待结款"
	 * @param order
	 * @return
	 * 1：创建订单日志记录表
	 * 2：修改订单结款信息
	 */
	public Order updateOrderStautsByPaying(Order order);
	
	/**
	 * 修改订单状态为"已结款"
	 * @param order
	 * @return
	 * 1：创建订单日志记录表
	 */
	public Order updateOrderStatusByComplete(Order order) throws Exception;
	
	/**
	 * 创建状态为"入会申请"的套餐(适用于前端的入会申请请求或者升级套餐请求)
	 * @param order
	 * @return
	 */
	public Order createOrderByApplyOrUpgrade(Order order);
	
	/**
	 * 根据会员ID查询该会员有没有未完成的订单(提交购买订单前的判断)
	 * @param order
	 * @return
	 */
	public List<Order> findOrderByNoComplete(Order order);
	/**
	 * 根据会员ID查询该会员有没有已完成的订单
	 * @param order
	 * @return
	 */
	public List<Order> findOrderByComplete(Order order);
	
	/**
	 * 修改订单信息业务接口(非状态的修改)
	 * @param order
	 * @return
	 */
	public Order updateOrderByNoStatus(Order order);
	
	/**
	 * 创建订单变更日志
	 * @param orderStatusLog
	 * @return
	 */
	public void createOrderStatusLog(OrderLog orderLog);
	
	/**订单列表根据手机号或者组织机构代码或者姓名模糊搜索
	 * @return
	 */
	public List<Order> findOrderListByFuzzySearch(Order order);
	
}
