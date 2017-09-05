package com.hy.gcar.entity;

import java.io.Serializable;

public class AuthJsonObject implements Serializable{

	/**    
	* serialVersionUID:TODO
	* author:JIAZHIPENG
	*/    
	private static final long serialVersionUID = 1L;
	
	/**
	 * 	网页授权接口调用凭 用户授权token
	 */
	private String access_token;
	
	/**
	 * 有效时间
	 */
	private int expires_in;
	
	/**
	 * 用户刷新access_token
	 */
	private String refresh_token;
	
	/**
	 * 用户openId
	 */
	private String openid;
	
	/**
	 * 用户授权作用域
	 */
	private String scope;
	
	/**
	 * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
	 */
	private String unionid;
	
	/**
	 * 错误code码
	 */
	private int errcode;
	
	/**
	 * 错误 message
	 */
	private String errmsg;

	public String getAccess_token() {
		return access_token;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public String getOpenid() {
		return openid;
	}

	public String getScope() {
		return scope;
	}

	public String getUnionid() {
		return unionid;
	}

	public int getErrcode() {
		return errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

}
