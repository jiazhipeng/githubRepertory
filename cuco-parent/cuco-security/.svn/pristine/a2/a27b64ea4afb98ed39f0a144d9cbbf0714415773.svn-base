package com.hy.security.interceptor;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.hy.common.utils.JedisUtils;
import com.hy.constant.Constant;
import com.hy.enums.ServerStatus;
import com.hy.exception.RuntimeExceptionWarn;
import com.hy.security.common.AccessToken;
import com.hy.security.common.HttpServiceContext;
import com.hy.security.entity.Api;
import com.hy.security.entity.System;
import com.hy.security.entity.User;
import com.hy.security.service.api.ApiService;
import com.hy.security.service.system.SystemService;
import com.hy.security.service.user.AccessTokenService;
import com.hy.security.service.user.UserService;
import com.hy.security.util.UserUtils;
import com.hy.security.util.VerifyUtil;

/**所有的请求都会经过这个自定义拦截器判断权限
 * @author 1mobility
 *
 */
public class AllInterceptor extends HandlerInterceptorAdapter{

	private static Logger logger = LoggerFactory.getLogger(AllInterceptor.class);
	
	@Autowired
	private AccessTokenService accessTokenService;
	
	@Autowired
	private ApiService apiService;
	
	@Autowired
    private SystemService systemService;
	@Autowired
    private UserService userService;
	
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
		
		//用户注册,用户登录，获取验证码跳过该操作不需要走权限控制--加上微信回调
		if((-1 != url.indexOf("user/login")) || (-1 != url.indexOf("user/register")) || (-1 != url.indexOf("/wechatLogin")) || (-1 != url.indexOf("/webserver"))){
			return true;
		}
		//从请求头里面拿出token
		tokenID = request.getHeader("HaiYi-Access-Token");
		this.logger.info("取出的token---"+tokenID);
		if(null == tokenID){
			throw new RuntimeExceptionWarn(ServerStatus.USER_TOKEN_ISNULL);
		}
		return interceptorJustLogin(tokenID);//登录就放行
		//return interceptorApis(tokenID);//验证是否有api集合的权限
	}
	
	public boolean interceptorJustLogin(String tokenID){
		
		
		Date tokenEndDateTime = UserUtils.getTokenEndDateTime(tokenID);
		if(tokenEndDateTime != null){
			if(tokenEndDateTime.before(new Date())){
				throw new RuntimeExceptionWarn(ServerStatus.USER_TOKEN_FAILURE);
			}else{
				UserUtils.setTokenEndDateTime(tokenID);
			}
		}
		
		
		User user = UserUtils.getUserFromRedisAndDB(tokenID,accessTokenService);
		
		if(null==user){
			throw new RuntimeExceptionWarn(ServerStatus.USER_TOKEN_FAILURE);
		}
		
		HttpServiceContext.setUser(user);
		return true;
	}
	
	
	public boolean interceptorApis(String tokenID){
		//根据token从redis里面取出该用户的权限集合
		User user = null;
		Object object = JedisUtils.getObject(tokenID);
		logger.info("redis里面权限集合---"+object);
		if(null==object){
			//说明用户权限已经更新，需要去数据库中查询，如果数据库中也没有的话，那么说明用户权限已经完全被禁用了
			user = accessTokenService.getUserAuthByUserToken(tokenID);
			if(null==user){
				throw new RuntimeExceptionWarn(ServerStatus.USER_TOKEN_FAILURE);
			}else if(user.getApis() == null || user.getMenus() == null){
				throw new RuntimeExceptionWarn(ServerStatus.USER_SECURITY_ISEMPTY);
			}
			JedisUtils.setObject(tokenID, JSON.toJSON(user), Constant.TOKEN_LIVETIME);
		}else{
			user = JSON.parseObject(object.toString(),User.class);
		}
		
		//无权限集合，放在这是因为如果一个用户禁用了，那么无权限集合也是不给他用的
		System system = new System(); 
		system.setType(Constant.SYSTEM_TYPE_MAIN);//现在是主系统(权限系统)
		List<System> findBySystem = systemService.findBySystem(system);
		Long mainSystemId = findBySystem.get(0).getId();
		String systemCode = findBySystem.get(0).getCode();
		List<Api> noApis = null;
		Object noSecurityObject = JedisUtils.getObject(AccessToken.getNoSecurityTokens(systemCode));
		if(noSecurityObject == null){
			Api apiSearch = new Api();
			apiSearch.setAuth(false);
			apiSearch.setSystemId(mainSystemId);
			//无权限api集合
			noApis = apiService.findByApi(apiSearch);
			if(!noApis.isEmpty() && noApis != null){
				JedisUtils.setObject(AccessToken.getNoSecurityTokens(systemCode), JSON.toJSON(noApis), Constant.TOKEN_LIVETIME);
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
					HttpServiceContext.setUser(user);
					return true;
				}
			}
		}
		
		//管理员全部放行
		if(VerifyUtil.isAdmin(userService.findById(user.getId()))){
			HttpServiceContext.setUser(user);
			return true;
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
