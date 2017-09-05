package com.hy.gcar.service.butlerTask;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hy.gcar.constant.CarOperatePlanEnum;
import com.hy.gcar.entity.ButlerTask;
import com.hy.gcar.entity.CarOperate;
import com.hy.gcar.entity.CarOperatePlan;
import com.hy.gcar.entity.Member;
import com.hy.gcar.entity.PowerUsed;

/**
 * 
 * @author auto create
 * @param <ButlerTask>
 * @since 1.0,2016-09-10 15:42:34
 */
public interface ButlerTaskService {

	/**
	 * 创建车管家任务
	 *
	 * @param butlerTask
	 * @return
	 */
	public ButlerTask createButlerTask(ButlerTask butlerTask);

	/**
	 * 修改车管家任务
	 *
	 * @param butlerTask
	 * @return
	 */
	public ButlerTask updateButlerTask(ButlerTask butlerTask);

	/**
	 * 分页查询车管家任务
	 *
	 * @param butlerTask
	 * @return
	 */
	public List<ButlerTask> getButlerTask(ButlerTask butlerTask);

	/**
	 * 查询单个车管家任务详情
	 *
	 * @param butlerTask
	 * @return
	 */
	public ButlerTask getButlerTaskById(Long id);

	/**
	 * 删除单个车管家任务
	 * @param butlerTask
	 * @return
	 */
	public void DeleteButlerTaskById(Long id);
	
	/**
	 * 查询管家送车任务的列表（查询条件 - 计划执行时间  - 送车任务类型- 管家id）
	 *
	 * @param butlerTask
	 * @return
	 */
	public List<ButlerTask> getButlerTasksBySendCar(ButlerTask butlerTask);

	/**
	 * 根据查询出来的任务列表，和当月最大天数组装成日历显示的数据
	 * @param butlerTask
	 * @param maxDay
	 * @return
	 */
	public List<ButlerTask> getButlerTaskCalendarList(List<ButlerTask> butlerTask,int maxDay);



	/**
	 * 创建送车任务
	 * 任务状态：待送车
	 *
	 * @param butlerTask
	 */
	public ButlerTask createSendCarButlerTask(ButlerTask butlerTask) throws Exception;

	/**
	 * 创建送车任务
	 * @param butlerTask
	 * @return
	 * @throws Exception
	 */
	public ButlerTask createMemberSendCarButlerTask(ButlerTask butlerTask) throws  Exception;
	/**
	 * 修改送车任务状态为"送车中"
	 * 1.修改状态
	 * 任务状态：送车中
	 *
	 * @param butlerTask
	 */
	public ButlerTask updateSendCarButlerTaskBySending(ButlerTask butlerTask) throws Exception;





	/**
	 * 修改送车任务状态为"已到达"
	 * 任务状态：已到达
	 *
	 * @param butlerTask
	 */
	public ButlerTask updateSendCarButlerTaskByArrived(ButlerTask butlerTask);


	/**
	 * 修改送车任务状态为"已完成"
	 * 任务状态：已完成
	 *
	 * @param butlerTask
	 */
	public ButlerTask updateSendCarButlerTaskByComplete(ButlerTask butlerTask) throws Exception;

	/**
	 * 取消送车任务
	 * 任务状态：已取消
	 *
	 * @param butlerTask
	 */
	public ButlerTask updateSendCarButlerTaskByCancel(ButlerTask butlerTask) throws Exception;

	/**
	 * 创建取车任务
	 * 任务状态：待取车
	 *
	 * @param butlerTask
	 */
	public ButlerTask createGetCarButlerTask(ButlerTask butlerTask);
	
	/**
	 * 中止取车任务
	 * 任务状态：待取车
	 *
	 * @param butlerTask
	 */
	public ButlerTask updateGetCarButlerTaskByWaitGet(ButlerTask butlerTask);

	/**
	 * 接受取车任务
	 * 任务状态：取车中
	 *
	 * @param butlerTask
	 */
	public ButlerTask updateGetCarButlerTaskByGeting(ButlerTask butlerTask);

	/**
	 * 接受取车任务
	 * 任务状态：已到达
	 *
	 * @param butlerTask
	 */
	public ButlerTask updateGetCarButlerTaskByArrived(ButlerTask butlerTask);

	/**
	 * 接受取车任务
	 * 任务状态：已失联
	 *
	 * @param butlerTask
	 */
	public ButlerTask updateGetCarButlerTaskByLose(ButlerTask butlerTask) throws Exception;

