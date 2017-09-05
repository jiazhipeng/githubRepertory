package cn.cuco.service.wechat.event.impl;

import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.cuco.entity.Member;
import cn.cuco.service.member.info.MemberService;
import cn.cuco.service.wechat.event.EventsService;
import cn.cuco.wechat.entity.AccessToken;
import cn.cuco.wechat.entity.WeChatReceiveMsg;
import cn.cuco.wechat.util.AccessTokenUtil;

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
	
	@Override
	public String response(WeChatReceiveMsg msg) throws Exception {
		// 如果是事件推送信息
		String event = msg.getEvent();
		Member member = memberService.getMemberByOpenId(msg.getFromUserName());
		AccessToken info = null;
		try{
			info = AccessTokenUtil.getNickname(msg.getFromUserName());
		}catch(Exception e){
			info = new AccessToken();
		}
		if(null == info){
			info = new AccessToken();
		}
		if(member != null){	
			logger.info("execute {} update {} begin .....",event,msg.getFromUserName());
			Member member0 = new Member();
			member0.setId(member.getId());
			member0.setFocusStatus(2);//取消关注
			if(StringUtils.isBlank(member.getImageUrl())){
				member0.setImageUrl(info.getHeadimgurlreturn());
			}
			member0.setLasttimeModify(new Date());
			memberService.unsubscribe(member0);
			logger.info("execute {} update {} end .....",event,msg.getFromUserName());

		}
		return "ok";

	}

}
