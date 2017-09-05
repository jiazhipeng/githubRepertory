package com.hy.security.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hy.annotation.API;
import com.hy.common.utils.JedisUtils;
import com.hy.constant.Constant;
import com.hy.enums.ServerStatus;
import com.hy.exception.RuntimeExceptionWarn;
import com.hy.security.entity.System;
import com.hy.security.service.child.ChildSystemServiceImpl;
import com.hy.security.service.system.SystemService;
import com.hy.security.view.response.SystemResView;
/**
 * 管理员通知权限变化控制接口
 * @author zhubing
 *
 */
@RestController
public class SecurityController {

	@Autowired
	private SystemService systemService;
	
	public static Map<Long,String> securityLoginChildToken = new HashMap<Long,String>();
	
	
	@API(value = "通知指定或者全部系统权限变化")
	@RequestMapping(value = "/v1/system/security/notice",method = RequestMethod.POST)
	public Object systemSecurityChangeNotice(@RequestBody List<System> systems){
		
		//如果没有指定系统，那么就更新所有系统的权限(包括本地系统)
		if(systems == null || systems.isEmpty()){
			List<System> findAllSystems = systemService.findBySystem(new System());
			securityChange(findAllSystems);
			
		}else{
			securityChange(systems);
		}
		
		return "success";
	}
	
	@API(value = "通知指定或者全部系统无权限集合变化")
	@RequestMapping(value = "/v1/system/nosecurity/notice",method = RequestMethod.POST)
	public Object systemNoSecurityChangeNotice(@RequestBody List<System> systems){
		
		//如果没有指定系统，那么就更新所有系统的无权限集合(包括本地系统)
		if(systems == null || systems.isEmpty()){
			List<System> findAllSystems = systemService.findBySystem(new System());
			noSecurityChange(findAllSystems);
		}else{
			noSecurityChange(systems);
		}
		return "success";
	}
	
	public void noSecurityChange(List<System> systems){
		System system = new System();
		for(int i=0;i<systems.size();i++){
			system = systemService.findById(systems.get(i).getId());
			if(Constant.SYSTEM_TYPE_MAIN.equals(system.getType())){
				//清空系统无权限集合
				JedisUtils.delLikeKey("system*");
				continue;
			}
			String token = securityLoginChildToken.get(system.getId());
			if(token == null){
				token = ChildSystemServiceImpl.loginChild(system.getAppId(), system.getAppSecret(), system.getApiSyncUrl());
			}
			if(token == null){
				throw new RuntimeExceptionWarn("权限系统登录"+system.getName()+"发生错误，请核对用户名密码！");
			}
			securityLoginChildToken.put(system.getId(), token);
			ChildSystemServiceImpl.systemNoSecurityChange(system.getApiSyncUrl(), token);
		}
	}
	
	private void securityChange(List<System> systems){
		System system = new System();
		for(int i=0;i<systems.size();i++){
			system = systemService.findById(systems.get(i).getId());
			if(Constant.SYSTEM_TYPE_MAIN.equals(system.getType())){
				//清空用户的权限信息
				JedisUtils.delLikeKey("token*");
				//清空系统无权限集合
				JedisUtils.delLikeKey("system*");
				continue;
			}
			String token = securityLoginChildToken.get(system.getId());
			if(token == null){
				token = ChildSystemServiceImpl.loginChild(system.getAppId(), system.getAppSecret(), system.getApiSyncUrl());
			}
			if(token == null){
				throw new RuntimeExceptionWarn("权限系统登录"+system.getName()+"发生错误，请核对用户名密码！");
			}
			securityLoginChildToken.put(system.getId(), token);
			ChildSystemServiceImpl.systemSecurityChange(system.getApiSyncUrl(), token);
		}
	}

}
