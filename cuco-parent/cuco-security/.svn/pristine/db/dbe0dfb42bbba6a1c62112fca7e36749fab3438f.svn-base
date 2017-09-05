package com.hy.security.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 短信验证码信息
 */
public class Captcha implements Serializable{
	
	private static final long serialVersionUID = -5290673428796782328L;
	private Long id;
	private String mobile;
	private String code;//验证码
	private Date createDate;//验证码创建时间
	private boolean isUsed;//是否用过
	private Long count;//当天条用总次数
	private Date date;
	
	public Captcha() {}

	public Captcha(String mobile,String code,Long count,Date date){
		this.mobile = mobile;
		this.code = code;
		this.count = count;
		this.date = date;
	}
	public Captcha(String code, Date createDate, boolean isUsed, Long count) {
		super();
		this.code = code;
		this.createDate = createDate;
		this.isUsed = isUsed;
		this.count = count;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
