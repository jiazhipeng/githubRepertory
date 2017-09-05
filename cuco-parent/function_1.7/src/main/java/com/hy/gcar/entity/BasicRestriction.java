package com.hy.gcar.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author auto create
 * @since 1.0,2016-11-07 11:28:06
 */
public class BasicRestriction implements Serializable {

    private static final long serialVersionUID = 9584036362890473L;

    private Long id;//ID

    private Integer cityId;//服务城市ID

    private String cityName;//服务城市名称

    private Date restrictionsDate;//限行时间；格式：年月日

    private String restrictions;//限行号码；例：1,2,3

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCityId() {
        return this.cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Date getRestrictionsDate() {
        return this.restrictionsDate;
    }

    public void setRestrictionsDate(Date restrictionsDate) {
        this.restrictionsDate = restrictionsDate;
    }

    public String getRestrictions() {
        return this.restrictions;
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

}
