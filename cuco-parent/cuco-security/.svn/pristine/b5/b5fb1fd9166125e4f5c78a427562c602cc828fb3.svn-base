package com.hy.security.controller;

import com.hy.annotation.API;
import com.hy.security.service.user.UserWechatService;
import com.hy.security.view.request.UserWechatView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by WangShuai on 2016/7/21.
 */
@RestController
public class SignUpController {

    @Autowired
    private UserWechatService userWechatService;

    //TODO 需研究微信相关流程，确定是否有此接口
    @API("根据wechat-openId获取用户信息")
    @RequestMapping(value = "/v1/user_wechat/opend_id",method = RequestMethod.GET)
    public Object findUserByWechatOpendId(@RequestParam("open_id") String openId){
        return userWechatService.findByWechatOpenId(openId);
    }

    //TODO 需研究微信相关流程，确定是否有此接口
    @API("根据wechat-opennId判断是否存在对应用户")
    @RequestMapping(value = "/v1/exist_user/wechat/opend_id",method = RequestMethod.GET)
    public Object existUserByWechatOpenId(@RequestParam("open_id") String openId){
        return userWechatService.existUserByWechatOpenId(openId);
    }

    @API(value = "通过wechat注册用户",auth = false)
    @RequestMapping(value = "/v1/sign_up/wechat",method = RequestMethod.POST)
    public Object signUpByWechat(@RequestBody UserWechatView userWechatView){
        return null;
    }
}
