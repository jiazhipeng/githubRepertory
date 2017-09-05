package cn.cuco.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.cuco.entity.PreAuthorizedDebit;

public interface PreAuthorizedDebitMapper<T> extends BaseDao<T> {

	/**
	 * @Title: selectByConditionByPage
	 * @Description: 数据
	 * @author huanghua
	 * @param preAuthorizedDebit
	 * @return List<PreAuthorizedDebit>
	 */
	public List<PreAuthorizedDebit> selectByConditionByPage(PreAuthorizedDebit preAuthorizedDebit);

	/**
	 * @Title: selectCountByConditionByPage
	 * @Description: 数量
	 * @author huanghua
	 * @param preAuthorizedDebit
	 * @return
	 * @return Integer
	 */
	public Integer selectCountByConditionByPage(PreAuthorizedDebit preAuthorizedDebit);

	/**
	 * @Title: getTotalFrozenMoneyByMemberId
	 * @Description: 根据用户ID获取冻结金额总和
	 * @author zc.du
	 * @param memberId
	 * @return BigDecimal
	 */
	public BigDecimal getTotalFrozenMoneyByMemberId(@Param("memberId")Long memberId);
	
	/**   
	 * @Title: getPreAuthorizedDebitsOver15Days   
	 * @Description: 查询15日还没有解冻的类型为违章押金的预授权记录
	 * @param: @return      
	 * @return: List<PreAuthorizedDebit>       
	 */
	public List<PreAuthorizedDebit> getPreAuthorizedDebitsOver15Days();
}
