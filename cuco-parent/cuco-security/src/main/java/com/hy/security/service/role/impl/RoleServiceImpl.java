package com.hy.security.service.role.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.hy.common.utils.StringUtils;
import com.hy.enums.ServerStatus;
import com.hy.exception.RuntimeExceptionWarn;
import com.hy.model.PageResult;
import com.hy.security.dao.RoleMapper;
import com.hy.security.entity.Menu;
import com.hy.security.entity.MenuOperation;
import com.hy.security.entity.Operation;
import com.hy.security.entity.Organization;
import com.hy.security.entity.PermissionExchange;
import com.hy.security.entity.Role;
import com.hy.security.entity.System;
import com.hy.security.entity.User;
import com.hy.security.service.menu.MenuService;
import com.hy.security.service.operation.OperationService;
import com.hy.security.service.organization.OrganizationService;
import com.hy.security.service.permissionExchange.PermissionExchangeService;
import com.hy.security.service.role.RoleService;
import com.hy.security.service.system.SystemService;
import com.hy.security.service.user.UserService;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper<Role>tdRoleMapper;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionExchangeService permissionExchangeService;
    @Autowired
    private OperationService operationService;
    @Override
    public Role insertSelective(Role tdRole){
    	this.tdRoleMapper.insertSelective(tdRole);   
    	return (Role) this.tdRoleMapper.selectByPrimaryKey(tdRole.getId());
    }

    @Override
    public Integer insertBatch(List<Role> tdRole){
           return this.tdRoleMapper.insertBatch(tdRole) ;
    }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tdRoleMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tdRoleMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(Role tdRole) {
           return this.tdRoleMapper.updateByPrimaryKeySelective(tdRole);
    }

    @Override
    public Role findById(Object id) {
           return (Role) this.tdRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> selectByCondition(Role tdRole) {
           return (List<Role>) this.tdRoleMapper.selectByCondition(tdRole);
    }

    /**
     * 创建角色
     */
    public Role createRole(Role role){
    	if(null == role || StringUtils.isBlank(role.getName()) || null == role.getSystemId() || null == role.getModifierId() 
    			|| StringUtils.isBlank(role.getModifier())){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
//    	role.setCode(code);
    	role.setCreated(new Date());
    	role.setLasttimeModify(new Date());
    	return this.insertSelective(role);
    }
    
    /**
     * 修改角色状态
     */
	@Override
	public Role delete(Role role) {
		if(null == role ||  null == role.getId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
    	role.setLasttimeModify(new Date());
    	role.setIsValue(1);
    	this.updateByPrimaryKeySelective(role);
    	return this.findById(role.getId());
	}

	  /**
     * 修改角色
     */
	@Override
	public Role updateRole(Role role) {
		if(null == role ||  null == role.getId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
    	role.setLasttimeModify(new Date());
    	this.updateByPrimaryKeySelective(role);
    	return this.findById(role.getId());
	}
	
	/**
	 * 根据用户ID查询角色
	 */
	@Override
	public List<Role> findRolesByUserId(Role tdRole) {
//		Assert.notNull(tdRole,"根据用户ID查询角色列表,信息不能为null!");
//		Assert.notNull(tdRole.getUserId(), "根据用户ID查询角色列表、组织机构ID不能为空！");
		if(null == tdRole || null == tdRole.getUserId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
		return this.tdRoleMapper.selectRolesByUserId(tdRole);
	}

	/**
	 * 根据组织机构查询用户拥有所有角色
	 */
	@Override
	public List<Role> findRolesByOrganizationIdAndUserId(Role tdRole) {
//		Assert.notNull(tdRole,"根据组织机构ID查询角色列表,信息不能为null!");
//		Assert.notNull(tdRole.getOrganizationId(), "根据组织机构ID查询用户角色列表、组织机构ID不能为空！");
		if(null == tdRole || null == tdRole.getOrganizationId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
		return this.tdRoleMapper.selectRolesByUserIdAndOrganizationId(tdRole);
	}

	/**
	 * 根据组织机构ID查询角色列表
	 */
	@Override
	public List<Role> findRolesByOrganizationId(Role tdRole) {
//		Assert.notNull(tdRole,"根据组织机构ID查询角色列表,信息不能为null!");
//		Assert.notNull(tdRole.getOrganizationId(), "根据组织机构ID查询角色列表、组织机构ID不能为空！");
//		Assert.notNull(tdRole.getSystemId(), "根据系统ID查询角色列表、组织机构ID不能为空！");
		if(null == tdRole || null == tdRole.getOrganizationId() || null == tdRole.getSystemId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
		return this.tdRoleMapper.selectByCondition(tdRole);
	}
	
	 /**
	  * 按用户id、系统ID、角色为root权限为查询条件 查找组织机构(包括子菜单)--找到所有角色
	  */
    public List<Role> selectRolesByUserIdAndSystemId(Role tdRole){
//    	Assert.notNull(tdRole,"按用户id、系统ID为查询条件 查找组织机构(包括子菜单)所有角色,信息不能为null!");
//		Assert.notNull(tdRole.getUserId(), "按用户id、系统ID为查询条件 查找组织机构(包括子菜单)所有角色,用户不能为空!");
		if(null == tdRole || null == tdRole.getUserId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
    	return this.tdRoleMapper.selectRolesByUserIdAndSystemId(tdRole);
    }
    
    
    
    /**
     * 组织机构给root角色重置权限信息
     */
    public Role insertMenuForRootrole(Role role){
    	//验证信息
    	if(null == role || null == role.getId() || StringUtils.isBlank(role.getCode()) 
    			|| null == role.getMenuId() || null == role.getSystemId() ){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
    	//删除root权限下所有组织机构角色含有当前未添加权限的信息
    	// role.getOperationIds() == ""的话那么就是删除所有，但是在此没有删，但是下面又删了
    	this.checkMenuForRootrole(role);
    	
    	//删除角色菜单下所有操作关联
    	this.tdRoleMapper.deleteOperationForRole(role);
    	//查询菜单，操作关联关系
    	List<Role> roles = new ArrayList<>();
    	if(role.getOperationIds().length()>0){
    		String[] roleArray= role.getOperationIds().split(",");
    		for (int i = 0; i < roleArray.length; i++) {
    			MenuOperation menuOperation = new MenuOperation();
    			menuOperation.setMenuId(role.getMenuId());
    			menuOperation.setOperationId(Long.parseLong(roleArray[i]));
    			menuOperation.setSystemId(role.getSystemId());
    			menuOperation= menuService.findMenuOperationId(menuOperation);
    			
    			Role insertRole = new Role();
    			insertRole.setMenuId(role.getMenuId());
    			insertRole.setOperationId(Long.parseLong(roleArray[i]));
    			insertRole.setMenuOperationId(menuOperation.getId());
    			insertRole.setId(role.getId());
    			roles.add(insertRole);
			}
    		role.setRoleList(roles);
    		//插入关联关系
    		this.tdRoleMapper.insertMenuForRole(role);
    	}
    	//记录权限信息变更
    	this.insertPermissionExchange(role);
    	
    	Organization updateModifier = new Organization(); 
    	updateModifier.setId(role.getOrganizationId());
    	updateModifier.setLasttimeModify(new Date());
    	updateModifier.setModifierId(role.getModifierId());
    	updateModifier.setModifier(role.getModifier());
    	if(updateModifier.getId() != null){
    		
    		organizationService.updateByPrimaryKeySelective(updateModifier);
    	}
    	return this.findById(role.getId());
    }
    private List<Operation> getDeleteOpers(Role role){
    	Menu menu = new Menu();
    	menu.setRoleId(role.getId());
    	menu.setId(role.getMenuId());
    	List<Operation> operations = operationService.findOperationsByRoleIdAndMenuId(menu);
    	//if(StringUtils.isNoneBlank( role.getOperationIds())){
    		//新分配权限
    		Map< Long, Operation> map = new HashMap<>();
    		//如果没有选中菜单的操作，那么就是全部删除这个菜单的操作，之前是如果不选，这里什么都不做
    		//现在这里吧操作集合置为空，那么下面的删除集合就是菜单的全部
    		if(StringUtils.isNoneBlank( role.getOperationIds())){
    			String[] operationArray= role.getOperationIds().split(",");
	    		for (int i = 0; i < operationArray.length; i++) {
	    			Operation operationNew = operationService.findOperationById(Long.parseLong(operationArray[i]));
	    			map.put(Long.parseLong(operationArray[i]), operationNew);
	    		}
    		}
    		List<Operation> deleteOper = new ArrayList<>();
    		//检测删除权限
    		Role menuOperIdCondition = new Role();
    		
    		menuOperIdCondition.setOrganizationId(role.getOrganizationId());
    		menuOperIdCondition.setMenuId(role.getMenuId());
    		for (Operation operationView : operations) {
				if(map.get(operationView.getId())==null){
					//当前在哪个部门role.organization_id  是哪个菜单role.getMenuId()  要删除哪个操作operationView.getId()
					//需要知道删除菜单的操作在下一级有没有选择，如果选择了，那么需要提示下一级菜单角色中选中，不能删除
					menuOperIdCondition.setOperationId(operationView.getId());
					Integer cnt = tdRoleMapper.countChildOrgOpersByMenuOperId(menuOperIdCondition);
					if(cnt > 0){
						throw new RuntimeExceptionWarn(operationView.getName()+"被下级部门或者同级部门其他角色选中，不能删除！！！");
					}
					deleteOper.add(operationView);
				}
			}
    		return deleteOper;
    }
    /**
     * 检测权限变更 删除root权限下子权限
     */
    private void checkMenuForRootrole(Role role){
    	
    	List<Operation> deleteOper = getDeleteOpers(role);
    		//删除角色下权限
    		if(deleteOper.size()>0){
    			//组织机构下所有角色  #此处是根据部门code模糊查询的，自定义角色没有code，所以自定义的角色下的操作没删除
    			//所以在此选择本级部门下的角色，包括**-root和自定义的角色，应为在上面已经判断了操作在子级有没有被选，在此只要安心删除本级下即可
//    			List<Role> roles =  this.tdRoleMapper.selectRolesByCode(role);
    			Role systemIdCondition = new Role();
    			systemIdCondition.setSystemId(role.getSystemId());
    			systemIdCondition.setOrganizationId(role.getOrganizationId());
    			List<Role> roles =  tdRoleMapper.selectByCondition(systemIdCondition );
    			
    			for (Role roleView : roles) {
    				for (Operation deleteOperation : deleteOper) {
    					roleView.setMenuId(role.getMenuId());
    					roleView.setOperationId(deleteOperation.getId());
    					this.tdRoleMapper.deleteMenuForRole(roleView);
    				}
    				//权限变更通知
    				roleView.setModifier(role.getModifier());
    				roleView.setModifierId(role.getModifierId());
    				roleView.setSystemId(role.getSystemId());
    				this.insertPermissionExchange(roleView);
    			}
    		}
    	//}
    	
    }
    
    /**
     * 给角色添加菜单
     */
    public Role insertMenuForRole(Role role){
//    	Assert.notNull(role.getOperationIds(),"插入角色菜单关联关系，操作Id不能为空");
    	if(null == role || null == role.getId() 
    			|| null == role.getMenuId() || null == role.getSystemId() ){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
    	//验证删除的操作在子级部门角色中是否选中，如果选中，那么就提示不能删除
    	Role roleById = tdRoleMapper.selectByPrimaryKey(role.getId());
    	if(roleById != null && roleById.getCode() != null){
    		role.setOrganizationId(roleById.getOrganizationId());
        	getDeleteOpers(role);
    	}
    	
    	//this.checkMenuForRootrole(role);
    	//删除角色菜单下所有操作关联
    	this.tdRoleMapper.deleteOperationForRole(role);
    	//查询菜单，操作关联关系
    	List<Role> roles = new ArrayList<>();
    	if(role.getOperationIds().length()>0){
    		String[] roleArray= role.getOperationIds().split(",");
    		for (int i = 0; i < roleArray.length; i++) {
    			MenuOperation menuOperation = new MenuOperation();
    			menuOperation.setMenuId(role.getMenuId());
    			menuOperation.setOperationId(Long.parseLong(roleArray[i]));
    			menuOperation.setSystemId(role.getSystemId());
    			menuOperation= menuService.findMenuOperationId(menuOperation);
    			
    			Role insertRole = new Role();
    			insertRole.setMenuId(role.getMenuId());
    			insertRole.setOperationId(Long.parseLong(roleArray[i]));
    			insertRole.setMenuOperationId(menuOperation.getId());
    			insertRole.setId(role.getId());
    			roles.add(insertRole);
			}
    		role.setRoleList(roles);
    		//插入关联关系
    		this.tdRoleMapper.insertMenuForRole(role);
    	}
    	//记录权限信息变更
    	this.insertPermissionExchange(role);
    	
    	//跟新角色修改人和修改时间
    	Role updateModifier = new Role(); 
    	updateModifier.setId(role.getId());
    	updateModifier.setModifier(role.getModifier());
    	updateModifier.setModifierId(role.getModifierId());
    	updateModifier.setLasttimeModify(new Date());
		tdRoleMapper.updateByPrimaryKeySelective(updateModifier);
    	
    	return this.findById(role.getId());
    }
    
    /**
     * 删除角色菜单
     */
    public Role deleteMenuForRole(Role role){
    	if(null == role || null == role.getId() || null == role.getMenuId() || null == role.getOperationId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
    	this.tdRoleMapper.deleteMenuForRole(role);
    	return this.findById(role.getId());
    }
    /**
     * 权限变更通知
    * @Title: insertPermissionExchange 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param role    设定文件 
    * @return void    返回类型 
    * @throws
     */
    private void insertPermissionExchange(Role role){
    		List<PermissionExchange> permissionExchangeList = new ArrayList<>();
    		User user = new User();
    		user.setRoleId(role.getId());
    		List<User> users = userService.selectUserByRoleId(user);
    		for (User userView : users) {
    			//插入用户修改信息
    			PermissionExchange permissionExchange = new PermissionExchange();
    			permissionExchange.setCreated(new Date());
    			permissionExchange.setLasttimeModify(new Date());
    			permissionExchange.setModifier(role.getModifier());
    			permissionExchange.setModifierId(role.getModifierId());
    			permissionExchange.setSystemId(role.getSystemId());
    			permissionExchange.setType(2);
    			permissionExchange.setUserId(userView.getId());
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
	 /**
	  * 分页
	  * 按用户id、系统ID、角色为root权限为查询条件 查找组织机构(包括子菜单)--找到所有角色
	  */
	  public List<Role> selectRolesByUserIdAndSystemIdForPage(Role tdRole){
	//   	Assert.notNull(tdRole,"按用户id、系统ID为查询条件 查找组织机构(包括子菜单)所有角色,信息不能为null!");
	//		Assert.notNull(tdRole.getUserId(), "按用户id、系统ID为查询条件 查找组织机构(包括子菜单)所有角色,用户不能为空!");
			if(null == tdRole || null == tdRole.getUserId()){
				throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
			}
	   	return this.tdRoleMapper.selectRolesByUserIdAndSystemIdForPage(tdRole);
	  }
    
    
    /**
     * 根据组织机构Id取机构root用户
     */
    public Role findRoleByOrganizationId(Role role){
//    	Assert.notNull(role,"根据组织机构Id取机构root用户,信息不能为null!");
//    	Assert.notNull(role.getOrganizationId(),"根据组织机构Id取机构root用户信息,id不能为null!");
    	if(null == role || null == role.getOrganizationId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
    	
    	Organization organization = organizationService.findById(role.getOrganizationId());
    	Role newrole = new Role();
    	newrole.setCode(organization.getCode());
    	newrole.setOrganizationId(organization.getId());
    	List<Role> roleList = this.selectByCondition(newrole);
    	if(roleList.size()>0){
    		//根据角色查询菜单
    		return roleList.get(0);
    	}
    	return null;
    }
    
    /**
     * 分页查询
     */
    public PageResult<Role> findOrganizationListByPage(Role role){
    	Integer page = role.getPage();
	    Integer pageSize = role.getPageSize();
	     /*搜索条件*/
	    Role roleSearch = new Role();
	    roleSearch.setUserId(role.getUserId());
        if(StringUtils.isNotBlank(role.getName())){
        	roleSearch.setName(role.getName());
        }
	    List<Role> roles = null;
	    /*总记录数*/
	    Integer totalSize = this.selectRolesByUserIdAndSystemIdForPage(roleSearch).size();
	    /*分页信息*/
	    PageHelper.startPage(page,pageSize);
	    roles = this.selectRolesByUserIdAndSystemIdForPage(roleSearch);
        if(!CollectionUtils.isEmpty(roles)){
        	for (Role roleView : roles) {
        		if(roleView.getSystemId()!=null){
        			System system = systemService.findById(roleView.getSystemId());
        			roleView.setSystemName(system.getName());
        		}
        		if(roleView.getOrganizationId()!=null){
        			String name = "";
        			Organization organization = new Organization();
        			organization =organizationService.findById(roleView.getOrganizationId());
        			for (int i = 1; i < organization.getCode().length()/2; i++) {
						List<Organization> organizationsL = organizationService.selectByCondition(new Organization(organization.getCode().substring(0,i+1*2)));
						if(organizationsL.size()>0){
							name+= organizationsL.get(0).getName()+"-";
						}
					}
					roleView.setOrganizationName(name+organization.getName());
        		}
			}
        }
        PageResult<Role> pageResult = new PageResult<Role>(page,pageSize,totalSize,roles);
        return pageResult;
    }
    
    /**
     * 查询角色信息携带上级组织机构信息
     */
    public Role selectRoleInfoAddOrganization(Role role){
    	if(null == role || null == role.getId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
    	role =  this.findById(role.getId());
    	Organization organization = organizationService.findById(role.getOrganizationId());
    	List<Organization> organizations = new ArrayList<>();
    	for (int i = 0; i < organization.getCode().length()/2; i++) {
    		Organization organizationView = organizationService.selectOrganizationByUserCode(new Organization(organization.getCode().substring(0,(i+1)*2)));
    		organizations.add(organizationView);
		}
    	role.setOrganizations(organizations);
    	return role;
    }
}
