package com.hy.security.service.menu;

import java.util.List;

import com.hy.security.entity.Menu;
import com.hy.security.entity.MenuOperation;
import com.hy.security.entity.Operation;
import com.hy.security.entity.User;

/**
 * Created by WangShuai on 2016/7/22.
 */
public interface MenuService {

    public Boolean exists(Long id);

    public Menu create(Menu menu);

    public Menu update(Menu menu);

    public void delete(Menu menu);

    /**
     * 根据用户ID查询所属菜单
     * @param userId
     * @return
     */
    public Object findMenusByUserId(Long userId);

    /**
     * 根据系统ID查询所属菜单
     * @param systemId
     * @return
     */
    public List<Menu> findMenusBySystemId(Long systemId);

    /**
     * 更新菜单拥有的操作
     * @param object
     * @return
     */
    public List<Operation> updateMenuOperations(MenuOperation menuOperation);
	/**
	 * 查询菜单是否重复
	 * @param menu
	 * @return true 已存在  false 不存在
	 */
	public boolean findNameIsExistsBySystemIdAndParentIdAndName(Menu menu);
	
	/**
	 * 根据角色id查询菜单
	 * @param menu
	 * @return
	 */
	public List<Menu> findMenuByRole(Menu menu);
	/**
	 * 查询菜单与操作关系表id
	 * @param menuOperation 
	 * @return
	 */
	public MenuOperation findMenuOperationId(MenuOperation menuOperation);
	/**
	 * 根据菜单和操作删除关系表
	 * @param menuOperation
	 */
	public void deleteMenuOperation(MenuOperation menuOperation);
	/**
	 * 根据系统id和用户id
	 * @param user
	 * @return
	 */
	public List<Menu> findMenuBySystemIdAndUserId(User user);
	
	
}
