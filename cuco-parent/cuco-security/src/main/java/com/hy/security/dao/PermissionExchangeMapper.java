package com.hy.security.dao;

import java.util.List;

import com.hy.security.entity.PermissionExchange;

public interface PermissionExchangeMapper<T> extends BaseDao<T> {

	List<PermissionExchange> findSystemIds();

	void updateBatch(List<PermissionExchange> list);

	List<PermissionExchange> selectNotSendExchange(PermissionExchange exchange);

}
