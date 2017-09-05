package cn.cuco.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.cuco.entity.OrderCarUsed;

public interface OrderCarUsedMapper<T> extends BaseDao<T> {
	
	/** 
	* @Title: getOrderCarUsedCountByFuzzy 
	* @Description: 模糊查询用车结算
	* @author zc.du
	* @param orderCarUsed
	* @return Integer
	*/
	public Integer getOrderCarUsedCountByFuzzy(OrderCarUsed orderCarUsed);

	/** 
	* @Title: getMemberListByFuzzy 
	* @Description: 模糊查询用车结算
	* @author zc.du
	* @param orderCarUsed
	* @return List<OrderCarUsed>
	*/
	public List<OrderCarUsed> getOrderCarUsedListByFuzzy(OrderCarUsed orderCarUsed);
	

	/** 
	* @Title: getMemberDriveCountByMileage 
	* @Description: 根据公里数统计用户用车结算次数
	* @author zc.du
	* @param memberId 用户ID
	* @param min 最小公里数
	* @param max 最大公里数
	* @return Integer
	*/
	public Integer getMemberDriveCountByMileage(@Param("memberId") Long memberId,@Param("min") Integer min,@Param("max") Integer max);

	/**   
	 * @Title: getOrderCarUsedCount   
	 * @Description: 根据两个时间查询结算的次数 
	 * @param: @param orderCarUsed
	 * @param: @return      
	 * @return: int      
	 */
	public int getOrderCarUsedCount(OrderCarUsed orderCarUsed);
	
	/** 
    * @Title: getOrderCarUsedMoney 
    * @Description: 获取两个时间之间的结费金额(支持根据车型、车库ID、用车类型[会员用车、非会员用车])
    * @author zc.du
    * @param orderCarUsed
    * @return BigDecimal
    */
    public BigDecimal getOrderCarUsedMoney(OrderCarUsed orderCarUsed);
}
