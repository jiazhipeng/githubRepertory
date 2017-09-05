package com.hy.weixin.entity;
/*{
	   "access_token":"ACCESS_TOKEN",
	   "expires_in":7200,
	   "refresh_token":"REFRESH_TOKEN",
	   "openid":"OPENID",
	   "scope":"SCOPE",
	   "unionid":"o6_bmasdasdsad6_2sgVt7hMZOPfL"
	}*/
public class W3AccessToken {
	private String access_token;
	private String refresh_token;
	private  Integer expires_in;
	private String  headimgurlreturn;
	private  String openid;
	private  String  nicknamereturn;
	
	
	public String getHeadimgurlreturn() {
		return headimgurlreturn;
	}
	public void setHeadimgurlreturn(String headimgurlreturn) {
		this.headimgurlreturn = headimgurlreturn;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public Integer getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNicknamereturn() {
		return nicknamereturn;
	}
	public void setNicknamereturn(String nicknamereturn) {
		this.nicknamereturn = nicknamereturn;
	}
	

}