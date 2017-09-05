package com.hy.security.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hy.common.utils.JedisUtils;
import com.hy.constant.Constant;
import com.hy.enums.ServerStatus;
import com.hy.exception.RuntimeExceptionWarn;
import com.hy.security.service.system.SystemService;
import com.hy.security.util.MemoryToken;
import com.hy.security.entity.System;

/**
 * 子系统与权限系统通信拦截器
 * @author zhubing
 *
 */
public class ChildSystemInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private SystemService systemService;
	private String requestURI;
	private String contextPath;
	private String url;
	private String tokenID;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		//得到uri请求路径
		requestURI = request.getRequestURI();
		contextPath = request.getContextPath();
		url = requestURI.substring(contextPath.length());
		logger.info("子系统请求URI为---"+requestURI);
		logger.info("子系统项目路径为---"+contextPath);
		logger.info("子系统请求路径为---"+url);
		
		//用户注册,用户登录，获取验证码跳过该操作不需要走权限控制--加上微信回调
		if(-1 != url.indexOf("child/login")){
			return true;
		}
		//从请求头里面拿出token
		tokenID = request.getHeader("System-Access-Token");
		this.logger.info("取出的token---"+tokenID);
		if(null == tokenID){
			throw new RuntimeExceptionWarn(ServerStatus.SYSTEM_TOKEN_FAILURE);
		}
		String string = JedisUtils.get(tokenID);
		if(StringUtils.isBlank(string)){
			
			System system = systemService.findSystemByToken(StringUtils.replace(tokenID, Constant.CHILD_LOGIN_SECURITY_TOKEN, ""));
			if(system == null){
				throw new RuntimeExceptionWarn(ServerStatus.SYSTEM_TOKEN_FAILURE);
			}
			JedisUtils.set(tokenID, system.getId().toString(), 0);
		}
		
		return true;
	}
}
