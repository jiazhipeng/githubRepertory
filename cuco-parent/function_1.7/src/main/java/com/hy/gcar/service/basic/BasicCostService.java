package com.hy.gcar.service.basic;


import com.hy.gcar.entity.BasicCost;
import java.util.List;

/**
 * 
 * @author auto create
 * @param <BasicCost>
 * @since 1.0,2016-09-23 19:19:52
 */
public interface BasicCostService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tcBasicCost
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月23日 19:19:52
    *@修改时间 2016年09月23日 19:19:52
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(BasicCost tcBasicCost) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tcBasicCost
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月23日 19:19:52
    *@修改时间 2016年09月23日 19:19:52
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<BasicCost> tcBasicCost) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tcBasicCost
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月23日 19:19:52
    *@修改时间 2016年09月23日 19:19:52
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteByPrimaryKey(Object id);

    /**
    *@方法名: deleteBatchByPrimaryKey
    *@方法描述: 根据主键批量删除
    *@param tcBasicCost
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月23日 19:19:52
    *@修改时间 2016年09月23日 19:19:52
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteBatchByPrimaryKey(List<Object> ids);

    /**
    *@方法名: updateByPrimaryKeySelective
    *@方法描述: 根据主键选择更新单个对象
    *@param tcBasicCost
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月23日 19:19:52
    *@修改时间 2016年09月23日 19:19:52
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(BasicCost tcBasicCost);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tcBasicCost
    *@返回值 对象 返回类型
    *@作者：hycx
    *@创建时间 2016年09月23日 19:19:52
    *@修改时间 2016年09月23日 19:19:52
    *@版本 V1.0
    *@异常
    **/
    public BasicCost findById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tcBasicCost
    *@返回值 List<BasicCost> 返回类型
    *@作者：hycx
    *@创建时间 2016年09月23日 19:19:52
    *@修改时间 2016年09月23日 19:19:52
    *@版本 V1.0
    *@异常
    **/
    public List<BasicCost> selectByCondition(BasicCost tcBasicCost);

    /**
    *@方法名: selectCountByCondition
    *@方法描述: 分条件查询对象总数
    *@param tcBasicCost
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月23日 19:19:52
    *@修改时间 2016年09月23日 19:19:52
    *@版本 V1.0
    *@异常
    **/
    public Integer selectCountByCondition(BasicCost tcBasicCost);
    public BasicCost selectBasicCost();

}
