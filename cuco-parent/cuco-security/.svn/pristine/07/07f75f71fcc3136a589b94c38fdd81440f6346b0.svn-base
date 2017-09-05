package com.hy.security.service.menu.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.enums.ServerStatus;
import com.hy.exception.RuntimeExceptionWarn;
import com.hy.security.common.HttpServiceContext;
import com.hy.security.dao.MenuMapper;
import com.hy.security.entity.Menu;
import com.hy.security.entity.MenuOperation;
import com.hy.security.entity.Operation;
import com.hy.security.entity.PermissionExchange;
import com.hy.security.entity.User;
import com.hy.security.service.menu.MenuService;
import com.hy.security.service.operation.OperationService;
import com.hy.security.service.permissionExchange.PermissionExchangeService;
import com.hy.security.service.user.UserService;

/**
 * Created by WangShuai on 2016/7/25.
 */
@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	MenuMapper<Menu> menuMapper;
	
	@Autowired
	OperationService operationService;
	@Autowired
	PermissionExchangeService exchangeService;
	@Autowired
	UserService userService;
	
    @Override
    public Boolean exists(Long id) {
        return null;
    }

    @Override
    public Menu create(Menu menu) {
//    	Assert.notNull(menu.getSystemId(), "新增菜单>>>系统ID为空");
//		Assert.notNull(menu.getParentId(), "新增菜单>>>父级ID为空");
//		Assert.notNull(menu.getName(), "新增菜单>>>菜单名称为空");
//		Assert.notNull(menu.getLink(), "新增菜单>>>菜单链接为空");
		if(null==menu||StringUtils.isBlank(menu.getName())||StringUtils.isBlank(menu.getLink())||null==menu.getSystemId()||null==menu.getParentId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
		menu.setCreated(new Date());
		menu.setLasttimeModify(menu.getCreated());
		menu.setModifier(HttpServiceContext.getCurrentUserName());
		menu.setModifierId(HttpServiceContext.getCurrentUserId());
		menuMapper.insertSelective(menu);
		menu = menuMapper.selectByPrimaryKey(menu);
        return menu;
    }

    @Override
    public Menu update(Menu menu) {
//		Assert.notNull(menu.getId(), "修改菜单>>>菜单ID为空");
//		Assert.notNull(menu.getName(), "修改菜单>>>菜单名称为空");
//		Assert.notNull(menu.getLink(), "修改菜单>>>菜单链接为空");
		if(null==menu||StringUtils.isBlank(menu.getName())||StringUtils.isBlank(menu.getLink())||null==menu.getId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
    	menuMapper.updateByPrimaryKeySelective(menu);
    	menu = menuMapper.selectByPrimaryKey(menu);
        return menu;
    }

    @Override
    public void delete(Menu menu) {
//		Assert.notNull(menu.getId(), "删除菜单>>>菜单ID为空");
		if(null==menu||null==menu.getId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
		
    	menuMapper.deleteByPrimaryKey(menu);
    	menuMapper.deleteMenuOperationByMenu(menu);
    	MenuOperation menuOperation = new MenuOperation();
    	menuOperation.setMenuId(menu.getId());
		menuMapper.deleteRoleMenuOperation(menuOperation);
    }

    @Override
    public Object findMenusByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Menu> findMenusBySystemId(Long systemId) {
//    	Assert.notNull(systemId, "根据系统id 查询菜单列表>>>systemId为空");
    	if(null==systemId){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
    	Menu menu = new Menu();
    	menu.setSystemId(systemId);
    	menu.setIsValue(0);
    	List<Menu> menuList  =menuMapper.selectMenuBySystemId(menu);
        return menuList;
    }

    @Override
    public List<Operation> updateMenuOperations(MenuOperation menuOperation) {
//    	Assert.notNull(menuOperation.getSystemId(), "添加操作>>>系统ID为空");
//		Assert.notNull(menuOperation.getMenuId(), "添加操作>>>菜单ID为空");
		if(null==menuOperation||null==menuOperation.getSystemId()||null==menuOperation.getMenuId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
		
		Menu menu = new Menu();
    	menu.setSystemId(menuOperation.getSystemId());
    	menu.setId(menuOperation.getMenuId());
    	//原来已关联的操作
		List<Operation> relatedsOperationList = operationService.findMenuOperationsByMenuId(menu);
		
		//先删除菜单操作关系表数据 再插入
    	operationService.deleteMenuOperations(menuOperation);
    	
    	if(StringUtils.isNoneBlank(menuOperation.getOperationIds())){
    		String[] ids = menuOperation.getOperationIds().split(",");
    		List<MenuOperation> list = new ArrayList<>();
    		for (String id : ids) {
    			MenuOperation operation = new MenuOperation();
    			operation.setMenuId(menuOperation.getMenuId());
    			operation.setOperationId(Long.valueOf(id));
    			operation.setSystemId(menuOperation.getSystemId());
    			list.add(operation);
    		}
    		for (Operation operation : relatedsOperationList) {
    			MenuOperation menuOperationR = new MenuOperation();
    			menuOperationR.setMenuId(menuOperation.getMenuId());
    			menuOperationR.setOperationId(operation.getId());
    			menuOperationR.setSystemId(menuOperation.getSystemId());
    			list.add(menuOperationR);
    		}
    	
    		operationService.insertBatchMenuOperation(list);
    	}
    	
		List<Operation> operationList = operationService.findMenuOperationsByMenuId(menu);
		
        return operationList;
    }

	@Override
	public boolean findNameIsExistsBySystemIdAndParentIdAndName(Menu menu) {
//		Assert.notNull(menu.getSystemId(), "新增菜单>>>系统ID为空");
//		Assert.notNull(menu.getParentId(), "新增菜单>>>父级ID为空");
//		Assert.notNull(menu.getName(), "新增菜单>>>菜单名称为空");
		if(null==menu||null==menu.getSystemId()||null==menu.getParentId()||StringUtils.isBlank(menu.getName())){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
		Menu menu_new = new Menu();
		menu_new.setName(menu.getName());
		menu_new.setSystemId(menu.getSystemId());
		menu_new.setParentId(menu.getParentId());
		menu_new.setIsValue(0);//有效
		List<Menu> list = menuMapper.selectByCondition(menu_new);
		if(list!=null&&list.size()>0){
			return true;
		}
		return false;
	}



	@Override
	public List<Menu> findMenuByRole(Menu menu) {
//		Assert.notNull(menu.getRoleId(), "根据角色id查询菜单>>>角色ID为空");
		if(null==menu||null==menu.getRoleId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
		return menuMapper.findMenuByRole(menu);
	}

	@Override
	public MenuOperation findMenuOperationId(MenuOperation menuOperation) {
//		Assert.notNull(menuOperation.getMenuId(), "查询菜单与操作关系表id>>>菜单ID为空");
//		Assert.notNull(menuOperation.getSystemId(), "查询菜单与操作关系表id>>>系统ID为空");
//		Assert.notNull(menuOperation.getOperationId(), "查询菜单与操作关系表id>>>操作ID为空");
		if(null==menuOperation||null==menuOperation.getMenuId()||null==menuOperation.getSystemId()||null==menuOperation.getOperationId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
		return menuMapper.findMenuOperationId(menuOperation);
	}

	@Override
	public void deleteMenuOperation(MenuOperation menuOperation) {
//		Assert.notNull(menuOperation.getMenuId(), "根据菜单和操作删除关系表>>>菜单ID为空");
//		Assert.notNull(menuOperation.getOperationId(), "根据菜单和操作删除关系表>>>操作ID为空");
		if(null==menuOperation||null==menuOperation.getMenuId()||null==menuOperation.getOperationId()||null==menuOperation.getSystemId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
		User user = new User();
		user.setOperationId(menuOperation.getOperationId());
		user.setMenuId(menuOperation.getMenuId());
		//查询删除操作所影响的人
		List<User> users = userService.selectUserByOperationIdAndMenuId(user);
		List<PermissionExchange> exchanges = new ArrayList<>();
		int batchNum = 0;
		
		for (User user2 : users) {
			PermissionExchange exchange =new PermissionExchange();
			exchange.setModifier(HttpServiceContext.getCurrentUserName());
			exchange.setModifierId(HttpServiceContext.getCurrentUserId());
			exchange.setType(0);
			exchange.setCreated(new Date());
			exchange.setLasttimeModify(exchange.getCreated());
			exchange.setSystemId(menuOperation.getSystemId());
			exchange.setUserId(user2.getId());
			exchange.setIsSend(0);
			exchanges.add(exchange);
			batchNum++;
			if (batchNum == 100) {
				if (exchanges != null) {
				// 插入数据库
					try {
						exchangeService.insertBatch(exchanges);
					} catch (Exception e) {
						throw new RuntimeExceptionWarn("插入权限变更表失败");
					}
				}
				// 重置参数变量
				batchNum = 0;
				exchanges = new ArrayList<PermissionExchange>();
			}
		}
		if (exchanges != null && exchanges.size()>0) {
			try {
				exchangeService.insertBatch(exchanges);
			} catch (Exception e) {
				throw new RuntimeExceptionWarn("插入权限变更表失败");
			}
			batchNum = 0;
			exchanges = new ArrayList<PermissionExchange>();
		}
	
		
		menuMapper.deleteRoleMenuOperation(menuOperation);
		menuMapper.deleteMenuOperation(menuOperation);
	}

	@Override
	public List<Menu> findMenuBySystemIdAndUserId(User user) {
		if(null==user||null==user.getId()||null==user.getSystemId()){
			throw new RuntimeExceptionWarn(ServerStatus.PARAM_NOT_EMPTY);
		}
		return 	menuMapper.findMenuBySystemIdAndUserId(user);
	}

	
}
