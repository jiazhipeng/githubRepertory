package com.hy.security.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hy.annotation.API;
import com.hy.common.utils.DesUtil;
import com.hy.common.utils.ParamVerifyUtil;
import com.hy.enums.ServerStatus;
import com.hy.exception.RuntimeExceptionWarn;
import com.hy.httpservice.ResponseUtil;
import com.hy.security.entity.Menu;
import com.hy.security.entity.MenuOperation;
import com.hy.security.entity.Operation;
import com.hy.security.entity.PermissionExchange;
import com.hy.security.entity.System;
import com.hy.security.service.menu.MenuService;
import com.hy.security.service.operation.OperationService;
import com.hy.security.service.system.SystemService;

/**
 * Created by WangShuai on 2016/7/22.
 */
@RestController
public class MenuController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private OperationService operationService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private SystemService systemService;
    
    
    
    @RequestMapping(value = "/httptest",method = RequestMethod.POST)
    public Object httpTest(HttpServletRequest request) throws IOException, Exception {
    	ServletInputStream in = request.getInputStream();
    	BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));  
		StringBuffer sb = new StringBuffer();  
		String str="";
		while ((str = reader.readLine()) != null)  
		{  
			sb.append(str);  
		}
		in.close();
		String key = "hycx!@#$";
		Map<String, Object> map = JSON.parseObject(sb.toString());
		String keyV = (String) map.get("key");
		String decrypt = DesUtil.decrypt(keyV, key);
		String secretKey = decrypt.substring(0, decrypt.length()-32);
		
		
		logger.info("系统秘钥是：》》》》》》》》》》》》》》》》》》"+secretKey);
//		if(!"appid1".equals(secretKey)){
//			throw new RuntimeExceptionWarn("秘钥错误！");
//		
//		}
		List<PermissionExchange> array = JSON.parseArray((String)map.get("exchange"), PermissionExchange.class);
		for (PermissionExchange permissionExchange : array) {
			logger.info("更改的权限资源影响人：》》》》》》》》》》》》》》》》》》"+permissionExchange.getUserId());
		}
    	return "成功";
    }
    
    @API(value = "创建菜单")
    @RequestMapping(value = "/v1/menu/create",method = RequestMethod.POST)
    public Object createMenu(@RequestBody Menu menu){
    	logger.info("===============创建菜单================菜单名称："+menu.getName());
    	ParamVerifyUtil.paramNotNull(menu);
//    	入参  link	    name		parent_id	system_id
    	ParamVerifyUtil.paramNotNull(menu.getLink(), "菜单地址不能为空",50);
    	ParamVerifyUtil.paramNotNull(menu.getName(), "菜单名称不能为空",50);
    	ParamVerifyUtil.paramNotNull(menu.getParentId(), "菜单所属父级ID不能为空");
    	ParamVerifyUtil.paramNotNull(menu.getSystemId(), "菜单所属系统ID不能为空");
    	boolean isExist =menuService.findNameIsExistsBySystemIdAndParentIdAndName(menu);
    	if(isExist){
    		throw new RuntimeExceptionWarn(ServerStatus.MENU_NAME_REPEAT);
    	}
    	menu = menuService.create(menu);
        return menu;
    }

    @API(value = "更新菜单")
    @RequestMapping(value = "/v1/menu/update",method = RequestMethod.POST)
    public Object updateMenu(@RequestBody Menu menu){
    	logger.info("===============更新菜单================菜单名称："+menu.getName());
    	
    	ParamVerifyUtil.paramNotNull(menu);
//    	入参  ID link	    name	
    	ParamVerifyUtil.paramNotNull(menu.getId(), "菜单ID不能为空");
    	ParamVerifyUtil.paramNotNull(menu.getName(), "菜单名称不能为空");
    	ParamVerifyUtil.paramNotNull(menu.getLink(), "菜单地址不能为空");
    	menu = menuService.update(menu);
        return menu;
    }

