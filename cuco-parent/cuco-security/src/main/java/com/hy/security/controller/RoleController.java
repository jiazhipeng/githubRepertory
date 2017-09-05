package com.hy.security.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.hy.exception.RuntimeExceptionWarn;
import com.hy.security.common.HttpServiceContext;
import com.hy.security.entity.Menu;
import com.hy.security.entity.Operation;
import com.hy.security.entity.Organization;
import com.hy.security.entity.Role;
import com.hy.security.entity.System;
import com.hy.security.entity.User;
import com.hy.security.service.menu.MenuService;
import com.hy.security.service.operation.OperationService;
import com.hy.security.service.organization.OrganizationService;
import com.hy.security.service.role.RoleService;
import com.hy.security.service.system.SystemService;

/**
 * Created by WangShuai on 2016/7/22.
 */
@RestController
public class RoleController {
	private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RoleService roleService;
    @Autowired
    private OperationService operationService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private OrganizationService organizationService;
    /**
     * 分页查询角色列表
    * @Title: findUserListByPage 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param userSearchReqView
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    @API(value = "分页查询角色列表")
    @RequestMapping(value = "/v1/role/page",method = RequestMethod.POST)
    public Object findRoleListByPage(@RequestBody Role role){
        role.setUserId(HttpServiceContext.getCurrentUserId());
        return roleService.findOrganizationListByPage(role);
    }
    
    /**
     * 根据组织结构ID查询角色
    * @Title: findRolesBySystemId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param systemId
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    @API(value = "根据组织ID查询所有角色")
    @RequestMapping(value = "/v1/roles/organizations",method = RequestMethod.POST)
    public Object findRolesBySystemId(@RequestBody Role role){
    	ParamVerifyUtil.paramNotNull(role);
    	ParamVerifyUtil.paramNotNull(role.getOrganizationId(), "组织机构ID不能为空");
    	
        return roleService.findRolesByOrganizationId(role);
    }
    
    /**
     * 创建角色
    * @Title: createRole 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param role
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    @API(value = "创建角色")//TODO 角色至能属于部门，不可直接属于系统(即部门ID不可为空)
    @RequestMapping(value = "/v1/role/create",method = RequestMethod.POST)
    public Object createRole(@RequestBody Role role){
    	ParamVerifyUtil.paramNotNull(role);
    	ParamVerifyUtil.paramNotNull(role.getOrganizationId(), "组织机构ID不能为空");
    	ParamVerifyUtil.paramNotNull(role.getName(), "组织机构ID不能为空",200);
    	if(!RegexUtils.isAvailableSizeCharacter(role.getName(), 10)){
    		throw new RuntimeExceptionWarn("角色名称长度超出限制");
    	}
    	ParamVerifyUtil.paramNotNull(role.getSystemId(), "系统ID不能为空");
    	role.setModifier(HttpServiceContext.getCurrentUserName());
        role.setModifierId(HttpServiceContext.getCurrentUserId());
//        Organization organization = organizationService.findById(role.getOrganizationId());
//        role.setCode(organization.getCode());
    	return roleService.createRole(role);
    }

    /**
     * 修改角色
    * @Title: updateRole 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param role
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    @API(value = "更新角色") //TODO 只更新角色名称，不改变角色所属部门
    @RequestMapping(value = "/v1/role/update",method = RequestMethod.POST)
    public Object updateRole(@RequestBody Role role){
    	ParamVerifyUtil.paramNotNull(role);
    	ParamVerifyUtil.paramNotNull(role.getId(), "角色ID不能为空");
    	ParamVerifyUtil.paramNotNull(role.getName(), "组织机构ID不能为空",200);
    	if(!RegexUtils.isAvailableSizeCharacter(role.getName(), 10)){
    		throw new RuntimeExceptionWarn("角色名称长度超出限制");
    	}
    	role.setModifier(HttpServiceContext.getCurrentUserName());
        role.setModifierId(HttpServiceContext.getCurrentUserId());
        return roleService.updateRole(role);
    }

    
    /**
     * 删除角色信息
    * @Title: deleteRole 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param role
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    @API(value = "删除角色")
    @RequestMapping(value = "/v1/role/delete",method = RequestMethod.POST)
    public Object deleteRole(@RequestBody Role role){
    	ParamVerifyUtil.paramNotNull(role);
    	ParamVerifyUtil.paramNotNull(role.getId(), "角色ID不能为空");
    	role.setModifier(HttpServiceContext.getCurrentUserName());
        role.setModifierId(HttpServiceContext.getCurrentUserId());
    	return roleService.delete(role);
    }
    /**
     * 为角色添加菜单操作
    * @Title: createOrganizationOperations 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param object
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    @API(value = "为角色添加菜单操作")//TODO 待确认
    @RequestMapping(value = "/v1/role/add_operations",method = RequestMethod.POST)
    public Object createOrganizationOperations(@RequestBody Role role){
    	ParamVerifyUtil.paramNotNull(role);
    	ParamVerifyUtil.paramNotNull(role.getId(), "角色ID不能为空");
    	ParamVerifyUtil.paramNotNull(role.getMenuId(), "菜单ID不能为空");
//    	ParamVerifyUtil.paramNotNull(role.getOperationIds(), "操作ID不能为空");
    	ParamVerifyUtil.paramNotNull(role.getSystemId(), "系统ID不能为空");
    	role.setModifier(HttpServiceContext.getCurrentUserName());
    	role.setModifierId(HttpServiceContext.getCurrentUserId());
        return roleService.insertMenuForRole(role);
    }
    /**
     * 删除角色菜单操作
    * @Title: deleteOperations 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param object
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    @API(value = "删除角色菜单操作")
    @RequestMapping(value = "/v1/role/delete_operations",method = RequestMethod.POST)
    public Object deleteOperations(@RequestBody Role role){
    	ParamVerifyUtil.paramNotNull(role);
    	ParamVerifyUtil.paramNotNull(role.getId(), "角色ID不能为空");
    	ParamVerifyUtil.paramNotNull(role.getMenuId(), "菜单ID不能为空");
    	ParamVerifyUtil.paramNotNull(role.getOperationId(), "操作ID不能为空");
    	role.setModifier(HttpServiceContext.getCurrentUserName());
    	role.setModifierId(HttpServiceContext.getCurrentUserId());
    	return roleService.deleteMenuForRole(role);
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
    @RequestMapping(value = "/v1/role/system",method = RequestMethod.POST)
    public Object findOrganizationsBySystemId(@RequestBody Role role){
    	ParamVerifyUtil.paramNotNull(role);
    	ParamVerifyUtil.paramNotNull(role.getSystemId(), "系统ID不能为空");
    	List<Organization> organizations = new ArrayList<>();
		organizations = organizationService.selectOrganizationBySystemIdAndForOrganizationAndRoles(new Organization(HttpServiceContext.getCurrentUserId(), role.getSystemId()));
        return  organizations;
    }
    
    /**
     * 根据角色ID查询所拥有菜单
    * @Title: findMenuByOrganizationId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param organizationId
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    @API(value = "根据角色ID查询所拥有菜单")
    @RequestMapping(value = "/v1/role/find_menus",method = RequestMethod.POST)
    public Object findMenuByOrganizationId(@RequestBody Role role){
    	ParamVerifyUtil.paramNotNull(role);
    	ParamVerifyUtil.paramNotNull(role.getId(), "角色ID不能为空");
    	Menu menu = new Menu();
    	menu.setRoleId(role.getId());
    	List<Menu> menus = menuService.findMenuByRole(menu);
    	return menus;
    }
    
    /**
     * 根据角色ID和菜单ID查询所拥有操作
    * @Title: findOperationsByOrganizationId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param organizationId
    * @param @param menuId
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    @API(value = "根据角色ID和菜单ID查询所拥有操作")
    @RequestMapping(value = "/v1/role/find_operations",method = RequestMethod.POST)
    public Object findOperationsByOrganizationId(@RequestBody Role role){
    	ParamVerifyUtil.paramNotNull(role);
    	ParamVerifyUtil.paramNotNull(role.getId(), "角色ID不能为空");
    	ParamVerifyUtil.paramNotNull(role.getMenuId(), "菜单ID不能为空");
    	Menu menu = new Menu();
    	menu.setId(role.getMenuId());
    	menu.setRoleId(role.getId());
    	List<Operation> operations = new ArrayList<>();
    	operations = operationService.findOperationsByRoleIdAndMenuId(menu);
    	return  operations;
    }

    /**
     * 根据菜单ID查询操作
    * @Title: findOperationsByMenuId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param organizationId
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    @API("根据菜单ID查询操作")
    @RequestMapping(value = "/v1/role/operations",method = RequestMethod.POST)
    public Object findOperationsByMenuId(@RequestBody Role role){
    	ParamVerifyUtil.paramNotNull(role);
    	ParamVerifyUtil.paramNotNull(role.getMenuId(), "菜单ID不能为空");
    	ParamVerifyUtil.paramNotNull(role.getOrganizationId(), "组织结构ID不能为空");
    	ParamVerifyUtil.paramNotNull(role.getId(), "角色ID不能为空");
    	
    	Role roleTemp = roleService.findRoleByOrganizationId(role);
    	Menu menu = new Menu();
    	menu.setId(role.getMenuId());
    	menu.setRoleId(roleTemp.getId());
    	List<Operation> operations = new ArrayList<>();
    	operations = operationService.findOperationsByRoleIdAndMenuId(menu);
    	
    	if(operations.size()>0){
    		Menu menuUser = new Menu();
    		menuUser.setId(role.getMenuId());
    		menuUser.setRoleId(role.getId());
    		List<Operation> operationList = new ArrayList<>();
    		operationList = operationService.findOperationsByRoleIdAndMenuId(menuUser);
    		Map<Long, Operation> tempOperation = new HashMap<>();
    		
    		if(operationList.size()>0){
    			for (Operation operationView : operationList) {
    				tempOperation.put(operationView.getId(), operationView);
				}
    			if(operations.size()>0){
    				for (Operation operationCheck : operations) {
						if(tempOperation.get(operationCheck.getId())!=null){
							operationCheck.setIsCheck(1);
						}else{
							operationCheck.setIsCheck(0);
						}
					}
    			}
    		}
    	}
    	
    	
        return operations;
    }
    
    /**
     * 根据部门ID查询菜单
    * @Title: findMenusByOrganizationId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param organizationId
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    @API("根据组织机构ID查询菜单")
    @RequestMapping(value = "/v1/role/menus",method = RequestMethod.POST)
    public Object findMenusByOrganizationId(@RequestBody Role role){
    	ParamVerifyUtil.paramNotNull(role);
    	ParamVerifyUtil.paramNotNull(role.getOrganizationId(), "组织机构ID不能为空");
    	ParamVerifyUtil.paramNotNull(role.getId(), "角色ID不能为空");
    	Role roleTemp = roleService.findRoleByOrganizationId(role);
    	Menu menu = new Menu();
    	menu.setRoleId(roleTemp.getId());
    	List<Menu> menus = new ArrayList<>();
    	menus = menuService.findMenuByRole(menu);
    	
    	 if(menus.size()>0){
    		 //角色拥有的菜单
    		 Menu menuUser = new Menu();
    		 menuUser.setRoleId(role.getId());
    		 List<Menu> menuList = menuService.findMenuByRole(menuUser);
    		 
    		 Map<Long, Menu> tempMenu = new HashMap<>();
    		 
    		 if(menuList.size()>0){
    			 for (Menu menuView : menuList) {
    				 tempMenu.put(menuView.getId(), menuView);
				}
    		 }
			 if(menus.size()>0){
				 for (Menu menuCheck : menus) {
					if(tempMenu.get(menuCheck.getId())!=null){
						menuCheck.setIsCheck(1);
					}else{
						menuCheck.setIsCheck(0);
					}
				}
			 }
		 }
    	
    	return menus;
    }
    
    /**
     * 查询系统列表
    * @Title: findSystems 
    * @Description: TODO(根据用户查询可分配系统列表) 
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    @API("查询系统列表")
    @RequestMapping(value = "/v1/role/systems",method = RequestMethod.GET)
    public Object findSystems(){
    	User user = new User();
    	user.setId(HttpServiceContext.getCurrentUserId());
    	List<System> systems = new ArrayList<>();
    	systems = systemService.findSystemsOfUser(user);
        return systems;
    }
    
    /**
     * 修改组织机构名称
    * @Title: findOperationsByOrganizationId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param organizationId
    * @param @param menuId
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    @API(value = "取角色信息")
    @RequestMapping(value = "/v1/role/update_role",method = RequestMethod.POST)
    public Object updateRoleView(@RequestBody Role role){
    	ParamVerifyUtil.paramNotNull(role);
    	ParamVerifyUtil.paramNotNull(role.getId(), "组织机构ID不能为空");
	    return roleService.selectRoleInfoAddOrganization(role);
    }
}
