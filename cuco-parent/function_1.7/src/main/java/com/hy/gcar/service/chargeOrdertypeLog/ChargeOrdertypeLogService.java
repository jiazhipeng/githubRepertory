package com.hy.gcar.service.chargeOrdertypeLog;


import com.hy.gcar.entity.ChargeOrdertypeLog;

import java.util.List;

/**
 * 
 * @author auto create
 * @param <ChargeOrdertypeLog>
 * @since 1.0,2016-09-21 12:15:57
 */
public interface ChargeOrdertypeLogService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tdChargeOrdertypeLog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 12:15:57
    *@修改时间 2016年09月21日 12:15:57
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(ChargeOrdertypeLog tdChargeOrdertypeLog) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tdChargeOrdertypeLog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 12:15:57
    *@修改时间 2016年09月21日 12:15:57
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<ChargeOrdertypeLog> tdChargeOrdertypeLog) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tdChargeOrdertypeLog
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
    *@param tdChargeOrdertypeLog
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
    *@param tdChargeOrdertypeLog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 12:15:57
    *@修改时间 2016年09月21日 12:15:57
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(ChargeOrdertypeLog tdChargeOrdertypeLog);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tdChargeOrdertypeLog
    *@返回值 对象 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 12:15:57
    *@修改时间 2016年09月21日 12:15:57
    *@版本 V1.0
    *@异常
    **/
    public ChargeOrdertypeLog findById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tdChargeOrdertypeLog
    *@返回值 List<ChargeOrdertypeLog> 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 12:15:57
    *@修改时间 2016年09月21日 12:15:57
    *@版本 V1.0
    *@异常
    **/
    public List<ChargeOrdertypeLog> selectByCondition(ChargeOrdertypeLog tdChargeOrdertypeLog);

    /**
    *@方法名: selectCountByCondition
    *@方法描述: 分条件查询对象总数
    *@param tdChargeOrdertypeLog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 12:15:57
    *@修改时间 2016年09月21日 12:15:57
    *@版本 V1.0
    *@异常
    **/
    public Integer selectCountByCondition(ChargeOrdertypeLog tdChargeOrdertypeLog);

    /**
     * 创建支付类型日志表
     * @param tdChargeOrdertypeLog
     * @return
     */
    public ChargeOrdertypeLog createChargeOrdertypeLog(ChargeOrdertypeLog tdChargeOrdertypeLog) throws Exception;

    /**
     * 修改是否打开网页
     * @param chargeOrdertypeLog
     * @return
     */
    public ChargeOrdertypeLog  updateIsOpenpage(ChargeOrdertypeLog chargeOrdertypeLog);



}
