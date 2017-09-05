package com.hy.security.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hy.annotation.API;
import com.hy.common.utils.ParamVerifyUtil;
import com.hy.exception.RuntimeExceptionWarn;
import com.hy.security.entity.Api;
import com.hy.security.entity.Operation;
import com.hy.security.service.api.ApiService;
import com.hy.security.service.operation.OperationService;

/**
 * Created by WangShuai on 2016/7/22.
 */
@RestController
public class OperationController {
	
	private static Logger logger = LoggerFactory.getLogger(OperationController.class);

    @Autowired
    private OperationService operationService;
    @Autowired
    private ApiService apiService;

    @API(value = "创建操作")
    @RequestMapping(value = "/v1/operation/create",method = RequestMethod.POST)
    public Object createOperation(@RequestBody Operation operation){
//    	ParamVerifyUtil.paramNotEmpty(operation.getName());
    	ParamVerifyUtil.paramNotNull(operation.getName(),"操作名称不能为空",50);
    	ParamVerifyUtil.paramNotNull(operation.getSystemId());
    	//1.判断操作名称是否重复
    	Boolean exists = operationService.exists(operation);
    	if(exists){
    		logger.info("在{}系统中操作名称{}已经存在！",operation.getSystemId(),operation.getName());
			throw new RuntimeExceptionWarn("操作名称已经存在！");
    	}
    	//2.创建操作
    	operation = operationService.create(operation);
    	operation = setApisForOperation(operation);
    	return operation;
    }

    @API(value = "更新操作")
    @RequestMapping(value = "/v1/operation/update",method = RequestMethod.POST)
    public Object updateOperation(@RequestBody Operation operation){
    	ParamVerifyUtil.paramNotNull(operation.getId());
//    	ParamVerifyUtil.paramNotEmpty(operation.getName());
    	ParamVerifyUtil.paramNotNull(operation.getName(),"操作名称不能为空",50);
    	ParamVerifyUtil.paramNotNull(operation.getSystemId());
    	//1.判断操作名称是否重复   
    	Boolean exists = operationService.exists(operation);
    	if(exists){
    		logger.info("在{}系统中操作名称{}已经存在！",operation.getSystemId(),operation.getName());
			throw new RuntimeExceptionWarn("操作名称已经存在！");
    	}
    	operation = operationService.update(operation);
    	operation = setApisForOperation(operation);
    	return operation;
    }

   /* @API(value = "查询菜单所拥有操作")
    @RequestMapping(value = "/v1/menu/{menu_id}/operations",method = RequestMethod.GET)
    public Object findOperationsByMenuId(@PathVariable("menu_id") Long menuId){
        operationService.findMenuOperationsByMenuId(menuId);
        return null;
    }*/

    @API(value = "查询系统所拥有操作")
    @RequestMapping(value = "/v1/system/{system_id}/operations",method = RequestMethod.GET)
    public Object findOperationsBySystemId(@PathVariable("system_id") Long systemId){
        operationService.findOperationsBySystemId(systemId);
        return null;
    }

    @API(value = "分页查询操作")
    @RequestMapping(value = "/v1/operation/page",method = RequestMethod.POST)
    public Object findOperationPage(@RequestBody Operation operation){
    	ParamVerifyUtil.paramNotNull(operation);
    	ParamVerifyUtil.paramNotNull(operation.getSystemId());
    	return operationService.findOperationsByPage(operation);
    }

    /**
     * 暂不实现
     * @param object
     * @return
     */
    @API(value = "更新操作有效状态")
    @RequestMapping(value = "/v1/operation_disabled/update",method = RequestMethod.POST)
    public Object updateOperationAvailable(@RequestBody Object object){

        return null;
    }

    //暂不实现
    @API(value = "查询组织机构所拥有操作")
    @RequestMapping(value = "/v1/operations/organization/{organization_id}",method = RequestMethod.GET)
    public Object findOperationsByOrganizationId(@PathVariable("organization_id") Long organizationId){
        operationService.findOperationsByOrganizationId(organizationId);
        return null;
    }

    //暂不实现
    @API(value = "查询角色所拥有操作")
    @RequestMapping(value = "/v1/operations/role/{role_id}",method = RequestMethod.GET)
    public Object findOperationsByRoleId(@PathVariable("role_id") Long roleId){
        operationService.findOperationsByRoleId(roleId);
        return null;
    }

    //暂不实现
    @API(value = "查询用户所拥有操作")
    @RequestMapping(value = "/v1/operations/user/{user_id}",method = RequestMethod.GET)
    public Object findOperationsByUserId(@PathVariable("user_id") Long userId){
        operationService.findOperationsByUserId(userId);
        return null;
    }
    
    @API(value = "根据系统ID获取API列表")
    @RequestMapping(value = "/v1/system/apis",method = RequestMethod.GET)
	public Object findAllApisBySystemId(Long system_id){
    	ParamVerifyUtil.paramNotNull(system_id);
    	Api api = new Api();
    	api.setSystemId(system_id);
    	api.setAuth(true);
    	api.setDisabled(false);
        return apiService.findAvailableBySystemId(api);
    }

    @API(value = "根据操作ID查询操作")
    @RequestMapping(value = "/v1/operation",method = RequestMethod.GET)
	public Object findOperationByOperationId(Long operation_id){
    	ParamVerifyUtil.paramNotNull(operation_id);
    	Operation operation = operationService.findOperationById(operation_id);
    	if(null == operation){
    		logger.info("id为{}的操作不存在",operation_id);
			throw new RuntimeExceptionWarn("操作不存在！");
    	}
    	operation = setApisForOperation(operation);
    	/*OperationResView view = new OperationResView();
    	BeanUtils.copyProperties(operation, view);
    	List<Api> other = getOtherApis(operation);
    	view.setOtherApis(other);*/
        return operation;
    }
    
    /**
     * 设置操作的api
     * @param operation
     * @return
     */
    public Operation setApisForOperation(Operation operation){
    	if(null == operation || null == operation.getId()){
    		return null;
    	}
    	List<Api> apis = apiService.findAllByOperationId(operation.getId());
    	operation.setApis(apis);
    	return operation;
    }
    
    /**
     * 获取所在系统中操作不包含的API列表不包含的api
     * @param operation
     * @return
     */
    public List<Api> getOtherApis(Operation operation){
    	Api api = new Api();
    	api.setSystemId(operation.getSystemId());
    	List<Api> all = apiService.findAvailableBySystemId(api);
    	List<Api> cur = operation.getApis();
    	if(CollectionUtils.isEmpty(cur)){
    		return all;
    	}
    	List<Api> otherList = new ArrayList<Api>();
    	for(Api api0 : all){
    		boolean exits = false;
    		for(Api api1 : cur){
    			if(api0.getId() == api1.getId()){
    				exits = true;
    				break;
    			}
    		}
    		if(!exits){
    			otherList.add(api0);
    		}
    	}
    	return otherList;
    }
    
}
