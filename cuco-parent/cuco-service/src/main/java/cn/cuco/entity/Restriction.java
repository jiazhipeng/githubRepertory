package cn.cuco.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author auto create
 * @since 1.0,2017-02-24 14:44:55
 */
public class Restriction implements Serializable {

    private static final long serialVersionUID = 7867679951915455L;

    private Long id;//ID

    private Long cityId;//服务城市ID

    private String cityCode;//城市编码

    private String cityName;//服务城市名称

    private Date restrictionsDate;//限行时间；格式：年月日

    private String restrictions;//限行号码；例：1,2,3

    private String beginDate;//限行开始时间

    private String endDate;//限行结束时间

    private String modifier;//操作人姓名

    private Long modifierId;//操作人id

    private Date lasttimeModify;//最后修改时间

    private Date searchBeginDate;//开始时间（查询）
    
    private Date searchEndDate;//结束时间（查询）
    
    private Date searchDate;//结束时间（查询）
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCityId() {
        return this.cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Date getRestrictionsDate() {
        return this.restrictionsDate;
    }

    public void setRestrictionsDate(Date restrictionsDate) {
        this.restrictionsDate = restrictionsDate;
    }

    public String getRestrictions() {
        return this.restrictions;
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    public String getBeginDate() {
        return this.beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getModifier() {
        return this.modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Long getModifierId() {
        return this.modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    public Date getLasttimeModify() {
        return this.lasttimeModify;
    }

    public void setLasttimeModify(Date lasttimeModify) {
        this.lasttimeModify = lasttimeModify;
    }

	public Date getSearchBeginDate() {
		return searchBeginDate;
	}

	public void setSearchBeginDate(Date searchBeginDate) {
		this.searchBeginDate = searchBeginDate;
	}

	public Date getSearchEndDate() {
		return searchEndDate;
	}

	public void setSearchEndDate(Date searchEndDate) {
		this.searchEndDate = searchEndDate;
	}

	public Date getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}

	
}
