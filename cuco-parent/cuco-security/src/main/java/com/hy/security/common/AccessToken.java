package com.hy.security.common;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;

import com.hy.common.utils.JedisUtils;
import com.hy.constant.Constant;

public class AccessToken {

	private static Logger logger = Logger.getLogger(AccessToken.class);
	

	public static String generateUserAccessToken(){
		
		return Constant.USER_TOKEN_PREFIX+RandomStringUtils.randomAlphanumeric(16);
		
	}
	
	public synchronized static String getNoSecurityTokens(String systemId){
		
		String accesstoken = null;
		try {
			accesstoken =Constant.SYSTEM_NO_SECURITY_TOKEN_PREFIX;
			accesstoken += systemId;
		} catch (Exception e) {
			logger.error("获取accesstoken 异常---",e);
			return null;
		}
		return accesstoken;
		
	}
}
