package com.hy.project.service.wechat.notice;


import com.hy.weixin.entity.WechatNotice;
import com.hy.weixin.entity.WxTemplate;

public interface WechatNoticeService {
    int deleteByPrimaryKey(Long id);

    int insert(WechatNotice record);

    int insertSelective(WechatNotice record);

    WechatNotice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WechatNotice record);

    int updateByPrimaryKey(WechatNotice record);

    void saveMessage(WxTemplate wxTemplate, WechatNotice wechatNotice);

}
