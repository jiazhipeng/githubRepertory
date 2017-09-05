package com.hy.security.service.system.impl;

import com.github.pagehelper.PageHelper;

import java.util.List;

import com.hy.common.utils.*;
import com.hy.model.PageResult;
import com.hy.security.entity.Organization;
import com.hy.security.entity.User;
import com.hy.security.service.organization.OrganizationService;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.constant.Constant;
import com.hy.exception.RuntimeExceptionWarn;
import com.hy.security.common.HttpServiceContext;
import com.hy.security.dao.SystemMapper;
import com.hy.security.entity.System;
import com.hy.security.service.system.SystemService;
import com.hy.security.view.response.SystemResView;

/**
 * Created by WangShuai on 2016/7/22.
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private SystemMapper<System> systemMapper;
    @Autowired
    private OrganizationService organizationService;

    @Override
    public Boolean exists(Long systemId) {
        if(systemId==null){
            return false;
        }
        return systemMapper.selectByPrimaryKey(systemId) != null;
    }

    @Override
    public List<System> findBySystem(System system) {
        return systemMapper.selectByCondition(system);
    }

    @Override
    public System createSystem(System system) {
        ParamVerifyUtil.paramNotNull(system);
        system.setId(null);

        //verify param
        ParamVerifyUtil.paramNotNull(system.getRouteUrl(),"主页地址不能为空",64);
        ParamVerifyUtil.paramNotNull(system.getApiSyncUrl(),"API同步地址不能为空",128);
        ParamVerifyUtil.paramNotNull(system.getName(),"API同步地址不能为空",200);

        String code = system.getCode();
        if(StringUtils.isEmpty(code)){
            code = RandomStringUtils.randomAlphanumeric(8);
            system.setCode(code);
        }
        verifyCode(system);
        verifyName(system);

        //generate app_id & app_secret
        system.setAppId(this.generateAppId());
        system.setAppSecret(this.generateAppSecret());
        system.setType(Constant.SYSTEM_TYPE_SUB);

        PrePersistUtil.onCreate(system, HttpServiceContext.getCurrentUserId(),HttpServiceContext.getCurrentUserName());
        //save data
        systemMapper.insertSelective(system);

        //organization_id
        try {
            Organization organization = new Organization();
            organization.setSystemId(system.getId());
            organization.setName(system.getName());
            organization.setModifier(HttpServiceContext.getCurrentUserName());
            organization.setModifierId(HttpServiceContext.getCurrentUserId());
            Long organizationId = organizationService.createOrganizationForOne(organization).getId();
            system.setOrganizationId(organizationId);
        }catch (Exception e){
            LogUtil.getLogger().error("创建系统时，保存一级部门失败:",e);
            throw new RuntimeExceptionWarn("创建系统时，保存一级部门失败");
        }

        //save data
        systemMapper.updateByPrimaryKeySelective(system);
        return system;
    }

    @Override
    public System updateSystem(System system) {
        //verify param
        ParamVerifyUtil.paramNotNull(system);

        //记录是否存在
        Long id = system.getId();
        ParamVerifyUtil.notExists(this.exists(id),"系统不存在");

        //code & name
        String code = system.getCode();
        if(StringUtils.isEmpty(code)){
            code = RandomStringUtils.randomAlphanumeric(8);
            system.setCode(code);
        }
        verifyCode(system);
        verifyName(system);

        //不能修改字段
        system.setAppId(null);
        system.setAppSecret(null);
        system.setOrganizationId(null);
        system.setType(null);
        system.setCreated(null);

        PrePersistUtil.onModify(system,HttpServiceContext.getCurrentUserId(),HttpServiceContext.getCurrentUserName());

        //save data
        systemMapper.updateByPrimaryKeySelective(system);

        return systemMapper.selectByPrimaryKey(system.getId());
    }

    private String generateAppId() {
        return "id-" + Identities.uuid2();
    }

    private String generateAppSecret() {
        return Identities.uuid2();
    }

    private void verifyCode(System system) {
        Long systemId = system.getId();

        String code = system.getCode();
        ParamVerifyUtil.paramNotEmpty(code);

        System systemSearch = new System();
        systemSearch.setCode(code);
        system = this.findFirstBySystem(systemSearch);
        if(system==null){
            return;
        }
        if(system.getId() != systemId){
            throw new RuntimeExceptionWarn("系统code不能重复");
        }
    }

    private void verifyName(System system) {
        Long systemId = system.getId();

        String name = system.getName();
        ParamVerifyUtil.paramNotEmpty(name);

        System systemSearch = new System();
        systemSearch.setName(name);
        system = this.findFirstBySystem(systemSearch);
        if(system==null){
            return;
        }
        if(system.getId() != systemId){
            throw new RuntimeExceptionWarn("系统名称不能重复");
        }
    }

    private System findFirstBySystem(System system) {
        List<System> systems = this.findBySystem(system);
        if(!systems.isEmpty()){
            return systems.get(0);
        }
        return null;
    }

    @Override
    public String getSystemType(System system) {
        ParamVerifyUtil.paramNotNull(system,"获取系统类型失败：系统为null");
        return system.getType();
    }

    private void verifySystemType(String systemType){
        if( !Constant.SYSTEM_TYPES.contains(systemType) ){
            throw new RuntimeExceptionWarn("系统类型无效："+systemType);
        }
    }

    @Override
    public boolean isMain(System system) {
        String systemType = this.getSystemType(system);
        this.verifySystemType(systemType);

        if( Constant.SYSTEM_TYPE_MAIN.equals(systemType)
                && Constant.SYSTEM_CODE_AUTH.equals(system.getCode()) ){
            return true;
        }
        return false;
    }

    @Override
    public boolean isSub(System system) {
        return !isMain(system
        );
    }

    @Override
    public List<SystemResView> findAllSystems() {
        return null;
    }

    @Override
    public System findById(Long id) {
        ParamVerifyUtil.paramNotNull(id,"查询系统失败：id不能为null");

        ParamVerifyUtil.notExists(this.exists(id),"查询失败：系统不存在- "+id);
        return systemMapper.selectByPrimaryKey(id);
    }

    @Override
    public Object findSystemPage(System system) {
        ParamVerifyUtil.paramNotNull(system);
        Integer page = system.getPage();
        Integer pageSize = system.getPageSize();

        /*查询条件*/
        system = new System();
        system.setIsValue(Constant.IS_VALUE_ENABLE);

        /*总记录数*/
        Integer totalSize = systemMapper.countByCondition(system);

        /*分页信息*/
        PageHelper.startPage(page,pageSize);

        /*结果集*/
        List<System> systems = this.findBySystem(system);

        PageResult<System> pageResult = new PageResult(page,pageSize,totalSize,systems);
        return pageResult;
    }

    @Override
    public List<System> findSystemsOfUser(User user) {
        ParamVerifyUtil.paramNotNull(user);
        Long userId = user.getId();
        ParamVerifyUtil.paramNotNull(userId,"用户ID不能为空");

        return systemMapper.findSystemsOfUser(userId);
    }

	@Override
	public System findSystemByToken(String token) {
		
		return systemMapper.selectSystemByToken(token);
	}

}
