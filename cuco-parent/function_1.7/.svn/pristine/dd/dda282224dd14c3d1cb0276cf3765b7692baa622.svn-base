package com.hy.gcar.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hy.gcar.entity.CarOperatePlan;


public interface CarOperatePlanMapper<T> extends BaseDao<T> {

	/**
	 * 根据状态查询
	* @Description:TODO    
	* @param type 1待分配 2会员预约  3 平台预约  
	* @author:JIAZHIPENG  
	* @time:2016-9-12 下午6:10:28   
	* @return String
	 */
	public List<CarOperatePlan> selectListByStatus(@Param("maxDate")Date maxDate,@Param("miniDate")Date miniDate,@Param("status")int status,@Param("statusIng")int statusIng,@Param("type")int type);
	
	/**
	 * 根据车牌号查询未执行执行中的所有运营计划
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-13 上午11:42:11   
	* @return String
	 */
	public List<CarOperatePlan> selectListByPlateNum(@Param("carPlateNum")String carPlateNum,@Param("maxDate")Date maxDate,@Param("miniDate")Date miniDate,@Param("status")int status,@Param("statusIng")int statusIng);
	
	/**
	 * 根据运营车辆编号和 查询日期 查询预约记录
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-13 下午4:19:20   
	* @return String
	 */
	public List<CarOperatePlan> selectListByOperateNum(@Param("operateNum")String operateNum,@Param("miniDate")Date miniDate);
	
	/**
	 * 根据运营车辆编号 和当前时间查询预约记录
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-13 下午4:58:05   
	* @return String
	 */
	public CarOperatePlan selectByOperateNum(@Param("operateNum")String operateNum,@Param("date")Date date);
	
	/**
	 * 根据运营车辆编号 和 预约开始时间查询
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-13 下午6:21:13   
	* @return String
	 */
	public CarOperatePlan selectByOperateNumAndStarted(@Param("operateNum")String operateNum,@Param("started")Date started);
	
	/**
	 * 根据运营车辆编号 和 待执行，执行中状态 和预约开始结束时间 查询预约记录
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-14 上午10:38:47   
	* @return String
	 */
	public List<CarOperatePlan> selectListByOperateNumAndStarted(@Param("status")int status,@Param("statusIng")int statusIng,@Param("ended")Date ended,@Param("started")Date started,@Param("operateNum")String operateNum);
	
	/**
	 *  根据运营车辆编号 和 选择时间 查询运营记录
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-22 下午3:58:24   
	* @return String
	 */
	public CarOperatePlan selectByCarPlateNumAndStartedEnded(@Param("carPlateNum")String carPlateNum,@Param("started")Date started,@Param("ended")Date ended);
	
	/**
	 * 根
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-22 下午5:16:47   
	* @return String
	 */
	public List<CarOperatePlan> selectListBystatusAndNow(@Param("stay")int stay,@Param("useing")int useing,@Param("nowDate")Date nowDate);
	
	/**
	 *  根据运营车辆编号 和 选择时间 查询运营记录
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-22 下午3:58:24   
	* @return String
	 */
	public CarOperatePlan selectByOperateIdAndStartedEnded(@Param("operateId")Long operateId,@Param("started")Date started);
	
	/**
	 * 根据车辆id 和 设置时间 取消此车辆之后的所有预约
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-22 下午3:58:24   
	* @return String
	 */
	public Integer updateStatusByOperateIdAndStarted(CarOperatePlan carOperatePlan);
	
	/**
	 * 根据车辆 Id 和 状态 查询车辆的真实状态
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-28 下午5:54:33   
	* @return String
	 */
	public CarOperatePlan selectCarStatus(CarOperatePlan carOperatePlan);
	
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
	 * 查询不可用车辆
	 * @param carOperatePlan
	 * @return
	 */
	public List<CarOperatePlan> selectNotAvailableCar(CarOperatePlan carOperatePlan);
	
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
	 * 批量更新运营计划的车辆id 为空
	 * @param carOperatePlanIds
	 */
	public void updateClearCarOperateId(List<Long> carOperatePlanIds);
	
	/**
	 * 查询车辆的运营状态
	 * @param carOperateIds
	 * @return
	 */
	public List<CarOperatePlan> selectCarOperateStateByNow(List<Long> carOperateIds);

	/**
	 * 根据用车id更新车辆运营计划
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
  	 *  根据车辆id 和 设置时间 取消待执行的车辆之前的所有预约 排除当前运营
  	* @Description:TODO      
  	* @author:JIAZHIPENG  
  	* @time:2016-11-24 下午7:20:14   
  	* @return String
  	 */
  	public Integer updateStatusByOperateIdStartedAndEndForPlatformNotById(CarOperatePlan carOperatePlan);
  	
  	/**
  	 * 根据车辆id 和 设置时间 取消待执行的车辆之前的所有预约  排除当前运营
  	* @Description:TODO      
  	* @author:JIAZHIPENG  
  	* @time:2016-11-24 下午7:20:59   
  	* @return String
  	 */
  	public Integer updateStatusByOperateIdStartedAndEndNotById(CarOperatePlan carOperatePlan);
  	
  	/**
  	 * 查找已失联的运营计划
  	* @Description:TODO      
  	* @author:JIAZHIPENG  
  	* @time:2016-11-25 下午5:52:37   
  	* @return String
  	 */
  	public CarOperatePlan selectoutofcontactByOperateIdAndStartedEnded(@Param("operateId")Long operateId);
  	
  	/**
  	 * 查找已签约的运营计划
  	* @Description:TODO      
  	* @author:JIAZHIPENG  
  	* @time:2016-12-10 下午4:17:46   
  	* @return String
  	 */
  	public CarOperatePlan selectAppointmentByOperateId(@Param("operateId")Long operateId);
  	
  	/**
     * 根据车牌号开始时间结束时间
    * @Description:TODO      
    * @author:JIAZHIPENG  
    * @time:2016-11-23 下午5:57:19   
    * @return String
     */
    public List<CarOperatePlan> selectCarPlanByOperateIdStartedAndEndForPlatform(CarOperatePlan carOperatePlan);
  	
  	
  	
}
