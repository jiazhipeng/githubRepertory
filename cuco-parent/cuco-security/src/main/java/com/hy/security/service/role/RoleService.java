package com.hy.security.service.role;

import java.util.List;

import com.hy.model.PageResult;
import com.hy.security.entity.Role;

/**
 * Created by WangShuai on 2016/7/22.
 */
public interface RoleService {

	 /**
	    *@方法名: insertSelective
	    *@方法描述: 根据对象插入单条记录
	    *@param tdRole
	    *@返回值 Integer 返回类型
	    *@作者：q.p.x
	    *@创建时间 2016年07月28日 17:18:24
	    *@修改时间 2016年07月28日 17:18:24
	    *@版本 V1.0
	    *@异常
	    **/
	    public Role insertSelective(Role tdRole) throws Exception;

	    /**
	    *@方法名: insertBatch
	    *@方法描述: 根据对象批量插入记录
	    *@param tdRole
	    *@返回值 Integer 返回类型
	    *@作者：q.p.x
	    *@创建时间 2016年07月28日 17:18:24
	    *@修改时间 2016年07月28日 17:18:24
	    *@版本 V1.0
	    *@异常
	    **/
	    public Integer insertBatch(List<Role> tdRole) throws Exception;

	    /**
	    *@方法名: deleteByPrimaryKey
	    *@方法描述: 根据主键删除单条记录
	    *@param tdRole
	    *@返回值 Integer 返回类型
	    *@作者：q.p.x
	    *@创建时间 2016年07月28日 17:18:24
	    *@修改时间 2016年07月28日 17:18:24
	    *@版本 V1.0
	    *@异常
	    **/
	    public Integer deleteByPrimaryKey(Object id);

	    /**
	    *@方法名: deleteBatchByPrimaryKey
	    *@方法描述: 根据主键批量删除
	    *@param tdRole
	    *@返回值 Integer 返回类型
	    *@作者：q.p.x
	    *@创建时间 2016年07月28日 17:18:24
	    *@修改时间 2016年07月28日 17:18:24
	    *@版本 V1.0
	    *@异常
	    **/
	    public Integer deleteBatchByPrimaryKey(List<Object> ids);

	    /**
	    *@方法名: updateByPrimaryKeySelective
	    *@方法描述: 根据主键选择更新单个对象
	    *@param tdRole
	    *@返回值 Integer 返回类型
	    *@作者：q.p.x
	    *@创建时间 2016年07月28日 17:18:24
	    *@修改时间 2016年07月28日 17:18:24
	    *@版本 V1.0
	    *@异常
	    **/
	    public Integer updateByPrimaryKeySelective(Role tdRole);

	    /**
	    *@方法名: findById
	    *@方法描述: 根据id 查询对象
	    *@param tdRole
	    *@返回值 对象 返回类型
	    *@作者：q.p.x
	    *@创建时间 2016年07月28日 17:18:24
	    *@修改时间 2016年07月28日 17:18:24
	    *@版本 V1.0
	    *@异常
	    **/
	    public Role findById(Object id);

	    /**
	    *@方法名: selectByCondition
	    *@方法描述: 分条件查询对象
	    *@param tdRole
	    *@返回值 List<Role> 返回类型
	    *@作者：q.p.x
	    *@创建时间 2016年07月28日 17:18:24
	    *@修改时间 2016年07月28日 17:18:24
	    *@版本 V1.0
	    *@异常
	    **/
	    public List<Role> selectByCondition(Role tdRole);

	    /**
	     * 创建角色
	    * @Title: createRole 
	    * @Description: TODO(这里用一句话描述这个方法的作用) 
	    * @param @param role
	    * @param @return    设定文件 
	    * @return Role    返回类型 
	    * @throws
	     */
	    public Role createRole(Role role);
	    
	    
	    /**
	     * 更新角色状态
	     * @param role
	     * @return
	     */
	    public Role delete(Role role);

	    /**
	     * 修改角色
	    * @Title: updateRole 
	    * @Description: TODO(这里用一句话描述这个方法的作用) 
	    * @param @param role
	    * @param @return    设定文件 
	    * @return Role    返回类型 
	    * @throws
	     */
	    public Role updateRole(Role role);
	    
	    /**
	     * 根据用户ID查询所有角色
	     * @param systemId
	     * @return
	     */
	    public List<Role> findRolesByUserId(Role tdRole);
	
	    /**
	     * 根据部门ID、用户ID查询角色
	     * @param organizationId
	     * @return
	     */
	    public List<Role> findRolesByOrganizationIdAndUserId(Role tdRole);
	    
	    /**
	     * 根据部门ID查询所有角色
	     * @param systemId
	     * @return
	     */
	    public List<Role> findRolesByOrganizationId(Role tdRole);
	    
	    /**
	     * 按用户id、系统ID为查询条件 查找组织机构(包括子菜单)--找到所有角色
	    * @Title: selectRolesByUserIdAndSystemIdAndRoot 
	    * @Description: TODO(这里用一句话描述这个方法的作用) 
	    * @param @param tdRole
	    * @param @return    设定文件 
	    * @return List<Role>    返回类型 
	    * @throws
	     */
	    public List<Role> selectRolesByUserIdAndSystemId(Role tdRole);
	    
	    /**
	     * 给角色添加菜单
	    * @Title: insertMenuForRole 
	    * @Description: TODO(这里用一句话描述这个方法的作用) 
	    * @param @param role    设定文件 
	    * @return Role    返回类型 
	    * @throws
	     */
	    public Role insertMenuForRole(Role role);
	    
	    /**
	     * 删除角色菜单
	    * @Title: deleteMenuForRole 
	    * @Description: TODO(这里用一句话描述这个方法的作用) 
	    * @param @param role    设定文件 
	    * @return Role    返回类型 
	    * @throws
	     */
	    public Role deleteMenuForRole(Role role);
	    
	    /**
	     * 根据组织机构Id取机构root用户
	    * @Title: findRoleByOrganizationId 
	    * @Description: TODO(这里用一句话描述这个方法的作用) 
	    * @param @param role
	    * @param @return    设定文件 
	    * @return Role    返回类型 
	    * @throws
	     */
	    public Role findRoleByOrganizationId(Role Role);
	    
	    /**
	     * 分页查询
	    * @Title: findOrganizationListByPage 
	    * @Description: TODO(这里用一句话描述这个方法的作用) 
	    * @param @param Role
	    * @param @return    设定文件 
	    * @return PageResult<Role>    返回类型 
	    * @throws
	     */
	    public PageResult<Role> findOrganizationListByPage(Role role);
	    
	    
	    /**
	     * 组织机构给root角色重置权限信息
	    * @Title: insertMenuForRootrole 
	    * @Description: TODO(这里用一句话描述这个方法的作用) 
	    * @param @param role
	    * @param @return    设定文件 
	    * @return Role    返回类型 
	    * @throws
	     */
	    public Role insertMenuForRootrole(Role role);

	    /**
	     * 查询角色信息携带上级组织机构信息
	    * @Title: selectRoleInfoAddOrganization 
	    * @Description: TODO(这里用一句话描述这个方法的作用) 
	    * @param @param role
	    * @param @return    设定文件 
	    * @return Role    返回类型 
	    * @throws
	     */
	    public Role selectRoleInfoAddOrganization(Role role);
}
