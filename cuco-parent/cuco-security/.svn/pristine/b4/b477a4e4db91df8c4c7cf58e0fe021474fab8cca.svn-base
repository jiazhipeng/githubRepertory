package com.hy.security.child.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hy.common.utils.HttpUtils;
import com.hy.common.utils.JsonUtil;
import com.hy.httpservice.ResponseBodyView;
import com.hy.security.child.constant.NamePassWord;
import com.hy.security.child.view.request.ChildReqView;
import com.hy.security.child.view.response.ChildResView;
import com.hy.security.entity.Api;
import com.hy.security.entity.User;
/**
 * 远程调用权限系统接口
 * @author zhubing
 *
 */
public class ParentServiceImpl {
	
	
	private static Logger logger = LoggerFactory.getLogger(ParentServiceImpl.class);
	
	public static String systemToken = null;
	
	static{
		if(systemToken == null){
			String appId = NamePassWord.USER_NAME; 
			String appSecret = NamePassWord.PASSWORD;
			ChildResView loginParent = loginParent(appId,appSecret);
			systemToken = loginParent.getChildToken();
		}
	}
	
	/**
	 * 登录权限系统
	 * @param appId
	 * @param appSecret
	 * @return
	 */
	public static ChildResView loginParent(String appId,String appSecret){
		
		ChildResView readValue = new ChildResView();
		try {
			Header headers[] = new BasicHeader[]{new BasicHeader("Content-Type","application/json")}; 
			ChildReqView childReqView = new ChildReqView();
			childReqView.setAppId(appId);
			childReqView.setAppSecret(appSecret);
			ObjectMapper objectMapper = JsonUtil.getObjectMapper();
			HttpEntity entity = new StringEntity(objectMapper.writeValueAsString(childReqView));
			
			CloseableHttpResponse response = HttpUtils.sendPost(NamePassWord.SECURITY_SYSTEM_HOST+"/child/login", headers, entity);
			String string = EntityUtils.toString(response.getEntity());
			ResponseBodyView readValue0 = objectMapper.readValue(string, ResponseBodyView.class);
			readValue = objectMapper.readValue(JSON.toJSONString(readValue0.getData()), ChildResView.class);
			return readValue;
		} catch (IOException e) {
			logger.error("登录发生错误...", e);
		}
		return readValue;
	}

	/**
	 * 从权限系统中根据用户token获得用户权限信息 
	 * @param token
	 */
	public static User getUserRolesByToken(String token){
		String string = null;
		User retVal = new User();
		try {
			Header headers[] = new BasicHeader[]{new BasicHeader("Content-Type","application/json"),
					new BasicHeader("System-Access-Token",ParentServiceImpl.systemToken)}; 
			ChildReqView childReqView = new ChildReqView();
			childReqView.setUserToken(token);
			ObjectMapper objectMapper = JsonUtil.getObjectMapper();
			HttpEntity entity = new StringEntity(objectMapper.writeValueAsString(childReqView));
			
			CloseableHttpResponse response = HttpUtils.sendPost(NamePassWord.SECURITY_SYSTEM_HOST+"/child/user/roles", headers, entity);
			string = EntityUtils.toString(response.getEntity());
			ResponseBodyView readValue0 = objectMapper.readValue(string, ResponseBodyView.class);
			ChildResView readValue = objectMapper.readValue(JSON.toJSONString(readValue0.getData()), ChildResView.class);
			if(readValue == null){
				return null;
			}else{
				retVal = readValue.getUser();
			}
		} catch (Exception e) {
			logger.error("登录发生错误...", e);
		}
		return retVal;
	}
	
