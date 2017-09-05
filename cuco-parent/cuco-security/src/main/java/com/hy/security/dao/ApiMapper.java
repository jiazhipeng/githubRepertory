package com.hy.security.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hy.security.entity.Api;

public interface ApiMapper<T> extends BaseDao<T> {
	
	public List<Api> findAllByOperationId(@Param("operation_id") Long operationId);

	public Integer countByCondition(T t);

	List<Api> findAvailableApisByUserIdAndSystemId(@Param("user_id")Long userId,@Param("system_id")Long systemId);
}
