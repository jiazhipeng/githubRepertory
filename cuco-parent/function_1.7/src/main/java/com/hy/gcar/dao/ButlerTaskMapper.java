package com.hy.gcar.dao;


import com.hy.gcar.entity.ButlerTask;

import java.util.List;

public interface ButlerTaskMapper<T> extends BaseDao<T> {

    /**
     * 查询送车任务显示面板数据
     * @param butlerTask
     * @return
     */
    public List<ButlerTask> selectListBySendCar(ButlerTask butlerTask);

    /**
     * 查询用户进行中的任务
     * @param butlerTask
     * @return
     */
    public int findHaveInHandButlerTask(ButlerTask butlerTask);
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
	 * 修改送车任务
	 * @param butlerTask
	 * @return
	 */
	public int updateSendCarButlerTaskByStatus(ButlerTask butlerTask);
	
	/**
	 * 给定一个车辆Id，查询开始时间在当前时间之后的所有任务
	 * @param butlerTask
	 */
	public List<ButlerTask> selectButlerTaskListByCarOperateId(ButlerTask butlerTask);
	/**
	 * 按主键将任务上的车辆Id设置为null
	 * @param butlerTask
	 */
	public void updateButlerTaskCarOperateNull(ButlerTask butlerTask);

	/**
	 * 查询不可用的车辆(送车任务状态不是 待送车和待处理的任务)
	 * @param carOperateIds
	 * @return
	 */
    List<ButlerTask> selectNotAvailableCar(ButlerTask butlerTask);
    
    /**
     * 根据任务id批量将 任务的车辆id值为空
     * @param powerUsedIds
     */
	public void updateClearCarOperateId(List<Long> powerUsedIds);
	
	/**
	 * 给定一个车辆Id，查询延期时间段的所有任务
	 * @param butlerTask
	 */
	public List<ButlerTask> selectButlerTaskListByCarOperateIdAndTime(ButlerTask butlerTask);

	/**
	 * 根据用车时间查询进行中的送车任务和取车任务
	 * @param butlerTask
	 * @return
	 */
	public List<ButlerTask> selectHaveInHandButlerTaskByPlanTime(ButlerTask butlerTask);
	
	/**
	 * 根据车型ID查询两个时间段内的待接单待送车的送车任务
	 * @param butlerTask
	 * @return
	 */
	public List<ButlerTask> selectByCartypeIdAndStartEnd(ButlerTask butlerTask);
	
	/**
	 * 根据  powerUsedId 集合查询用车
	 * @param powerUsedIds
	 * @return
	 */
	public List<ButlerTask> getButlerTaskBypowerUsedIds(List<Long> powerUsedIds);
	
}
