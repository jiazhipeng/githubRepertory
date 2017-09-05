package com.hy.gcar.service.channel;


import com.hy.gcar.entity.ChannelDetail;
import java.util.List;

/**
 * 
 * @author auto create
 * @param <ChannelDetail>
 * @since 1.0,2016-10-27 13:35:43
 */
public interface ChannelDetailService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tdChannelDetail
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年10月27日 13:35:43
    *@修改时间 2016年10月27日 13:35:43
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(ChannelDetail tdChannelDetail) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tdChannelDetail
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年10月27日 13:35:43
    *@修改时间 2016年10月27日 13:35:43
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<ChannelDetail> tdChannelDetail) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tdChannelDetail
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年10月27日 13:35:43
    *@修改时间 2016年10月27日 13:35:43
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteByPrimaryKey(Object id);

    /**
    *@方法名: deleteBatchByPrimaryKey
    *@方法描述: 根据主键批量删除
    *@param tdChannelDetail
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年10月27日 13:35:43
    *@修改时间 2016年10月27日 13:35:43
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteBatchByPrimaryKey(List<Object> ids);

    /**
    *@方法名: updateByPrimaryKeySelective
    *@方法描述: 根据主键选择更新单个对象
    *@param tdChannelDetail
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年10月27日 13:35:43
    *@修改时间 2016年10月27日 13:35:43
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(ChannelDetail tdChannelDetail);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tdChannelDetail
    *@返回值 对象 返回类型
    *@作者：hycx
    *@创建时间 2016年10月27日 13:35:43
    *@修改时间 2016年10月27日 13:35:43
    *@版本 V1.0
    *@异常
    **/
    public ChannelDetail findById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tdChannelDetail
    *@返回值 List<ChannelDetail> 返回类型
    *@作者：hycx
    *@创建时间 2016年10月27日 13:35:43
    *@修改时间 2016年10月27日 13:35:43
    *@版本 V1.0
    *@异常
    **/
    public List<ChannelDetail> selectByCondition(ChannelDetail tdChannelDetail);

    /**
    *@方法名: selectCountByCondition
    *@方法描述: 分条件查询对象总数
    *@param tdChannelDetail
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年10月27日 13:35:43
    *@修改时间 2016年10月27日 13:35:43
    *@版本 V1.0
    *@异常
    **/
    public Integer selectCountByCondition(ChannelDetail tdChannelDetail);


}
