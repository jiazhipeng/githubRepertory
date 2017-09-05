package com.hy.authorization.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author auto create
 * @since 1.0,2016-07-28 17:18:23
 */
public class Menu implements Serializable {

    private static final long serialVersionUID = 7178495541511844L;

    private Long id;//主键

    private String name;//菜单名称

    private Integer isValue;//数据有效性 0:有效；1:无效；默认为0

    private String link;//菜单地址
    
    @JsonProperty("parent_id")
    private Integer parentId;//父级菜单ID
    
    @JsonProperty("system_id")
    private Long systemId;//系统ID

    private Date created;//创建时间

    private Long modifierId;//操作人ID

    private String modifier;//操作人姓名

    private Date lasttimeModify;//最后修改时间
   
    private Long roleId;
    
    @JsonProperty("is_check")
    private Integer isCheck;//是否选中 0:未选中；1:选中；默认为0
    
    
	public Integer getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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

    public Integer getIsValue() {
        return this.isValue;
    }

    public void setIsValue(Integer isValue) {
        this.isValue = isValue;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getParentId() {
        return this.parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Long getSystemId() {
        return this.systemId;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
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
