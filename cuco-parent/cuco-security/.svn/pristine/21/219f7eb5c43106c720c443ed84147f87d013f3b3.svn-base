package com.hy.security.service.child;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hy.common.utils.HttpUtils;
import com.hy.common.utils.JsonUtil;
import com.hy.security.child.view.request.ParentReqView;

public class ChildSystemServiceImpl {
	
	
	private static Logger logger = LoggerFactory.getLogger(ChildSystemServiceImpl.class);
	
	/**
	 * 权限系统登录子系统
	 * @param appId
	 * @param appSecret
	 * @param syncUrl
	 * @return
	 */
	public static String loginChild(String appId,String appSecret,String syncUrl){
		
		String readValue = null;
		try {
			Header headers[] = new BasicHeader[]{new BasicHeader("Content-Type","application/json")}; 
			ParentReqView parentReqView = new ParentReqView();
			parentReqView.setUserName(appId);
			parentReqView.setPassWord(appSecret);
			ObjectMapper objectMapper = JsonUtil.getObjectMapper();
			HttpEntity entity = new StringEntity(objectMapper.writeValueAsString(parentReqView));
			
			CloseableHttpResponse response = HttpUtils.sendPost(syncUrl+"/parent/login", headers, entity);
			String string = EntityUtils.toString(response.getEntity());
			JSONObject parseObject = JSON.parseObject(string);
			JSONObject data = parseObject.getJSONObject("data");
			readValue = data.getString("user_token");

			
			
			return readValue;
		} catch (IOException e) {
			logger.error("登录发生错误...", e);
		}
		return readValue;
	}
	/**
	 * 通知子系统权限发生变化
	 * @param syncUrl
	 */
	public static void systemSecurityChange(String syncUrl,String token){
		
		try {
			Header headers[] = new BasicHeader[]{new BasicHeader("Content-Type","application/json"),
					new BasicHeader("System-Access-Token",token)}; 
			
			CloseableHttpResponse response = HttpUtils.sendPost(syncUrl+"/parent/security/change", headers, null);
			String string = EntityUtils.toString(response.getEntity());
		} catch (IOException e) {
			logger.error("登录发生错误...", e);
		}
	}
	/**
	 * 通知子系统仅无权限集合发生变化
	 * @param syncUrl
	 */
	public static void systemNoSecurityChange(String syncUrl,String token){
		
		try {
			Header headers[] = new BasicHeader[]{new BasicHeader("Content-Type","application/json"),
					new BasicHeader("System-Access-Token",token)}; 
			
			CloseableHttpResponse response = HttpUtils.sendPost(syncUrl+"/parent/nosecurity/change", headers, null);
			String string = EntityUtils.toString(response.getEntity());
		} catch (IOException e) {
			logger.error("登录发生错误...", e);
		}
	}
	
	public static void main(String[] args) {
//		ChildSystemServiceImpl.systemSecurityChange("http://192.168.1.79:8080", "6465AF7B019BDB86659596580142C5DE");
		ChildSystemServiceImpl.systemNoSecurityChange("http://192.168.1.79:8080", "6465AF7B019BDB86659596580142C5DE");
		//String loginChild = ChildSystemServiceImpl.loginChild("GPS", "gps123456", "http://192.168.1.79:8080");
		//System.out.println(loginChild);
	}
}
