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
public class Role  extends PageQuery<Role> implements Serializable {

    private static final long serialVersionUID = 4968774805271093L;

    private Long id;//

    private String name;//

    private String code;//
    
    @JsonProperty("organization_id")
    private Long organizationId;//
    
    @JsonProperty("system_id")
    private Long systemId;//

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
    
    @JsonProperty("menu_id")
    private Long menuId;//菜单ID

    @JsonIgnore
    private Long menuOperationId;//菜单操作关联ID
    
    @JsonProperty("operation_id")
    private Long operationId;//操作ID
    
    @JsonProperty("operation_ids")
    private String operationIds;//操作IDs
    
    private List<Role> roleList;
    
    @JsonProperty("system_name")
    private String systemName;//系统ID
    
    @JsonProperty("organization_name")
    private String organizationName;//系统ID
    
    private  List<Organization> organizations;//组织机构集合

    @JsonProperty("unique_code")
    private String uniqueCode;//角色唯一标识(用于程序获取指定角色)


    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    
    public List<Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
    
    public String getOperationIds() {
		return operationIds;
	}

	public void setOperationIds(String operationIds) {
		this.operationIds = operationIds;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
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

	public Role() {
		super();
	}

	public Role(Long organizationId, Long systemId) {
		super();
		this.organizationId = organizationId;
		this.systemId = systemId;
	}

	public Role(Long organizationId) {
		super();
		this.organizationId = organizationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getOrganizationId() {
        return this.organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getSystemId() {
        return this.systemId;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
