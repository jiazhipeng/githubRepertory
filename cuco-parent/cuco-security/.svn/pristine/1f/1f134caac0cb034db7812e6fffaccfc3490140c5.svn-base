package com.hy.session.common;

import com.alibaba.fastjson.JSON;
import com.hy.common.utils.JedisUtils;

/**
 * HySession的接口
 * @author duzhencheng
 * @author 1mobility
 */
public class HySessionImpl implements HySession {
	
	private String sessionKey;
	
	private String categoryKey;
	
	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getCategoryKey() {
		return categoryKey;
	}

	public void setCategoryKey(String categoryKey) {
		this.categoryKey = categoryKey;
	}

	//过期时间，不传入默认30分钟过期
	public int liveTime = 30*60;
	
	public HySessionImpl(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	
	public void setAttribute(String key, Object obj,int liveTime) {
		if (key == null) {
			throw new IllegalArgumentException("key not be null!");
		}
		if(obj != null) {
			JedisUtils.setObject(key, JSON.toJSON(obj), liveTime);
		}
	}
	
	public void setAttribute(String key, Object obj) {
		if (key == null) {
			throw new IllegalArgumentException("key not be null!");
		}
		if(obj != null) {
			JedisUtils.setObject(key, JSON.toJSON(obj), liveTime);
		}
	}
	
	public Object getAttribute(String key) {
		if (key == null) {
			throw new IllegalArgumentException("key not be null!");
		}
		return JedisUtils.getObject(key);
	}

	public void removeAttribute(String key) {
		if (key == null) {
			throw new IllegalArgumentException("key not be null!");
		}
		JedisUtils.delObject(key);
	}

}
