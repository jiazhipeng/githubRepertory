package com.hy.security.controller;

import com.hy.annotation.API;
import com.hy.security.view.request.SystemReqView;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WangShuai on 2016/7/22.
 */
@RestController
public class TokenController {

    @API(value = "系统获取access_token") //TODO 可直接用app_secret访问
    @RequestMapping(value = "/v1/system/access_token/create",method = RequestMethod.POST)
    public Object getSystemAccessToken(@RequestBody SystemReqView systemReqView){
        return null;
    }
}
