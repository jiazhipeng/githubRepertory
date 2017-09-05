package com.hy.gcar.entity;

import java.io.Serializable;

/**
 * wechat 用户信息
* description：    
* author：JIAZHIPENG    
* time：2016-9-8 下午3:29:19    
* 修改时间：2016-9-8 下午3:29:19    
* 修改备注：
 */
public class WeChatUserInfo implements Serializable{

	  
	/**    
	* serialVersionUID:TODO
	* author:JIAZHIPENG
	*/    
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户openId
	 */
	private String openid;
	
	/**
	 * 性别
	 */
	private int sex;
	
	/**
	 * 姓名
	 */
	private String nickname;
	
	/**
	 * 城市
	 */
	private String city;
	
	/**
	 * 国家
	 */
	private String country;
	
	/**
	 * 头像
	 */
	private String headimgurl;
	
	/**
	 * 特权
	 */
	private String privilege;
	
	/**
	 *  unionid
	 */
	private String unionid;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	
}
