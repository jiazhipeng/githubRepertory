package com.hy.gcar.service.chargeOrderLog;


import com.hy.gcar.entity.ChargeOrderLog;

import java.util.List;

/**
 * 
 * @author auto create
 * @param <ChargeOrderLog>
 * @since 1.0,2016-09-21 12:15:57
 */
public interface ChargeOrderLogService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tdChargeOrderLog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 12:15:57
    *@修改时间 2016年09月21日 12:15:57
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(ChargeOrderLog tdChargeOrderLog) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tdChargeOrderLog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 12:15:57
    *@修改时间 2016年09月21日 12:15:57
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<ChargeOrderLog> tdChargeOrderLog) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tdChargeOrderLog
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
    *@param tdChargeOrderLog
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
    *@param tdChargeOrderLog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 12:15:57
    *@修改时间 2016年09月21日 12:15:57
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(ChargeOrderLog tdChargeOrderLog);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tdChargeOrderLog
    *@返回值 对象 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 12:15:57
    *@修改时间 2016年09月21日 12:15:57
    *@版本 V1.0
    *@异常
    **/
    public ChargeOrderLog findById(Object id);

    /**
     * 根据订单号查询订单日志
     * @param orderNo
     * @return
     */
    public ChargeOrderLog findChargeOrderLogByOrderNo(String orderNo);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tdChargeOrderLog
    *@返回值 List<ChargeOrderLog> 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 12:15:57
    *@修改时间 2016年09月21日 12:15:57
    *@版本 V1.0
    *@异常
    **/
    public List<ChargeOrderLog> selectByCondition(ChargeOrderLog tdChargeOrderLog);

    /**
    *@方法名: selectCountByCondition
    *@方法描述: 分条件查询对象总数
    *@param tdChargeOrderLog
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 12:15:57
    *@修改时间 2016年09月21日 12:15:57
    *@版本 V1.0
    *@异常
    **/
    public Integer selectCountByCondition(ChargeOrderLog tdChargeOrderLog);


    /**
     * 创建权益充值消费日志表（账户）
     * @param chargeOrderLog
     * @return
     */
    public ChargeOrderLog createChargeOrderLog(ChargeOrderLog chargeOrderLog) throws Exception;


    /**
     * 修改益充值消费日志表为已经取消
     * @param chargeOrderLog
     * @return
     * @throws Exception
     */
    public ChargeOrderLog updateChargeOrderLogCancel(ChargeOrderLog chargeOrderLog) throws  Exception;

    /**
     * 修改益充值消费日志表为 已完成
     * @param chargeOrderLog
     * @return
     * @throws Exception
     */
    public ChargeOrderLog updateChargeOrderLogComplete(ChargeOrderLog chargeOrderLog) throws  Exception;


    /**
     * 更新未支付的订单记录
     * @param chargeOrderLog
     * @return
     */
    public ChargeOrderLog updateChargeOrderLogByNotPaid(ChargeOrderLog chargeOrderLog);

    /**
     * 支
     * @return
     */
    public ChargeOrderLog updateChargeOrderLogFail(ChargeOrderLog chargeOrderLog);

}
