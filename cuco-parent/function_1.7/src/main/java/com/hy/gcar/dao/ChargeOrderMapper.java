package com.hy.gcar.dao;


import com.hy.gcar.entity.ChargeOrder;

public interface ChargeOrderMapper<T> extends BaseDao<T> {

    public Integer updateByChargeOrder(ChargeOrder chargeOrder);
}
