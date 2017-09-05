package com.hy.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hy.annotation.API;
import com.hy.security.entity.Nation;
import com.hy.security.service.nation.NationService;

/**
 * Created by WangShuai on 2016/7/21.
 */
@RestController
public class NationController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
    private NationService nationService;

    
    @API(value = "查询所有城市")
    @RequestMapping(value = "/v1/nation/city",method = RequestMethod.GET)
    public Object createUser(){
        return nationService.selectAllCity(new Nation());
    }
}
