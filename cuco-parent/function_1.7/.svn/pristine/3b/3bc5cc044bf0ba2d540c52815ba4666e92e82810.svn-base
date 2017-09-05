package com.hy.gcar.dao;

import java.util.List;

import com.hy.gcar.entity.Order;

public interface OrderMapper<T> extends BaseDao<T> {
	
	public List<Order> selectOrderListByNoComplete(Order order);
	
	public List<Order> selectOrderListByComplete(Order order);
	
	public List<Order> findOrderListByFuzzySearch(Order order);
}
