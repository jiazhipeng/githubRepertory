package com.hy.security.service.permissionExchange;


import com.hy.security.entity.PermissionExchange;
import java.util.List;

/**
 * 
 * @author auto create
 * @param <PermissionExchange>
 * @since 1.0,2016-08-03 15:21:06
 */
public interface PermissionExchangeService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tdPermissionExchange
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月03日 15:21:06
    *@修改时间 2016年08月03日 15:21:07
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(PermissionExchange tdPermissionExchange) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tdPermissionExchange
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月03日 15:21:07
    *@修改时间 2016年08月03日 15:21:07
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<PermissionExchange> tdPermissionExchange) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tdPermissionExchange
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月03日 15:21:07
    *@修改时间 2016年08月03日 15:21:07
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteByPrimaryKey(Object id);

    /**
    *@方法名: deleteBatchByPrimaryKey
    *@方法描述: 根据主键批量删除
    *@param tdPermissionExchange
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月03日 15:21:07
    *@修改时间 2016年08月03日 15:21:07
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteBatchByPrimaryKey(List<Object> ids);

    /**
    *@方法名: updateByPrimaryKeySelective
    *@方法描述: 根据主键选择更新单个对象
    *@param tdPermissionExchange
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月03日 15:21:07
    *@修改时间 2016年08月03日 15:21:07
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(PermissionExchange tdPermissionExchange);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tdPermissionExchange
    *@返回值 对象 返回类型
    *@作者：hycx
    *@创建时间 2016年08月03日 15:21:07
    *@修改时间 2016年08月03日 15:21:07
    *@版本 V1.0
    *@异常
    **/
    public PermissionExchange findById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tdPermissionExchange
    *@返回值 List<PermissionExchange> 返回类型
    *@作者：hycx
    *@创建时间 2016年08月03日 15:21:07
    *@修改时间 2016年08月03日 15:21:07
    *@版本 V1.0
    *@异常
    **/
    public List<PermissionExchange> selectByCondition(PermissionExchange tdPermissionExchange);

    /**
    *@方法名: selectCountByCondition
    *@方法描述: 分条件查询对象总数
    *@param tdPermissionExchange
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年08月03日 15:21:07
    *@修改时间 2016年08月03日 15:21:07
    *@版本 V1.0
    *@异常
    **/
    public Integer selectCountByCondition(PermissionExchange tdPermissionExchange);
/**
 * 更新权限变更
 * @param list
 */
	public void updateBatch(List<PermissionExchange> list);
	/**
	 * 查询所有系统id
	 * @param exchange
	 * @return
	 */
	public List<PermissionExchange> findSystemIds();
	/**
	 * 查询未发送的权限变更信息
	 * @param exchange
	 * @return
	 */
	public List<PermissionExchange> selectNotSendExchange(PermissionExchange exchange);



}
