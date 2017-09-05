package com.hy.gcar.service.basic;


import com.hy.gcar.entity.BasicService;
import java.util.List;

/**
 * 
 * @author auto create
 * @param <BasicService>
 * @since 1.0,2016-09-19 16:39:26
 */
public interface BasicServiceService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tcBasicService
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月19日 16:39:26
    *@修改时间 2016年09月19日 16:39:26
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(BasicService tcBasicService) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tcBasicService
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月19日 16:39:26
    *@修改时间 2016年09月19日 16:39:26
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<BasicService> tcBasicService) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tcBasicService
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月19日 16:39:26
    *@修改时间 2016年09月19日 16:39:26
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteByPrimaryKey(Object id);

    /**
    *@方法名: deleteBatchByPrimaryKey
    *@方法描述: 根据主键批量删除
    *@param tcBasicService
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月19日 16:39:26
    *@修改时间 2016年09月19日 16:39:26
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteBatchByPrimaryKey(List<Object> ids);

    /**
    *@方法名: updateByPrimaryKeySelective
    *@方法描述: 根据主键选择更新单个对象
    *@param tcBasicService
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月19日 16:39:26
    *@修改时间 2016年09月19日 16:39:26
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(BasicService tcBasicService);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tcBasicService
    *@返回值 对象 返回类型
    *@作者：hycx
    *@创建时间 2016年09月19日 16:39:26
    *@修改时间 2016年09月19日 16:39:26
    *@版本 V1.0
    *@异常
    **/
    public BasicService findById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tcBasicService
    *@返回值 List<BasicService> 返回类型
    *@作者：hycx
    *@创建时间 2016年09月19日 16:39:26
    *@修改时间 2016年09月19日 16:39:26
    *@版本 V1.0
    *@异常
    **/
    public List<BasicService> selectByCondition(BasicService tcBasicService);

    /**
    *@方法名: selectCountByCondition
    *@方法描述: 分条件查询对象总数
    *@param tcBasicService
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月19日 16:39:26
    *@修改时间 2016年09月19日 16:39:26
    *@版本 V1.0
    *@异常
    **/
    public Integer selectCountByCondition(BasicService tcBasicService);


}
