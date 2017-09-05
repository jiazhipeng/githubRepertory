package com.hy.security.service.operation;

/**
 * Created by WangShuai on 2016/7/23.
 */
public interface MenuOperationService {

    /**
     * 为组织结构更新菜单操作
     * @param object
     * @return
     */
    public Object updateOrganizationMenuOperations(Object object);

    /**
     * 为角色更新菜单操作
     * @param object
     * @return
     */
    public Object updateRoleMenuOperations(Object object);

    /**
     * 根据角色ID查询所拥有菜单操作
     * 初步设想返回一颗树
      [
          {
               "menu_name":"车辆运营",
               "menu_id":1,
               "sub_menus":[
                   {

                   }
               ],
               "menu_operations":[
                   {
                       "menu_operation_id":1,
                       "operation_id":1,
                       "operation_name":"删除订单",
                   }
               ]
          }

      ]

     实现思路：
     1.查询该角色所拥有的所有List<MenuOperation>
     2.根据menuIds查询所有菜单信息，
     3.递归查询每层菜单信息
     * @param roleId
     * @return
     */
    public Object findMenuOperationsByRoleId(Long roleId);

    /**
     * 根据部门ID查询所有菜单操作
     * @param roleId
     * @return
     */
    public Object findMenuOperationsByOrganizationId(Long roleId);
}
