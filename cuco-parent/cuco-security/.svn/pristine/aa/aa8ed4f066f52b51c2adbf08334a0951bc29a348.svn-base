package com.hy.security.view.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author duzc
 * @since 1.0,2016-08-02 17:18:23
 */
public class ImageQRCodeResView implements Serializable {

	private static final long serialVersionUID = -2698142230398975317L;
	
	@JsonProperty("image_string")
    private String imageString;//二维码图片字符串
    private String token;//换取结果token
    @JsonProperty("expire_seconds")
    private Integer expireSeconds;
    
	public ImageQRCodeResView(String imageString, String token,Integer expireSeconds) {
		super();
		this.imageString = imageString;
		this.token = token;
		this.expireSeconds = expireSeconds;
	}
	public String getImageString() {
		return imageString;
	}
	public void setImageString(String imageString) {
		this.imageString = imageString;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getExpireSeconds() {
		return expireSeconds;
	}
	public void setExpireSeconds(Integer expireSeconds) {
		this.expireSeconds = expireSeconds;
	}
    
    
}
