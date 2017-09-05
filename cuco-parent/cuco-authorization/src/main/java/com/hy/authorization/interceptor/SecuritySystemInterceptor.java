package com.hy.authorization.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hy.authorization.constant.NamePassWord;
import com.hy.authorization.util.DigestUtil;
import com.hy.constant.Constant;
import com.hy.enums.ServerStatus;
import com.hy.exception.RuntimeExceptionWarn;

/**
 * 权限系统请求子系统的拦截器
 * @author zhubing
 *
 */
public class SecuritySystemInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
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
		if(-1 != url.indexOf("parent/login")){
			return true;
		}
		//从请求头里面拿出token
		tokenID = request.getHeader("System-Access-Token");
		this.logger.info("取出的token---"+tokenID);
		if(null == tokenID){
			throw new RuntimeExceptionWarn(ServerStatus.SYSTEM_TOKEN_FAILURE);
		}
		String childToken = DigestUtil.MD5(NamePassWord.USER_NAME+NamePassWord.PASSWORD);
		if(childToken.equals(tokenID)){
			throw new RuntimeExceptionWarn(ServerStatus.SYSTEM_TOKEN_FAILURE);
		}
		
		return true;
	}
}
