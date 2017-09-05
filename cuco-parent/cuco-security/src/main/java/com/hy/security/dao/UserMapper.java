package com.hy.security.dao;

import java.util.List;

public interface UserMapper<User> extends BaseDao<User> {
	
	List<User> selectByCondition(User user);
	
	/**
	 * 用户分页
	* @Title: selectByConditionForPage 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param user
	* @param @return    设定文件 
	* @return List<User>    返回类型 
	* @throws
	 */
	List<User> selectByConditionForPage(User user);
	/**
	 * 给用户添加角色
	* @Title: insertRoleForUser 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param user    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	void insertRoleForUser(User user);

	/**
	 * 批量删除用户角色
	* @Title: deleteBatchForUserRole 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param ids    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	void deleteBatchForUserRole(User user);
	
	/**
	 * 解除openId
	* @Title: updateOpenIdIsNull 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param user    设定文件 
	* @return void    返回类型 
	* @throws
	 */
    void updateOpenIdIsNull(User user);
    
    /**
     * 根据菜单ID和操作Id查询用户
    * @Title: selectUserByOperationIdAndMenuId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param user
    * @param @return    设定文件 
    * @return List<User>    返回类型 
    * @throws
     */
    List<User> selectUserByOperationIdAndMenuId(User user);
    
    /**
     * 根据角色查询用户
    * @Title: selectUserByRoleId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param user
    * @param @return    设定文件 
    * @return List<User>    返回类型 
    * @throws
     */
    List<User> selectUserByRoleId(User user);
}
