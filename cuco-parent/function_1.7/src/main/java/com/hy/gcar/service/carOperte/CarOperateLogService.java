package com.hy.gcar.service.carOperte;


import com.hy.gcar.entity.ButlerTask;
import com.hy.gcar.entity.CarOperateLog;
import com.hy.gcar.entity.PowerUsed;

import java.util.List;

/**
 * 
 * @author auto create
 * @param <CarOperateLog>
 * @since 1.0,2016-09-12 10:36:30
 */
public interface CarOperateLogService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tdCarOperateLog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(CarOperateLog tdCarOperateLog) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tdCarOperateLog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<CarOperateLog> tdCarOperateLog) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tdCarOperateLog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteByPrimaryKey(Object id);

    /**
    *@方法名: deleteBatchByPrimaryKey
    *@方法描述: 根据主键批量删除
    *@param tdCarOperateLog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteBatchByPrimaryKey(List<Object> ids);

    /**
    *@方法名: updateByPrimaryKeySelective
    *@方法描述: 根据主键选择更新单个对象
    *@param tdCarOperateLog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(CarOperateLog tdCarOperateLog);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tdCarOperateLog
    *@返回值 对象 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public CarOperateLog findById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tdCarOperateLog
    *@返回值 List<CarOperateLog> 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public List<CarOperateLog> selectByCondition(CarOperateLog tdCarOperateLog);

    /**
    *@方法名: selectCountByCondition
    *@方法描述: 分条件查询对象总数
    *@param tdCarOperateLog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public Integer selectCountByCondition(CarOperateLog tdCarOperateLog);

    /**
     * 根据用车记录 自动插入会员的车辆日志
    * @Description:TODO      
    * @author:JIAZHIPENG  
    * @time:2016-11-29 上午10:16:33   
    * @return String
     */
    public void autoCreateUseCarLog(PowerUsed powerUses,ButlerTask butlerTask);
    
	/**
	 * 根据车辆运营编号 和 创建时间 查询整天闲置的 单个车辆日志
	* @Description:TODO      
	* @author:JIAZHIPENG  
	* @time:2016-12-22 下午4:21:16   
	* @return String
	 */
	public CarOperateLog selectByOperateNumCreate(CarOperateLog log);
}
