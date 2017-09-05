package cn.cuco.service.car.carOperate;


import java.util.List;

import cn.cuco.entity.CarOperatePlan;


/** 
* @ClassName: CarOperatePlanService 
* @Description: 车辆运营计划service
* @author gongbw
* @date 2017年2月21日 下午5:18:50  
*/
public interface CarOperatePlanService {
   
	/**   
     * @Title: createCarOperatePlan   
     * @Description: 创建车辆运营计划
     * @param: CarOperatePlan
     * @return: CarOperatePlan      
     */
	public CarOperatePlan createCarOperatePlan(CarOperatePlan carOperatePlan);

	/**   
     * @Title: updateOperatePlan   
     * @Description: 根据用车记录ID修改车辆运营计划基础信息
     * @param: CarOperatePlan
     * @return: CarOperatePlan      
     */
	public CarOperatePlan updateOperatePlanInfoByCarUsedId(CarOperatePlan carOperatePlan);
	
	/**   
     * @Title: getCarOperatePlanList   
     * @Description: 获取车辆运营计划列表
     * @param: CarOperatePlan
     * @return: CarOperatePlan      
     */
	public List<CarOperatePlan> getCarOperatePlanList(CarOperatePlan carOperatePlan);
	
	/**   
     * @Title: updateOperatePlanByWaitExecute   
     * @Description: 根据用车记录ID修改车辆运营计划状态为待执行   
     * @param: CarOperatePlan
     * @return: CarOperatePlan      
     */
	public CarOperatePlan updateOperatePlan2WaitExecuteByCarUsedId(CarOperatePlan carOperatePlan);
	
	/**   
     * @Title: updateOperatePlanByExecuting   
     * @Description: 根据用车记录ID修改车辆运营计划状态为执行中
     * @param: CarOperatePlan
     * @return: CarOperatePlan      
     */
	public CarOperatePlan updateOperatePlan2ExecutingByCarUsedId(CarOperatePlan carOperatePlan);
	
	/**   
     * @Title: updateOperatePlanByCompelete   
     * @Description: 根据用车记录ID修改车辆运营计划状态为已完成
     * @param: CarOperatePlan
     * @return: CarOperatePlan      
     */
	public CarOperatePlan updateOperatePlan2CompeleteByCarUsedId(CarOperatePlan carOperatePlan);
	
	/**   
     * @Title: updateOperatePlanByCancel   
     * @Description: 根据用车记录ID修改车辆运营计划状态为已取消
     * @param: CarOperatePlan
     * @return: CarOperatePlan      
     */
	public CarOperatePlan updateOperatePlan2CancelByCarUsedId(CarOperatePlan carOperatePlan);
	
	/**
	 * 通过用车记录ID删除运营计划   
	 * @Title: deleteOperatePlanByCarUsedId   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param carOperatePlan      
	 * @return: void       
	 */
	public void deleteOperatePlanByCarUsedId(CarOperatePlan carOperatePlan);
	
}
