package com.hy.authorization.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hy.model.PageQuery;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author auto create
 * @since 1.0,2016-07-28 17:18:23
 */
public class Api extends PageQuery implements Serializable {

    private Long id;//

    private String name;//API名称

    private String uri;//

    private Boolean disabled;//是否禁用(true禁用；false不禁用)

    private Boolean auth;//true需要权限验证；false无需权限验证

    @JsonProperty("system_id")
    private Long systemId;//所属系统

    private String method;//请求动作(GET/POST)

    @JsonIgnore
    private Date created;//创建时间

    @JsonProperty("modifier_id")
    private Long modifierId;//操作人ID

    private String modifier;//操作人姓名

    @JsonProperty("lasttime_modify")
    private Date lasttimeModify;//最后修改时间

    public Api() {
    }

    public Api(String uri, String method, Long systemId) {
        this.uri = uri;
        this.method = method;
        this.systemId = systemId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getAuth() {
        return auth;
    }

    public void setAuth(Boolean auth) {
        this.auth = auth;
    }

    public Long getSystemId() {
        return this.systemId;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
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

}
