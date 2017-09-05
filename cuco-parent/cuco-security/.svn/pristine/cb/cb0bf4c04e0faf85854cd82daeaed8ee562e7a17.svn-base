package com.hy.security.view.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hy.security.entity.Api;

/**
 * 
 * @author auto create
 * @since 1.0,2016-07-28 17:18:24
 */
public class OperationResView implements Serializable {

	private static final long serialVersionUID = -4519037314144451900L;

	private Long id;//主键

    private String name;//操作名称

    @JsonProperty("system_id")
    private Long systemId;//系统ID

    @JsonProperty("owned_apis")
    private List<Api> apis;//操作对应的api
    
    @JsonProperty("other_apis")
    private List<Api> otherApis;//所在系统中操作不包含的API列表

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSystemId() {
		return systemId;
	}

	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}

	public List<Api> getApis() {
		return apis;
	}

	public void setApis(List<Api> apis) {
		this.apis = apis;
	}

	public List<Api> getOtherApis() {
		return otherApis;
	}

	public void setOtherApis(List<Api> otherApis) {
		this.otherApis = otherApis;
	}

}
