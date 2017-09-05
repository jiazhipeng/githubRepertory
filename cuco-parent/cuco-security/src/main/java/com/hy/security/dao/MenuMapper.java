package com.hy.security.dao;

import java.util.List;

import com.hy.security.entity.Menu;
import com.hy.security.entity.MenuOperation;
import com.hy.security.entity.User;

public interface MenuMapper<Menu> extends BaseDao<Menu> {


	List<Menu> selectMenuBySystemId(Menu menu);

	void deleteMenuOperationByMenu(Menu menu);

	List<Menu> findMenuByRole(Menu menu);

	MenuOperation findMenuOperationId(MenuOperation menuOperation);

	void deleteMenuOperation(MenuOperation menuOperation);

	List<Menu> findMenuBySystemIdAndUserId(User user);

	void deleteRoleMenuOperation(MenuOperation menuOperation);

	
}
