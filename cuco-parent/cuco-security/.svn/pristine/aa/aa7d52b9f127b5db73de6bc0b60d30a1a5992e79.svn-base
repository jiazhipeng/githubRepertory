package com.hy.security.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hy.security.entity.Menu;
import com.hy.security.entity.MenuOperation;
import com.hy.security.entity.Operation;


public interface OperationMapper<T> extends BaseDao<T> {
	
    /**
     * 插入操作对应的api
     * @param operationId
     * @param apiId
     * @return
     */
    public Integer insertOperationApi(@Param("operation_id")Long operationId,@Param("api_id")Long apiId);

    /**
     * 根据操作ID删除对应的Api
     * @param operationId    
     * @return
     */
    public Integer deleteApisByOperationId(@Param("operation_id")Long operationId);
    
    /**
     * 查询名称相同的记录条数
     * @param operation
     * @return
     */
    public Integer findRepeatRecordCount(Operation operation);
	/**
	 * 根据节点查询所有已关联操作
	 * @param menu
	 * @return
	 */
	public List<Operation> findMenuOperationsByMenuId(Menu menu);
	
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
     * 查找角色所拥有操作
     * @param roleId
     * @return
     */
    public List<Operation> findOperationsByRoleId(@Param("role_id")Long roleId);
    
    /**
     * 根据角色ID和菜单ID查看对应的操作
     * @param roleId
     * @param menuId
     * @return
     */
    public List<Operation> findOperationsByRoleIdAndMenuId(@Param("role_id")Long roleId,@Param("menu_id")Long menuId);
    /**
     * 根据菜单查询未关联的操作
     * @param menuOperation
     * @return
     */
	public List<Operation> findNotRelatedsOperations(MenuOperation menuOperation);
	
	/**
	 * 根据系统Id、操作名称、api名称查询操作
	 * @param name
	 * @param apiName
	 * @return
	 */
	public List<Operation> findOperationsByOperation(Operation operation);
}
