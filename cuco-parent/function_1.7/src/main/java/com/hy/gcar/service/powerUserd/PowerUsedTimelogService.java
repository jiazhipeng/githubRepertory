package com.hy.gcar.service.powerUserd;


import com.hy.gcar.entity.PowerUsedTimelog;
import java.util.List;

/**
 * 
 * @author auto create
 * @param <PowerUsedTimelog>
 * @since 1.0,2016-09-12 10:36:30
 */
public interface PowerUsedTimelogService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tdPowerUsedTimelog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(PowerUsedTimelog tdPowerUsedTimelog) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tdPowerUsedTimelog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<PowerUsedTimelog> tdPowerUsedTimelog) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tdPowerUsedTimelog
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
    *@param tdPowerUsedTimelog
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
    *@param tdPowerUsedTimelog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(PowerUsedTimelog tdPowerUsedTimelog);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tdPowerUsedTimelog
    *@返回值 对象 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public PowerUsedTimelog findById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tdPowerUsedTimelog
    *@返回值 List<PowerUsedTimelog> 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public List<PowerUsedTimelog> selectByCondition(PowerUsedTimelog tdPowerUsedTimelog);

    /**
    *@方法名: selectCountByCondition
    *@方法描述: 分条件查询对象总数
    *@param tdPowerUsedTimelog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 10:36:30
    *@修改时间 2016年09月12日 10:36:30
    *@版本 V1.0
    *@异常
    **/
    public Integer selectCountByCondition(PowerUsedTimelog tdPowerUsedTimelog);


}
