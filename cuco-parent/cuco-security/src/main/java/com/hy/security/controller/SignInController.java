package com.hy.security.controller;

import com.hy.annotation.API;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WangShuai on 2016/7/21.
 */
@RestController
public class SignInController {

    @API(value = "通过微信登录",auth = false)
    @RequestMapping(value = "/v1/sign_in/wechat",method = RequestMethod.POST)
    public Object signInByWechat(){
        //TODO 微信端校验成功后

        //TODO server发送access_token

        return null;
    }
}
