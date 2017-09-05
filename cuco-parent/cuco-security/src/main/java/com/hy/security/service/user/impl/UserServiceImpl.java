package com.hy.security.service.user.impl;

import java.util.*;

import com.hy.common.utils.ParamVerifyUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.hy.common.utils.StringUtils;
import com.hy.enums.ServerStatus;
import com.hy.exception.RuntimeExceptionWarn;
import com.hy.model.PageResult;
import com.hy.security.common.HttpServiceContext;
import com.hy.security.dao.MenuMapper;
import com.hy.security.dao.UserMapper;
import com.hy.security.entity.Menu;
import com.hy.security.entity.Nation;
import com.hy.security.entity.Organization;
import com.hy.security.entity.PermissionExchange;
import com.hy.security.entity.Role;
import com.hy.security.entity.User;
import com.hy.security.entity.UserCity;
import com.hy.security.service.api.ApiService;
import com.hy.security.service.menu.MenuService;
import com.hy.security.service.nation.NationService;
import com.hy.security.service.organization.OrganizationService;
import com.hy.security.service.permissionExchange.PermissionExchangeService;
import com.hy.security.service.role.RoleService;
import com.hy.security.service.user.UserCityService;
import com.hy.security.service.user.UserService;
import com.hy.security.util.VerifyUtil;

