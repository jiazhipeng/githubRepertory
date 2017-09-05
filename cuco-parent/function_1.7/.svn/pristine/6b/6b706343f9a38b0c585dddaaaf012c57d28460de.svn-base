package com.hy.gcar.service.carOperatePlan;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hy.gcar.entity.CarOperatePlan;

/**
 * 
 * @author auto create
 * @param <CarOperatePlan>
 * @since 1.0,2016-09-12 17:51:04
 */
public interface CarOperatePlanService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tdCarOperatePlan
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 17:51:04
    *@修改时间 2016年09月12日 17:51:05
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(CarOperatePlan tdCarOperatePlan) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tdCarOperatePlan
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 17:51:05
    *@修改时间 2016年09月12日 17:51:05
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<CarOperatePlan> tdCarOperatePlan) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tdCarOperatePlan
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 17:51:05
    *@修改时间 2016年09月12日 17:51:05
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteByPrimaryKey(Object id);

    /**
    *@方法名: deleteBatchByPrimaryKey
    *@方法描述: 根据主键批量删除
    *@param tdCarOperatePlan
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 17:51:05
    *@修改时间 2016年09月12日 17:51:05
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteBatchByPrimaryKey(List<Object> ids);

    /**
    *@方法名: updateByPrimaryKeySelective
    *@方法描述: 根据主键选择更新单个对象
    *@param tdCarOperatePlan
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 17:51:05
    *@修改时间 2016年09月12日 17:51:05
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(CarOperatePlan tdCarOperatePlan);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tdCarOperatePlan
    *@返回值 对象 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 17:51:05
    *@修改时间 2016年09月12日 17:51:05
    *@版本 V1.0
    *@异常
    **/
    public CarOperatePlan findById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tdCarOperatePlan
    *@返回值 List<CarOperatePlan> 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 17:51:05
    *@修改时间 2016年09月12日 17:51:05
    *@版本 V1.0
    *@异常
    **/
    public List<CarOperatePlan> selectByCondition(CarOperatePlan tdCarOperatePlan);

    /**
    *@方法名: selectCountByCondition
    *@方法描述: 分条件查询对象总数
    *@param tdCarOperatePlan
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 17:51:05
    *@修改时间 2016年09月12日 17:51:05
    *@版本 V1.0
    *@异常
    **/
    public Integer selectCountByCondition(CarOperatePlan tdCarOperatePlan);

    /**
     * 根据状态查询运营中未执行、执行中的 预定计划  --业务方法
    * @Description:TODO      
    * @param type 1待分配 2会员预约  3 平台预约  
    * @author:JIAZHIPENG  
    * @time:2016-9-12 下午6:07:57   
    * @return String
     */
    public List<CarOperatePlan> selectListByStatus(Date maxDate,Date miniDate,int type);
    
    /**
     * 根据车牌号查询运营中未执行、执行中的 预定计划 --业务方法
    * @Description:TODO      
    * @author:JIAZHIPENG  
    * @time:2016-9-13 上午11:42:53   
    * @return String
     */
    public List<CarOperatePlan> selectListByPlateNum(String carPlateNum,Date maxDate,Date miniDate);
    
	/**
	 * 根据运营车辆编号和 查询日期 查询预约记录 --业务方法
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-13 下午4:19:20   
	* @return String
	 */
	public List<CarOperatePlan> selectListByOperateNum(String operateNum,Date miniDate);
	
	/**
	 * 根据运营车辆编号 和当前时间查询预约记录 --业务方法
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-13 下午4:58:05   
	* @return String
	 */
	public CarOperatePlan selectByOperateNum(String operateNum);
	
	/**
	 * 根据运营车辆编号 和 预约开始时间查询 --业务方法
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-13 下午6:21:13   
	* @return String
	 */
	public CarOperatePlan selectByOperateNumAndStarted(String operateNum,Date started);
	
	/**
	 * 根据运营车辆编号 和 待执行，执行中状态 和预约开始结束时间 查询预约记录 --业务方法
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-14 上午10:38:47   
	* @return String
	 */
	public List<CarOperatePlan> selectListByOperateNumAndStarted(Date ended,Date started,String operateNum);
	
	/**
	 * 取消平台运营  和 收益记录  --业务方法
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-14 上午10:53:14   
	* @return String
	 */
	public Integer cacleOperatePlan(String reason,int type,String operateNum,long carOperateId,long carOperatePlanId);
	
	/**
	 * 车辆修理完成   --业务方法
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-14 下午1:44:19   
	* @return String
	 */
	public Integer repairFinish(Date finishDate,BigDecimal maintenanceCosts,int handle,Long carOperateId,Long carOperatePlanId,String repairManufacturer,Long memberId);
	
	/**
	 * 修改平台运营计划表中车辆状态为会员使用中   --业务方法  
	* @Description:TODO      
	* @param 主键id
	* @author:JIAZHIPENG  
	* @time:2016-9-14 下午4:39:48   
	* @return String
	 */
	public Integer memberuseing(long id);
	
	/**
	 * 修改平台运营计划表中车辆状态为平台使用中  --业务方法
	* @Description:TODO   
	* @param 主键id   
	* @author:JIAZHIPENG  
	* @time:2016-9-14 下午4:40:07   
	* @return String
	 */
	public Integer platformuseing(long id);
	
	/**
	 * 修改平台运营计划表中车辆状态为会员待使用  --业务方法
	* @Description:TODO
	* @param 主键id         
	* @author:JIAZHIPENG  
	* @time:2016-9-19 下午7:42:18   
	* @return String
	 */
	public Integer memberuse(long id);
	
	/**
	 * 修改平台运营计划表中车辆状态为平台待使用   --业务方法
	* @Description:TODO      
	* @param 主键id   
	* @author:JIAZHIPENG  
	* @time:2016-9-19 下午7:42:24   
	* @return String
	 */
	public Integer platformuse(long id);
	
	/**
	 *  根据运营车辆编号 和 选择时间 查询运营记录
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-22 下午3:58:24   
	* @return String
	 */
	public CarOperatePlan selectByCarPlateNumAndStartedEnded(@Param("carPlateNum")String carPlateNum,@Param("started")Date started,@Param("ended")Date ended);
	
	/**
	 *  根据车辆id 和 选择时间 查询运营记录
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-23 下午2:41:23   
	* @return String
	 */
	public CarOperatePlan selectByOperateIdAndStartedEnded(@Param("operateId")Long operateId,
			@Param("started")Date started); 
	
	/**
	 *  根据车辆id 和 设置时间 取消此车辆之后的所有预约
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-22 下午3:58:24   
	* @return String
	 */
	public Integer updateStatusByOperateIdAndStarted(CarOperatePlan carOperatePlan);
	
	/**
	 * 根据车辆id查询车辆状态
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-28 下午5:37:20   
	* @return String
	 */
	public Integer getCarStatus(Long carOperateId);
	
	/**
	 *  根据车辆id 和 设置时间 取消待执行的车辆之前的所有预约
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-28 下午9:19:57   
	* @return String
	 */
	public Integer updateStatusByOperateIdAndEnded(CarOperatePlan carOperatePlan);

	/**
	 * 查查询预约用车开始时间大于运营结束时间
	 * @param carOperatePlan
	 * @return
	 */
	public List<CarOperatePlan> selectPlanTimeGreaterThanEndTime(CarOperatePlan carOperatePlan);

	/**
	 * 查询预约用车开始时间小于运营结束时间
	 * @param carOperatePlan
	 * @return
	 */
	public List<CarOperatePlan> selectPlanTimeLessThanEndTime(CarOperatePlan carOperatePlan);

	/**
	 * 查询不可预约车辆
	 * @param carOperatePlan
	 * @return
	 */
	public List<CarOperatePlan> selectNotAvailableCar(CarOperatePlan carOperatePlan);
	/**
	 * 查询某时间段内是否有库存
	 * @param CarTypeId
	 * @param UseCarStartTime
	 * @param UseCarEndTime
	 * @return true 有库存 ；flase 无库存
	 */
	public boolean isStore(Long CarTypeId,Date useCarStartTime,Date useCarEndTime);
	
	
	/**
	 * 根据用车日期查询，用车日期小于=数据库中用车日期并且用车结束日期+整备时间>=数据库中的用车日期 的运营计划
	 * @param carOperatePlan
	 * @return
	 */
	public List<CarOperatePlan> selectCarOperatePlanByStarted(CarOperatePlan carOperatePlan);
	
	/**
	 * 批量更新运营计划为取消
	 * @param carOperatePlanIds
	 */
	public void updateCarOperatePlanByCancel(List<Long> carOperatePlanIds);
	/**
	 * 批量更新运营计划的车辆id
	 * @param carOperatePlanIds
	 */
	public void updateClearCarOperateId(List<Long> carOperatePlanIds);
	
	/**
	 * 查询车辆的运营状态
	 * @param carOperates
	 * @return
	 */
	public List<CarOperatePlan> selectCarOperateStateByNow(List<Long> carOperates);
	
	/**
	 * 方便测试用
	 * @param carTypeId
	 * @param useCarStartTime
	 * @param useCarEndTime
	 * @return
	 */
	public List<Map<String, Object>> getStoreByTime(Long carTypeId, Date useCarStartTime, Date useCarEndTime);


	/**
	 * 根据用车记录id更新车辆运营计划
	 * @param carOperatePlan
	 */
	public void updateCarOperatePlanByPowerPowerUsedId(CarOperatePlan carOperatePlan);
	
	 /**
     * 根据车牌号和用车任务id查询 车辆运营计划
    * @Description:TODO      
    * @author:JIAZHIPENG  
    * @time:2016-11-23 下午5:57:19   
    * @return String
     */
    public List<CarOperatePlan> selectCarPlanNumAndpowerUsedId(CarOperatePlan carOperatePlan);
    /**
	 *  根据车辆id 和 设置时间 取消此车辆之后的所有预约
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-22 下午3:58:24   
	* @return String
	 */
	public Integer updateStatusByOperateIdAndStartedForPlatform(CarOperatePlan carOperatePlan);
	
	/**
	 *  根据车辆id 和 设置时间 取消待执行的车辆之前的所有预约
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-28 下午9:19:57   
	* @return String
	 */
	public Integer updateStatusByOperateIdAndEndedForPlatform(CarOperatePlan carOperatePlan);
	/**
	 *  根据车辆id 和 设置时间 取消待执行的车辆之前的所有预约
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-28 下午9:19:57   
	* @return String
	 */
	public Integer updateStatusByOperateIdStartedAndEnd(CarOperatePlan carOperatePlan);
	/**
	 *  根据车辆id 和 设置时间 取消待执行的车辆之前的所有预约
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-28 下午9:19:57   
	* @return String
	 */
	public Integer updateStatusByOperateIdStartedAndEndForPlatform(CarOperatePlan carOperatePlan);
	
  	/**
  	 * 查找已失联的运营计划
  	* @Description:TODO      
  	* @author:JIAZHIPENG  
  	* @time:2016-11-25 下午5:52:37   
  	* @return String
  	 */
  	public CarOperatePlan selectoutofcontactByOperateIdAndStartedEnded(@Param("operateId")Long operateId);
  	
  	/**
  	 * 判断时间段内 是否有库存
  	* @Description: 
  	* @author:JIAZHIPENG  
  	* @time:2016-12-7 下午5:00:55   
  	* @return 1有库存 2 无库存 3限号无库存
  	 */
  	public int searchStore(Long carTypeId, Date useCarStartTime, Date useCarEndTime,String powerUsedId);
  	
  	/**
  	 * 查找已签约的运营计划
  	* @Description:TODO      
  	* @author:JIAZHIPENG  
  	* @time:2016-12-10 下午4:17:46   
  	* @return String
  	 */
  	public CarOperatePlan selectAppointmentByOperateId(@Param("operateId")Long operateId);
  	
  	/**
	 * 查询某时间延时段内是否有库存(针对延时还车用)
	 * @param CarTypeId
	 * @param UseCarStartTime
	 * @param UseCarEndTime
	 * @return true 有库存 ；flase 无库存
	 */
	public boolean isStoreForDelay(Long CarTypeId,Date useCarStartTime,Date useCarEndTime);
	
	/**
     * 根据车牌号和用车任务id查询 车辆运营计划
    * @Description:TODO      
    * @author:JIAZHIPENG  
    * @time:2016-11-23 下午5:57:19   
    * @return String
     */
    public List<CarOperatePlan> selectCarPlanByOperateIdStartedAndEndForPlatform(CarOperatePlan carOperatePlan);
    
    /**
     * 按小时查看排期
    * @Description:TODO      
    * @author:JIAZHIPENG  
    * @time:2016-12-14 下午6:57:19   
    * @return String
     */
    public Long isStoreByHours(Long carTypeId, Date useCarStartTime, Date useCarEndTime);
    
    /**
     * 根据运营车辆编号 和 待执行状态 和预约开始结束时间 查询预约记录 --业务方法  供 维修使用
    * @Description:TODO      
    * @author:JIAZHIPENG  
    * @time:2016-12-16 下午1:41:46   
    * @return String
     */
    public List<CarOperatePlan> selectListByOperateNumAndStartedByRepair(Date ended,
			Date started, String operateNum);
    
    /**
     * 查询排期 (有限号) 1有2无3限号
    * @Description:TODO      
    * @author:JIAZHIPENG  
    * @time:2016-12-21 下午7:52:59   
    * @return String
     */
    public int isStoreByRestriction(Long carTypeId, Date useCarStartTime, Date useCarEndTime);
}
