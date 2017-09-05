package com.hy.security.service.user;


import java.util.List;

import com.hy.model.PageResult;
import com.hy.security.entity.User;

/**
 * Created by WangShuai on 2016/7/21.
 */
public interface UserService {

    /**
    *@方法名: insertSelective
    *@方法描述: 根据对象插入单条记录
    *@param tdUser
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年07月28日 17:18:24
    *@修改时间 2016年07月28日 17:18:24
    *@版本 V1.0
    *@异常
    **/
    public User insertSelective(User tdUser) throws Exception;

    /**
    *@方法名: insertBatch
    *@方法描述: 根据对象批量插入记录
    *@param tdUser
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年07月28日 17:18:24
    *@修改时间 2016年07月28日 17:18:24
    *@版本 V1.0
    *@异常
    **/
    public Integer insertBatch(List<User> tdUser) throws Exception;

    /**
    *@方法名: deleteByPrimaryKey
    *@方法描述: 根据主键删除单条记录
    *@param tdUser
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年07月28日 17:18:24
    *@修改时间 2016年07月28日 17:18:24
    *@版本 V1.0
    *@异常
    **/
    public Integer deleteByPrimaryKey(Long id);

    /**
    *@方法名: deleteBatchByPrimaryKey
    *@方法描述: 根据主键批量删除
    *@param tdUser
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
    *@param tdUser
    *@返回值 Integer 返回类型
    *@作者：q.p.x
    *@创建时间 2016年07月28日 17:18:24
    *@修改时间 2016年07月28日 17:18:24
    *@版本 V1.0
    *@异常
    **/
    public User updateByPrimaryKeySelective(User tdUser);

    /**
    *@方法名: findById
    *@方法描述: 根据id 查询对象
    *@param tdUser
    *@返回值 对象 返回类型
    *@作者：q.p.x
    *@创建时间 2016年07月28日 17:18:24
    *@修改时间 2016年07月28日 17:18:24
    *@版本 V1.0
    *@异常
    **/
    public User findById(Long id);

    /**
    *@方法名: selectByCondition
    *@方法描述: 分条件查询对象
    *@param tdUser
    *@返回值 List<User> 返回类型
    *@作者：q.p.x
    *@创建时间 2016年07月28日 17:18:24
    *@修改时间 2016年07月28日 17:18:24
    *@版本 V1.0
    *@异常
    **/
    public List<User> selectByCondition(User tdUser);

    /**
     * 查询用户
    * @Title: findByUser 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param user
    * @param @return    设定文件 
    * @return User    返回类型 
    * @throws
     */
    public User findByUser(User user);
    
    /**
     * 根据用户ID查询
    * @Title: findByUser 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param id
    * @param @return    设定文件 
    * @return User    返回类型 
    * @throws
     */
    public User findByUserId(Long id);

    /**
     * 创建用户
    * @Title: createUser 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param user
    * @param @return    设定文件 
    * @return User    返回类型 
    * @throws
     */
    public User createUser(User user);
    
    /**
     * 修改用户
    * @Title: updateUser 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param user
    * @param @return    设定文件 
    * @return User    返回类型 
    * @throws
     */
    public User updateUser(User user);
    
    /**
     * 绑定用户OpenID
    * @Title: updateUser 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param user
    * @param @return    设定文件 
    * @return User    返回类型 
    * @throws
     */
    public User updateUserOpenId(User user);
    
    /**
     * 冻结用户
     * @param user
     * @return
     */
    public User freezeUser(User user);

    /**
     * 解冻
     * @param user
     * @return
     */
    public User unfreezeUser(User user);

    /**
     * 分页查询用户列表
     * @return
     */
    public PageResult<User> findUserListByPage(User user);
    
    
    /**
     * 查询用户权限
    * @Title: findApiByUserIdAndSystemId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param user
    * @param @return    设定文件 
    * @return List<Api>    返回类型 
    * @throws
     */
    public User findApiByUserIdAndSystemId(User user);
    
    /**
     * 根据菜单ID和操作Id查询用户
    * @Title: selectUserByOperationIdAndMenuId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param user
    * @param @return    设定文件 
    * @return List<User>    返回类型 
    * @throws
     */
    public List<User> selectUserByOperationIdAndMenuId(User user);
    
    /**
     * 根据角色查询用户
    * @Title: selectUserByRoleId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param user
    * @param @return    设定文件 
    * @return List<User>    返回类型 
    * @throws
     */
    public List<User> selectUserByRoleId(User user);
    
    public void insertUserRoleIds(User user);

    /**
    * @Title:
    * @Description:根据角色唯一标识（uniqueCode）查询所有用户
    * @author：WangShuai
    * @time：2017年03月01日 下午12:42:45
    * @param:
    * @return:
    * @throws:
    */
    public List<User> getUsersByRoleUniqueCode(String uniqueCode);
}
