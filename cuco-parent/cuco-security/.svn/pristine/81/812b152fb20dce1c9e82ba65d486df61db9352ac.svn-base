package com.hy.security.dao;

import java.util.List;

public interface OrganizationMapper<Organization> extends BaseDao<Organization>{

	List<Organization> selectByCondition(Organization organization);
	
	/**
	 * 按用户id、系统ID权限为查询条件 查找组织机构(包括子机构)
	* @Title: selectByUserIdAndSystemIdAndRoleNmae 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param organization
	* @param @return    设定文件 
	* @return List<Organization>    返回类型 
	* @throws
	 */
	List<Organization> selectByUserIdAndSystemId(Organization organization);
	
	/**
	 * 按用户id、系统ID权限为查询条件 查询组织机构
	* @Title: selectByRootOrganization 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param organization
	* @param @return    设定文件 
	* @return List<Organization>    返回类型 
	* @throws
	 */
	List<Organization> selectByOrganization(Organization organization);
	
	/**
	 * 根据code 查询组织机构
	* @Title: selectOrganizationByCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param organization
	* @param @return    设定文件 
	* @return List<Organization>    返回类型 
	* @throws
	 */
	List<Organization> selectOrganizationByCode(Organization organization);
	
	/**
	 * 查询所有一级部门
	* @Title: selectOrganizationByOne 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return List<Organization>    返回类型 
	* @throws
	 */
	List<Organization> selectOrganizationByOne();
	
	/**
	 * 根据code查询组织机构
	* @Title: selectOrganizationByUserCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param organization
	* @param @return    设定文件 
	* @return Organization    返回类型 
	* @throws
	 */
	Organization selectOrganizationByUserCode(Organization organization);
	
}
