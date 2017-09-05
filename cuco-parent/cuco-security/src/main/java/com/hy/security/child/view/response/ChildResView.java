package com.hy.security.child.view.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hy.security.entity.Api;
import com.hy.security.entity.Menu;
import com.hy.security.entity.User;


public class ChildResView {

	@JsonProperty("user")
	private User user;
	
	@JsonProperty("user_apis")
	private List<Api> userApis;
	
	@JsonProperty("user_menus")
	private List<Menu> userMenus;
	
	@JsonProperty("no_security_apis")
	private List<Api> noSecurityApis;

	@JsonProperty("child_token")
	private String childToken;
	
	
	public ChildResView(List<Api> noSecurityApis) {
		this.noSecurityApis = noSecurityApis;
	}

	public ChildResView(User user) {
		super();
		this.user = user;
	}

	

	public ChildResView() {
		super();
	}

	public ChildResView(List<Api> userApis, List<Menu> userMenus) {
		super();
		this.userApis = userApis;
		this.userMenus = userMenus;
	}

	public ChildResView(String childToken) {
		super();
		this.childToken = childToken;
	}

	public String getChildToken() {
		return childToken;
	}

	public void setChildToken(String childToken) {
		this.childToken = childToken;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Api> getNoSecurityApis() {
		return noSecurityApis;
	}

	public void setNoSecurityApis(List<Api> noSecurityApis) {
		this.noSecurityApis = noSecurityApis;
	}

	public List<Api> getUserApis() {
		return userApis;
	}

	public void setUserApis(List<Api> userApis) {
		this.userApis = userApis;
	}

	public List<Menu> getUserMenus() {
		return userMenus;
	}

	public void setUserMenus(List<Menu> userMenus) {
		this.userMenus = userMenus;
	}
	
	
	
}
