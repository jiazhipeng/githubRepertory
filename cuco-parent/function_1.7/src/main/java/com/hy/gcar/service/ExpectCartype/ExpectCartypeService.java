package com.hy.gcar.service.ExpectCartype;


import com.hy.gcar.entity.ExpectCartype;
import java.util.List;

/**
 * 
 * @author auto create
 * @param <ExpectCartype>
 * @since 1.0,2016-11-03 19:33:53
 */
public interface ExpectCartypeService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tdExpectCartype
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年11月03日 19:33:53
    *@修改时间 2016年11月03日 19:33:53
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(ExpectCartype tdExpectCartype) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tdExpectCartype
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年11月03日 19:33:53
    *@修改时间 2016年11月03日 19:33:53
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<ExpectCartype> tdExpectCartype) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tdExpectCartype
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年11月03日 19:33:53
    *@修改时间 2016年11月03日 19:33:53
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteByPrimaryKey(Object id);

    /**
    *@方法名: deleteBatchByPrimaryKey
    *@方法描述: 根据主键批量删除
    *@param tdExpectCartype
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年11月03日 19:33:53
    *@修改时间 2016年11月03日 19:33:53
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteBatchByPrimaryKey(List<Object> ids);

    /**
    *@方法名: updateByPrimaryKeySelective
    *@方法描述: 根据主键选择更新单个对象
    *@param tdExpectCartype
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年11月03日 19:33:53
    *@修改时间 2016年11月03日 19:33:53
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(ExpectCartype tdExpectCartype);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tdExpectCartype
    *@返回值 对象 返回类型
    *@作者：hycx
    *@创建时间 2016年11月03日 19:33:53
    *@修改时间 2016年11月03日 19:33:53
    *@版本 V1.0
    *@异常
    **/
    public ExpectCartype findById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tdExpectCartype
    *@返回值 List<ExpectCartype> 返回类型
    *@作者：hycx
    *@创建时间 2016年11月03日 19:33:53
    *@修改时间 2016年11月03日 19:33:53
    *@版本 V1.0
    *@异常
    **/
    public List<ExpectCartype> selectByCondition(ExpectCartype tdExpectCartype);

    /**
    *@方法名: selectCountByCondition
    *@方法描述: 分条件查询对象总数
    *@param tdExpectCartype
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年11月03日 19:33:53
    *@修改时间 2016年11月03日 19:33:53
    *@版本 V1.0
    *@异常
    **/
    public Integer selectCountByCondition(ExpectCartype tdExpectCartype);


}
