package cn.cuco.entity;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import cn.cuco.page.PageQuery;

/**
 * 
 * @author auto create
 * @since 1.0,2017-02-22 14:30:40
 */
public class City extends PageQuery implements Serializable {

    private static final long serialVersionUID = 6072369121241678L;

    private Long id;//

    private String code;//编码

    private String province;//省

    private String city;//市

    private String district;//区

    private String parent;//父级ID

    public String getName(){
        if(StringUtils.isNotEmpty(this.getDistrict())){
            return this.getDistrict();
        }
        if(StringUtils.isNotEmpty(this.getCity())){
            return this.getCity();
        }
        if(StringUtils.isNotEmpty(this.getProvince())){
            return this.getProvince();
        }
        return "";
    }
    
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

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getParent() {
        return this.parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

}
