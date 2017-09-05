package com.hy.security.service.operation;

import java.util.List;

import com.hy.model.PageResult;
import com.hy.security.entity.Api;
import com.hy.security.entity.Menu;
import com.hy.security.entity.MenuOperation;
import com.hy.security.entity.Operation;

/**
 * Created by WangShuai on 2016/7/22.
 */
public interface OperationService {

    public Boolean exists(Long id);

    public Operation create(Operation operation);

    public Operation update(Operation operation);

    /**
     * 查找系统所拥有操作
     * @param systemId
     * @return
     */
    public List<Operation> findOperationsBySystemId(Long systemId);

    /**
     * 根据菜单ID查询对应的操作
     * @param menuId
     * @return
     */
    public List<Operation> findMenuOperationsByMenuId(Menu menu);

    /**
     * 查找角色ID所拥有操作
     * @param roleId
     * @return
     */
    public List<Operation> findOperationsByRoleId(Long roleId);

    /**
     * 查找组织所拥有操作
     * @param organizationId
     * @return
     */
    public List<Operation> findOperationsByOrganizationId(Long organizationId);

    /**
     * 查找用户所拥有操作
     * @param roleId
     * @return
     */
    public List<Operation> findOperationsByUserId(Long roleId);

    /**
     * 为角色添加操作
     * @param object
     * @return
     */
    public Object updateRoleOperations(Object object);
    
    /**
     * 查询操作
     * @param operation
     * @return
     */
    public List<Operation> findOperationList(Operation operation);
    
    /**
     * 根据操作ID获取操作
     * @param operation
     * @return
     */
    public Operation findOperationById(Long operationId);
    
    /**
     * 根据Api获取对应的操作
     * @param api
     * @return
     */
    public List<Operation> findOperationsByApi(Api api);
    
    /**
     * 操作名称是否重复
     * @param operation
     * @return
     */
    public Boolean exists(Operation operation);

	/**
	 * 根据菜单删除已关联的操作
	 * @param menu
	 * @return
	 */
	public void deleteMenuOperations(MenuOperation menuOperation);
	
	/**
	 * 批量插入MenuOperation
	 * @param List<MenuOperation>
	 * @return
	 */
	public void insertBatchMenuOperation(List<MenuOperation> list);
	
	/**
     * 根据角色ID和菜单ID查看对应的操作
     * @param roleId
     * @param menuId
     * @return
     */
    public List<Operation> findOperationsByRoleIdAndMenuId(Menu menu);
	/**
	 * 根据菜单查询未关联的操作
	 * @param menuOperation
	 * @return
	 */
	public List<Operation> findNotRelatedsOperations(MenuOperation menuOperation);
	
	/**
	 * 分页查询操作
	 * @param operation
	 * @return
	 */
	public PageResult<Operation> findOperationsByPage(Operation operation);
}
