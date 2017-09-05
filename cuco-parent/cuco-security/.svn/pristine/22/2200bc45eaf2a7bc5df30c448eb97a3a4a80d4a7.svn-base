package com.hy.security.dao;

import java.util.List;

public interface RoleMapper<Role> extends BaseDao<Role> {

	List<Role> selectByCondition(Role role);
	
	/**
	 * 分页查询
	* @Title: selectRolesByUserIdAndSystemIdForPage 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param role
	* @param @return    设定文件 
	* @return List<Role>    返回类型 
	* @throws
	 */
	List<Role> selectRolesByUserIdAndSystemIdForPage(Role role);
	
	/**
	 * 根据用户ID查询所属角色
	* @Title: selectRolesByUserId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param role
	* @param @return    设定文件 
	* @return List<Role>    返回类型 
	* @throws
	 */
	List<Role> selectRolesByUserId(Role role);
	
	
	/**
	 * 根据用户ID、组织机构ID查询角色
	* @Title: selectRolesByUserIdAndOrganizationId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param role
	* @param @return    设定文件 
	* @return List<Role>    返回类型 
	* @throws
	 */
	List<Role> selectRolesByUserIdAndOrganizationId(Role role);
	
	/**
	 * 按用户id、系统ID为查询条件 查找组织机构(包括子菜单)--找到所有角色
	* @Title: selectRolesByUserIdAndSystemId
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param role
	* @param @return    设定文件 
	* @return List<Role>    返回类型 
	* @throws
	 */
	List<Role> selectRolesByUserIdAndSystemId(Role role);
	
	/**
	 * 给角色添加菜单
	* @Title: insertMenuForRole 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param role    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	void insertMenuForRole(Role role);
	
	/**
	 * 删除角色菜单
	* @Title: deleteMenuForRole 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param role    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	void deleteMenuForRole(Role role);
	
	/**
	 * 删除角色菜单所有操作
	* @Title: deleteOperationForRole 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param role    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	void deleteOperationForRole(Role role);
	
	/**
	 * 按code查询所有角色
	* @Title: selectRolesByCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param role
	* @param @return    设定文件 
	* @return List<Role>    返回类型 
	* @throws
	 */
	List<Role> selectRolesByCode(Role role);
	
	/**
	 * 查看子级部门下包含指定菜单和指定操作的个数
	 * @param role封装3个参数：父级部门Id，菜单Id，操作Id
	 * @return
	 */
	Integer countChildOrgOpersByMenuOperId(Role role);
}
