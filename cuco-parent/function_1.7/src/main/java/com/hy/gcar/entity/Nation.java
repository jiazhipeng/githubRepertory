package com.hy.gcar.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Nation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private Integer id;

    private String code;

    private String province;

    private String city;

    private String district;

    private String parent;
    
    private List<Nation> sub = new ArrayList<Nation>();
    
    private String name;
    
    private List<String> parentList;
    
    
    
    

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getProvince() {
        if(province == null){
            return "";
        }
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        if(city == null){
            return  "";
        }
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        if(district == null){
            return  "";
        }
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent == null ? null : parent.trim();
    }

	public List<String> getParentList() {
		return parentList;
	}

	public void setParentList(List<String> parentList) {
		this.parentList = parentList;
	}

	

	public List<Nation> getSub() {
		return sub;
	}

	public void setSub(List<Nation> sub) {
		this.sub = sub;
	}

	public String getName() {
		if(StringUtils.isNotBlank(this.province)){
			return this.province;
		}else if(StringUtils.isNotBlank(this.city)){
			return this.city;
		}else if(StringUtils.isNotEmpty(this.district)){
			return this.district;
		}

		if(this.name == null){
		    return "";
        }
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    
}