	/**
	 * 完成取车任务
	 * 任务状态：已完成
	 *
	 * @param butlerTask
	 */
	public ButlerTask updateGetCarButlerTaskByComplete(ButlerTask butlerTask);

	/**
	 * 取消取车任务
	 * 任务状态：已取消
	 *
	 * @param butlerTask
	 */
	public ButlerTask updateGetCarButlerTaskByCancel(ButlerTask butlerTask);

	/**
	 * 创建充电任务
	 * 任务状态：待取车
	 *
	 * @param butlerTask
	 */
	public ButlerTask createChargingButlerTask(ButlerTask butlerTask);


	/**
	 * 接受充电任务
	 * 任务状态：取车中
	 *
	 * @param butlerTask
	 */
	public ButlerTask updateChargingButlerTaskByWay(ButlerTask butlerTask);

	/**
	 * 接受充电任务
	 * 任务状态：充电中
	 *
	 * @param butlerTask
	 */
	public ButlerTask updateChargingButlerTaskByCharging(ButlerTask butlerTask);

	/**
	 * 接受充电任务
	 * 任务状态：送车中
	 *
	 * @param butlerTask
	 */
	public ButlerTask updateChargingButlerTaskBySending(ButlerTask butlerTask);

	/**
	 * 接受充电任务
	 * 任务状态：已取消
	 *
	 * @param butlerTask
	 */
	public ButlerTask updateChargingButlerTaskByCancel(ButlerTask butlerTask);

	/**
	 * 完成充电任务
	 * 任务状态：已完成
	 *
	 * @param butlerTask
	 */
	public ButlerTask updateChargingButlerTaskByComplete(ButlerTask butlerTask);

	/**
	 * 取充电任务列表
	 */
	public List<ButlerTask> getButlerTaskListForCharging(ButlerTask butlerTask);

	/**
	 * 二分法查找 每天的送车任务数量 - 保证arrsys 中的日期是从大到小排列的
	 *
	 * @param arrays  列表
	 * @param content 天数
	 * @return
	 */
	public int getEveryDaySendCarTaskTotal(List<ButlerTask> arrays, int content);
	
	/**
	 * 任务非状态修改业务方法
	 * @param butlerTask
	 * @return
	 * @throws Exception 
	 */
	public ButlerTask updateButlerTaskByNoStatus(ButlerTask butlerTask) throws Exception;
	
	/**
	 * 修改送车任务没有状态的方法
	 * @param butlerTask
	 * @return
	 * @throws Exception
	 */
	public ButlerTask updateSendCarButlerTaskByNoStatus(ButlerTask butlerTask) throws Exception;
	
	/**
	 * 取车任务列表
	 */
	public List<ButlerTask> getButlerTaskListForGetCar(ButlerTask butlerTask);
	
	/**
	 * 送车任务列表
	 */
	public List<ButlerTask> getButlerTaskListForSendCar(ButlerTask butlerTask);

	/**
	 * 若会员用车任务大于等于启用车位数量，则不可新建任务，弹窗提示“该用户正在进行任务，无法创建新任务
	 * @return
	 */
	public String checkCreateSendCarButlerTask(Member member) throws Exception;

    /**
     * 查询用户进行中的任务
     * @param memberID
     * @return
     */
    public int findHaveInHandButlerTask(Long memberID);


	/**
	 * 判断用户当前余额，若余额小于车辆一日使用费（小于车辆日使用费、0、负数），则弹窗提示“该用户余额不足，无法创建任务
	 * @return
	 */
	public String checkSendCarButlerTaskMemberBalance(ButlerTask butlerTask);
	
	/**
	 * 根据用车记录ID以及type获取任务
	 * @param butlerTask
	 * @return
	 */
	public ButlerTask getButlerTaskByPowerUsedId(ButlerTask butlerTask);
	/**
	 * 取车任务获取日历数据
	 * @param butlerTask
	 * @return
	 */
	public List<ButlerTask> getButlerTaskForCalendar(ButlerTask butlerTask);
	/**
	 * 根据车辆id 查询是否有充电任务
	 * @param butlerTask
	 * @return
	 */
	public ButlerTask getButlerTaskIsChargeing(ButlerTask butlerTask);
	
	/**
	 * 根据条件查询
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-25 下午4:09:13   
	* @return String
	 */
	public List<ButlerTask> selectByCondition(ButlerTask butlerTask);
	
