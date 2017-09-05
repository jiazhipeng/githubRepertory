package com.hy.security.service.operation.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.hy.common.utils.ParamVerifyUtil;
import com.hy.common.utils.StringUtils;
import com.hy.model.PageResult;
import com.hy.security.common.HttpServiceContext;
import com.hy.security.dao.ApiMapper;
import com.hy.security.dao.OperationMapper;
import com.hy.security.entity.Api;
import com.hy.security.entity.Menu;
import com.hy.security.entity.MenuOperation;
import com.hy.security.entity.Operation;
import com.hy.security.service.operation.OperationService;
@Service
public class OperationServiceImpl implements OperationService {

	@Autowired
	private OperationMapper<Operation> operationMapper;
	@Autowired
	private ApiMapper<Api> apiMapper;
	
	@Override
	public Boolean exists(Long id) {		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Operation create(Operation operation) {
		ParamVerifyUtil.paramNotNull(operation.getName(), "创建操作时，操作名称不能为空");
		ParamVerifyUtil.paramNotNull(operation.getSystemId(), "创建操作时，系统ID不能为空");
		//封装当前用户
		Date date = new Date();
		operation.setLasttimeModify(date);
		operation.setCreated(date);
		operation.setModifier(HttpServiceContext.getCurrentUserName());
		operation.setModifierId(HttpServiceContext.getCurrentUserId());
		operationMapper.insertSelective(operation);
		//插入操作与api的对应关系
		for(Long apiId : operation.getApiIds()){
			Api api = apiMapper.selectByPrimaryKey(apiId);
			if(null != api){
				//插入tr_operation_api
				operationMapper.insertOperationApi(operation.getId(), apiId);
			}
		}
		return operationMapper.selectByPrimaryKey(operation.getId());
	}

	@Override
	public Operation update(Operation operation) {
		ParamVerifyUtil.paramNotNull(operation.getId(), "更新操作时，操作Id不能为空");
		ParamVerifyUtil.paramNotNull(operation.getName(), "更新操作时，操作名称不能为空");
		ParamVerifyUtil.paramNotNull(operation.getSystemId(), "更新操作时，系统ID不能为空");
		//删除该操作下原来的api
		operationMapper.deleteApisByOperationId(operation.getId());
		Date date = new Date();
		operation.setLasttimeModify(date);
		operation.setModifier(HttpServiceContext.getCurrentUserName());
		operation.setModifierId(HttpServiceContext.getCurrentUserId());
		operationMapper.updateByPrimaryKeySelective(operation);
		//插入操作与api的对应关系
		for(Long apiId : operation.getApiIds()){
			Api api = apiMapper.selectByPrimaryKey(apiId);
			if(null != api){
				//插入tr_operation_api
				operationMapper.insertOperationApi(operation.getId(), apiId);
			}
		}
		return operationMapper.selectByPrimaryKey(operation.getId());
	}

	@Override
	public List<Operation> findOperationsBySystemId(Long systemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Operation> findMenuOperationsByMenuId(Menu menu) {
		ParamVerifyUtil.paramNotNull(menu.getId(), "根据菜单ID和系统ID查询对应的操作时，菜单Id不能为空");
//		ParamVerifyUtil.paramNotNull(menu.getSystemId(),"根据菜单ID和系统ID查询对应的操作时，系统ID不能为空");
		List<Operation> operationList =operationMapper.findMenuOperationsByMenuId(menu);
		return operationList;
	}

	@Override
	public List<Operation> findOperationsByRoleId(Long roleId) {
		ParamVerifyUtil.paramNotNull(roleId, "根据roleId查询对应的操作时，roleId不能为空");
		return operationMapper.findOperationsByRoleId(roleId);
	}

	@Override
	public List<Operation> findOperationsByOrganizationId(Long organizationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Operation> findOperationsByUserId(Long roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object updateRoleOperations(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Operation> findOperationList(Operation operation) {
		return operationMapper.selectByCondition(operation);
	}

	@Override
	public Operation findOperationById(Long operationId) {
		ParamVerifyUtil.paramNotNull(operationId, "根据操作ID查询操作时，操作ID不能为空");
		return operationMapper.selectByPrimaryKey(operationId);
	}

	@Override
	public List<Operation> findOperationsByApi(Api api) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean exists(Operation operation) {
		ParamVerifyUtil.paramNotNull(operation.getName(), "操作名称不能为空");
		Integer count = operationMapper.findRepeatRecordCount(operation);
		if(null != count && count.intValue() > 0){
			return true;
		}
		return false;
	}

	

	@Override
	public void deleteMenuOperations(MenuOperation menuOperation) {
		// TODO Auto-generated method stub
		operationMapper.deleteMenuOperations(menuOperation);
	}

	@Override
	public void insertBatchMenuOperation(List<MenuOperation> list) {
		// TODO Auto-generated method stub
		operationMapper.insertBatchMenuOperation(list);
	}

	@Override
	public List<Operation> findOperationsByRoleIdAndMenuId(Menu menu) {
		ParamVerifyUtil.paramNotNull(menu.getRoleId(), "根据roleId和menuId查询操作时，roleId不能为空");
		ParamVerifyUtil.paramNotNull(menu.getId(), "根据roleId和menuId查询操作时，menuId不能为空");
		return operationMapper.findOperationsByRoleIdAndMenuId(menu.getRoleId(), menu.getId());
	}

	@Override
	public List<Operation> findNotRelatedsOperations(MenuOperation menuOperation) {
		ParamVerifyUtil.paramNotNull(menuOperation.getMenuId(), "根据菜单查询未关联的操作，菜单id不能为空");
		ParamVerifyUtil.paramNotNull(menuOperation.getSystemId(), "根据菜单查询未关联的操作，系统id不能为空");
		return operationMapper.findNotRelatedsOperations(menuOperation);
	}

	@Override
	public PageResult<Operation> findOperationsByPage(Operation operation) {
		ParamVerifyUtil.paramNotNull(operation);
        Integer page = operation.getPage();
        Integer pageSize = operation.getPageSize();
        /*搜索条件*/
        List<Operation> operations = null;
        int totalSize = 0;
        Operation operationSearch = new Operation();
        operationSearch.setSystemId(operation.getSystemId());
        if(StringUtils.isNotBlank(operation.getName())){
        	operationSearch.setName(operation.getName());
        }
        if(StringUtils.isBlank(operation.getApiName())){
        	/*总记录数*/
            totalSize = this.operationMapper.selectByCondition(operationSearch).size();
            PageHelper.startPage(page,pageSize);
        	operations = this.operationMapper.selectByCondition(operationSearch);
        }else{
        	operationSearch.setApiName(operation.getApiName());
        	totalSize = this.operationMapper.findOperationsByOperation(operationSearch).size();
        	PageHelper.startPage(page,pageSize);
        	operations = this.operationMapper.findOperationsByOperation(operationSearch);
        }
        if(!CollectionUtils.isEmpty(operations)){
        	for(Operation oper : operations){
        		List<Api> apis = this.apiMapper.findAllByOperationId(oper.getId());
        		oper.setApis(apis);
        	}
        }
        PageResult<Operation> pageResult = new PageResult<Operation>(page,pageSize,totalSize,operations);
        return pageResult;
	}

}
