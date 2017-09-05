package com.hy.gcar.dao;


import com.hy.gcar.entity.TransferList;

public interface TransferListMapper<T> extends BaseDao<T> {

    public Integer deleteByTaskID(TransferList transferList);
}
