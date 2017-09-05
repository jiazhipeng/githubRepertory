package com.hy.security.service.user;

import java.util.List;

import com.hy.security.entity.AccessToken;
import com.hy.security.entity.User;

public interface AccessTokenService {

	/**
	 * 保存用户token信息 
	 * @param accessToken
	 */
	public void save(AccessToken accessToken);
	
	/**
	 * 根据选定条件查询token
	 * @param accessToken
	 * @return
	 */
	public List<AccessToken> selectByCondition(AccessToken accessToken);
	
	/**
	 * 根据主键删除记录
	 * @param id
	 */
	public void delById(Long id);
	
	/**
	 * 根据用户登录token删除 
	 * @param accessToken
	 */
	public void delByToken(String userToken);
	
	/**
	 * 根据用户token从数据库中获得用户实时权限
	 * @param userToken
	 * @return
	 */
	public User getUserAuthByUserToken(String userToken);
}
