package com.hy.security.service.user;


import com.hy.security.entity.UserWechat;
import com.hy.security.view.response.UserResView;

/**
 * Created by WangShuai on 2016/7/21.
 */
public interface UserWechatService {

    /**
     * 添加用户微信信息
     * @param
     * @param userWechat
     * @return
     */
    public UserResView addUserWechat(UserWechat userWechat);

    /**
     * 更新用户微信信息
     * @param
     * @param userWechat
     * @return
     */
    public UserResView updateUserWechat(UserWechat userWechat);

    /**
     * 根据微信opendId查询用户
     * @param openId
     * @return
     */
    public UserWechat findByWechatOpenId(String openId);

    /**
     * 根据微信openId判断是否存在用户
     * @param opedId
     * @return
     */
    public Boolean existUserByWechatOpenId(String opedId);
}
