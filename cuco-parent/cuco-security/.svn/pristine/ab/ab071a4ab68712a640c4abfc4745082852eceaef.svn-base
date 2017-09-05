package com.hy.security.service.api.impl;

import java.io.IOException;
import java.util.*;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.hy.common.utils.HttpUtils;
import com.hy.common.utils.JsonUtil;
import com.hy.common.utils.LogUtil;
import com.hy.common.utils.ParamVerifyUtil;
import com.hy.common.utils.PrePersistUtil;
import com.hy.common.utils.StringUtils;
import com.hy.constant.Constant;
import com.hy.enums.ServerStatus;
import com.hy.exception.RuntimeExceptionWarn;
import com.hy.httpservice.RequestRouting;
import com.hy.httpservice.ResponseBodyView;
import com.hy.model.PageResult;
import com.hy.runtime.APIContext;
import com.hy.security.common.HttpServiceContext;
import com.hy.security.controller.SecurityController;
import com.hy.security.dao.ApiMapper;
import com.hy.security.entity.Api;
import com.hy.security.entity.System;
import com.hy.security.service.api.ApiService;
import com.hy.security.service.child.ChildSystemServiceImpl;
import com.hy.security.service.system.SystemService;

/**
 * Created by WangShuai on 2016/7/25.
 */
@Service
public class ApiServiceImpl implements ApiService {

	@Autowired
	private ApiMapper<Api> apiMapper;
    @Autowired
    private SystemService systemService;

    @Override
    public List<Api> findAllByOperationId(Long operationId) {
    	ParamVerifyUtil.paramNotNull(operationId);
    	return apiMapper.findAllByOperationId(operationId);
    }

    @Override
    public Boolean exists(Long apiId) {
        if(apiId==null){
            return false;
        }
        Api api = apiMapper.selectByPrimaryKey(apiId);
        return api!=null;
    }

    private void apiExistsVerify(Api api){
        ParamVerifyUtil.paramNotNull(api);
        Long apiId = api.getId();

        ParamVerifyUtil.notExists(this.exists(apiId));
    }

    @Override
    public Api update(Api api) {
        this.apiExistsVerify(api);
        Api apiDb = apiMapper.selectByPrimaryKey(api.getId());

        /*仅能修改名字*/
        ParamVerifyUtil.paramNotEmpty(api.getName());
        apiDb.setName(api.getName());

        PrePersistUtil.onModify(apiDb, HttpServiceContext.getCurrentUserId(),HttpServiceContext.getCurrentUserName());
        apiMapper.updateByPrimaryKeySelective(apiDb);
        return apiDb;
    }

    @Override
    public Api updateApiDisabled(Api api) {
        this.apiExistsVerify(api);
        Api apiDb = apiMapper.selectByPrimaryKey(api.getId());
        ParamVerifyUtil.paramNotNull(api.getDisabled(),"禁用状态不能为空");

        apiDb.setDisabled(api.getDisabled());
        PrePersistUtil.onModify(apiDb,HttpServiceContext.getCurrentUserId(),HttpServiceContext.getCurrentUserName());
        apiMapper.updateByPrimaryKeySelective(apiDb);
        return apiDb;
    }

    @Override
    public Api updateApiAuth(Api api) {
        this.apiExistsVerify(api);
        Api apiDb = apiMapper.selectByPrimaryKey(api.getId());
        ParamVerifyUtil.paramNotNull(api.getAuth(),"权限状态不能为空");

        apiDb.setAuth(api.getAuth());
        PrePersistUtil.onModify(apiDb,HttpServiceContext.getCurrentUserId(),HttpServiceContext.getCurrentUserName());
        apiMapper.updateByPrimaryKeySelective(apiDb);
        return apiDb;
    }

    @Override
    public List<Api> findByApi(Api api) {
        return apiMapper.selectByCondition(api);
    }

    @Override
    public List<Api> findAvailableApisByUserIdAndSystemId(Long userId, Long systemId) {
        ParamVerifyUtil.paramNotNull(userId,"用户ID不能为空");
        ParamVerifyUtil.paramNotNull(systemId,"系统ID不能为空");

        return apiMapper.findAvailableApisByUserIdAndSystemId(userId,systemId);
    }

    @Override
    public List<Api> findAllBySystemId(Long systemId) {
    	ParamVerifyUtil.paramNotNull(systemId);
    	Api api = new Api();
    	api.setSystemId(systemId);
        return apiMapper.selectByCondition(api);
    }

    @Override
    public List<Api> findAvailableBySystemId(Api api) {
        ParamVerifyUtil.paramNotNull(api);
        return this.findByApi(api);
    }

    @Override
    public Object syncLocalApis(ApplicationContext applicationContext,Long systemId) {
        List<RequestRouting> list = APIContext.getAllReqMappings(applicationContext);
        return syncApis(list,systemId);
    }

    private Object syncApis(List<RequestRouting> list,Long systemId){
        int count = 0;
        for(RequestRouting r : list){
            if(r != null){
                count += syncApi(r,systemId);
            }
        }

        Map<String,Object> result = new HashMap();
        result.put("new_api_size",count);
        return result;
    }