	/**
	 * 用户端修改还车信息用（用户端修改还车信息，需要给管家发送消息推送）
	 * @param butlerTask
	 */
	public void updateGetCarButlerTaskByMember(ButlerTask butlerTask);
	
	/**
	 * 给定一个车辆Id，查询开始时间在当前时间之后的所有送车任务
	 * @param butlerTask
	 */
	public List<ButlerTask> selectButlerTaskListByCarOperateId(ButlerTask butlerTask);
	/**
	 * 按主键将任务上的车辆Id设置为null
	 * @param butlerTask
	 */
	public void updateButlerTaskCarOperateNull(ButlerTask butlerTask);

	/**
	 * 修改送车任务为待送车
	 * @param butlerTask
	 * @return
	 */
	public ButlerTask updateSendCarButlerTaskByWaitingForACar(ButlerTask butlerTask);
	
	/**
	 * 修改送车任务待送车跟进中状态
	 * @param butlerTask
	 */
	public ButlerTask updateWaitingForACar(ButlerTask butlerTask);

	/**
	 * 查询不可用的车辆
	 * @param objects
	 * @return
	 */
    public List<ButlerTask> selectNotAvailableCar(ButlerTask butlerTask);
    
    /**
     * 创建车辆运营计划 待执行
     * @param butlerTask
     * @param status
     * @return
     * @throws Exception
     */
	public CarOperatePlan insertCarRecordToBeExecuted(ButlerTask butlerTask) throws Exception;
	/**
     * 创建车辆运营计划 执行中
     * @param butlerTask
     * @param status
     * @return
     * @throws Exception
     */
	public CarOperatePlan insertCarRecordImplementationIn(ButlerTask butlerTask) throws Exception;

	/**
	 * 取消运营计划
	 * @param butlerTask
	 * @throws Exception
	 */
	public void updateCarOperatePlanCancel(ButlerTask butlerTask) throws Exception;
	
	/**
	 * 给定一个车辆Id，查询开始时间在当前时间之后的所有送车任务
	 * @param butlerTask
	 */
	public List<ButlerTask> selectButlerTaskListByCarOperateIdAndTime(ButlerTask butlerTask);

	/**
	 * 修改此车辆  之后的所有任务 的车辆id为null
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-23 下午12:27:34   
	* @return String
	 */
	public void updateButlerTaskCarOperate(ButlerTask butlerTask,Integer type);
	/**
	 * 用车时 修改用车、还车任务 时间或地点
	 * @param powerUsed
	 */
	public Boolean updateSendAndReturnButlerTask(PowerUsed powerUsed);
	
	/**
	 * 插入任务日志
	 * @param
	 */
	public void createButlerTaskStatusLog(ButlerTask old_butlerTask);

	/**
	 * 根据用车时间查询进行中的送车任务和取车任务
	 * @param butlerTask
	 * @return
	 */
	public List<ButlerTask> selectHaveInHandButlerTaskByPlanTime(ButlerTask butlerTask);
	
	/**
	 * 创建 送车完成的 任务 日志
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-12-12 下午5:41:24   
	* @return String
	 */
	public ButlerTask createMemberSendCarButlerTasked(ButlerTask butlerTask);
	
	/**
	 *  送车完成时 权益余额扣减第一次费用(如果有优惠卷，则需要用优惠券抵扣)，同时插入用车费用日志表
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-12-13 上午10:39:24   
	* @return String
	 */
	public void deductionOfFare(ButlerTask butlerTask,BigDecimal price) throws Exception;
	
	/**
	 * 发送消息推送
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-12-13 上午10:41:01   
	* @return String
	 */
	public void  sendMessageByTaskStatusChangeApp(ButlerTask butlerTask,String value);
	
	/**
	 * 根据车型ID查询两个时间段内的待接单待送车的送车任务
	 * @param butlerTask
	 * @return
	 */
	public List<ButlerTask> selectByCartypeIdAndStartEnd(ButlerTask butlerTask);
	
	/**
	 * 
	 * @param poList
	 * @return
	 */
	public List<ButlerTask> getButlerTaskBypowerUsedIds(List<Long> poList);
	
	/**
	 * 创建取车任务
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-12-15 下午6:15:00   
	* @return String
	 */
	public ButlerTask createGetCarButlerTasked(ButlerTask butlerTask);
	
	/**
	 * 记录任务日志
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-12-21 下午2:44:54   
	* @return String
	 */
	public void createButlertaskLogByemptyCar(ButlerTask butlertask, Member member, Integer operateType, Integer dealType, CarOperate car);


}
