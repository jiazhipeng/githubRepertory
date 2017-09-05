package cn.cuco.dao;

import cn.cuco.entity.CarOperatePlan;

public interface CarOperatePlanMapper<T> extends BaseDao<T> {

	/**   
	 * @Title: getCarPlanTotalByMember   
	 * @Description: 根据车库查询会员占用的车辆总数
	 * @param: @return      
	 * @return: int      
	 */
	public int getCarPlanTotalByMember();
	
	/**   
	 * @Title: getCarPlanTotalByMember   
	 * @Description: 根据车库查询会员占用的车辆总数
	 * @param: @return      
	 * @return: int      
	 */
	public int getCarPlanTotalByUnMember();
	
	/**   
	 * @Title: updateOperatePlanByCarUsedId   
	 * @Description: 根据用车记录ID修改车辆运营计划 
	 * @param: carOperatePlan
	 * @return: CarOperatePlan      
	 */
	public CarOperatePlan updateOperatePlanByCarUsedId(CarOperatePlan carOperatePlan);
	
	/**   
	 * @Title: getCarPlanTotalByCarTypeId   
	 * @Description: 根据车型ID查询一个时间段内的运营计划数量
	 * @param: @return      
	 * @return: int      
	 */
	public int getCarPlanTotalByCarTypeId(CarOperatePlan carOperatePlan);
	/**   
	 * @Title: getMemberCarPlanTotalByCarTypeId   
	 * @Description: 根据车型ID查询一个时间段内的会员运营计划数量
	 * @param: @return      
	 * @return: int      
	 */
	public int getMemberCarPlanTotalByCarTypeId(CarOperatePlan carOperatePlan);
	/**   
	 * @Title: getUnMemberCarPlanTotalByCarTypeId   
	 * @Description: 根据车型ID查询一个时间段内的非会员运营计划数量
	 * @param: @return      
	 * @return: int      
	 */
	public int getUnMemberCarPlanTotalByCarTypeId(CarOperatePlan carOperatePlan);
	
	/**
	 * 根据用车记录ID删除运营计划   
	 * @Title: deleteByCarUsedId   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param carOperatePlan
	 * @param: @return      
	 * @return: int       
	 */
	public int deleteByCarUsedId(CarOperatePlan carOperatePlan);
}
