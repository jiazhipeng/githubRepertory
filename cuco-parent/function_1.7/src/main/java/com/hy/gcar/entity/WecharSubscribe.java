package com.hy.gcar.entity;

import com.hy.weixin.entity.W3AccessToken;
import com.hy.weixin.entity.WeChatReceiveMsg;

public class WecharSubscribe {
	
	private W3AccessToken w3AccessToken;
	
	private String event;
	
	private Member member;
	
	private WeChatReceiveMsg weChatReceiveMsg;
	

	public W3AccessToken getW3AccessToken() {
		return w3AccessToken;
	}

	public void setW3AccessToken(W3AccessToken w3AccessToken) {
		this.w3AccessToken = w3AccessToken;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public WeChatReceiveMsg getWeChatReceiveMsg() {
		return weChatReceiveMsg;
	}

	public void setWeChatReceiveMsg(WeChatReceiveMsg weChatReceiveMsg) {
		this.weChatReceiveMsg = weChatReceiveMsg;
	}
	
	
	

}
