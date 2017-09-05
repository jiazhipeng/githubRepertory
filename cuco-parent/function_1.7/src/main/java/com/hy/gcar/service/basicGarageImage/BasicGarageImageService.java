package com.hy.gcar.service.basicGarageImage;


import com.hy.gcar.entity.BasicGarageImage;
import java.util.List;

/**
 * 
 * @author auto create
 * @param <BasicGarageImage>
 * @since 1.0,2016-08-19 11:47:52
 */
public interface BasicGarageImageService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tcBasicGarageImage
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月19日 11:47:52
    *@修改时间 2016年08月19日 11:47:52
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(BasicGarageImage tcBasicGarageImage) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tcBasicGarageImage
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月19日 11:47:52
    *@修改时间 2016年08月19日 11:47:52
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<BasicGarageImage> tcBasicGarageImage) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tcBasicGarageImage
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月19日 11:47:52
    *@修改时间 2016年08月19日 11:47:52
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteByPrimaryKey(Object id);

    /**
    *@方法名: deleteBatchByPrimaryKey
    *@方法描述: 根据主键批量删除
    *@param tcBasicGarageImage
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月19日 11:47:52
    *@修改时间 2016年08月19日 11:47:52
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteBatchByPrimaryKey(List<Object> ids);

    /**
    *@方法名: updateByPrimaryKeySelective
    *@方法描述: 根据主键选择更新单个对象
    *@param tcBasicGarageImage
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月19日 11:47:52
    *@修改时间 2016年08月19日 11:47:52
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(BasicGarageImage tcBasicGarageImage);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tcBasicGarageImage
    *@返回值 对象 返回类型
    *@作者：hycx
    *@创建时间 2016年08月19日 11:47:52
    *@修改时间 2016年08月19日 11:47:52
    *@版本 V1.0
    *@异常
    **/
    public BasicGarageImage findById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tcBasicGarageImage
    *@返回值 List<BasicGarageImage> 返回类型
    *@作者：hycx
    *@创建时间 2016年08月19日 11:47:52
    *@修改时间 2016年08月19日 11:47:52
    *@版本 V1.0
    *@异常
    **/
    public List<BasicGarageImage> selectByCondition(BasicGarageImage tcBasicGarageImage);

    /**
    *@方法名: selectCountByCondition
    *@方法描述: 分条件查询对象总数
    *@param tcBasicGarageImage
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月19日 11:47:52
    *@修改时间 2016年08月19日 11:47:52
    *@版本 V1.0
    *@异常
    **/
    public Integer selectCountByCondition(BasicGarageImage tcBasicGarageImage);


}
