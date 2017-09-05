package com.hy.security.entity;

import java.io.Serializable;

/**
 * 
 * @author auto create
 * @since 1.0,2016-07-28 17:18:24
 */
public class UserCity implements Serializable {

    private static final long serialVersionUID = 6133156785962324L;

    private Long id;//

    private Long userId;//

    private Long cityId;//

    private String name;//
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserCity() {
		super();
	}

	public UserCity(Long userId) {
		super();
		this.userId = userId;
	}

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

    public Long getCityId() {
        return this.cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

}
