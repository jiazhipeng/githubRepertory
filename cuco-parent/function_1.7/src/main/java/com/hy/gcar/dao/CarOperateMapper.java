package com.hy.gcar.dao;

import java.util.Date;
import java.util.List;

import com.hy.gcar.entity.ButlerTask;
import org.apache.ibatis.annotations.Param;
import com.hy.gcar.entity.CarOperate;
import com.hy.gcar.entity.CarOperatePlan;


public interface CarOperateMapper<T> extends BaseDao<T> {

	
	/**
	 * 查询当天的待分配和 整配种车辆
	* @Description:TODO      
	* @param type 1待分配 整备中 2会员预约 3 平台预约 6维修中
	* @author:JIAZHIPENG  
	* @time:2016-9-13 下午2:31:29   
	* @return String
	 */
	public List<CarOperate> selectListByDay(@Param("status")int status,@Param("statusIng")int statusIng,@Param("stay")int stay,@Param("readinessing")int readinessing,@Param("maxDate")Date maxDate,@Param("miniDate")Date miniDate,@Param("type")int type,@Param("memberuse")int memberuse,@Param("platformuse")int platformuse,@Param("repairsing")int repairsing);
	
	/**
	 * 根据状态查询可使用车辆  --待分配，会员待使用，平台待使用
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-14 下午3:00:59   
	* @return String
	 */
	public List<CarOperate> selectListBystatus(@Param("stay")int stay);
	
	/**
	 * 查询管家分配成立可分配的车
	 * @param butlerTask
	 * @return
	 */
	public List<CarOperate> selectAvailableVehicles(ButlerTask butlerTask);
	
	/**
	 * 根据状态查询  车辆总数
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-9-20 上午10:22:34   
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
	public List<CarOperate> searchCarPlateNum(@Param("carPlateNum")String carPlateNum);
}