/**
 * Created by WangShuai on 2016/7/22.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper<User>tdUserMapper;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private UserCityService userCityService;
    
    @Autowired
    private ApiService apiService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private PermissionExchangeService permissionExchangeService;
    @Autowired
    private NationService nationService;
    @Autowired
    private MenuMapper<Menu> menuMapper;
    @Autowired
    private OrganizationService organizationService;
    @Override
    public User insertSelective(User tdUser){
    	   this.tdUserMapper.insertSelective(tdUser);
           return (User) this.tdUserMapper.selectByPrimaryKey(tdUser.getId());
    }

    @Override
    public Integer insertBatch(List<User> tdUser){
           return this.tdUserMapper.insertBatch(tdUser) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Long id) {
            return this.tdUserMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tdUserMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public User updateByPrimaryKeySelective(User tdUser) {
    	this.tdUserMapper.updateByPrimaryKeySelective(tdUser);
        return  (User) this.tdUserMapper.selectByPrimaryKey(tdUser.getId());
    }

    @Override
    public User findById(Long id) {
           return (User) this.tdUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectByCondition(User tdUser) {
           return (List<User>) this.tdUserMapper.selectByCondition(tdUser);
    }
    
    
    /**
     * 删除用户角色
    * @Title: deleteRolesByUser 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param userId    设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void deleteRolesByUser(User user){
    	if(null == user || null==user.getId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
    	}
    	//新增角色信息
    	Map<Long, Role> map = new HashMap<>();
    	if(StringUtils.isNoneBlank(user.getRoleIds())){
			String[] roleArray = user.getRoleIds().split(",");
			for (int i = 0; i < roleArray.length; i++) {
				Role roleNew = roleService.findById(Long.parseLong(roleArray[i]));
				map.put(Long.parseLong(roleArray[i]), roleNew);
			}
		}
    	//插入权限变更记录
    	this.insertPermissionExchange(map, user);
    	//删除角色
    	this.tdUserMapper.deleteBatchForUserRole(user);
    	
    }
    
    /**
     * 记录权限变更
    * @Title: insertPermissionExchange 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param map
    * @param @param user    设定文件 
    * @return void    返回类型 
    * @throws
     */
    private void insertPermissionExchange(Map<Long, Role> map,User user){
    	
    	//查询用户现有角色
    	Role role = new Role();
    	role.setUserId(user.getId());
    	List<Role> roles = roleService.findRolesByUserId(role);
    	
    	if(roles.size()>0){
    		//筛选角色
    		Map<Long,Role> mapCheck = new HashMap<>();
    		for (Role roleView : roles) {
    			if(map.get(roleView.getId())==null){
    				mapCheck.put(roleView.getSystemId(),roleView);
    			}
			}
    		
    		List<PermissionExchange> permissionExchangeList = new ArrayList<>();
    		for (Long key : mapCheck.keySet()) {  
    			//插入用户修改信息
    			PermissionExchange permissionExchange = new PermissionExchange();
    			permissionExchange.setCreated(new Date());
    			permissionExchange.setLasttimeModify(new Date());
    			permissionExchange.setModifier(user.getModifier());
    			permissionExchange.setModifierId(user.getModifierId());
    			permissionExchange.setSystemId(mapCheck.get(key).getSystemId());
    			permissionExchange.setType(3);
    			permissionExchange.setUserId(user.getId());
    			permissionExchangeList.add(permissionExchange);
    		} 
    		try {
    			if(permissionExchangeList.size()>0){
    				permissionExchangeService.insertBatch(permissionExchangeList);
    			}
			} catch (Exception e) {
				throw new RuntimeExceptionWarn("插入权限变更记录异常");
			}
    	}
    	
    }
    
    
    /**
     * 给用户分配角色
    * @Title: insertRolesForUser 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param user    设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void insertRolesForUser(User user){
		if(null == user || null==user.getId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
    	//插入角色
    	if(StringUtils.isNoneBlank(user.getRoleIds())){
    		String[] roles = user.getRoleIds().split(",");
    		List<Role> roleList = new ArrayList<>();
    		for (int i = 0; i < roles.length; i++) {
    			Role role = new Role();
    			Long roleId;
    			try {
    				roleId = Long.parseLong(roles[i]);
    				role.setId(roleId);
        			if(!roleList.contains(role)){
        				
        				roleList.add(role);
        			}
				} catch (Exception e) {
					continue;
				}
    			
			}
    		user.setRoles(roleList);
    		this.tdUserMapper.insertRoleForUser(user);
    	}
    }
    
    /**
     * 给用户添加城市
    * @Title: insertCitysForUser 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param user    设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void insertCitysForUser(User user){
    	if(null == user || null==user.getId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
    	//插入城市
    	if(StringUtils.isNoneBlank(user.getCityIds())){
    		String[] citys = user.getCityIds().split(",");
    		List<UserCity> UserCityList = new ArrayList<>();
    		for (int i = 0; i < citys.length; i++) {
    			try {
    				UserCity userCity = new UserCity();
        			userCity.setUserId(user.getId());
        			userCity.setCityId(Long.parseLong(citys[i]));
        			UserCityList.add(userCity);
				} catch (Exception e) {
					continue;
				}
    			
			}
    		this.userCityService.insertBatch(UserCityList);
    	}
    }
    
    /**
     * 删除用户城市
    * @Title: deleteCitysByUser 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param user    设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void deleteCitysByUser(User user){
//    	Assert.notNull(user.getId(), "删除用户城市，用户ID不允许为空");
    	if(null == user || null==user.getId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
    	this.userCityService.deleteCityByUser(new UserCity(user.getId()));
    }
    /**
     * 创建用户
    * @Title: createUser 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param user
    * @param @return    设定文件 
    * @return User    返回类型 
    * @throws
     */
    public User createUser(User user){
//    	Assert.notNull(user, "添加用户数据不允许为空");
		if(null == user || StringUtils.isBlank(user.getName())||StringUtils.isBlank(user.getEmail())||StringUtils.isBlank(user.getPhone())
				||StringUtils.isBlank(user.getModifier()) || null == user.getModifierId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
    	user.setCreated(new Date());
    	user.setLasttimeModify(new Date());
    	//创建用户
    	User returtnUser = this.insertSelective(user);
    	user.setId(returtnUser.getId());
    	//插入角色
    	this.insertRolesForUser(user);
    	//插入城市
    	this.insertCitysForUser(user);
    	return user;
    }
    
    
    /**
     * 修改用户
    * @Title: createUser 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param user
    * @param @return    设定文件 
    * @return User    返回类型 
    * @throws
     */
    public User updateUser(User user){
//    	Assert.notNull(user, "查询用户对象不允许为空");
    	if(null == user || null==user.getId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
    	user.setLasttimeModify(new Date());
    	
    	User UserOpenId = this.findById(user.getId());
    	if(StringUtils.isNoneBlank(user.getPhone())){
    		if(!user.getPhone().equals(UserOpenId.getPhone())){
    			this.tdUserMapper.updateOpenIdIsNull(user);
    		}
    	}
    	//修改用户信息
    	this.updateByPrimaryKeySelective(user);
		//批量删除用户
    	this.deleteRolesByUser(user);
    	//插入角色
    	this.insertRolesForUser(user);
    	//删除城市
    	this.deleteCitysByUser(user);
    	//插入城市
    	this.insertCitysForUser(user);
    	return user;
    }

    
    /**
     * 根据ID 查询用户信息
     */
    public User findByUserId(Long id){
//    	Assert.notNull(id, "查询用户对象ID不允许为空");
		if(null==id){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
    	User user = this.findById(id);
    	//查询用户角色
    	if(user!=null){
    		List<UserCity> userCity = userCityService.selectByCondition(new UserCity(user.getId()));
			if(userCity.size()>0){
				for (UserCity userCityView : userCity) {
					Nation nation = nationService.findById(userCityView.getCityId());
					userCityView.setName(nation.getCity());
				}
			}
			user.setCitys(userCity);
			//查询用户角色
			Role role = new Role();
			role.setUserId(user.getId());
			List<Role> roles = roleService.findRolesByUserId(role);
			user.setRoles(roles);
    	}
    	return user;
    }
    
    /**
     * 根据用户资料查询用户信息
     */
    @Override
    public User findByUser(User user) {
//    	Assert.notNull(user, "查询用户对象不允许为空");
    	if(null == user){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
    	List<User> users = this.selectByCondition(user);
    	if(users.size()>0){
    		return users.get(0);
    	}
        return null;
    }

   /**
    * 冻结用户
   * @Title: freezeUser 
   * @Description: TODO(这里用一句话描述这个方法的作用) 
   * @param @param user
   * @param @return    设定文件 
   * @return User    返回类型 
   * @throws
    */
    @Override
    public User freezeUser(User user) {
//    	Assert.notNull(user, "冻结用户，对象不允许为空");
//    	Assert.notNull(user.getId(), "冻结用户，对象ID不允许为空");
    	if(null == user || null == user.getId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
    	user.setFreezing(1);
    	user.setLasttimeModify(new Date());
    	this.updateByPrimaryKeySelective(user);
        return this.findById(user.getId());
    }

    /**
     * 解冻用户
    * @Title: unfreezeUser 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param user
    * @param @return    设定文件 
    * @return User    返回类型 
    * @throws
     */
    @Override
    public User unfreezeUser(User user) {
//    	Assert.notNull(user, "冻结用户，对象不允许为空");
//    	Assert.notNull(user.getId(), "冻结用户，对象ID不允许为空");
    	if(null == user || null == user.getId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
    	user.setFreezing(0);
    	user.setLasttimeModify(new Date());
    	this.updateByPrimaryKeySelective(user);
        return this.findById(user.getId());
    }

    /**
     *分页查询
     */
    @Override
    public PageResult<User> findUserListByPage(User user) {
		Integer page = user.getPage();
	    Integer pageSize = user.getPageSize();
	     /*搜索条件*/
	    List<User> users = null;
	    User userSearch = new User();
        if(StringUtils.isNotBlank(user.getPhone())){
        	userSearch.setPhone(user.getPhone());
        }
        if(user.getSystemId()!=null){
        	userSearch.setSystemId(user.getSystemId());
        }
        if(StringUtils.isNotBlank(user.getCityIds())){
        	userSearch.setCityIds(user.getCityIds());
        }
        /*总记录数*/
        Integer totalSize = this.tdUserMapper.selectByConditionForPage(userSearch).size();
        /*分页信息*/
        PageHelper.startPage(page,pageSize);
        users = this.tdUserMapper.selectByConditionForPage(userSearch);
        if(!CollectionUtils.isEmpty(users)){
    		//待补全
    		for (User userView : users) {
    			List<UserCity> userCity = userCityService.selectByCondition(new UserCity(userView.getId()));
    			if(userCity.size()>0){
    				for (UserCity userCityView : userCity) {
    					Nation nation = nationService.findById(userCityView.getCityId());
    					userCityView.setName(nation.getCity());
    				}
    			}
    			userView.setCitys(userCity);
    			//查询用户角色
    			Role role = new Role();
    			role.setUserId(userView.getId());
    			List<Role> roles = roleService.findRolesByUserId(role);
    			for(Role r : roles){
    				StringBuilder sb = new StringBuilder();
    				Organization organizationTmp = organizationService.findById(r.getOrganizationId());
    				sb.append(organizationTmp.getName());
    				while(true){
    					if(!"0".equals(organizationTmp.getParentId())){
    						organizationTmp = organizationService.findById(Long.parseLong(organizationTmp.getParentId()));
    						sb.insert(0,"-").insert(0,organizationTmp.getName());
    						
        				}else{
        					break;
        				}
    				}
    				r.setOrganizationName(sb.toString());
    				
    			}
    			userView.setRoles(roles);
    		}
        }
        PageResult<User> pageResult = new PageResult<User>(page,pageSize,totalSize,users);
        return pageResult;
    }
    
    /**
     * 绑定用户OpenId
     */
    public User updateUserOpenId(User user){
//    	Assert.notNull(user, "绑定用户，对象不允许为空");
//    	Assert.notNull(user.getId(), "绑定用户，对象ID不允许为空");
//    	Assert.notNull(user.getWechatOpenId(), "绑定用户，openID不允许为空");
    	if(null == user || null == user.getId() || StringUtils.isBlank(user.getWechatOpenId())){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
    	User userOpenId = new User();
    	userOpenId.setId(user.getId());
    	userOpenId.setWechatOpenId(user.getWechatOpenId());
    	return this.updateByPrimaryKeySelective(userOpenId);
    }
    /**
     * 查询用户权限
     */
    public User findApiByUserIdAndSystemId(User user){
//    	Assert.notNull(user, "查询用户权限，对象不允许为空");
//    	Assert.notNull(user.getId(), "查询用户权限，用户ID不允许为空");
//    	Assert.notNull(user.getSystemId(), "查询用户权限，系统ID不允许为空");
    	if(null == user || null == user.getId() || null ==user.getSystemId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
    	User userInfo = findByUserId(user.getId());
    	user.setName(userInfo.getName());
    	if(VerifyUtil.isAdmin(userInfo)){
    		//admin用户直接把当前登录系统的全部api和menu全部加载
    		user.setApis(apiService.findAllBySystemId(user.getSystemId()));
    		user.setMenus(menuService.findMenusBySystemId(user.getSystemId()));
    		return user;
		}
    	List<Menu> findMenuBySystemIdAndUserId = menuService.findMenuBySystemIdAndUserId(user);
    	List<Menu> menus = new ArrayList<Menu>();
    	if(findMenuBySystemIdAndUserId != null
    			&& !findMenuBySystemIdAndUserId.isEmpty()){
    		for(int i=0;i<findMenuBySystemIdAndUserId.size();i++){
    			menus.add(menuMapper.selectByPrimaryKey(findMenuBySystemIdAndUserId.get(i).getId()));
    		}
    	}
    	user.setApis(apiService.findAvailableApisByUserIdAndSystemId(user.getId(), user.getSystemId()));
    	user.setMenus(menus);
    	return user;
    }
    
    /**
     * 根据菜单ID和操作Id查询用户
     */
    public List<User> selectUserByOperationIdAndMenuId(User user){
    	if(null == user || null == user.getOperationId() || null ==user.getMenuId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
    	return tdUserMapper.selectUserByOperationIdAndMenuId(user);
    }
    /**
     * 根据菜单ID和操作Id查询用户
     */
    public List<User> selectUserByRoleId(User user){
    	if(null == user || null == user.getRoleId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
    	return tdUserMapper.selectUserByRoleId(user);
    }

	@Override
	public void insertUserRoleIds(User user) {
		if(user.getId() == null 
				|| user.getRoles() == null 
				|| user.getRoleIds().isEmpty()){
			return;
		}
		this.tdUserMapper.insertRoleForUser(user);
	}

	@Override
	public List<User> getUsersByRoleUniqueCode(String uniqueCode) {
		/* verify */
		ParamVerifyUtil.paramNotEmpty(uniqueCode,"角色标识不可为空");

		/* selectRoleByUniqueCode */
		Role role = new Role();
		role.setUniqueCode(uniqueCode);
		List<Role> roles = roleService.selectByCondition(role);
		if(CollectionUtils.isEmpty(roles)){
			return Collections.emptyList();
		}
		Long roleId = roles.get(0).getId();

		/* selectUsersByRoleId */
		User user = new User();
		user.setRoleId(roleId);
		return this.selectUserByRoleId(user);
	}

}