/*    @API(value = "删除菜单")
    @RequestMapping(value = "/v1/menu/delete",method = RequestMethod.POST)
    public Object deleteMenu(@RequestBody Menu menu){
    	logger.info("===============删除菜单================菜单ID："+menu.getId());
    	ParamVerifyUtil.paramNotNull(menu);
//    	入参  ID 
    	ParamVerifyUtil.paramNotNull(menu.getId(), "菜单ID不能为空");
    	menuService.delete(menu);
        return "删除成功";
    }*/

    @API(value = "添加操作")
    @RequestMapping(value = "/v1/menu/operation/update",method = RequestMethod.POST)
    public Object updateMenuOperation(@RequestBody MenuOperation menuOperation){
    	logger.info("===============更新菜单所拥有操作================菜单ID："+menuOperation.getMenuId());
//    	menu_id	operationIds	system_id
    	ParamVerifyUtil.paramNotNull(menuOperation);
    	ParamVerifyUtil.paramNotNull(menuOperation.getMenuId(), "菜单ID不能为空");
    	ParamVerifyUtil.paramNotNull(menuOperation.getSystemId(), "菜单所属系统ID不能为空");
        List<Operation> operationList=   menuService.updateMenuOperations(menuOperation);
        return  ResponseUtil.toSuccessBody(operationList);
    }
    @API(value = "删除操作")
    @RequestMapping(value = "/v1/menu/operation/delete",method = RequestMethod.POST)
    public Object deleteMenuOperation(@RequestBody MenuOperation menuOperation){
    	logger.info("===============删除操作================操作ID："+menuOperation.getId());
//    	入参  menu_id operation_id system_id
    	ParamVerifyUtil.paramNotNull(menuOperation);
    	ParamVerifyUtil.paramNotNull(menuOperation.getMenuId(), "菜单ID不能为空");
    	ParamVerifyUtil.paramNotNull(menuOperation.getOperationId(), "操作ID不能为空");
    	ParamVerifyUtil.paramNotNull(menuOperation.getSystemId(), "系统ID不能为空");
    	menuService.deleteMenuOperation(menuOperation);
    	return  "删除成功";
    }
    @API(value = "点击添加操作按钮查看未选择操作列表")
    @RequestMapping(value = "/v1/menu/operation/view",method = RequestMethod.POST)
    public Object findOperationListView(@RequestBody MenuOperation menuOperation){
    	logger.info("===============点击添加操作按钮查看已或未选择操作列表================菜单ID："+menuOperation.getMenuId());
//    	入参 id	system_id
    	ParamVerifyUtil.paramNotNull(menuOperation);
    	ParamVerifyUtil.paramNotNull(menuOperation.getMenuId(), "菜单ID不能为空");
    	ParamVerifyUtil.paramNotNull(menuOperation.getSystemId(), "菜单所属系统ID不能为空");
    	
    	//未关联
    	List<Operation> notRelateds = operationService.findNotRelatedsOperations(menuOperation);
    	//已关联
//    	List<Operation> relateds=menuService.findOperationListByMenuId(menu);
//    	notRelateds.removeAll(relateds);
//    	operation.setNotRelateds(notRelateds);
//    	operation.setRelateds(relateds);
    	return ResponseUtil.toSuccessBody(notRelateds);
    }
    @API(value = "根据节点查询所有已关联操作")
    @RequestMapping(value = "/v1/menu/operation/list",method = RequestMethod.GET)
    public Object findOperationList(Long id){
    	logger.info("===============根据节点查询所有操作================菜单ID："+id);
    	ParamVerifyUtil.paramNotNull(id, "菜单ID不能为空");
    	Menu menu = new Menu();
    	menu.setId(id);
    	List<Operation> operations = operationService.findMenuOperationsByMenuId(menu);
    	return ResponseUtil.toSuccessBody(operations);
    }

   /* @API(value = "查询用户所拥有菜单")
    @RequestMapping(value = "/v1/menus/user",method = RequestMethod.GET)
    public Object findMenusByUserId(Long user_id){
    	Menu menu = new Menu();
    	menu.setRoleId(Long.valueOf(user_id));
		//        menuService.findMenusByUserId(user_id);
    	List<Menu> findMenuByRole = menuService.findMenuByRole(menu);
    	MenuOperation menuOperation = new MenuOperation();
    	menuOperation.setMenuId(1L);
    	menuOperation.setOperationId(1L);
    	menuOperation.setSystemId(1L);
    	menuOperation = menuService.findMenuOperationId(menuOperation);
        return menuOperation;
    }*/

    @API(value = "查询系统所属菜单列表")
    @RequestMapping(value = "/v1/menus/system",method = RequestMethod.GET)
    public Object findMenusBySystemId(Long system_id){
    	logger.info("===============查询系统所属菜单列表================系统ID："+system_id);
    	ParamVerifyUtil.paramNotNull(system_id, "系统ID不能为空");
    	System system = systemService.findById(system_id);
        List<Menu> menuList= menuService.findMenusBySystemId(system_id);
        Map<String, Object> menus = new HashMap<>();
        menus.put("system_name", system.getName());
        menus.put("menuList", menuList);
        return menus;
    }
}
