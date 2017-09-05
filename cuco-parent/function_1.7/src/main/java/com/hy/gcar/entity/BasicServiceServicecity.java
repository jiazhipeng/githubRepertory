package com.hy.gcar.entity;

import java.io.Serializable;

/**
 * 
 * @author auto create
 * @since 1.0,2016-09-19 16:39:26
 */
public class BasicServiceServicecity implements Serializable {

    private static final long serialVersionUID = 4396902648295032L;

    private Long id;//

    private Integer cityId;//城市ID

    private String cityName;//城市名称

    private String cityCode;//城市编码

    private Long serviceId;//服务设置ID

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

    public String getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Long getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

}
