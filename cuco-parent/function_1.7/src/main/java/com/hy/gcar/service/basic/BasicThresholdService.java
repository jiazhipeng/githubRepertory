package com.hy.gcar.service.basic;


import com.hy.gcar.entity.BasicThreshold;
import java.util.List;

/**
 * 
 * @author auto create
 * @param <BasicThreshold>
 * @since 1.0,2016-09-12 17:51:05
 */
public interface BasicThresholdService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tcBasicThreshold
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 17:51:05
    *@修改时间 2016年09月12日 17:51:05
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(BasicThreshold tcBasicThreshold) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tcBasicThreshold
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 17:51:05
    *@修改时间 2016年09月12日 17:51:05
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<BasicThreshold> tcBasicThreshold) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tcBasicThreshold
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
    *@param tcBasicThreshold
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
    *@param tcBasicThreshold
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 17:51:05
    *@修改时间 2016年09月12日 17:51:05
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(BasicThreshold tcBasicThreshold);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tcBasicThreshold
    *@返回值 对象 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 17:51:05
    *@修改时间 2016年09月12日 17:51:05
    *@版本 V1.0
    *@异常
    **/
    public BasicThreshold findById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tcBasicThreshold
    *@返回值 List<BasicThreshold> 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 17:51:05
    *@修改时间 2016年09月12日 17:51:05
    *@版本 V1.0
    *@异常
    **/
    public List<BasicThreshold> selectByCondition(BasicThreshold tcBasicThreshold);

    /**
    *@方法名: selectCountByCondition
    *@方法描述: 分条件查询对象总数
    *@param tcBasicThreshold
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月12日 17:51:05
    *@修改时间 2016年09月12日 17:51:05
    *@版本 V1.0
    *@异常
    **/
    public Integer selectCountByCondition(BasicThreshold tcBasicThreshold);

	public BasicThreshold selectBasicThreshold();


}
