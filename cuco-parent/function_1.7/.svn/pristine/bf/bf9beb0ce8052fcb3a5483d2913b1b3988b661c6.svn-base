package com.hy.gcar.service.weixin.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.gcar.entity.Member;
import com.hy.gcar.service.marketing.MarketingService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.weixin.EventsService;
import com.hy.weixin.entity.W3AccessToken;
import com.hy.weixin.entity.WeChatReceiveMsg;
import com.hy.weixin.utils.WeiXinUtils;

/**
 * 取消关注事件
 * @author 海易出行
 *
 */
@Service
public class UnsubscribeEventsServiceImpl implements EventsService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MemberService memberService;
	
	@Autowired
	MarketingService marketingService;
	@Override
	public String response(WeChatReceiveMsg msg) throws Exception {
		// 如果是事件推送信息
		String event = msg.getEvent();
		Member member = memberService.findMemberByOpenID(msg.getFromUserName());
		W3AccessToken info = null;
		try{
			info = WeiXinUtils.getNickname(null,msg.getFromUserName());
		}catch(Exception e){
			info = new W3AccessToken();
		}
		if(null == info){
			info = new W3AccessToken();
		}
		if(member != null){	
			logger.info("execute {} update {} begin .....",event,msg.getFromUserName());
			Member member0 = new Member();
			member0.setId(member.getId());
			member0.setFocusType(0);//取消关注
			if(StringUtils.isBlank(member.getImageUrl())){
				member0.setImageUrl(info.getHeadimgurlreturn());
			}
			member0.setLasttimeModify(new Date());
			memberService.updatePersonalMember(member0);
			logger.info("execute {} update {} end .....",event,msg.getFromUserName());

		}
		return "ok";

	}

}
