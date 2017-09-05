package com.hy.gcar.service.selles.order;

import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.Order;

/**
 * 销售意向Service
 * @author 海易出行
 *
 */
public interface SellesOrderService {
	
	/**
	 * 创建销售订单
	 * @param order
	 * @return
	 */
	public Order createSellesOrder(Order order,Member member) throws Exception;
}
