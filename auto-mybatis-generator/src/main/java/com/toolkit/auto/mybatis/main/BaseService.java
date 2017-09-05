package com.toolkit.auto.mybatis.main;


import java.util.List;

public interface BaseService<T> {

	/**
     * @方法名: insertSelective
     * @方法描述: 根据对象插入单条记录
     * @param t
     * @return
     * @返回值 Integer 返回类型
     * @作者：FuShihua
     * @创建时间 2015年3月13日 上午12:59:39
     * @修改时间 2015年3月13日 上午12:59:39
     * @版本 V1.0
     * @异常
     */
    public Integer insertSelective(T t) throws Exception;

    /**
     * @方法名: insertBatch
     * @方法描述: 根据对象批量插入记录
     * @param t
     * @return
     * @throws Exception 
     * @返回值 Integer 返回类型
     * @作者：FuShihua
     * @创建时间 2015年3月13日 上午12:59:59
     * @修改时间 2015年3月13日 上午12:59:59
     * @版本 V1.0
     * @异常
     */
    public Integer insertBatch(List<T> t) throws Exception;

    /**
     * @方法名: deleteByPrimaryKey
     * @方法描述: 根据主键删除单条记录
     * @param t
     * @return
     * @返回值 Integer 返回类型
     * @作者：FuShihua
     * @创建时间 2015年3月13日 上午12:49:01
     * @修改时间 2015年3月13日 上午12:49:01
     * @版本 V1.0
     * @异常
     */
    public Integer deleteByPrimaryKey(Object id);


    /**
     * @方法名: deleteBatchByPrimaryKey
     * @方法描述: 根据主键批量删除
     * @param t
     * @return
     * @返回值 Integer 返回类型
     * @作者：FuShihua
     * @创建时间 2015年3月13日 上午12:44:25
     * @修改时间 2015年3月13日 上午12:44:25
     * @版本 V1.0
     * @异常
     */
    public Integer deleteBatchByPrimaryKey(List<Object> ids);


    /**
     * @方法名: updateByPrimaryKeySelective
     * @方法描述: 根据主键选择更新单个对象
     * @param t
     * @return
     * @返回值 Integer 返回类型
     * @作者：FuShihua
     * @创建时间 2015年3月13日 上午1:08:26
     * @修改时间 2015年3月13日 上午1:08:26
     * @版本 V1.0
     * @异常
     */
    public Integer updateByPrimaryKeySelective(T t);
    
    /**
     * @方法名: findById
     * @方法描述: 根据id 查询对象
     * @param t
     * @return
     * @返回值 Integer 返回类型
     * @作者：FuShihua
     * @创建时间 2016年7月5日 上午1:08:26
     * @修改时间 2016年3月13日 上午1:08:26
     * @版本 V1.0
     * @异常
     */
    public Object findById(Object id);

}
