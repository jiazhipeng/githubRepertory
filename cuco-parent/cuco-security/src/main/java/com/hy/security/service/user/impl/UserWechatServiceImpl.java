package com.hy.security.service.user.impl;

import com.hy.security.entity.UserWechat;
import com.hy.security.service.user.UserWechatService;
import com.hy.security.view.response.UserResView;
import org.springframework.stereotype.Service;

/**
 * Created by WangShuai on 2016/7/25.
 */
@Service
public class UserWechatServiceImpl implements UserWechatService {
    @Override
    public UserResView addUserWechat(UserWechat userWechat) {
        return null;
    }

    @Override
    public UserResView updateUserWechat(UserWechat userWechat) {
        return null;
    }

    @Override
    public UserWechat findByWechatOpenId(String openId) {
        return null;
    }

    @Override
    public Boolean existUserByWechatOpenId(String opedId) {
        return null;
    }
}
