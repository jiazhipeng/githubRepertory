package com.hy.session.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;

import com.hy.constant.Constant;
import com.hy.security.common.AccessToken;

/**
 * HySession的接口
 * @author duzhencheng
 * @author 1mobility
 */
public class HySessionFactory {

		//自定义session名
		protected static String categoryKey = "HaiYiSession";
		//cookie里面的存储名称
		private static String cookieName = "HaiYi-Access-Token";
		
		public static HySession getHaiYiSession(HttpServletRequest request, HttpServletResponse response, String openid) {
			return new HySessionImpl(getSessionID(request,response,openid));
		}
		//获取sessionID，根据当前登录用户，从cookie里面获取，如果已经存在，直接获取该用户的seeionID，如果不存在，根据一定的规则生成一个新的sessionID放到cookie中
		private static String getSessionID(HttpServletRequest request, HttpServletResponse response, String openid) {

			return AccessToken.generateUserAccessToken();
	    }
		
}