    /**
     * 拉取子系统API
     * {
     *     "code":"0000",
     *     "data":[
     *          {
     *              "uri":"/v1/aa",
     *              "method":"POST",
     *              "name":"aa",
     *              "auth":true
     *          }
     *     ]
     * }
     */
    @Override
    public Object syncSubApis(System system) {
        /*verify data*/
        ParamVerifyUtil.paramNotNull(system,"系统不存在");

        /*不是子系统*/
        if(!systemService.isSub(system)){
            throw new RuntimeExceptionWarn("系统非子系统");
        }

        /*API同步地址*/
        String  apiSyncUrl = system.getApiSyncUrl();
        if(StringUtils.isEmpty(apiSyncUrl)){
            throw new RuntimeExceptionWarn("api同步地址为空");
        }

        /*发送请求解析数据*/
        List<RequestRouting> list = getSubApisRequest(system.getId(),apiSyncUrl+"/parent/system/system_api_pull");
        return syncApis(list,system.getId());
    }

    private List<RequestRouting> getSubApisRequest(Long systemId,String apiSyncUrl) {
        //TODO 系统交互鉴权待确定
        try {
        	System system = systemService.findById(systemId);
        	String token = ChildSystemServiceImpl.loginChild(system.getAppId(), system.getAppSecret(), system.getApiSyncUrl());
        	Header headers[] = new BasicHeader[]{new BasicHeader("Content-Type","application/json"),
					new BasicHeader("System-Access-Token",token)}; 
            CloseableHttpResponse response = HttpUtils.sendGet(apiSyncUrl,headers);
            String responseStr = EntityUtils.toString(response.getEntity(), Constant.CHARSET_UTF8);
            java.lang.System.out.println(responseStr);
            ObjectMapper objectMapper = JsonUtil.getObjectMapper();

            ResponseBodyView<List<RequestRouting>> responseBody = objectMapper.readValue(responseStr.getBytes(Constant.CHARSET_UTF8), new TypeReference<ResponseBodyView<List<RequestRouting>>>(){});
            String code = responseBody.getCode();
            if(!ServerStatus.SUCCESS.getCode().equals(code)){
                throw new RuntimeExceptionWarn("调用子系统获取API接口失败>"+StringUtils.fillNull(responseBody.getMessage()));
            }

            List<RequestRouting> requestRoutings = new ArrayList();
            if(responseBody.getData()!=null){
                requestRoutings = responseBody.getData();
            }
            return requestRoutings;
        } catch (Exception e){
            LogUtil.getLogger().error("拉取子系统API调用接口异常：",e);
            throw new RuntimeExceptionWarn("拉取子系统API调用接口异常");
        }
    }

    @Override
    public PageResult findApiPage(Api api) {
        ParamVerifyUtil.paramNotNull(api);
        Integer page = api.getPage();
        Integer pageSize = api.getPageSize();

        /*搜索条件*/
        Api apiSearch = new Api();
        apiSearch.setAuth(api.getAuth());
        apiSearch.setDisabled(api.getDisabled());
        apiSearch.setName(api.getName());
        apiSearch.setSystemId(api.getSystemId());

        /*总记录数*/
        int totalSize = apiMapper.countByCondition(apiSearch);

        /*分页信息*/
        PageHelper.startPage(page,pageSize);

        List<Api> apis = this.findByApi(apiSearch);

        PageResult<Api> pageResult = new PageResult(page,pageSize,totalSize,apis);

        return pageResult;
    }

    private int syncApi(RequestRouting requestRouting,Long systemId) {
        int index = 0;

        String uri = requestRouting.getUri();
        String method = requestRouting.getMethod();
        if(StringUtils.isEmpty(uri) || StringUtils.isEmpty(method)){
            return index;
        }

        Api api = new Api(uri,method,systemId);
        List<Api> apis = this.findByApi(api);
        if(apis==null || apis.isEmpty()){
            //新增
            return addApi(requestRouting,systemId);
        }
        if(!apis.isEmpty()){
            //更新
            return updateAPI(apis.get(0),requestRouting);
        }
        return index;
    }

    private int updateAPI(Api api, RequestRouting requestRouting) {
        boolean update = false;

        String name = requestRouting.getName();
        if(StringUtils.isNotEmpty(name) && !name.equals(api.getName())){
            api.setName(name);
            update = true;
        }

        boolean auth = requestRouting.isAuth();
        if(auth != api.getAuth()){
            api.setAuth(auth);
            update = true;
        }

        if(update){
            apiMapper.updateByPrimaryKeySelective(api);
            return 1;
        }

        return 0;
    }

    private int addApi(RequestRouting requestRouting,Long systemId) {
        Api api = new Api();
        api.setName(requestRouting.getName());
        api.setUri(requestRouting.getUri());
        api.setMethod(requestRouting.getMethod());
        api.setSystemId(systemId);
        api.setDisabled(Boolean.FALSE);
        api.setAuth(requestRouting.isAuth());

        PrePersistUtil.onCreate(api,HttpServiceContext.getCurrentUserId(),HttpServiceContext.getCurrentUserName());

        apiMapper.insertSelective(api);
        return 1;
    }

}
