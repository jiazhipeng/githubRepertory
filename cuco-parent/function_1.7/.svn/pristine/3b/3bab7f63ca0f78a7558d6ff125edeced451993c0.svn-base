package com.hy.gcar.service.powerUserd;


import com.hy.gcar.entity.ButlerTask;
import com.hy.gcar.entity.PowerUsed;
import com.hy.gcar.entity.PowerUsedCostLog;

import java.util.List;

/**
 * 
 * @author auto create
 * @param <PowerUsedCostLog>
 * @since 1.0,2016-09-25 14:43:20
 */
public interface PowerUsedCostLogService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tdPowerUsedCostLog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月25日 14:43:20
    *@修改时间 2016年09月25日 14:43:21
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(PowerUsedCostLog tdPowerUsedCostLog) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tdPowerUsedCostLog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月25日 14:43:21
    *@修改时间 2016年09月25日 14:43:21
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<PowerUsedCostLog> tdPowerUsedCostLog) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tdPowerUsedCostLog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月25日 14:43:21
    *@修改时间 2016年09月25日 14:43:21
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteByPrimaryKey(Object id);

    /**
    *@方法名: deleteBatchByPrimaryKey
    *@方法描述: 根据主键批量删除
    *@param tdPowerUsedCostLog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月25日 14:43:21
    *@修改时间 2016年09月25日 14:43:21
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteBatchByPrimaryKey(List<Object> ids);

    /**
    *@方法名: updateByPrimaryKeySelective
    *@方法描述: 根据主键选择更新单个对象
    *@param tdPowerUsedCostLog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月25日 14:43:21
    *@修改时间 2016年09月25日 14:43:21
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(PowerUsedCostLog tdPowerUsedCostLog);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tdPowerUsedCostLog
    *@返回值 对象 返回类型
    *@作者：hycx
    *@创建时间 2016年09月25日 14:43:21
    *@修改时间 2016年09月25日 14:43:21
    *@版本 V1.0
    *@异常
    **/
    public PowerUsedCostLog findById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tdPowerUsedCostLog
    *@返回值 List<PowerUsedCostLog> 返回类型
    *@作者：hycx
    *@创建时间 2016年09月25日 14:43:21
    *@修改时间 2016年09月25日 14:43:21
    *@版本 V1.0
    *@异常
    **/
    public List<PowerUsedCostLog> selectByCondition(PowerUsedCostLog tdPowerUsedCostLog);

    /**
    *@方法名: selectCountByCondition
    *@方法描述: 分条件查询对象总数
    *@param tdPowerUsedCostLog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月25日 14:43:21
    *@修改时间 2016年09月25日 14:43:21
    *@版本 V1.0
    *@异常
    **/
    public Integer selectCountByCondition(PowerUsedCostLog tdPowerUsedCostLog);


    /**
     * 每天扣用车费用 ，插入用车费用日志表
     * @param butlerTask
     * @param powerUsed
     * @throws Exception
     */
    public void deductionOfFare(ButlerTask butlerTask, PowerUsed powerUsed) throws Exception;


}
