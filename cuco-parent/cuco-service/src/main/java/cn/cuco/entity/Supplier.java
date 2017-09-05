package cn.cuco.entity;

import java.io.Serializable;
import java.util.Date;

import cn.cuco.page.PageQuery;

/**
 * 
 * @author auto create
 * @since 1.0,2017-02-25 14:44:02
 */
public class Supplier extends PageQuery implements Serializable {

    private static final long serialVersionUID = 2802265223476631L;

    private Long id;//

    private String supplierName;//供应商名称

    private String supplierReferred;//供应商简称

    private String firstContactName;//第一联系人姓名

    private String firstContactMobile;//第一联系人手机号

    private String secondContactName;//第二联系人姓名

    private String secondContactMobile;//第二联系人电话

    private String email;//联系邮箱

    private Long insuranceId;//保险公司ID

    private String insuranceName;//保险公司名称

    private String outDangerName;//出险联系人

    private String outDangerMobile;//出险联系电话

    private String repairShops;//合作维修厂

    private String repairShopsMobile;//合作维修电话

    private Date created;//创建时间

    private Date lasttimeModify;//最后修改时间

    private String modifier;//操作人姓名

    private Long modifierId;//操作人ID

    private Integer valid;//是否删除 0：删除；1：未删除
    
    private Integer carCount;//供应数量
    

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierName() {
        return this.supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierReferred() {
        return this.supplierReferred;
    }

    public void setSupplierReferred(String supplierReferred) {
        this.supplierReferred = supplierReferred;
    }

    public String getFirstContactName() {
        return this.firstContactName;
    }

    public void setFirstContactName(String firstContactName) {
        this.firstContactName = firstContactName;
    }

    public String getFirstContactMobile() {
        return this.firstContactMobile;
    }

    public void setFirstContactMobile(String firstContactMobile) {
        this.firstContactMobile = firstContactMobile;
    }

    public String getSecondContactName() {
        return this.secondContactName;
    }

    public void setSecondContactName(String secondContactName) {
        this.secondContactName = secondContactName;
    }

    public String getSecondContactMobile() {
        return this.secondContactMobile;
    }

    public void setSecondContactMobile(String secondContactMobile) {
        this.secondContactMobile = secondContactMobile;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getInsuranceId() {
        return this.insuranceId;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getInsuranceName() {
        return this.insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getOutDangerName() {
        return this.outDangerName;
    }

    public void setOutDangerName(String outDangerName) {
        this.outDangerName = outDangerName;
    }

    public String getOutDangerMobile() {
        return this.outDangerMobile;
    }

    public void setOutDangerMobile(String outDangerMobile) {
        this.outDangerMobile = outDangerMobile;
    }

    public String getRepairShops() {
        return this.repairShops;
    }

    public void setRepairShops(String repairShops) {
        this.repairShops = repairShops;
    }

    public String getRepairShopsMobile() {
        return this.repairShopsMobile;
    }

    public void setRepairShopsMobile(String repairShopsMobile) {
        this.repairShopsMobile = repairShopsMobile;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLasttimeModify() {
        return this.lasttimeModify;
    }

    public void setLasttimeModify(Date lasttimeModify) {
        this.lasttimeModify = lasttimeModify;
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

    public Integer getValid() {
        return this.valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

	public Integer getCarCount() {
		return carCount;
	}

	public void setCarCount(Integer carCount) {
		this.carCount = carCount;
	}
    
}
