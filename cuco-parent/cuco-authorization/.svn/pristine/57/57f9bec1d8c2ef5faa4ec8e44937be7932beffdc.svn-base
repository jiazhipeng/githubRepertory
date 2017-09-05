package com.hy.authorization.view.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChildReqView {
	
	@JsonProperty("user_token")
	private String userToken;//用户的token
	
	@JsonProperty("system_id")
	private Long systemId;
	@JsonProperty("system_code")
	private String systemCode;
	
	@JsonProperty("security_type")
	private Integer securityType;//权限类型，1：获取用户权限，2：获取无权限API集合
	
	
	@JsonProperty("app_id")
    private String appId;//

	@JsonProperty("app_secret")
    private String appSecret;//

	
	
	
	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public Long getSystemId() {
		return systemId;
	}

	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}

	public Integer getSecurityType() {
		return securityType;
	}

	public void setSecurityType(Integer securityType) {
		this.securityType = securityType;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	
	
	
	
}
