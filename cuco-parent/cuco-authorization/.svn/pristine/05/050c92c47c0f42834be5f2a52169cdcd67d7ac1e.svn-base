package com.hy.authorization.service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hy.authorization.entity.Role;
import com.hy.authorization.view.request.ChildUserReqView;
import com.hy.common.utils.LogUtil;
import com.hy.common.utils.ParamVerifyUtil;
import com.hy.exception.RuntimeExceptionWarn;
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
import com.hy.authorization.constant.NamePassWord;
import com.hy.authorization.entity.Api;
import com.hy.authorization.entity.User;
import com.hy.authorization.view.request.ChildReqView;
import com.hy.authorization.view.response.ChildResView;
import com.hy.common.utils.HttpUtils;
import com.hy.common.utils.JsonUtil;
import com.hy.httpservice.ResponseBodyView;
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


	/**
	* @Description: 根据角色标识去权限系统获取拥有该角色的用户列表
	* @author：WangShuai
	* @time：2017年03月01日 下午04:32:22
	* @param:
	* @return:
	* @throws:
	*/
	public static List<User> getUsersByRoleUniqueCode(String uniqueCode){
		ParamVerifyUtil.paramNotEmpty(uniqueCode,"uniqueCode不可为空");

		try {
			Header headers[] = new BasicHeader[]{new BasicHeader("Content-Type","application/json"),
					new BasicHeader("System-Access-Token",ParentServiceImpl.systemToken)};

			String url = NamePassWord.SECURITY_SYSTEM_HOST+"/child/users/role/unique_code/"+uniqueCode;
			CloseableHttpResponse response = HttpUtils.sendPost(url, headers, null);
			String responseStr = EntityUtils.toString(response.getEntity());
			ResponseBodyView responseBodyView = JsonUtil.getObjectMapper().readValue(responseStr, ResponseBodyView.class);
			List<User> users = JsonUtil.getObjectMapper().readValue(JSON.toJSONString(responseBodyView.getData()), new TypeReference<List<User>>(){});
			return users;
		}catch (Exception e){
			LogUtil.getLogger().error("根据角色标识去权限系统获取拥有该角色的用户列表，失败:",e);
			throw new RuntimeExceptionWarn("根据角色标识去权限系统获取拥有该角色的用户列表，失败"+e.getMessage());
		}
	}

	/**
	 * 根据用户ID(从权限系统)获取用户基本信息及其角色列表
	 * @param userId
	 * @return
     */
	public static User getUserRolesByUserId(Long userId){
		User user = getBasicUserByUserId(userId);
		ParamVerifyUtil.paramNotNull(user,"获取用户基本信息及其角色列表失败：用户不存在");

		List<Role> roles = getRolesByUserId(userId);
		user.setRoles(roles);

		return user;
	}

	/**
	 * 根据用户ID(从权限系统)获取用户基本信息
	 * @param userId
	 * @return
     */
	public static User getBasicUserByUserId(Long userId){
		ChildUserReqView searchUser = new ChildUserReqView();
		searchUser.setId(userId);
		return getUserByCondition(searchUser);
	}

	/**
	 * 根据用户openid(从权限系统)获取用户基本信息
	 * @param openId
	 * @return
     */
	public static User getBasicUserByOpenId(String openId){
		ChildUserReqView searchUser = new ChildUserReqView();
		searchUser.setOpenid(openId);
		return getUserByCondition(searchUser);
	}

	/**
	 * 根据用户mobile & email(从权限系统)获取用户基本信息
	 * @param
	 * @return
	 */
	public static User getBasicUserByMobileAndEmail(String mobile,String email){
		ChildUserReqView searchUser = new ChildUserReqView();
		searchUser.setPhone(mobile);
		searchUser.setEmail(email);
		return getUserByCondition(searchUser);
	}

	private static User getUserByCondition(ChildUserReqView searchUser){
		ParamVerifyUtil.paramNotNull(searchUser);

		try {
			Header headers[] = new BasicHeader[]{new BasicHeader("Content-Type","application/json"),
					new BasicHeader("System-Access-Token",ParentServiceImpl.systemToken)};
			ObjectMapper objectMapper = JsonUtil.getObjectMapper();
			HttpEntity entity = new StringEntity(objectMapper.writeValueAsString(searchUser));

			String url = NamePassWord.SECURITY_SYSTEM_HOST+"/child/user/condition";
			CloseableHttpResponse response = HttpUtils.sendPost(url, headers, entity);
			String responseStr = EntityUtils.toString(response.getEntity());
			ResponseBodyView responseBodyView = objectMapper.readValue(responseStr, ResponseBodyView.class);
			User user = objectMapper.readValue(JSON.toJSONString(responseBodyView.getData()), User.class);
			return user;
		} catch (Exception e) {
			LogUtil.getLogger().error("根据用户信息去权限系统获取用户信息，失败", e);
			throw new RuntimeExceptionWarn("根据用户信息去权限系统获取用户信息，失败"+e.getMessage());
		}
	}

	/**
	 * 根据用户ID（去权限系统）获取用户角色列表
	 * @return
     */
	public static List<Role> getRolesByUserId(Long userId){
		ParamVerifyUtil.paramNotNull(userId,"根据用户ID获取角色列表失败：用户ID不可为空");

		try {
			Header headers[] = new BasicHeader[]{new BasicHeader("Content-Type","application/json"),
					new BasicHeader("System-Access-Token",ParentServiceImpl.systemToken)};
			ObjectMapper objectMapper = JsonUtil.getObjectMapper();
			HttpEntity entity = null;

			String url = NamePassWord.SECURITY_SYSTEM_HOST+"/child/user/roles/user_id/"+userId;
			CloseableHttpResponse response = HttpUtils.sendPost(url, headers, entity);
			String responseStr = EntityUtils.toString(response.getEntity());
			ResponseBodyView responseBodyView = objectMapper.readValue(responseStr, ResponseBodyView.class);
			List<Role> roles = objectMapper.readValue(JSON.toJSONString(responseBodyView.getData()), new TypeReference<List<Role>>(){});
			return roles;
		} catch (Exception e) {
			LogUtil.getLogger().error("根据用户ID（去权限系统）获取用户角色列表，失败", e);
			throw new RuntimeExceptionWarn("根据用户ID（去权限系统）获取用户角色列表，失败"+e.getMessage());
		}
	}

}
