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
import com.hy.common.utils.StringUtils;
import com.hy.enums.ServerStatus;
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
 * Created by WangShuai on 2016/7/23.
 */
@RestController
public class OrganizationController {
	private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private OperationService operationService;
    /**
     * 分页查询组织机构
    * @Title: page 
    * @Description: TODO(根据登录用户查询) 
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    @API("分页查询组织机构")
    @RequestMapping(value = "/v1/organizations/page",method = RequestMethod.POST)
    public Object page(@RequestBody Organization Organization){
    	 Organization.setUserId(HttpServiceContext.getCurrentUserId());
    	 return organizationService.findOrganizationListByPage(Organization);
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
    @RequestMapping(value = "/v1/organizations/systems",method = RequestMethod.GET)
    public Object findSystems(){
    	User user = new User();
    	user.setId(HttpServiceContext.getCurrentUserId());
        return systemService.findSystemsOfUser(user);
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
    @API("根据系统ID-用户ID查询组织机构")
    @RequestMapping(value = "/v1/organizations/system",method = RequestMethod.POST)
    public Object findOrganizationsBySystemId(@RequestBody Organization tdOrganization){
    	ParamVerifyUtil.paramNotNull(tdOrganization);
    	ParamVerifyUtil.paramNotNull(tdOrganization.getSystemId(), "系统ID不能为空");
    	//取登录人ID
    	User user = new User();
    	user.setId(HttpServiceContext.getCurrentUserId());
        return  organizationService.selectByUserIdAndSystemId(new Organization(user.getId(), tdOrganization.getSystemId()));
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
    @RequestMapping(value = "/v1/organizations/menus",method = RequestMethod.POST)
    public Object findMenusByOrganizationId(@RequestBody Organization tdOrganization){
    	ParamVerifyUtil.paramNotNull(tdOrganization);
    	ParamVerifyUtil.paramNotNull(tdOrganization.getId(), "组织机构ID不能为空");
    	ParamVerifyUtil.paramNotNull(tdOrganization.getSystemId(), "系统ID不能为空");
    	Organization Organization = organizationService.findById(tdOrganization.getId());
    	Organization.setSystemId(tdOrganization.getSystemId());
    	 //取所有菜单
    	List<Menu> menus = new ArrayList<>();
    	String code = Organization.getCode();
 		if(Organization.getCode().length()-2>2){
			code = Organization.getCode().substring(0, Organization.getCode().length()-2);
			List<Organization> orList = organizationService.selectByCondition(new Organization(code));
			if(orList.size()>0){
				//取管理员角色
				Role role = roleService.findRoleByOrganizationId(new Role(orList.get(0).getId()));
				//取root角色菜单
				Menu menu = new Menu();
				menu.setRoleId(role.getId());
				menus = menuService.findMenuByRole(menu);
			}
		}else{
			menus = menuService.findMenusBySystemId(tdOrganization.getSystemId());
		}
 		//查询已拥有的菜单
		List<Organization> orList = organizationService.selectByCondition(new Organization(Organization.getCode()));
		if(orList.size()>0){
			 //取管理员角色
			 Role role = roleService.findRoleByOrganizationId(new Role(orList.get(0).getId()));
			 //取root角色菜单
			 Menu menu = new Menu();
			 menu.setRoleId(role.getId());
			 List<Menu> myMenus = menuService.findMenuByRole(menu);
			 Map<Long, Menu> tempMenu = new HashMap<>();
			 if(myMenus.size()>0){
				 for (Menu menuView : myMenus) {
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
     * 根据菜单ID查询操作
    * @Title: findOperationsByMenuId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param organizationId
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    @API("根据菜单ID查询操作")
    @RequestMapping(value = "/v1/organizations/operations",method = RequestMethod.POST)
    public Object findOperationsByMenuId(@RequestBody Organization tdOrganization){
		ParamVerifyUtil.paramNotNull(tdOrganization);
		ParamVerifyUtil.paramNotNull(tdOrganization.getId(), "组织机构ID不能为空");
		ParamVerifyUtil.paramNotNull(tdOrganization.getMenuId(), "菜单ID不能为空");
		
		Organization Organization = organizationService.findById(tdOrganization.getId());
		String code = Organization.getCode();
		List<Operation> operations = new ArrayList<>();
		if(Organization.getCode().length()>2){
			code = Organization.getCode().substring(0, Organization.getCode().length()-2);
			List<Organization> orList = organizationService.selectByCondition(new Organization(code));
			if(orList.size()>0){
				//取管理员角色
				Role role = roleService.findRoleByOrganizationId(new Role(orList.get(0).getId()));
				//取root角色菜单
				Menu menu = new Menu();
				menu.setRoleId(role.getId());
				menu.setId(tdOrganization.getMenuId());
				operations = operationService.findOperationsByRoleIdAndMenuId(menu);
			}
		}else{
			Menu menu = new Menu();
			menu.setId(tdOrganization.getMenuId());
			operations = operationService.findMenuOperationsByMenuId(menu);
		}
		 //查询已拥有的操作
		 List<Organization> orList = organizationService.selectByCondition(new Organization(Organization.getCode()));
		 if(orList.size()>0){
			 //取管理员角色
			 Role role = roleService.findRoleByOrganizationId(new Role(orList.get(0).getId()));
			 //取root角色菜单
			 Menu menu = new Menu();
			 menu.setRoleId(role.getId());
			 menu.setId(tdOrganization.getMenuId());
			 List<Operation>  operationsList = operationService.findOperationsByRoleIdAndMenuId(menu);
			 
			 Map<Long, Operation> tempOperation = new HashMap<>();
			 if(operationsList.size()>0){
				 for (Operation operationView : operationsList) {
					 tempOperation.put(operationView.getId(), operationView);
				 }
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
    	return operations;
    }
    
    /**
     * 新增组织机构
    * @Title: createOrganization 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param object
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    @API(value = "添加组织机构")
    @RequestMapping(value = "/v1/organization/create",method = RequestMethod.POST)
    public Object createOrganization(@RequestBody Organization tdOrganization){
    	ParamVerifyUtil.paramNotNull(tdOrganization);
    	ParamVerifyUtil.paramNotNull(tdOrganization.getName(), "组织机构名称不能为空",10000);
    	if(!RegexUtils.isAvailableSizeCharacter(tdOrganization.getName(), 10)){
    		throw new RuntimeExceptionWarn("部门名称长度超出限制");
    	}
    	ParamVerifyUtil.paramNotNull(tdOrganization.getParentCode(), "组织机构父级code不能为空");
    	ParamVerifyUtil.paramNotNull(tdOrganization.getSystemId(), "系统ID不能为空");
    	ParamVerifyUtil.paramNotNull(tdOrganization.getParentId(), "组织机构父级id不能为空");
    	tdOrganization.setModifier(HttpServiceContext.getCurrentUserName());
    	tdOrganization.setModifierId(HttpServiceContext.getCurrentUserId());
        return  organizationService.createOrganization(tdOrganization);
    }
    
    /**
     * 为组织结构添加菜单操作
    * @Title: createOrganizationOperations 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param object
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    @API(value = "为组织结构添加菜单操作")
    @RequestMapping(value = "/v1/organization/add",method = RequestMethod.POST)
    public Object createOrganizationOperations(@RequestBody Organization tdOrganization){
    	ParamVerifyUtil.paramNotNull(tdOrganization);
    	ParamVerifyUtil.paramNotNull(tdOrganization.getId(), "组织机构ID不能为空");
    	ParamVerifyUtil.paramNotNull(tdOrganization.getMenuId(), "菜单ID不能为空");
    	ParamVerifyUtil.paramNotNull(tdOrganization.getOperationsId(), "操作ID不能为空");
    	ParamVerifyUtil.paramNotNull(tdOrganization.getSystemId(), "系统ID不能为空"); 
    	//取组织机构信息
    	Organization organization = organizationService.findById(tdOrganization.getId());
    	//取管理员角色
    	Role role = roleService.findRoleByOrganizationId(new Role(tdOrganization.getId()));
    	role.setMenuId(tdOrganization.getMenuId());
    	role.setOperationIds(tdOrganization.getOperationsId());
    	role.setSystemId(tdOrganization.getSystemId());
    	role.setCode(organization.getCode());
    	role.setModifier(HttpServiceContext.getCurrentUserName());
    	role.setModifierId(HttpServiceContext.getCurrentUserId());
    	roleService.insertMenuForRootrole(role);
        return role;
    }

    /**
     * 删除组织机构菜单选中操作
    * @Title: deleteOperations 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param object
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    @API(value = "删除组织机构菜单操作")
    @RequestMapping(value = "/v1/organization/delete_operations",method = RequestMethod.POST)
    public Object deleteOperations(@RequestBody Organization tdOrganization){
    	ParamVerifyUtil.paramNotNull(tdOrganization);
    	ParamVerifyUtil.paramNotNull(tdOrganization.getId(), "组织机构ID不能为空");
    	ParamVerifyUtil.paramNotNull(tdOrganization.getMenuId(), "菜单ID不能为空");
    	ParamVerifyUtil.paramNotNull(tdOrganization.getOperationsId(), "操作ID不能为空");
    	
    	//取管理员角色
    	Role role = roleService.findRoleByOrganizationId(new Role(tdOrganization.getId()));
    	role.setMenuId(tdOrganization.getMenuId());
    	role.setOperationId(tdOrganization.getOperationId());
    	roleService.deleteMenuForRole(role);
        return  role;
    }
    
    /**
     * 根据组织机构ID查询所拥有菜单
    * @Title: findMenuByOrganizationId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param organizationId
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    @API(value = "根据组织机构ID查询所拥有菜单")
    @RequestMapping(value = "/v1/organization/find_menus",method = RequestMethod.POST)
    public Object findMenuByOrganizationId(@RequestBody Organization tdOrganization){
    	ParamVerifyUtil.paramNotNull(tdOrganization);
    	ParamVerifyUtil.paramNotNull(tdOrganization.getId(), "组织机构ID不能为空");
    	
    	Organization Organization = organizationService.findById(tdOrganization.getId());
		List<Organization> orList = organizationService.selectByCondition(new Organization(Organization.getCode()));
		List<Menu> menus = new ArrayList<>();
		if(orList.size()>0){
			 //取管理员角色
			 Role role = roleService.findRoleByOrganizationId(new Role(orList.get(0).getId()));
			 //取root角色菜单
			 Menu menu = new Menu();
			 menu.setRoleId(role.getId());
			 menus = menuService.findMenuByRole(menu);
		}
    	return menus;
    }
    
    /**
     * 根据组织机构ID和菜单ID查询所拥有操作
    * @Title: findOperationsByOrganizationId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param organizationId
    * @param @param menuId
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    @API(value = "根据组织机构ID和菜单ID查询所拥有操作")
    @RequestMapping(value = "/v1/organization/find_operations",method = RequestMethod.POST)
    public Object findOperationsByOrganizationId(@RequestBody Organization tdOrganization){
    	ParamVerifyUtil.paramNotNull(tdOrganization);
    	ParamVerifyUtil.paramNotNull(tdOrganization.getId(), "组织机构ID不能为空");
    	ParamVerifyUtil.paramNotNull(tdOrganization.getMenuId(), "菜单ID不能为空");
    	Organization Organization = organizationService.findById(tdOrganization.getId());
 
		 List<Organization> orList = organizationService.selectByCondition(new Organization(Organization.getCode()));
		 List<Operation> operations = new ArrayList<>();
		 if(orList.size()>0){
			 //取管理员角色
			 Role role = roleService.findRoleByOrganizationId(new Role(orList.get(0).getId()));
			 //取root角色菜单
			 Menu menu = new Menu();
			 menu.setRoleId(role.getId());
			 menu.setId(tdOrganization.getMenuId());
			 operations = operationService.findOperationsByRoleIdAndMenuId(menu);
		 }
		 return operations;
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
    @API(value = "修改组织机构名称")
    @RequestMapping(value = "/v1/organization/update_name",method = RequestMethod.POST)
    public Object updateName(@RequestBody Organization tdOrganization){
    	ParamVerifyUtil.paramNotNull(tdOrganization);
    	ParamVerifyUtil.paramNotNull(tdOrganization.getId(), "组织机构ID不能为空");
    	ParamVerifyUtil.paramNotNull(tdOrganization.getName(), "组织机构名称不能为空",10000);
    	if(!RegexUtils.isAvailableSizeCharacter(tdOrganization.getName(), 10)){
    		throw new RuntimeExceptionWarn("部门名称长度超出限制");
    	}
    	tdOrganization.setModifier(HttpServiceContext.getCurrentUserName());
    	tdOrganization.setModifierId(HttpServiceContext.getCurrentUserId());
	    return organizationService.updateByPrimaryKeySelective(tdOrganization);
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
    @API(value = "取组织机构信息")
    @RequestMapping(value = "/v1/organization/update_organization",method = RequestMethod.POST)
    public Object updateOrganization(@RequestBody Organization tdOrganization){
    	ParamVerifyUtil.paramNotNull(tdOrganization);
    	ParamVerifyUtil.paramNotNull(tdOrganization.getId(), "组织机构ID不能为空");
	    return organizationService.findOrganizationInfo(tdOrganization);
    }
}
