package com.hy.authorization.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hy.model.PageQuery;

/**
 * 
 * @author auto create
 * @since 1.0,2016-07-28 17:18:24
 */
public class Organization extends PageQuery<Organization> implements Serializable {

    private static final long serialVersionUID = 6046555750714924L;

    private Long id;//

    private String name;//

    private String description;//

    private String code;//

    private Integer isValue;//数据有效性 0:有效；1:无效；默认为0
    @JsonIgnore
    private Date created;//创建时间
    @JsonIgnore
    private Long modifierId;//操作人ID

    private String modifier;//操作人姓名

    @JsonProperty("lasttime_modify")
    private Date lasttimeModify;//最后修改时间
    
    @JsonIgnore
    private Long userId;//用户ID
    
    @JsonProperty("system_id")
    private Long systemId;//系统ID
    
    @JsonProperty("menu_id")
    private Long menuId;//菜单ID
    
    @JsonIgnore
    private Long menuOperationId;//菜单操作关联ID
    
    @JsonIgnore
    private Long operationId;//操作ID
    
    @JsonProperty("parent_code")
    private String parentCode;//父级code
    
    @JsonProperty("parent_id")
    private String parentId;//父级ID
    
    @JsonProperty("operations_id")
    private String operationsId;//操作集合
    
    @JsonProperty("system_name")
    private String systemName;//系统ID
    
    private List<Role> roles;
    
    private List<Organization> Organizations;
    
    public List<Organization> getOrganizations() {
		return Organizations;
	}

	public void setOrganizations(List<Organization> organizations) {
		Organizations = organizations;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getOperationsId() {
		return operationsId;
	}

	public void setOperationsId(String operationsId) {
		this.operationsId = operationsId;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public Organization(String code) {
		super();
		this.code = code;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Long getMenuOperationId() {
		return menuOperationId;
	}

	public void setMenuOperationId(Long menuOperationId) {
		this.menuOperationId = menuOperationId;
	}

	public Long getOperationId() {
		return operationId;
	}

	public void setOperationId(Long operationId) {
		this.operationId = operationId;
	}

	public Organization() {
		super();
	}

	public Organization(Long userId, Long systemId) {
		super();
		this.userId = userId;
		this.systemId = systemId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getSystemId() {
		return systemId;
	}

	public void setSystemId(Long systemId) {
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
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

}
