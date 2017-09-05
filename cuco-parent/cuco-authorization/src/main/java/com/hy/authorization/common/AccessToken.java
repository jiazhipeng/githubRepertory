package com.hy.authorization.common;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;

import com.hy.common.utils.JedisUtils;
import com.hy.constant.Constant;

public class AccessToken {

	private static Logger logger = Logger.getLogger(AccessToken.class);
	
	public synchronized static String getAccessToken(String openid){
		Object accesstoken = JedisUtils.getObject(openid);
		if(accesstoken != null){
			logger.info("从 redis中获取 accesstoken---"+accesstoken.toString());
			return accesstoken.toString();
		}
		
		logger.info("根据openid生成一个accesstoken放到redis中---");
		try {
			String token ="";
			token += openid;
			token += RandomStringUtils.randomAlphanumeric(4);
			JedisUtils.setObject(openid, token, 720*60);
			accesstoken = JedisUtils.getObject(openid);
			logger.info("为空重新获取 accesstoken保存到redis中值为 ---" + accesstoken.toString());
		} catch (Exception e) {
			logger.error("获取accesstoken 异常---",e);
			return null;
		}
		return accesstoken.toString();
		
	}
	/**
	 * 用户登录每次都生成一个token，上面的方法用户只有一个token,因为openId是唯一的，所以每个用户只能登录一个系统
	 * @param openid
	 * @return
	 */
	public synchronized static String getAccessTokens(String openid){
		
		String accesstoken = null;
		try {
			accesstoken =Constant.SYSTEM_NO_SECURITY_TOKEN_PREFIX;
			accesstoken += openid;
			accesstoken += RandomStringUtils.randomAlphanumeric(4);
		} catch (Exception e) {
			logger.error("获取accesstoken 异常---",e);
			return null;
		}
		return accesstoken;
		
	}
	
	public synchronized static String getNoSecurityTokens(String systemCode){
		
		String accesstoken = null;
		try {
			accesstoken = Constant.SYSTEM_NO_SECURITY_TOKEN_PREFIX;
			accesstoken += systemCode;
		} catch (Exception e) {
			logger.error("获取accesstoken 异常---",e);
			return null;
		}
		return accesstoken;
		
	}
}
