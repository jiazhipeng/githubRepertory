package com.hy.security.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author duzc
 * @since 1.0,2016-08-02 17:18:23
 */
public class ImageQRCodeInfo implements Serializable {

    private static final long serialVersionUID = 7178495541511844L;
    
    private int sceneId;//场景ID 临时二维码时为32位非0整型，永久二维码时最大值为100000
    private boolean isScanned  = false;//二维码是否失效
    private Date createDate;
    
	public int getSceneId() {
		return sceneId;
	}
	public void setSceneId(int sceneId) {
		this.sceneId = sceneId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public boolean isScanned() {
		return isScanned;
	}
	public void setScanned(boolean isScanned) {
		this.isScanned = isScanned;
	}
}
