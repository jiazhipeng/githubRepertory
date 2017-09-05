package cn.cuco.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.cuco.entity.OrderRenewal;

public interface OrderRenewalMapper<T> extends BaseDao<T> {

	/**
	 * @Title: getOrderRenewalListWithSort
	 * @Description: 查询续费订单列表(带排序)
	 * @author zc.du
	 * @param orderRenewal
	 * @return List<OrderRenewal>
	 */
	public List<OrderRenewal> getOrderRenewalListWithSort(OrderRenewal orderRenewal);

	/**
	 * @Title: getOrderRenewalListForCancelForTask
	 * @Description: 查询需要进行“定时取消”的订单列表
	 * @author zc.du
	 * @return List<OrderRenewal>
	 */
	public List<OrderRenewal> getOrderRenewalListForCancelForTask();

	/**
	 * @Title: getUnFinishOrderRenewalListByMemberId
	 * @Description: 根据用户Id查询未完成的储值订单列表
	 * @author zc.du
	 * @param memberId
	 * @return List<OrderRenewal>
	 */
	public List<OrderRenewal> getUnFinishOrderRenewalListByMemberId(@Param("memberId") Long memberId);

	/**
	 * @Title: getUnFinishOrderRenewalCountByMemberId
	 * @Description: 根据用户ID查询未完成的储值订单数量
	 * @author zc.du
	 * @param memberId
	 * @return List<OrderRenewal>
	 */
	public Integer getUnFinishOrderRenewalCountByMemberId(@Param("memberId") Long memberId);

	/**
	 * @Title: getOrderRenewalCountByFuzzy
	 * @Description: 模糊查询储值订单数量
	 * @author zc.du
	 * @param orderRenewal
	 * @return
	 * @return Integer
	 */
	public Integer getOrderRenewalCountByFuzzy(OrderRenewal orderRenewal);

	/**
	 * @Title: getOrderRenewalListByFuzzy
	 * @Description: 模糊查询储值订单列表
	 * @author zc.du
	 * @param member
	 * @return List<Member>
	 */
	public List<OrderRenewal> getOrderRenewalListByFuzzy(OrderRenewal orderRenewal);

	/**
	 * @Title: getCompletedPayOrderRenewalMoneyByDate
	 * @Description: 查询指定时间段内支付完成的储值订单金额
	 * @author zc.du
	 * @param start
	 * @param end
	 * @return BigDecimal
	 */
	public BigDecimal getCompletedPayOrderRenewalMoneyByDate(@Param("start") Date start, @Param("end") Date end);
}
