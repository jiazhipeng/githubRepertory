package com.hy.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hy.model.PageQuery;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author auto create
 * @since 1.0,2016-07-28 17:18:24
 */
public class System extends PageQuery<System> implements Serializable {

    private static final long serialVersionUID = 1293151485173265L;

    private Long id;//

    private String code;//系统标识

    private String name;//系统名称

    @JsonProperty("route_url")
    private String routeUrl;//根路由地址

    private String description;//系统描述

    @JsonIgnore
    private String appId;//

    @JsonIgnore
    private String appSecret;//

    @JsonProperty("api_sync_url")
    private String apiSyncUrl;//系统API同步j接口

    @JsonProperty("organization_id")
    private Long organizationId;//部门ID

    private String type;//main主系统；sub子系统；

    @JsonProperty("is_value")
    private Integer isValue;//数据有效性 0:有效；1:无效；默认为0

    private Date created;//创建时间

    @JsonProperty("modifier_id")
    private Long modifierId;//操作人ID

    private String modifier;//操作人姓名

    @JsonProperty("lasttime_modify")
    private Date lasttimeModify;//最后修改时间

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRouteUrl() {
        return this.routeUrl;
    }

    public void setRouteUrl(String routeUrl) {
        this.routeUrl = routeUrl;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return this.appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getApiSyncUrl() {
        return this.apiSyncUrl;
    }

    public void setApiSyncUrl(String apiSyncUrl) {
        this.apiSyncUrl = apiSyncUrl;
    }

    public Long getOrganizationId() {
        return this.organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIsValue() {
        return this.isValue;
    }

    public void setIsValue(Integer isValue) {
        this.isValue = isValue;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getModifierId() {
        return this.modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    public String getModifier() {
        return this.modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getLasttimeModify() {
        return this.lasttimeModify;
    }

    public void setLasttimeModify(Date lasttimeModify) {
        this.lasttimeModify = lasttimeModify;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
