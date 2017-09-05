package com.hy.security.service.LoginLog;


import com.hy.security.entity.LoginLog;
import java.util.List;

/**
 * 
 * @author auto create
 * @param <LoginLog>
 * @since 1.0,2016-08-02 15:27:53
 */
public interface LoginLogService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tdLoginLog
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月02日 15:27:53
    *@修改时间 2016年08月02日 15:27:53
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(LoginLog tdLoginLog) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tdLoginLog
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月02日 15:27:53
    *@修改时间 2016年08月02日 15:27:53
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<LoginLog> tdLoginLog) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tdLoginLog
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月02日 15:27:53
    *@修改时间 2016年08月02日 15:27:53
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteByPrimaryKey(Object id);

    /**
    *@方法名: deleteBatchByPrimaryKey
    *@方法描述: 根据主键批量删除
    *@param tdLoginLog
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月02日 15:27:53
    *@修改时间 2016年08月02日 15:27:53
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteBatchByPrimaryKey(List<Object> ids);

    /**
    *@方法名: updateByPrimaryKeySelective
    *@方法描述: 根据主键选择更新单个对象
    *@param tdLoginLog
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月02日 15:27:53
    *@修改时间 2016年08月02日 15:27:53
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(LoginLog tdLoginLog);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tdLoginLog
    *@返回值 对象 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月02日 15:27:53
    *@修改时间 2016年08月02日 15:27:53
    *@版本 V1.0
    *@异常
    **/
    public LoginLog findById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tdLoginLog
    *@返回值 List<LoginLog> 返回类型
    *@作者：q.p.x
    *@创建时间 2016年08月02日 15:27:53
    *@修改时间 2016年08月02日 15:27:53
    *@版本 V1.0
    *@异常
    **/
    public List<LoginLog> selectByCondition(LoginLog tdLoginLog);
    
    /**根据用户ID查询用户登录信息
     * @return
     */
    public List<LoginLog> selectLoginListByUserId(Long userId);


}
