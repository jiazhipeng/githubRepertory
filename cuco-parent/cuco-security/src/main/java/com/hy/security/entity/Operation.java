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
public class Operation extends PageQuery<Operation> implements Serializable {

    private static final long serialVersionUID = 6569115491233749L;

    private Long id;//主键

    private String name;//操作名称

    @JsonProperty("system_id")
    private Long systemId;//系统ID

    private Date created;//创建时间

    @JsonProperty("modifier_id")
    private Long modifierId;//操作人ID

    private String modifier;//操作人姓名

    @JsonProperty("lasttime_modify")
    private Date lasttimeModify;//最后修改时间
    
    private List<Api> apis;//操作对应的pi
    
    @JsonProperty("api_ids")
    private List<Long> apiIds;//操作对应api的id
    
    @JsonProperty("api_name")
    private String apiName;//api名称(查询用)
    
    @JsonProperty("is_check")
    private Integer isCheck;//是否选中 0:未选中；1:选中；默认为0
    
    
   	public Integer getIsCheck() {
   		return isCheck;
   	}

   	public void setIsCheck(Integer isCheck) {
   		this.isCheck = isCheck;
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

	public List<Api> getApis() {
		return apis;
	}

	public void setApis(List<Api> apis) {
		this.apis = apis;
	}

	public List<Long> getApiIds() {
		return apiIds;
	}

	public void setApiIds(List<Long> apiIds) {
		this.apiIds = apiIds;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	

}
