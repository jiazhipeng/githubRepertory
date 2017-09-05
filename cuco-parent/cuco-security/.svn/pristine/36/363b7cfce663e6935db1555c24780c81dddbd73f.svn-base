package com.hy.security.controller;

import com.hy.common.utils.ParamVerifyUtil;
import com.hy.annotation.API;
import com.hy.enums.ServerStatus;
import com.hy.exception.RuntimeExceptionWarn;
import com.hy.security.entity.Api;
import com.hy.security.entity.System;
import com.hy.security.service.api.ApiService;
import com.hy.security.service.system.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by WangShuai on 2016/7/21.
 */
@RestController
public class ApiController {

    @Autowired
    private ApiService apiService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private ApplicationContext applicationContext;

    @API(value = "更新API信息")
    @RequestMapping(value = "/v1/api/update",method = RequestMethod.POST)
    public Object updateApi(@RequestBody Api api){
        return apiService.update(api);
    }

    @API(value = "更新API禁用状态")
    @RequestMapping(value = "/v1/api/disabled/update",method = RequestMethod.POST)
    public Object updateApiDisabled(@RequestBody Api api){
        return apiService.updateApiDisabled(api);
    }

    @API(value = "更新API权限状态")
    @RequestMapping(value = "/v1/api/auth/update",method = RequestMethod.POST)
    public Object updateApiAuth(@RequestBody Api api){
        return apiService.updateApiAuth(api);
    }

    @API(value = "根据系统ID查询所有有效API")
    @RequestMapping(value = "/v1/available_apis/system",method = RequestMethod.GET)
    public Object findAvailableApisBySystemId(@RequestParam("system_id") Long systemId){
        ParamVerifyUtil.paramNotNull(systemId);
        Api api = new Api();
        api.setDisabled(false);
        api.setSystemId(systemId);
        return apiService.findAvailableBySystemId(api);
    }

    @API(value = "拉取系统API")
    @RequestMapping(value = "/v1/system_api_pull",method = RequestMethod.POST)
    public Object pullSubSystemApi(@RequestBody Api api){
        /*基本校验*/
        ParamVerifyUtil.paramNotNull(api);
        Long systemId = api.getSystemId();
        ParamVerifyUtil.paramNotNull(systemId);

        /*判断系统是否存在*/
        ParamVerifyUtil.notExists(systemService.exists(systemId),"系统不存在");
        /*获取系统*/
        System system = systemService.findById(systemId);
        /*本系统拉取本地API*/
        if(systemService.isMain(system)){
            return apiService.syncLocalApis(applicationContext,systemId);
        }
        /*子系统发请求获取API*/
        if(systemService.isSub(system)){
            return apiService.syncSubApis(system);
        }
        return "";
    }

    @API(value = "根据系统ID分页查询所有API")
    @RequestMapping(value = "/v1/api/page",method = RequestMethod.POST)
    public Object findApiPage(@RequestBody Api api){
        ParamVerifyUtil.paramNotNull(api);
        return apiService.findApiPage(api);
    }

    @API(value = "mock子系统api数据")
    @RequestMapping(value = "/v1/sub_api_mock",method = RequestMethod.GET)
    public Object mockSubApis(){
        com.hy.httpservice.ResponseBody responseBody = new com.hy.httpservice.ResponseBody();
        responseBody.setCode(ServerStatus.SUCCESS.getCode());
        responseBody.setMessage("");

        Api api = new Api();
        api.setName("创建");
        List<Api> apiList = apiService.findByApi(api);
        responseBody.setData(apiList);
        return apiList;
    }
}
