package com.hy.security.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author auto create
 * @since 1.0,2016-11-14 11:36:04
 */
public class AccessToken implements Serializable {

    private static final long serialVersionUID = 1627251833965197L;

    private Long id;//

    private Long userId;//使用token的用户

    private Long systemId;//当前登录哪个系统

    private String token;//用户token

    private Date createDate;//创建时间

    private Integer liveTime;//token有效时间,单位：秒

    private Date invalidTime;//token失效时间

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSystemId() {
        return this.systemId;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getLiveTime() {
        return this.liveTime;
    }

    public void setLiveTime(Integer liveTime) {
        this.liveTime = liveTime;
    }

    public Date getInvalidTime() {
        return this.invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

}
