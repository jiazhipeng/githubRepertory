package com.hy.security.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hy.annotation.API;
import com.hy.common.utils.ParamVerifyUtil;
import com.hy.common.utils.RegexUtils;
import com.hy.constant.Constant;
import com.hy.enums.ServerStatus;
import com.hy.exception.RuntimeExceptionWarn;
import com.hy.security.child.view.request.ChildReqView;
import com.hy.security.child.view.response.ChildResView;
import com.hy.security.common.HttpServiceContext;
import com.hy.security.entity.AccessToken;
import com.hy.security.entity.Organization;
import com.hy.security.entity.Role;
import com.hy.security.entity.User;
import com.hy.security.service.organization.OrganizationService;
import com.hy.security.service.role.RoleService;
import com.hy.security.service.system.SystemService;
import com.hy.security.service.user.AccessTokenService;
import com.hy.security.service.user.UserService;

/**
 * Created by WangShuai on 2016/7/21.
 */
@RestController
public class UserController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private SystemService systemService;
	@Autowired
	private AccessTokenService accessTokenService;
    
    @API(value = "创建用户")
    @RequestMapping(value = "/v1/user/create",method = RequestMethod.POST)
    public Object createUser(@RequestBody User user){
    	ParamVerifyUtil.paramNotNull(user);
    	ParamVerifyUtil.paramNotNull(user.getCityIds(), "用户城市不能为空");
    	ParamVerifyUtil.paramNotNull(user.getEmail(), "邮箱不能为空",64);
    	ParamVerifyUtil.paramNotNull(user.getName(), "用户名不能为空",15);
    	ParamVerifyUtil.paramNotNull(user.getPhone(), "手机号不能为空",20);
    	ParamVerifyUtil.verifyMobile(user.getPhone(), "请填写正确的手机号");
    	boolean email = RegexUtils.isEmail(user.getEmail());
    	if(!email){
    		throw new RuntimeExceptionWarn("请填写正确的邮箱");
    	}
    	ParamVerifyUtil.paramNotNull(user.getRoleIds(), "角色不能为空");
    	
    	User userSearch = new User(); 
    	userSearch.setPhone(user.getPhone());
    	userSearch.setFreezing(Constant.INTEGER_NO);
		List<User> usersByPhone = userService.selectByCondition(userSearch);
		if(usersByPhone != null
				&& !usersByPhone.isEmpty()){
			throw new RuntimeExceptionWarn("手机号码已经注册");
		}
		userSearch = new User(); 
    	userSearch.setEmail(user.getEmail());
    	userSearch.setFreezing(Constant.INTEGER_NO);
		List<User> usersByEmail = userService.selectByCondition(userSearch);
		if(usersByEmail != null
				&& !usersByEmail.isEmpty()){
			throw new RuntimeExceptionWarn("邮箱已经注册");
		}
    	user.setModifier(HttpServiceContext.getCurrentUserName());
    	user.setModifierId(HttpServiceContext.getCurrentUserId());
        return userService.createUser(user);
    }

    @API(value = "编辑用户")
    @RequestMapping(value = "/v1/user/update",method = RequestMethod.POST)
    public Object updateUser(@RequestBody User user){
    	ParamVerifyUtil.paramNotNull(user);
    	ParamVerifyUtil.paramNotNull(user.getId(), "用户ID不能为空");
    	ParamVerifyUtil.paramNotNull(user.getCityIds(), "用户城市不能为空");
    	ParamVerifyUtil.paramNotNull(user.getEmail(), "邮箱不能为空",64);
    	ParamVerifyUtil.paramNotNull(user.getName(), "用户名不能为空",15);
    	ParamVerifyUtil.paramNotNull(user.getPhone(), "手机号不能为空",20);
    	ParamVerifyUtil.verifyMobile(user.getPhone(), "请填写正确的手机号");
    	boolean email = RegexUtils.isEmail(user.getEmail());
    	if(!email){
    		throw new RuntimeExceptionWarn("请填写正确的邮箱");
    	}
    	ParamVerifyUtil.paramNotNull(user.getRoleIds(), "角色不能为空");
    	
    	
    	User userDB = userService.findById(user.getId());
    	User userSearch = new User(); 
    	userSearch.setPhone(user.getPhone());
    	userSearch.setFreezing(Constant.INTEGER_NO);
		List<User> usersByPhone = userService.selectByCondition(userSearch);
		if(usersByPhone != null
				&& !usersByPhone.isEmpty()){
			if(!userDB.getPhone().equals(usersByPhone.get(0).getPhone())){
				throw new RuntimeExceptionWarn("手机号码已经注册");
			}
			
		}
		userSearch = new User(); 
    	userSearch.setEmail(user.getEmail());
    	userSearch.setFreezing(Constant.INTEGER_NO);
		List<User> usersByEmail = userService.selectByCondition(userSearch);
		if(usersByEmail != null
				&& !usersByEmail.isEmpty()){
			if(!userDB.getEmail().equals(usersByEmail.get(0).getEmail())){
				throw new RuntimeExceptionWarn("邮箱已经注册");
			}
			
		}
    	
    	user.setModifier(HttpServiceContext.getCurrentUserName());
    	user.setModifierId(HttpServiceContext.getCurrentUserId());
    	
    	return userService.updateUser(user);
    }

    @API(value = "解冻或冻结用户")
    @RequestMapping(value = "/v1/user/disabled/update",method = RequestMethod.POST)
    public Object updateUserDisabled(@RequestBody User user){
    	ParamVerifyUtil.paramNotNull(user);
    	ParamVerifyUtil.paramNotNull(user.getId(), "用户ID不能为空");
    	user.setModifier(HttpServiceContext.getCurrentUserName());
    	user.setModifierId(HttpServiceContext.getCurrentUserId());
    	if(user.getFreezing()==1){
    		userService.freezeUser(user);
    	}else{
    		userService.unfreezeUser(user);
    	}
        return userService.findByUserId(user.getId());
    }

    /**
     * 根据用户ID查询用户信息
    * @Title: findUserByUserId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param userId
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    @API(value = "用户详情")
    @RequestMapping(value = "/v1/user",method = RequestMethod.POST)
    public Object findUserByUserId(@RequestBody User user){
    	ParamVerifyUtil.paramNotNull(user);
    	ParamVerifyUtil.paramNotNull(user.getId(), "用户ID不能为空");
        return userService.findByUserId(user.getId());
    }
    
    /**
     * 分页查询用户列表
    * @Title: findUserListByPage 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param userSearchReqView
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    @API(value = "分页查询用户列表")
    @RequestMapping(value = "/v1/users/page",method = RequestMethod.POST)
    public Object findUserListByPage(@RequestBody User user){
        return userService.findUserListByPage(user);
    }
    
    /**
     * 根据系统-用户查询组织机构
    * @Title: findOrganizationsBySystemId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param systemId
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    @API("用户-根据系统ID-用户ID查询组织机构")
    @RequestMapping(value = "/v1/user/system",method = RequestMethod.POST)
    public Object findOrganizationsBySystemId(@RequestBody User user){
    	ParamVerifyUtil.paramNotNull(user);
    	ParamVerifyUtil.paramNotNull(user.getSystemId(), "系统ID不能为空");
    	//取登录人ID
        return  organizationService.selectByUserIdAndSystemId(new Organization(HttpServiceContext.getCurrentUserId(), user.getSystemId()));
    }
    
    /**
     * 根据组织机构ID查询角色
    * @Title: findRolesByOrganizationId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param organization_id
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    @API(value = "用户-根据组织ID查询所有角色")
    @RequestMapping(value = "/v1/user/organizations",method = RequestMethod.POST)
    public Object findRolesByOrganizationId(@RequestBody User user){
    	ParamVerifyUtil.paramNotNull(user);
    	ParamVerifyUtil.paramNotNull(user.getOrganizationId(), "组织机构ID不能为空");
    	ParamVerifyUtil.paramNotNull(user.getSystemId(), "系统ID不能为空");
        return roleService.findRolesByOrganizationId(new Role(user.getOrganizationId(), user.getSystemId()));
    }
    
    /**
     * 根据组织机构ID、用户ID查询角色
    * @Title: findRolesBySystemId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param systemId
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    @API(value = "用户-根据组织ID、用户Id查询所有角色")
    @RequestMapping(value = "/v1/user/organizations_user",method = RequestMethod.POST)
    public Object findRolesByUserIdAndOrganizationId(@RequestBody User user){
    	ParamVerifyUtil.paramNotNull(user);
    	ParamVerifyUtil.paramNotNull(user.getOrganizationId(), "组织机构ID不能为空");
    	//取当前登录人
    	Role role = new Role();
		role.setUserId(HttpServiceContext.getCurrentUserId());
		role.setOrganizationId(user.getOrganizationId());
        return roleService.findRolesByOrganizationIdAndUserId(role);
    }
    
    /**
     * 查询系统列表
    * @Title: findSystems 
    * @Description: TODO(根据用户查询可分配系统列表) 
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    @API("用户-查询系统列表")
    @RequestMapping(value = "/v1/user/systems",method = RequestMethod.GET)
    public Object findSystems(){
    	User user = new User();
    	user.setId(HttpServiceContext.getCurrentUserId());
        return systemService.findSystemsOfUser(user);
    }
    
    @API(value="权限中系统根据用户token获取其权限-api列表和menus列表")
	@RequestMapping(value="/v1/user/roles",method=RequestMethod.POST)
	public Object getUserRoleByToken(HttpServletRequest request){
		
    	//从请求头里面拿出token
		String tokenID = request.getHeader("HaiYi-Access-Token");
		if(null == tokenID){
			throw new RuntimeExceptionWarn("请求头里不含token");
		}	
		//下面是获取用户的权限
		User userAuthByUserToken = accessTokenService.getUserAuthByUserToken(tokenID);
		//如果返回用户权限为空，那么在子系统端提示客户端用户已经被禁用

		return userAuthByUserToken;
	}
    @API(value="权限系统中根据用户token获取用户的详细信息",auth = false)
	@RequestMapping(value="/v1/user/info",method=RequestMethod.POST)
	public Object childSystemGetUserInfoByToken(HttpServletRequest request){
		
    	//从请求头里面拿出token
		String tokenID = request.getHeader("HaiYi-Access-Token");
		if(null == tokenID){
			throw new RuntimeExceptionWarn("请求头里不含token");
		}	
		//根据用户token从数据库中获得用户
		AccessToken accessTokenSearch = new AccessToken();
		accessTokenSearch.setToken(tokenID);
		//根据token获取userId
		List<AccessToken> selectByCondition = accessTokenService.selectByCondition(accessTokenSearch);
		
		if(selectByCondition == null || selectByCondition.isEmpty()){
			//用户token失效
			throw new RuntimeExceptionWarn(ServerStatus.USER_TOKEN_FAILURE);
		}
		AccessToken accessToken = selectByCondition.get(0);
		
		User userById = userService.findById(accessToken.getUserId());
		return userById;
	}
}
