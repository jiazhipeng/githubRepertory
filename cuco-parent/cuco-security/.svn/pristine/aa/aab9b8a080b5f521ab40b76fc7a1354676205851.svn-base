package com.hy.security.child.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.hy.common.utils.JedisUtils;
import com.hy.constant.Constant;
import com.hy.enums.ServerStatus;
import com.hy.exception.RuntimeExceptionWarn;
import com.hy.security.child.constant.NamePassWord;
import com.hy.security.child.service.ParentServiceImpl;
import com.hy.security.child.view.response.ChildResView;
import com.hy.security.common.AccessToken;
import com.hy.security.common.HttpServiceContext;
import com.hy.security.entity.Api;
import com.hy.security.entity.System;
import com.hy.security.entity.User;
/**
 * 子系统自己的拦截器
 * @author zhubing
 *
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);
	
	
	private String requestURI;
	private String contextPath;
	private String url;
	private String tokenID;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//得到uri请求路径
		requestURI = request.getRequestURI();
		contextPath = request.getContextPath();
		url = requestURI.substring(contextPath.length());
		logger.info("用户请求URI为---"+requestURI);
		logger.info("用户项目路径为---"+contextPath);
		logger.info("用户请求路径为---"+url);
		
		//在此可以加上不用拦截器拦截的接口
//				if((-1 != url.indexOf("user/login")) || (-1 != url.indexOf("user/register")) || (-1 != url.indexOf("/wechatLogin")) || (-1 != url.indexOf("/webserver"))){
//					return true;
//				}
		if(-1 != url.indexOf("parent/login")){
			return true;
		}
		
		
		//无权限集合，放在这是因为如果一个用户禁用了，那么无权限集合也是不给他用的
		String mainSystemId = NamePassWord.SYSTEM_CODE;
		
		List<Api> noApis = null;
		Object noSecurityObject = JedisUtils.getObject(AccessToken.getNoSecurityTokens(mainSystemId));
		if(noSecurityObject == null){
			//无权限api集合
			noApis = ParentServiceImpl.getNoSecurityRolesBySystemId(mainSystemId);
			if(noApis != null && !noApis.isEmpty()){
				JedisUtils.setObject(AccessToken.getNoSecurityTokens(mainSystemId), JSON.toJSON(noApis), Constant.TOKEN_LIVETIME);
			}
		}else{
			noApis = JSON.parseArray(noSecurityObject.toString(), Api.class);
		}
		//没有无权限集合的API
		if(noApis != null && !noApis.isEmpty()){
			for(Api api : noApis){
				//将请求的uri与无权限集合API比较
				if(url.equals(api.getUri())){
					//成功之后将用户信息存起来
					HttpServiceContext.setUser(null);
					return true;
				}
			}
		}
		
		
		//从请求头里面拿出token
		tokenID = request.getHeader("HaiYi-Access-Token");
		this.logger.info("取出的token---"+tokenID);
		if(null == tokenID){
			throw new RuntimeExceptionWarn(ServerStatus.USER_TOKEN_ISNULL);
		}
		//根据token从redis里面取出该用户的权限集合
		User user = null;
		Object object = JedisUtils.getObject(tokenID);
		logger.info("redis里面权限集合---"+object);
		if(null==object){
			//说明用户权限已经更新，需要去数据库中查询，如果数据库中也没有的话，那么说明用户权限已经完全被禁用了
			user = ParentServiceImpl.getUserRolesByToken(tokenID);
			if(null==user){
				throw new RuntimeExceptionWarn(ServerStatus.USER_TOKEN_FAILURE);
			}else if(user.getApis() == null || user.getMenus() == null){
				throw new RuntimeExceptionWarn(ServerStatus.USER_SECURITY_ISEMPTY);
			}
			JedisUtils.setObject(tokenID, JSON.toJSON(user), Constant.TOKEN_LIVETIME);
		}else{
			user = JSON.parseObject(object.toString(),User.class);
		}
		
		
		List<Api> apiList = user.getApis();
		if(null==apiList){
			throw new RuntimeExceptionWarn(ServerStatus.USER_LACKOF_SECURITY);
		}
		//循环权限集合
		for(Api api : apiList){
			//将请求的uri与API中的作比较
			if(url.equals(api.getUri())){
				//成功之后将用户信息存起来
				HttpServiceContext.setUser(user);
				return true;
			}
		}
		throw new RuntimeExceptionWarn(ServerStatus.USER_LACKOF_SECURITY);
	}
}
