package com.hy.project.service.wechat.notice.impl;

import com.alibaba.fastjson.JSON;
import com.hy.project.service.wechat.notice.WechatNoticeService;
import com.hy.weixin.entity.WechatNotice;
import com.hy.weixin.entity.WxTemplate;
import com.hy.weixin.push.dao.WechatNoticeMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WechatNoticeServiceImpl implements WechatNoticeService {

	@Autowired
    WechatNoticeMapper wechatNoticeMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return wechatNoticeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(WechatNotice record) {
        return wechatNoticeMapper.insert(record);
    }

    @Override
    public int insertSelective(WechatNotice record) {
        return wechatNoticeMapper.insertSelective(record);
    }

    @Override
    public WechatNotice selectByPrimaryKey(Long id) {
        return wechatNoticeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(WechatNotice record) {
        return wechatNoticeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WechatNotice record) {
        return wechatNoticeMapper.updateByPrimaryKey(record);
    }

    /**
     * 持久化保存消息
     * @param wxTemplate
     * @throws Exception
     */
    @Override
    public void saveMessage(WxTemplate wxTemplate, WechatNotice wechatNotice){

        Long memberID = wxTemplate.getMemberId();
        String memberName = wxTemplate.getMemberName();

        wechatNotice.setCreated(new Date());
        wechatNotice.setJsondata(JSON.toJSONString(wxTemplate));
        wechatNotice.setCount(1);
        wechatNotice.setOpenid(wxTemplate.getTouser());

        wechatNotice.setMemberId(memberID);
        wechatNotice.setMemberName(memberName);
        wechatNotice.setUrl(wxTemplate.getUrl());
        wechatNotice.setSended(new Date());
        wechatNotice.setTemplateId(wxTemplate.getTemplate_id());

        this.insertSelective(wechatNotice);
    }
}
