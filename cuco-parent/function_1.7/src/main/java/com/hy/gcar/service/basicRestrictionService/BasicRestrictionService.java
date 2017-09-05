package com.hy.gcar.service.basicRestrictionService;


import com.hy.gcar.entity.BasicRestriction;
import java.util.List;

/**
 * 
 * @author auto create
 * @param <BasicRestriction>
 * @since 1.0,2016-11-07 11:28:06
 */
public interface BasicRestrictionService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tdBasicRestriction
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年11月07日 11:28:06
    *@修改时间 2016年11月07日 11:28:06
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(BasicRestriction tdBasicRestriction) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tdBasicRestriction
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年11月07日 11:28:06
    *@修改时间 2016年11月07日 11:28:06
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<BasicRestriction> tdBasicRestriction) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tdBasicRestriction
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年11月07日 11:28:06
    *@修改时间 2016年11月07日 11:28:06
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteByPrimaryKey(Object id);

    /**
    *@方法名: deleteBatchByPrimaryKey
    *@方法描述: 根据主键批量删除
    *@param tdBasicRestriction
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年11月07日 11:28:06
    *@修改时间 2016年11月07日 11:28:06
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteBatchByPrimaryKey(List<Object> ids);

    /**
    *@方法名: updateByPrimaryKeySelective
    *@方法描述: 根据主键选择更新单个对象
    *@param tdBasicRestriction
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年11月07日 11:28:06
    *@修改时间 2016年11月07日 11:28:06
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(BasicRestriction tdBasicRestriction);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tdBasicRestriction
    *@返回值 对象 返回类型
    *@作者：hycx
    *@创建时间 2016年11月07日 11:28:06
    *@修改时间 2016年11月07日 11:28:06
    *@版本 V1.0
    *@异常
    **/
    public BasicRestriction findById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tdBasicRestriction
    *@返回值 List<BasicRestriction> 返回类型
    *@作者：hycx
    *@创建时间 2016年11月07日 11:28:06
    *@修改时间 2016年11月07日 11:28:06
    *@版本 V1.0
    *@异常
    **/
    public List<BasicRestriction> selectByCondition(BasicRestriction tdBasicRestriction);

    /**
    *@方法名: selectCountByCondition
    *@方法描述: 分条件查询对象总数
    *@param tdBasicRestriction
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年11月07日 11:28:06
    *@修改时间 2016年11月07日 11:28:06
    *@版本 V1.0
    *@异常
    **/
    public Integer selectCountByCondition(BasicRestriction tdBasicRestriction);


}