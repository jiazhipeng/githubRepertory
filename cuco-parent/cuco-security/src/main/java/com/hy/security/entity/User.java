package com.hy.security.entity;

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
public class User extends PageQuery<User>  implements Serializable {

    private static final long serialVersionUID = 8317127905401969L;

    private Long id;//

    private String name;//用户姓名

    private String phone;//用户手机号

    private Integer gender;//性别

    private String email;//邮箱

    private String source;//注册来源

    private Integer isValue;//数据有效性 0:有效；1:无效；默认为0

    @JsonIgnore
    private Date created;//创建时间

    @JsonIgnore
    private Long modifierId;//操作人ID

    private String modifier;//操作人姓名

    @JsonProperty("lasttime_modify")
    private Date lasttimeModify;//最后修改时间
    
    @JsonProperty("openid")
    private String wechatOpenId;//微信openID

    private Integer freezing;//true已冻结；false未冻结

    @JsonIgnore
    private Integer isAdmin;//是否为admin:0,不是；1是；
    
    private List<Api> apis;

    private List<Menu> menus;
    
    private List<Role> roles;
    
    private List<UserCity> citys;
    
    private List<System> systems;
    
    
    private System system;//上次访问系统信息
    
    
    
    
    
    public System getSystem() {
		return system;
	}

	public void setSystem(System system) {
		this.system = system;
	}

	@JsonIgnore
    private Long operationId;//操作ID
    
    @JsonIgnore
    private Long roleId;//角色ID
    
    @JsonIgnore
    private Long menuId;//角色ID

	@JsonProperty("role_ids")
    private String roleIds;
    
    @JsonProperty("city_ids")
    private String cityIds;
    
    @JsonProperty("system_id")
    private Long systemId;//系统ID
    
    @JsonProperty("organization_id")
    private Long organizationId;//组织结构ID
    //验证码
    private String message_code;
    //验证码
    private String user_token;


    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Long getMenuId() {
		return menuId;
	}

	public String getUser_token() {
		return user_token;
	}

	public void setUser_token(String user_token) {
		this.user_token = user_token;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getOperationId() {
		return operationId;
	}

	public void setOperationId(Long operationId) {
		this.operationId = operationId;
	}

	public List<System> getSystems() {
		return systems;
	}

	public void setSystems(List<System> systems) {
		this.systems = systems;
	}
    public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public String getMessage_code() {
		return message_code;
	}

	public void setMessage_code(String message_code) {
		this.message_code = message_code;
	}
    public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getSystemId() {
		return systemId;
	}

	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}

	public List<UserCity> getCitys() {
		return citys;
	}

	public void setCitys(List<UserCity> citys) {
		this.citys = citys;
	}

	public String getCityIds() {
		return cityIds;
	}

	public void setCityIds(String cityIds) {
		this.cityIds = cityIds;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

    public List<Api> getApis() {
		return apis;
	}

	public void setApis(List<Api> apis) {
		this.apis = apis;
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

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getGender() {
        return this.gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public String getWechatOpenId() {
        return this.wechatOpenId;
    }

    public void setWechatOpenId(String wechatOpenId) {
        this.wechatOpenId = wechatOpenId;
    }

    public Integer getFreezing() {
        return this.freezing;
    }

    public void setFreezing(Integer freezing) {
        this.freezing = freezing;
    }

}
