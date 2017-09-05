package com.hy.security.controller;

import java.util.List;

import com.hy.common.utils.ParamVerifyUtil;
import com.hy.security.entity.*;
import com.hy.security.entity.System;
import com.hy.security.service.role.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hy.annotation.API;
import com.hy.common.utils.JedisUtils;
import com.hy.constant.Constant;
import com.hy.enums.ServerStatus;
import com.hy.exception.RuntimeExceptionWarn;
import com.hy.security.child.view.request.ChildReqView;
import com.hy.security.child.view.response.ChildResView;
import com.hy.security.service.api.ApiService;
import com.hy.security.service.system.SystemService;
import com.hy.security.service.user.AccessTokenService;
import com.hy.security.service.user.UserService;
import com.hy.security.util.DigestUtil;
/**
 * 
 * @author zhubing 20161112
 *
 */
@RestController
public class ChildSystemController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	
	@Autowired
    private UserService userService;
	@Autowired
    private SystemService systemService;
	@Autowired
	private AccessTokenService accessTokenService;
	@Autowired
	private ApiService apiService;
	@Autowired
	private RoleService roleService;

	
	@API(value="子系统登录权限系统获取token",auth = false)
	@RequestMapping(value="/child/login",method=RequestMethod.POST)
	public Object loginSecurity(@RequestBody ChildReqView childRequest){
		
		if(childRequest == null 
				|| StringUtils.isBlank(childRequest.getAppSecret()) 
				|| StringUtils.isBlank(childRequest.getAppId())){
			throw new RuntimeExceptionWarn("登录信息缺失，请检查后重试...");
		}
		System system = new System();
		system.setAppId(childRequest.getAppId());
		system.setAppSecret(childRequest.getAppSecret());
		List<System> findBySystem = systemService.findBySystem(system);
		if(findBySystem == null 
				|| findBySystem.isEmpty()){
			throw new RuntimeExceptionWarn("用户名密码错误，请重试...");
		}
		System system2 = findBySystem.get(0);
		String childToken = Constant.CHILD_LOGIN_SECURITY_TOKEN+DigestUtil.MD5(system2.getAppId()+system2.getAppSecret());
		JedisUtils.set(childToken, system2.getId().toString(), 0);
		return new ChildResView(childToken);
	}
	
	@API(value="子系统根据用户token获取其权限",auth = false)
	@RequestMapping(value="/child/user/roles",method=RequestMethod.POST)
	public Object childSystemGetUserRoleByToken(@RequestBody ChildReqView childRequest){
		
		if(childRequest == null
				|| StringUtils.isBlank(childRequest.getUserToken())){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}		
		//下面是获取用户的权限
		User userAuthByUserToken = accessTokenService.getUserAuthByUserToken(childRequest.getUserToken());
		//如果返回用户权限为空，那么在子系统端提示客户端用户已经被禁用
		if(userAuthByUserToken == null){
			return null;
		}
		return new ChildResView(userAuthByUserToken);
	}
	@API(value="子系统获取无权限集合",auth = false)
	@RequestMapping(value="/child/nosecurity/roles",method=RequestMethod.POST)
	public Object childSystemGetNoSecurityRoles(@RequestBody ChildReqView childRequest){
		
		if(childRequest == null
				|| childRequest.getSystemCode() == null){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
		
		System system = new System(); 
		system.setCode(childRequest.getSystemCode());
		List<System> findBySystem = systemService.findBySystem(system);
		if(findBySystem != null
				&& !findBySystem.isEmpty()){
			childRequest.setSystemId(findBySystem.get(0).getId());
		}
		Api api = new Api();
		api.setAuth(false);
		api.setSystemId(childRequest.getSystemId());
		//子系统无权限api集合
		List<Api> noSecurityApis = apiService.findByApi(api);
		
		return new ChildResView(noSecurityApis);
	}
	@API(value="子系统获根据用户token获取用户的详细信息",auth = false)
	@RequestMapping(value="/child/user/info",method=RequestMethod.POST)
	public Object childSystemGetUserInfoByToken(@RequestBody ChildReqView childRequest){
		
		if(childRequest == null
				|| StringUtils.isBlank(childRequest.getUserToken())){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
		//根据用户token从数据库中获得用户
		AccessToken accessTokenSearch = new AccessToken();
		accessTokenSearch.setToken(childRequest.getUserToken());
		//根据token获取userId
		List<AccessToken> selectByCondition = accessTokenService.selectByCondition(accessTokenSearch);
		
		if(selectByCondition == null || selectByCondition.isEmpty()){
			//用户token失效
			throw new RuntimeExceptionWarn(ServerStatus.USER_TOKEN_FAILURE);
		}
		AccessToken accessToken = selectByCondition.get(0);
		
		User userById = userService.findById(accessToken.getUserId());
		return new ChildResView(userById);
	}
	@API(value="子系统获根据用户token退出系统清空用户token",auth = false)
	@RequestMapping(value="/child/user/logout",method=RequestMethod.POST)
	public Object childSystemUserLogoutByUserToken(@RequestBody ChildReqView childRequest){
		
		if(childRequest == null
				|| StringUtils.isBlank(childRequest.getUserToken())){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
		//根据用户token删除用户登录token信息
		accessTokenService.delByToken(childRequest.getUserToken());
		return "success";
	}
	@API(value = "子系统根据用户token获得用户系统列表")
	@RequestMapping(value = "/child/user/systems",method = RequestMethod.POST)
	public Object getUserSystems(@RequestBody ChildReqView childRequest){
		
		if(childRequest == null
				|| StringUtils.isBlank(childRequest.getUserToken())){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
		AccessToken accessToken = new AccessToken();
		accessToken.setToken(childRequest.getUserToken());
		List<AccessToken> selectByCondition = accessTokenService.selectByCondition(accessToken);
		if(selectByCondition.isEmpty() || selectByCondition == null){
			//没有分配系统权限
			throw new RuntimeExceptionWarn(ServerStatus.USER_TOKEN_FAILURE);
		}
		Long userId = selectByCondition.get(0).getUserId();
		User user_Searche = new User(); 
		user_Searche.setId(userId);
		User user_new = this.userService.findByUser(user_Searche);
		
		//首次登录的情况根据用户ID查询该用户所对应的系统信息
		List<com.hy.security.entity.System> systemList = this.systemService.findSystemsOfUser(user_new);
		if(systemList.size()==0){
			//没有分配系统权限
			throw new RuntimeExceptionWarn(ServerStatus.USER_NO_ANY_SYSTEM_ACCESS);
		}

		//有多个系统的权限，跳转到系统选择列表
		user_new.setSystems(systemList);
		return user_new;
	}


	@API(value = "子系统根据角色标识获取拥有该角色的用户列表")
	@RequestMapping(value = "/child/users/role/unique_code/{unique_code}",method = RequestMethod.POST)
	private Object getUsersByRoleUniqueCode(@PathVariable("unique_code") String uniqueCode){

		return userService.getUsersByRoleUniqueCode(uniqueCode);
	}


	@API(value = "子系统根据用户搜索信息获取用户基本信息")
	@RequestMapping(value = "/child/user/condition",method = RequestMethod.POST)
	private Object getUserByCondition(@RequestBody User user){

		return userService.findByUser(user);
	}

	@API(value = "子系统根据用户ID获取用户角色列表")
	@RequestMapping(value = "/child/user/roles/user_id/{user_id}",method = RequestMethod.POST)
	private Object getUserRolesByUserId(@PathVariable("user_id") Long userId){
		ParamVerifyUtil.paramNotNull(userId,"子系统根据用户ID获取用户角色列表：用户ID不可为空");

		Role searchRole = new Role();
		searchRole.setUserId(userId);
		List<Role> roles = roleService.findRolesByUserId(searchRole);
		return roles;
	}

}
