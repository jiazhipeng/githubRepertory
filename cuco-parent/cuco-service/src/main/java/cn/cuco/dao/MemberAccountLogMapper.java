package cn.cuco.dao;

import java.math.BigDecimal;
import java.util.List;

import cn.cuco.entity.MemberAccountLog;

public interface MemberAccountLogMapper<T> extends BaseDao<T> {

	/**
	 * @Title: getALlCostByMemberId
	 * @Description: 根据用户ID获取用户消费总额
	 * @author zc.du
	 * @param memberId
	 * @return BigDecimal
	 */
	public BigDecimal getALlCostByMemberId(Long memberId);

	/**
	 * @Title: getMemberAccountLogListWithSort
	 * @Description: 获取用户账户明细列表(带排序)
	 * @author zc.du
	 * @param memberAccountLog
	 * @return List<MemberAccountLog>
	 */
	public List<MemberAccountLog> getMemberAccountLogListWithSort(MemberAccountLog memberAccountLog);
	
	/** 
	* @Title: getMemberAccountLogCount 
	* @Description: 获取用户账户明细(余额)记录数量
	* @author zc.du
	* @param memberAccountLog
	* @return Integer
	*/
	public Integer getMemberAccountLogCount(MemberAccountLog memberAccountLog);
}
