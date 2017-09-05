package com.hy.gcar.service.chargeOrder;


import com.hy.gcar.entity.ChargeOrder;

import java.util.List;

/**
 * 
 * @author auto create
 * @param <ChargeOrder>
 * @since 1.0,2016-09-21 17:18:27
 */
public interface ChargeOrderService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tdChargeOrder
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 17:18:27
    *@修改时间 2016年09月21日 17:18:27
    *@版本 V1.0
    *@异常
    **/
    public Integer insertSelective(ChargeOrder tdChargeOrder) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tdChargeOrder
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 17:18:27
    *@修改时间 2016年09月21日 17:18:27
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<ChargeOrder> tdChargeOrder) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tdChargeOrder
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 17:18:27
    *@修改时间 2016年09月21日 17:18:27
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteByPrimaryKey(Object id);

    /**
    *@方法名: deleteBatchByPrimaryKey
    *@方法描述: 根据主键批量删除
    *@param tdChargeOrder
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 17:18:27
    *@修改时间 2016年09月21日 17:18:27
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteBatchByPrimaryKey(List<Object> ids);

    /**
    *@方法名: updateByPrimaryKeySelective
    *@方法描述: 根据主键选择更新单个对象
    *@param tdChargeOrder
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 17:18:27
    *@修改时间 2016年09月21日 17:18:27
    *@版本 V1.0
    *@异常
    **/
    public Integer updateByPrimaryKeySelective(ChargeOrder tdChargeOrder);


    /**
     * 根据主键更新单个对象
     * @param chargeOrder
     * @return
     */
    public Integer updateByChargeOrder(ChargeOrder chargeOrder);


    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tdChargeOrder
    *@返回值 对象 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 17:18:27
    *@修改时间 2016年09月21日 17:18:27
    *@版本 V1.0
    *@异常
    **/
    public ChargeOrder findById(Object id);


    /**
     * 根据订单号查询订单（第三方支付流水单号）
     * @param chargeOrder
     * @return
     */
    public ChargeOrder findChargeOrderByOrderNo(String orderNo);

    /**
     * 根据订单号查询订单
     * @param chargeOrderNo
     * @return
     */
    public ChargeOrder findChargeOrderByChargeOrderNo(String chargeOrderNo);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tdChargeOrder
    *@返回值 List<ChargeOrder> 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 17:18:27
    *@修改时间 2016年09月21日 17:18:27
    *@版本 V1.0
    *@异常
    **/
    public List<ChargeOrder> selectByCondition(ChargeOrder tdChargeOrder);

    /**
    *@方法名: selectCountByCondition
    *@方法描述: 分条件查询对象总数
    *@param tdChargeOrder
    *@返回值 Integer 返回类型
    *@作者：hycx
    *@创建时间 2016年09月21日 17:18:27
    *@修改时间 2016年09月21日 17:18:27
    *@版本 V1.0
    *@异常
    **/
    public Integer selectCountByCondition(ChargeOrder tdChargeOrder);

    /**
     * 创建订单详情
     * @param chargeOrder
     */
    public ChargeOrder createChargeOrder(ChargeOrder chargeOrder) throws Exception;


    /**
     * 修改订单状态为取消状态
     * @param chargeOrder
     * @return
     * @throws Exception
     */
    public ChargeOrder updateChargeOrderCancel(ChargeOrder chargeOrder) throws  Exception;


    /**
     * 生成订单号
     * @return
     */
    public String createOrderNo();



    /**
     * 修改订单状态为完成状态
     * @param chargeOrder
     * @return
     * @throws Exception
     */
    public ChargeOrder updateChargeOrderComplete(ChargeOrder chargeOrder) throws  Exception;


    /**
     * 修改支付方式
     * @param chargeOrder
     * @return
     * @throws Exception
     */
    public ChargeOrder updateChargeOrderByPayType(ChargeOrder chargeOrder) throws  Exception;

}
