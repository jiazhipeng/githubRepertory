package com.hy.security.service.api;

import com.hy.model.PageResult;
import com.hy.security.entity.Api;
import com.hy.security.entity.System;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * Created by WangShuai on 2016/7/21.
 */
public interface ApiService {
	
	/**
	 * 根据操作ID获取对应的api
	 * @param operationId
	 * @return
	 */
	public List<Api> findAllByOperationId(Long operationId);

    public Boolean exists(Long apiId);
    /**
     * 更新Api
     * @param api
     * @return
     */
    public Api update(Api api);

    public Api updateApiDisabled(Api api);

    public Api updateApiAuth(Api api);

    public List<Api> findByApi(Api api);

    /**
     * 根据用户ID和系统ID获取用户有效Api集合
     * @param userId
     * @param systemId
     * @return
     */
    public List<Api> findAvailableApisByUserIdAndSystemId(Long userId,Long systemId);

    /**
     * 根据系统ID查询所有api
     * @return
     */
    public List<Api> findAllBySystemId(Long systemId);

    /**
     * 根据系统ID查询所有可用api
     * @return
     */
    public List<Api> findAvailableBySystemId(Api api);

    /**
     * 同步本系统API
     * @param applicationContext
     * @param systemId
     * @return
     */
    public Object syncLocalApis(ApplicationContext applicationContext,Long systemId);

    /**
     * 同步子系统API
     * @param system
     * @return
     */
    public Object syncSubApis(System system);

    /**
     * 分页查询Api
     * @param api
     * @return
     */
    public PageResult findApiPage(Api api);
}
