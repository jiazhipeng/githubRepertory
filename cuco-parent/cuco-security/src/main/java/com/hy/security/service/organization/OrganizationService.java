package com.hy.security.service.organization;

import com.hy.model.PageResult;
import com.hy.security.entity.Organization;
import com.hy.security.entity.User;

import java.util.List;

/**
 * Created by WangShuai on 2016/7/23.
 */
public interface OrganizationService {
    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tdOrganization
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年07月28日 17:18:24
    *@修改时间 2016年07月28日 17:18:24
    *@版本 V1.0
    *@异常
    **/
    public Organization insertSelective(Organization tdOrganization) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tdOrganization
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年07月28日 17:18:24
    *@修改时间 2016年07月28日 17:18:24
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<Organization> tdOrganization) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tdOrganization
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
    *@param tdOrganization
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
    *@param tdOrganization
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年07月28日 17:18:24
    *@修改时间 2016年07月28日 17:18:24
    *@版本 V1.0
    *@异常
    **/
    public Organization updateByPrimaryKeySelective(Organization tdOrganization);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tdOrganization
    *@返回值 对象 返回类型
    *@作者：q.p.x
    *@创建时间 2016年07月28日 17:18:24
    *@修改时间 2016年07月28日 17:18:24
    *@版本 V1.0
    *@异常
    **/
    public Organization findById(Object id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tdOrganization
    *@返回值 List<Organization> 返回类型
    *@作者：q.p.x
    *@创建时间 2016年07月28日 17:18:24
    *@修改时间 2016年07月28日 17:18:24
    *@版本 V1.0
    *@异常
    **/
    public List<Organization> selectByCondition(Organization tdOrganization);
	
    
    /**
     * 按用户id、系统ID为查询条件 查找组织机构 
    * @Title: selectByUserIdAndSystemId
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param organization
    * @param @return    设定文件 
    * @return List<Organization>    返回类型 
    * @throws
     */
    List<Organization> selectByUserIdAndSystemId(Organization organization);

    /**
     * 创建组织机构
     * @param object
     * @return
     */
    public Organization createOrganization(Organization tdOrganization);
    
    /**
     * 创建一级组织机构
     * @param object
     * @return
     */
    public Organization createOrganizationForOne(Organization tdOrganization);

    /**
     * 更新组织机构信息
     * TODO
     * 为了简化操作，不允许更新父级部门，可通过删除后重新添加的方式去处理
     * @param object
     * @return
     */
    public Organization updateOrganization(Organization tdOrganization);

    /**
     * 删除组织机构
     * @param object
     * @return
     */
    public Organization deleteOrganization(Organization tdOrganization);

    /**
     * 根据组织机构ID查询基本信息
     * @param organizationId
     * @return
     */
    public Organization findOrganizationById(Long organizationId);
    
    /**
     * 分页查询
    * @Title: findOrganizationListByPage 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param tdOrganization
    * @param @return    设定文件 
    * @return PageResult<Organization>    返回类型 
    * @throws
     */
    public PageResult<Organization> findOrganizationListByPage(Organization tdOrganization);
    
    /**
     * 查询组织机构（包含角色列表）
    * @Title: selectOrganizationBySystemIdAndForOrganizationAndRoles 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param organization
    * @param @return    设定文件 
    * @return List<Organization>    返回类型 
    * @throws
     */
    List<Organization> selectOrganizationBySystemIdAndForOrganizationAndRoles(Organization organization);
    
    /**
     * 根据ID查询组织机构详细信息(包含父级组织机构信息)
    * @Title: findOrganizationInfo 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param organization
    * @param @return    设定文件 
    * @return Organization    返回类型 
    * @throws
     */
    public Organization findOrganizationInfo(Organization organization);
    
    /**
     * 根据code查询组织机构信息
    * @Title: selectOrganizationByUserCode 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param organization
    * @param @return    设定文件 
    * @return Organization    返回类型 
    * @throws
     */
    public Organization selectOrganizationByUserCode(Organization organization);
    
    
    public String getOrganizationParentNames(String organizationCode);
}