	/**
	 * 子系统获取无权限集合
	 * @param token
	 * @return
	 */
	public static List<Api> getNoSecurityRolesBySystemId(String systemCode){
		String string = null;
		ChildResView readValue = null;
		List<Api> apis = null;
		try {
			Header headers[] = new BasicHeader[]{new BasicHeader("Content-Type","application/json"),
					new BasicHeader("System-Access-Token",ParentServiceImpl.systemToken)}; 
			ChildReqView childReqView = new ChildReqView();
			childReqView.setSystemCode(systemCode);
			ObjectMapper objectMapper = JsonUtil.getObjectMapper();
			HttpEntity entity = new StringEntity(objectMapper.writeValueAsString(childReqView));
			
			CloseableHttpResponse response = HttpUtils.sendPost(NamePassWord.SECURITY_SYSTEM_HOST+"/child/nosecurity/roles", headers, entity);
			string = EntityUtils.toString(response.getEntity());
			ResponseBodyView readValue0 = objectMapper.readValue(string, ResponseBodyView.class);
			readValue = objectMapper.readValue(JSON.toJSONString(readValue0.getData()), ChildResView.class);
			
			if(readValue == null){
				return null;
			}
			apis = readValue.getNoSecurityApis();
		} catch (Exception e) {
			logger.error("登录发生错误...", e);
		}
		return apis;
	}
	
	/**
	 * 根据用户token获取用户的详细信息
	 * @param systemId
	 * @return
	 */
	public static ChildResView getUserInfoByToken(String token){
		String string = null;
		ChildResView readValue = null;
		try {
			Header headers[] = new BasicHeader[]{new BasicHeader("Content-Type","application/json"),
					new BasicHeader("System-Access-Token",ParentServiceImpl.systemToken)}; 
			ChildReqView childReqView = new ChildReqView();
			childReqView.setUserToken(token);
			ObjectMapper objectMapper = JsonUtil.getObjectMapper();
			HttpEntity entity = new StringEntity(objectMapper.writeValueAsString(childReqView));
			
			CloseableHttpResponse response = HttpUtils.sendPost(NamePassWord.SECURITY_SYSTEM_HOST+"/child/user/info", headers, entity);
			string = EntityUtils.toString(response.getEntity());
			ResponseBodyView readValue0 = objectMapper.readValue(string, ResponseBodyView.class);
			readValue = objectMapper.readValue(JSON.toJSONString(readValue0.getData()), ChildResView.class);
		} catch (Exception e) {
			logger.error("登录发生错误...", e);
		}
		return readValue;
	}
	/**
	 * 根据用户token退出系统清空用户token
	 * @param token
	 * @return
	 */
	public static ChildResView logoutByToken(String token){
		String string = null;
		ChildResView readValue = null;
		try {
			Header headers[] = new BasicHeader[]{new BasicHeader("Content-Type","application/json"),
					new BasicHeader("System-Access-Token",ParentServiceImpl.systemToken)}; 
			ChildReqView childReqView = new ChildReqView();
			childReqView.setUserToken(token);
			ObjectMapper objectMapper = JsonUtil.getObjectMapper();
			HttpEntity entity = new StringEntity(objectMapper.writeValueAsString(childReqView));
			
			CloseableHttpResponse response = HttpUtils.sendPost(NamePassWord.SECURITY_SYSTEM_HOST+"/child/user/logout", headers, entity);
			string = EntityUtils.toString(response.getEntity());
			ResponseBodyView readValue0 = objectMapper.readValue(string, ResponseBodyView.class);
			readValue = objectMapper.readValue(JSON.toJSONString(readValue0.getData()), ChildResView.class);
		} catch (Exception e) {
			logger.error("登录发生错误...", e);
		}
		return readValue;
	}
	/**
	 * 根据用户token获得用户系统列表
	 * @param token
	 * @return
	 */
	public static User getUserSystems(String token){
		String string = null;
		User readValue = null;
		try {
			Header headers[] = new BasicHeader[]{new BasicHeader("Content-Type","application/json"),
					new BasicHeader("System-Access-Token",ParentServiceImpl.systemToken)}; 
			ChildReqView childReqView = new ChildReqView();
			childReqView.setUserToken(token);
			ObjectMapper objectMapper = JsonUtil.getObjectMapper();
			HttpEntity entity = new StringEntity(objectMapper.writeValueAsString(childReqView));
			
			String url = NamePassWord.SECURITY_SYSTEM_HOST+"/child/user/systems";
			CloseableHttpResponse response = HttpUtils.sendPost(url, headers, entity);
			string = EntityUtils.toString(response.getEntity());
			ResponseBodyView readValue0 = objectMapper.readValue(string, ResponseBodyView.class);
			readValue = objectMapper.readValue(JSON.toJSONString(readValue0.getData()), User.class);
		} catch (Exception e) {
			logger.error("登录发生错误...", e);
		}
		return readValue;
	}

}
