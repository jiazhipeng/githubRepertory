package com.hy.common.utils;

import java.util.Date;

import org.apache.log4j.Logger;

import com.hy.constant.Constant;
import com.hy.weixin.entity.AccessToken;
import com.hy.weixin.utils.WeiXinUtils;

public class AccessTokenUtils {
	private static Logger logger = Logger.getLogger(AccessTokenUtils.class);


	
	
	public static synchronized String getAccessToken(){
		String tokenKey = "accesstoken_hy_security";
		String tokenKeyGenerator = tokenKey + "_" + "generator";
		Object accesstoken = JedisUtils.getObject(tokenKey);
		if(accesstoken != null){
			logger.info("从 redis 获取 accesstoken>>>>>>>>>>>>>"+accesstoken.toString());
			logger.info("从 redis 获取 accesstoken-generator>>>>>>>>>>>>>"+JedisUtils.getObject(tokenKeyGenerator));
			return accesstoken.toString();
		}
		
		logger.info("calculationprofit.....");
		try {
			AccessToken accessToken = WeiXinUtils.getToken(Constant.APPID, Constant.APPSECRET);
			String tokenInit  = accessToken.getToken();
			logger.info("accessToken.getToken()>>>>>>>>>>>>" + tokenInit);
			JedisUtils.setObject(tokenKey, tokenInit, 7200);
			
			JedisUtils.setObject(tokenKeyGenerator, tokenInit+"["+Constant.APPID+" __ "+Constant.APPSECRET+" __ "+DateUtils.formatDateTime(new Date())+" __ "+"]", 7200);
			accesstoken = JedisUtils.getObject(tokenKey);
			logger.info("accesstoken 为空 重新获取 accesstoken保存到redis中值为 >>>>>>>>>>>>" + tokenInit);
		} catch (Exception e) {
			logger.error("获取accesstoken 异常.......",e);
			return null;
		}
		return accesstoken.toString();
		
		
	}
	
	public static String getTicket() throws Exception{
		/*>>>>>>>>>>>>>>>>>>>>>获取ticket开始>>>>>>>>>>>>>>>>>>>>>>>>>*/
		String accessToken = AccessTokenUtils.getAccessToken();
//		String accessTokenMD5 = MD5Util.MD5Encode(accessToken);
		String ticketStr = JedisUtils.get(accessToken);
		
		logger.info("aaa---------------------------" + accessToken);
		logger.info("根据accessToken从redis 获取 ticketObj >>>>>" +accessToken + ",,,,,"+ ticketStr);
		if(ticketStr == null || "".equals(ticketStr)){
			logger.info("ticketObj为空.....调用接口重新获取 ticket ...");
			String ticket = WeiXinUtils.getTokenAPI(accessToken);
			JedisUtils.set(accessToken, ticket, 7200);
			ticketStr = JedisUtils.get(accessToken);

		}
		/*>>>>>>>>>>>>>>>>>>>>>获取ticket结束>>>>>>>>>>>>>>>>>>>>>>>>>*/
		return ticketStr;
		
		
		
	}

}
