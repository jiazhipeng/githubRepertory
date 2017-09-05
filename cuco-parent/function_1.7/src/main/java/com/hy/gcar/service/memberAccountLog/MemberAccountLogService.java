package com.hy.gcar.service.memberAccountLog;


import com.hy.gcar.entity.MemberAccountLog;

import java.util.List;

/**
 * 
 * @author auto create
 * @param <MemberAccountLog>
 * @since 1.0,2016-09-21 12:15:57
 */
public interface MemberAccountLogService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tdMemberAccountLog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 12:15:57
    *@修改时间 2016年09月21日 12:15:57
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(MemberAccountLog tdMemberAccountLog) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tdMemberAccountLog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 12:15:57
    *@修改时间 2016年09月21日 12:15:57
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<MemberAccountLog> tdMemberAccountLog) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tdMemberAccountLog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 12:15:57
    *@修改时间 2016年09月21日 12:15:57
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteByPrimaryKey(Object id);

    /**
    *@方法名: deleteBatchByPrimaryKey
    *@方法描述: 根据主键批量删除
    *@param tdMemberAccountLog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 12:15:57
    *@修改时间 2016年09月21日 12:15:57
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteBatchByPrimaryKey(List<Object> ids);

    /**
    *@方法名: updateByPrimaryKeySelective
    *@方法描述: 根据主键选择更新单个对象
    *@param tdMemberAccountLog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 12:15:57
    *@修改时间 2016年09月21日 12:15:57
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(MemberAccountLog tdMemberAccountLog);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tdMemberAccountLog
    *@返回值 对象 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 12:15:57
    *@修改时间 2016年09月21日 12:15:57
    *@版本 V1.0
    *@异常
    **/
    public MemberAccountLog findById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tdMemberAccountLog
    *@返回值 List<MemberAccountLog> 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 12:15:57
    *@修改时间 2016年09月21日 12:15:57
    *@版本 V1.0
    *@异常
    **/
    public List<MemberAccountLog> selectByCondition(MemberAccountLog tdMemberAccountLog);

    /**
    *@方法名: selectCountByCondition
    *@方法描述: 分条件查询对象总数
    *@param tdMemberAccountLog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 12:15:57
    *@修改时间 2016年09月21日 12:15:57
    *@版本 V1.0
    *@异常
    **/
    public Integer selectCountByCondition(MemberAccountLog tdMemberAccountLog);


    /**
     * 权益充值消费日志表（账户）
     * @param tdMemberAccountLog
     * @return
     */
    public MemberAccountLog createMemberAccountLog(MemberAccountLog tdMemberAccountLog) throws Exception;



}
