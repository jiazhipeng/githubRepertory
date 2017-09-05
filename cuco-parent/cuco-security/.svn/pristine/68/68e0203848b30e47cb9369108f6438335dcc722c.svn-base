package com.hy.security.controller;

import com.hy.annotation.API;
import com.hy.common.utils.ParamVerifyUtil;
import com.hy.constant.Constant;
import com.hy.security.entity.System;
import com.hy.security.entity.User;
import com.hy.security.service.system.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by WangShuai on 2016/7/21.
 */
@RestController
public class SystemController {

    @Autowired
    private SystemService systemService;

    @API(value = "创建系统")
    @RequestMapping(value = "/v1/system/create",method = RequestMethod.POST)
    public Object createSystem(@RequestBody System system){
        ParamVerifyUtil.paramNotNull(system);

        return systemService.createSystem(system);
    }

    @API(value = "编辑系统")
    @RequestMapping(value = "/v1/system/update",method = RequestMethod.POST)
    public Object updateSystem(@RequestBody System system){
        ParamVerifyUtil.paramNotNull(system);
        return systemService.updateSystem(system);
    }

    @API(value = "查询所有可用系统")
    @RequestMapping(value = "/v1/systems/availability",method = RequestMethod.GET)
    public Object findAvailableSystems(){
        System system = new System();
        system.setIsValue(Constant.IS_VALUE_ENABLE);
        return systemService.findBySystem(system);
    }

    @API(value = "通过系统ID查询系统")
    @RequestMapping(value = "/v1/system/id",method = RequestMethod.GET)
    public Object findSystemBySystemId(@RequestParam("system_id") Long systemId){
        ParamVerifyUtil.paramNotNull(systemId);
        return systemService.findById(systemId);
    }

    @API(value = "分页查询所有系统")
    @RequestMapping(value = "/v1/system/page",method = RequestMethod.POST)
    public Object findSystemPage(@RequestBody System system){
        ParamVerifyUtil.paramNotNull(system);
        return systemService.findSystemPage(system);
    }
}
