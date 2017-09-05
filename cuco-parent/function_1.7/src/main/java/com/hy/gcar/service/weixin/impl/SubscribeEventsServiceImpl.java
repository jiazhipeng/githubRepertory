package com.hy.gcar.service.weixin.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.hy.weixin.entity.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.common.utils.FilterStr;
import com.hy.gcar.entity.Marketing;
import com.hy.gcar.entity.Member;
import com.hy.gcar.service.marketing.MarketingService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.weixin.EventsService;
import com.hy.weixin.utils.WeiXinUtils;
import com.thoughtworks.xstream.XStream;


/**
 * 关注事件
 * @author 海易出行
 *
 */
@Service
public class SubscribeEventsServiceImpl implements EventsService{

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
		if(member == null){//添加	
			logger.info("execute {} add {} begin .....",event,msg.getFromUserName());
			member = add(msg, info);
			logger.info("execute {} add {} begin .....",event,msg.getFromUserName());

		}else{//修改
			logger.info("execute {} update {} begin .....",event,msg.getFromUserName());
			member = update(msg,member, info);
			logger.info("execute {} update {} end .....",event,msg.getFromUserName());
		}

		String message = null;
		if(member.getMarketingId()!= null){
			Marketing marketing = new Marketing();
			marketing.setId(member.getMarketingId());
			List<Marketing> marketingList = marketingService.getMarketingList(marketing);
			if(CollectionUtils.isNotEmpty(marketingList)){
				marketing = marketingList.get(0);
				if(StringUtils.isNotEmpty(marketing.getWelcomeLanguage())){
					message = marketing.getWelcomeLanguage();
				}
			}
		}

		return focusOnPush(msg,message);
	}

	/**
	 * 关注的时候推送
	 * @param msg
	 * @return
	 */
	public String focusOnPush(WeChatReceiveMsg msg,String messageStr) throws Exception {
		String xml = "ok";

		this.logger.info("关注的时候发送链接消息。。。。。。。。。。。。。。");

		String message = "终于等到你～ /:rose\n\n现在起，仅需8000元，即可加入极车公社，成为会员，享受极致管家服务，在每一个用车的场景都有一辆百万豪车在等你~\n\n"
				+"点击<a href='http://www.gcarclub.com/h5/new/sale_300.html'>申请入会</a>，可立即领取300元代金券哦~\n\n"
				+ "我在这里等你，不见不散。";
		if(StringUtils.isNotEmpty(messageStr)){
			message = messageStr;
		}
		WxBtnMsgBack wxmsg = new WxBtnMsgBack();
		wxmsg.setToUserName(msg.getFromUserName());
		wxmsg.setFromUserName(msg.getToUserName());
		wxmsg.setCreateTime(System.currentTimeMillis()/1000/60/60+"");
		wxmsg.setMsgType("text");
		wxmsg.setContent(message);

		//hwInfo.setDiskInfos(diskInfos);

		XStream xStream = new XStream();
		xStream.autodetectAnnotations(true);
		xml = xStream.toXML(wxmsg);
		this.logger.info("发送的报文为>>>>>>>：" + xml);
		return xml;
	}

	/**
	 * 修改用户
	 * @param member
	 * @param info
	 * @throws Exception
	 */
	public Member update(WeChatReceiveMsg msg, Member member,W3AccessToken info) throws Exception {
		Member member0 = this.setMember(msg, info);
		member0.setId(member.getId());
		return memberService.updatePersonalMember(member0);
	}

	
	public Member add(WeChatReceiveMsg msg, W3AccessToken info) throws Exception{
		Member member = this.setMember(msg, info);
		member.setCreated(new Date());
		return memberService.createMember(member);

	}
	
	/**
	 * 新增用户
	 * @param msg
	 * @param info
	 * @throws Exception
	 */
	public Member setMember(WeChatReceiveMsg msg, W3AccessToken info) throws Exception {
		int from = 2;// 自主关注 用户来源 0:线下；1:活动；2:自主关注；3:推荐；
		long id = 0L;
		String eventKey = msg.getEventKey();
		if (!StringUtils.isBlank(eventKey)) {
			if (eventKey.startsWith("qrscene_member_")) {// 推荐
				from = 3;
				id = Long.parseLong(eventKey.replace("qrscene_member_", ""));
			} else if (eventKey.startsWith("qrscene_marketing_")) {// 活动
				from = 1;
				id = Long.parseLong(eventKey.replace("qrscene_marketing_", ""));
			}
		}
		Member m = new Member();
		m.setOpenid(msg.getFromUserName());
		m.setFocusType(1);//关注状态 0:取消关注；1:已关注；2:未关注
		if(StringUtils.isNotBlank(info.getNicknamereturn())){
			m.setName(FilterStr.filter(info.getNicknamereturn()));
		}
		if(StringUtils.isBlank(m.getName())){
			m.setName("极车会员");
		}
		this.logger.info("会员名称......" + m.getName());
		m.setImageUrl(info.getHeadimgurlreturn());
		m.setStatuts(1);//会员状态 0:冻结；1:正常；
		m.setFromMember(from);//用户来源 0:线下；1:活动；2:自主关注；3:推荐；4:安卓；5:iOS；6:极车产品
		if (from == 1) {// 活动
			m.setMarketingId(id);
			Marketing marketing = new Marketing();
			marketing.setId(id);
			List<Marketing> marketingList = marketingService.getMarketingList(marketing);
			if (!CollectionUtils.isEmpty(marketingList)) {
				marketing = marketingList.get(0);
				m.setMarketingName(marketing.getName());
			}
		} else if (from == 3) {// 推荐
			m.setRecommendId(id);
			Member member1 = new Member();
			member1.setId(id);
			List<Member> memberList = memberService.selectByCondition(member1);
			if (!CollectionUtils.isEmpty(memberList)) {
				member1 = memberList.get(0);
				m.setRecommendName(member1.getName());
			}
		}
		m.setLasttimeModify(new Date());
		//m.setImageQrcode(request.getServletContext().getRealPath("/"));
		m.setType(0);//会员类型 0:个人会员；1:企业员；
		return m;
	}




}