package com.hy.security.service.organization.impl;

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
import com.hy.constant.Constant;
import com.hy.enums.ServerStatus;
import com.hy.exception.RuntimeExceptionWarn;
import com.hy.model.PageResult;
import com.hy.security.dao.OrganizationMapper;
import com.hy.security.dao.UserMapper;
import com.hy.security.entity.Organization;
import com.hy.security.entity.Role;
import com.hy.security.entity.System;
import com.hy.security.entity.User;
import com.hy.security.service.organization.OrganizationService;
import com.hy.security.service.role.RoleService;
import com.hy.security.service.system.SystemService;
import com.hy.security.service.user.UserService;

@Service
public class OrganizationServiceImpl implements OrganizationService{

    @Autowired
    private OrganizationMapper<Organization>tdOrganizationMapper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private UserService userService;
    @Override
    public Organization insertSelective(Organization tdOrganization){
    		this.tdOrganizationMapper.insertSelective(tdOrganization);
           return (Organization) this.tdOrganizationMapper.selectByPrimaryKey(tdOrganization.getId());
        }

    @Override
    public Integer insertBatch(List<Organization> tdOrganization){
           return this.tdOrganizationMapper.insertBatch(tdOrganization) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tdOrganizationMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tdOrganizationMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Organization updateByPrimaryKeySelective(Organization tdOrganization) {
    	tdOrganization.setLasttimeModify(new Date());
           this.tdOrganizationMapper.updateByPrimaryKeySelective(tdOrganization);
           return (Organization) this.tdOrganizationMapper.selectByPrimaryKey(tdOrganization.getId());
    }

    @Override
    public Organization findById(Object id) {
           return (Organization) this.tdOrganizationMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Organization> selectByCondition(Organization tdOrganization) {
           return (List<Organization>) this.tdOrganizationMapper.selectByCondition(tdOrganization);
    }

    /**
     * 部门管理中创建子集部门
     */
	@Override
	public Organization createOrganization(Organization tdOrganization) {
//		Assert.notNull(tdOrganization, "添加组织机构，对象不允许为空");
//		Assert.notNull(tdOrganization.getParentCode(), "添加组织机构，对象不允许为空");
		if(null == tdOrganization || StringUtils.isBlank(tdOrganization.getParentCode()) || StringUtils.isBlank(tdOrganization.getName())
				|| StringUtils.isBlank(tdOrganization.getParentCode())|| null==tdOrganization.getSystemId() || null==tdOrganization.getParentId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
		//计算code
		tdOrganization.setCode(this.checkCode(tdOrganization));
		
		tdOrganization.setCreated(new Date());
		tdOrganization.setLasttimeModify(new Date());
		Role role = new Role();
		role.setName(tdOrganization.getName()+"-root");
		role.setCode(tdOrganization.getCode());
		role.setSystemId(tdOrganization.getSystemId());
		role.setModifierId(tdOrganization.getModifierId());
		role.setModifier(tdOrganization.getModifier());
		//创建组织机构
		tdOrganization = this.insertSelective(tdOrganization);
		role.setOrganizationId(tdOrganization.getId());
		//创建默认角色
		roleService.createRole(role);
		return tdOrganization;
	}

	/**
	 * 筛选code
	* @Title: checkCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param tdOrganization
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String checkCode(Organization tdOrganization){
		List<Organization> organizationsList = this.tdOrganizationMapper.selectOrganizationByCode(tdOrganization);
		if(organizationsList.size()>0){
			Map<Integer,Organization> orMap = new HashMap<>();
			for (Organization organization : organizationsList) {
				orMap.put(Integer.parseInt(organization.getCode().substring(tdOrganization.getParentCode().length())), organization);
			}
			for (int i = 1; i <100; i++) {
				 if(orMap.get(i)==null){
					 if(i<10){
						 return tdOrganization.getParentCode()+"0"+i;
					 }else{
						 return tdOrganization.getParentCode()+i;
					 }
				 }
			}
		}
		return tdOrganization.getParentCode()+"01";
	}
	
	/**
	 * 筛选一级部门code
	* @Title: checkCodeForOne 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String checkCodeForOne(){
		List<Organization> organizationsList = this.tdOrganizationMapper.selectOrganizationByOne();
		if(organizationsList.size()>0){
			Map<Integer,Organization> orMap = new HashMap<>();
			for (Organization organization : organizationsList) {
				orMap.put(Integer.parseInt(organization.getCode()), organization);
			}
			for (int i = 1; i <100; i++) {
				 if(orMap.get(i)==null){
					 if(i<10){
						 return "0"+i;
					 }else{
						 return i+"";
					 }
				 }
			}
		}
		return "01";
	}
	
	@Override
	public Organization updateOrganization(Organization tdOrganization) {
		tdOrganization.setLasttimeModify(new Date());
		return this.updateByPrimaryKeySelective(tdOrganization);
	}

	@Override
	public Organization deleteOrganization(Organization tdOrganization) {
		tdOrganization.setLasttimeModify(new Date());
		tdOrganization.setIsValue(1);
		return this.updateByPrimaryKeySelective(tdOrganization);
	}

	@Override
	public Organization findOrganizationById(Long organizationId) {
		// TODO Auto-generated method stub
		return this.tdOrganizationMapper.selectByPrimaryKey(organizationId);
	}

	/**
	 * 按用户id、系统ID权限为查询条件 查找组织机构 
	 */
	@Override
	public List<Organization> selectByUserIdAndSystemId(Organization organization) {
//		Assert.notNull(organization, "按用户id、系统ID为查询条件 查找组织机构，对象不允许为空");
//    	Assert.notNull(organization.getUserId(), "按用户id、系统ID为查询条件 查找组织机构，对象不允许为空，用户ID不允许为空");
    	if(null == organization || null==organization.getUserId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
    	return (List<Organization>) this.tdOrganizationMapper.selectByUserIdAndSystemId(organization);
	}

	/**
	 * 创建一级组织机构
	 */
	 public Organization createOrganizationForOne(Organization tdOrganization){
//		Assert.notNull(tdOrganization, "添加组织机构，对象不允许为空");
//		Assert.notNull(tdOrganization.getName(), "添加组织机构，组织名称不允许为空");
		if(null == tdOrganization || StringUtils.isBlank(tdOrganization.getName())|| 
				StringUtils.isBlank(tdOrganization.getModifier()) || null == tdOrganization.getModifierId()|| null == tdOrganization.getSystemId() ){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
		//计算code
		tdOrganization.setCode(this.checkCodeForOne());
		
		tdOrganization.setCreated(new Date());
		tdOrganization.setLasttimeModify(new Date());
		
		Role role = new Role();
		role.setName(tdOrganization.getName()+"-root");
		role.setCode(tdOrganization.getCode());
		role.setSystemId(tdOrganization.getSystemId());
		role.setModifierId(tdOrganization.getModifierId());
		role.setModifier(tdOrganization.getModifier());
		//创建组织机构
		tdOrganization = this.insertSelective(tdOrganization);
		role.setOrganizationId(tdOrganization.getId());
		//创建默认角色
		Role createRole = roleService.createRole(role);
		
		
		//创建admin与这个最高角色的关联关系
		User adminSearch = new User();
		adminSearch.setIsAdmin(Constant.INTEGER_YES);
		List<User> admins = userService.selectByCondition(adminSearch);
		for(User admin : admins){
			User user = new User(); 
			user.setId(admin.getId());
			List<Role> roles = new ArrayList<Role>();
			roles.add(createRole);
			user.setRoles(roles);
			user.setRoleIds(createRole.getId()+"");
			userService.insertUserRoleIds(user);
		}
		
		return tdOrganization;
	 }
	 
	 /**
	  * 分页查询
	  */
	 public PageResult<Organization> findOrganizationListByPage(Organization tdOrganization){
		Integer page = tdOrganization.getPage();
	    Integer pageSize = tdOrganization.getPageSize();
	     /*搜索条件*/
	    List<Organization> organizations = null;
	    /*总记录数*/
	    Integer totalSize= this.selectByUserIdAndSystemId(tdOrganization).size();
	    /*分页信息*/
	    PageHelper.startPage(page,pageSize);
	    organizations = this.selectByUserIdAndSystemId(tdOrganization);
        if(!CollectionUtils.isEmpty(organizations)){
        	for (Organization organization : organizations) {
				//查询系统名称 --组织机构
        		if(organization.getCode().length()<=2){
					System system = new System();
					system.setOrganizationId(organization.getId());
					List<System> systemList = systemService.findBySystem(system);
					if(systemList.size()>0){
						system = systemService.findBySystem(system).get(0);
						organization.setSystemName(system.getName());
					}
				}else{
					String name = "";
					List<Organization> organizationsList = this.selectByCondition(new Organization(organization.getCode().substring(0,2)));
					if(organizationsList.size()>0){
						Organization organizationView =organizationsList.get(0);
						System system = new System();
						system.setOrganizationId(organizationView.getId());
						List<System> systemList = systemService.findBySystem(system);
						if(systemList.size()>0){
							system = systemService.findBySystem(system).get(0);
							organization.setSystemName(system.getName());
						}
						
						name = getOrganizationParentNames(organization.getCode());
						organization.setName(name+organization.getName());
					}
							
				}
			}
        	
        }
        PageResult<Organization> pageResult = new PageResult<Organization>(page,pageSize,totalSize,organizations);
        return pageResult;
	 }
	 /**
	  * 查询组织机构（包含角色列表）
	 * @Title: selectOrganizationBySystemIdAndForOrganizationAndRoles 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param organization
	 * @param @return    设定文件 
	 * @return List<Organization>    返回类型 
	 * @throws
	  */
	 public List<Organization> selectOrganizationBySystemIdAndForOrganizationAndRoles(Organization organization){
		 if(null == organization || null==organization.getUserId()  || null==organization.getSystemId()){
				throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		 }
		 List<Organization> organizations =  this.selectByUserIdAndSystemId(organization);
		 if(organizations.size()>0){
			 for (Organization organizationView : organizations) {
				 List<Role> roles =  roleService.findRolesByOrganizationId(new Role(organizationView.getId(), organization.getSystemId()));
				 List<Role> checkRole = new ArrayList<>();
				 if(roles.size()>0){
					 for (Role role : roles) {
						 if (StringUtils.isBlank(role.getCode())) {
							 checkRole.add(role);
						 }
					 }
				 }
				 organizationView.setRoles(checkRole);
			}
		 }
		return organizations;
	 }
	 /**
	  * 根据ID查询组织机构详细信息
	  */
	 public Organization findOrganizationInfo(Organization organization){
		 if(null == organization || null==organization.getId()){
				throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		 }
		organization = this.findOrganizationById(organization.getId());
    	if( organization!=null && StringUtils.isNoneBlank(organization.getCode())){
    		String code = organization.getCode().substring(0, 2);
    		Organization organizationView = this.selectOrganizationByUserCode(new Organization(code));
			System system  = new System();
			system.setOrganizationId(organizationView.getId());
			List<System> systems = systemService.findBySystem(system);
			if(systems.size()>0){
				organization.setSystemId(systems.get(0).getId());
			}
    	}
    	List<Organization> organizations = new ArrayList<>();
    	for (int i = 0; i < organization.getCode().length()/2; i++) {
    		Organization organizationView = this.selectOrganizationByUserCode(new Organization(organization.getCode().substring(0,(i+1)*2)));
    		organizations.add(organizationView);
    	}
    	organization.setOrganizations(organizations);
    	return organization;
	 }
	 
	 /**
	  * 根据code查询组织机构信息
	  */
	 public Organization selectOrganizationByUserCode(Organization organization){
		 if(null == organization || StringUtils.isBlank(organization.getCode())){
				throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		 }
		 return this.tdOrganizationMapper.selectOrganizationByUserCode(organization);
	 }

	@Override
	public String getOrganizationParentNames(String organizationCode) {
		
		String name = "";
		for (int i = 1; i < organizationCode.length()/2 - 1; i++) {
			List<Organization> organizationsL = this.selectByCondition(new Organization(organizationCode.substring(0,i*2 + 2)));
			if(organizationsL.size()>0){
				name+= organizationsL.get(0).getName()+"-";
			}
		}
		return name;
	}
}
