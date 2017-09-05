package com.hy.authorization.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hy.annotation.API;
import com.hy.httpservice.RequestRouting;
import com.hy.runtime.APIContext;

@RestController
public class SubApiController {

	@Autowired
    private ApplicationContext applicationContext;
	
	
	@API(value = "拉取子系统本地API")
    @RequestMapping(value = "/parent/system/system_api_pull",method = RequestMethod.GET)
    public Object pullSystemApi(){

		List<RequestRouting> list = APIContext.getAllReqMappings(applicationContext);
        return list;
    }
}
