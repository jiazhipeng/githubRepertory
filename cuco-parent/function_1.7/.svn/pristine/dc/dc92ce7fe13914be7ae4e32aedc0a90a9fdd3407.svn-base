package com.hy.gcar.service.carOperte;


import java.util.Date;
import java.util.List;

import com.hy.gcar.entity.ButlerTask;
import com.hy.gcar.entity.CarOperate;
import com.hy.gcar.entity.CarOperatePlan;
import com.hy.gcar.entity.ItemCartype;
import com.hy.gcar.entity.Member;

/**
 * 
 * @author auto create
 * @param <CarOperate>
 * @since 1.0,2016-08-19 12:43:36
 */
public interface CarOperateService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tdCarOperate
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月19日 12:43:36
    *@修改时间 2016年08月19日 12:43:36
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(CarOperate tdCarOperate) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tdCarOperate
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月19日 12:43:36
    *@修改时间 2016年08月19日 12:43:36
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<CarOperate> tdCarOperate) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tdCarOperate
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月19日 12:43:36
    *@修改时间 2016年08月19日 12:43:36
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteByPrimaryKey(Object id);

    /**
    *@方法名: deleteBatchByPrimaryKey
    *@方法描述: 根据主键批量删除
    *@param tdCarOperate
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月19日 12:43:36
    *@修改时间 2016年08月19日 12:43:36
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteBatchByPrimaryKey(List<Object> ids);

    /**
    *@方法名: updateByPrimaryKeySelective
    *@方法描述: 根据主键选择更新单个对象
    *@param tdCarOperate
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月19日 12:43:36
    *@修改时间 2016年08月19日 12:43:36
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(CarOperate tdCarOperate);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tdCarOperate
    *@返回值 对象 返回类型
    *@作者：hycx
    *@创建时间 2016年08月19日 12:43:36
    *@修改时间 2016年08月19日 12:43:36
    *@版本 V1.0
    *@异常
    **/
    public CarOperate findById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tdCarOperate
    *@返回值 List<CarOperate> 返回类型
    *@作者：hycx
    *@创建时间 2016年08月19日 12:43:36
    *@修改时间 2016年08月19日 12:43:36
    *@版本 V1.0
    *@异常
    **/
    public List<CarOperate> selectByCondition(CarOperate tdCarOperate);

    /**
    *@方法名: selectCountByCondition
    *@方法描述: 分条件查询对象总数
    *@param tdCarOperate
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月19日 12:43:36
    *@修改时间 2016年08月19日 12:43:36
    *@版本 V1.0
    *@异常
    **/
    public Integer selectCountByCondition(CarOperate tdCarOperate);
    
	/**
	 * 查询当天的待分配和 整配种车辆  --业务方法
	 * @param type 1待分配 整备中 2会员预约 3 平台预约 6维修中
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-13 下午2:31:29   
	* @return String
	 */
	public List<CarOperate> selectListByDay(Date maxDate,Date miniDate,int type);
	
	/**
	 * 根据状态查询可使用车辆  --待分配，会员待使用，平台待使用  --业务方法
	 * 根据预约情况，剔除今天内要预约使用的车辆
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-14 下午3:00:59   
	* @return String
	 */
	public List<CarOperate> selectListBystatus(Date nowDate);
	
	/**
	 * 修改车辆状态为待分配    --业务方法 车辆中只提供待分配，维修中，整备中 三个业务状态修改
	 * loginId 可为null
	* @Description:TODO    
	* @param type 1.整备完成 2.失联找回  
	* @author:JIAZHIPENG  
	* @time:2016-9-14 下午3:58:31   
	* @return String
	 */
	public Integer stay(long carOperateId,Long loginId,int type);
	
	/**
	 * 修改车辆状态为维修中   --业务方法  车辆中只提供待分配，维修中，整备中 三个业务状态修改
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-14 下午4:35:19   
	* @return String
	 */
	public Integer repairsing(long carOperateId);
	
	/**
	 * 修改车辆状态为会员使用中   --业务方法  
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-14 下午4:39:48   
	* @return String
	 */
	//public Integer memberuseing(long carOperateId);
	
	/**
	 * 修改车辆状态为平台使用中  --业务方法
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-14 下午4:40:07   
	* @return String
	 */
	//public Integer platformuseing(long carOperateId);
	
	/**
	 * 修改车辆状态为整备中  --业务方法 车辆中只提供待分配，维修中，整备中 三个业务状态修改
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-19 下午5:01:10   
	* @return String
	 */
	public Integer readinessing(long carOperateId);
	
	/** 
	 * 故障换车                       --业务方法
	 * 收取用户一次性故障换车 费用，
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-14 下午4:21:02   
	* @return String
	 */
	public Integer replaceCar(String oldCarPlateNum, String newCarPlateNum,
			String remark, long carOperatePlanId,int status,Long loginId);


	/**
	 * 查询管家分配成立可分配的车
	 * @param butlerTask
	 * @return
	 */
	public List<CarOperate> selectAvailableVehicles(ButlerTask butlerTask);


	/**
	 * 查询管家可以分配的车辆
	 * @param butlerTask
	 * @return
	 */
	public List<CarOperate> selectAvailableVehiclesCar(ButlerTask butlerTask);

	/**
	 * 校验车辆时候合法
	 * @param butlerTask
	 * @return
	 */
	public boolean checkVehicleLegitimate(ButlerTask butlerTask,ButlerTask butlerTaskDate);
	
	/**
	 * 根据状态查询  车辆总数
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-20 上午10:23:28   
	* @return String
	 */
	public Integer getCountByStatus(CarOperate carOperate);
	
	/**
	 * 根据车牌号模糊查询 车牌信息
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-22 上午10:57:07   
	* @return String
	 */
	public List<CarOperate> searchCarPlateNum(String carPlateNum);
	
	/**
	 * 根据权益id和 车型id查询
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-26 下午3:05:59   
	* @return String
	 */
	public ItemCartype getItemCarType(Long memberItemId);
	
	/**
	 * 修改车辆状态为 已失联
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-11-2 下午4:48:47   
	* @return String
	 */
	public Integer outOfContact(long carOperateId);
	
	
	/**
	 * 修改车辆状态为已预约 
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-14 下午4:35:19   
	* @return String
	 */
	public Integer appointment(long carOperateId);
	
	/**
	 * 修改车辆状态为待分配
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-12-12 下午4:20:37   
	* @return String
	 */
	public Integer stay(long carOperateId);
	
	/**
	 * 通过车牌号 查找车辆信息
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-12-14 下午3:10:54   
	* @return String
	 */
	 public CarOperate findByCarPlanNum(String carPlanNum);
	 
	 /**
	  * 失联,维修，故障,延时 取消 平台运营插入车辆日志
	  * operateNum 车辆运营编号,member loginId登录人对象,remark取消原因,status车辆状态
	 * @Description:TODO      
	 * @author:JIAZHIPENG  
	 * @time:2016-12-21 下午4:44:06   
	 * @return String
	  */
	 public void cancelPlanLog(String operateNum,Member member,String remark,Integer status) throws Exception;
}
