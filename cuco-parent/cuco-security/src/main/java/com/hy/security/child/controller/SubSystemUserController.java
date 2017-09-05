package com.hy.security.child.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hy.annotation.API;
import com.hy.enums.ServerStatus;
import com.hy.exception.RuntimeExceptionWarn;
import com.hy.security.child.service.ParentServiceImpl;
import com.hy.security.child.view.response.ChildResView;
import com.hy.security.entity.User;

@RestController
public class SubSystemUserController {

	
	
	@API(value = "子系统退出系统")
    @RequestMapping(value = "/v1/system/user/logout",method = RequestMethod.POST)
    public Object subSystemLogout(HttpServletRequest request){

		//从请求头里面拿出token
		String tokenID = request.getHeader("HaiYi-Access-Token");
		if(null == tokenID){
			throw new RuntimeExceptionWarn("请求头里不含token");
		}
		ParentServiceImpl.logoutByToken(tokenID);
        return "success";
    }
	
	
	@API(value = "子系统根据用户token获取用户详细信息")
    @RequestMapping(value = "/v1/system/user/info",method = RequestMethod.POST)
    public Object subSystemUserInfo(HttpServletRequest request){

		//从请求头里面拿出token
		String tokenID = request.getHeader("HaiYi-Access-Token");
		if(null == tokenID){
			throw new RuntimeExceptionWarn("请求头里不含token");
		}
		ChildResView userInfoByToken = ParentServiceImpl.getUserInfoByToken(tokenID);
		if(userInfoByToken.getUser() == null){
			throw new RuntimeExceptionWarn(ServerStatus.USER_TOKEN_FAILURE);
		}
        return userInfoByToken.getUser();
    }
	
	@API(value = "子系统根据用户token获取用户的系统列表")
    @RequestMapping(value = "/v1/system/user/systems",method = RequestMethod.POST)
    public Object subSystemUserSystems(HttpServletRequest request){

		//从请求头里面拿出token
		String tokenID = request.getHeader("HaiYi-Access-Token");
		if(null == tokenID){
			throw new RuntimeExceptionWarn("请求头里不含token");
		}
		User userSystems = ParentServiceImpl.getUserSystems(tokenID);
		if(userSystems.getSystems() == null || userSystems.getSystems().isEmpty()){
			throw new RuntimeExceptionWarn(ServerStatus.USER_TOKEN_FAILURE);
		}
        return userSystems.getSystems();
    }
	
	@API(value = "子系统根据用户token获取用户的权限列表")
    @RequestMapping(value = "/v1/system/user/roles",method = RequestMethod.POST)
    public Object subSystemUserRoles(HttpServletRequest request){

		//从请求头里面拿出token
		String tokenID = request.getHeader("HaiYi-Access-Token");
		if(null == tokenID){
			throw new RuntimeExceptionWarn("请求头里不含token");
		}
		User userRolesByToken = ParentServiceImpl.getUserRolesByToken(tokenID);
		if(userRolesByToken == null){
			throw new RuntimeExceptionWarn(ServerStatus.USER_TOKEN_FAILURE);
		}
        return userRolesByToken;
    }
	
}
