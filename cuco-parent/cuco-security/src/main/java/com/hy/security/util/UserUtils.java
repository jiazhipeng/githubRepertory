package com.hy.security.util;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.hy.common.utils.JedisUtils;
import com.hy.constant.Constant;
import com.hy.security.child.service.ParentServiceImpl;
import com.hy.security.entity.User;
import com.hy.security.service.user.AccessTokenService;

public class UserUtils {

	
	/**
	 * 根据用户token获取用户redis中的权限信息
	 * @param tokenID
	 * @return
	 */
	public static User getUserByTokenID(String tokenID){
		User user = null;
		Object jsonUser = JedisUtils.getObject(tokenID);
		if(null!=jsonUser){
			user = JSON.parseObject(jsonUser.toString(),User.class);
		}
		return user;
	}
	
	
	public static User getUserFromRedisAndDB(String tokenID,AccessTokenService accessTokenService){
		User user = null;
		user = getUserByTokenID(tokenID);
		if(user == null){
			//说明用户权限已经更新，需要去数据库中查询，如果数据库中也没有的话，那么说明用户权限已经完全被禁用了
			user = accessTokenService.getUserAuthByUserToken(tokenID);
		}
		if(user != null){
			JedisUtils.setObject(tokenID, JSON.toJSON(user), Constant.TOKEN_LIVETIME);
			UserUtils.setTokenEndDateTime(tokenID);
		}
		return user;
		
	}
	
	public static Date getTokenEndDateTime(String tokenID){
		Date endTime = (Date) JedisUtils.getObject(tokenID+Constant.USER_TOKEN_TIMEOUT_SUFFIX);
		return endTime;
	}
	
	public static void setTokenEndDateTime(String tokenID){
		JedisUtils.setObject(tokenID+Constant.USER_TOKEN_TIMEOUT_SUFFIX, StringUtil.getDateByTime(new Date().getTime()+Constant.TOKEN_ENDTIME_MILLISECOND), Constant.TOKEN_LIVETIME);
	}
}
